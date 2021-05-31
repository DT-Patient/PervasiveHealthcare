/*
 *
 *  * Copyright (c) 2021.  Ylenia Battistini, Enrico Gnagnarella, Matteo Scucchia
 *  *
 *  *                              Licensed under the Apache License, Version 2.0 (the "License");
 *  *                              you may not use this file except in compliance with the License.
 *  *                              You may obtain a copy of the License at
 *  *
 *  *                                  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *                              Unless required by applicable law or agreed to in writing, software
 *  *                              distributed under the License is distributed on an "AS IS" BASIS,
 *  *                              WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *                              See the License for the specific language governing permissions and
 *  *                              limitations under the License.
 *
 */

package behaviours

import behaviours.CardiologyVisitData._
import domainmodel.Gender.Gender
import domainmodel._
import org.apache.log4j.{Level, Logger}
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{
  IndexToString,
  StringIndexer,
  VectorAssembler,
  VectorIndexer
}
import org.apache.spark.ml.{Pipeline, PipelineModel}
import org.apache.spark.sql.SparkSession

/**
 * Cardiology Visit Data.
 */
object CardiologyVisitData {

  case class CardiologyVisitData(
    age: Int,
    gender: Int,
    chestPainType: Int,
    restingBloodPressure: Int,
    cholesterol: Int,
    fastingBloodSugar: Boolean,
    restingElectrocardiographic: Int,
    maxHeartRate: Int,
    isAnginaInducted: Boolean,
    oldPeakST: Double,
    slopeST: Int,
    numberVesselColoured: Int,
    thal: Int
  )

  def apply(
    age: Int,
    gender: Gender,
    cardiologyVisit: CardiologyVisit
  ): CardiologyVisitData =
    CardiologyVisitData(
      age,
      1 - gender.id,
      cardiologyVisit.chestPainType.id,
      cardiologyVisit.restingBloodPressure.value,
      cardiologyVisit.cholesterol.value,
      cardiologyVisit.fastingBloodSugar.value,
      cardiologyVisit.restingElectrocardiographic.id,
      cardiologyVisit.maxHeartRate.value,
      isAnginaInducted = cardiologyVisit.isAnginaInducted,
      cardiologyVisit.oldPeakST.value,
      cardiologyVisit.slopeST.id,
      cardiologyVisit.numberVesselColoured.value,
      cardiologyVisit.thal.id
    )
}

object CardiologyDiseasesPredictor {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  val spark: SparkSession = SparkSession
    .builder()
    .master("local[*]")
    .appName("spark")
    .getOrCreate()

  import spark.implicits._

  private var model: PipelineModel = _

  def train(): Unit = {
    var data = spark.read
      .format("csv")
      .option("inferSchema", "true")
      .option("header", value = true)
      .load("core/src/main/resources/heart.csv")

    data = data.withColumnRenamed("target", "label")

    val assembler = new VectorAssembler()
      .setInputCols(
        Array(
          "age",
          "sex",
          "cp",
          "trestbps",
          "chol",
          "fbs",
          "restecg",
          "thalach",
          "exang",
          "oldpeak",
          "slope",
          "ca",
          "thal"
        )
      )
      .setOutputCol("features")

    val features = assembler.transform(data)

    val labelIndexer = new StringIndexer()
      .setInputCol("label")
      .setOutputCol("indexedLabel")
      .fit(features)

    val featureIndexer = new VectorIndexer()
      .setInputCol("features")
      .setOutputCol("indexedFeatures")
      .setMaxCategories(5)
      .fit(features)

    val Array(trainingData, testData) = features.randomSplit(Array(0.7, 0.3))

    val dt = new DecisionTreeClassifier()
      .setLabelCol("indexedLabel")
      .setFeaturesCol("indexedFeatures")
      .setMinInstancesPerNode(3)
      .setImpurity("entropy")

    val labelConverter = new IndexToString()
      .setInputCol("prediction")
      .setOutputCol("predictedLabel")
      .setLabels(labelIndexer.labelsArray(0))

    val pipeline = new Pipeline()
      .setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))

    model = pipeline.fit(trainingData)

    val predictions = model.transform(testData)

    val evaluator = new MulticlassClassificationEvaluator()
      .setLabelCol("indexedLabel")
      .setPredictionCol("prediction")
      .setMetricName("accuracy")
    val accuracy = evaluator.evaluate(predictions)
  }

  def predict(
    age: Int,
    gender: Gender,
    cardiologyVisit: CardiologyVisit
  ): CardiologyDiseasePrediction = {
    val cardiologyData: Seq[CardiologyVisitData] = List(
      CardiologyVisitData(age, gender, cardiologyVisit)
    )

    val data = cardiologyData.toDF(
      "age",
      "sex",
      "cp",
      "trestbps",
      "chol",
      "fbs",
      "restecg",
      "thalach",
      "exang",
      "oldpeak",
      "slope",
      "ca",
      "thal"
    )

    val assembler = new VectorAssembler()
      .setInputCols(
        Array(
          "age",
          "sex",
          "cp",
          "trestbps",
          "chol",
          "fbs",
          "restecg",
          "thalach",
          "exang",
          "oldpeak",
          "slope",
          "ca",
          "thal"
        )
      )
      .setOutputCol("features")

    val features = assembler.transform(data)

    val predictions = model.transform(features)

    if (predictions.select("predictedLabel").head().getString(0).toInt == 0)
      CardiologyDiseasesAbsence()
    else CardiologyDiseasesPresence()

  }
}

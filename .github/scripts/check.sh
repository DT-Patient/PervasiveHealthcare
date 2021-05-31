#!/bin/bash
set -e
if [ -x 'gradlew' ]; then
    echo 'Detected gradle wrapper'
    if ./gradlew tasks | grep '^check\s'; then
        echo 'Detected check task'
        ./gradlew check --parallel
        if ./gradlew tasks | grep '^jacocoTestReport\s'; then
            ./gradlew jacocoTestReport --parallel
        else
          echo 'No known jacoco tasks'
        fi
        if ./gradlew tasks | grep '^spotlessCheck\s'; then
            ./gradlew spotlessCheck --parallel
        else
          echo 'No known spotless tasks'
        fi
    else
        echo 'No known check tasks'
    fi
else
    echo 'No configuration detected, skipping checks'
fi

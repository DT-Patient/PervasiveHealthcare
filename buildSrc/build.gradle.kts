plugins{
    `kotlin-dsl`
}

repositories{
    gradlePluginPortal()

}

dependencies{
    implementation("org.danilopianini:git-sensitive-semantic-versioning:_")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:_")
}
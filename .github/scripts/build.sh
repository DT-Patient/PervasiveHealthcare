#!/bin/bash
set -e
if [ -x 'gradlew' ]; then
    echo 'Detected gradle wrapper'
    if ./gradlew tasks | grep '^assemble\s'; then
        echo 'Detected assemble task'
        ./gradlew assemble --parallel
    elif ./gradlew tasks | grep '^build\s'; then
        echo 'Detected build task'
        ./gradlew build --parallel
    else
        echo 'No known tasks'
        ./gradlew
    fi
else
    echo 'No configuration detected, build failing'
    exit 1
fi

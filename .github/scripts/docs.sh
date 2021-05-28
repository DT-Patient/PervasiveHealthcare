#!/bin/bash
set -e
if [ -x 'gradlew' ]; then
    echo 'Detected gradle wrapper, checking for known tasks'
    if ./gradlew tasks | grep '^scaladoc\s'; then
        echo 'Detected scaladoc task'
        ./gradlew scaladoc --parallel
    else
        echo 'No known docs tasks'
    fi
else
    echo 'No valid configuration detected, skipping documentation creation'
fi

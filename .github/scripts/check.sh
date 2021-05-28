#!/bin/bash
set -e
if [ -x 'gradlew' ]; then
    echo 'Detected gradle wrapper'
    if ./gradlew tasks | grep '^check\s'; then
        echo 'Detected check task'
        ./gradlew check --parallel
        if ./gradlew tasks | grep '^jacocoTestReport\s'; then
            ./gradlew jacocoTestReport --parallel
        fi
    else
        echo 'No known check tasks'
    fi
else
    echo 'No configuration detected, skipping checks'
fi

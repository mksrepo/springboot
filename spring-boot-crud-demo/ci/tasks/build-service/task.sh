#!/bin/bash

set -e # fail fast
#set -x # debug

echo ""
echo " .. Running build"
echo ""

cd service-repo

# gradle build
export GRADLE_OPTS="-Dorg.gradle.native=false"
./gradlew clean test assemble build

# -- target folder is auto created
# mkdir -f ../build-output

# move all manifests file to target
cp -f manifest.yml  ../build-output/
cp -f build/libs/*.jar ../build-output/

echo ""
echo " Build completed!!!"
echo ""

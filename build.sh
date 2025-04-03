#!/bin/bash

# Compile the Scala code to graalvm native image
mkdir -p out
timestamp=$(date +%s)
mv out/flyway-checksum out/flyway-checksum.bak__$timestamp || true
scala-cli --power package --native-image FlywayChecksum.scala -o ./out/flyway-checksum -- --no-fallback

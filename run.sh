#!/bin/bash

# Script to run the Spring Boot application with a specific profile.
# Usage: ./run.sh [profile]
# Example: ./run.sh dev
# Example: ./run.sh prod

# --- Configuration ---
# Set the default profile if none is provided.
DEFAULT_PROFILE="dev"
# Set the name of the final JAR file.
ARTIFACT_ID="Challenge-5"
VERSION="0.0.1-SNAPSHOT"
JAR_FILE="target/${ARTIFACT_ID}-${VERSION}.jar"
# --- End Configuration ---

# Check if a profile is provided as an argument
if [ -n "$1" ]; then
    PROFILE="$1"
else
    echo "No profile specified. Available options could be: dev, prod"
    read -p "Enter profile (default: $DEFAULT_PROFILE): " input_profile
    if [ -z "$input_profile" ]; then
        PROFILE="$DEFAULT_PROFILE"
    else
        PROFILE="$input_profile"
    fi
fi

echo "Building the application..."
# Use Maven Wrapper if available, otherwise use system's mvn
if [ -f "./mvnw" ]; then
    ./mvnw clean package
else
    mvn clean package
fi

if [ $? -ne 0 ]; then
    echo "Maven build failed. Aborting."
    exit 1
fi

echo "Starting application with profile: $PROFILE"
java -jar -Dspring.profiles.active="$PROFILE" "$JAR_FILE"
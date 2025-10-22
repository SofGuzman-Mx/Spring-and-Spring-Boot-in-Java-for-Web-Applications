@echo off

:: Script to run the Spring Boot application with a specific profile.
:: Usage: run.bat [profile]
:: Example: run.bat dev
:: Example: run.bat prod

:: --- Configuration ---
:: Set the default profile if none is provided.
set "DEFAULT_PROFILE=dev"
:: Set the name of the final JAR file.
set "ARTIFACT_ID=Challenge-5"
set "VERSION=0.0.1-SNAPSHOT"
set "JAR_FILE=target\%ARTIFACT_ID%-%VERSION%.jar"
:: --- End Configuration ---

set "PROFILE=%1"

if not defined PROFILE (
    echo No profile specified. Available options could be: dev, prod
    set /p "PROFILE=Enter profile (default: %DEFAULT_PROFILE%): "
    if not defined PROFILE (
        set "PROFILE=%DEFAULT_PROFILE%"
    )
)

echo Building the application...

:: Use Maven Wrapper if available, otherwise use system's mvn
if exist "mvnw.cmd" (
    call mvnw.cmd clean package
) else (
    call mvn clean package
)

if %errorlevel% neq 0 (
    echo Maven build failed. Aborting.
    exit /b %errorlevel%
)

echo Starting application with profile: %PROFILE%
java -jar -Dspring.profiles.active=%PROFILE% "%JAR_FILE%"
@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM delete saved data from previous run to ensure a fresh start for strict comparison
if exist data\SnoopyData.txt del data\SnoopyData.txt
if exist data rmdir /s /q data

REM compile the code into the bin folder by targeting all sub-packages
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\snoopy\command\*.java ..\src\main\java\snoopy\exception\*.java ..\src\main\java\snoopy\storage\*.java ..\src\main\java\snoopy\task\*.java ..\src\main\java\snoopy\ui\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program using the fully qualified package name, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin snoopy.ui.Snoopy < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
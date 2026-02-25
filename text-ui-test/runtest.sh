#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# delete saved data from previous run to ensure a fresh start for strict comparison
if [ -d "./data" ]
then
    rm -rf ./data
fi

# compile the code into the bin folder, terminates if error occurred
# The /*/*.java wildcard ensures all sub-packages within snoopy are compiled
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/snoopy/*/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program using the fully qualified package name, feed commands from input.txt file and redirect the output to ACTUAL.TXT
java -classpath ../bin snoopy.ui.Snoopy < input.txt > ACTUAL.TXT

# convert to UNIX format for reliable diff comparison
cp EXPECTED.TXT EXPECTED-UNIX.TXT
cp ACTUAL.TXT ACTUAL-UNIX.TXT
dos2unix ACTUAL-UNIX.TXT EXPECTED-UNIX.TXT

# compare the actual output to the expected output
diff ACTUAL-UNIX.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
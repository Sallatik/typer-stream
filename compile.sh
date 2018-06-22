#!bin/bash
rm -rf classes/*;
javac -d classes src/*/*.java
javac -d classes -cp classes src/*.java

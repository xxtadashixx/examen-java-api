#!/usr/bin/env bash
javac -cp "lib/gson-2.8.9.jar:." src/*.java
java -cp "lib/gson-2.8.9.jar:src" Main


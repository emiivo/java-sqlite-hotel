#!/usr/bin/env bash
javac *.java
java -classpath ".:sqlite-jdbc-3.32.3.2.jar" HotelManagement

#!/usr/bin/env bash
set -o  -x errexit

git clone ./mybatis-generator-core ./excel-generator-jar

cd excel-generator-jar

mvn deploy
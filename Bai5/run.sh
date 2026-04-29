#!/bin/bash
echo "--- Dang kiem tra chat luong va do bao phu code (JaCoCo) ---"

# Thay 'test' bang 'verify' de kich hoat JaCoCo Check
mvn clean verify

if [ $? -eq 0 ]; then
    echo "--- [SUCCESS] Code dat chuan chat luong va do bao phu > 80% ---"
else
    echo "--- [FAILED] Code chua dat chuan hoac do bao phu thap (< 80%) ---"
    echo "Hay kiem tra bao cao tai: target/site/jacoco/index.html"
fi
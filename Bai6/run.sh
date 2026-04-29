#!/bin/bash
echo "--- Dang su dung Maven de build va test ---"

mvn clean test

if [ $? -eq 0 ]; then
    echo "--- BUILD SUCCESSFUL ---"
else
    echo "--- BUILD FAILED ---"
fi
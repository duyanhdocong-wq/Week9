#!/bin/bash
echo "--- Dang kiem tra: Dinh dang code & Logic Test ---"

# Dung 'verify' de chay ca Checkstyle (dinh dang) va Test (logic)
mvn clean verify

if [ $? -eq 0 ]; then
    echo "-------------------------------------------------------"
    echo "--- [SUCCESS] ---"
    echo "-------------------------------------------------------"
else
    echo "-------------------------------------------------------"
    echo "--- [FAILED] ---"
    echo "-------------------------------------------------------"
fi
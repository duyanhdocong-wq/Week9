#!/bin/bash
echo "--- [BAI 8] Dang dong goi san pham thực thi ---"

mvn clean package

if [ $? -eq 0 ]; then
    echo "--- Build thanh cong! Dang chay thu file JAR... ---"
    echo "---------------------------------------------------"
    # Lệnh này sẽ tự động tìm file .jar trong target để chạy
    java -jar target/*.jar
else
    echo "--- Build that bai! Vui long kiem tra lai code. ---"
fi
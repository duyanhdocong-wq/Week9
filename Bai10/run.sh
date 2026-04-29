#!/bin/bash

echo "======================================================="
echo "   [BAI 10] KIỂM TRA PIPELINE DƯỚI LOCAL    "
echo "======================================================="

# Đây là bước sẽ văng ra lỗi nếu pom.xml hoặc code có vấn đề
echo "--- Đang chạy mvn clean package... ---"
mvn clean package > build_log.txt 2>&1

if [ $? -eq 0 ]; then
    echo "-------------------------------------------------------"
    echo " [SUCCESS] "
    echo "-------------------------------------------------------"
    # Chạy thử file JAR để kiểm tra logic lần cuối
    java -jar target/*.jar
else
    echo "-------------------------------------------------------"
    echo " [FAILED] "
    echo "-------------------------------------------------------"

    # Lọc ra các dòng chứa ERROR để dễ bắt bệnh
    grep -i "ERROR" build_log.txt | head -n 10
fi
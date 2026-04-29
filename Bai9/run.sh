#!/bin/bash

echo "-------------------------------------------------------"
echo "--- TRIEN KHAI LOGGING CHUYEN NGHIEP ---"
echo "-------------------------------------------------------"

# 1. Chạy Maven Test để kích hoạt các dòng Log
mvn clean test

# Kiểm tra xem lệnh mvn test có thành công không
if [ $? -eq 0 ]; then
    echo ""
    echo "--- [SUCCESS] Test hoàn tất! Đang kiểm tra tệp Log... ---"

    # 2. Kiểm tra xem file log có tồn tại không (Đường dẫn khớp với logback.xml)
    LOG_FILE="logs/math-utils.log"

    if [ -f "$LOG_FILE" ]; then
        echo "--- [OK] Đã tìm thấy tệp log tại: $LOG_FILE ---"
        echo "--- Nội dung 5 dòng log cuối cùng: ---"
        echo "-------------------------------------------------------"
        # Hiển thị vài dòng cuối của file log để chứng minh nó có dữ liệu
        tail -n 5 "$LOG_FILE"
        echo "-------------------------------------------------------"
    else
        echo "--- [ERROR] Không tìm thấy tệp log! Kiểm tra lại cấu hình logback.xml ---"
    fi
else
    echo "--- [FAILED] Có lỗi xảy ra trong quá trình chạy Test ---"
fi
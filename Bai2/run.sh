#!/bin/bash

# Đảm bảo lệnh mvn chạy mượt mà trên môi trường Git Bash/Windows
export MSYS2_ARG_CONV_EXCL="*"

echo "--- [BUOC 1] Dang kiem tra chat luong code (Checkstyle - Google Style) ---"

# Chạy goal checkstyle:check để tìm các lỗi vi phạm định dạng
mvn clean checkstyle:check

# Kiểm tra mã thoát (exit code) của lệnh trước đó
if [ $? -eq 0 ]; then
    echo "--- CHECKSTYLE SUCCESSFUL: Code cua ban rat dep! ---"
    echo ""
    echo "--- [BUOC 2] Dang chay kiem thu (Unit Test) ---"
    mvn test
else
    echo "--- CHECKSTYLE FAILED: Ban con loi vi pham format ---"
    exit 1
fi
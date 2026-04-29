import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MathUtils {
    // Khai báo Logger chuyên nghiệp
    private static final Logger logger = LoggerFactory.getLogger(MathUtils.class);

    public int add(int a, int b) {
        // Sử dụng Parameterized Logging (dấu {})
        logger.info("Thuc hien phep cong: {} + {}", a, b);
        return a + b;
    }

    public int divide(int a, int b) {
        try {
            if (b == 0) {
                throw new ArithmeticException("Loi chia cho 0");
            }
            logger.info("Thuc hien phep chia: {} / {}", a, b);
            return a / b;
        } catch (ArithmeticException e) {
            // Sử dụng mức độ ERROR cho ngoại lệ
            logger.error("NGOAI LE: Khong the thuc hien phep chia - {}", e.getMessage());
            throw e;
        }
    }
}
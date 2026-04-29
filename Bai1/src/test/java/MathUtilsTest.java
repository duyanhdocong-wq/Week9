import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MathUtilsTest {

    // Khởi tạo Logger để thay thế sout
    private static final Logger logger = LoggerFactory.getLogger(MathUtilsTest.class);

    // 1. Kiểm thử hàm tìm giá trị lớn nhất (max)
    @Test
    void testMax() {
        // Kiểm thử phân vùng tương đương (EP)
        assertEquals(10, MathUtils.max(10, 5)); // Trường hợp a > b
        assertEquals(7, MathUtils.max(7, 7));   // Trường hợp a = b
        assertEquals(8, MathUtils.max(3, 8));   // Trường hợp a < b

        // Kiểm thử giá trị biên (BVA)
        assertEquals(Integer.MAX_VALUE, MathUtils.max(Integer.MAX_VALUE, 0));
        assertEquals(0, MathUtils.max(Integer.MIN_VALUE, 0));
        assertEquals(Integer.MAX_VALUE, MathUtils.max(Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertEquals(Integer.MIN_VALUE, MathUtils.max(Integer.MIN_VALUE, Integer.MIN_VALUE));
    }

    // 2. Kiểm thử hàm chia (divide)
    @Test
    void testDivide() {
        assertEquals(5, MathUtils.divide(10, 2));   // b > 0
        assertEquals(-5, MathUtils.divide(10, -2)); // b < 0

        // Kiểm tra ném ngoại lệ khi chia cho 0
        assertThrows(IllegalArgumentException.class, () -> {
            MathUtils.divide(10, 0);
        });
    }

    // 4. Các phương thức vòng đời sử dụng Logging có cấu trúc
    @BeforeAll
    static void beforeAll() {
        logger.info("Bắt đầu chạy MathUtilsTest");
    }

    @AfterAll
    static void afterAll() {
        logger.info("Kết thúc");
    }
}
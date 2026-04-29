import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;

public class MathUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(MathUtilsTest.class);
    private final MathUtils mathUtils = new MathUtils();

    @Test
    void testAddLogging() {
        logger.info("--- Bat dau test phep cong ---");
        int result = mathUtils.add(5, 10);
        assertEquals(15, result);
        logger.info("--- Ket thuc test phep cong ---");
    }

    @Test
    void testDivideByZeroLogging() {
        logger.info("--- Bat dau test phep chia cho 0 ---");
        // Phep chia cho 0 se kich hoat logger.error() trong class MathUtils
        assertThrows(ArithmeticException.class, () -> {
            mathUtils.divide(10, 0);
        });
        logger.info("--- Ket thuc test phep chia cho 0 ---");
    }
}
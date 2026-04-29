import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import org.junit.jupiter.api.Test;

public class PathUtilsTest {
    @Test
    public void testPathSeparatorAcrossOS() {
        PathUtils pu = new PathUtils();
        String result = pu.constructPath("data", "results.txt");

        // Lấy dấu phân cách chuẩn của hệ điều hành hiện tại (/ hoặc \)
        String currentSeparator = File.separator;

        // Kiểm tra xem đường dẫn tạo ra có chứa đúng dấu của hệ điều hành đó không
        assertTrue(result.contains(currentSeparator),
                "Đường dẫn phải chứa dấu phân cách: " + currentSeparator);
    }
}
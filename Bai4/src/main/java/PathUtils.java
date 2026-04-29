import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathUtils {
    private static final Logger logger = LoggerFactory.getLogger(PathUtils.class);

    /**
     * CÁCH SAI (Gây lỗi ở Bài 4): folder + "\\" + file (Chỉ chạy trên Windows)
     * CÁCH ĐÚNG: Dùng Paths.get() để tự động chọn dấu / hoặc \
     */
    public String constructPath(String folder, String fileName) {
        Path path = Paths.get(folder, fileName);
        String result = path.toString();
        logger.info("Đường dẫn được tạo ra: {}", result);
        return result;
    }
}
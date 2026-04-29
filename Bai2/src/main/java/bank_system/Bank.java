package bank_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp đại diện cho một ngân hàng, quản lý danh sách khách hàng.
 * Tuân thủ Google Java Style (2-space indent).
 */
public class Bank {
    private static final Logger logger = LoggerFactory.getLogger(Bank.class);
    private List<Customer> customerList; // Đổi tên từ c_list để rõ nghĩa

    public Bank() {
        this.customerList = new ArrayList<>();
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        if (customerList == null) {
            this.customerList = new ArrayList<>();
        } else {
            this.customerList = customerList;
        }
    }

    /**
     * Đọc danh sách khách hàng từ InputStream.
     * Sử dụng Try-with-resources để tự động đóng stream.
     */
    public void readCustomerList(InputStream inputStream) {
        if (inputStream == null) {
            logger.warn("InputStream đầu vào bị null.");
            return;
        }

        logger.info("Bắt đầu đọc dữ liệu khách hàng..."); // Thay System.out bằng logger

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            Customer currentCustomer = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                // Sử dụng logic tách biệt để tránh lồng IF quá sâu
                currentCustomer = processDataLine(line, currentCustomer);
            }
            logger.info("Đã hoàn thành việc nạp danh sách khách hàng.");
        } catch (IOException e) {
            logger.error("Lỗi I/O khi đọc dữ liệu: {}", e.getMessage());
        }
    }

    /**
     * Xử lý từng dòng dữ liệu: Phân loại là Khách hàng hay Tài khoản.
     */
    private Customer processDataLine(String line, Customer current) {
        int lastSpace = line.lastIndexOf(' ');
        if (lastSpace <= 0) {
            return current;
        }

        String token = line.substring(lastSpace + 1).trim();

        // Kiểm tra nếu là dòng thông tin Khách hàng (9 chữ số CMND)
        if (token.matches("\\d{9}")) {
            String name = line.substring(0, lastSpace).trim();
            Customer newCustomer = new Customer(Long.parseLong(token), name);
            customerList.add(newCustomer);
            logger.debug("Đã thêm khách hàng mới: {}", name);
            return newCustomer;
        }

        // Nếu không phải khách hàng, xử lý nạp tài khoản cho khách hàng hiện tại
        if (current != null) {
            parseAndAddAccount(line, current);
        }
        return current;
    }

    private void parseAndAddAccount(String line, Customer current) {
        String[] parts = line.split("\\s+");
        if (parts.length < 3) {
            return;
        }

        try {
            long accNum = Long.parseLong(parts[0]);
            String type = parts[1];
            double balance = Double.parseDouble(parts[2]);

            if (Account.CHECKING_TYPE.equals(type)) {
                current.addAccount(new CheckingAccount(accNum, balance));
            } else if (Account.SAVINGS_TYPE.equals(type)) {
                current.addAccount(new SavingsAccount(accNum, balance));
            }
        } catch (NumberFormatException e) {
            logger.warn("Dữ liệu tài khoản không hợp lệ: {}", line);
        }
    }

    /**
     * Lấy thông tin khách hàng sắp xếp theo ID.
     */
    public String getCustomersInfoByIdOrder() {
        List<Customer> sortedList = new ArrayList<>(this.customerList);
        // Sử dụng Lambda thay cho Anonymous class
        Collections.sort(sortedList, (c1, c2) -> Long.compare(c1.getIdNumber(), c2.getIdNumber()));

        return buildCustomerInfoString(sortedList);
    }

    /**
     * Lấy thông tin khách hàng sắp xếp theo Tên.
     */
    public String getCustomersInfoByNameOrder() {
        List<Customer> sortedList = new ArrayList<>(this.customerList);
        Collections.sort(sortedList, (c1, c2) -> {
            int nameComp = c1.getFullName().compareTo(c2.getFullName());
            return nameComp != 0 ? nameComp : Long.compare(c1.getIdNumber(), c2.getIdNumber());
        });

        return buildCustomerInfoString(sortedList);
    }

    /**
     * Dùng StringBuilder để tối ưu hiệu năng cộng chuỗi
     */
    private String buildCustomerInfoString(List<Customer> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getCustomerInfo());
            if (i < list.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
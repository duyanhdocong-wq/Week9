public class MathUtils {

    public int add(int a, int b) {
        return a + b;
    }

    public static int max(int a, int b) {
        return (a >= b) ? a : b;
    }

    public static int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }

    public static void main(String[] args) {
        MathUtils mu = new MathUtils();
        System.out.println("--- MathUtils Executable Running ---");
        System.out.println("Max of 10 and 20: " + mu.max(10, 20));
    }
}
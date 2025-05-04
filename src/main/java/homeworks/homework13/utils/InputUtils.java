public class InputUtils {

    public static int parseCount(String value) throws IllegalArgumentException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Невалидное значение");
        }
    }

    public static Object validateCount(String value) {
        try {
            return parseCount(value);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public static double parseNumber(String value) throws IllegalArgumentException {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Невалидное значение");
        }
    }
}
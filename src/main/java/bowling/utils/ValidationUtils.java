package bowling.utils;

public class ValidationUtils {

    private ValidationUtils() {}

    public static void checkNotNullNorBlank(String value, String errorMessage) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}

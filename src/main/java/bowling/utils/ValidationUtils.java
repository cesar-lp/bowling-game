package bowling.utils;

import bowling.exception.InvalidPinsAmountException;

public class ValidationUtils {

    private ValidationUtils() {}

    public static void checkNotNullNorBlank(String value, String errorMessage) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkPinsKnockedOver(int amount) {
        if (amount < 0 || amount > 10) {
            throw new InvalidPinsAmountException(amount);
        }
    }
}

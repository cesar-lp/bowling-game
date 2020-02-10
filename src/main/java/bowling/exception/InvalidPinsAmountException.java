package bowling.exception;

public class InvalidPinsAmountException extends RuntimeException {

    public InvalidPinsAmountException(int knockedOver) {
        super("Pins knocked over must be between 0 and 10, found " + knockedOver);
    }
}

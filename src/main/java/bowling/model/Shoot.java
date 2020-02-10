package bowling.model;

import bowling.exception.InvalidPinsAmountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static bowling.utils.ValidationUtils.checkPinsKnockedOver;

public class Shoot {

    private static final Logger logger = LoggerFactory.getLogger(Shoot.class.getName());

    private int knockedOver;
    private boolean faultCommitted;

    public Shoot(int knockedOver) {
        try {
            checkPinsKnockedOver(knockedOver);
            this.knockedOver = knockedOver;
        } catch (InvalidPinsAmountException invalidPinsAmountExc) {
            logger.error(invalidPinsAmountExc.getMessage());
            System.exit(1);
        }
    }

    public Shoot(int knockedOver, boolean faultCommitted) {
        try {
            checkPinsKnockedOver(knockedOver);
            this.knockedOver = knockedOver;
            this.faultCommitted = faultCommitted;
        } catch (InvalidPinsAmountException invalidPinsAmountExc) {
            logger.error(invalidPinsAmountExc.getMessage());
            System.exit(1);
        }
    }

    public static Shoot createShootFromDescription(String shootDesc) {
        if (shootDesc.equals("F")) {
            return new Shoot(0, true);
        }
        try {
            return new Shoot(Integer.parseInt(shootDesc));
        } catch (NumberFormatException numberFormatExc) {
            logger.error("Can't register turn shoot, expected a number but found {}", shootDesc);
            System.exit(1);
        }

        return null;
    }

    public static Shoot missedShoot() {
        return new Shoot(0);
    }

    public int getKnockedOver() {
        return knockedOver;
    }

    @Override
    public String toString() {
        if (faultCommitted) {
            return "F";
        }

        return String.valueOf(knockedOver);
    }
}

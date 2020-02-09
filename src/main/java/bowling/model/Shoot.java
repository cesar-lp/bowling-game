package bowling.model;

public class Shoot {

    private int knockedOver;
    private boolean faultCommitted;

    public Shoot(int knockedOver) {
        this.knockedOver = knockedOver;
    }

    public Shoot(int knockedOver, boolean faultCommitted) {
        this.knockedOver = knockedOver;
        this.faultCommitted = faultCommitted;
    }

    public static Shoot createFromDesc(String shootDesc) {
        if (shootDesc.equals("F")) {
            return new Shoot(0, true);
        }
        return new Shoot(Integer.parseInt(shootDesc));
    }

    public int getKnockedOver() {
        return knockedOver;
    }

    public boolean faultWasCommitted() {
        return faultCommitted;
    }

    @Override
    public String toString() {
        if (faultCommitted) {
            return "F";
        }

        return String.valueOf(knockedOver);
    }
}

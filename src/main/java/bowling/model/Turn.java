package bowling.model;

import bowling.constants.PointType;

public class Turn {

    Shoot firstShoot;
    Shoot secondShoot;
    PointType pointType = PointType.REGULAR;

    public static Turn strike() {
        return new Turn(new Shoot(10), new Shoot(0));
    }

    public Turn(Shoot firstShoot) {
        this.firstShoot = firstShoot;

        if (this.firstShoot.getKnockedOver() == 10) {
            this.pointType = PointType.STRIKE;
            this.secondShoot = new Shoot(0);
        }
    }

    public Turn(Shoot firstShoot, Shoot secondShoot) {
        this.firstShoot = firstShoot;
        this.secondShoot = secondShoot;

        if (this.firstShoot.getKnockedOver() == 10) {
            pointType = PointType.STRIKE;
        } else if (getTurnTotal() == 10) {
            pointType = PointType.SPARE;
        }
    }

    public int getAmountKnockedOverOnFirstShoot() {
        return firstShoot.getKnockedOver();
    }

    public String getFirstShootDesc() {
        if (isStrike()) {
            return pointType.toString();
        }
        return firstShoot.toString();
    }

    public String getSecondChanceDesc() {
        if (isSpare()) {
            return pointType.toString();
        }
        return secondShoot.toString();
    }

    public PointType getPointType() {
        return pointType;
    }

    public boolean isInProgress() {
        return this.firstShoot == null || this.secondShoot == null;
    }

    public void registerSecondShoot(Shoot secondShoot) {
        this.secondShoot = secondShoot;

        if (getTurnTotal() == 10) {
            this.pointType = PointType.SPARE;
        }
    }

    public Integer getScore() {
        switch (pointType) {
            case STRIKE:
            case SPARE:
                return 10;
            default:
                return getTurnTotal();
        }
    }

    private int getTurnTotal() {
        return firstShoot.getKnockedOver() + secondShoot.getKnockedOver();
    }

    @Override
    public String toString() {
        return "[" + firstShoot.getKnockedOver() + "," + secondShoot.getKnockedOver() + "]";
    }

    public boolean isStrike() {
        return pointType.equals(PointType.STRIKE);
    }

    public boolean isSpare() {
        return pointType.equals(PointType.SPARE);
    }

    public boolean isRegular() {
        return pointType.equals(PointType.REGULAR);
    }
}

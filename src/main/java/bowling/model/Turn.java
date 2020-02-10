package bowling.model;

import bowling.constants.ScoreType;

public class Turn {

    Shoot firstShoot;
    Shoot secondShoot;
    ScoreType scoreType = ScoreType.REGULAR;

    public static Turn strike() {
        return new Turn(new Shoot(10), new Shoot(0));
    }

    public static Turn incompleteExtraTurn(Shoot firstShoot) {
        Turn incompleteExtraTurn = new Turn(firstShoot);
        incompleteExtraTurn.secondShoot = null;
        return incompleteExtraTurn;
    }

    public Turn(Shoot firstShoot) {
        this.firstShoot = firstShoot;

        if (this.firstShoot.getKnockedOver() == 10) {
            this.scoreType = ScoreType.STRIKE;
            this.secondShoot = new Shoot(0);
        }
    }

    public Turn(Shoot firstShoot, Shoot secondShoot) {
        this.firstShoot = firstShoot;
        this.secondShoot = secondShoot;

        if (this.firstShoot.getKnockedOver() == 10) {
            scoreType = ScoreType.STRIKE;
        } else if (getTurnScore() == 10) {
            scoreType = ScoreType.SPARE;
        }
    }

    public int getAmountKnockedOverOnFirstShoot() {
        return firstShoot.getKnockedOver();
    }

    public String getFirstShootDesc() {
        if (isStrike()) {
            return scoreType.toString();
        }
        return firstShoot.toString();
    }

    public String getSecondChanceDesc() {
        if (isSpare()) {
            return scoreType.toString();
        }
        return secondShoot.toString();
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public boolean isInProgress() {
        return this.firstShoot == null || this.secondShoot == null;
    }

    public void registerSecondShoot(Shoot secondShoot) {
        this.secondShoot = secondShoot;

        if (getTurnScore() == 10) {
            if (firstShoot != null && firstShoot.getKnockedOver() == 10) {
                this.scoreType = ScoreType.STRIKE;
            } else {
                this.scoreType = ScoreType.SPARE;
            }
        }
    }

    public Integer getScore() {
        switch (scoreType) {
            case STRIKE:
            case SPARE:
                return 10;
            default:
                return getTurnScore();
        }
    }

    private int getTurnScore() {
        return firstShoot.getKnockedOver() + secondShoot.getKnockedOver();
    }

    @Override
    public String toString() {
        String fShoot = firstShoot != null ? String.valueOf(firstShoot.getKnockedOver()) : " ";
        String sShoot = secondShoot != null ? String.valueOf(secondShoot.getKnockedOver()) : " ";
        return "[" + fShoot + "," + sShoot + "]";
    }

    public boolean isStrike() {
        return scoreType.equals(ScoreType.STRIKE);
    }

    public boolean isSpare() {
        return scoreType.equals(ScoreType.SPARE);
    }

    public boolean isRegular() {
        return scoreType.equals(ScoreType.REGULAR);
    }

    public void resetSecondShoot() {
        secondShoot = new Shoot(0);
    }

    public Shoot getFirstShoot() {
        return firstShoot;
    }
}

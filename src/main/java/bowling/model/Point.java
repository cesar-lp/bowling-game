package bowling.model;

import bowling.constants.PointType;

public class Point {
    Integer first;
    Integer second;
    PointType pointType;

    public Integer getFirstShootScore() {
        return first;
    }

    public boolean isStrike() {
        return first == 10;
    }

    private Point() {
    }

    public Point(String first) {
        if (first.equals("F")) {
            this.first = 0;
        } else {
            this.first = Integer.valueOf(first);
        }
    }

    public Point(String first, String second) {
        this.first = Integer.valueOf(first);
        this.second = Integer.valueOf(second);
    }


    public void trackSecondPoint(String second) {
        this.second = Integer.valueOf(second);
    }

    public static Point strike() {
        return new Point("10", "0");
    }


    public static Point empty() {
        return new Point();
    }

    public void printToConsole() {
        if (this.first == 10) {
            System.out.printf("\t%s\t", "X");
        } else {
            if (first + second == 10) {
                System.out.printf("%s\t%s\t", first, "/");
            } else {
                System.out.printf("%s\t%s\t", first != -1 ? first : "F", second != -1 ? second : "F");
            }
        }
    }

    public boolean isRegularPoint() {
        return first != -1 && second != -1 && first + second < 10;
    }

    public boolean isSpare() {
        return first != -1 && second != -1 && first != 10 && first + second == 10;
    }

    public Integer getScore() {
        return first + second;
    }

    @Override
    public String toString() {
        return "[" + this.first + "," + this.second + "]";
    }
}

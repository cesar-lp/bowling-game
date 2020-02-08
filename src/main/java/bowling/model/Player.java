package bowling.model;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class Player {
    private String name;
    private List<Point> points = new LinkedList<>();

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, Point point) {
        this.name = name;
        addPoint(point);
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public String getName() {
        return name;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void printScores() {
        ListIterator<Point> iterator = points.listIterator();
        Integer total = 0;
        Integer frame = 1;

        while (iterator.hasNext()) {
            total += getScore(iterator, iterator.next(), 0, frame == 10);
            System.out.printf("%d\t\t", total);
            frame++;
        }
    }

    public Integer getScore(ListIterator<Point> iterator, Point currentPoint, Integer strikesCounter, boolean lastFrame) {
        Integer total = 0;

        if (currentPoint.isRegularPoint()) {
            total += strikesCounter == 2 ? currentPoint.getFirstShootScore() : currentPoint.getScore();
        }

        if (currentPoint.isSpare()) {
            // handle iterator next case available
            if (strikesCounter == 2) {
                total += currentPoint.getFirstShootScore();
            } else {
                total += currentPoint.getScore();

                if (iterator.hasNext()) {
                    total += iterator.next().getFirstShootScore();
                    iterator.previous();
                }
            }
        }

        if (currentPoint.isStrike()) {
            // handle iterator next case available
            total += 10;

            if (strikesCounter < 2) {
                strikesCounter++;

                if (iterator.hasNext()) {
                    Point nextPoint = iterator.next();
                    total += getScore(iterator, nextPoint, strikesCounter, lastFrame);

                    if (!lastFrame) {
                        iterator.previous();
                    }
                }
            }
        }

        if (lastFrame) {
            while (iterator.hasNext()) {
                total += getScore(iterator, iterator.next(), 0, true);
            }
        }

        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

package a03a.sol_mia;

import java.util.*;

import javax.swing.JButton;

public class LogicImpl implements Logic {
    private final Map<Position, String> cells = new HashMap<>();
    boolean isOver = false;

    public LogicImpl() {
        init();
    }

    @Override
    public boolean hit(Position position) {

        if (cells.containsKey(position)) {
            return false;
        }

        if (!enoughClicks()) {
            // keep selecting
            cells.put(position, "*");
        } else {
            // move a random one
            moveStar();
        }

        if (isOver) {
            init();
        }
        
        
        return true;
    }

    public boolean collision(Position pos) {
        if (cells.containsKey(pos)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBotton(Position pos) {
        if (pos.y()==10) {
            return true;
        } else {
            return false;
        }
    }


    public void moveStar() {
        Position star = chooseRandomStar();
        // delete from cells, 
        Position oldPos = star;
        Position newPos = new Position(star.x(), star.y() + 1);

        if (!collision(newPos) && !isBotton(newPos)) {
            cells.remove(oldPos);
            cells.put(newPos, "*");
            isOver = false;
        }
    }

    public void init() {
        addObstacles(3);
        isOver = false;
    }

    // Aggiungi ostacoli a map
    public void addObstacles(int n) {
        for (int i=0; i<n; i++) {
            cells.put((new Position((new Random()).nextInt(10), (new Random()).nextInt(10))), "o");
        }

        System.out.println(cells);
    }

    @Override
    public boolean hasValue(Position position) {
        return cells.containsKey(position);
    }

    @Override
    public String getValue(Position position) {
        if (hasValue(position)) {
            return cells.get(position);
        } else {
            return "";
        }
    }

    public boolean enoughClicks() {
        if (cells.size()-3 == 5) {
            return true;
        } else {
            return false;
        }
    }

    public Position chooseRandomStar() {
        LinkedList<Position> stars = new LinkedList<>();
        cells.forEach((pos, str) -> {
            if (str=="*") {
                stars.add(pos);
            }
        });

        Random random = new Random();
        Position randomElement = stars.get(random.nextInt(stars.size()));
        return randomElement;
    }

    @Override
    public boolean isOver() {
        return this.isOver;
    }
}

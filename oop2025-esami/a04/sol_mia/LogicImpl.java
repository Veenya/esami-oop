package a04.sol_mia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LogicImpl implements Logic {

    private final Map<Position, Integer> pathValues = new HashMap<>();
    private Position current;
    private boolean isOver = false;

    public LogicImpl() {
        initValue(new Position(0, 0));
    }

    @Override
    public void initValue(Position startPos) {
        this.current = startPos;
        this.pathValues.clear();
        this.pathValues.put(startPos, 0);
    }

    @Override
    public boolean hit(Position position) {
        if (position.equals(current)) {
            return false; // click sulla cella corrente, ignoralo
        }

        List<Position> path = computePath(current, position);

        pathValues.clear();
        // path è ordinato da C1 (escluso) a C2 (incluso).
        // Assegno i valori partendo da C2 (0) e crescendo a ritroso, cap a 4 -> poi -1
        int n = path.size();
        for (int i = 0; i < n; i++) {
            Position cell = path.get(i);
            int distFromEnd = n - 1 - i; // 0 per l'ultima cella (C2)
            int value = distFromEnd <= 4 ? distFromEnd : -1;
            pathValues.put(cell, value);
        }

        this.current = position;
        return true;
    }

    // Percorso orizzontale da 'from' fino alla colonna di 'to', poi verticale fino a 'to'.
    // Esclude 'from', include 'to'.
    private List<Position> computePath(Position from, Position to) {
        List<Position> path = new LinkedList<>();

        int stepX = Integer.signum(to.x() - from.x());
        int x = from.x();
        while (x != to.x()) {
            x += stepX;
            path.add(new Position(x, from.y()));
        }

        int stepY = Integer.signum(to.y() - from.y());
        int y = from.y();
        while (y != to.y()) {
            y += stepY;
            path.add(new Position(to.x(), y));
        }

        if (path.isEmpty()) {
            // from e to coincidono su entrambe le coordinate (non dovrebbe succedere
            // se hit() controlla già position.equals(current))
            path.add(to);
        }

        return path;
    }

    @Override
    public boolean isOver() {
        return this.isOver;
    }

    @Override
    public boolean hasValue(Position position) {
        return this.pathValues.containsKey(position);
    }

    @Override
    public void clear() {
        this.pathValues.clear();
    }

    @Override
    public int getValue(Position position) {
        return this.pathValues.get(position);
    }
}
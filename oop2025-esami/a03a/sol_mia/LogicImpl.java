package a03a.sol_mia;

import java.util.LinkedList;

public class LogicImpl implements Logic {

    private final LinkedList<Position> selected = new LinkedList<>();


    private Position max(){
        return new Position(
                this.selected.stream().map(Position::x).max(Integer::compareTo).get(),
                this.selected.stream().map(Position::y).max(Integer::compareTo).get());
    }

    private Position min(){
        return new Position(
                this.selected.stream().map(Position::x).min(Integer::compareTo).get(),
                this.selected.stream().map(Position::y).min(Integer::compareTo).get());
    }

    private boolean isAdjacent(Position a, Position b) {
        int dx = Math.abs(a.x() - b.x());
        int dy = Math.abs(a.y() - b.y());

        return dx <= 1 && dy <= 1 && !(dx==0 && dy == 0);
    }
    
    @Override
    public boolean hit(Position position) {

        if (!this.selected.isEmpty() && !isAdjacent(this.selected.getLast(), position)) {
            this.selected.clear();
        }
        this.selected.add(position);
        return true;
    }

    @Override
    public boolean isOver() {
        return this.selected.size() == 8;
    }

    @Override
    public boolean isBorder(Position position) {
        if (!isOver()) {
            return false;
        }
        Position min = min();
        Position max = max();
        boolean onBorderRow = position.y() == min.y() || position.y() == max.y();
        boolean onBorderCol = position.x() == min.x() || position.x() == max.x();
        return onBorderRow || onBorderCol;
    }
}

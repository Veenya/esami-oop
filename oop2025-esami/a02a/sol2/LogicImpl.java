package a02a.sol2;

import java.util.LinkedList;
import java.util.function.BiPredicate;

public class LogicImpl implements Logic {
    private static final int TRAILING_LIST_SIZE = 4;
    private final LinkedList<Position> selected = new LinkedList<>();
    private final BiPredicate<Position, Position> valid = (p1, p2) -> p1.x() == p2.x() || p1.y() == p2.y();

    @Override
    public boolean hit(Position position) {
        if (this.selected.isEmpty() || valid.test(this.selected.getFirst(), position)){
            this.selected.addFirst(position);
            return true;
        }
        this.selected.clear();
        return false;
    }

    @Override
    public boolean isOver() {
        return this.selected.size() >= TRAILING_LIST_SIZE && (
            this.selected.subList(0, TRAILING_LIST_SIZE).stream().map(Position::x).distinct().count() ==1 ||
            this.selected.subList(0, TRAILING_LIST_SIZE).stream().map(Position::y).distinct().count() == 1);
    }

    @Override
    public int count() {
        return this.selected.size();
    }

}

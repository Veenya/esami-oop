package a03a.sol2;

import java.util.LinkedList;
import java.util.Optional;
import java.util.function.BiPredicate;

public class LogicImpl implements Logic {
    private static final int SELECTION_SIZE = 8;
    private final LinkedList<Position> selected = new LinkedList<>();
    private final BiPredicate<Position, Position> valid = (p1, p2) -> Math.abs(p1.x() - p2.x()) <= 1 && Math.abs(p1.y() - p2.y()) <= 1;
    private Optional<Position> max = Optional.empty();
    private Optional<Position> min = Optional.empty();; 

    @Override
    public boolean hit(Position position) {
        if (this.selected.isEmpty() || valid.test(this.selected.getFirst(), position)){
            this.selected.addFirst(position);
            if (this.selected.size() == SELECTION_SIZE){
                max = Optional.of(new Position(
                    this.selected.stream().map(Position::x).max(Integer::compareTo).get(),
                    this.selected.stream().map(Position::y).max(Integer::compareTo).get()
                ));
                min = Optional.of(new Position(
                    this.selected.stream().map(Position::x).min(Integer::compareTo).get(),
                    this.selected.stream().map(Position::y).min(Integer::compareTo).get()
                ));
            }
            return true;
        }
        this.selected.clear();
        return false;
    }

    @Override
    public boolean isOver() {
        return this.max.isPresent();
    }

    @Override
    public boolean isHidden(Position position) {
        return isOver() && 
            //(((position.x() == min.get().x() || position.x() == max.get().x()) && position.y() >= min.get().y() && position.y() <= max.get().y()) ||
            //(position.x() >= min.get().x() && position.x() <= max.get().x() && (position.y() == min.get().y() || position.y() == max.get().y())));
            ((position.x() == min.get().x() || position.x() == max.get().x()) || position.y() == min.get().y() || position.y() == max.get().y());
    }

}

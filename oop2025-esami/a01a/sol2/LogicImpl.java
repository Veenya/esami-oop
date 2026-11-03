package e01a.sol2;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class LogicImpl implements Logic {

    private final Deque<Position> selected = new LinkedList<>();

    @Override
    public boolean hit(Position position) {
        if (!this.selected.contains(position)){
            this.selected.add(position);
            return true;
        }
        return false;
    }

    private List<Position> line(Function<Position, Integer> coordinate){
        return this.selected.isEmpty() ? Collections.emptyList() : 
                this.selected
                        .stream()
                        .filter(p -> coordinate.apply(p) == coordinate.apply(this.selected.getLast()))
                        .filter(p -> !p.equals(this.selected.getLast()))
                        .sorted((p, q) -> coordinate.apply(p) - coordinate.apply(q))
                        .toList();
    }

    private List<Position> column(){
        return line(Position::x);
    }

    private List<Position> row(){
        return line(Position::y);
    }

    @Override
    public boolean isOver() {
        return this.column().size() == 4 && this.row().size() == 4;
    }

    @Override
    public Optional<Integer> indexOf(Position pos) {
        return Optional.of(this.column().contains(pos) ? this.column() : this.row())
                .filter(l -> l.contains(pos))
                .map(l -> l.indexOf(pos));
    }

}

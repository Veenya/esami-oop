package a04.sol2;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class LogicImpl implements Logic {

    private final LinkedList<Position> selected = new LinkedList<>();
    private boolean over = false;

    {
        this.selected.add(new Position(0,0));
    }

    private Stream<Integer> range(int start, int end) {
        return Stream.iterate(start, i -> i != end, i -> i + (end > i ? 1 : -1));
    }

    private Stream<Integer> rangeIncluded(int start, int end) {
        return Stream.concat(range(start, end), Stream.of(end));
    }

    @Override
    public boolean hit(Position position) {
        var start = this.selected.getLast();
        if (start.equals(position)){
            this.over = true;
            return false;
        }
        this.selected.clear();
        range(start.x(), position.x())
                .forEach(i -> this.selected.add(new Position(i, start.y())));
        rangeIncluded(start.y(), position.y())
                .forEach(i -> this.selected.add(new Position(position.x(), i)));        
        return true;
    }

    @Override
    public Optional<Integer> indexOf(Position pos) {
        System.out.println(selected);
        return Optional.of(this.selected.indexOf(pos))
                .filter(i -> i >= 0)
                .map(i -> this.selected.size() - 1 - i)
                .map(i -> i >= 5 ? -1 : i);
    }

}

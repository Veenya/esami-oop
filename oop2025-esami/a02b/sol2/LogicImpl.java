package a02b.sol2;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class LogicImpl implements Logic {

    private final LinkedList<Position> selected = new LinkedList<>();

    private static final List<Position> LINE = List.of(
            new Position(0, -2),
            new Position(0, -1),            
            new Position(0, 0),
            new Position(0, 1),
            new Position(0, 2)
        );

    @Override
    public boolean hit(Position position) {
        if (!this.selected.contains(position)){
            this.selected.add(position);
            return true;
        }
        return false;
    }

    private Optional<List<Position>> line(Position top){
        List<Position> inStar = LINE.stream()
                .map(p -> new Position(top.x() + p.x(), top.y() + p.y()))
                .filter(this.selected::contains)
                .toList();
        return Optional.of(inStar).filter(l -> l.size() == LINE.size());
    }


    private Optional<List<Position>> configuration(){
        return LINE.stream()
                .map(p -> new Position(this.selected.getLast().x() + p.x(), this.selected.getLast().y() + p.y()))
                .map(this::line)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny();
    }

    @Override
    public boolean isOver() {
        return !this.selected.isEmpty() && this.configuration().isPresent();
    }

    @Override
    public Optional<Integer> indexOf(Position pos) {
        return this.configuration()
                .map(l -> this.selected
                        .stream()
                        .filter(p -> l.contains(p))
                        .toList()
                        .indexOf(pos))
                .filter(i -> i != -1);
    }

}

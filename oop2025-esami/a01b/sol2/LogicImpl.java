package e01b.sol2;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class LogicImpl implements Logic {

    private final LinkedList<Position> selected = new LinkedList<>();

    private static final List<Position> STAR = List.of(
            new Position(0, 0),
            new Position(0, -1),            
            new Position(1, 0),
            new Position(0, 1),
            new Position(-1, 0)
        );

    @Override
    public boolean hit(Position position) {
        if (!this.selected.contains(position)){
            this.selected.add(position);
            return true;
        }
        return false;
    }

    private Optional<List<Position>> star(Position center){
        List<Position> inStar = STAR.stream()
                .map(p -> new Position(center.x() + p.x(), center.y() + p.y()))
                .filter(this.selected::contains)
                .toList();
        return Optional.of(inStar).filter(l -> l.size() == 5);
    }


    private Optional<List<Position>> configuration(){
        return STAR.stream()
                .map(p -> new Position(this.selected.getLast().x() + p.x(), this.selected.getLast().y() + p.y()))
                .map(this::star)
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

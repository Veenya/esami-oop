package a02b.sol_mia;

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
        if (!this.selected.contains(position)) {
            this.selected.add(position);
            return true;
        }
        return false;
    }

    private Optional<List<Position>> line(Position top) {

    }

    private Optional<List<Position>> configuration() {

    }

    @Override
    public boolean isOver() {
        // True se ci sono 5 celle una sopra l'altra
        return !this.selected.isEmpty() && this.configuration().isPresent();
    }

    @Override
    public Optional<Integer> indexOf(Position pos) {
        return;
    }
}

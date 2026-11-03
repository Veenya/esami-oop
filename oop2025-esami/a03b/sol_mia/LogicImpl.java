package a03b.sol_mia;

import java.awt.desktop.OpenFilesEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class LogicImpl implements Logic{
    private final LinkedList<Position> selected = new LinkedList<>();
    boolean isSquare;
    boolean isOver = false;

    // pattern
    private static final List<Position> SQUARE = List.of(
        new Position(-1, -1),
        new Position(0, -1),
        new Position(+1, -1),
        new Position(-1, 0),
        new Position(0, 0),
        new Position(+1, 0),
        new Position(-1, +1),
        new Position(0, +1),
        new Position(+1, +1)
    );

    private boolean inSquare(Position position) {        
        this.isSquare = false;

        selected.forEach((pos) -> {

            System.out.println(pos.x() - position.x());
            System.out.println(pos.y() - position.y());

            if (pos.x() - position.x() < 5 && pos.y() - position.y() < 5) {
                this.isSquare = true;
            }
        });

        return this.isSquare;
    }
    


    @Override
    public boolean hit(Position position) {

        

        if (this.selected.contains(position)) {
            return false;
        }

        if (this.selected.size()>=8) {
            System.out.println(selected);
            return false;
        }

        this.selected.add(position);
        System.out.println("added");

        if (inSquare(position)) {
            System.out.println("quadrato");
            this.selected.add(position);
        } else {
            System.out.println("no diocan reset");
            reset();
        }

        return true;
    }

    @Override
    public boolean isOver() {
        return false;
    }

    public void reset() {
        this.selected.clear();
        this.isSquare = true; //?
        this.isOver = false;
    }

}

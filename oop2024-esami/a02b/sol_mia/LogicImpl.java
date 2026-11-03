package a02b.sol_mia;

import java.util.*;

public class LogicImpl implements Logic {

    private final Map<Position, Integer> values = new HashMap<>();

    private LinkedList<Position> selected = new LinkedList<>();

    @Override
    public boolean hit(Position position) {

        if (selected.contains(position)) {
            return false;
        }

        selected.add(position);

        calculateValues();
        return true;
    }

    // diagonale: colonna
    public void calculateValues() {
        initValues();

        selected.forEach((pos) -> {

                // aggiungici 1
                int oldVal = values.get((new Position(pos.x(), pos.x())));
                values.put(new Position(pos.x(), pos.x()), oldVal+1);
            


                oldVal = values.get((new Position(9, pos.y())));
                values.put(new Position(9, pos.y()), oldVal+1);

        });
    }


    public void initValues() {

        for (int i=0; i<9; i++) {
            values.put(new Position(9, i), 0); // RIga
            values.put(new Position(i, i), 0); // Diagonale
        }

        System.out.println(values);
    }

    @Override
    public boolean isMap(Position position) {
        return position.x() - position.y() < 1
                || position.x() == 9;
    }

    @Override
    public boolean isOver() {
        if (selected.size()>=5) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getValue(Position position) {
        return values.get(position);
    }

    @Override
    public boolean isPresent(Position position) {
        if (values.containsKey(position)) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void reset() {
        selected.clear();
        values.clear();
    }
}

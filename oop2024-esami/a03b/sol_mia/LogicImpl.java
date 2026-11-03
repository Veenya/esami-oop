package a03b.sol_mia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

public class LogicImpl implements Logic{
 
    private final Map<Position, String> selected = new HashMap<>();
    public boolean isOver = false;
    private int currentIndex = 0;

    @Override
    public boolean hit(Position position) {

        //if (selected.containsKey(position)) {
            //return false;
        //}

        if (selected.size()<5) {
            // mettine
            selected.put(position, "^");
        }

        //toggle
        toggle(position);
        move(position);

        return true;
    }

    // quando nessuna cella può più spostarsi (fig2) si cancellino le selezioni e si ricominci dal punto 1

    @Override
    public boolean isOver() {
        this.isOver = true;
        selected.forEach((pos, str) -> {
            //this.isOver = false;
            // se almeno uno in selected non ha come y == max, allora false
            if ((str.equals("v") && pos.y() < 9) || (str.equals("^") && pos.y() > 0)) {
                this.isOver = false;
            }
        });
    
        return this.isOver;
    }

    @Override
    public void reset() {
        this.selected.clear();
        this.isOver = false;
        this.currentIndex = 0;
    }

    public void toggle(Position position) {
        if (hasValue(position)) {
            if (getValue(position) == "^") {
                selected.put(position, "v");
            } else {
                selected.put(position, "^");
            }
        }
    }


    public void move(Position position) {

        if (!hasValue(position)) {
            // chose random selected

            if (isBorder(position)) {
                return;
            }

            Position pos = getNext();

            // move it accordingly
            if (selected.get(pos) == "^") {
                ///
                Position newPos = new Position(pos.x(), pos.y()-1);
                selected.put(newPos, "^");
                selected.remove(pos);
            } else if (selected.get(pos) == "v") {
                ///
                Position newPos = new Position(pos.x(), pos.y()+1);
                selected.put(newPos, "v");
                selected.remove(pos);
            }
            
        }
    }   
    
    public boolean isBorder(Position position) {
        if (position.y() == 0 || position.y() == 9) {
            return true;
        } else {
            return false;
        }
    }

    public Position getNext() {
        List<Position> keys = new ArrayList<>(selected.keySet());
        if (keys.isEmpty()) {
            throw new IllegalStateException("nessuna cella selezionata");
        }

        Position pos = keys.get(this.currentIndex % keys.size());
        this.currentIndex++;
        return pos;
    }

    @Override
    public boolean hasValue(Position position) {
        return selected.containsKey(position);
    }

    @Override
    public String getValue(Position position) {
        //if (selected.containsKey(position)) {
            return selected.get(position);
        //} else {
            //return null;
        //}
    }
}

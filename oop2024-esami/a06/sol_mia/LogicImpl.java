package a06.sol_mia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LogicImpl implements Logic{
    
    private final Map<Position, Integer> cells = new HashMap<>();
    //private final List<Integer> base;
    private final int size;

    public LogicImpl(int size) {
        this.size = size;
    }

    @Override
    public void hit(Position pos) {
        this.cells.merge(pos, 1, (a, b) -> a + b > size ? null : a + b);
    }

    private static boolean checkConfiguration(Position position) {
        
        // for every cell in cells
        // ROW
        // if ( (position.value&&not null) != (position+1.value &&not null) != (position+2.value &&not null) ) return true
        //POi stessa cosa per column
        // ...
        // if tutto perfetto return true
        // else return false

        return false;
    }

    @Override
    public boolean hasValue(Position pos) {
        return this.cells.containsKey(pos);
    }

    @Override
    public int getValue(Position pos) {
        return this.cells.get(pos);
    }

    @Override
    public boolean solved() {
        // Checks all cells to see if its solved
            // inside it should be:
                // for every cell check 1 in every direction
                // if condition ++
                // if condition >= cap -> solved
        return false;
    }

    @Override
    public void addValue(int val) {
        
    }
}

package a02a.sol_mia;
import java.util.*;

public interface Logic {
    Optional<Integer> indexOf(Position position);
    int counter();
    boolean hit(Position position);
    boolean isOver();
}

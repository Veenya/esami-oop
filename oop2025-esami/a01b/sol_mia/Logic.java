package a01b.sol_mia;

import java.util.Optional;

public interface Logic {
    
    Optional<Integer> indexOf(Position position);
    boolean hit(Position position);

    boolean isOver();
}

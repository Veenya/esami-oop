package a04.sol2;

import java.util.Optional;

public interface Logic {

    boolean hit(Position position);

    Optional<Integer> indexOf(Position pos);

}

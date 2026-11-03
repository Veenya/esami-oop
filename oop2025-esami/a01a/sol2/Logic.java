package e01a.sol2;

import java.util.Optional;

public interface Logic {

    boolean hit(Position position);

    boolean isOver();

    Optional<Integer> indexOf(Position pos);

}

package a06.sol_mia;

import java.util.Optional;

public interface Logic {
    void hit(Position position);
    boolean solved();
    
    boolean hasValue(Position pos);
    int getValue(Position pos);  

    void addValue(int val);
}

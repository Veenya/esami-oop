package a04.sol_mia;

public interface Logic {

    boolean hit(Position position);
    boolean isOver();

    boolean hasValue(Position position);
    void initValue(Position position);
    int getValue(Position position);
    void clear();
    
}

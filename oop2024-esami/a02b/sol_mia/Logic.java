package a02b.sol_mia;

public interface Logic {
    
    boolean hit(Position position);
    boolean isOver();
    boolean isMap(Position position);

    void reset();

    int getValue(Position position);
    boolean isPresent(Position position);
}

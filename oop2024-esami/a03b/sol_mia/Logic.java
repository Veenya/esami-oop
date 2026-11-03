package a03b.sol_mia;

public interface Logic {
    boolean hit(Position position);
    boolean isOver();

    void reset();

    boolean hasValue(Position position);
    String getValue(Position position);
}

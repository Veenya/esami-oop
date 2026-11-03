package a03a.sol_mia;

public interface Logic {
    boolean hit(Position position);
    boolean isOver();

    boolean hasValue(Position position);
    String getValue(Position position);
}

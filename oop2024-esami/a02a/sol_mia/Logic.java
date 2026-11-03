package a02a.sol_mia;

public interface Logic {
    boolean hit(Position position);
    boolean isBorder(Position position);
    boolean isOVer();

    boolean hasValue(Position position);
    String getValue(Position position);
}

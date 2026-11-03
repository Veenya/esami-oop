package a04.sol_mia;

public interface Logic {
    
    boolean disableCondition(Position position, int size);

    /* Ritorna true se il click ha avuto effetto */
    boolean hit(Position position);

    /* Ritorna true se la condizione di stop c'e' */
    boolean isOver();

    boolean isStar(Position position);
}

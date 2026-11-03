package a03a.sol_mia;

public interface Logic {
    /* comunica un click, ritorna se la selezione resta valida. */
    boolean hit(Position position);
    /* dice se il gioco è finito (8 celle selezionate). */
    boolean isOver();
    /* Dice se una posizione va disabilitata perche' sta sul bordo del rettangolo finale */
    boolean isBorder(Position position); 
}

package a05.sol_mia;

public interface Logic {

    /**
     * Controlla se il click è valido rispetto allo stato attuale
     * Aggiorna selected (aggiunge, oppure svuota e ricomincia)
     * 
     * @param position
     * @return dice alla GUI se il click è stato "accettato" (per decidere se disegnare qualcosa sulla cella o no)
     */
    boolean hit(Position position);

    boolean isOver();

    String getValue(Position position);
}

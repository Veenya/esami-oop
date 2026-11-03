package a01b.sol_mia;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class LogicImpl implements Logic {

    private final LinkedList<Position> selected = new LinkedList<>();

    private static final List<Position> STAR = List.of(
            new Position(0, 0),
            new Position(0, -1),
            new Position(1, 0),
            new Position(0, 1),
            new Position(-1, 0)
    );

    @Override
    public boolean hit(Position position) {
        if (this.selected.contains(position)) {
            return false;
        }
        this.selected.add(position);
        return true;
    }

    /**
     * Dato un centro, il metodo verifica se il giocatore ha selezionato esattamente le 5 celle che formano 
     * una "stella" attorno a quel centro. Se sì, ritorna quelle 5 celle avvolte in un Optional; 
     * se anche solo una manca, ritorna un Optional vuoto.
     */
    private Optional<List<Position>> star(Position center) {
        List<Position> cellsOfThisStar = new LinkedList<>(); // Lista dove accumuliamo le celle della stella che risultano slezionata

        for (Position offset : STAR) { // for each
            Position cell = new Position(center.x() + offset.x(), center.y() + offset.y());
            if (this.selected.contains(cell)) {
                cellsOfThisStar.add(cell);
            }
        }

        if (cellsOfThisStar.size() == 5) {
            return Optional.of(cellsOfThisStar);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Prova tutte le posizioni che l'ultimo click potrebbe occupare dentro
     * una stella (centro, sopra, destra, sotto, sinistra) e controlla se
     * una di queste ipotesi forma una stella completa.
     */
    private Optional<List<Position>> configuration() {
        if (this.selected.isEmpty()) {
            return Optional.empty();
        }

        Position lastClicked = this.selected.getLast();

        for (Position offset : STAR) {
            Position possibleCenter = new Position(
                    lastClicked.x() + offset.x(),
                    lastClicked.y() + offset.y()
            );

            Optional<List<Position>> result = star(possibleCenter);
            if (result.isPresent()) {
                return result;
            }
        }

        return Optional.empty();
    }

    @Override
    public boolean isOver() {
        return !this.selected.isEmpty() && this.configuration().isPresent();
    }

    /**
     * Se esiste una stella vincente e "pos" ne fa parte, ritorna in che
     * ordine cronologico è stata cliccata (0 = prima delle 5, 4 = ultima).
     */
    @Override
    public Optional<Integer> indexOf(Position pos) {
        Optional<List<Position>> winningStar = this.configuration();
        if (winningStar.isEmpty()) {
            return Optional.empty();
        }

        List<Position> starCells = winningStar.get();

        List<Position> clickedInOrder = new LinkedList<>();
        for (Position clicked : this.selected) {
            if (starCells.contains(clicked)) {
                clickedInOrder.add(clicked);
            }
        }

        int index = clickedInOrder.indexOf(pos);
        if (index == -1) {
            return Optional.empty();
        }
        return Optional.of(index);
    }
}
package a02a.sol_mia;

import java.util.LinkedList;
import java.util.Optional;

public class LogicImpl implements Logic{
 
    private final LinkedList<Position> selected = new LinkedList<>();
    private int counter = 0;
    private boolean isOver = false;
    
    /*
     * 4 celle di fila nella stessa riga o colonna
     *
     * 1 - l'utente clicka su una cella qualunque della griglia: su questa compare un "1" e si disabilita
     * 2 - l'utente clicka su una ulteriore cella qualunque della griglia:
     *  2.1 se non è nella stessa riga o colonna della precedente, si cancellano tutti i numeri presenti e si ricomincia dal punto 1
     *  2.2 se invece è nella stessa riga o colonna della precedente, compare il numero successivo (2,3,4 e così via), e in più...
     *    2.2.1 se la cella appena selezionata è tale per cui le ultime 4 selezionate stanno tutte nella stessa riga o nella stessa colonna, 
     *          allora si disabilitano tutte le celle, e il "gioco" finisce
     *    2.2.2 se invece non vale la condizione di cui al punto 2.2.1, si ricomincia dal punto 2
     * 
     */

    @Override
    public boolean hit(Position position) {

        if (this.selected.contains(position)) {
            return false;
        }

        if ((!this.selected.isEmpty() && (position.x() == selected.getLast().x() || position.y() == selected.getLast().y())) || (this.selected.isEmpty())) {
            this.selected.add(position);
            this.counter ++;

            if (this.condition()) {
                this.isOver = true;
                // todo metti altro forse boh
                System.out.println("WON");
            }
        } else {
            this.reset();
            return false;
            
        }
        return true;
    }

    // 4 celle selezionate in una stessa riga o colonna
    public boolean condition() {
        //return false;
        // guarda tutte le selected
        int rowCounter = 0;
        int colCounter = 0;

        for (Position pos: selected) {
            //System.out.println(pos);

            int lastX = this.selected.getLast().x();
            int lastY = this.selected.getLast().y();
            

            if (pos.x() == lastX) {colCounter++;}
            if (pos.y() == lastY) {rowCounter++;}

            if (rowCounter >=3 || colCounter>=3) {
                //System.out.println("VIDNSDOTODFJLSD");
                return true;
                
            } else {
                //System.out.println("XXXXXXXXXXXXX");
            }

            
        }

        return false;
    }

    public void reset() {
        this.selected.clear(); // svuota la lista
        this.counter = 0; // resetta counter a 1
    }


    @Override
    public int counter() {
        return counter;
    }

    @Override
    public boolean isOver() {
        return this.isOver;
    }

    @Override
    public Optional<Integer> indexOf(Position position) {
        return null;
    }
}

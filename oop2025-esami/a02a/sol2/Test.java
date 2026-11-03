package a02a.sol2;

public class Test {

	 /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine fig1.png, fornita, 
     * che realizza la possibilità di selezionare un insieme di celle, in cui ognuna è nella stessa riga o colonna
     * della precedente, finché non se ne selezionano 4 di fila nella stessa riga o colonna:
     * 1 - l'utente clicka su una cella qualunque della griglia: su questa compare un "1" e si disabilita
     * 2 - l'utente clicka su una ulteriore cella qualunque della griglia:
     *  2.1 se non è nella stessa riga o colonna della precedente, si cancellano tutti i numeri presenti e si ricomincia dal punto 1
     *  2.2 se invece è nella stessa riga o colonna della precedente, compare il numero successivo (2,3,4 e così via), e in più...
     *    2.2.1 se la cella appena selezionata è tale per cui le ultime 4 selezionate stanno tutte nella stessa riga o nella stessa colonna, 
     *          allora si disabilitano tutte le celle, e il "gioco" finisce
     *    2.2.2 se invece non vale la condizione di cui al punto 2.2.1, si ricomincia dal punto 2
     * 
     * Sono considerati opzionali ai fini della possibilità di correggere l'esercizio, ma concorrono comunque 
     * al raggiungimento della totalità del punteggio:
     * - scorporamento via delegazione di tutti gli aspetti che non sono di view in una interfaccia+classe esterna
     * - gestione del punto 2.2.1 (ossia non si gestisca la conclusione del gioco)
     *  
     * La classe GUI fornita, da modificare, include codice che potrebbe essere utile per la soluzione.
     * 
     * Indicazioni di punteggio:
	 * - correttezza della parte obbligatoria: 10 punti
	 * - qualità della parte opzionale: 5 punti
	 * - correttezza della parte opzionale: 2 punti
     */


    public static void main(String[] args) throws java.io.IOException {
        new GUI(10); 
    }
}

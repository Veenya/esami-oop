package a03a.sol2;

public class Test {

	 /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine fig1.png, fornita, e con la 
     * seguente logica: si selezionano un insieme di celle, in cui ognuna è adiacente (in orizzontale, verticale o diagonale) 
     * alla precedente, finché non se ne selezionano 8, a quel punto si disabilitano le celle nelle righe e colonne di confine
     * del gruppo di celle selezionate. In dettaglio:
     * 1 - l'utente clicka su una cella qualunque della griglia: su questa compare un "*"
     * 2 - l'utente clicka su una ulteriore cella qualunque della griglia:
     *  2.1 se non è adiacente alla precedente, si cancellano tutti le "*" presenti e si ricomincia dal punto 1
     *  2.2 se invece è adiacente alla precedente, compare una nuova "*" (si veda fig 1 dopo 7 click), e in più...
     *  2.3 arrivati all'ottavo click, si disabilitino tutte le celle che stanno nelle 2 righe e nelle 2 colonne di confine del
     *    gruppo di celle selezionate (ossia che individuano il più piccolo rettangolo che include tutte le celle con la *, 
     *    come da (fig 2)
     * 
     * Sono considerati opzionali ai fini della possibilità di correggere l'esercizio, ma concorrono comunque 
     * al raggiungimento della totalità del punteggio:
     * - scorporamento via delegazione di tutti gli aspetti che non sono di view in una interfaccia+classe esterna
     * - gestione completa del punto 2.3 (ossia si disabilitino a piacimento o solo le 2 righe o solo le 2 colonne)
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

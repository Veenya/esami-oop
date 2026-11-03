package a03b.sol2;

public class Test {

	 /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine fig1.png, fornita, e con la 
     * seguente logica: si selezionano un insieme di celle che devono stare in un quadrato 3x3, finché non se ne selezionano 4, 
     * a quel punto si disabilitano le celle nel minimo rettangolo che le contiene. In dettaglio:
     * 1 - l'utente clicka su una cella qualunque della griglia: su questa compare un "*"
     * 2 - l'utente clicka su una ulteriore cella qualunque della griglia:
     *  2.1 se questa cella, insieme alle precedenti, non sta in un quadrato 3x3, si cancellano tutte le "*" presenti e si ricomincia dal punto 1
     *  2.2 se invece stanno nel quadrato, compare una nuova "*" (si veda fig 1 dopo 3 click), e in più...
     *  2.3 arrivati al quarto click, si disabilitino tutte le celle che stanno nel più piccolo rettangolo che include
     *   tutte le celle con la *, e a questo punto la pressione di un qualunque altro pulsante non ha più alcun effetto (fig 2)
     * 
     * Sono considerati opzionali ai fini della possibilità di correggere l'esercizio, ma concorrono comunque 
     * al raggiungimento della totalità del punteggio:
     * - scorporamento via delegazione di tutti gli aspetti che non sono di view in una interfaccia+classe esterna
     * - gestione del punto 2.1 (ossia non si cancellino tutte le *, ma semplicemente si ignori il click)
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

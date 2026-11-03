package e01b.e2;

public class Test {

	 /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine fig1.png, fornita, 
     * che realizza la possibilità di selezionare un insieme di celle, finché non si seleziona una crocetta
     * fatta da 5 celle:
     * 1 - l'utente clicka su una cella qualunque della griglia, e su questa compare un "*" (fig 1 dopo un po' di click)
     * 2 - quando si clicka una cella che va a completare un croce da cinque caselle (centrale/su/giù/dx/sx, come da fig 2 
     * clickando sulla cella di quart'ultima riga e quart'ultima colonna) si ha che:
     *  2.1 - le 5 celle di questa crocetta si disabilitano
     *  2.2 - le 5 celle di questa colonna si numerano incrementalmente (0,1,2,3,4) sulla base di quando furono selezionate
     * (0 la prima, 1 la seconda,... 4 l'ultima)
     *  2.3 - clickando su una qualunque cella l'applicazione si chiude
     * 
     * Sono considerati opzionali ai fini della possibilità di correggere l'esercizio, ma concorrono comunque 
     * al raggiungimento della totalità del punteggio:
     * - scorporamento via delegazione di tutti gli aspetti che non sono di view in una interfaccia+classe esterna
     * - gestione del punto 2.2
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

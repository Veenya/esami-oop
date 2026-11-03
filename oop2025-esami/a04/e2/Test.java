package a04.e2;

public class Test {

	 /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine fig1.png, fornita, 
     * che realizza la possibilità di selezionare via via una cella, mostrando un percorso da questa a quella 
     * precedentemente selezionata, senza mai fermarsi:
     * 1 - inizialmente compare uno zero in alto a destra, disabilitato, che assumiamo sia la selezione corrente C1
     * 2 - quando si clicka su una cella C2 allora dovranno essere disabilitate tutte le celle di un percorso che va da C1 a C2, come segue:
     *  2.1 - tale percorso parte da C1 orizzontalmente, e poi si muove verso C2 verticalmente, disabilitando tutte le celle che incontra
     *  2.2 - in tale percorso le celle disabilitate dovranno mostrare un numero, che parte da 0 in C2 e a ritroso 
     * aumenta di 1 per ogni cella successiva, fino a raggiungere il numero 4, dopodiché tutte le celle successive mostreranno il numero -1
     *  2.3 - clickando su una qualunque ulteriore cella si riparte da 2.1
     * (le figure fig2, fig3, fig4 mostrano cosa succede con click successivi -- si consideri che la cella clickata mostra lo 0)
     * 
     * Sono considerati opzionali ai fini della possibilità di correggere l'esercizio, ma concorrono comunque 
     * al raggiungimento della totalità del punteggio:
     * - scorporamento via delegazione di tutti gli aspetti che non sono di view in una interfaccia+classe esterna
     * - gestione parziale del punto 2.2 (va bene se le celle disabilitate mostrano semplicemente un numero crescente)
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

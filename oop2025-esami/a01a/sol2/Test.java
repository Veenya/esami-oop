package e01a.sol2;

public class Test {

	 /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine fig1.png, fornita, 
     * che realizza la possibilità di selezionare un insieme di celle, finché nella colonna e nella riga della selezione ci
     * sono precisamente 5 celle selezionate:
     * 1 - l'utente clicka su una cella qualunque della griglia, e su questa compare un "*"
     * 2 - se la cella selezionata C è su una colonna con 5 selezioni e su una riga con 5 selezioni, si ha che:
     *  2.1 - le celle selezionate presenti nella colonna di C e nella riga di C si disabilitano, tranne C stessa
     *  2.2 - le celle disabilitate nella riga di C vengono numerate incrementalmente (0,1,2,3), da sinistra a destra
     *  2.3 - le celle disabilitate nella colonna di C vengono numerate incrementalmente (0,1,2,3), dall'alto al basso
     *  2.4 - clickando su una qualunque cella l'applicazione si chiude
     * 
     * Sono considerati opzionali ai fini della possibilità di correggere l'esercizio, ma concorrono comunque 
     * al raggiungimento della totalità del punteggio:
     * - scorporamento via delegazione di tutti gli aspetti che non sono di view in una interfaccia+classe esterna
     * - gestione dei punto 2.3 e 2.4
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

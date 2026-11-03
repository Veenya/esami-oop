package a02b.e1;

import static org.junit.Assert.*;

import java.util.*;

public class Test {

	/*
	 * Implementare l'interfaccia CompetitionBuilder come indicato nel metodo
	 * init qui sotto. Realizza un builder per impostare tutti i dati di una competizione con
	 * batterie di qualificazione ("heats") in semifinale, e dove il vincitore di ogni batteria 
	 * finisce in finale. Si assume una gara di 100 metri, dove quindi vince chi fa il tempo/risultato
	 * più basso.
	 * Un esempio di corretto uso del builder è nel metodo basicCompetition qui sotto.
	 * Impostati i vari dati, il builder genera un Competition, usabile per ottenere statistiche.
	 * 
	 * Sono considerati opzionali ai fini della possibilità di correggere
	 * l'esercizio, ma concorrono comunque al raggiungimento della totalità del
	 * punteggio:
	 * 
	 * - far passare tutti i test (ossia, nella parte obbligatoria si può omettere di considerare
	 * un test)
	 * - la buona progettazione della soluzione
	 * 
	 * Si tolga il commento dal metodo initFactory.
	 * 
	 * Indicazioni di punteggio:
	 * - correttezza della parte obbligatoria: 10 punti
	 * - correttezza della parte opzionale: 3 punti (far passare anche l'ultimo test)
	 * - qualità della soluzione: 4 punti (per buon design)
	 */


	private CompetitionBuilder builder;

	@org.junit.Before
	public void init() {
		//this.builder = new CompetitionBuilderImpl();
	}

	private Competition basicCompetition(){
		// una competizione di una gara con 3 batterie
		this.builder.newHeat(); // prima batteria, vince l'atleta 100
		this.builder.heatResult(100, 9.90);
		this.builder.heatResult(101, 9.94);
		this.builder.heatResult(102, 9.98);
		this.builder.heatResult(103, 9.99);
		this.builder.newHeat(); // seconda batteria, vince l'atleta 202
		this.builder.heatResult(200, 9.90);
		this.builder.heatResult(201, 9.94);
		this.builder.heatResult(202, 9.89);
		this.builder.heatResult(203, 9.92);
		this.builder.heatResult(204, 9.91);
		this.builder.newHeat(); // terza batteria, vince l'atleta 303
		this.builder.heatResult(300, 9.93);
		this.builder.heatResult(301, 9.97);
		this.builder.heatResult(302, 9.92);
		this.builder.heatResult(303, 9.87);
		this.builder.heatResult(304, 9.91);
		this.builder.finalMatch(); // inizia la finale, e ci sono nuovi risultati 
		this.builder.finalResult(100, 9.94);
		this.builder.finalResult(202, 9.88);
		this.builder.finalResult(303, 9.91);
		return this.builder.build();
	}
	
	@org.junit.Test
	public void basicTests() {
		// verifiche base sulle batterie della competizione Basic
		var event = basicCompetition();
		assertEquals(Set.of(100,101,102,103,200,201,202,203,204,300,301,302,303,304), event.athletes());
		assertEquals(3, event.heats());
		assertEquals(Map.of(100, 9.90, 101, 9.94, 102, 9.98, 103, 9.99), event.heatResults(0));
		assertEquals(Map.of(200, 9.90, 201, 9.94, 202, 9.89, 203, 9.92, 204, 9.91), event.heatResults(1));
	}

	@org.junit.Test
	public void testFinalResults() {
		// verifiche sui risultati finali
		var event = basicCompetition();
		assertEquals(List.of(9.88, 9.91, 9.94), event.orderedFinalResults());
	}

	@org.junit.Test
	public void testAthleteResults() {
		// verifiche sui risultati di vari atleti: qualcuno ne ha solo uno, i finalisti ne hanno due
		var event = basicCompetition();
		assertEquals(List.of(9.87, 9.91), event.athleteResults(303));
		assertEquals(List.of(9.90, 9.94), event.athleteResults(100));
		assertEquals(List.of(9.98), event.athleteResults(102));
		assertEquals(List.of(9.93), event.athleteResults(300));
	}

	@org.junit.Test
	public void testErrors() {
		// si intercettano vari errori nella costruzione di una Competition
		assertThrows("Should start a heat first", IllegalStateException.class, () -> this.builder.heatResult(100, 9.89));
		this.builder.newHeat();
		this.builder.heatResult(100, 9.90);
		assertThrows("Athlete already registered", IllegalStateException.class, () -> this.builder.heatResult(100, 9.89));
		this.builder.heatResult(101, 9.94);
		this.builder.heatResult(102, 9.98);
		this.builder.heatResult(103, 9.99);
		this.builder.newHeat();
		assertThrows("Athlete already registered", IllegalStateException.class, () -> this.builder.heatResult(100, 9.89));
		this.builder.heatResult(200, 9.90);
		this.builder.heatResult(201, 9.94);
		this.builder.heatResult(202, 9.89);
		this.builder.heatResult(203, 9.92);
		this.builder.heatResult(204, 9.91);
		this.builder.newHeat();
		this.builder.heatResult(300, 9.93);
		this.builder.heatResult(301, 9.97);
		this.builder.heatResult(302, 9.92);
		this.builder.heatResult(303, 9.87);
		this.builder.heatResult(304, 9.91);
		assertThrows("Cannot build before the final match", IllegalStateException.class, () -> this.builder.build());
		this.builder.finalMatch();
		assertThrows("Athlete did not attend a heat", IllegalStateException.class, () -> this.builder.heatResult(500, 9.89));
		this.builder.finalResult(100, 9.94);
		assertThrows("Only the best of each heat can be in the final", IllegalStateException.class, () -> this.builder.finalResult(102, 9.89));
		this.builder.finalResult(202, 9.88);
		this.builder.finalResult(303, 9.91);
	}
}

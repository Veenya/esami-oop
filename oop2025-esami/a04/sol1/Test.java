package a04.sol1;

import static org.junit.Assert.*;

import java.util.*;

public class Test {

	/*
	 * Implementare l'interfaccia Competition come indicato nel metodo init qui sotto. 
	 * Realizza un comcetto di competizione con batterie di qualificazione ("heats") in semifinale, e
	 * dove chi fa un risultato entro il cutoff finisce in finale. Si assume una gara di 100 metri, 
	 * dove quindi si qualifica chi in batteria fra un tempo minore o uguale al cutoff.
	 * Un esempio di corretta compilazione dei risultati è nel metodo basicCompetition qui sotto.
	 * Impostati i vari dati, si possono poi ottenere statistiche.
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


	private Competition competition;

	@org.junit.Before
	public void init() {
		this.competition = new CompetitionImpl();
	}

	private void basicCompetition() {
		// una competizione di una gara con 3 batterie
		this.competition.setCutoff(9.91);
		this.competition.newHeat(); // prima batteria, si qualifica solo l'atleta 100
		this.competition.addResult(100, 9.90);
		this.competition.addResult(101, 9.94);
		this.competition.addResult(102, 9.98);
		this.competition.addResult(103, 9.99);
		this.competition.newHeat(); // seconda batteria, si qualificano solo gli atleti 200, 202 e 204
		this.competition.addResult(200, 9.90);
		this.competition.addResult(201, 9.94);
		this.competition.addResult(202, 9.89);
		this.competition.addResult(203, 9.92);
		this.competition.addResult(204, 9.91);
		this.competition.newHeat(); // terza batteria, si qualificano solo gli atleti 303 e 304
		this.competition.addResult(300, 9.93);
		this.competition.addResult(301, 9.97);
		this.competition.addResult(302, 9.92);
		this.competition.addResult(303, 9.87);
		this.competition.addResult(304, 9.91);
		this.competition.startFinal(); // inizia la finale (per gli atleti qualificati), e ci sono nuovi risultati 
		this.competition.addResult(100, 9.94);
		this.competition.addResult(200, 9.93);
		this.competition.addResult(202, 9.92);
		this.competition.addResult(204, 9.91);
		this.competition.addResult(303, 9.90);
		this.competition.addResult(304, 9.89);
	}
	
	@org.junit.Test
	public void basicTests() {
		this.basicCompetition();
		// verifiche base sulle batterie della competizione Basic
		assertEquals(Set.of(100,101,102,103,200,201,202,203,204,300,301,302,303,304), this.competition.athletes());
		assertEquals(3, this.competition.heats());
		assertEquals(Map.of(100, 9.90, 101, 9.94, 102, 9.98, 103, 9.99), this.competition.heatResults(0));
		assertEquals(Map.of(200, 9.90, 201, 9.94, 202, 9.89, 203, 9.92, 204, 9.91), this.competition.heatResults(1));
	}

	@org.junit.Test
	public void testFinalResults() {
		this.basicCompetition();
		// verifiche sui risultati finali
		assertEquals(Map.of(100, 9.94, 200, 9.93, 202, 9.92, 204, 9.91, 303, 9.90, 304, 9.89), this.competition.finalResults());
		assertEquals(List.of(9.89, 9.90, 9.91, 9.92, 9.93, 9.94), this.competition.orderedFinalResults());
	}

	@org.junit.Test
	public void testAthleteResults() {
		this.basicCompetition();
		// verifiche sui risultati di vari atleti: qualcuno ne ha solo uno, i finalisti ne hanno due
		assertEquals(List.of(9.87, 9.90), this.competition.athleteResults(303));
		assertEquals(List.of(9.90, 9.94), this.competition.athleteResults(100));
		assertEquals(List.of(9.98), this.competition.athleteResults(102));
		assertEquals(List.of(9.93), this.competition.athleteResults(300));
	}

	@org.junit.Test
	public void testErrors() {
		// si intercettano vari errori nella costruzione di una Competition
		this.competition.setCutoff(9.91);
		assertThrows("Should start a heat first", IllegalStateException.class, () -> this.competition.addResult(100, 9.89));
		this.competition.newHeat();
		this.competition.addResult(100, 9.90);
		assertThrows("Athlete already registered", IllegalStateException.class, () -> this.competition.addResult(100, 9.89));
		this.competition.addResult(101, 9.94);
		this.competition.addResult(102, 9.98);
		this.competition.addResult(103, 9.99);
		this.competition.newHeat();
		assertThrows("Athlete already registered", IllegalStateException.class, () -> this.competition.addResult(100, 9.89));
		this.competition.addResult(200, 9.90);
		this.competition.addResult(201, 9.94);
		this.competition.addResult(202, 9.89);
		this.competition.addResult(203, 9.92);
		this.competition.addResult(204, 9.91);
		this.competition.newHeat();
		this.competition.addResult(300, 9.93);
		this.competition.addResult(301, 9.97);
		this.competition.addResult(302, 9.92);
		this.competition.addResult(303, 9.87);
		this.competition.addResult(304, 9.91);
		assertThrows("Cannot get final results before the final started", IllegalStateException.class, () -> this.competition.finalResults());
		this.competition.startFinal();
		assertThrows("Athlete did not attend a heat", IllegalStateException.class, () -> this.competition.addResult(500, 9.89));
		this.competition.addResult(100, 9.94);
		assertThrows("Only athletes under the cutoff can be in the final", IllegalStateException.class, () -> this.competition.addResult(102, 9.89));
		this.competition.addResult(202, 9.88);
		this.competition.addResult(303, 9.91);
	}
}

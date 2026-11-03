package a02a.e1;

import static org.junit.Assert.*;

import java.util.*;

public class Test {

	/*
	 * Implementare l'interfaccia TournamentBuilder come indicato nel metodo
	 * init qui sotto. Realizza un builder per impostare tutti i dati di un torneo all'italiana
	 * a singolo girone a N squadre (N pari), dove in ogni giornata ("round") ci sono N/2 partite,
	 * e dopo N-1 giornate nessuna coppia di squadre ha giocato contro più di una volta.
	 * Un esempio di corretto uso del builder è nel metodo basicTournament qui sotto.
	 * Impostati i vari dati, il builder genera un Tournament, usabile per ottenere statistiche.
	 * 
	 * Sono considerati opzionali ai fini della possibilità di correggere
	 * l'esercizio, ma concorrono comunque al raggiungimento della totalità del
	 * punteggio:
	 * 
	 * - far passare tutti i test (ossia, nella parte obbligatoria si può omettere di considerare
	 * un test)
	 * - la buona progettazione della soluzione
	 * 
	 * Si tolga il commento dal metodo init.
	 * 
	 * Indicazioni di punteggio:
	 * - correttezza della parte obbligatoria: 10 punti
	 * - correttezza della parte opzionale: 3 punti (far passare anche l'ultimo test)
	 * - qualità della soluzione: 4 punti (per buon design)
	 */

	private TournamentBuilder builder;

	@org.junit.Before
	public void init() {
		// this.builder = new TournamentBuilderImpl();
	}

	private Tournament basicTournament(){
		this.builder.teams(Set.of("Inter", "Milan", "Napoli", "Juve"));
		this.builder.newRound(); // prima giornata
		this.builder.addGame("Inter", "Milan");
		this.builder.addGame("Napoli", "Juve");
		this.builder.newRound(); // seconda giornata
		this.builder.addGame("Inter", "Juve");
		this.builder.addGame("Napoli", "Milan");
		this.builder.newRound(); // terza giornata
		this.builder.addGame("Inter", "Napoli");
		this.builder.addGame("Juve", "Milan");
		return this.builder.build();
	}

	@org.junit.Test
	public void testBasic() {
		// verifiche base sul sistema Basic
		var turnament = basicTournament();
		assertEquals(Set.of("Inter", "Milan", "Napoli", "Juve"), turnament.teams());
		assertEquals(3, turnament.rounds());
	}
	
	@org.junit.Test
	public void testTeams() {
		// verifiche di corretto tracciamento delle giornate 0, 1, 2
		var turnament = basicTournament();
		assertEquals(Set.of("Inter", "Milan", "Napoli", "Juve"), turnament.teams());
		assertEquals(3, turnament.rounds());
		assertEquals(Set.of(
			new Pair<>("Inter", "Milan"),
			new Pair<>("Napoli", "Juve")), turnament.roundGames(0));
		assertEquals(Set.of(
			new Pair<>("Inter", "Juve"),
			new Pair<>("Napoli", "Milan")), turnament.roundGames(1));	
		assertEquals(Set.of(
			new Pair<>("Inter", "Napoli"),
			new Pair<>("Juve" , "Milan")), turnament.roundGames(2));	
	}

	@org.junit.Test
	public void testSchedule() {
		// verifiche del calendario di ogni squadra, ossia l'ordine delle avversarie giornata per giornata
		var turnament = basicTournament();
		assertEquals(List.of("Milan", "Juve", "Napoli"), turnament.teamSchedule("Inter"));	
		assertEquals(List.of("Inter", "Napoli", "Juve"), turnament.teamSchedule("Milan"));	
		assertEquals(List.of("Napoli", "Inter", "Milan"), turnament.teamSchedule("Juve"));	
		assertEquals(List.of("Juve", "Milan", "Inter"), turnament.teamSchedule("Napoli"));	
	}

	@org.junit.Test
	public void testRanking() {
		// verifica sulla classifica finale
		var turnament = basicTournament();
		assertEquals(Map.of("Inter", 3, "Napoli", 2, "Juve", 1, "Milan", 0), turnament.finalRanking());
	}

	@org.junit.Test
	public void testErrors() {
		// si intercettano vari errori nella costruzione di un Tournament
		this.builder.teams(Set.of("Inter", "Milan", "Napoli", "Juve"));
		assertThrows("Need to start a round before adding a game", IllegalStateException.class, () -> this.builder.addGame("Inter", "Milan"));
		this.builder.newRound();
		assertThrows("One of the two teams has not been registered", IllegalArgumentException.class, () -> this.builder.addGame("Cesena", "Inter"));
		this.builder.addGame("Inter", "Milan");
		assertThrows("One of the two teams already played", IllegalStateException.class, () -> this.builder.addGame("Inter", "Juve"));
		assertThrows("Not all games have been played in this round", IllegalStateException.class, () -> this.builder.newRound());
		this.builder.addGame("Napoli", "Juve");
		this.builder.newRound();
		assertThrows("This game have already been played in a previous round", IllegalStateException.class, () -> this.builder.addGame("Inter", "Milan"));
		this.builder.addGame("Inter", "Juve");
		this.builder.addGame("Napoli", "Milan");
	}
}

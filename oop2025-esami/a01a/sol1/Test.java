package e01a.sol1;

import static org.junit.Assert.*;

import java.util.*;
import java.util.stream.Collectors;

public class Test {

	/*
	 * Implementare l'interfaccia GraphFactory come indicato nel metodo
	 * initFactory qui sotto. Realizza una factory per un concetto di grafo, 
	 * catturato dall'interfaccia Graph.
	 * 
	 * Sono considerati opzionali ai fini della possibilità di correggere
	 * l'esercizio, ma concorrono comunque al raggiungimento della totalità del
	 * punteggio:
	 * 
	 * - implementazione di tutti i metodi della factory (ossia, nella parte
	 * obbligatoria è sufficiente implementarli tutti tranne uno a piacimento -- 
	 * il primo, empty, è obbligatorio)
	 * - la buona progettazione della soluzione, utilizzando soluzioni progettuali che portino a
	 * codice succinto
	 * 
	 * Si tolga il commento dal metodo initFactory.
	 * 
	 * Indicazioni di punteggio:
	 * - correttezza della parte obbligatoria: 10 punti
	 * - correttezza della parte opzionale: 3 punti (ulteriore metodo della factory)
	 * - qualità della soluzione: 4 punti (per buon design)
	 * 
	 */

	private GraphFactory factory;

	@org.junit.Before
	public void initFactory() {
		this.factory = new GraphFactoryImpl();
	}

	private Graph<Integer> basicGraph(){
		// un grafo con catena bidirezionale fra i nodi 0,1,2,3 (0 <->1 <-> 2 <->3),
		// poi con l'arco 4 -> 5,
		// e poi con autoarchi sui i nodi 0,1,2,3,4
		return this.factory
				.emptyGraph(Set.of(0, 1, 2, 3, 4, 5))
				.withEdgesFromNode(0, Set.of(0, 1))
				.withEdgesFromNode(1, Set.of(0, 1, 2))
				.withEdgesFromNode(2, Set.of(1, 2, 3))
				.withEdgesFromNode(3, Set.of(2, 3))
				.withEdgesFromNode(4, Set.of(4, 5));
	}

	private void basicTestsOnGraph(Graph<Integer> graph){
		// test base per il grafo di cui sopra
		// per i metodi nodes, reachableInOneStep, toMap
		assertEquals(Set.of(0, 1, 2, 3, 4, 5), graph.nodes());
		assertEquals(Set.of(0, 1), graph.reachableInOneStep(0));
		assertEquals(Set.of(0, 1, 2), graph.reachableInOneStep(1));
		assertEquals(Set.of(1, 2, 3), graph.reachableInOneStep(2));
		assertEquals(Set.of(2, 3), graph.reachableInOneStep(3));
		assertEquals(Set.of(4, 5), graph.reachableInOneStep(4));
		assertEquals(Set.of(), graph.reachableInOneStep(5));
		assertEquals(Map.of(
			0, Set.of(0, 1), 
			1, Set.of(0, 1, 2), 
			2, Set.of(1, 2, 3), 
			3, Set.of(2, 3),
			4, Set.of(4, 5),
			5, Set.of()), graph.toMap());
	}

	@org.junit.Test
	public void testBasics() {
		// test base basicGraph
		Graph<Integer> graph = basicGraph();
		this.basicTestsOnGraph(graph);
	}

	@org.junit.Test
	public void testReachableInTwoSteps() {
		// funzionamento del metodo reachableInTwoSteps
		Graph<Integer> graph = basicGraph();
		assertEquals(Set.of(0, 1, 2), graph.reachableInTwoSteps(0));
		assertEquals(Set.of(0, 1, 2, 3), graph.reachableInTwoSteps(1));
		assertEquals(Set.of(0, 1, 2, 3), graph.reachableInTwoSteps(2));
		assertEquals(Set.of(1, 2, 3), graph.reachableInTwoSteps(3));
		assertEquals(Set.of(4, 5), graph.reachableInTwoSteps(4));
		assertEquals(Set.of(), graph.reachableInTwoSteps(5));
	}

	@org.junit.Test
	public void testReachableFrom() {
		// funzionamento del metodo reachableFrom
		Graph<Integer> graph = basicGraph();
		assertEquals(Set.of(0, 1, 2, 3), graph.reachable(0));
		assertEquals(Set.of(0, 1, 2, 3), graph.reachable(1));
		assertEquals(Set.of(0, 1, 2, 3), graph.reachable(2));
		assertEquals(Set.of(0, 1, 2, 3), graph.reachable(3));
		assertEquals(Set.of(4, 5), graph.reachable(4));
	}

	@org.junit.Test
	public void testBasicsWithNextFunction() {
		// Modo alternativo per costruire il basicGraph, con la nextFunction
		Graph<Integer> graph = this.factory
				.emptyGraph(Set.of(0, 1, 2, 3, 4, 5))
				.withNextFunction(i -> switch(i){
					case 0, 1, 2, 3 -> Set.of(i-1, i, i+1)
							.stream()
							.filter(j -> j>=0)
							.filter(j -> j<=3)
							.collect(Collectors.toSet());
					case 4 -> Set.of(4, 5);
					default -> Set.of();
				});
		this.basicTestsOnGraph(graph);
	}

	@org.junit.Test
	public void testBasicsWithNextRelation() {
		// Modo alternativo per costruire il basicGraph, con la nextRelation
		Graph<Integer> graph = this.factory
				.emptyGraph(Set.of(0, 1, 2, 3, 4, 5))
				.withNextRelation(Set.of(
					new Pair<>(0, 0),
					new Pair<>(0, 1),
					new Pair<>(1,0),
					new Pair<>(1,1),
					new Pair<>(1,2),
					new Pair<>(2,1),
					new Pair<>(2,2),
					new Pair<>(2,3),
					new Pair<>(3,2),
					new Pair<>(3,3),
					new Pair<>(4,4),
					new Pair<>(4,5)
				));
		this.basicTestsOnGraph(graph);
	}
}
package a03a.e1;

import static org.junit.Assert.*;

import java.util.*;

public class Test {

	/*
	 * Implementare l'interfaccia DetectorFactory come indicato nel metodo
	 * init qui sotto. Realizza una factory per dei Detector, oggetti che ricevono
	 * sequenze di valori con una chiamata alla volta, e segnalano non appena possibile 
	 * che hanno intercettato una sottosequenza con certe caratteristiche dipendenti 
	 * dall'implementazione specifica.
	 * Ad esempio un detector potrebbe segnalare se riceve una sottosequenza 10,10...10
	 * lunga almeno 5.
	 * 
	 * Sono considerati opzionali ai fini della possibilità di correggere
	 * l'esercizio, ma concorrono comunque al raggiungimento della totalità del
	 * punteggio:
	 * 
	 * - far passare tutti i test (ossia, nella parte obbligatoria si può omettere di considerare
	 * un test)
	 * - la buona progettazione della soluzione, che porti a codice conciso e senza ripetizioni.
	 * 
	 * Si tolga il commento dal metodo initFactory.
	 * 
	 * Indicazioni di punteggio:
	 * - correttezza della parte obbligatoria: 10 punti
	 * - correttezza della parte opzionale: 3 punti (far passare anche l'ultimo test)
	 * - qualità della soluzione: 4 punti (per buon design)
	 */

	private DetectorFactory factory;

	@org.junit.Before
	public void init() {
		//this.factory = new DetectorFactoryImpl();
	}

	@org.junit.Test
	public void testExactly() {
		// intercetta esattamente la sottolista 10,20,30
		Detector<Integer> detector = this.factory.exactly(List.of(10,20,30));
		assertEquals(Optional.empty(), detector.parseNext(5)); // arriva un 5
		assertEquals(Optional.empty(), detector.parseNext(10)); // arriva un 10
		assertEquals(Optional.empty(), detector.parseNext(20)); // ...
		assertEquals(Optional.empty(), detector.parseNext(5));
		assertEquals(Optional.empty(), detector.parseNext(5));
		assertEquals(Optional.empty(), detector.parseNext(10));
		assertEquals(Optional.empty(), detector.parseNext(20));
		// arriva un 30, sono arrivati di seguito 10,20,30, segnalo la sequenza in uscita
		assertEquals(Optional.of(List.of(10,20,30)), detector.parseNext(30)); 
		// da qui in poi, continuo comunque a segnalarla
		assertEquals(Optional.of(List.of(10,20,30)), detector.parseNext(5));
		assertEquals(Optional.of(List.of(10,20,30)), detector.parseNext(5));
	}

	@org.junit.Test
	public void testStartAndSize() {
		// intercetta sottoliste che cominciano con 10 e sono lunghe 5
		Detector<Integer> detector = this.factory.byStartAndSize(10,5);
		assertEquals(Optional.empty(), detector.parseNext(5)); // arriva un 5
		assertEquals(Optional.empty(), detector.parseNext(6)); // arriva un 6
		assertEquals(Optional.empty(), detector.parseNext(10)); // arriva un 10... si comincia a tracciare
		assertEquals(Optional.empty(), detector.parseNext(11));
		assertEquals(Optional.empty(), detector.parseNext(12));
		assertEquals(Optional.empty(), detector.parseNext(13));
		// dopo 5 elementi, abbiamo 10,11,12,13,14, segnalo la sottolista e la mantengo
		assertEquals(Optional.of(List.of(10,11,12,13,14)), detector.parseNext(14)); 
		assertEquals(Optional.of(List.of(10,11,12,13,14)), detector.parseNext(30));
		assertEquals(Optional.of(List.of(10,11,12,13,14)), detector.parseNext(5));
		assertEquals(Optional.of(List.of(10,11,12,13,14)), detector.parseNext(5));
	}

	@org.junit.Test
	public void testWhileFromList() {
		// intercetta sottoliste di 10,20,30,40,50 lunghe almeno 4
		Detector<Integer> detector = this.factory.whileFromList(List.of(10,20,30,40,50), 4);
		assertEquals(Optional.empty(), detector.parseNext(5));
		assertEquals(Optional.empty(), detector.parseNext(10));
		assertEquals(Optional.empty(), detector.parseNext(20)); //solo 10,20... non basta
		assertEquals(Optional.empty(), detector.parseNext(5));
		assertEquals(Optional.empty(), detector.parseNext(5));
		assertEquals(Optional.empty(), detector.parseNext(10));
		assertEquals(Optional.empty(), detector.parseNext(20));
		assertEquals(Optional.empty(), detector.parseNext(30));
		assertEquals(Optional.of(List.of(10,20,30,40)), detector.parseNext(40)); //10,20...40, intercettato
		assertEquals(Optional.of(List.of(10,20,30,40,50)), detector.parseNext(50)); //anche il 50 arriva! lo segnalo
		assertEquals(Optional.of(List.of(10,20,30,40,50)), detector.parseNext(5)); // mantengo
		assertEquals(Optional.of(List.of(10,20,30,40,50)), detector.parseNext(5));
		detector.reset(); // con reset si ricomincia
		assertEquals(Optional.empty(), detector.parseNext(5));
		assertEquals(Optional.empty(), detector.parseNext(5));
		assertEquals(Optional.empty(), detector.parseNext(10));
		assertEquals(Optional.empty(), detector.parseNext(20));
		assertEquals(Optional.empty(), detector.parseNext(30));
		assertEquals(Optional.of(List.of(10,20,30,40)), detector.parseNext(40)); // 10,20,30,40 intercettato
		assertEquals(Optional.of(List.of(10,20,30,40)), detector.parseNext(5));
		assertEquals(Optional.of(List.of(10,20,30,40)), detector.parseNext(5));
	}

	@org.junit.Test
	public void testWhileFromCondition() {
		// intercetta sottoliste di numeri positivi lunghe almeno 4
		Detector<Integer> detector = this.factory.whileFromCondition(x -> x > 0, 4);
		assertEquals(Optional.empty(), detector.parseNext(5));
		assertEquals(Optional.empty(), detector.parseNext(5));
		assertEquals(Optional.empty(), detector.parseNext(-2));
		assertEquals(Optional.empty(), detector.parseNext(10));
		assertEquals(Optional.empty(), detector.parseNext(20));
		assertEquals(Optional.empty(), detector.parseNext(30));
		assertEquals(Optional.of(List.of(10,20,30,40)), detector.parseNext(40));
		assertEquals(Optional.of(List.of(10,20,30,40,50)), detector.parseNext(50));
		assertEquals(Optional.of(List.of(10,20,30,40,50)), detector.parseNext(-3));
		assertEquals(Optional.of(List.of(10,20,30,40,50)), detector.parseNext(5));
	}
}

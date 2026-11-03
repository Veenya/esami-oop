package a03b.e1;

import static org.junit.Assert.*;

import java.util.*;
import java.util.stream.Stream;

import a03b.e1.Picker.Source;

public class Test {

	/*
	 * Implementare l'interfaccia PickerFactory come indicato nel metodo init qui sotto. 
	 * Realizza una factory per dei Picker, che sono oggetti che data una sorgente dati infinita
	 * ne crea un'altra che produce solo alcuni degli elementi di quella di ingresso, con varie opzioni
	 * su come trattare gli elementi che vengano in realtà scartati. La factory genera due tipi di
	 * Picker, e ogni picker ha tre metodi di picking diversi, quindi ci sono 6 funzionalità da realizzare
	 * (molto simili tuttavia).
	 * 
	 * Sono considerati opzionali ai fini della possibilità di correggere
	 * l'esercizio, ma concorrono comunque al raggiungimento della totalità del
	 * punteggio:
	 * 
	 * - far passare tutti i test (ossia, nella parte obbligatoria si può omettere di considerare
	 * un test)
	 * - la buona progettazione della soluzione, con riuso di codice e senza ripetizioni
	 * 
	 * Si tolga il commento dal metodo initFactory.
	 * 
	 * Indicazioni di punteggio:
	 * - correttezza della parte obbligatoria: 10 punti
	 * - correttezza della parte opzionale: 3 punti (far passare anche l'ultimo test)
	 * - qualità della soluzione: 4 punti (per buon design)
	 */

	private PickerFactory factory;

	@org.junit.Before
	public void init() {
		// this.factory = new PickerFactoryImpl();
	}

	// semplice funzionalità che da uno stream realizza una Source
	private static <X> Source<X> fromStream(Stream<X> stream){
		return stream.iterator()::next;
	}



	@org.junit.Test
	public void pickEven() {
		// test del metodo pick per la factory evenNumbers
		Picker<Integer> splitter = this.factory.evenNumbers();
		var inputSource = fromStream(Stream.iterate(0, i -> i+1)); // 0,1,2,3,...
		var result = splitter.pick(inputSource);
		assertEquals(0, result.next().intValue()); // produce 0
		assertEquals(2, result.next().intValue()); // produce 2
		assertEquals(4, result.next().intValue()); // produce 4
		assertEquals(6, result.next().intValue()); // produce 6
		assertEquals(8, result.next().intValue()); // produce 8
	}

	@org.junit.Test
	public void pickEvenWithSkippedElements() {
		// test del metodo pickWithSkippedElements per la factory evenNumbers
		Picker<Integer> splitter = this.factory.evenNumbers();
		var inputSource = fromStream(Stream.iterate(0, i -> i+1)); // 0,1,2,3,...
		var result = splitter.pickWithSkippedElements(inputSource);
		assertEquals(new Pair<>(0, List.of()), result.next());
		assertEquals(new Pair<>(2, List.of(1)), result.next());
		assertEquals(new Pair<>(4, List.of(1,3)), result.next());
		assertEquals(new Pair<>(6, List.of(1,3,5)), result.next()); // produce il 6, e gli elementi skippati fin qui (1,3,5)
		assertEquals(new Pair<>(8, List.of(1,3,5,7)), result.next());
	}

	@org.junit.Test
	public void pickEvenWithLastSkippedElement() {
		// test del metodo pickWithLastSkippedElement per la factory evenNumbers
		Picker<Integer> splitter = this.factory.evenNumbers();
		var inputSource = fromStream(Stream.iterate(0, i -> i+1)); // 0,1,2,3,...
		var result = splitter.pickWithLastSkippedElement(inputSource);
		assertEquals(new Pair<>(0, Optional.empty()), result.next());
		assertEquals(new Pair<>(2, Optional.of(1)), result.next());
		assertEquals(new Pair<>(4, Optional.of(3)), result.next());
		assertEquals(new Pair<>(6, Optional.of(5)), result.next());// produce il 6, e l'ultimo elemento skippato fin qui (5)
		assertEquals(new Pair<>(8, Optional.of(7)), result.next());
	}

	@org.junit.Test
	public void pickFromSet() {
		// test del metodo pick per la factory fromSet
		Picker<Integer> splitter = this.factory.fromSet(Set.of(1,2,3));
		var inputSource = fromStream(Stream.iterate(0, i -> (i+1) % 6)); //0,1,2,3,4,5,0,1,2,3,4,5,...
		var result = splitter.pick(inputSource);
		assertEquals(1, result.next().intValue());
		assertEquals(2, result.next().intValue());
		assertEquals(3, result.next().intValue());
		assertEquals(1, result.next().intValue());
		assertEquals(2, result.next().intValue());
		assertEquals(3, result.next().intValue());
		assertEquals(1, result.next().intValue());
	}

	@org.junit.Test
	public void filterFromWithSkipped() {
		// test del metodo pickWithSkippedElements per la factory fromSet
		Picker<Integer> splitter = this.factory.fromSet(Set.of(1,2,3));
		var inputSource = fromStream(Stream.iterate(0, i -> (i+1) % 6)); //0,1,2,3,4,5,0,1,2,3,4,5,...
		var result = splitter.pickWithSkippedElements(inputSource);
		assertEquals(new Pair<>(1, List.of(0)), result.next());
		assertEquals(new Pair<>(2, List.of(0)), result.next());
		assertEquals(new Pair<>(3, List.of(0)), result.next());
		assertEquals(new Pair<>(1, List.of(0,4,5,0)), result.next());
		assertEquals(new Pair<>(2, List.of(0,4,5,0)), result.next());
		assertEquals(new Pair<>(3, List.of(0,4,5,0)), result.next());
		assertEquals(new Pair<>(1, List.of(0,4,5,0,4,5,0)), result.next());
	}

	@org.junit.Test
	public void filterFromWithLastSkipped() {
		// test del metodo pickWithLastSkippedElement per la factory fromSet
		Picker<Integer> splitter = this.factory.fromSet(Set.of(1,2,3));
		var inputSource = fromStream(Stream.iterate(0, i -> (i+1) % 6)); //0,1,2,3,4,5,0,1,2,3,4,5,...
		var result = splitter.pickWithLastSkippedElement(inputSource);
		assertEquals(new Pair<>(1, Optional.of(0)), result.next());
		assertEquals(new Pair<>(2, Optional.of(0)), result.next());
		assertEquals(new Pair<>(3, Optional.of(0)), result.next());
		assertEquals(new Pair<>(1, Optional.of(0)), result.next());
		assertEquals(new Pair<>(2, Optional.of(0)), result.next());
		assertEquals(new Pair<>(3, Optional.of(0)), result.next());
		assertEquals(new Pair<>(1, Optional.of(0)), result.next());
	}
}

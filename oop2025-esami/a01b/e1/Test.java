package e01b.e1;

import static org.junit.Assert.*;

import java.util.*;

public class Test {

	/*
	 * Implementare l'interfaccia ExamSystemBuilder come indicato nel metodo
	 * init qui sotto. Realizza un builedr per un concetto di sistema di gestione esami
	 * universitari, catturato dall'interfaccia ExamSystem: essenzialmente tiene traccia di 
	 * appelli (exam), della partecipazione di uno studente ad un appello (enrollment), e 
	 * dell'eventuale voto conseguito (evaluation).
	 * 
	 * Sono considerati opzionali ai fini della possibilità di correggere
	 * l'esercizio, ma concorrono comunque al raggiungimento della totalità del
	 * punteggio:
	 * 
	 * - far passare tutti i test (ossia, nella parte obbligatoria si può omettere di considerare
	 * l'ultimo test, testOverallEvaluationOnFull)
	 * - la buona progettazione della soluzione
	 * 
	 * Si tolga il commento dal metodo initFactory.
	 * 
	 * Indicazioni di punteggio:
	 * - correttezza della parte obbligatoria: 10 punti
	 * - correttezza della parte opzionale: 3 punti (far passare anche l'ultimo test)
	 * - qualità della soluzione: 4 punti (per buon design)
	 */

	private ExamSystemBuilder builder;

	@org.junit.Before
	public void init() {
		//this.builder = new ExamSystemBuilderImpl();
	}

	private ExamSystem basicSystem(){
		// un unico appello (101) con 3 studenti partecipanti, due dei quali conseguono un voto valido
		this.builder.newExam(101);
		this.builder.enrollStudent("mario");
		this.builder.enrollStudent("lucia");
		this.builder.enrollStudent("carla");
		this.builder.exitExam();
		this.builder.addEvaluation(101, "mario", 25);
		this.builder.addEvaluation(101, "carla", 30);
		return this.builder.build();
	}
	
	private ExamSystem alternativeBasicSystem(){
		// formulazione del tutto equivalente (rispetto a basicSystem), solo che sfrutta lo stile
		// fluente delle chiamate di metodo
		return this.builder
				.newExam(101)
						.enrollStudent("mario")
						.enrollStudent("lucia")
						.enrollStudent("carla")
				.exitExam()
				.addEvaluation(101, "mario", 25)
				.addEvaluation(101, "carla", 30)
			.build();
	}
	
	private ExamSystem fullSystem(){
		// usando lo stile fluente, un sistema fatto da 3 appelli:
		// - con studenti che passano al primo esame (mario/carla)
		// - con studenti che non passano l'esame e ritentano (lucia)
		// - con studenti che ritentano anche se hanno passato il precedente (beatrice)
		// - con studenti che alla fine comunque non hanno passato l'esame (sandro)
		// si noti che i voti possono essere inseriti anche "molto dopo" un appello, non prima
		return this.builder
				.newExam(101)
						.enrollStudent("mario")
						.enrollStudent("lucia")
						.enrollStudent("carla")
				.exitExam()
				.addEvaluation(101, "mario", 25)
				.addEvaluation(101, "carla", 30)
				.newExam(201)		
						.enrollStudent("gino")
						.enrollStudent("lucia")
						.enrollStudent("beatrice")
				.exitExam()
				.newExam(301)		
						.enrollStudent("lucia")
						.enrollStudent("beatrice")
						.enrollStudent("sandro")
				.exitExam()
				.addEvaluation(201, "gino", 28)
				.addEvaluation(201, "lucia", 26)
				.addEvaluation(301, "lucia", 28)
				.addEvaluation(301, "beatrice", 30)
			.build();
	}

	@org.junit.Test
	public void testBasic() {
		// verifiche base sul sistema Basic
		var examSystem = basicSystem();
		assertEquals(List.of(101), examSystem.exams());
		assertEquals(Set.of("mario", "lucia", "carla"), examSystem.students());
		assertEquals(Optional.of(25), examSystem.studentEvaluation(101, "mario"));
		assertEquals(Optional.empty(), examSystem.studentEvaluation(101, "lucia"));
		assertEquals(Optional.of(30), examSystem.studentEvaluation(101, "carla"));
		assertEquals(Map.of("mario", Optional.of(25), "lucia", Optional.empty(), "carla", Optional.of(30)), examSystem.examEvaluations(101));
	}

	@org.junit.Test
	public void testAlternativeBasic() {
		// stesse verifiche base sullo stesso sistema ma espresso in stile fluente: è equivalente!
		var examSystem = alternativeBasicSystem();
		assertEquals(List.of(101), examSystem.exams());
		assertEquals(Set.of("mario", "lucia", "carla"), examSystem.students());
		assertEquals(Optional.of(25), examSystem.studentEvaluation(101, "mario"));
		assertEquals(Optional.empty(), examSystem.studentEvaluation(101, "lucia"));
		assertEquals(Optional.of(30), examSystem.studentEvaluation(101, "carla"));
		assertEquals(Map.of("mario", Optional.of(25), "lucia", Optional.empty(), "carla", Optional.of(30)), examSystem.examEvaluations(101));
	}

	@org.junit.Test
	public void testFull() {
		// verifiche base sul sistema full
		var examSystem = fullSystem();
		assertEquals(List.of(101, 201, 301), examSystem.exams());
		assertEquals(Set.of("mario", "lucia", "carla", "gino", "beatrice", "sandro"), examSystem.students());
		assertEquals(Optional.of(25), examSystem.studentEvaluation(101, "mario"));
		assertEquals(Optional.empty(), examSystem.studentEvaluation(101, "lucia"));
		assertEquals(Optional.of(30), examSystem.studentEvaluation(101, "carla"));
		assertEquals(Optional.of(28), examSystem.studentEvaluation(201, "gino"));
		assertEquals(Optional.of(26), examSystem.studentEvaluation(201, "lucia"));
		assertEquals(Optional.empty(), examSystem.studentEvaluation(201, "beatrice"));
		assertEquals(Optional.of(30), examSystem.studentEvaluation(301, "beatrice"));
		assertEquals(Optional.of(28), examSystem.studentEvaluation(301, "lucia"));
		assertEquals(Optional.empty(), examSystem.studentEvaluation(301, "sandro"));
	}

	@org.junit.Test
	public void testOverallEvaluationOnFull() {
		// verifiche method overallBestEvaluations sul sistema full
		var examSystem = fullSystem();
		assertEquals(Map.of("mario", 25, "lucia", 28, "carla", 30, "gino", 28, "beatrice", 30), examSystem.overallBestEvaluations());
	}

}

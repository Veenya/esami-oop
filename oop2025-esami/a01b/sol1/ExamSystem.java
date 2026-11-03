package e01b.sol1;

import java.util.*;

/**
 * This interface models a data structure for tracking evaluations of a university course. There are various exams ("appelli"), 
 * each with an integer ID, attended by a group of students (represented by a String): each student may or may not pass the exam, 
 * if it is passed he/she receives an evaluation (18,19...30)
 */
public interface ExamSystem {

	/**
	 * @return the progressive list of exam (ids), e.g. 101, 102, 103,...
	 */
	List<Integer> exams();

	/**
	 * @return the entire set of students which attended the various exams
	 */
	Set<String> students();

	/**
	 * @param exam
	 * @param student
	 * @return the evaluation (or nothing) that @param student received at @param exam
	 */
	Optional<Integer> studentEvaluation(int exam, String student);

	/**
	 * @param exam
	 * @return a map from students to their evaluations at @param exam
	 */
	Map<String, Optional<Integer>> examEvaluations(int exam);

	/**
	 * A student may participate to various exams, receiving an evalutation at each.
	 * @return a map from students to the highest evaluation they received ever
	 */
	Map<String, Integer> overallBestEvaluations();
		
}

package e01b.e1;

/**
 * This interface models a builder (according to the Builder Pattern) to fill data and construct an ExamSystem.
 * It has a fluent style, namely, each method returning ExamSystemBuilder is assumed to return "this".
 */
public interface ExamSystemBuilder {

    /**
     * Creates a new exam: from now, this is considered the "current exam" (see enrollStudent below)
     * @param id
     * @return this
     */
    ExamSystemBuilder newExam(int id);

    /**
     * Enrolls a student into current exam ("iscrizione all'appello")
     * @param student
     * @return this
     */
    ExamSystemBuilder enrollStudent(String student);
    
    /**
     * Closes enrollments in current exam
     * @return this
     */
    ExamSystemBuilder exitExam();

    /**
     * Adds evaluation for a student into an exam
     * @param examId
     * @param student
     * @param evaluation
     * @return this
     */
    ExamSystemBuilder addEvaluation(int examId, String student, int evaluation);


    /**
     * @return an ExamSystem built with all information provided by the above methods
     */
    ExamSystem build();
   
}

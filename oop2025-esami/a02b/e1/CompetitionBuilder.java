package a02b.e1;

/**
 * This interface models a builder (according to the Builder Pattern) to fill data and construct a Competition.
 * It has a fluent style, namely, each method returning ExamSystemBuilder is assumed to return "this".
 */
public interface CompetitionBuilder {

    /**
     * Initiates a new heat
     * @return the builder itself
     */
    CompetitionBuilder newHeat();

    /**
     * Sets the result of the athlete, for the current heat
     * @param athlete
     * @param result
     * @return
     */
    CompetitionBuilder heatResult(int athlete, double result);

    /**
     * Initiates final match
     * @return the builder itself
     */
    CompetitionBuilder finalMatch();

    /**
     * Sets the result of the athlete, for the final
     * @param athlete
     * @param result
     * @return
     */
    CompetitionBuilder finalResult(int athlete, double result);
    
    /**
     * @return the competition, to be used for statistics
     */
    Competition build();
   
}

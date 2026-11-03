package a02a.e1;

import java.util.Set;

/**
 * This interface models a builder (according to the Builder Pattern) to fill data and construct a Tournament.
 * It has a fluent style, namely, each method returning ExamSystemBuilder is assumed to return "this".
 */
public interface TournamentBuilder {

    /**
     * sets the teams
     * @param teams
     * @return the builder itself
     */
    TournamentBuilder teams(Set<String> teams);

    /**
     * Initiates a new round (no more than N-1 where N is the size of the team)
     * @return the builder itself
     */
    TournamentBuilder newRound();

    /**
     * Adds information of a game
     * @param winner
     * @param loser
     * @return the builder itself
     */
    TournamentBuilder addGame(String winner, String loser);
    
    /**
     * @return the turnament, to be used for statistics
     */
    Tournament build();
   
}

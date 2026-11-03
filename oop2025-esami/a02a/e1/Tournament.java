package a02a.e1;

import java.util.*;

/**
 * This interface models a Tournament (torneo all'italiana a singolo girone), with operations to get statistics of what happened.
 * The tournament could be completed or not. However, tests are made on a complete tournament with N-1 rounds.
 */
public interface Tournament {

	/**
	 * @return the teams in the tournament (we assume it's an even number)
	 */
	Set<String> teams();

	/**
	 * @return the number of rounds (giornate), which should be at most the number of teams - 1
	 */
	int rounds();

	/**
	 * @param round (0 for the first, 1 for the second...)
	 * @return the set of games, each as a pair (winner, loser) -- note there is always a winner
	 */
	Set<Pair<String, String>> roundGames(int round);

	/**
	 * @param team
	 * @return the list of opponents of @param team, one per round, in order
	 */
	List<String> teamSchedule(String team);

	/**
	 * @return a map from teams to their number of wins
	 */
	Map<String, Integer> finalRanking();
}

package a02b.e1;

import java.util.*;

/**
 * This interface models a Competition ("batterie in semifinale, e solo i vincitori di ogni batteria in finale"), with operations to get statistics of what happened.
 */
public interface Competition {

	/**
	 * @return the set of (id of) all athletes
	 */
	Set<Integer> athletes();

	/**
	 * @return number of heats
	 */
	int heats();

	/**
	 * @param heat (0,1,2,...) for the various heats
	 * @return a map from athlete (id) and his/her result (a double representing time to run 100 meters)
	 */
	Map<Integer, Double> heatResults(int heat);

	/**
	 * @return a map from athlete (id) and his/her result at the final (a double representing time to run 100 meters)
	 */
	Map<Integer, Double> finalResults();

	/**
	 * @return a sorted list (from smaller to bigger) of results (running time) at the final
	 */
	List<Double> orderedFinalResults();

	/**
	 * @param athlete
	 * @return the list of athlete results (the heat result, and then optionally the final result)
	 */
	List<Double> athleteResults(int athlete);
}

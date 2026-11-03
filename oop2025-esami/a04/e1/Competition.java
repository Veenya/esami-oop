package a04.e1;

import java.util.*;

/**
 * This interface models a Competition of the following kind:
 * "batterie in semifinale, e solo quelli con risultato entro un minimo (cutoff) si qualificano per la finale"
 * It provides operations to add results, and then at the end to get statistics of what happened.
 */
public interface Competition {

	/**
	 * Sets the cutoff result to access the final
	 */
	void setCutoff(double cutoff);

	/**
	 * Starts a new heat
	 */
	void newHeat();

	/**
	 * Starts the final round
	 */
	void startFinal();

	/**
	 * Adds a result for a specific athlete
	 * @param athlete the athlete's ID
	 * @param result the result (time to run 100 meters)
	 */
	void addResult(int athlete, double result);

	/**
	 * @return the set of (id of) all athletes that participated in the competition with a result
	 */
	Set<Integer> athletes();

	/**
	 * @return number of heats (excluding the final)
	 */
	int heats();

	/**
	 * @param heat (0,1,2,...) for the various heats
	 * @return a map from athlete (id) and his/her result (a double representing time to run 100 meters)
	 */
	Map<Integer, Double> heatResults(int heat);

	/**
	 * @return a map from athlete (id) and his/her result at the final (a double representing time to run 100 meters)
	 * @throws IllegalStateException if the final has not yet started
	 */
	Map<Integer, Double> finalResults();

	/**
	 * @return a sorted list (from smaller to bigger) of results (running time) at the final
	 * @throws IllegalStateException if the final has not yet started
	 */
	List<Double> orderedFinalResults();

	/**
	 * @param athlete
	 * @return the list of athlete results (the heat result, and then optionally the final result)
	 */
	List<Double> athleteResults(int athlete);
}

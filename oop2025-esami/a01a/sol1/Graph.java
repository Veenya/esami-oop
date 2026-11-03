package e01a.sol1;

import java.util.*;
import java.util.function.Function;

/**
 * An interface to model a graph with directed edges, with nodes labelled with elements of type N.
 */
public interface Graph<N> {

	/**
	 * @return the set of nodes of the graph
	 */
	Set<N> nodes();

	/**
	 * @param s
	 * @return the nodes reachable from @param s in one step, namely, following one edge
	 */
	Set<N> reachableInOneStep(N s);

	/**
	 * @return a representation of this graph as a mapping a node to the nodes it can reach in one step
	 */
	Map<N, Set<N>> toMap();

	/**
	 * @param s
	 * @return the nodes reachable from @param s in one or two steps
	 */
	Set<N> reachableInTwoSteps(N s);

	/**
	 * @param s
	 * @return the nodes reachable from @param s in 1,2,3,... steps
	 */
	Set<N> reachable(N s);
	
	/**
	 * adds to this graph a set of edges from @param node to the elements in @param next
	 * @param node
	 * @param next
	 * @return the graph so obtained
	 */
	Graph<N> withEdgesFromNode(N node, Set<N> next);

	/**
	 * adds to this graph all edges obtained by applying @param nextFunction to the nodes of the graph
	 * (see the test)
	 * @param nextFunction
	 * @return the graph so obtained
	 */
	Graph<N> withNextFunction(Function<N, Set<N>> nextFunction);

	/**
	 * adds to this graph all edges obtained by the @param nextRelation set of pairs
	 * (see the test)
	 * @param nextRelation
	 * @return the graph so obtained
	 */
	Graph<N> withNextRelation(Set<Pair<N, N>> nextRelation);
	
}

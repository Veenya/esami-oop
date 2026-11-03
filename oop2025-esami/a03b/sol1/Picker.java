package a03b.sol1;

import java.util.*;
import java.util.function.Supplier;

/**
 * This interface models a an object with some pick methods: each takes elements from a Source 
 * (essentially an infinite iterator, supposed to never be over), and produces another Source
 * of only certain elements produced by the input source (depending on implementation), 
 * optionally giving also information about the elements that were skipped.
 * For instance, in a source of numbers we may pick only the even ones (0,2,4,...).
 */
public interface Picker<X> {

	/**
 	 * Models a source, essentially as a Supplier, or equivalently as an iterator that won't stop.
	 * A Source can be built also with a lambda () -> {...returning the next x...}
 	 */
	@FunctionalInterface
	interface Source<X>{
		X next();
	}

	/**
	 * @param source of elements s1,s2,s3,...
	 * @return creates a new source that only picks certain elements in s1,s2,s3...
	 */
	Source<X> pick(Source<X> source);

	/**
	 * @param source of elements s_i (s_1,s_2,s_3,...)
	 * @return creates a new source that only picks certain elements s_i:
	 * each element in output is a pair of the picked element in s_i, and the elements s_i that were not picked so far
	 */
	Source<Pair<X, List<X>>> pickWithSkippedElements(Source<X> source);

	/**
	 * @param source of elements s_i (s_1,s_2,s_3,...)
	 * @return creates a new source that only picks certain elements s_i:
	 * each element in output is a pair of the picked element in s_i, and the last element s_i that was not picked
	 */
	Source<Pair<X, Optional<X>>> pickWithLastSkippedElement(Source<X> source);

}

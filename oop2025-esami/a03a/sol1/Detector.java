package a03a.sol1;

import java.util.List;
import java.util.Optional;

/**
 * This interface models detection of a subsequence from an iteration of elements
 * provided through method parseNext. The kind of subsequence to detect depends on the
 * specific implementation. It is assumed that parseNext gives empty Optional until
 * a valid subsequence is detected: at that point, parseNext keeps providing the detected
 * subsequence.
 */
public interface Detector<X> {

    /**
     * Resets the objects, so that the next call to @parseNext is like the first one 
     */
    void reset();

    /**
     * @param x, the next element to consider as the input iteration of elements (x1, x2, x3,...)
     * @return empty optional if no valid subsequence has been identified so far, or
     * a list of elements that is a sublist of inputs received so far, and it is valid
     */
    Optional<List<X>> parseNext(X x);
}

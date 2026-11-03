package a03a.sol1;

import java.util.List;
import java.util.function.Predicate;

public interface DetectorFactory {

    /**
     * @param <X>
     * @param list
     * @return a detector that only detects @param list as vaid subsequence
     */
    <X> Detector<X> exactly(List<X> list);

    /**
     * @param <X>
     * @param start
     * @param size
     * @return a detector that only detects a sublist starting with @param start and with length @param size
     */
    <X> Detector<X> byStartAndSize(X start, int size);

    /**
     * @param <X>
     * @param list of elements e1,e2,...,en
     * @param minimumSize
     * @return a detector that only detects a sublist of elements e1,e2,.. with size at least @param minimumSize
     */
    <X> Detector<X> whileFromList(List<X> list, int minimumSize);

    /**
     * @param <X>
     * @param condition
     * @param minimumSize
     * @return a detector that only detects a sublist of elements that all satisfy @param condition and with size at least @param minimumSize
     */
    <X> Detector<X> whileFromCondition(Predicate<X> condition, int minimumSize);

}

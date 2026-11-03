package a03b.sol1;

import java.util.Set;

public interface PickerFactory {

    /**
     * @return a picker of only the even numbers
     */
    Picker<Integer> evenNumbers();

    /**
     * @param <X>
     * @param set
     * @return a picker of only the elements in @param set
     */
    <X> Picker<X> fromSet(Set<X> set);

}

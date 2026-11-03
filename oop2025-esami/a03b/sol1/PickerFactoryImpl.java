package a03b.sol1;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class PickerFactoryImpl implements PickerFactory {

    @Override
    public Picker<Integer> evenNumbers() {
        return byPredicate(x -> x % 2 == 0);
    }

    @Override
    public <X> Picker<X> fromSet(Set<X> set) {
        return byPredicate(set::contains);
    }

    private <X> Picker<X> byPredicate(Predicate<X> predicate) {
        return new Picker<X>() {

            @Override
            public Source<Pair<X, List<X>>> pickWithSkippedElements(Source<X> source) {
                return general(source, (x, l) -> {l.add(x);}, (x, l) -> new Pair<>(x, l));
            }

            @Override
            public Source<X> pick(Source<X> source) {
                return general(source, (x, l) -> {}, (x, l) -> x);
            }

            @Override
            public Source<Pair<X, Optional<X>>> pickWithLastSkippedElement(Source<X> source) {
                return general(source, 
                    (x, l) -> { l.clear(); l.add(x); }, 
                    (x, l) -> new Pair<>(x, Optional.of(l).filter(list -> !list.isEmpty()).map(list -> list.get(0))));
            }

            private <Y> Source<Y> general(Source<X> source, BiConsumer<X, List<X>> consumer, BiFunction<X, List<X>, Y> mapper) {
                List<X> buffer = new LinkedList<>();
                return () -> {
                    X x;
                    while (!predicate.test(x = source.next())){
                        consumer.accept(x, buffer);
                    }
                    return mapper.apply(x, buffer);
                };
            }
        };
    }

}

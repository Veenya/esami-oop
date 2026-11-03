package a03a.sol1;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DetectorFactoryImpl implements DetectorFactory {

    @Override
    public <X> Detector<X> exactly(List<X> list) {
        return this.byValidity(
			l -> l.size() <= list.size() && l.equals(list.subList(0, l.size())),
			l -> l.equals(list)
		);
    }

    @Override
    public <X> Detector<X> byStartAndSize(X start, int size) {
        return this.byValidity(
			l -> l.size() <= size && l.get(0).equals(start),
			l -> l.size() == size
		);
    }

    @Override
    public <X> Detector<X> whileFromList(List<X> list, int minimumSize) {
        return this.byValidity(
			l -> l.size() <= list.size() && l.equals(list.subList(0, l.size())),
			l -> l.size() <= list.size() && l.size() >= minimumSize
		);
    }

    @Override
    public <X> Detector<X> whileFromCondition(Predicate<X> condition, int minimumSize) {
        return this.byValidity(
			l -> l.size() >=1 && l.stream().allMatch(condition),
			l -> l.size() >= minimumSize
		);
    }

    private <X> Detector<X> byValidity(Predicate<List<X>> keep, Predicate<List<X>> valid) {
        return new Detector<X>() {

            private final List<X> temp = new LinkedList<>();
            private boolean over = false;

            @Override
            public void reset() {
                this.over = false;
                this.temp.clear();
            }

            @Override
            public Optional<List<X>> parseNext(X x) {
                if (over){
                    return Optional.of(temp).filter(valid);
                }
                var newTemp = new LinkedList<>(temp);
                newTemp.add(x);
                if (keep.test(newTemp)){
                    temp.add(x);
                } else {
                    if (!valid.test(temp)){
                        temp.clear();
                        temp.add(x);
                        if (!keep.test(temp)){
                            temp.clear();
                        }
                    } else {
                        over = true;
                    }
                }
                return Optional.of(temp).filter(l -> valid.test(temp));
            }
        };
    }
}

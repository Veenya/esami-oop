package e01a.sol1;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GraphFactoryImpl implements GraphFactory {

    private static record GraphImpl<N>(Map<N, Set<N>> map) implements Graph<N> {

        @Override
        public Set<N> nodes() {
            return Collections.unmodifiableSet(map.keySet());
        }

        @Override
        public Set<N> reachableInOneStep(N n) {
            return Collections.unmodifiableSet(map.get(n));
        }

        @Override
        public Set<N> reachableInTwoSteps(N n) {
            Set<N> outSet = new HashSet<>();
            this.reachableInOneStep(n).forEach(n2 -> outSet.addAll(this.reachableInOneStep(n2)));
            return outSet;
        }

        @Override
        public Set<N> reachable(N start) {
            return this.reachable(Collections.singleton(start));
        }

        private Set<N> reachable(Set<N> from) {
            var nextSet = from.stream()
                    .flatMap(n -> this.reachableInOneStep(n).stream())
                    .collect(Collectors.toSet());
            return from.size() == nextSet.size() ? from : reachable(nextSet);        
        }

        @Override
        public Map<N, Set<N>> toMap() {
            return Collections.unmodifiableMap(map);
        }

        @Override
        public Graph<N> withNextFunction(Function<N, Set<N>> nextFunction) {
            var newMap = new HashMap<>(map);
            map.keySet().forEach(n -> newMap.merge(n, nextFunction.apply(n), GraphImpl::joinSets));
            return new GraphImpl<>(newMap);
        }

        @Override
        public Graph<N> withNextRelation(Set<Pair<N, N>> nextRelation) {
            return this.withNextFunction(n -> nextRelation.stream().filter(p -> p.get1().equals(n)).map(p -> p.get2()).collect(Collectors.toSet()));
        }

        private static <N> Set<N> joinSets(Set<N> s1, Set<N> s2){
            Set<N> sout = new HashSet<>(s1);
            sout.addAll(s2);
            return sout;
        }

        @Override
        public Graph<N> withEdgesFromNode(N state, Set<N> next) {
            var newMap = new HashMap<>(map);
            newMap.merge(state, next, GraphImpl::joinSets);
            return new GraphImpl<>(newMap);
        }
    }

    @Override
    public <N> Graph<N> emptyGraph(Set<N> nodes){
        return new GraphImpl<>(nodes.stream().collect(Collectors.toMap(n -> n, n -> Collections.emptySet())));
    }

}

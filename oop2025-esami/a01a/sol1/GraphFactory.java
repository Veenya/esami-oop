package e01a.sol1;

import java.util.Set;

public interface GraphFactory {

    <N> Graph<N> emptyGraph(Set<N> nodes);
}

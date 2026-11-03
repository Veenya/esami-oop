//package e01a.e1; //NOTA: Riga tolta perche' dava errore
package a01a.e1;

import java.util.Set;

public interface GraphFactory {

    <N> Graph<N> emptyGraph(Set<N> nodes);
}

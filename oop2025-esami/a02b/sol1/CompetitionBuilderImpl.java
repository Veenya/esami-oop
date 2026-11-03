package a02b.sol1;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompetitionBuilderImpl implements CompetitionBuilder {

    private List<List<Pair<Integer, Double>>> heats = new LinkedList<>(); 
    private boolean finalDone = false;
    
    @Override
    public CompetitionBuilder newHeat() {
        this.heats.add(new LinkedList<>());
        return this;
    }

    @Override
    public CompetitionBuilder heatResult(int athlete, double result) {
        if (this.heats.isEmpty()){
            throw new IllegalStateException();
        }
        if (!finalDone && this.heats.stream().anyMatch(l -> l.stream().anyMatch(p -> p.get1() == athlete))){
            throw new IllegalStateException();
        }
        if (finalDone && !this.heats.stream().limit(this.heats.size()-1).anyMatch(l -> l.stream().anyMatch(p -> p.get1() == athlete))){
            throw new IllegalStateException();
        }
        this.heats.getLast().add(new Pair<>(athlete, result));
        return this;
    }

    @Override
    public CompetitionBuilder finalMatch() {
        this.finalDone = true;
        this.heats.add(new LinkedList<>());
        return this;
    }

    private Set<Integer> finalists(){
        return this.heats.stream()
                .limit(this.heats.size() - 1)
                .flatMap(l -> 
                    l.stream()
                            .sorted((p1, p2) -> p1.get2().compareTo(p2.get2()))
                            .limit(1)
                            .map(p -> p.get1())).collect(Collectors.toSet());
    }

    @Override
    public CompetitionBuilder finalResult(int athlete, double result) {
        System.out.println(finalists());
        if (!finalists().contains(athlete)){
            throw new IllegalStateException();
        }
        this.heatResult(athlete, result);
        return this;
    }

    @Override
    public Competition build() {
        if (!finalDone){
            throw new IllegalStateException();
        }
        return new Competition() {

            @Override
            public Set<Integer> athletes() {
                return heats.stream().flatMap(l -> l.stream()).map(p -> p.get1()).collect(Collectors.toSet());
            }

            @Override
            public int heats() {
                return heats.size()-1;
            }

            @Override
            public Map<Integer, Double> heatResults(int heat) {
                return heats.get(heat).stream().collect(Collectors.toMap(p -> p.get1(), p -> p.get2()));
            }

            @Override
            public Map<Integer, Double> finalResults() {
                return heats.getLast().stream().collect(Collectors.toMap(p -> p.get1(), p -> p.get2()));
            }

            @Override
            public List<Double> orderedFinalResults() {
                return this.finalResults().entrySet().stream().map(p -> p.getValue()).sorted().toList();
            }

            @Override
            public List<Double> athleteResults(int athlete) {
                return IntStream.rangeClosed(0, heats()).mapToObj(i -> heatResults(i)).filter(m -> m.containsKey(athlete)).map(m -> m.get(athlete)).toList();
            }           
        };
    }

}

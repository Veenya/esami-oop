package a04.sol1;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompetitionImpl implements Competition {

    private List<List<Pair<Integer, Double>>> heats = new LinkedList<>();
    private boolean finalDone = false;
    private double cutoff;

    @Override
    public void setCutoff(double cutoff) {
        this.cutoff = cutoff;
    }

    @Override
    public void newHeat() {
        this.heats.add(new LinkedList<>());
    }

    @Override
    public void addResult(int athlete, double result) {
        if (this.heats.isEmpty()) {
            throw new IllegalStateException();
        }
        if (!finalDone && this.heats.stream().anyMatch(l -> l.stream().anyMatch(p -> p.get1() == athlete))) {
            throw new IllegalStateException();
        }
        if (finalDone && !this.heats.stream().limit(this.heats.size() - 1)
                .anyMatch(l -> l.stream().anyMatch(p -> p.get1() == athlete))) {
            throw new IllegalStateException();
        }
        if (finalDone && !finalists().contains(athlete)) {
            throw new IllegalStateException();
        }
        this.heats.getLast().add(new Pair<>(athlete, result));
    }

    @Override
    public void startFinal() {
        this.finalDone = true;
        this.heats.add(new LinkedList<>());
    }

    private Set<Integer> finalists() {
        return this.heats.stream()
                .flatMap(l -> l.stream())
                .filter(p -> p.get2() <= this.cutoff)
                .map(p -> p.get1())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Integer> athletes() {
        return heats.stream().flatMap(l -> l.stream()).map(p -> p.get1()).collect(Collectors.toSet());
    }

    @Override
    public int heats() {
        return heats.size() - 1;
    }

    @Override
    public Map<Integer, Double> heatResults(int heat) {
        return heats.get(heat).stream().collect(Collectors.toMap(p -> p.get1(), p -> p.get2()));
    }

    @Override
    public Map<Integer, Double> finalResults() {
        if (!finalDone) {
            throw new IllegalStateException();
        }
        return heats.getLast().stream().collect(Collectors.toMap(p -> p.get1(), p -> p.get2()));
    }

    @Override
    public List<Double> orderedFinalResults() {
        return this.finalResults().entrySet().stream().map(p ->
                p.getValue()).sorted().toList();
    }

    @Override
    public List<Double> athleteResults(int athlete) {
        return IntStream.rangeClosed(0, heats()).mapToObj(i ->
                heatResults(i)).filter(m -> m.containsKey(athlete)).map(m ->
                m.get(athlete)).toList();
    }

}

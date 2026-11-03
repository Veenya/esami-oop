package a02a.sol1;

import java.util.*;
import java.util.stream.Collectors;

public class TournamentBuilderImpl implements TournamentBuilder {

    private static Set<String> asSet(Pair<String, String> p){
        return Set.of(p.get1(), p.get2());
    }

    private final LinkedList<Set<Pair<String, String>>> games = new LinkedList<>();
    private Set<String> teams = new HashSet<>();

    @Override
    public TournamentBuilder teams(Set<String> teams) {
        this.teams = teams;
        return this;
    }

    @Override
    public TournamentBuilder newRound() {
        if (!this.games.isEmpty() && this.games.getLast().size() != this.teams.size()/2){
            throw new IllegalStateException();
        }
        this.games.addLast(new HashSet<>());
        return this;
    }

    @Override
    public TournamentBuilder addGame(String winner, String loser) {
        if (this.games.isEmpty()){
            throw new IllegalStateException();
        }
        if (!this.teams.containsAll(Set.of(winner, loser))){
            throw new IllegalArgumentException();
        }
        if (this.games.getLast().stream().anyMatch(g -> asSet(g).contains(winner) || asSet(g).contains(loser))){
            throw new IllegalStateException();
        }
        var game = new Pair<>(winner, loser);
        if (this.games.stream().flatMap(s -> s.stream()).anyMatch(g -> g.equals(game))){
            throw new IllegalStateException();
        }
        this.games.getLast().add(game);
        return this;
    }

    @Override
    public Tournament build() {
        return new Tournament() {

            @Override
            public Set<String> teams() {
                return teams;
            }

            @Override
            public int rounds() {
                return games.size();
            }

            @Override
            public Set<Pair<String, String>> roundGames(int round) {
                return games.get(round);
            }

            private Set<Pair<String, String>> allGames() {
                return games.stream().flatMap(s -> s.stream()).collect(Collectors.toSet());
            }

            private String opponent(Set<String> teams, String team){
                return teams.stream().filter(t -> !t.equals(team)).findAny().get();    
            }

            @Override
            public List<String> teamSchedule(String team) {
                return games.stream().map(s -> s.stream()
                        .map(g -> asSet(g))
                        .filter(tt -> tt.contains(team))
                        .map(tt -> opponent(tt, team)).findAny().get()).toList();
            }

            @Override
            public Map<String, Integer> finalRanking() {
                var map = new HashMap<>(allGames().stream().map(g -> g.get1()).collect(Collectors.toMap(t->t, t->1, (a,b) -> a+b)));
                this.teams().forEach(t -> {
                    if (!map.containsKey(t)){
                        map.put(t, 0);
                    }});    
                return map;    
            }
            
        };
    }

}

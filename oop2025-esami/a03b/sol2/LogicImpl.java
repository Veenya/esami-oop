package a03b.sol2;

import java.util.LinkedList;

public class LogicImpl implements Logic {
    private final LinkedList<Position> selected = new LinkedList<>(); //Posizioni cliccate finora
    
    

    private Position max(){
        return new Position(
                this.selected.stream().map(Position::x).max(Integer::compareTo).get(),
                this.selected.stream().map(Position::y).max(Integer::compareTo).get());
    }

    private Position min(){
        return new Position(
                    this.selected.stream().map(Position::x).min(Integer::compareTo).get(),
                    this.selected.stream().map(Position::y).min(Integer::compareTo).get());
    }

    private boolean valid(){
        return max().x() - min().x() <= 2 && max().y() - min().y() <= 2;
    }

    @Override
    public boolean hit(Position position) {
        this.selected.add(position);
        if (!valid()){
            this.selected.clear();
            return false;
        }  
        return true;     
    }

    @Override
    public boolean isOver() {
        return this.selected.size() == 4;
    }

    @Override
    public boolean isHidden(Position position) {
        Position max = max();
        Position min = min();
        return isOver() && position.x() >= min.x() && position.x() <= max.x() && position.y() >= min.y() && position.y() <= max.y();
    }

}

package a04.sol_mia;
import java.util.ArrayList;
import java.util.List;

public class LogicImpl implements Logic {

    public LogicImpl() {
        createDisabled();
    }
    
    private static final List<Position> PATTERN = List.of(
        new Position(-1, -1),
        new Position(-1, 0),
        new Position(-1, 1),
        new Position(0, 1),
        new Position(1, 1),
        new Position(1, 0),
        new Position(1, -1),
        new Position(0, -1)
    );

    private static final List<Position> CENTERS = List.of(
        new Position(1, 1),
        new Position(5, 5),
        new Position(9, 9)
    );


    private final List<Position> DISABLED = new ArrayList<>();


    private void createDisabled() {
        for (Position center : CENTERS) {
            for (Position offset : PATTERN) {
                Position cell = new Position(center.x() + offset.x(), center.y() + offset.y());
                System.out.println("CELL :"+cell);
                DISABLED.add(cell);
            }
        }

    }

    @Override
    public boolean disableCondition(Position position, int size) {
        if (DISABLED.contains(position)){
            System.out.println("POS :"+position);
            return true;
        }
        return false;
    }

    @Override
    public boolean isStar(Position position){
        if (position.x()==0 && position.y()==0) {
            return true;
        } else if (position.x()==4 && position.y()==4) {
            return true;
        } else if (position.x()==8 && position.y()==8) {
            return true;
        }

        return false;
    }

    @Override
    public boolean hit(Position position) {

        if (isStar(position)) {
            //...
        }
        
        return false;
    }

    @Override
    public boolean isOver() {
        return false;
    }


}

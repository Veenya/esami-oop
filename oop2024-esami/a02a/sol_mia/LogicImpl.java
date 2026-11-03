package a02a.sol_mia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JButton;

public class LogicImpl implements Logic{

    private Map<Position, String> cells = new HashMap<>(); // Coppia posizione, stringa (es. [0, 0], "*")
    private Position starPosition = new Position(0, 0);
    private LinkedList<Position> obastaclesPos = new LinkedList<>();
    private Position currePosition = starPosition;
    public boolean isOver = false;
    private int counter = 4;
    public int size;

    public LogicImpl(int size) {
        this.size = size;
        init();
    }

    @Override
    public boolean hit(Position position) {

        int os = calculateOffset();
        System.out.println(os);
        for (int i=0; i<os; i++) {
            
            move(currePosition);

            if (collision()) {
                //this.isOver = true;

                // restart
                currePosition = this.starPosition;
                
            }
        }

        return true;
    }

    @Override
    public boolean isOVer() {
        return this.isOver;
    }

    public void move(Position currrentPosition) {
        
        int movementX = 0;
        int movementY = 0;
        

        //System.out.println(this.size-1);

        if (currrentPosition.x() < size-1 && currrentPosition.y() == 0) {
            // aggiungi x
            movementX ++;
            
        } 

        else if (currrentPosition.x() == size-1 && currrentPosition.y() < size-1) {
            // aggiungi y
            movementY++;
        }

        else if (currrentPosition.x() > 0 && currrentPosition.y() == size-1) {
            // togli x
            movementX--;
        }

        else if (currrentPosition.x()==0 && currrentPosition.y()>0 ) {
            // togli y
            movementY--;
        } else {
            movementX++;
            movementY++;
            //System.out.println("err");
        }

        Position newCurrentPosition = new Position(currrentPosition.x()+movementX, currrentPosition.y()+movementY);

        cells.put(newCurrentPosition, "X");
        cells.remove(currrentPosition);
        this.currePosition = newCurrentPosition;     

    }

    public int calculateOffset() {
        if (this.counter == 3) {
            this.counter = 4;
            return 4;
        } else {
            this.counter = 3;
            return 3;
        }
    }

    public void init() {
        cells.put(starPosition, "X");
        //todo aggiungere ostacoli
        placeObstacles();
    }

    @Override
    public boolean hasValue(Position position) {
        return cells.containsKey(position);
    }

    @Override
    public String getValue(Position position) {
        return cells.get(position);
    }

    @Override
    public boolean isBorder(Position position) {
        if (position.x() == 0 || position.y() ==0 || position.x() == (this.size-1) || position.y() == (this.size-1)) {
            return true;
        }
        return false;
    }

    public void placeObstacles() {
        //todo fallo randm
        cells.put(new Position(3, 0), "O");
        cells.put(new Position(4, 0), "O");
        cells.put(new Position(2, 0), "O");

        obastaclesPos.add(new Position(3, 0));
        obastaclesPos.add(new Position(4, 0));
        obastaclesPos.add(new Position(2, 0));
    }

    public boolean collision() {
        if (obastaclesPos.contains(this.currePosition)) {
            System.out.println("Collisione");
            return true;
            
        } else {
            return false;
        }
    }
}

package a05.sol_mia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

public class LogicImpl implements Logic {

    private final Map<Position, String> cells = new HashMap<>();
    //private final ArrayList<int> idk = new ArrayList<int>();
    private boolean isOver = false;
 
    @Override
    public boolean hit(Position position) {

        if (checkCollision(position)) {
            //System.out.println("COllisione!");
            System.out.println(isNext(position));
            if (!(isNext(position).isEmpty())) {
                //TODO cancella la croce vecchia
                deleteStar(isNext(position));

                // disegna quella nuova
                drawStar(position);
            }
        } else {
            
            if (isNext(position).isEmpty()) {
                this.isOver = true;   
            } else {
                drawStar(position);
            }
        }
        return true;
    }

    @Override
    public boolean isOver() {
        return this.isOver;
    }

    @Override
    public String getValue(Position position) {
        return cells.get(position);
    }

    private void setValue(Position pos) {
        cells.put(pos, "*");
    }

    // 0, 1, 2, 3, -1
    private ArrayList<Integer> isNext(Position pos) {
        ArrayList<Integer> res = new ArrayList<>();

        if (getValue(new Position(pos.x(), pos.y()+2))== "*") {
            res.add(0); //TOP
        }
        else if (getValue(new Position(pos.x()+2, pos.y()))== "*") {
            res.add(1);; // RIGHT
        }
        else if (getValue(new Position(pos.x(), pos.y()-2))== "*") {
            res.add(2); //BOTTOM
        }
        else if (getValue(new Position(pos.x()-2, pos.y()))== "*") {
            res.add(3); //LEFT
        } //else {
            //res.add(-1);;
        //}

        return res;

    }

    private void drawStar(Position pos) {
        cells.put(pos, "*");
        cells.put(new Position(pos.x(), pos.y()+1), "*");
        cells.put(new Position(pos.x()+1, pos.y()), "*");
        cells.put(new Position(pos.x(), pos.y()-1), "*");
        cells.put(new Position(pos.x()-1, pos.y()), "*");
    }

    private void deleteStar(ArrayList<Integer> arr) {
        // data una posizione, cancella la stella
        if (arr.contains(1)) {
            // cancella
        }
        //ecc
    }

    private boolean checkCollision(Position pos) {
        if (getValue(pos) != "*" && 
            getValue(new Position(pos.x(), pos.y()+1))!= "*" &&
            getValue(new Position(pos.x()+1, pos.y()))!= "*" &&
            getValue(new Position(pos.x(), pos.y()-1))!= "*" &&
            getValue(new Position(pos.x()-1, pos.y()))!= "*"
            ) {
            return false;
        } else {
            return true;
        }
    }
}

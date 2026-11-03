package a02b.sol_mia;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private final Map<JButton, Position> cells = new HashMap<>();
    public Logic logic;
    public int size;
    
    public GUI(int size) {
        this.size = size;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * size, 70 * size);
        this.logic = new LogicImpl();
    
        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            //jb.setText(cells.get(jb).x()+" "+cells.get(jb).y());

            if (this.logic.isOver()) {
                reset();
                this.logic.reset();
            }


            if (this.logic.hit(cells.get(jb))) {
                jb.setText("*");
            }

            if (this.logic.isOver()) {
                showResults();
            }
            
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton();
                this.cells.put(jb, new Position(j, i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        init();
        this.setVisible(true);
    }

    public void init() {
        cells.forEach((jb, pos) -> {
            // if in triangle, disable
            if (this.logic.isMap(pos)) {
                jb.setEnabled(false);
            }
        });
    }

    public void showResults() {
        cells.forEach((jb, pos) -> {
            // prendi i cosi, e mostrali
            // se cell e' in columns, mettine il ris
            if (this.logic.isPresent(pos)) {
                jb.setText(String.valueOf(this.logic.getValue(pos)));
            }
        });
    }

    public void reset() {
        cells.forEach((jb, pos) -> {
            jb.setText(" ");
        });
    }
}

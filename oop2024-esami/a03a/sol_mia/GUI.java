package a03a.sol_mia;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private final Map<JButton, Position> cells = new HashMap<>();
    private Logic logic;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * size, 70 * size);
        this.logic = new LogicImpl();
    
        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            //jb.setText(cells.get(jb).x()+" "+cells.get(jb).y());

            if (this.logic.isOver()) {
                //draw();
                //this.logic.reset();
            }

            if (this.logic.hit(cells.get(jb))) {
                draw();
            }

            if (this.logic.isOver()) {
                //draw();
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
        draw();
        this.setVisible(true);
    }

    public void draw() {
        cells.forEach((jb, pos) -> {
            if (this.logic.hasValue(pos)) {
                System.out.println("IN LOOP");
                jb.setText(this.logic.getValue(pos));
            } else {
                jb.setText("");
            }
        });
    }
}

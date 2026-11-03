package a04.sol_mia;

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
            jb.setText(cells.get(jb).x()+" "+cells.get(jb).y());
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton();
                var pos = new Position(j, i);
                this.cells.put(jb, pos);
                //jb.setText(cells.get(jb).x()+" "+cells.get(jb).y()); //TODO removve
                jb.setText(" ");

                if (this.logic.isOver()) {
                    System.exit(0);
                }

                if (this.logic.disableCondition(pos, size)) {
                    jb.setEnabled(false);
                }

                if (this.logic.hit(pos)) {
                    redraw();
                }

                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    public void redraw() {
        
    }
}

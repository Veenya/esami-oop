package a02a.sol_mia;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private final Map<JButton, Position> cells = new HashMap<>();
    private Logic logic;
    public int size;
    
    public GUI(int size) {
        this.size = size;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * size, 70 * size);
        
        this.logic = new LogicImpl(this.size);
    
        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();

            //jb.setText(cells.get(jb).x()+" "+cells.get(jb).y());

            if (this.logic.isOVer()) {
                System.exit(0);
            }

            if (this.logic.hit(cells.get(jb))) {

                redraw();
            }

        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton();
                this.cells.put(jb, new Position(j, i));
                jb.addActionListener(al);
                panel.add(jb);

                createBorder(size);
                redraw();
            }
        }
        this.setVisible(true);
    }

    public void redraw() {
        cells.forEach((jb, pos) -> {
            if (this.logic.hasValue(pos)) {
                jb.setText(this.logic.getValue(pos));
            } else {
                jb.setText(" ");
            }
        });
    }

    public void createBorder(int size) {
        cells.forEach((jb, pos) -> {
            if (this.logic.isBorder(pos)) {
                jb.setEnabled(false);
            }
        });
    }
}

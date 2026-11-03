package a06.sol_mia;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private final Map<JButton, Position> cells = new HashMap<>();
    private final Logic logic;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * size, 70 * size);
        this.logic = new LogicImpl(size);
    
        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            var pos = this.cells.get(jb);
            this.logic.hit(pos);
            updateVal(jb, null);
            System.out.println("Val "+pos);

            if (this.logic.solved()) {
                System.exit(0);
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                
                final JButton jb = new JButton(" ");
                //this.cells.put(jb, new Position(j, i));
                //this.cells.put(jb, val);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    private void updateVal(JButton jb, Position pos) {
        ///jb.setText("0");
        //this.logic.addValue(1);
        if (this.logic.hasValue(pos)) {
            jb.setText(""+this.logic.getValue(pos));
        }
    }
}

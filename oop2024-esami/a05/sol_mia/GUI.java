package a05.sol_mia;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * In questa classe NON ci deve essere nessuna decisione,
 * solo applicare quello che la logica ha deciso.
 */
public class GUI extends JFrame {

    /** Lista (HashMap) di celle che linka JButton e Position */
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
            
            // Cosa succede ad ogni click?
            if (this.logic.hit(this.cells.get(jb))) {
                this.redraw();
            }

            if (this.logic.isOver()) {
                System.exit(0);
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
        this.setVisible(true);
    }

    public void redraw() {
        // Leggi dalla logica come ridisegnare
        cells.forEach((jb, pos) -> {
            String str = this.logic.getValue(pos);
            //System.out.println(str);
            jb.setText(str);
        });
    }
}

package a04.sol2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private final Map<JButton, Position> cells = new HashMap<>();
    private final Logic logic;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        this.logic = new LogicImpl();
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            var jb = (JButton)e.getSource();
            if (!this.logic.hit(this.cells.get(jb))){
                System.exit(0);
            }
            this.updateCells();
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	var pos = new Position(j,i);
                final JButton jb = new JButton(" ");
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.updateCells();
        this.setVisible(true);
    }

    private void updateCells() {
        cells.forEach((b, pos) -> {
            var io = this.logic.indexOf(pos);
            if (io.isEmpty()){
                b.setText(" ");
                b.setEnabled(true);
            } else {
                b.setText(""+io.get());
                b.setEnabled(false);
            }
        });
    }
    
}

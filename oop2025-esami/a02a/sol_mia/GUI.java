package a02a.sol_mia;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Position> cells = new HashMap<>();
    private Logic logic;
    private int size;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        this.logic = new LogicImpl();
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            var jb = (JButton)e.getSource();
        	//jb.setText(String.valueOf(cells.indexOf(jb)));

            if (this.logic.isOver()) {
                this.disableCells();
            }

            if (this.logic.hit(this.cells.get(jb))) {

                jb.setText(String.valueOf(this.logic.counter()));
            } else {
                this.redrawCells();
            }
            //this.logic.isOver();
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	var pos = new Position(j,i);
                //final JButton jb = new JButton(pos.toString());
                final JButton jb = new JButton(" ");
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    public void disableCells() {
        cells.forEach((b, pos) -> {
            b.setEnabled(false);
        });
    }

    public void redrawCells() {
        cells.forEach((b, pos) -> {
            // todo
            b.setText(" ");
        });
    }
    
}

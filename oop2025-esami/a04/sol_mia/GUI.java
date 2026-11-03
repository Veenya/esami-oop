package a04.sol_mia;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Position> cells = new HashMap<>();
    private Logic logic;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        this.logic = new LogicImpl();
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            var jb = (JButton)e.getSource();
        	//jb.setText(" ");
            //jb.setText(String.valueOf(cells.indexOf(jb)));

            System.out.println(cells.values());
            redraw();
            if (this.logic.isOver()) {
                //reset(this.cells.get(jb));
            }

            if (this.logic.hit(this.cells.get(jb))) {
                //jb.setText("*");
                redraw();
                
            }

        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	//var pos = new Pair<>(j,i);
                var pos = new Position(i, j);
                //final JButton jb = new JButton(pos.toString());
                final JButton jb = new JButton();
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    public void redraw() {
        cells.forEach((jb, pos) -> {
            if (this.logic.hasValue(pos)) {
                jb.setText(String.valueOf(this.logic.getValue(pos)));
                jb.setEnabled(false);
            } else {
                jb.setText(" ");
                jb.setEnabled(true);
            }
        });
    }

    public void reset(Position position) {
        // svuota tutto ...
        //this.logic.initValue(position);
        //this.cells.clear();
        //this.logic.clear();
    }
}

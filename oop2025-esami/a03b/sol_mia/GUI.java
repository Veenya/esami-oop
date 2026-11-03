package a03b.sol_mia;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    //private static final long serialVersionUID = -6218820567019985015L;
    //private final List<JButton> cells = new ArrayList<>();
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
        	
            if (this.logic.isOver()) {
                return;
            }

            if (this.logic.hit(this.cells.get(jb))) {
                jb.setText("*");
            }

            if (this.logic.isOver()) {
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	//var pos = new Pair<>(j,i);
                var pos = new Position(i, j);
                final JButton jb = new JButton(" ");
                //this.cells.add(jb);
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    
}

package a03a.sol2;

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
            if (this.logic.isOver()){
                return;
            }
            if (this.logic.hit(this.cells.get(jb))){
                jb.setText("*");
            } else {
                cells.forEach((b, pos) -> {
                    b.setText("");
                });
            }
            if (this.logic.isOver()){
                for (var entry: this.cells.entrySet()){
                    if (logic.isHidden(entry.getValue())){
                        entry.getKey().setEnabled(false);
                    }
                }
            }
        	
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
        this.setVisible(true);
    }
    
}

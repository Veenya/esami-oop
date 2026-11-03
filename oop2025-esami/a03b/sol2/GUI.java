package a03b.sol2;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private final Map<JButton, Position> cells = new HashMap<>(); //Cambiato da List<Button>
    private final Logic logic; //Aggiunto logic
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        this.logic = new LogicImpl(); //Crea logic
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            var jb = (JButton)e.getSource();
            // jb.setText(String.valueOf(cells.indexOf(jb))); //TOLTO
            if (this.logic.isOver()){ //game over logic
                return;
            }
            /*
            Recupera la Position del bottone cliccato dalla mappa,
            e la passa a logic.hit().
            Se ritorna true scrive * sul bottone cliccato
            se ritorna false ripulisce tutti i bottoni con ""
            */
            if (this.logic.hit(this.cells.get(jb))){
                jb.setText("*");
            } else {
                cells.forEach((b, pos) -> {
                    b.setText("");
                });
            }
            /*
            Dopo l'hit, se il gioco ora e' finito,
            scorre tutte le entry della mappa e disabilita tutti i bottoni
            la cui posizione e' dentro il rettangolo
            */
            if (this.logic.isOver()){
                for (var entry: this.cells.entrySet()){
                    if (logic.isHidden(entry.getValue())){
                        entry.getKey().setEnabled(false);
                    }
                }
            }
        	
        };
        /*
        Stesso doppio ciclo di e2.GUI, ma: Position al posto di Pair, cells.put(jb, pos) al posto di cells.add(jb),
        e il testo iniziale del bottone è uno spazio " " invece delle coordinate (pos.toString()) 
        
        nella soluzione finale le celle partono vuote, non mostrano la loro posizione.
        */
                
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

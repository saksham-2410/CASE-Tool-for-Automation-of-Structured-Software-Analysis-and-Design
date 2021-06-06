/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgcase.tool;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author Aseem
 */
public class Toolbar extends JPanel
                         implements ActionListener {
 
    public Toolbar() {
        super(new BorderLayout());

        JToolBar toolBar = new JToolBar("Toolbar new");
        addButtons(toolBar);

        setPreferredSize(new Dimension(50, 130));
        add(toolBar, BorderLayout.PAGE_START);
        //add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    protected void addButtons(JToolBar toolBar) {
    JButton button;

    //first button
    button = new JButton("Create Entity");
    button.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            Global.mode = 1;     
            System.out.println("mode 1");
        }
    });
    toolBar.add(button);

    //second button
    button = new JButton("Create Bubble");    
    button.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Global.mode = 2;     
            System.out.println("mode 2");
        }
    });
    toolBar.add(button);
    
    //third button
    button = new JButton("Create Data Store");    
    button.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Global.mode = 3;     
            System.out.println("mode 3");
        }
    });
    toolBar.add(button);
    
    //third button
    button = new JButton("Create Data Flow Arrow");    
    button.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Global.mode = 4;     
            System.out.println("mode 4");
        }
    });
    toolBar.add(button);
}
}

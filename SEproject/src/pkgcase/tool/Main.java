/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgcase.tool;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import javax.sound.sampled.Line;
import javax.swing.*;

/**
 *
 * @author Aseem
 */
public class Main {
    public static void main(String args[]) {
        /*
        final JFrame f = new JFrame("Testing");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600,600);
        f.setLocation(100,100);
        
        final dfd testdfd = new dfd("new");
        
        GraphicDragController gdadc = new GraphicDragController(testdfd);

     
        
        
        // Set up a panel for the buttons
        JPanel btnPanel = new JPanel(new FlowLayout());
        
        
        JButton drawEntity = new JButton("Draw Entity ");
        btnPanel.add(drawEntity);
        drawEntity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                Global.mode = 0;
                testdfd.addentity(100,100,150,75, "new");
                testdfd.repaint();;
            }
        });
        JButton drawBubble = new JButton("Draw Bubble");
        btnPanel.add(drawBubble);
        drawBubble.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testdfd.addbubble(50, 300, 300,"");
                testdfd.repaint();;
                Global.mode = 0;
             
         }
        });
      
        JButton drawDStore = new JButton("Draw Data Store");
        btnPanel.add(drawDStore);
        drawDStore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
         }
      });
        JButton drawArrow = new JButton("Draw Arrow");
        btnPanel.add(drawArrow);
        drawArrow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             //new MakeArrow(testdfd);
         }
        });
        
     
      // Add both panels to this JFrame
      Container cp = f.getContentPane();
      cp.setLayout(new BorderLayout());
      cp.add(testdfd, BorderLayout.CENTER);
      cp.add(btnPanel, BorderLayout.SOUTH);
      cp.add(new Toolbar(), BorderLayout.PAGE_START);
        f.setVisible(true);
        
        */
        
        //MainFrame
       Global.n = new MainFrame();
       Global.n.setVisible(true);
    }
}
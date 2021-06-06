/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgcase.tool;

/**
 *
 * @author Aseem
 */
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
public class GraphicDragAndDrop extends JPanel {
    
    
    
   static ArrayList <Rectangle> entity = new ArrayList<>();
    
//ArrayList <String> lrec = new ArrayList<>();
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(Color.blue);
        /*
        rar[0] = new Rectangle(100,100,150,75); 
        rar[1] = new Rectangle(100,300,150,75); 
        */
        
       int i;
       
        for(i=0; i<entity.size(); i++) {
            g2.draw(entity.get(i));
        }
    }
 
    public void setRect(int i, int x, int y) {
        entity.get(i).setLocation(x, y);
        repaint();
    }
}
class GraphicDragController2 extends MouseInputAdapter {
    GraphicDragAndDrop component;
    Point offset = new Point();
    boolean dragging = false;
 
    public GraphicDragController2(GraphicDragAndDrop gdad) {
        component = gdad;
        component.addMouseListener(this);
        component.addMouseMotionListener(this);
    }
    int sel=-1;
    public void mousePressed(MouseEvent e) {
        sel=-1;
        Point p = e.getPoint();
        for(int i=0; i<component.entity.size(); i++){
            Rectangle r =  component.entity.get(i);
        if(r.contains(p)) {
            sel=i;
        }
        else {
        }
        }
        if(sel>=0) {      
            Rectangle r =  component.entity.get(sel);
            offset.x = p.x - r.x;
            offset.y = p.y - r.y;
            dragging = true;
        }
        else {
            component.entity.add(new Rectangle(p.x, p.y,150,75));
            component.repaint();;
            System.out.println("added");
            
        }
    }
 
    public void mouseReleased(MouseEvent e) {
        dragging = false;
    }
 
    public void mouseDragged(MouseEvent e) {
        if(dragging) {
            int x = e.getX() - offset.x;
            int y = e.getY() - offset.y;
            component.setRect(sel, x, y);
        System.out.println(sel +" "+ x + " " + y);
        }
    }
}



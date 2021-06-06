/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgcase.tool;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Aseem
 */


public class dfd extends JPanel implements Serializable{
    dfd (String name) {
    this.name = name;
    shapes = new ArrayList<Symbol>();
    dataDic = new ArrayList<>();
    }
    /***********************Main components************************/
    String name;
    ArrayList <Symbol> shapes;
    ArrayList <Data> dataDic;
    
    /***************************************************************/
    int sel=-1;
    
    void addentity(int x, int y, int width, int height, String name) {
        Entity entity = new Entity(x,y,width,height,name);
        shapes.add(entity);
        System.out.println("Entity index " + shapes.size());
    }
    void addbubble(int r, int x, int y, String name) {
        Bubble bubble = new Bubble(r,x,y,name);
        shapes.add(bubble);
        System.out.println("Entity index " + shapes.size());
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(3));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(Color.blue);

        int i;
        for(i=0; i<shapes.size(); i++) {
            Symbol s = shapes.get(i);
            if(i==sel) s.draw(g2, 1);
            //Draw Shape
            else s.draw(g2,0);
        }
    }
    void updateDic () {
        Data d;
        FlowArrow a;
        dataDic.removeAll(dataDic);
        for(int i=0; i<shapes.size(); i++) {
            Symbol s = shapes.get(i);
            if(s.gettype().equals("flowarrow")) {
                a = (FlowArrow) s;
                d = new Data(a.name,a.type,a.description);
                dataDic.add(d);
            }
        }
    }
    void addtochild(dfd p, Symbol ps, dfd c) {
        FlowArrow a;
        Symbol temp;
        for(int i=0; i<p.shapes.size(); i++) {
            Symbol s = p.shapes.get(i);
            if(s.gettype().equals("flowarrow")) {
                a = (FlowArrow) s;
                if(a.head == ps) {
                    
                    c.shapes.add(a.tail.getcopy());
                }
                else if (a.tail==ps) {
                    c.shapes.add(a.head.getcopy());
                }
            }
        }    
    }
    /*
    private void printSimpleString(String s, int width, int XPos, int YPos){  
            int stringLen = (int)  
                g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();  
            int start = width/2 - stringLen/2;  
            g2d.drawString(s, start + XPos, YPos);  
     }*/  
}




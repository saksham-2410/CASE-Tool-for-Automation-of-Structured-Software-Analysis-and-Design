/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgcase.tool;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author Aseem
 */
public class DataStore extends Symbol{
    
    Rectangle2D.Double rect;
    DataStore (int x, int y, int width, int height, String name){
        //rect = new RoundRectangle2D.Double(x,y,width,height,roundness,roundness);
        rect = new Rectangle2D.Double(x,y,width,height);
        this.name = name;
        System.out.println("New Data Store Created");
    }   
    Symbol getcopy () {
        return new DataStore (this);
    }
    DataStore (DataStore d) {
        this.name = d.name;
        this.description = d.description;
        this.id = d.id;
        this.rect = d.rect;
    }
    void draw (Graphics2D g, int sel) {
        if (sel==1) g.setPaint(Color.BLACK);
        else g.setPaint(Color.BLUE);
        g.draw(rect);
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D strb = fm.getStringBounds(name, g);
        g.drawString ( name,
                (getX() + getWidth()*5/8 - fm.stringWidth(name)/2),
                (int) (getY() + getHeight()/2 + strb.getHeight()/2));
        g.drawLine(getX()+ getWidth()/4, getY(), getX()+getWidth()/4, getY()+ getHeight());
    };
    void move(int x, int y) {
        rect.setFrame(x, y, rect.width, rect.height);
    }
    void resize (int width, int height) {
        rect.height = height;
        rect.width = width;
    }
    
    boolean contains(Point p) {
        return rect.contains(p);
    }
    
    Rectangle2D getBounds2D () {
        return rect.getBounds2D();
    }
    String gettype () {
        return "datastore";
    }

    /*
    Line2D.Double l1;
    Line2D.Double l2;
    Point p;
    int height;
    int width;
    pline line;
    DataStore (int x1, int y1, int width, int height, String name){
        p = new Point(x1, y1);
        this.height = height;
        this.width = width;
        refreshlines();
        this.name = name;
        System.out.println("New Entity Created");
    }
    @Override
    Shape getshape() {
        return line;
    }
    void move(int x, int y) {
        p.x = x;
        p.y = y;
    }
    void resize (int width, int height) {
        this.height = height;
        this.width = width;
    }
    void refreshlines() {
        l1.setLine(p.x, p.y, p.x+width, p.y);
        l1.setLine(p.x+height, p.y, p.x+width, p.y+height);
    }
    public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D)g;
    GeneralPath path = new GeneralPath();
    path.moveTo(p.x, p.y);
    path.lineTo(p.x, p.x+width);

    path.moveTo(p.x+height, p.y);
    path.lineTo(p.x+height, p.y+width);
    g2d.draw(path);
}
    */
}

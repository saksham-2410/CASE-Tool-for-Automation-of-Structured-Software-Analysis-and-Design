/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgcase.tool;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Aseem
 */
public class CtrlArrow extends Symbol{
    
    Symbol head;
    Symbol tail;
    Line2D.Double line;
    String type;
    CtrlArrow(Point p,Point q, String name) {
        line = new Line2D.Double(p, q);
        this.name = name;
        arrowHead = new Polygon();  
        //Arrow head
        arrowHead.addPoint( 0,5);
        arrowHead.addPoint( -5, -5);
        arrowHead.addPoint( 5,-5);
    }
    void draw (Graphics2D g, int sel) {
        if (sel==1) g.setPaint(Color.BLACK);
        else g.setPaint(Color.BLUE);
        
        //dashed line
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.draw(line);
        g2d.dispose();
        
        //other things
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D strb = fm.getStringBounds(name, g);
        g.drawString ( name,
                (getX() + getWidth()/2 - fm.stringWidth(name)/2),
                (int) (getY() + getHeight()/2 + strb.getHeight()/2)-15);
        
        //Shape arrow = createArrowShape(line.getP1(), line.getP2());
        drawArrowHead(g);
    }
    void resize (int width, int height) {
        line.x2 = line.x1 + width;
        line.y2 = line.y1 + height;
    }
    Rectangle2D getBounds2D () {
        return line.getBounds2D();
    }
    boolean isdrawn () {
        if(this.getHeight()>0 || this.getWidth()>0) {
            return true; 
        }
        else return false;
    }
    void setterminals(Symbol head, Symbol tail) {
        this.head = head;
        this.tail = tail;
    }
    boolean contains(Point p) {
        if(line.ptSegDist(p)<5) return true;
        else return false;
    }
    void movehead (int x, int y) {
        line.x2 +=x;
        line.y2 +=y;
    }
    void movetail (int x, int y) {
        line.x1 +=x;
        line.y1 +=y;
    }
            
    void refactor() {
        System.out.println(head.name + "" + tail.name);
        double x1 = line.x1, x2 = line.x2, y1 = line.y1, y2 = line.y2;
        Point t = new Point((int)x2, (int)y2);
        double tan = (y2-y1) / (x2-x1);
        while (head.contains(t)) {
            if(x2>x1) {
                t.setLocation(t.getX()-1,t.getY()-tan);
            }
            else {
                t.setLocation(t.getX()+1,t.getY()+tan);
            }
        }
        line.setLine(line.getP1(), t);
        t.setLocation(x1, y1);
        while (tail.contains(t)) {
            if(x2>x1) {
                t.setLocation(t.getX()+1,t.getY()+tan);
            }
            else {
                t.setLocation(t.getX()-1,t.getY()-tan);
            }
        }
        line.setLine(t, line.getP2());
        
    }
    
    public static double GetAngleOfLineBetweenTwoPoints(Point.Double p1, Point.Double p2) { 
        double xDiff = p2.x - p1.x; 
        double yDiff = p2.y - p1.y; 
        return Math.toDegrees(Math.atan2(yDiff, xDiff)); 
    } 
    AffineTransform tx = new AffineTransform();
    Polygon arrowHead;
    
    private void drawArrowHead(Graphics2D g2d) {  
    tx.setToIdentity();
    double angle = Math.atan2(line.y2-line.y1, line.x2-line.x1);
    tx.translate(line.x2, line.y2);
    tx.rotate((angle-Math.PI/2d));  

    Graphics2D g = (Graphics2D) g2d.create();
    g.setTransform(tx);   
    g.fill(arrowHead);
    g.dispose();
}
    String gettype () {
        return "ctrlarrow";
    }
}

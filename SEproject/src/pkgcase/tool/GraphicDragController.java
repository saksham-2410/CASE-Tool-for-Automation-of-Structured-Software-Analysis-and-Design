/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgcase.tool;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Aseem
 */
class GraphicDragController extends MouseInputAdapter {
    dfd component;
    Point offset = new Point();
    boolean dragging = false;
    boolean constructing=false;
    public GraphicDragController(dfd gdad) {
        component = gdad;
        component.addMouseListener(this);
        component.addMouseMotionListener(this);
    }
    
    //Selected symbol
    int sel=-1;
    Point p,q;
    
    //For connstructing new symbol
    Symbol temp;
    
    //For constructing new arrow
    FlowArrow tempa;
    public void mouseMoved(MouseEvent e) {
        sel = getsymbolindex(e.getPoint());
        component.sel = sel;
        component.repaint();
    }
    public void mouseClicked(MouseEvent e) {
        
        sel = getsymbolindex(p);
        if (SwingUtilities.isRightMouseButton(e)) {
            PopUpDemo menu = new PopUpDemo();
            menu.show(e.getComponent(), e.getX(), e.getY());
            System.out.println("showing menu");
        }
        if (e.getClickCount() == 2){
            sel = getsymbolindex(e.getPoint());
            if(Global.mode ==11 && component.shapes.get(sel).gettype().equals("bubble")) {
                dfd d = ((Bubble)component.shapes.get(sel)).d;
                d.name = component.shapes.get(sel).name;
                GraphicDragController gdadc = new GraphicDragController(d);
                //if(d.shapes.size()==0) d.addtochild(component, component.shapes.get(sel), d);
                Global.n.tabs.add(d.name, d);
                Global.n.tabs.setSelectedIndex(Global.n.tabs.getTabCount()-1);
            }
            else {/*Global.mode==5*/
                if(sel>=0) {
                    Symbol s = component.shapes.get(sel);
                    System.out.println("double clicked "+sel);
                    if(sel>=0) {
                        switch(sel) {
                        }
                        Edit en;
                        en = new Edit(s);
                        en.setVisible(true);
                    }
                }
            }
        }
        if(Global.mode==21) {
            
            sel = getsymbolindex(e.getPoint());
            int ans;
            if(component.shapes.get(sel).gettype().equals("flowarrow")) {
                ans = JOptionPane.showConfirmDialog(component, "Are you sure you want to delete "+component.shapes.get(sel).name +". The data flow arrow and the corresponding data name will get deleted.?");
            }
            else {
                ans = JOptionPane.showConfirmDialog(component, "Are you sure you want to delete "+component.shapes.get(sel).name +" and the connected arrows?");
            }
            if(ans == JOptionPane.YES_OPTION) {
                deletearrow(component.shapes.get(sel));
                component.shapes.remove(sel);
                component.repaint();
            }
        }
    }
    public void mousePressed(MouseEvent e) {
        sel=-1;
        p = e.getPoint();
        if(Global.mode !=4 && Global.mode !=6) sel = getsymbolindex(p);
        if(sel>=0) {
            Symbol r =  component.shapes.get(sel);
            offset.x = (int) (p.x - r.getBounds2D().getX());
            offset.y = (int) (p.y - r.getBounds2D().getY());
            dragging = true;
            System.out.println(sel);
        }
        else{
            switch (Global.mode) {
                case 1:
                    temp = new Entity(p.x,p.y,0,0,"Entity");
                    component.shapes.add(temp);
                    break;
                case 2: 
                    temp = new Bubble(0,p.x,p.y,"Bubble");
                    component.shapes.add(temp);
                    break;
                case 3: 
                    temp = new DataStore(p.x, p.y, 0, 0, "Data Store");
                    component.shapes.add(temp);
                    break;
                case 4:
                    temp = new FlowArrow(p, p, "Arrow");
                    component.shapes.add(temp);
                    break;
                case 6:
                    temp = new CtrlArrow(p, p, "Arrow");
                    component.shapes.add(temp);
                    break;
                case 7:
                    temp = new Module(p.x,p.y,0,0,"Module");
                    component.shapes.add(temp);
                    break;
            }
            dragging = true;
            constructing = true;
        }
    }
 
    public void mouseReleased(MouseEvent e) {
        dragging = false;
        //if(Global.mode==0) return;
        q = e.getPoint();
        if(constructing){
            if (temp.isdrawn()) {
                
                //Validity of arrow
                if(Global.mode ==4 || Global.mode ==6) {
                    int c1,c2;
                    int max = component.shapes.size()-2;
                    c1 = getsymbolindex(p);
                    c2 = getsymbolindex(q);
                    if(c1>=0 && c2>=0 && c1!=c2 && c1<=max && c2<=max && Global.mode ==4) {
                        FlowArrow a = (FlowArrow) temp;
                        a.setterminals(component.shapes.get(c2), component.shapes.get(c1));
                        a.refactor();;
                        Edit edit = new Edit (component.shapes.get(component.shapes.size()-1));
                        edit.setVisible(true);
                    }
                    else if(c1>=0 && c2>=0 && c1!=c2 && c1<=max && c2<=max && Global.mode ==6) {
                        CtrlArrow a = (CtrlArrow) temp;
                        a.setterminals(component.shapes.get(c2), component.shapes.get(c1));
                        a.refactor();;
                        Edit edit = new Edit (component.shapes.get(component.shapes.size()-1));
                        edit.setVisible(true);
                    }
                    else {
                        component.shapes.remove(component.shapes.size()-1);
                        System.out.println("Invalid Arrow");
                        component.repaint();
                        return;
                    }
                }
                else {
                    Edit edit = new Edit (component.shapes.get(component.shapes.size()-1));
                    edit.setVisible(true);
                }
                System.out.println("New " + temp.getClass().toString() + " drawn");
                System.out.println("Count: " + component.shapes.size());
            }
            else {
                 component.shapes.remove(component.shapes.size()-1);
            }
        }
        else{
                int x = e.getX()- p.x;
                int y = e.getY() - p.y;
                adjustarrows(component.shapes.get(sel), x,y);
        }
        component.repaint();
        constructing = false;
    }
 
    public void mouseDragged(MouseEvent e) {
        if (dragging) {
            if(!constructing){
                int x = e.getX() - offset.x;
                int y = e.getY() - offset.y;
                component.shapes.get(sel).move(x, y);
            }
            else {
            switch (Global.mode) {
                case 0:
                    break;
                case 1:
                    temp.resize(e.getX() - p.x,e.getY() - p.y);
                case 2:
                    temp.resize(e.getX() - p.x,e.getY() - p.y);
                case 3:
                    temp.resize(e.getX() - p.x,e.getY() - p.y);
                case 4:
                    temp.resize(e.getX() - p.x,e.getY() - p.y);
                case 6:
                    temp.resize(e.getX() - p.x,e.getY() - p.y);
                case 7:
                    temp.resize(e.getX() - p.x,e.getY() - p.y);
            }
            }
            
            component.repaint();
        }
    }
    public int getsymbolindex(Point p) {
        int index=-1;
        for(int i=0; i<component.shapes.size(); i++){
            Symbol s =  component.shapes.get(i);
            if(s.contains(p)) {
                index=i;
                return i;
            }
        }
        return index;
    }
    void adjustarrows (Symbol curr, int x, int y) {
        FlowArrow a;
        for(int i=0; i<component.shapes.size(); i++) {
            Symbol s = component.shapes.get(i);
            if(s.gettype().equals("flowarrow")) {
                a = (FlowArrow) s;
                if(a.head == curr) a.movehead(x, y);
                else if (a.tail==curr) a.movetail(x,y);
            }
        }    
        CtrlArrow b;
        for(int i=0; i<component.shapes.size(); i++) {
            Symbol s = component.shapes.get(i);
            if(s.gettype().equals("ctrlarrow")) {
                b = (CtrlArrow) s;
                if(b.head == curr) b.movehead(x, y);
                else if (b.tail==curr) b.movetail(x,y);
            }
        }
        System.out.println("Arrows adjusted");
    }
    void deletearrow(Symbol curr) {
        FlowArrow a;
        for(int i=0; i<component.shapes.size(); i++) {
            Symbol s = component.shapes.get(i);
            if(s.gettype().equals("flowarrow")) {
                a = (FlowArrow) s;
                if(a.head == curr) component.shapes.remove(i);
                else if (a.tail==curr) component.shapes.remove(i);
            }
        }    
    }
    
}


class PopUpDemo extends JPopupMenu {
    JMenuItem anItem;
    public PopUpDemo(){
        
        addMouseListener(new menuclicklistener());
        anItem = new JMenuItem("Add Entity");
        anItem.addMouseListener(new menuclicklistener());
        add(anItem);
        anItem = new JMenuItem("Add Bubble");
        anItem.addMouseListener(new menuclicklistener());
        add(anItem);
        anItem = new JMenuItem("Add Data Store");
        anItem.addMouseListener(new menuclicklistener());
        add(anItem);
        anItem = new JMenuItem("");
        anItem.addMouseListener(new menuclicklistener());
        add(anItem);
    }
}
class menuclicklistener implements MouseListener {
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked (# of clicks: " + e.getClickCount() + ")");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

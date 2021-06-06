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
public class Global {
    public static int mode=0;
        /*
    0 - Do nothing, adjust position
    1 - Draw entity
    2 - Draw bubble
    3 - Draw Data Store
    4 - Data Flow Arrow
    6 - Control Flow Arrow
    5 - Editing
    7 - Module
    21 - Delete
    11 - Internal Diagram
    */
    public static MainFrame n;
    /*
    void changeMode(int m) {
        this.mode = m;
        System.out.println("Mode = "+m);
    }
    */
    static void changemode(int m) {
        mode = m;
        System.out.println("Mode: " + mode);
        MainFrame.updatestatus(m);
    }
}

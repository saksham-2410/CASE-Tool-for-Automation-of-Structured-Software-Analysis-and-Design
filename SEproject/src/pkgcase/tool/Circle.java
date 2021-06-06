/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgcase.tool;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Aseem
 */
public class Circle extends Ellipse2D.Double {
    Circle(int radius, int cx, int cy) {
        height = 2*radius;
        width = 2*radius;
        x = cx-radius;
        y = cy-radius;
    }
    
}

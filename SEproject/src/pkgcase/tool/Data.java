/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgcase.tool;

import java.io.Serializable;

/**
 *
 * @author Aseem
 */
public class Data implements Serializable{
    Data (String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description; 
    }
    String name;
    String type;
    String description;
}

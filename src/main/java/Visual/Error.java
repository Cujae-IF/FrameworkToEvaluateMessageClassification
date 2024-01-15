/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import javax.swing.JOptionPane;

/**
 *
 * @author Unknown
 */
public class Error {
    public static void show(String text){
        JOptionPane.showMessageDialog(null,text,"Error",JOptionPane.ERROR_MESSAGE);
    }
}

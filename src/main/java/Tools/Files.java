/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

/**
 *
 * @author Unknown
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Files {
    public static <Type> Type loadFile(File file) throws Exception {
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        Type object = (Type) objIn.readObject();
        objIn.close();
        fileIn.close();
        return object;
    }

    public static List<String> loadText(File file) throws Exception {
        List<String> reading = new ArrayList<String>();
        FileReader read = new FileReader(file);
        BufferedReader B_Read = new BufferedReader(read);
        String line = null;
        while ((line = B_Read.readLine()) != null) {
            reading.add(line);
        }
        B_Read.close();
        read.close();
        return reading;
    }
    
    public static void writeFile(Object object, String direction) throws Exception {
        FileOutputStream fileOut = new FileOutputStream(direction);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(object);
        objOut.close();
        fileOut.close();
    }
   
}

package Projet;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



/**
 * BasŽ sur http://www.jmdoudoux.fr/java/dej/chap-serialisation.htm
 *
 * @author blay
 *
 */

public class Memoire {


    public  static void save (Object o, String fileName){
        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichier = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(o);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    public  static Object read (String fileName){
        ObjectInputStream ois = null;
        Object o = null;
        try {
            final FileInputStream fichier = new FileInputStream(fileName);
            ois = new ObjectInputStream(fichier);
            o = ois.readObject();
            System.out.println(o);
        } catch (final java.io.FileNotFoundException e) {
            System.out.println("Pas de prŽcŽdentes sauvegardes");
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();

            }
        }
        return o;
    }
}
package gestionTexture;

//Importation des packages n�cessaires
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BMPImage {
  //Constantes
  private static final int MASK = 0xFF;/**Constante de masque pour la comparaison bit � bit*/  
  
 /**M�thode permettant de lire une image au format BMP

  
@param filename Fichier image
  
@return Image g�n�r�*/

 public static Image readBMP(File filename) {
   try{
     DataInputStream inBMP = new DataInputStream(new FileInputStream(filename));

     //On va lire le d�but de l'ent�te
     inBMP.skipBytes(18);

     //Maintenant, on lit la largeur et la hauteur de l'image
     int width = readInt(inBMP);  
     //System.out.println(width);
     int height = readInt(inBMP);
    // System.out.println(" oui " + height);
     
     //On saute les donn�es inutiles del'ent�te
     inBMP.skipBytes(28);

     //Nous allons remplir le BufferedImage
     BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
     int sup = (width * 3) % 4;
     
     //Lecture des donn�es
     for(int y = height - 1; y >= 0; y--){
        for(int x = 0; x < width; x++){
        	//System.out.println("nb : " + nbTile + " x : " + x);
          //R�cup�ration des couleurs
          img.setRGB(x, y, readColor(inBMP));
        }
        
        //On saute le bourrage
        inBMP.skipBytes(sup);
      }

     inBMP.close();
     return img;
   }
   catch(Exception e){
     return null;
   }
 }
 
 /**M�thode permettant de lire une image au format BMP

  
@param filename Endroit o� il faut le sauver
  
@return Image g�n�r�*/

 //public static Image readBMP(String filename) {
 	//return readBMP(new File(filename));
 //}
 
 /**M�thode permettant de lire un entier dans un fichier BMP

  
@param in Flux d'entr�e
  
@return Entier lu*/

 private static int readInt(DataInputStream in){
 	byte[] b = new byte[4];
 	int result = 0;
 	
 	try{
 		in.read(b);
 		result = b[0] & MASK;
 		result = result + ((b[1] & MASK) << 8);
 		result = result + ((b[2] & MASK) << 16);
 		result = result + ((b[3] & MASK) << 24);
 		
 	}
   catch(Exception e){
   	
   }
   
   return result;
 }
 
 /**M�thode permettant de lire une "couleur" dans un fichier BMP

  
@param in Flux d'entr�e
  
@return Entier lu*/

 private static int readColor(DataInputStream in){
 	byte[] b = new byte[3];
 	int result = 0;
 	
 	try{
 		in.read(b);  		
 		result = b[0] & MASK;
 		result = result + ((b[1] & MASK) << 8);
 		result = result + ((b[2] & MASK) << 16);
 	}
   catch(Exception e){
   	
   }
   
   return result;
 }

 /**M�thode permettant de cr�er une image au format BMP

  
@param img Image � sauver
  
@param filename Endroit o� il faut le sauver
  
@return Permet de savoir si l'enregistrement s'est bien pass�*/

 public static boolean writeBMP(BufferedImage img, String filename) {
   try{
     DataOutputStream outBMP = new DataOutputStream(new FileOutputStream(filename));
     int resx = img.getWidth();
     int resy = img.getHeight();

     //�criture de l'ent�te du fichier
     outBMP.write(0x42); //Type
     outBMP.write(0x4D); //Type
     writeInt(outBMP,resx*resy*3+54); //Taille du fichier
     writeInt(outBMP,0); //R�serv�
     writeInt(outBMP,54);
     writeInt(outBMP,40);
     writeInt(outBMP,resx); //Largeur
     writeInt(outBMP,resy); //Hauteur
     outBMP.write(0);
     outBMP.write(0);
     outBMP.write(24); //Nombre de bits par pixel
     outBMP.write(0);
     writeInt(outBMP,0);
     writeInt(outBMP,resx*resy*3);
     writeInt(outBMP,2851);
     writeInt(outBMP,2851);
     writeInt(outBMP,0);
     writeInt(outBMP,0);

     //Ecriture du corps dufichier BMP
     int sup = (resx * 3) % 4;
     
     for (int y = resy - 1; y >= 0; y--) {
         for (int x = 0; x < resx; x++) {
         	//On �crit la couleur
           writeColor(outBMP, img.getRGB(x, y));
         }
         
         //Bourrage
         for (int j = 0; j < sup; j++)
           outBMP.writeByte(0);
       }

     //Fermeture du fichier
     outBMP.close();

     return true;
   }
   catch(Exception e){
     return false;
   }
 }

 /**M�thode permettant d'�crire un int dans un DataOutputStream selon les format Intel

  
@param sortie Stream de destination
  
@param n int � �crire*/

 private static void writeInt(DataOutputStream sortie, int n) {
   try {
     sortie.write((n) & MASK);
     sortie.write((n>>8) & MASK);
     sortie.write((n>>16) & MASK);
     sortie.write((n>>24) & MASK);
   }
   catch (Exception e) {
    
   }
 }
 
 /**M�thode permettant d'�crire une "couleur" dans un DataOutputStream

  
@param sortie Stream de destination
  
@param couleur Couleur � �crire   */

 private static void writeColor(DataOutputStream sortie, int couleur) {
   try {
     sortie.write((couleur) & MASK);
     sortie.write((couleur>>8) & MASK);
     sortie.write((couleur>>16) & MASK);
   }
   catch (Exception e) {
     
   }
 }
}

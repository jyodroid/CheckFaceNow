/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checkfacenow.servicelogic;

import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.MultiStepRescaleOp;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author John
 */
public class UploadImage {
    
    public boolean subirImagen(String id, Image foto){
        if(foto==null){
            System.out.println("no hay imagen");    
            return false;
        }else{
            //path to save the file using the file name from the URL.
            String path = "C:\\Users\\John\\Desktop\\"+id+".jpg";
            if (id.compareTo("identificado")==0){
                //asi para identificar
                path = "C:\\Users\\John\\Desktop\\identificar.jpg";
                
                //tamano para cortar y dejar en factor de proporsion de (200/150)=(4/3)
                int cancho=801;
                int clargo=1068;
                
                //coordenada desde donde cortar
                int cx=377;
                int cy=569;
                
                //tamano original es
                int oancho=150;
                int olargo=200;
                
                try{
                //se resibe la imagen
                    Image imageninput = foto;
                    BufferedImage inputImage = (BufferedImage) imageninput;
                    int alto = inputImage.getHeight();
                    int ancho = inputImage.getWidth();
                 
                    //se invierte el tama�o
                    BufferedImage resizedImageinput = 
                            new BufferedImage(alto, ancho, BufferedImage.TYPE_INT_RGB);
                    Graphics2D gi = resizedImageinput.createGraphics();
                    
                    //se gira
                    gi.rotate(Math.toRadians(-90));
                 
                    //invierte tama�o donde se dibuja
                    gi.drawImage(inputImage, 0, alto, -ancho, -alto, null);
                    gi.dispose();   
                
                    //en original image recibo la imagen girada y en escala de grises
                    BufferedImage originalImage = resizedImageinput; 
                    
                    //type
                    int type = originalImage.getType() == 
                            0? BufferedImage.TYPE_INT_RGB : originalImage.getType();
                    //se debe calcular x y
                    int x = (originalImage.getWidth()-cancho) / 2;
                    int y = (originalImage.getHeight()-clargo) / 2;
                
                    //desde donde vamos a tomar la imagen
                    BufferedImage bufferedThumbnail = 
                            new BufferedImage(cancho,clargo,type); 
                
                    //para recortar el rostro
                    Graphics2D g = bufferedThumbnail.createGraphics();
                    g.drawImage(originalImage, -x, -y,originalImage.getWidth(),originalImage.getHeight(), null);
                    g.dispose();
                    
                    //resize img
                    MultiStepRescaleOp rescale = new MultiStepRescaleOp(oancho, olargo);
                    rescale.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Soft);
                    BufferedImage resizedImage = rescale.filter(bufferedThumbnail, null);
               
                    //asi se guarda 
                    ImageIO.write(resizedImage, "png", new File(path));
                    
                    return true;
                }catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }else{
            //asi se sube para el registro
                try{    
                    BufferedImage bi = (BufferedImage) foto;
                    File f = new File(path);
                    ImageIO.write(bi, "png", f);
                    System.out.println("imagen subida");
                    return true;
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    return false;
                }
            }
        }
    }
}
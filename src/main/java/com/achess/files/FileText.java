package com.achess.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author achess
 */
public class FileText {
    private File file;
    String text;
    private static FileText fileText;
    
    private FileText(){
        text = "";
    }
    
    public static FileText getFileText(){
        if(fileText == null){
            fileText = new FileText();
        }
        return fileText;
    }
    
    public String getText(){
        return text;
    }
    
    private void openFile(){
        JFileChooser upload = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("txt","Texto");
        upload.setFileFilter(filtro);
        upload.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        upload.showOpenDialog(null);        
        FileReader fr = null;
        try{
            file = upload.getSelectedFile();
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            this.text = "";
            String line;                     
            while((line = br.readLine()) != null){                
                text += line + "\n";
            }                                    
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Error al cargar el archivo");     
        }
        finally{
                try{
                    if(fr!=null){
                    fr.close();
                    }
                }catch(Exception ex){
                    ex.printStackTrace(System.out);
                }                
            }
    }
    
}

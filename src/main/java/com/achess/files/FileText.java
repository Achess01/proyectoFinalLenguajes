package com.achess.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author achess
 */
public class FileText {
    private File file;
    private String text;
    private String previusText;
    private String nextText;
    private static FileText fileText;
    private boolean changed;
    
    private FileText(){
        text = "";
        previusText = text;
        nextText = text;
        changed = false;
    }
    
    public static FileText getFileText(){
        if(fileText == null){
            fileText = new FileText();
        }
        return fileText;
    }
    
    public String getPath(){
        String text = "";
        if(file != null){
            text = file.getPath();
        }
        return text;
    }
    public String getText(){
        return text;
    }
    
    public boolean hasChanged(String text){
        changed = !this.text.equals(text);
        if(changed){
            this.nextText = text;            
        }                
        return changed;
    }
    
    public boolean save(){
         
        if(changed){
            boolean saved = saveFile(nextText);
            if(saved){
               previusText = text;
               text = nextText;               
            }else{
                this.file = null;
            }
            return saved;
        }
        return false;
    }
           
    public boolean openFile(){
        boolean opened = false;
        JFileChooser upload = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Texto","txt");
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
            previusText = text;
            opened = true;
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Error al cargar el archivo");  
            opened = false;
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
        return opened;
    }
    
     private File saveAs(){     
            File newFile = null;
            JFileChooser save = new JFileChooser();                        
            save.showSaveDialog(null);
            save.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);            
            FileWriter fw = null;            
            String extension = ".txt";
            try{
                newFile = save.getSelectedFile();                
                if(!newFile.getAbsolutePath().endsWith(extension)){
                    newFile = new File(newFile + extension);
                }              
            }catch (Exception ex) {                
                newFile = null;
            }            
            return newFile;
    }
    
    private boolean saveFile(String text){       
            boolean saved = false;
            if(text.length() >= 0){
            if(this.file == null){
                this.file = saveAs();
            }                   
            try{
                File file = this.file; 
                PrintWriter pw = new PrintWriter(file.getPath());
                pw.print(text); 
                pw.close();       
                saved = true;
            }catch (FileNotFoundException | NullPointerException ex) {                
                saved = false;
            }                     
        }
            return saved;
    }
    
}

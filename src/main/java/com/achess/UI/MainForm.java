/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.achess.UI;

import com.achess.backend.lex.Automaton;
import com.achess.backend.Token;
import com.achess.backend.lex.WordAutomaton;
import com.achess.backend.sintaxis.PDA;
import com.achess.files.FileText;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author achess
 */
public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        customSettings();
    }
    private void customSettings(){
        LineNumber line1 = new LineNumber(textEditor);
        jScrollPane1.setRowHeaderView(line1);
        LineNumber line2 = new LineNumber(textFound);
        jScrollPane2.setRowHeaderView(line2);    
        jScrollPane1.requestFocusInWindow();
    }
    private void showTextFound(){
        this.jScrollPane2.setVisible(true);
        this.buttonCloseTextFound.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    private void hideTextFound(){
        this.jScrollPane2.setVisible(false);
        this.buttonCloseTextFound.setVisible(false);
        SwingUtilities.updateComponentTreeUI(this);
    }
    private void saveFile(){        
        FileText fileText = FileText.getFileText();
        if(fileText.save()){
            this.labelFile.setText("File: " + fileText.getPath());
        }        
    }
    
     private void openFile(){
        if(newFile()){
            FileText fileText = FileText.getFileText();
            if(fileText.openFile()){
                String text = fileText.getText();
                this.textEditor.setText(text);
                this.labelFile.setText("File: " + fileText.getPath());
            }
        }
        
    }
    
     
     //Pruebas borrar***
    private String openFilePrueba(){       
        FileReader fr = null;
        String text = "";
        try{
            File file = new File("/home/achess/pruebas.txt");
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);                        
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
        return text;
    }
    
    private void analizeTest(){
        hideTextFound();        
        String text = openFilePrueba();
        if (text.length() > 0){
           Automaton.getAutomaton().analize(text);
           ArrayList<Token> errors = Automaton.getAutomaton().getErrors();
           if(errors.size() > 0){
               String errorMessage = "";
               for (Token tk: errors){
                   String pos = "Ln:" + tk.getRow() + " Col:" + tk.getColumn();
                   errorMessage += "Error '"+ tk.getLexeme() + "'\ten " +pos+"\t" +tk.getDescription()+ "\n";
               }               
               this.textFound.setText(errorMessage);
               showTextFound();
           }
           else{
               ArrayList<Token> tokens = Automaton.getAutomaton().getTokens();
               JDialog dialog = new DialogTokens(this, true, tokens);
               dialog.setLocationRelativeTo(null);
               dialog.setVisible(true);
           }        
        }        
    }
    ///
    private void analize(){
        hideTextFound();        
        String text = textEditor.getText();
        if (text.length() > 0){
           Automaton.getAutomaton().analize(text);
           ArrayList<Token> errors = Automaton.getAutomaton().getErrors();
           if(errors.size() > 0){
               String errorMessage = "";
               for (Token tk: errors){
                   String pos = "Ln:" + tk.getRow() + " Col:" + tk.getColumn();
                   errorMessage += "Error '"+ tk.getLexeme() + "'\ten " +pos+"\t" +tk.getDescription()+ "\n";
               }               
               this.textFound.setText(errorMessage);
               showTextFound();
           }
           else{
               ArrayList<Token> tokens = Automaton.getAutomaton().getTokens();
               JDialog dialog = new DialogTokens(this, true, tokens);
               dialog.setLocationRelativeTo(null);
               dialog.setVisible(true);
           }        
        }        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonAnalize = new javax.swing.JButton();
        buttonSearch = new javax.swing.JButton();
        textWordSearch = new javax.swing.JTextField();
        labelCords = new javax.swing.JLabel();
        buttonAnalize1 = new javax.swing.JButton();
        labelFile = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textEditor = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        textFound = new javax.swing.JTextArea();
        buttonCloseTextFound = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuOpen = new javax.swing.JMenuItem();
        menuSave = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ALEXEME");
        setBackground(new java.awt.Color(46, 41, 46));
        setExtendedState(6);

        jPanel1.setBackground(new java.awt.Color(53, 53, 53));

        buttonAnalize.setBackground(new java.awt.Color(46, 41, 46));
        buttonAnalize.setForeground(new java.awt.Color(250, 231, 88));
        buttonAnalize.setText("L??xico");
        buttonAnalize.setBorder(null);
        buttonAnalize.setBorderPainted(false);
        buttonAnalize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAnalizeActionPerformed(evt);
            }
        });

        buttonSearch.setBackground(new java.awt.Color(46, 41, 46));
        buttonSearch.setForeground(new java.awt.Color(250, 231, 88));
        buttonSearch.setText("Buscar");
        buttonSearch.setBorder(null);
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        textWordSearch.setBackground(new java.awt.Color(25, 23, 26));
        textWordSearch.setFont(new java.awt.Font("Liberation Mono", 0, 13)); // NOI18N
        textWordSearch.setForeground(new java.awt.Color(255, 255, 255));
        textWordSearch.setCaretColor(new java.awt.Color(255, 255, 255));

        labelCords.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        labelCords.setForeground(new java.awt.Color(250, 231, 88));
        labelCords.setText("Ln: 1 Col: 1");

        buttonAnalize1.setBackground(new java.awt.Color(46, 41, 46));
        buttonAnalize1.setForeground(new java.awt.Color(250, 231, 88));
        buttonAnalize1.setText("Sint??ctico");
        buttonAnalize1.setBorder(null);
        buttonAnalize1.setBorderPainted(false);
        buttonAnalize1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAnalize1ActionPerformed(evt);
            }
        });

        labelFile.setFont(new java.awt.Font("Liberation Mono", 0, 14)); // NOI18N
        labelFile.setForeground(new java.awt.Color(250, 231, 88));
        labelFile.setText("File:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(buttonAnalize, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAnalize1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(labelFile, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelCords)
                .addGap(30, 30, 30)
                .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textWordSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(labelCords, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(textWordSearch))
            .addComponent(buttonSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonAnalize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonAnalize1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(46, 41, 46));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane1.setBorder(null);

        textEditor.setBackground(new java.awt.Color(46, 41, 46));
        textEditor.setColumns(20);
        textEditor.setFont(new java.awt.Font("Liberation Mono", 0, 18)); // NOI18N
        textEditor.setForeground(new java.awt.Color(255, 255, 255));
        textEditor.setRows(5);
        textEditor.setTabSize(1);
        textEditor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 191, 78)));
        textEditor.setCaretColor(new java.awt.Color(255, 255, 255));
        textEditor.setSelectionColor(new java.awt.Color(100, 100, 100));
        textEditor.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                textEditorCaretUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(textEditor);

        jScrollPane2.setBorder(null);

        textFound.setEditable(false);
        textFound.setBackground(new java.awt.Color(46, 41, 46));
        textFound.setColumns(20);
        textFound.setFont(new java.awt.Font("Liberation Mono", 0, 16)); // NOI18N
        textFound.setForeground(new java.awt.Color(255, 255, 255));
        textFound.setRows(5);
        textFound.setTabSize(1);
        textFound.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 191, 78)));
        textFound.setCaretColor(new java.awt.Color(255, 255, 255));
        textFound.setSelectionColor(new java.awt.Color(100, 100, 100));
        jScrollPane2.setViewportView(textFound);

        buttonCloseTextFound.setBackground(new java.awt.Color(46, 41, 46));
        buttonCloseTextFound.setForeground(new java.awt.Color(250, 231, 88));
        buttonCloseTextFound.setText("X");
        buttonCloseTextFound.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonCloseTextFound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseTextFoundActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addGap(0, 0, 0)
                .addComponent(buttonCloseTextFound, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCloseTextFound)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane2.setVisible(false);
        buttonCloseTextFound.setVisible(false);

        jMenuBar1.setBackground(new java.awt.Color(53, 53, 53));

        menuFile.setForeground(new java.awt.Color(255, 255, 255));
        menuFile.setText("Archivo");

        menuOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuOpen.setText("Abrir");
        menuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpenActionPerformed(evt);
            }
        });
        menuFile.add(menuOpen);

        menuSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuSave.setText("Guardar");
        menuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaveActionPerformed(evt);
            }
        });
        menuFile.add(menuSave);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Nuevo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuFile.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Guardar como");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuFile.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Deshacer");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuFile.add(jMenuItem3);

        jMenuBar1.add(menuFile);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpenActionPerformed
        // TODO add your handling code here:
        openFile();
        
    }//GEN-LAST:event_menuOpenActionPerformed

    private void textEditorCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_textEditorCaretUpdate
        // TODO add your handling code here:
        lookForChanges();        
        try{
            JTextArea txt = this.textEditor;
            int caretOffset = txt.getCaretPosition();
            int lineNumber = txt.getLineOfOffset(caretOffset);                    
            int col = caretOffset - txt.getLineStartOffset(lineNumber) + 1;
            lineNumber+=1;            
            labelCords.setText("Ln: " + lineNumber +" Col: " + col);            
        }
        catch(Exception ex){
            ex.printStackTrace(System.out);
        }
    }//GEN-LAST:event_textEditorCaretUpdate

    private void buttonCloseTextFoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseTextFoundActionPerformed
       
        hideTextFound();
    }//GEN-LAST:event_buttonCloseTextFoundActionPerformed

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        // TODO add your handling code here:
        String word = textWordSearch.getText();
        String text = textEditor.getText();
        WordAutomaton autW = new WordAutomaton(word);
        autW.analize(text);
        ArrayList<Token> foundedWords = autW.getWords();        
        if(foundedWords.size()>0){
            showTextFound();
            textFound.getHighlighter().removeAllHighlights();
            Highlighter hilit;
            Highlighter.HighlightPainter painter;
            hilit = new DefaultHighlighter();        
            Color color = new Color(100, 100, 100);
            painter = new DefaultHighlighter.DefaultHighlightPainter(color);
            textFound.setHighlighter(hilit);        
            textFound.setText(text);                        
            for(Token tk : foundedWords){
                try {            
                    hilit.addHighlight(tk.getBegin(),tk.getEnd() ,painter);
                    textFound.setCaretPosition(tk.getEnd());
                } catch (BadLocationException ex) {
                    ex.printStackTrace(System.out);
                }
            }    
        }
        else{
            JOptionPane.showMessageDialog(null, "Ninguna coincidencia encontrada", "No encontrado", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_buttonSearchActionPerformed
    
    private void lookForChanges(){
        FileText fileText = FileText.getFileText();
        String text = this.textEditor.getText();
        String fileInfo = this.labelFile.getText();
        fileInfo = fileInfo.replace("*", "");
        if(fileText.hasChanged(text)){
             fileInfo += "*";            
        }        
        this.labelFile.setText(fileInfo);
    }
    private void analizeSintaxis(){
        ArrayList<Token> tokens = Automaton.getAutomaton().getTokens();
        ArrayList<Token> errors = Automaton.getAutomaton().getErrors();
        if(tokens.size() > 0 && errors.size() == 0){
            PDA automaton = PDA.getAutomaton(tokens);
            automaton.analize();
            String error = automaton.getError();
            if(error != ""){
                this.textFound.setText(error);
                showTextFound();
            }
            else{
                //automaton.printTree();
                FileText ft = FileText.getFileText();
                File fl = ft.getNewFile();                
                String text = automaton.runCode();                
                if(fl != null){
                    String info = "Error";
                    if(ft.saveFile(text, fl)){
                        info = "Guardado con ??xito en:\n"+fl.getPath();                        
                    }
                    JOptionPane.showMessageDialog(null, info);
                }                
            }
        }else{
            JOptionPane.showMessageDialog(null, "El an??lisis l??xico debe completarse (sin errores)");
        }
    }
    
    private boolean newFile(){
        boolean goNextStep = false;
        FileText ft = FileText.getFileText();
        //boolean fl = ft.thereIsFile();
        int op = 1; // No
        if(ft.getChanged()){
            op = JOptionPane.showConfirmDialog(null, "Tiene cambios sin guardar. ??Desea guardarlos?");
            if(op == 0){
                ft.save();                
            }                             
        }
        if(op != 2){
            ft.createNew();
            this.textEditor.setText("");
            this.labelFile.setText("File: ");
            goNextStep = true;
        }     
        return goNextStep;
    }
    
    private void saveAs(){
        FileText ft = FileText.getFileText();
        if(ft.thereIsFile()){
           ft.saveAs();
        }
    }
    private void undone(){
        FileText ft = FileText.getFileText();                
        String text = ft.getPreviusText();
        this.textEditor.setText(text);
        ft.changePrevAndNext();
        lookForChanges();
    }
    private void buttonAnalizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAnalizeActionPerformed
        // TODO add your handling code here:  
        analize();
        //analizeTest();
    }//GEN-LAST:event_buttonAnalizeActionPerformed

    private void menuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveActionPerformed
        // TODO add your handling code here:
        saveFile();        
    }//GEN-LAST:event_menuSaveActionPerformed

    private void buttonAnalize1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAnalize1ActionPerformed
        // TODO add your handling code here:
        analizeSintaxis();
    }//GEN-LAST:event_buttonAnalize1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        newFile();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        saveAs();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        undone();
    }//GEN-LAST:event_jMenuItem3ActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAnalize;
    private javax.swing.JButton buttonAnalize1;
    private javax.swing.JButton buttonCloseTextFound;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelCords;
    private javax.swing.JLabel labelFile;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuOpen;
    private javax.swing.JMenuItem menuSave;
    private javax.swing.JTextArea textEditor;
    private javax.swing.JTextArea textFound;
    private javax.swing.JTextField textWordSearch;
    // End of variables declaration//GEN-END:variables
}

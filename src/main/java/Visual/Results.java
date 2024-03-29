/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import Model.Statistics;
import Visual.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel
 */
public class Results extends javax.swing.JFrame {
//  private ArrayList<Statistics> statisticsGeneral;
//  private ArrayList<Statistics> statisticsSpammer;
//  private ArrayList<Statistics> statisticsPublic;
//  private ArrayList<Statistics> statisticsRedes;
//  private ArrayList<Statistics> statisticsInform;
//  private ArrayList<Statistics> statisticsUsuarios;
//  private DefaultTableModel tableModelResultadosGenerales;
//  private DefaultTableModel tableModelResultadosSpammer;
//  private DefaultTableModel tableModelResultadosInform;
//  private DefaultTableModel tableModelResultadosPublic;
//  private DefaultTableModel tableModelResultadosRedes;
//  private DefaultTableModel tableModelResultadosUsuarios;
  
  private DefaultTableModel tableModelResults;
  
  private ArrayList<Statistics> statistics;     
    
  private static ArrayList<String> sourceNames;
  
  private static ArrayList<String> clasifierNames;
  
  private static ArrayList<GeneralMeasures> generalMeasures;

   
    
  
  
    /**
     * Creates new form Results
     */
    public Results() {
        initComponents();

        statistics = new ArrayList<>();
        sourceNames = new ArrayList<>();
        generalMeasures = new ArrayList<>();
        clasifierNames = new ArrayList<>();
       
        rellenarListas();
        fillTables();
        setLocationRelativeTo(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton2.setText("Atrás");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Tabla de resultados");

        jTable1.setModel(getDefaultTableModelResultadosGenerales());
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Guardar Resultados...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Graficar resultados");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(717, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(115, 115, 115)
                        .addComponent(jButton1)
                        .addGap(52, 52, 52))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new Browser(null,2,"data"+File.separator+"Simulaciones"+File.separator,new Action() {
            @Override public void actionPerformed(String path) {
                try {
                    FileWriter fw=new FileWriter(path+".txt");
                    BufferedWriter bf=new BufferedWriter(fw);
                    PrintWriter text=new PrintWriter(bf);
                    String cuerpo="";
                    for(int i=0;i<generalMeasures.size();i++){
                        cuerpo=cuerpo+"Clasificador: " + generalMeasures.get(i).getClasifierName() + " - Fuente:" +generalMeasures.get(i).getSource()+" - "+"Cantidad de correos:" +generalMeasures.get(i).getCantEmails()+" - "+"VP:" +generalMeasures.get(i).getCantTP()+" - "
                                +"VN:" +generalMeasures.get(i).getCantTN()+" - "+"FP:" +generalMeasures.get(i).getCantFP()+" - "+"FN:" +generalMeasures.get(i).getCantFN()+" - "
                                +"Exactitud:" +generalMeasures.get(i).getAccuracy()+" - "+"Precision:" +generalMeasures.get(i).getPrecision()+" - "+"Cobertura:" +generalMeasures.get(i).getRecall()+"\n";
                    }
                    text.print(cuerpo);
                    text.close();
                    
                } catch (IOException ex) {
                    Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        }).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new Barra().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Results().setVisible(true);
            }
        });
    }
    public void rellenarListas(){
        extractSourceNames();
        extractClasifierName();
        for (int i = 0; i < clasifierNames.size(); i++) {
            for (int j = 0; j < sourceNames.size(); j++) {
                calculateGeneralMeasures(sourceNames.get(j), clasifierNames.get(i));
            }            
        }
    }
     private DefaultTableModel getDefaultTableModelResultadosGenerales() {
        if (tableModelResults == null) {
            
            Object[] object;
            object = new Object[]{"Clasificador","Fuente","Cantidad de correos", "VP", "VN", "FP", "FN", "Exactitud", "Precisión", "Cobertura"};
            
            tableModelResults = new DefaultTableModel(object, 0);
            
        }
        return tableModelResults;
    }

     private void fillTables() {
        
        tableModelResults.setRowCount(0);
        for (int i = 0; i < generalMeasures.size(); i++) {        
            tableModelResults.addRow(new Object[]{generalMeasures.get(i).getClasifierName(), generalMeasures.get(i).getSource(), generalMeasures.get(i).getCantEmails(), generalMeasures.get(i).getCantTP(), generalMeasures.get(i).getCantTN(), generalMeasures.get(i).getCantFP(), generalMeasures.get(i).getCantFN(), generalMeasures.get(i).getAccuracy(),generalMeasures.get(i).getPrecision(), generalMeasures.get(i).getRecall()});    
        }               
    }
       
        private void extractSourceNames(){
            statistics = Principal.getStatistics();
            
            for (int i = 0; i < statistics.size(); i++) {
                if(sourceNames.isEmpty()){
                    sourceNames.add(statistics.get(i).getSource());                    
                } else {
                    if(!sourceNames.contains(statistics.get(i).getSource())){
                        sourceNames.add(statistics.get(i).getSource());
                    }
                }
            }       
        }
        
        private void extractClasifierName(){
            statistics = Principal.getStatistics();
            
            for (int i = 0; i < statistics.size(); i++) {
                if(clasifierNames.isEmpty()){
                    clasifierNames.add(statistics.get(i).getClasifierName());                    
                } else {
                    if(!clasifierNames.contains(statistics.get(i).getClasifierName())){
                        clasifierNames.add(statistics.get(i).getClasifierName());
                    }
                }
            }  
        }
        
//        private void calculateGeneralMeasures(String source){
//            int cantEmails = 0;
//            int TP = 0;
//            int TN = 0;
//            int FP = 0;
//            int FN = 0;
//            float precision = 0;
//            float accuracy = 0;
//            float recall = 0;    
//            int count = 0;
//            GeneralMeasures generalMeasure;
//            for (int i = 0; i < statistics.size(); i++) {
//                if(statistics.get(i).getSource().equalsIgnoreCase(source)){
//                   count++;
//                   TP += statistics.get(i).getTP();
//                   TN += statistics.get(i).getTN();
//                   FP += statistics.get(i).getFP();
//                   FN += statistics.get(i).getFN();
//                   precision += statistics.get(i).getPrecision();
//                   accuracy += statistics.get(i).getAccuracy();
//                   recall += statistics.get(i).getRecall();
//                }                
//            }
//            precision = precision/count;
//            accuracy = accuracy/count;
//            recall = recall/count;
//            cantEmails = TP + TN + FP + FN;
//            generalMeasure = new GeneralMeasures(source, cantEmails, TP, TN, FP, FN, precision, recall, accuracy);
//            generalMeasures.add(generalMeasure);
//        }
        
        private void calculateGeneralMeasures(String source, String clasifierName){
            int cantEmails = 0;
            int TP = 0;
            int TN = 0;
            int FP = 0;
            int FN = 0;
            float precision = 0;
            float accuracy = 0;
            float recall = 0;    
            int count = 0;
            GeneralMeasures generalMeasure;
            for (int i = 0; i < statistics.size(); i++) {
                if(statistics.get(i).getSource().equalsIgnoreCase(source) && statistics.get(i).getClasifierName().equalsIgnoreCase(clasifierName)){
                   count++;
                   TP += statistics.get(i).getTP();
                   TN += statistics.get(i).getTN();
                   FP += statistics.get(i).getFP();
                   FN += statistics.get(i).getFN();
                   precision += statistics.get(i).getPrecision();
                   accuracy += statistics.get(i).getAccuracy();
                   recall += statistics.get(i).getRecall();
                }                
            }
            precision = precision/count;
            accuracy = accuracy/count;
            recall = recall/count;
            cantEmails = TP + TN + FP + FN;
            generalMeasure = new GeneralMeasures(source, cantEmails, TP, TN, FP, FN, precision, recall, accuracy, clasifierName);
            generalMeasures.add(generalMeasure);
        }
        
    public static ArrayList<String> getSourceNames() {
        return sourceNames;
    }

    public static ArrayList<GeneralMeasures> getGeneralMeasures() {
        return generalMeasures;
    }

    public static ArrayList<String> getClasifierNames() {
        return clasifierNames;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

package Visual;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Browser extends javax.swing.JDialog {
    private Action action;
   
    public Browser(JFrame parent,int mode,String path,Action action) {
        super(parent,true);
        initComponents();
        this.action = action;
        this.fileChooser.setFileSelectionMode(mode);
        this.fileChooser.setCurrentDirectory(new File(path));
        this.setLocationRelativeTo(parent);
    }
    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser(){
            public void cancelSelection() {
                Browser.this.dispose();
            }
            public void approveSelection(){
                File f = getSelectedFile();
                if(f.exists() && getDialogType() == SAVE_DIALOG){
                    int result = JOptionPane.showConfirmDialog(this,"The file exists, overwrite ?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    switch(result){
                        case JOptionPane.YES_OPTION:
                        super.approveSelection();
                        return;
                        case JOptionPane.NO_OPTION:
                        return;
                        case JOptionPane.CLOSED_OPTION:
                        return;
                        case JOptionPane.CANCEL_OPTION:
                        cancelSelection();
                        return;
                    }
                }
                super.approveSelection();
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        fileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserActionPerformed(evt);
            }
        });
        getContentPane().add(fileChooser);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserActionPerformed
        if(fileChooser.isAcceptAllFileFilterUsed()){
            action.actionPerformed(fileChooser.getSelectedFile().getPath());
        }
        this.dispose();
    }//GEN-LAST:event_fileChooserActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileChooser;
    // End of variables declaration//GEN-END:variables
    
}



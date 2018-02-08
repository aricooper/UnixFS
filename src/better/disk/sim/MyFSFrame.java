package better.disk.sim;

/**
 *
 * @author levenick
 */
public class MyFSFrame extends javax.swing.JFrame {
    FileSystem theFileSystem;
    
    /** Creates new form MyFSFrame */
    public MyFSFrame() {
        initComponents();
        setSize(800,500);
        theFileSystem = new FileSystem();
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        inTA = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        outTA = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        loadItem = new javax.swing.JMenuItem();
        saveItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        inTA.setColumns(20);
        inTA.setRows(5);
        jScrollPane1.setViewportView(inTA);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(120, 90, 260, 210);

        outTA.setColumns(20);
        outTA.setRows(5);
        jScrollPane2.setViewportView(outTA);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(390, 90, 240, 210);

        jLabel1.setText("IN");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(240, 70, 20, 16);

        jLabel2.setText("OUT");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(500, 70, 50, 16);

        jMenu1.setText("File");

        loadItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        loadItem.setText("Load");
        loadItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadItemActionPerformed(evt);
            }
        });
        jMenu1.add(loadItem);

        saveItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveItem.setText("Save");
        saveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveItemActionPerformed(evt);
            }
        });
        jMenu1.add(saveItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadItemActionPerformed
        System.out.println("load!");
        outTA.setText(theFileSystem.load());
                    }//GEN-LAST:event_loadItemActionPerformed

    private void saveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveItemActionPerformed
        theFileSystem.save(inTA.getText());
    }//GEN-LAST:event_saveItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea inTA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem loadItem;
    private javax.swing.JTextArea outTA;
    private javax.swing.JMenuItem saveItem;
    // End of variables declaration//GEN-END:variables

}

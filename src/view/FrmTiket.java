package view;

import dao.ObjekWisataDAO;
import dao.PetugasDAO;
import dao.TiketDAO;
import model.ObjekWisata;
import model.User;
import model.Petugas;
import model.Tiket;

import javax.swing.JOptionPane;
import java.text.NumberFormat;
import java.util.*;

public class FrmTiket extends javax.swing.JFrame {

    // User
    private User userLogin;

    // DAO
    private ObjekWisataDAO wisataDAO = new ObjekWisataDAO();
    private PetugasDAO petugasDAO = new PetugasDAO();
    private TiketDAO tiketDAO = new TiketDAO();

    // Map bantu
    private Map<String, Integer> wisataMap = new HashMap<>();
    private Map<Integer, Double> hargaMap = new HashMap<>();
    private Map<String, Integer> petugasMap = new HashMap<>();

    public FrmTiket() {
        initComponents();
        setLocationRelativeTo(null);
        loadWisata();
        loadPetugas();
        DateChooser.setDate(new Date());

        // hitung otomatis saat ganti wisata
        cmbWisata.addActionListener(e -> hitungOtomatis());

        // hitung otomatis saat jumlah diketik
        txtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hitungOtomatis();
            }
        });
    }

    public FrmTiket(User user) {
        this.userLogin = user;
        initComponents();
        setLocationRelativeTo(null);
    }

    // ================= LOAD DATA =================
    private void loadWisata() {
        try {
            cmbWisata.removeAllItems();
            List<ObjekWisata> list = wisataDAO.getAll();

            for (ObjekWisata ow : list) {
                cmbWisata.addItem(ow.getNama());
                wisataMap.put(ow.getNama(), ow.getIdWisata());
                hargaMap.put(ow.getIdWisata(), ow.getHargaTiket());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal load wisata: " + e.getMessage());
        }
    }

    private void loadPetugas() {
        try {
            cmbPetugas.removeAllItems();
            List<Petugas> list = petugasDAO.getAll();

            for (Petugas p : list) {
                cmbPetugas.addItem(p.getNamaPetugas());
                petugasMap.put(p.getNamaPetugas(), p.getIdPetugas());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal load petugas: " + e.getMessage());
        }
    }

    // ================= LOGIKA HITUNG =================
    private void hitungOtomatis() {
        try {
            if (txtJumlah.getText().isEmpty()) {
                txtTotal.setText("");
                return;
            }

            int jumlah = Integer.parseInt(txtJumlah.getText());
            String namaWisata = cmbWisata.getSelectedItem().toString();
            int idWisata = wisataMap.get(namaWisata);
            double harga = hargaMap.get(idWisata);

            double total = jumlah * harga;
            txtTotal.setText(formatRupiah(total));
        } catch (Exception e) {
            // abaikan jika input belum valid
        }
    }

    private String formatRupiah(double nilai) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(localeID);
        return rupiah.format(nilai);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        cmbWisata = new javax.swing.JComboBox<>();
        cmbPetugas = new javax.swing.JComboBox<>();
        btnSimpan = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        DateChooser = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Transaksi Tiket");

        jPanel1.setBackground(new java.awt.Color(186, 226, 241));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Transaksi Tiket Wisata");
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Wisata");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Petugas");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Jumlah Tiket");

        cmbWisata.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbPetugas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSimpan.setBackground(new java.awt.Color(253, 211, 88));
        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save_16dp_000000.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Tanggal");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Total harga");

        txtTotal.setEditable(false);

        jButton1.setBackground(new java.awt.Color(253, 211, 88));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout_16dp_000000_FILL0_wght400_GRAD0_opsz20.png"))); // NOI18N
        jButton1.setText("Kembali");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnCetak.setBackground(new java.awt.Color(253, 211, 88));
        btnCetak.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/print_16dp_000000_FILL0_wght400_GRAD0_opsz20.png"))); // NOI18N
        btnCetak.setText("Cetak Struk");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbWisata, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbPetugas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtJumlah)
                                    .addComponent(DateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                    .addComponent(txtTotal)))
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                            .addComponent(btnCetak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbWisata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(DateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCetak)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new FrmMenuUtama(userLogin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        try {
            Tiket t = new Tiket();

            String namaWisata = cmbWisata.getSelectedItem().toString();
            String namaPetugas = cmbPetugas.getSelectedItem().toString();

            t.setIdWisata(wisataMap.get(namaWisata));
            t.setIdPetugas(petugasMap.get(namaPetugas));
            t.setJumlah(Integer.parseInt(txtJumlah.getText()));
            t.setTanggal(DateChooser.getDate());

            // hapus format Rupiah sebelum simpan
            String totalText = txtTotal.getText()
                    .replace("Rp", "")
                    .replace(".", "")
                    .replace(",", "")
                    .trim();

            t.setTotal(Double.parseDouble(totalText));

            tiketDAO.insert(t);

            JOptionPane.showMessageDialog(this, "Transaksi tiket berhasil disimpan!");
            clearForm();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal simpan tiket: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        // TODO add your handling code here:
        try {
            String wisata = cmbWisata.getSelectedItem().toString();
            String petugas = cmbPetugas.getSelectedItem().toString();
            String jumlah = txtJumlah.getText();
            String total = txtTotal.getText();
            String tanggal = new java.text.SimpleDateFormat("dd-MM-yyyy")
                    .format(DateChooser.getDate());

            String struk
                    = "WISATA GUNUNG MAMAKE\n"
                    + "============================\n"
                    + "Tanggal  : " + tanggal + "\n"
                    + "Wisata   : " + wisata + "\n"
                    + "Petugas  : " + petugas + "\n"
                    + "Jumlah   : " + jumlah + " tiket\n"
                    + "----------------------------\n"
                    + "TOTAL    : " + total + "\n"
                    + "============================\n"
                    + "Terima kasih 🙏\n";

            javax.swing.JTextArea ta = new javax.swing.JTextArea(struk);
            ta.print(); // langsung ke printer / PDF

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal cetak struk");
        }
    }//GEN-LAST:event_btnCetakActionPerformed

    private void clearForm() {
        txtJumlah.setText("");
        txtTotal.setText("");
        DateChooser.setDate(new Date());
    }

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
            java.util.logging.Logger.getLogger(FrmTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmTiket().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateChooser;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cmbPetugas;
    private javax.swing.JComboBox<String> cmbWisata;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

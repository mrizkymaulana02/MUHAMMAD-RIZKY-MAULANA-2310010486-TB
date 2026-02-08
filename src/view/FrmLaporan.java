package view;

import dao.ObjekWisataDAO;
import dao.TransaksiDAO;
import model.ObjekWisata;
import model.User;
import model.Transaksi;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JTextArea;
import java.sql.Statement;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.util.HashMap;
import java.util.Map;

public class FrmLaporan extends javax.swing.JFrame {

    private User userLogin;
    private ObjekWisataDAO wisataDAO = new ObjekWisataDAO();
    private TransaksiDAO transaksiDAO = new TransaksiDAO();

    public FrmLaporan() {
        initComponents();
        setLocationRelativeTo(null);
        loadDataWisata();
        loadDataTransaksi();
        loadDataPendapatan();
    }

    public FrmLaporan(User user) {
        this.userLogin = user;
        initComponents();
        setLocationRelativeTo(null);
    }

    private void loadDataWisata() {
        try {
            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"ID", "Nama", "Lokasi", "Kategori", "Harga Tiket"}, 0
            );

            List<ObjekWisata> list = wisataDAO.getAll();
            for (ObjekWisata w : list) {
                model.addRow(new Object[]{
                    w.getIdWisata(),
                    w.getNama(),
                    w.getLokasi(),
                    w.getIdKategori(),
                    formatRupiah(w.getHargaTiket())
                });
            }

            tblWisata.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data wisata: " + e.getMessage());
        }
    }

    private void loadDataTransaksi() {
        try {
            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"ID Transaksi", "ID Wisata", "Tanggal", "Jumlah Pengunjung", "Total Pembayaran"}, 0
            );

            List<Transaksi> list = transaksiDAO.getAll();

            for (Transaksi t : list) {
                model.addRow(new Object[]{
                    t.getIdTransaksi(),
                    t.getIdWisata(),
                    t.getTanggal(),
                    t.getJumlahPengunjung(),
                    formatRupiah(t.getTotalPembayaran())
                });
            }

            jTable1.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data transaksi: " + e.getMessage());
        }
    }

    private void loadDataPendapatan() {
        try {
            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"Nama Wisata", "Total Pendapatan", "TOTAL_ASLI"}, 0
            );

            String sql
                    = "SELECT ow.nama AS nama, SUM(t.total_pembayaran) AS total "
                    + "FROM transaksi t "
                    + "JOIN objek_wisata ow ON t.id_wisata = ow.id_wisata "
                    + "GROUP BY ow.nama";

            Connection conn = database.Koneksi.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("nama"),
                    formatRupiah(rs.getDouble("total")),
                    rs.getDouble("total") // angka asli
                });
            }

            tblPendapatan.setModel(model);

            // sembunyikan kolom TOTAL_ASLI
            tblPendapatan.getColumnModel().getColumn(2).setMinWidth(0);
            tblPendapatan.getColumnModel().getColumn(2).setMaxWidth(0);
            tblPendapatan.getColumnModel().getColumn(2).setWidth(0);

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal memuat pendapatan: " + e.getMessage());
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
        tabPane = new javax.swing.JTabbedPane();
        panelWisata = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblWisata = new javax.swing.JTable();
        btnRefreshWisata = new javax.swing.JButton();
        panelTransaksi = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnRefreshTransaksi = new javax.swing.JButton();
        panelPendapatan = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPendapatan = new javax.swing.JTable();
        btnRefreshPendapatan = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        btnKembali = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        btnFilter = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();
        btnExportCSV = new javax.swing.JButton();
        btnCetak1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Laporan");

        jPanel1.setBackground(new java.awt.Color(175, 217, 69));

        tabPane.setBackground(new java.awt.Color(189, 229, 245));

        panelWisata.setBackground(new java.awt.Color(175, 217, 69));

        tblWisata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblWisata);

        btnRefreshWisata.setBackground(new java.awt.Color(250, 213, 17));
        btnRefreshWisata.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnRefreshWisata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh_16dp_000000_FILL0_wght400_GRAD0_opsz20.png"))); // NOI18N
        btnRefreshWisata.setText("Refresh data wisata");
        btnRefreshWisata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshWisataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelWisataLayout = new javax.swing.GroupLayout(panelWisata);
        panelWisata.setLayout(panelWisataLayout);
        panelWisataLayout.setHorizontalGroup(
            panelWisataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
            .addComponent(btnRefreshWisata, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelWisataLayout.setVerticalGroup(
            panelWisataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelWisataLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefreshWisata)
                .addContainerGap())
        );

        tabPane.addTab("Wisata", panelWisata);

        panelTransaksi.setBackground(new java.awt.Color(175, 217, 69));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        btnRefreshTransaksi.setBackground(new java.awt.Color(250, 213, 17));
        btnRefreshTransaksi.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnRefreshTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh_16dp_000000_FILL0_wght400_GRAD0_opsz20.png"))); // NOI18N
        btnRefreshTransaksi.setText("Refresh data transaksi");
        btnRefreshTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshTransaksiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTransaksiLayout = new javax.swing.GroupLayout(panelTransaksi);
        panelTransaksi.setLayout(panelTransaksiLayout);
        panelTransaksiLayout.setHorizontalGroup(
            panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
            .addComponent(btnRefreshTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelTransaksiLayout.setVerticalGroup(
            panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransaksiLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefreshTransaksi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabPane.addTab("Transaksi", panelTransaksi);

        panelPendapatan.setBackground(new java.awt.Color(175, 217, 69));

        tblPendapatan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblPendapatan);

        btnRefreshPendapatan.setBackground(new java.awt.Color(250, 213, 17));
        btnRefreshPendapatan.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnRefreshPendapatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh_16dp_000000_FILL0_wght400_GRAD0_opsz20.png"))); // NOI18N
        btnRefreshPendapatan.setText("Refresh Data Pendapatan");
        btnRefreshPendapatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshPendapatanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPendapatanLayout = new javax.swing.GroupLayout(panelPendapatan);
        panelPendapatan.setLayout(panelPendapatanLayout);
        panelPendapatanLayout.setHorizontalGroup(
            panelPendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
            .addComponent(btnRefreshPendapatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelPendapatanLayout.setVerticalGroup(
            panelPendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPendapatanLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(btnRefreshPendapatan)
                .addContainerGap())
        );

        tabPane.addTab("Pendapatan", panelPendapatan);

        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblTitle.setText("Data & Statistik WISATA GUNUNG MAMAKE ");
        lblTitle.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnKembali.setBackground(new java.awt.Color(250, 213, 17));
        btnKembali.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnKembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout_16dp_000000_FILL0_wght400_GRAD0_opsz20.png"))); // NOI18N
        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Dari ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Sampai");

        btnFilter.setBackground(new java.awt.Color(189, 229, 245));
        btnFilter.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/filter_list_16dp_000000_FILL0_wght400_GRAD0_opsz20.png"))); // NOI18N
        btnFilter.setText("Filter");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(189, 229, 245));
        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clear_all_16dp_000000_FILL0_wght400_GRAD0_opsz20.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnCetak.setBackground(new java.awt.Color(189, 229, 245));
        btnCetak.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/print_16dp_000000_FILL0_wght400_GRAD0_opsz20.png"))); // NOI18N
        btnCetak.setText("Cetak Laporan Transaksi");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        btnExportCSV.setBackground(new java.awt.Color(189, 229, 245));
        btnExportCSV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExportCSV.setText("Export CSV");
        btnExportCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportCSVActionPerformed(evt);
            }
        });

        btnCetak1.setBackground(new java.awt.Color(189, 229, 245));
        btnCetak1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCetak1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/print_16dp_000000_FILL0_wght400_GRAD0_opsz20.png"))); // NOI18N
        btnCetak1.setText("Cetak Laporan Wisata");
        btnCetak1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetak1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPane, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(lblTitle))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(24, 24, 24)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCetak1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCetak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnKembali, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExportCSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(422, 422, 422)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnReset)
                            .addComponent(btnCetak1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tabPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(btnFilter))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCetak)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(btnExportCSV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKembali)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshWisataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshWisataActionPerformed
        // TODO add your handling code here:
        loadDataWisata();
    }//GEN-LAST:event_btnRefreshWisataActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        new FrmMenuUtama(userLogin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnRefreshTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshTransaksiActionPerformed
        // TODO add your handling code here:
        loadDataTransaksi();
    }//GEN-LAST:event_btnRefreshTransaksiActionPerformed

    private void btnRefreshPendapatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshPendapatanActionPerformed
        // TODO add your handling code here:
        loadDataPendapatan();

    }//GEN-LAST:event_btnRefreshPendapatanActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        if (jDateChooser1.getDate() == null || jDateChooser2.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Tanggal harus diisi!");
            return;
        }

        try {
            java.sql.Date tglAwal = new java.sql.Date(jDateChooser1.getDate().getTime());
            java.sql.Date tglAkhir = new java.sql.Date(jDateChooser2.getDate().getTime());

            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"Nama Wisata", "Total Pendapatan", "TOTAL_ASLI"}, 0
            );

            String sql = """
            SELECT ow.nama AS nama,
                   IFNULL(SUM(t.total_pembayaran),0) AS total
            FROM transaksi t
            JOIN objek_wisata ow ON t.id_wisata = ow.id_wisata
            WHERE t.tanggal BETWEEN ? AND ?
            GROUP BY ow.nama
        """;

            java.sql.Connection conn = database.Koneksi.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, tglAwal);
            ps.setDate(2, tglAkhir);

            java.sql.ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                double total = rs.getDouble("total");
                model.addRow(new Object[]{
                    rs.getString("nama"),
                    formatRupiah(total),
                    total
                });
            }

            tblPendapatan.setModel(model);

            // sembunyikan kolom TOTAL_ASLI
            tblPendapatan.getColumnModel().getColumn(2).setMinWidth(0);
            tblPendapatan.getColumnModel().getColumn(2).setMaxWidth(0);
            tblPendapatan.getColumnModel().getColumn(2).setWidth(0);

            rs.close();
            ps.close();
            conn.close();

            if (tblPendapatan.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal filter data: " + e.getMessage());
        }
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        try {
            JasperReport jr = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/report/laporan_transaksi.jrxml")
            );

            HashMap<String, Object> param = new HashMap<>();
            param.put("tgl_awal", jDateChooser1.getDate());
            param.put("tgl_akhir", jDateChooser2.getDate());

            JasperPrint jp = JasperFillManager.fillReport(
                    jr,
                    param,
                    database.Koneksi.getConnection()
            );

            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal mencetak laporan: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCetakActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        loadDataPendapatan();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnExportCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportCSVActionPerformed

        if (tblPendapatan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada data untuk diekspor!");
            return;
        }

        try {
            javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
            chooser.setDialogTitle("Simpan Laporan CSV");

            int userSelection = chooser.showSaveDialog(this);
            if (userSelection != javax.swing.JFileChooser.APPROVE_OPTION) {
                return;
            }

            java.io.File file = chooser.getSelectedFile();
            if (!file.getName().endsWith(".csv")) {
                file = new java.io.File(file.getAbsolutePath() + ".csv");
            }

            java.io.FileWriter fw = new java.io.FileWriter(file);
            java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);

            // Header CSV
            bw.write("Nama Wisata,Total Pendapatan");
            bw.newLine();

            javax.swing.table.DefaultTableModel model
                    = (javax.swing.table.DefaultTableModel) tblPendapatan.getModel();

            for (int i = 0; i < model.getRowCount(); i++) {
                String nama = model.getValueAt(i, 0).toString();
                String total = model.getValueAt(i, 1).toString();
                bw.write(nama + "," + total);
                bw.newLine();
            }

            bw.close();
            fw.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Laporan berhasil diekspor ke CSV:\n" + file.getAbsolutePath()
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal ekspor CSV: " + e.getMessage());
        }


    }//GEN-LAST:event_btnExportCSVActionPerformed

    private void btnCetak1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetak1ActionPerformed
        // TODO add your handling code here:
        try {
            JasperReport jr = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/report/laporan_wisata.jrxml")
            );

            // laporan wisata TIDAK pakai parameter tanggal
            Map<String, Object> param = new HashMap<>();

            JasperPrint jp = JasperFillManager.fillReport(
                    jr,
                    param,
                    database.Koneksi.getConnection()
            );

            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal mencetak laporan wisata: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCetak1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLaporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnCetak1;
    private javax.swing.JButton btnExportCSV;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnRefreshPendapatan;
    private javax.swing.JButton btnRefreshTransaksi;
    private javax.swing.JButton btnRefreshWisata;
    private javax.swing.JButton btnReset;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel panelPendapatan;
    private javax.swing.JPanel panelTransaksi;
    private javax.swing.JPanel panelWisata;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JTable tblPendapatan;
    private javax.swing.JTable tblWisata;
    // End of variables declaration//GEN-END:variables
}

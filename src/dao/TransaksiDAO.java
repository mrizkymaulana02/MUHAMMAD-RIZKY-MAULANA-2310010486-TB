package dao;

import database.Koneksi;
import model.Transaksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {

    public void insert(Transaksi t) throws SQLException {
        String sql = "INSERT INTO transaksi (id_wisata, tanggal, jumlah_pengunjung, total_pembayaran) VALUES (?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, t.getIdWisata());
            ps.setDate(2, new java.sql.Date(t.getTanggal().getTime()));
            ps.setInt(3, t.getJumlahPengunjung());
            ps.setDouble(4, t.getTotalPembayaran());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM transaksi WHERE id_transaksi=?";
        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Transaksi> getAll() throws SQLException {
        List<Transaksi> list = new ArrayList<>();
        String sql = "SELECT * FROM transaksi";
        try (Connection conn = Koneksi.getConnection(); Statement s = conn.createStatement(); ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                Transaksi t = new Transaksi();
                t.setIdTransaksi(rs.getInt("id_transaksi"));
                t.setIdWisata(rs.getInt("id_wisata"));
                t.setTanggal(rs.getDate("tanggal"));
                t.setJumlahPengunjung(rs.getInt("jumlah_pengunjung"));
                t.setTotalPembayaran(rs.getDouble("total_pembayaran"));
                list.add(t);
            }
        }
        return list;
    }
}

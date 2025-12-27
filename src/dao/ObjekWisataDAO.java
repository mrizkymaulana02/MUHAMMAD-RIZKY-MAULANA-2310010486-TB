package dao;

import database.Koneksi;
import model.ObjekWisata;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObjekWisataDAO {

    // === INSERT DATA WISATA ===
    public void insert(ObjekWisata ow) throws SQLException {
        String sql = "INSERT INTO objek_wisata (nama, lokasi, deskripsi, id_kategori, harga_tiket) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ow.getNama());
            ps.setString(2, ow.getLokasi());
            ps.setString(3, ow.getDeskripsi());
            ps.setInt(4, ow.getIdKategori());
            ps.setDouble(5, ow.getHargaTiket());
            ps.executeUpdate();
        }
    }

    // === UPDATE DATA WISATA ===
    public void update(ObjekWisata ow) throws SQLException {
        String sql = "UPDATE objek_wisata SET nama=?, lokasi=?, deskripsi=?, id_kategori=?, harga_tiket=? WHERE id_wisata=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ow.getNama());
            ps.setString(2, ow.getLokasi());
            ps.setString(3, ow.getDeskripsi());
            ps.setInt(4, ow.getIdKategori());
            ps.setDouble(5, ow.getHargaTiket());
            ps.setInt(6, ow.getIdWisata());
            ps.executeUpdate();
        }
    }

    // === HAPUS DATA WISATA ===
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM objek_wisata WHERE id_wisata=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // === AMBIL SEMUA DATA WISATA ===
    public List<ObjekWisata> getAll() throws SQLException {
        String sql = "SELECT o.id_wisata, o.nama, o.lokasi, o.deskripsi, o.id_kategori, o.harga_tiket, k.nama_kategori " +
                     "FROM objek_wisata o JOIN kategori k ON o.id_kategori = k.id_kategori";
        List<ObjekWisata> list = new ArrayList<>();
        try (Connection conn = Koneksi.getConnection();
             Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql)) {

            while (rs.next()) {
                ObjekWisata ow = new ObjekWisata();
                ow.setIdWisata(rs.getInt("id_wisata"));
                ow.setNama(rs.getString("nama"));
                ow.setLokasi(rs.getString("lokasi"));
                ow.setDeskripsi(rs.getString("deskripsi"));
                ow.setIdKategori(rs.getInt("id_kategori"));
                ow.setHargaTiket(rs.getDouble("harga_tiket"));
                list.add(ow);
            }
        }
        return list;
    }

    // === AMBIL DATA BERDASARKAN ID ===
    public ObjekWisata getById(int id) throws SQLException {
        String sql = "SELECT * FROM objek_wisata WHERE id_wisata=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ObjekWisata ow = new ObjekWisata();
                    ow.setIdWisata(rs.getInt("id_wisata"));
                    ow.setNama(rs.getString("nama"));
                    ow.setLokasi(rs.getString("lokasi"));
                    ow.setDeskripsi(rs.getString("deskripsi"));
                    ow.setIdKategori(rs.getInt("id_kategori"));
                    ow.setHargaTiket(rs.getDouble("harga_tiket"));
                    return ow;
                }
            }
        }
        return null;
    }
}

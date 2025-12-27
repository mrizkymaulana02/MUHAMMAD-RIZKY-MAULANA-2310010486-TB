package dao;

import database.Koneksi;
import model.Kategori;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KategoriDAO {
    
    // INSERT
    public void insert(Kategori k) throws SQLException {
        String sql = "INSERT INTO kategori (nama_kategori) VALUES (?)";
        PreparedStatement pst = Koneksi.getConnection().prepareStatement(sql);
        pst.setString(1, k.getNamaKategori());
        pst.executeUpdate();
        pst.close();
    }

    // UPDATE
    public void update(Kategori k) throws SQLException {
        String sql = "UPDATE kategori SET nama_kategori=? WHERE id_kategori=?";
        PreparedStatement pst = Koneksi.getConnection().prepareStatement(sql);
        pst.setString(1, k.getNamaKategori());
        pst.setInt(2, k.getIdKategori());
        pst.executeUpdate();
        pst.close();
    }

    // DELETE
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM kategori WHERE id_kategori=?";
        PreparedStatement pst = Koneksi.getConnection().prepareStatement(sql);
        pst.setInt(1, id);
        pst.executeUpdate();
        pst.close();
    }

    // SELECT
    public List<Kategori> getAll() throws SQLException {
        List<Kategori> list = new ArrayList<>();
        String sql = "SELECT * FROM kategori";
        Statement st = Koneksi.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            list.add(new Kategori(rs.getInt("id_kategori"), rs.getString("nama_kategori")));
        }
        return list;
    }
}

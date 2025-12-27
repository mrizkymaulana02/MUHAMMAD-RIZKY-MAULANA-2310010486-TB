package dao;

import database.Koneksi;
import model.Petugas;
import java.sql.*;
import java.util.*;

public class PetugasDAO {

    public void insert(Petugas p) throws SQLException {
        String sql = "INSERT INTO petugas VALUES (NULL, ?, ?, ?)";
        PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql);
        ps.setString(1, p.getNamaPetugas());
        ps.setString(2, p.getJabatan());
        ps.setString(3, p.getNoHp());
        ps.executeUpdate();
    }

    public void update(Petugas p) throws SQLException {
        String sql = "UPDATE petugas SET nama_petugas=?, jabatan=?, no_hp=? WHERE id_petugas=?";
        PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql);
        ps.setString(1, p.getNamaPetugas());
        ps.setString(2, p.getJabatan());
        ps.setString(3, p.getNoHp());
        ps.setInt(4, p.getIdPetugas());
        ps.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        PreparedStatement ps = Koneksi.getConnection()
            .prepareStatement("DELETE FROM petugas WHERE id_petugas=?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public List<Petugas> getAll() throws SQLException {
        List<Petugas> list = new ArrayList<>();
        ResultSet rs = Koneksi.getConnection()
            .createStatement().executeQuery("SELECT * FROM petugas");
        while (rs.next()) {
            Petugas p = new Petugas();
            p.setIdPetugas(rs.getInt(1));
            p.setNamaPetugas(rs.getString(2));
            p.setJabatan(rs.getString(3));
            p.setNoHp(rs.getString(4));
            list.add(p);
        }
        return list;
    }
}

package dao;

import database.Koneksi;
import model.Tiket;
import java.sql.*;

public class TiketDAO {
    public void insert(Tiket t) throws SQLException {
        String sql = "INSERT INTO tiket VALUES (NULL, ?, ?, ?, ?, ?)";
        PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql);
        ps.setInt(1, t.getIdWisata());
        ps.setInt(2, t.getIdPetugas());
        ps.setDate(3, new java.sql.Date(t.getTanggal().getTime()));
        ps.setInt(4, t.getJumlah());
        ps.setDouble(5, t.getTotal());
        ps.executeUpdate();
    }
}

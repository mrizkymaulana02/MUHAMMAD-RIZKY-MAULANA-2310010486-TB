package dao;

import database.Koneksi;
import model.User;
import java.sql.*;

public class UserDAO {
    public User login(String u, String p) throws SQLException {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql);
        ps.setString(1, u);
        ps.setString(2, p);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setRole(rs.getString("role"));
            return user;
        }
        return null;
    }
}

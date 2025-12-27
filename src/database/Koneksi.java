package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static Connection conn;
    private static final String URL = "jdbc:mysql://localhost:3306/db_gunungmamake";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        try {
            // Pastikan koneksi selalu aktif
            if (conn == null || conn.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver"); // pastikan driver dimuat
                conn = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Koneksi Berhasil");
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC tidak ditemukan: " + e.getMessage());
        }
        return conn;
    }

    // Optional: bisa dipanggil saat aplikasi keluar
    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Koneksi ditutup");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menutup koneksi: " + e.getMessage());
        }
    }
}

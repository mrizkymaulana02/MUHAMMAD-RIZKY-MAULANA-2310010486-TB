package model;

import java.util.Date;

public class Transaksi {

    private int idTransaksi;
    private int idWisata;
    private String namaWisata; // 🔥 TAMBAHAN WAJIB
    private Date tanggal;
    private int jumlahPengunjung;
    private double totalPembayaran;

    // === GETTER & SETTER LAMA ===
    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public int getIdWisata() {
        return idWisata;
    }

    public void setIdWisata(int idWisata) {
        this.idWisata = idWisata;
    }

    // === GETTER & SETTER BARU (WAJIB) ===
    public String getNamaWisata() {
        return namaWisata;
    }

    public void setNamaWisata(String namaWisata) {
        this.namaWisata = namaWisata;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public int getJumlahPengunjung() {
        return jumlahPengunjung;
    }

    public void setJumlahPengunjung(int jumlahPengunjung) {
        this.jumlahPengunjung = jumlahPengunjung;
    }

    public double getTotalPembayaran() {
        return totalPembayaran;
    }

    public void setTotalPembayaran(double totalPembayaran) {
        this.totalPembayaran = totalPembayaran;
    }
}

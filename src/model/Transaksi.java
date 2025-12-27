package model;

import java.util.Date;

public class Transaksi {
    private int idTransaksi;
    private int idWisata;
    private Date tanggal;
    private int jumlahPengunjung;
    private double totalPembayaran;

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

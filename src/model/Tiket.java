package model;

import java.util.Date;

public class Tiket {
    private int idWisata, idPetugas, jumlah;
    private Date tanggal;
    private double total;

    public int getIdWisata() { return idWisata; }
    public void setIdWisata(int idWisata) { this.idWisata = idWisata; }

    public int getIdPetugas() { return idPetugas; }
    public void setIdPetugas(int idPetugas) { this.idPetugas = idPetugas; }

    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }

    public Date getTanggal() { return tanggal; }
    public void setTanggal(Date tanggal) { this.tanggal = tanggal; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}

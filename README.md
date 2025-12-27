# AplikasiWisataGunungMamake  
**Ujian Akhir Semester – Pemrograman Berbasis Objek 2**

## 📌 Informasi Mahasiswa
- **Nama** : Muhammad Rizky Maulana  
- **NPM** : 2310010486  
- **Kelas** : 5B Non Reguler BJM  
- **Mata Kuliah** : Pemrograman Berbasis Objek 2  
- **Jenis Tugas** : Tugas Besar / UAS  

---

## 🏞️ Deskripsi Aplikasi
Aplikasi **Wisata Gunung Mamake** merupakan aplikasi berbasis **Java GUI (Swing)** yang terintegrasi dengan **database MySQL**.  
Aplikasi ini digunakan untuk mengelola data objek wisata, transaksi penjualan tiket, serta laporan pendapatan wisata.

Aplikasi ini dibuat untuk memenuhi ketentuan **Ujian Akhir Semester (UAS)** dengan fitur:
- CRUD data
- Transaksi
- Reporting
- Login dan role user

---

## 🎯 Fitur Utama Aplikasi

### 🔐 Login
- Autentikasi pengguna
- Role user (Admin / Petugas)
- Proteksi akses menu

### 📂 Data Master
1. **Data Kategori**
   - Tambah, Edit, Hapus, Lihat kategori wisata

2. **Data Objek Wisata**
   - Nama wisata
   - Lokasi
   - Kategori
   - Harga tiket
   - Deskripsi
   - Harga ditampilkan dalam format Rupiah

3. **Data Petugas**
   - Mengelola user yang melakukan transaksi

### 🎟️ Transaksi
- Penjualan tiket wisata
- Perhitungan total otomatis
- Harga diambil dari master objek wisata
- Data transaksi tersimpan ke database

### 📊 Laporan
- Laporan Data Wisata
- Laporan Transaksi
- Laporan Pendapatan per Wisata
- Filter berdasarkan tanggal
- Cetak laporan ke **PDF**

---

## 🗂️ Struktur Database
Aplikasi menggunakan **lebih dari satu tabel**, yaitu:

- `kategori` (master)
- `objek_wisata` (master)
- `petugas` (master tambahan)
- `transaksi` (tabel transaksi)

---

## 🛠️ Teknologi yang Digunakan
- **Bahasa Pemrograman** : Java  
- **GUI** : Java Swing (NetBeans)  
- **Database** : MySQL  
- **IDE** : Apache NetBeans  
- **Library Tambahan** :
  - JCalendar (DateChooser)
  - Java Printing (PDF)

---

## ▶️ Cara Menjalankan Aplikasi
1. Import database `.sql` ke MySQL
2. Buka project menggunakan **NetBeans**
3. Atur koneksi database pada class `Koneksi.java`
4. Jalankan file `FrmLogin.java`
5. Login menggunakan akun yang tersedia di database

---

## 📄 Output
- Data tersimpan di database MySQL
- Laporan dapat dicetak ke **PDF**
- Tampilan GUI user-friendly

---

## 📹 Demo Aplikasi
- Video demo berdurasi **≤ 10 menit**
- Menampilkan:
  - Login
  - CRUD master data
  - Transaksi tiket
  - Laporan dan cetak PDF

*(Link video demo disertakan pada pengumpulan di e-learning)*

---

## ✅ Kesesuaian dengan Ketentuan UAS

| Komponen Penilaian | Status |
|------------------|--------|
| Minimal 2 Tabel Master | ✅ |
| Minimal 1 Tabel Transaksi | ✅ |
| Minimal 2 Laporan | ✅ |
| Kesesuaian UI | ✅ |
| Login & Role | ✅ |

---

## 📌 Catatan
Aplikasi ini dibuat secara mandiri dan tidak menyalin karya orang lain.  
Semua fitur telah diuji dan berjalan dengan baik.

---

**Muhammad Rizky Maulana**  
2310010486  
UAS PBO 2  

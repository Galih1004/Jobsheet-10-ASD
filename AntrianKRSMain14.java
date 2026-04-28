import java.util.Scanner;

public class AntrianKRSMain14 {

    public static void tampilMenu() {
        System.out.println("\n========================================");
        System.out.println("   SISTEM ANTRIAN PERSETUJUAN KRS DPA  ");
        System.out.println("========================================");
        System.out.println("1.  Tambah Mahasiswa ke Antrian");
        System.out.println("2.  Panggil Antrian untuk Proses KRS (2 mhs)");
        System.out.println("3.  Tampilkan Semua Antrian");
        System.out.println("4.  Tampilkan 2 Antrian Terdepan");
        System.out.println("5.  Tampilkan Antrian Paling Akhir");
        System.out.println("6.  Cek Antrian Kosong");
        System.out.println("7.  Cek Antrian Penuh");
        System.out.println("8.  Cetak Jumlah Antrian");
        System.out.println("9.  Cetak Jumlah yang Sudah KRS");
        System.out.println("10. Cetak Jumlah yang Belum KRS");
        System.out.println("11. Kosongkan Antrian");
        System.out.println("0.  Keluar");
        System.out.println("----------------------------------------");
        System.out.print("Pilih menu: ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AntrianKRS14 antrian = new AntrianKRS14(10, 30);
        int pilihan;

        System.out.println("Selamat datang di Sistem Antrian Persetujuan KRS DPA");

        do {
            tampilMenu();
            pilihan = sc.nextInt();
            sc.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("NIM   : ");
                    String nim = sc.nextLine();
                    System.out.print("Nama  : ");
                    String nama = sc.nextLine();
                    System.out.print("Prodi : ");
                    String prodi = sc.nextLine();
                    System.out.print("Kelas : ");
                    String kelas = sc.nextLine();
                    MahasiswaKRS14 mhs = new MahasiswaKRS14(nim, nama, prodi, kelas);
                    antrian.tambahAntrian(mhs);
                    break;
                case 2:
                    antrian.panggilAntrian();
                    break;
                case 3:
                    antrian.tampilkanSemua();
                    break;
                case 4:
                    antrian.tampilkanDuaTerdepan();
                    break;
                case 5:
                    antrian.lihatAkhir();
                    break;
                case 6:
                    if (antrian.isEmpty()) {
                        System.out.println("Antrian KOSONG.");
                    } else {
                        System.out.println("Antrian TIDAK kosong. Jumlah: " + antrian.jumlahAntrian());
                    }
                    break;
                case 7:
                    if (antrian.isFull()) {
                        System.out.println("Antrian PENUH (maks 10 mahasiswa).");
                    } else {
                        System.out.println("Antrian BELUM penuh. Sisa slot: " + (10 - antrian.jumlahAntrian()));
                    }
                    break;
                case 8:
                    System.out.println("Jumlah mahasiswa dalam antrian: " + antrian.jumlahAntrian());
                    break;
                case 9:
                    System.out.println("Jumlah mahasiswa yang sudah KRS: " + antrian.jumlahSudahKRS());
                    break;
                case 10:
                    System.out.println("Jumlah mahasiswa yang belum KRS (masih antri): " + antrian.jumlahBelumKRS());
                    break;
                case 11:
                    antrian.kosongkanAntrian();
                    break;
                case 0:
                    System.out.println("\n=== Rekap Akhir ===");
                    System.out.println("Mahasiswa yang sudah KRS   : " + antrian.jumlahSudahKRS());
                    System.out.println("Mahasiswa yang belum KRS   : " + antrian.jumlahBelumKRS());
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Masukkan angka 0-11.");
            }
        } while (pilihan != 0);

        sc.close();
    }
}
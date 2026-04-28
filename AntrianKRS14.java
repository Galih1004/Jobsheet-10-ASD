public class AntrianKRS14 {
    MahasiswaKRS14[] data;
    int front;
    int rear;
    int size;
    int max;
    int maxDPA;
    int sudahKRS;

    public AntrianKRS14(int max, int maxDPA) {
        this.max = max;
        this.maxDPA = maxDPA;
        this.data = new MahasiswaKRS14[max];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        this.sudahKRS = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == max;
    }

    public void tambahAntrian(MahasiswaKRS14 mhs) {
        if (isFull()) {
            System.out.println("Antrian penuh! Maksimal " + max + " mahasiswa dalam antrian.");
            return;
        }
        if (sudahKRS >= maxDPA) {
            System.out.println("DPA sudah menangani " + maxDPA + " mahasiswa. Sesi selesai.");
            return;
        }
        rear = (rear + 1) % max;
        data[rear] = mhs;
        size++;
        System.out.println(mhs.nama + " berhasil masuk ke antrian KRS (No. " + size + ").");
    }

    public void panggilAntrian() {
        if (isEmpty()) {
            System.out.println("Antrian kosong, tidak ada mahasiswa untuk dipanggil.");
            return;
        }
        System.out.println("=== Memanggil Antrian KRS ===");
        int dipanggil = 0;
        while (dipanggil < 2 && !isEmpty()) {
            if (sudahKRS >= maxDPA) {
                System.out.println("DPA sudah menangani maksimal " + maxDPA + " mahasiswa.");
                break;
            }
            MahasiswaKRS14 mhs = data[front];
            front = (front + 1) % max;
            size--;
            sudahKRS++;
            dipanggil++;
            System.out.print("Mahasiswa " + dipanggil + " dipanggil: ");
            mhs.tampilkanData();
        }
        if (dipanggil > 0) {
            System.out.println("Total dipanggil sesi ini: " + dipanggil + " mahasiswa.");
        }
    }

    public void tampilkanSemua() {
        if (isEmpty()) {
            System.out.println("Antrian kosong.");
            return;
        }
        System.out.println("=== Daftar Semua Antrian KRS ===");
        System.out.println("NIM - NAMA - PRODI - KELAS");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % max;
            System.out.print((i + 1) + ". ");
            data[index].tampilkanData();
        }
    }

    public void tampilkanDuaTerdepan() {
        if (isEmpty()) {
            System.out.println("Antrian kosong.");
            return;
        }
        System.out.println("=== 2 Antrian Terdepan ===");
        System.out.println("NIM - NAMA - PRODI - KELAS");
        int tampil = Math.min(2, size);
        for (int i = 0; i < tampil; i++) {
            int index = (front + i) % max;
            System.out.print((i + 1) + ". ");
            data[index].tampilkanData();
        }
    }

    public void lihatAkhir() {
        if (isEmpty()) {
            System.out.println("Antrian kosong.");
        } else {
            System.out.println("=== Antrian Paling Akhir ===");
            System.out.print("Mahasiswa paling belakang: ");
            data[rear].tampilkanData();
        }
    }

    public void kosongkanAntrian() {
        if (isEmpty()) {
            System.out.println("Antrian sudah kosong.");
        } else {
            front = 0;
            rear = -1;
            size = 0;
            System.out.println("Antrian berhasil dikosongkan.");
        }
    }

    public int jumlahAntrian() {
        return size;
    }

    public int jumlahSudahKRS() {
        return sudahKRS;
    }

    public int jumlahBelumKRS() {
        return size;
    }
}
## 2.1.3 Pertanyaan Percobaan 1
### 1. Mengapa nilai awal front dan rear = -1, sementara size = 0?

- **front = rear = -1** digunakan sebagai penanda bahwa queue masih kosong (belum ada elemen).
  Nilai -1 adalah sentinel/flag yang tidak valid sebagai indeks array, sehingga mudah dibedakan
  dari kondisi saat sudah ada data (indeks 0 ke atas).
- **size = 0** karena size menyimpan jumlah elemen aktual. Saat queue baru dibuat, belum ada
  elemen, jadi hitungannya 0.
- Strategi ini memudahkan pengecekan isEmpty() cukup dengan `size == 0`, dan ketika pertama
  kali Enqueue dilakukan, front dan rear langsung diset ke 0.
---
### 2. Potongan kode Enqueue:
if (rear == max - 1) {
    rear = 0;
}

Penjelasan:Kode ini mengimplementasikan **Circular Queue** (antrian melingkar). Ketika
pointer `rear` sudah berada di posisi paling akhir array (indeks max-1), maka rear "melingkar"
kembali ke posisi 0. Ini memungkinkan slot-slot yang sudah dikosongkan oleh Dequeue di bagian
depan array bisa digunakan kembali untuk menyimpan data baru, sehingga tidak ada pemborosan
memori.
---
### 3. Potongan kode Dequeue:
if (front == max - 1) {
    front = 0;
}

Penjelasan: Sama seperti nomor 2, ini juga bagian dari mekanisme **Circular Queue**.
Setelah elemen di posisi front diambil (dequeue), pointer `front` maju satu langkah. Jika
front sudah berada di posisi terakhir array (max-1), maka front diputar kembali ke indeks 0.
Ini memastikan traversal array bersifat melingkar dan tidak melebihi batas.

---

### 4. Mengapa perulangan di method print dimulai dari `int i = front`, bukan `int i = 0`?

Karena queue tidak selalu dimulai dari indeks 0. Setelah beberapa kali operasi Enqueue dan
Dequeue, posisi elemen pertama (terdepan) bisa berada di indeks berapa saja di dalam array
(itulah sifat Circular Queue). Jika dimulai dari i=0, maka elemen yang ditampilkan bisa berupa
slot kosong atau urutan yang salah. Dengan memulai dari `i = front`, kita memastikan iterasi
dimulai tepat dari elemen terdepan yang valid.

### 5. Penjelasan potongan kode di method print:

i = (i + 1) % max;

Penjelasan:Ini adalah cara melakukan iterasi pada **Circular Queue**. Operator modulo `%`
memastikan bahwa ketika nilai i mencapai `max` (batas akhir array), ia akan kembali ke 0.
Contoh: jika max=4 dan i=3, maka (3+1)%4 = 0, sehingga iterasi melingkar kembali ke awal
array. Tanpa modulo ini, i bisa melebihi batas array dan menyebabkan ArrayIndexOutOfBounds.

---

### 6. Potongan kode yang merupakan Queue Overflow:

Queue overflow terjadi saat Enqueue dilakukan pada queue yang sudah penuh. Potongan kodenya:
public void Enqueue(int dt) {
    if (IsFull()) {
        System.out.println("Queue sudah penuh");  // <-- INI adalah kondisi overflow
    } else {
        ...
    }
}

Kondisi `IsFull()` bernilai true ketika `size == max`, artinya semua slot array sudah terisi
dan tidak bisa lagi menerima data baru → itulah **queue overflow**.

---

### 7. Modifikasi agar program berhenti saat overflow / underflow

Perubahan dilakukan pada method `Enqueue` (overflow) dan `Dequeue` (underflow) di class Queue,
dengan menambahkan `System.exit(1)` setelah menampilkan pesan error.


// Modifikasi Enqueue - Queue Overflow
public void Enqueue(int dt) {
    if (IsFull()) {
        System.out.println("Queue sudah penuh - QUEUE OVERFLOW! Program dihentikan.");
        System.exit(1);  // Program berhenti
    } else {
        if (IsEmpty()) {
            front = rear = 0;
        } else {
            if (rear == max - 1) {
                rear = 0;
            } else {
                rear++;
            }
        }
        data[rear] = dt;
        size++;
    }
}

// Modifikasi Dequeue - Queue Underflow

public int Dequeue() {
    int dt = 0;
    if (IsEmpty()) {
        System.out.println("Queue masih kosong - QUEUE UNDERFLOW! Program dihentikan.");
        System.exit(1);  // Program berhenti
    } else {
        dt = data[front];
        size--;
        if (IsEmpty()) {
            front = rear = -1;
        } else {
            if (front == max - 1) {
                front = 0;
            } else {
                front++;
            }
        }
    }
    return dt;
}

## 2.2.3 Pertanyaan Percobaan 2
### Modifikasi: Tambah method `LihatAkhir` pada AntrianLayanan

Method `lihatAkhir()` sudah diimplementasikan di dalam class `AntrianLayanan`:
// Di dalam class AntrianLayanan
public void lihatAkhir() {
    if (isEmpty()) {
        System.out.println("Antrian kosong.");
    } else {
        System.out.print("Mahasiswa paling belakang: ");
        System.out.println("NIM - NAMA - PRODI - KELAS");
        data[rear].tampilkanData();
    }
}
Dan di class `LayananAkademikSIAKAD`, ditambahkan menu ke-6:
    java
// Tambahan di menu do-while
System.out.println("6. Cek Antrian paling belakang");

// Tambahan di switch-case
case 6:
    antrian.lihatAkhir();
    break;

## 2.3 Tugas - Diagram Class AntrianKRS
+-------------------------------------------------------+
|                    MahasiswaKRS                       |
+-------------------------------------------------------+
| - nim    : String                                     |
| - nama   : String                                     |
| - prodi  : String                                     |
| - kelas  : String                                     |
+-------------------------------------------------------+
| + MahasiswaKRS(nim, nama, prodi, kelas : String)      |
| + tampilkanData() : void                              |
+-------------------------------------------------------+

+-------------------------------------------------------+
|                    AntrianKRS                         |
+-------------------------------------------------------+
| - data      : MahasiswaKRS[]                          |
| - front     : int                                     |
| - rear      : int                                     |
| - size      : int                                     |
| - max       : int  (default = 10)                     |
| - maxDPA    : int  (default = 30)                     |
| - sudahKRS  : int                                     |
+-------------------------------------------------------+
| + AntrianKRS(max: int, maxDPA: int)                   |
| + isEmpty()              : boolean                    |
| + isFull()               : boolean                    |
| + tambahAntrian(mhs: MahasiswaKRS) : void             |
| + panggilAntrian()       : void  (2 mhs per panggil)  |
| + tampilkanSemua()       : void                       |
| + tampilkanDuaTerdepan() : void                       |
| + lihatAkhir()           : void                       |
| + kosongkanAntrian()     : void                       |
| + jumlahAntrian()        : int                        |
| + jumlahSudahKRS()       : int                        |
| + jumlahBelumKRS()       : int                        |
+-------------------------------------------------------+
            |
            |
            |
+-------------------------------------------------------+
|                  AntrianKRSMain                       |
+-------------------------------------------------------+
| + main(args: String[]) : void                         |
| + tampilMenu()         : void                         |
+-------------------------------------------------------+
### Relasi:
- `AntrianKRS` menggunakan (uses/depends on) `MahasiswaKRS` sebagai tipe data elemen antrian.
- `AntrianKRSMain` menggunakan `AntrianKRS` dan `MahasiswaKRS` pada method main.

### Penjelasan Fitur Tugas:
| Fitur | Method | Keterangan |
|---|---|---|
| Cek kosong | isEmpty() | return size == 0 |
| Cek penuh | isFull() | return size == max (max=10) |
| Kosongkan | kosongkanAntrian() | reset front, rear, size |
| Tambah antrian | tambahAntrian() | Enqueue, cek full & maxDPA |
| Panggil 2 mhs | panggilAntrian() | Dequeue 2x per panggilan |
| Tampil semua | tampilkanSemua() | Loop seluruh elemen |
| Tampil 2 terdepan | tampilkanDuaTerdepan() | Loop max 2 elemen dari front |
| Tampil akhir | lihatAkhir() | Akses data[rear] |
| Jumlah antrian | jumlahAntrian() | return size |
| Jumlah sudah KRS | jumlahSudahKRS() | return sudahKRS |
| Jumlah belum KRS | jumlahBelumKRS() | return size (masih di antrian) |
| Maks antrian | - | max = 10 |
| Maks per DPA | - | maxDPA = 30 |
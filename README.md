

# Quiz Sederhana

Sebuah sistem aplikasi berbasis desktop (Java Swing) untuk memfasilitasi proses ujian/quiz, yang didesain untuk penggunaan oleh tiga peran utama: **Admin**, **Guru**, dan **Siswa**.

-----

## ✨ Fitur Utama Program

Aplikasi ini mengadopsi arsitektur multi-role untuk memastikan pengelolaan dan pelaksanaan quiz yang terstruktur.

### 👤 Admin

| Fitur | Deskripsi |
| :--- | :--- |
| **Verifikasi Akun** | Melakukan persetujuan (`Verifikasi`) atau penolakan (`Tolak`) terhadap akun **Guru** dan **Siswa** yang baru mendaftar. |
| **Pengawasan** | Melihat daftar akun yang masih tertunda verifikasi. |

### 👨‍🏫 Guru

| Fitur | Deskripsi |
| :--- | :--- |
| **Manajemen Quiz** | Membuat quiz baru (`Tambah Quiz`), memperbarui soal yang sudah ada (`Update Quiz`), dan menghapus quiz beserta semua skor terkait (`Hapus Quiz`). |
| **Evaluasi Skor** | Melihat semua skor siswa untuk quiz yang dibuat, termasuk opsi untuk menghapus skor individu. |
| **Feedback** | Mengirimkan pesan/umpan balik ke siswa tertentu. |

### 👩‍🎓 Siswa

| Fitur | Deskripsi |
| :--- | :--- |
| **Pengerjaan Quiz** | Melihat daftar quiz yang tersedia dan mengerjakannya, dengan fitur navigasi antar soal (`Next`/`Kembali`) sebelum `Kumpulkan`. |
| **Rapor Nilai** | Melihat riwayat skor pribadi (jumlah benar, salah, dan total nilai) dari semua quiz yang telah dikerjakan. |
| **Pesan** | Melihat umpan balik/pesan yang dikirim oleh Guru. |

-----

## 🏗️ Penerapan Konsep Object-Oriented Programming (OOP)

Program ini dirancang menggunakan prinsip-prinsip OOP untuk modularitas dan kemudahan pemeliharaan.

### 1\. Enkapsulasi (Encapsulation)

  * **Penerapan:** Melindungi data internal objek dari akses langsung dan menyediakan antarmuka publik yang terkontrol melalui metode *getter* dan *setter*.
  * **Contoh Kode:** Kelas model seperti `QuestionItem` mendeklarasikan properti penting (`number`, `question`, `correct`) sebagai `private final` dan hanya mengeksposnya melalui metode *getter* (`getNumber()`, `getQuestion()`, `getCorrect()`), memastikan integritas data. Selain itu, kredensial sensitif database dalam `DatabaseConnection.java` dideklarasikan sebagai `private static final`.

### 2\. Pewarisan (Inheritance)

  * **Penerapan:** Kelas turunan mewarisi properti dan perilaku dari kelas induk untuk menghindari duplikasi kode.
  * **Contoh Kode:** Semua kelas *Controller* yang berhubungan dengan database, seperti `AdminController`, `AuthController`, dan `TeacherQuizController`, mewarisi fungsionalitas dasar database (seperti `execUpdate` dan `execQuery`) dari kelas induk **`BaseController`**.

### 3\. Abstraksi (Abstraction)

  * **Penerapan:** Menyembunyikan implementasi yang rumit dan hanya menampilkan fungsionalitas yang relevan kepada pengguna (atau kelas lain).
  * **Contoh Kode:** Kelas *View* (misalnya, `TeacherTambahQuiz.java`) hanya berinteraksi dengan `TeacherQuizController` melalui metode tingkat tinggi seperti `saveBatch(teacherId, quizTitle, items)`. Detail tentang koneksi database, penyiapan `PreparedStatement`, dan penanganan transaksi SQL (`INSERT INTO quiz...`) sepenuhnya tersembunyi di dalam lapisan *Controller* dan *Utilities*.

### 4\. Polimorfisme (Polymorphism)

  * **Penerapan:** Kemampuan suatu objek untuk mengambil banyak bentuk, diwujudkan melalui implementasi *interface*.
  * **Contoh Kode:** Kelas `StudentAuthentication` dan `TeacherAuthentication` keduanya memiliki metode **`registrationQuery()`**. Metode ini, meskipun namanya sama, memberikan implementasi SQL yang berbeda sesuai dengan peran (`INSERT INTO student...` atau `INSERT INTO teacher...`).

### 5\. Interface

  * **Penerapan:** Mendefinisikan kontrak standar yang harus diikuti oleh kelas-kelas yang terkait.
  * **Contoh Kode:** **`RegistrationMechanism`** adalah sebuah *interface* yang mendefinisikan metode **`registrationQuery()`**. Kelas konkret seperti `StudentAuthentication` dan `TeacherAuthentication` **wajib** mengimplementasikan antarmuka ini untuk menyediakan query SQL pendaftaran yang spesifik.

-----

## 📦 Struktur Folder / Package

Struktur *package* proyek ini mengikuti prinsip pemisahan tanggung jawab (Separation of Concerns):

```
ghenayari/capstonepastibisa
└── src/main/java/app
    ├── controller/
    │   ├── AdminController.java
    │   ├── AuthController.java
    │   ├── StudentQuizController.java
    │   └── TeacherQuizController.java
    ├── model/
    │   └── QuestionItem.java
    ├── utilities/
    │   ├── authentication/registration/
    │   │   ├── RegistrationMechanism.java
    │   │   ├── StudentAuthentication.java
    │   │   └── TeacherAuthentication.java
    │   ├── base/
    │   │   └── BaseController.java
    │   └── data/
    │       └── DatabaseConnection.java
    ├── view/
    │   ├── AdminDashboard.java
    │   ├── Login.java
    │   ├── StudentDashboard.java
    │   ├── StudentFeedback.java
    │   ├── StudentKerjakanQuiz.java
    │   ├── StudentRapor.java
    │   ├── TeacherDashboard.java
    │   ├── TeacherFeedback.java
    │   ├── TeacherLihatSkor.java
    │   ├── TeacherTambahQuiz.java
    │   └── TeacherUpdateQuiz.java
    └── MainApplication.java
```

| Package | Tanggung Jawab |
| :--- | :--- |
| `app` | Titik masuk utama aplikasi (`MainApplication`). |
| `app.controller` | Logika bisnis, mengelola alur data, dan berkoordinasi dengan *utilities* database. |
| `app.model` | Representasi struktur data (misalnya, objek soal quiz). |
| `app.view` | Komponen antarmuka pengguna (GUI), bertanggung jawab atas tampilan dan interaksi pengguna. |
| `app.utilities.data` | Fungsionalitas koneksi dan operasi dasar database (`DatabaseConnection`). |
| `app.utilities.base` | Kelas dasar yang menyediakan fungsionalitas umum untuk *Controller* (contoh: `BaseController`). |
| `app.utilities.authentication.registration` | Logika dan kontrak untuk mekanisme pendaftaran pengguna. |



## Library





## Cara Menggunakan Program


### Menu Login
<img width="1157" height="864" alt="image" src="https://github.com/user-attachments/assets/441a083c-a4b5-44f4-9e8e-f7c7026fc2fd" />

Menu ini berfungsi sebagai pintu masuk bagi pengguna untuk mengakses sistem. Terdapat beberapa komponen seperti kolom untuk memasukkan username, nama, dan password, serta tombol Login dan Register yang digunakan untuk masuk atau mendaftar akun baru. Selain itu, terdapat juga pilihan peran pengguna seperti Admin yang dapat dipilih sebelum login.


<img width="1062" height="819" alt="image" src="https://github.com/user-attachments/assets/9d8a4239-0e2c-44db-b4e6-3868ee6db74e" />

Bagian ini menunjukkan bahwa proses registrasi gagal karena username yang dimasukkan sudah pernah digunakan oleh pengguna lain. Jadi sistem otomatis menampilkan pesan “Username sudah dipakai” sebagai peringatan. Tujuannya biar setiap akun punya username yang unik dan tidak terjadi data ganda di dalam database.


<img width="1075" height="800" alt="image" src="https://github.com/user-attachments/assets/01e7448b-e74b-4139-9e85-66839d560db7" />

Bagian ini menunjukkan bahwa proses registrasi berhasil dilakukan. Setelah mengisi semua data dengan benar dan menekan tombol Register, sistem menampilkan pesan “Registrasi berhasil. Menunggu verifikasi admin.” Pesan ini berarti data sudah tersimpan di database, tapi akun belum bisa digunakan untuk login sebelum diverifikasi oleh admin terlebih dahulu.


<img width="1077" height="827" alt="image" src="https://github.com/user-attachments/assets/f8523101-83f4-42cd-9dd3-fd729eb32e00" />

Bagian ini menunjukkan bahwa proses verifikasi akun oleh admin telah berhasil dilakukan. Setelah admin memilih data pengguna yang ingin diverifikasi dan menekan tombol Verifikasi, sistem menampilkan pesan “Berhasil diverifikasi" yang menandakan bahwa akun pengguna tersebut sudah diverifikasi oleh admin dan kini dapat digunakan untuk login ke sistem.


### Hapus Quiz

<img width="1026" height="782" alt="image" src="https://github.com/user-attachments/assets/f876ae1d-ce16-47f8-8bd0-3afba2944aa1" />


<img width="759" height="706" alt="image" src="https://github.com/user-attachments/assets/ee0e88ef-7100-41dd-a082-e33435d81e40" />


<img width="763" height="705" alt="image" src="https://github.com/user-attachments/assets/2bb179a0-9043-4890-ad4f-328ed134f0ce" />


<img width="756" height="714" alt="image" src="https://github.com/user-attachments/assets/90dcab04-a68d-4fb4-b6da-0b90e5da7c7a" />


### Tambah Quiz

<img width="827" height="701" alt="image" src="https://github.com/user-attachments/assets/b7be9f3d-c4ab-43c6-827a-f985f09c0fe6" />

<img width="806" height="686" alt="image" src="https://github.com/user-attachments/assets/fd84af0d-1187-4793-9d07-83cae5ea8371" />

<img width="831" height="705" alt="image" src="https://github.com/user-attachments/assets/4269a3a9-ebee-447c-a5aa-d6e922d1f23a" />

<img width="766" height="713" alt="image" src="https://github.com/user-attachments/assets/776eb31d-73ab-4acf-8392-7299503af31e" />

<img width="766" height="713" alt="image" src="https://github.com/user-attachments/assets/d75826c0-6ff5-49b3-b85f-c574e435c4f3" />


### Mengerjakan quiz dan melihat skor

<img width="932" height="807" alt="image" src="https://github.com/user-attachments/assets/9b68ff82-f4ac-4f44-a1ea-678120210aa3" />

<img width="712" height="459" alt="image" src="https://github.com/user-attachments/assets/8f2d8233-e822-4516-853d-7c2bd2b40660" />

<img width="726" height="455" alt="image" src="https://github.com/user-attachments/assets/8851dd0b-14a5-478a-bb09-614b59e39a13" />

<img width="691" height="454" alt="image" src="https://github.com/user-attachments/assets/158eb9e6-1004-40f7-a921-4f33c9958f86" />

<img width="1032" height="728" alt="image" src="https://github.com/user-attachments/assets/ab942e64-c909-4d6a-80cb-7d7e03128676" />


### Teacher melihat skor

<img width="994" height="801" alt="image" src="https://github.com/user-attachments/assets/49b4b241-cce1-4d5d-bfab-450c5712bca5" />


### Teacher update quiz

<img width="776" height="710" alt="image" src="https://github.com/user-attachments/assets/57bf9f9e-eb90-40b9-bbcf-6d1d210b3bda" />

<img width="989" height="756" alt="image" src="https://github.com/user-attachments/assets/82bf0277-c630-4442-97d7-a5fee4566ad1" />

<img width="999" height="764" alt="image" src="https://github.com/user-attachments/assets/bbb96928-5192-42fc-ade7-2d8d37e82d52" />

<img width="713" height="458" alt="image" src="https://github.com/user-attachments/assets/1f594a93-8311-4d89-a948-e45752559b8a" />


###  Teacher mengirim pesan ke student

<img width="1097" height="691" alt="image" src="https://github.com/user-attachments/assets/daed18d9-44b9-4154-859e-31c869628d60" />

<img width="1098" height="690" alt="image" src="https://github.com/user-attachments/assets/d754cd22-548a-4854-8093-5e74cfcdad2f" />

<img width="1002" height="775" alt="image" src="https://github.com/user-attachments/assets/af7208af-5590-42cd-a369-1d2fc45a1568" />


### Teacher hanya bisa melihat quiz yang dibuat sendiri dan tidak bisa melihat quiz yang guru lain buat

<img width="764" height="710" alt="image" src="https://github.com/user-attachments/assets/a2bb6a88-a6c1-4934-a1d9-cfaf1b193649" />

<img width="764" height="710" alt="image" src="https://github.com/user-attachments/assets/af86e0f7-bb65-4539-a439-bc72983afd85" />

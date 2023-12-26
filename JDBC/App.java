import java.sql.*;
import java.util.Scanner;

public class App {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/jdbc_pbo";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    public static void main(String[] args) throws Exception {
        Kasir kasir = new Kasir();
        kasir.Login(); 
        
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            System.out.print("Masukkan Nomor Faktur: ");
            String nomorFaktur = scanner.nextLine();

            System.out.print("Masukkan Nama Pelanggan: ");
            String namaPelanggan = scanner.nextLine();

            System.out.print("Masukkan Nomor HP: ");
            String nomorHp = scanner.nextLine();

            System.out.print("Masukkan Alamat: ");
            String alamat = scanner.nextLine();

            String sql = "INSERT INTO java14 (no_Faktur, nama, no_HP, alamat) VALUE ('%s', '%s', '%s', '%s' )";
            sql = String.format(sql, nomorFaktur, namaPelanggan, nomorHp, alamat);
            stmt.execute(sql);

            System.out.print("Masukkan Kode Barang: ");
            String kodeBarang = scanner.nextLine();

            System.out.print("Masukkan Nama Barang: ");
            String namaBarang = scanner.nextLine();

            System.out.print("Masukkan Harga Barang (max 1000000)\t: ");
            Integer hargaBarang = Integer.parseInt(scanner.nextLine());
            if (hargaBarang > 500000) {
                throw new IllegalArgumentException("Harga barang tidak boleh lebih dari 1000000");
            }

            System.out.print("Masukkan Jumlah Beli (max 100)\t: ");
            Integer jumlahBeli = Integer.parseInt(scanner.nextLine());
            if (jumlahBeli > 100) {
                throw new IllegalArgumentException("Jumlah beli tidak boleh lebih dari 100");
            }

            System.out.print("pilih Metode Pembayaran (faktur/online): ");
            String metodePembayaran = scanner.nextLine();

            faktur faktur;
            if (metodePembayaran == "faktur") {
                faktur = new faktur(nomorFaktur, namaPelanggan, nomorHp, alamat, kodeBarang, namaBarang, hargaBarang, jumlahBeli);
            } else {
                faktur = new fakturOnline(nomorFaktur, namaPelanggan, nomorHp, alamat, kodeBarang, namaBarang, hargaBarang, jumlahBeli, metodePembayaran);
            }

            if (faktur instanceof fakturOnline) {
                ((fakturOnline) faktur).tampilkanDetailFakturOnline();
            } else {
                faktur.tampilkanDetailFaktur();
            }

            while (!conn.isClosed()) {
               showMenu();
            }

            stmt.close();
            conn.close();

        } catch (NumberFormatException e) {
            System.out.println("Input harus berupa angka");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Kasir : " + kasir.getUsernameBenar());
        }
        scanner.close();
    } 
    
    static Scanner scanner = new Scanner(System.in); 
    
    static void showMenu() {
        System.out.println("\n========= CRUD =========");
        System.out.println("1. Input Data");
        System.out.println("2. Show Data");
        System.out.println("3. Edit Data");
        System.out.println("4. Delete Data");
        System.out.println("0. Keluar");
        System.out.println("");
        System.out.print("PILIHAN> ");

        try {
            Integer pilihan = Integer.parseInt(scanner.nextLine());

            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    inputData();
                case 2:
                    showData();
                    break;
                case 3:
                    updateData();
                    break;
                case 4:
                    deleteData();
                    break;
                default:
                    System.out.println("Pilihan salah!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void showData() {
        String sql = "SELECT * FROM java14";

        try {
            rs = stmt.executeQuery(sql);
            
            System.out.println("\n\n");
            System.out.println("      DATA PELANGGAN A STORE      ");
            System.out.println("==================================");

            while (rs.next()) {
                String no_faktur = rs.getString("no_Faktur");
                String nama = rs.getString("nama");
                String no_hp = rs.getString("no_HP");
                String alamat = rs.getString("alamat");

                
                System.out.println(String.format("%s. %s -- %s -- (%s)", no_faktur, nama, alamat, no_hp));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void inputData(){
        try{
        System.out.print("Masukkan Nomor Faktur: ");
            String nomorFaktur = scanner.nextLine();

            System.out.print("Masukkan Nama Pelanggan: ");
            String namaPelanggan = scanner.nextLine();

            System.out.print("Masukkan Nomor HP: ");
            String nomorHp = scanner.nextLine();

            System.out.print("Masukkan Alamat: ");
            String alamat = scanner.nextLine();

            String sql = "INSERT INTO java14 (no_Faktur, nama, no_HP, alamat) VALUE ('%s', '%s', '%s', '%s' )";
            sql = String.format(sql, nomorFaktur, namaPelanggan, nomorHp, alamat);
            stmt.execute(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void updateData() {
        try {
            
            System.out.print("Faktur yang mau diedit : ");
            String no_faktur = scanner.nextLine();
            System.out.print("Nama Pelanggan (Baru): ");
            String nama = scanner.nextLine().trim();
            System.out.print("Nomor HP (Baru): ");
            String no_hp = scanner.nextLine().trim();
            System.out.print("Alamat (Baru): ");
            String alamat = scanner.nextLine().trim();

            String sql = "UPDATE java14 SET nama='%s', no_HP='%s', alamat='%s' WHERE no_Faktur='%s'";
            sql = String.format(sql, nama, no_hp, alamat, no_faktur);

            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void deleteData() {
        try {
            
            // ambil input dari user
            System.out.print("Masukkan Faktur yang mau dihapus : ");
            String no_faktur = (scanner.nextLine());
            
            // buat query hapus
            String sql = String.format("DELETE FROM java14 WHERE no_Faktur='%s'", no_faktur);

            // hapus data
            stmt.execute(sql);
            
            System.out.println("Data telah dihapus");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
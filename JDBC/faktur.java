import java.text.SimpleDateFormat;
import java.util.Date;

public class faktur implements hitungTotalBayar {
    private String nomorFaktur;
    private String namaPelanggan;
    private String nomorHp; 
    private String namaBarang; 
    private Integer hargaBarang;
    private Integer jumlahBeli;
    private String alamat;
    private String kodeBarang; 

    Date date = new Date();
    SimpleDateFormat tanggal = new SimpleDateFormat("E dd/MM/yyyy");
    SimpleDateFormat jam = new SimpleDateFormat("hh:mm:ss zzzz");

    public faktur(String nomorFaktur, String namaPelanggan, String nomorHp, String alamat, String kodeBarang, String namaBarang, Integer hargaBarang, Integer jumlahBeli) {
        this.nomorFaktur = nomorFaktur;
        this.namaPelanggan = namaPelanggan;
        this.nomorHp = nomorHp;
        this.alamat = alamat;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.jumlahBeli = jumlahBeli;
    }


    public String getNomorFaktur() {
        return nomorFaktur;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public Integer getHargaBarang() {
        return hargaBarang;
    }

    public Integer getJumlahBeli() {
        return jumlahBeli;
    }
    public String getAlamat() {
        return alamat;
    }
    public String getKodeBarang() {
        return kodeBarang;
    }


    public void tampilkanDetailFaktur() {
        System.out.println("");
        System.out.println("A STORE");
        System.out.println("Tanggal : " + tanggal.format(date));
        System.out.println("Jam : " + jam.format(date));
        System.out.println("Nomor Faktur: " + nomorFaktur.toUpperCase());
        System.out.println("=======================");
        System.out.println("DATA PELANGGAN");
        System.out.println("Nama Pelanggan: " + namaPelanggan);
        System.out.println("Nomor HP: " + nomorHp);
        System.out.println("Alamat : " + alamat);
        System.out.println("+++++++++++++++++++");
        System.out.println("DATA PEMBELIAN BARANG");
        System.out.println("------------------");
        System.out.println("Kode Barang : " + kodeBarang.toUpperCase());
        System.out.println("Nama Barang: " + namaBarang);
        System.out.println("Harga Barang: " + hargaBarang);
        System.out.println("Jumlah Beli: " + jumlahBeli);
        System.out.println("Total Bayar: " + hitungTotalBayar(hargaBarang, jumlahBeli));
        System.out.println("++++++++++++++++++");
    }

    @Override
    public Integer hitungTotalBayar(Integer harga, Integer jumlah) {
        return harga * jumlah;
    }
}
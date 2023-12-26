public class fakturOnline extends faktur {
    private String metodePembayaran; 

    public fakturOnline(String nomorFaktur, String namaPelanggan, String alamat, String nomorHp, String kodeBarang, String namaBarang, Integer hargaBarang, int jumlahBeli, String metodePembayaran) {
        super(nomorFaktur, namaPelanggan, nomorHp, alamat, kodeBarang, namaBarang, hargaBarang, jumlahBeli);
        this.metodePembayaran = metodePembayaran;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    @Override
    public Integer hitungTotalBayar(Integer harga, Integer jumlah) {
        return harga * jumlah;
    }

    public void tampilkanDetailFakturOnline() {
        tampilkanDetailFaktur();
        System.out.println("Metode Pembayaran: " + metodePembayaran);
    }
}
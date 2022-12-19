package pboprak;

public class Penjualan implements ProductCounter {
    private int jumlahProduk, stok;
    private String namaProduk, idBarcode;

    public Penjualan(int jumlahProduk, String namaProduk, String idBarcode) {
        this.jumlahProduk = jumlahProduk;
        this.namaProduk = namaProduk;
        this.idBarcode = idBarcode;
    }

    public Penjualan(String idBarcode) {
        this.idBarcode = idBarcode;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public int getJumlahProduk() {
        return jumlahProduk;
    }

    public void setJumlahProduk(int jumlahProduk) {
        this.jumlahProduk = jumlahProduk;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    @Override
    public int hitungJumlahProduk() {
        return 0;
    }

    @Override
    public double hitungHargaProduk() {
        return 0;
    }

    public String getIdBarcode() {
        return idBarcode;
    }

    public void setIdBarcode(String idBarcode) {
        this.idBarcode = idBarcode;
    }
}

abstract class Instrumento {

    String codigo;
    double precio;
    int stock;
    String material;

    public Instrumento(String codigo, double precio, int stock, String material) {
        this.codigo = codigo;
        this.precio = precio;
        this.stock = stock;
        this.material = material;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public String getMaterial() {
        return material;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
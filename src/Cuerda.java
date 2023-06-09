public final class Cuerda extends Instrumento{

    final private String modeloInst;
    final private String cuerda;
    final private int numeroCuerda;
    final private String tipo;

    public Cuerda(String codigo, double precio, int stock, String material, String modeloInst, String cuerda, int numeroCuerda, String tipo) {
        super(codigo, precio, stock, material);
        this.modeloInst = modeloInst;
        this.cuerda = cuerda;
        this.numeroCuerda = numeroCuerda;
        this.tipo = tipo;
    }

    public String getModeloInst() {
        return modeloInst;
    }

    public String getCuerda() {
        return cuerda;
    }

    public int getNumeroCuerda() {
        return numeroCuerda;
    }

    public String getTipo() {
        return tipo;
    }
}
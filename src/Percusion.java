public final class Percusion extends Instrumento{

    final private String modeloInst;
    final private String tipo;
    final private String altura;

    public Percusion(String codigo, double precio, int stock, String material, String modeloInst, String tipo, String altura) {
        super(codigo, precio, stock, material);
        this.modeloInst = modeloInst;
        this.tipo = tipo;
        this.altura = altura;
    }

    public String getModeloInst() {
        return modeloInst;
    }

    public String getTipo() {
        return tipo;
    }

    public String getAltura() {
        return altura;
    }
}
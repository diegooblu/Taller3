public class Percusion extends Instrumento{

    private String modeloInst;

    private String tipo;

    private double altura;

    public Percusion(String codigo, double precio, int stock, String material, String modeloInst, String tipo, double altura) {
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

    public double getAltura() {
        return altura;
    }
}

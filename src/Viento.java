public final class Viento extends Instrumento{

    final private String modeloInst;

    public Viento(String codigo, double precio, int stock, String material, String modeloInst) {
        super(codigo, precio, stock, material);
        this.modeloInst = modeloInst;
    }

    public String getModeloInst() {
        return modeloInst;
    }
}
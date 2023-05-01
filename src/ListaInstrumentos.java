public class ListaInstrumentos {

    Instrumento [] listaInstrumentos;
    int cantidadActual;
    int cantidadMax;

    public ListaInstrumentos(int cantidadMax) {
        this.listaInstrumentos = new Instrumento[cantidadMax];
        this.cantidadMax = cantidadMax;
        this.cantidadActual = 0;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public int getCantidadMax() {
        return cantidadMax;
    }
}

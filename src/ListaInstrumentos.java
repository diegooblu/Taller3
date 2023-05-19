import ucn.StdOut;

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

    /**
     * Subprograma que entrega el instrumento de una posicion especifica en la lista
     * @param pos es la posicion en la cual se buscara el instrumento
     * @return el instrumento en la posicion mencionada
     */
    public Instrumento obtenerInstrumento(int pos){
        if(pos < 0 || pos >= this.cantidadActual){
            return null;
        }
        return listaInstrumentos[pos];
    }

    public int getCantidadMax() {
        return cantidadMax;
    }


    /**
     * Subprograma que agrega un instrumento a la lista
     * @param instrumento nuevo que se quiera agregar
     */
    public void agregar (Instrumento instrumento) {
        if(this.cantidadActual == this.cantidadMax){
            return;
        }
        listaInstrumentos[cantidadActual] = instrumento;
        this.cantidadActual++;
    }

    /**
     * Subprograma que despliega la informacion de un instrumento dado el tipo de instrumento que sea
     * @param nombreClase con la cual se identificaria el nombre de la clase del instrumento
     * @param posicion en donde se encontraria ubicado el instrumento
     */
    public void informacion (String nombreClase, int posicion) {
        switch (nombreClase) {
            case "Cuerda" -> {
                Cuerda instrumento = (Cuerda) listaInstrumentos[posicion];
                String codigo = instrumento.getCodigo();
                double precio = instrumento.getPrecio();
                int stock = instrumento.getStock();
                String material = instrumento.getMaterial();
                String nombreInst = instrumento.getModeloInst();
                String cuerda = instrumento.getCuerda();
                int numeroCuerdas = instrumento.getNumeroCuerda();
                String tipo = instrumento.getTipo();
                StdOut.println(
                                "Codigo Producto: " + codigo + "\n" +
                                "Precio: " + precio + "\n" +
                                "Stock: " + stock + "\n" +
                                "Material: " + material + "\n" +
                                "Nombre instrumento: " + nombreInst + "\n" +
                                "Tipo de cuerda: " + cuerda + "\n" +
                                "Numero de cuerdas" + numeroCuerdas + "\n" +
                                "Tipo instrumento: " + tipo
                );
            }
            case "Viento" -> {
                Viento instrumento = (Viento) listaInstrumentos[posicion];
                String codigo = instrumento.getCodigo();
                double precio = instrumento.getPrecio();
                int Stock = instrumento.getStock();
                String material = instrumento.getMaterial();
                String nombreInst = instrumento.getModeloInst();
                StdOut.println(
                        "Codigo Producto: " + codigo + "\n" +
                        "Precio: " + precio + "\n" +
                        "Stock: " + Stock + "\n" +
                        "Material: " + material + "\n" +
                        "Nombre instrumento: " + nombreInst
                );
            }
            case "Percusion" -> {
                Percusion instrumento = (Percusion) listaInstrumentos[posicion];
                String codigo = instrumento.getCodigo();
                double precio = instrumento.getPrecio();
                int Stock = instrumento.getStock();
                String nombreInst = instrumento.getModeloInst();
                String tipo = instrumento.getTipo();
                String altura = instrumento.getAltura();
                StdOut.println(
                        "Codigo Producto :" + codigo + "\n" +
                        "Precio: " + precio + "\n" +
                        "Nombre: " + nombreInst + "\n" +
                        "Stock: " + Stock + "\n" +
                        "Tipo Percusion: " + tipo + "\n" +
                        "Altura: " + altura
                );
            }
        }
    }
}
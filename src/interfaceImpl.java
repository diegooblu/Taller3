import ucn.ArchivoEntrada;
import ucn.Registro;
import ucn.StdIn;
import ucn.StdOut;
import java.io.IOException;

public class interfaceImpl implements Interface{

    private ListaInstrumentos listadoInstrumentos;

    /**
     *Subprograma utilizado para la lectura previa de archivos, añadiendo los instrumentos al inventario,
     * si es que se encuentran instrumentos con el mismo codigo, se le sumaria el stock al primero que
     * se añadio.
     */
    public void lecturaArchivo () throws IOException {
        ArchivoEntrada archivo1 = new ArchivoEntrada("csv_prueba.csv");
        while (!archivo1.isEndFile()){
            Registro regEnt = archivo1.getRegistro();
            String codigo = regEnt.getString();
            double precio = regEnt.getDouble();
            int stock = regEnt.getInt();
            String nombreInst = regEnt.getString();
            if (nombreInst.equalsIgnoreCase("Guitarra") || nombreInst.equalsIgnoreCase("Bajo") ||
                    nombreInst.equalsIgnoreCase("violin") || nombreInst.equalsIgnoreCase("Arpa")){
                String cuerda = regEnt.getString();
                int numeroCuerdas = regEnt.getInt();
                String material = regEnt.getString();
                String tipo = regEnt.getString();
                Instrumento instrumento = new Cuerda(codigo,precio,stock,material,nombreInst,cuerda,numeroCuerdas,tipo);
                boolean verificar = verificarCodigo(codigo);
                if (!verificar) {
                    listadoInstrumentos.agregar(instrumento);
                } else {
                    for (int i = 0; i < listadoInstrumentos.getCantidadActual(); i++) {
                        Instrumento instrumentoAux = listadoInstrumentos.obtenerInstrumento(i);
                        if (codigo.equals(instrumentoAux.getCodigo())){
                            instrumentoAux.setStock(instrumentoAux.getStock() + stock);
                        }
                    }
                }
            }
            if (nombreInst.equalsIgnoreCase("Bongo") || nombreInst.equalsIgnoreCase("cajón") ||
                    nombreInst.equalsIgnoreCase("campanas tubulares") || nombreInst.equalsIgnoreCase("bombo")){
                String tipo = regEnt.getString();
                String material = regEnt.getString();
                String altura = regEnt.getString();
                Instrumento instrumento = new Percusion(codigo,precio,stock,nombreInst,tipo,material,altura);
                boolean verificar = verificarCodigo(codigo);
                if (!verificar) {
                    listadoInstrumentos.agregar(instrumento);
                } else {
                    for (int i = 0; i < listadoInstrumentos.getCantidadActual(); i++) {
                        Instrumento instrumentoAux = listadoInstrumentos.obtenerInstrumento(i);
                        if (codigo.equals(instrumentoAux.getCodigo())){
                            instrumentoAux.setStock(instrumentoAux.getStock() + stock);
                        }
                    }
                }
            }
            if (nombreInst.equalsIgnoreCase("Trompeta") || nombreInst.equalsIgnoreCase("Saxofon") ||
                    nombreInst.equalsIgnoreCase("Clarinete") || nombreInst.equalsIgnoreCase("Flauta traversa")) {
                String material = regEnt.getString();
                Instrumento instrumento = new Viento(codigo,precio,stock,material,nombreInst);
                boolean verificar = verificarCodigo(codigo);
                if (!verificar) {
                    listadoInstrumentos.agregar(instrumento);
                } else {
                    for (int i = 0; i < listadoInstrumentos.getCantidadActual(); i++) {
                        Instrumento instrumentoAux = listadoInstrumentos.obtenerInstrumento(i);
                        if (codigo.equals(instrumentoAux.getCodigo())){
                            instrumentoAux.setStock(instrumentoAux.getStock() + stock);
                        }
                    }
                }
            }
        }
    }

    /**
     * Subprograma base, el cual da inicio al resto de codigo, en el cual se inicia el
     * listadoInstrumentos y la lectura de archivos previos
     */
    public interfaceImpl() throws IOException {
        this.listadoInstrumentos = new ListaInstrumentos(100);
        lecturaArchivo();
        menu();
    }

    /**
     * Subprograma que tendra el menu principal por el cual se manejara el usuario,
     * derivando a los siguientes menus y resto de codigo
     */
    @Override
    public void menu() {
        String opcionEscogida = "";
        while(!opcionEscogida.equals("4")) {
            StdOut.println("""
                    ¡¡¡BIENVENIDO A BEAT THE RHYTHM!!!
                                            
                    [1] Agregar un Instrumento.
                    [2] Vender Instrumento.
                    [3] Consultar el inventario.
                                            
                    [4] Cierre.
                    """);
            StdOut.print("Escoja una opcion: ");
            opcionEscogida = StdIn.readLine();

            switch (opcionEscogida) {
                case "1" -> agregarInstrumento();
                case "2" -> vender();
                case "3" -> consultarInventario();
                case "4" -> cierre();
                default -> StdOut.println("Opcion no valida, por favor intente nuevamente");
            }
        }
    }

    /**
     *Este metodo nos permite agregar instrumentos en general y deriva a los
     * siguientes subprogramas para agregar segun el tipo de instrumento que se
     * desee agregar
     */
    @Override
    public void agregarInstrumento() {
        String tipoInstrumento = "";
        while(!tipoInstrumento.equals("meme centro")){
            StdOut.print("""
                
                Tipo de instrumento a agregar:
               
                [1] Cuerdas.
                [2] Viento.
                [3] Percusión.
                
                """);
            tipoInstrumento = StdIn.readLine();
            switch (tipoInstrumento) {
                case "1" -> {
                    agregarInstCuerda();
                    tipoInstrumento = "meme centro";
                }
                case "2" -> {
                    agregarInstViento();
                    tipoInstrumento = "meme centro";
                }
                case "3" -> {
                    agregarInstPercusion();
                    tipoInstrumento = "meme centro";
                }
                default -> StdOut.println("Por favor ingrese una opcion valida!");
            }
        }
    }

    /**
     * Añade un instrumento de tipo cuerda con sus respectivos atributos
     */
    public void agregarInstCuerda() {
        String codigo;
        StdOut.println("Ingrese los siguientes datos del instrumento de percusion.");
        while (true) {
            StdOut.print("Codigo: ");
            codigo = StdIn.readString();
            boolean verificador = verificarCodigo(codigo);
            if (!verificador) {
                break;
            } else {
                StdOut.println("¡El codigo que esta ingresado ya se encuentra registrado con otro instrumento!");
            }
        }
        StdOut.print("Precio: ");
        double precio = StdIn.readDouble();
        StdOut.print("Stock: ");
        int stock = StdIn.readInt();
        StdOut.print("Material (Madera, metal): ");
        String material = StdIn.readString();
        StdOut.print("Modelo instrumento (Guitarra, bajo, violin, arpa): ");
        String modeloInst = StdIn.readString();
        StdOut.print("Tipo de cuerda (Nylon, acero, tripa): ");
        String tipoCuerda = StdIn.readString();
        StdOut.print("Numero de cuerdas: ");
        int numeroCuerda = StdIn.readInt();
        StdOut.print("Tipo de instrumento (Electrico, acustico): ");
        String tipo = StdIn.readString();
        Instrumento instrumento = new Cuerda(codigo,precio,stock,material,modeloInst,tipoCuerda,numeroCuerda,tipo);
        listadoInstrumentos.agregar(instrumento);
    }

    /**
     * Añade un instrumento de tipo Viento con sus respectivos atributos
     */
    public void agregarInstViento() {
        String codigo;
        StdOut.println("Ingrese los siguientes datos del instrumento de viento.");
        while (true) {
            StdOut.print("Codigo: ");
            codigo = StdIn.readString();
            boolean verificador = verificarCodigo(codigo);
            if (!verificador) {
                break;
            } else {
                StdOut.println("¡El codigo que esta ingresado ya se encuentra registrado con otro instrumento!");
            }
        }
        StdOut.print("Precio: ");
        double precio = StdIn.readDouble();
        StdOut.print("Stock: ");
        int stock = StdIn.readInt();
        StdOut.print("Material (Madera, metal): ");
        String material = StdIn.readString();
        StdOut.print("Modelo instrumento (Trompeta, saxofon, clarinete, flauta traversa): ");
        String modeloInst = StdIn.readString();
        Instrumento instrumento = new Viento(codigo,precio,stock,material,modeloInst);
        listadoInstrumentos.agregar(instrumento);
    }

    /**
     * Añade un instrumento de tipo Persusion con sus respectivos atributos
     */
    public void agregarInstPercusion() {
        String codigo;
        StdOut.println("Ingrese los siguientes datos del instrumento de percusion.");
        while (true) {
            StdOut.print("Codigo: ");
            codigo = StdIn.readString();
            boolean verificador = verificarCodigo(codigo);
            if (!verificador) {
                break;
            } else {
                StdOut.println("¡El codigo que esta ingresado ya se encuentra registrado con otro instrumento!");
            }
        }
        StdOut.print("Precio: ");
        double precio = StdIn.readDouble();
        StdOut.print("Stock: ");
        int stock = StdIn.readInt();
        StdOut.print("Material (Madera, metal, piel): ");
        String material = StdIn.readString();
        StdOut.print("Modelo instrumento (Bongo, cajón, campanas tubulares, bombo): ");
        String modeloInst = StdIn.readString();
        StdOut.print("Tipo de percusión (Membranófono, idiófono): ");
        String tipoPercusion = StdIn.readString();
        StdOut.print("Tipo de altura (Definida, indefinida): ");
        String altura = StdIn.readString();
        Instrumento instrumento = new Percusion(codigo, precio, stock, material, modeloInst, tipoPercusion, altura);
        listadoInstrumentos.agregar(instrumento);
    }

    /**
     * subprograma que verifica que el codigo ingresado por pantalla coincida con algun
     * instrumento de la lista, devuelve un false si no lo encuentra y un true
     * si lo hace
     * @param codigo el cual se verificaria la existencia previa
     * @return true si es que exisita previamente, false si es que no existia
     */
    public boolean verificarCodigo (String codigo) {
        int largoLista = listadoInstrumentos.getCantidadActual();
        String auxCodigo;
        for (int i = 0; i < largoLista; i++) {
            Instrumento instrumento = listadoInstrumentos.obtenerInstrumento(i);
            auxCodigo = instrumento.getCodigo();
            if (codigo.equals(auxCodigo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que pide codigo por pantalla y despliega las caracteristicas del instrumento, solo si
     * hay stock disponible, le quita 1 al stock del instrumento luego de ser vendido
     */
    @Override
    public void vender() {
        StdOut.println("Ingrese el codigo del instrumento a vender: ");
        String codigoInst = StdIn.readString();
        boolean verificar = verificarStock(codigoInst);
        if (verificar) {
            StdOut.println("'No hay stock o no existe el instrumento que esta buscando!");
        } else {
            String auxCodigo;
            for (int i = 0; i < listadoInstrumentos.getCantidadActual(); i++) {
                Instrumento instrumento = listadoInstrumentos.obtenerInstrumento(i);
                auxCodigo = instrumento.getCodigo();
                if (codigoInst.equals(auxCodigo)) {
                    String nombreClase = instrumento.getClass().getName();
                    switch (nombreClase) {
                        case "Cuerda" -> {
                            StdOut.println("Esta comprando un instrumento de cuerdas con la siguiente informacion:");
                            listadoInstrumentos.informacion("Cuerda",i);
                            StdOut.print("¿Esta seguro de realizar la compra? [Si / No]: ");
                            String confirmacion = StdIn.readString();
                            if(confirmacion.equalsIgnoreCase("Si")){
                                despliegueBoleta("Cuerda",i);
                                instrumento.setStock(instrumento.getStock()-1);
                            }
                        }
                        case "Percusion" -> {
                            StdOut.println("Esta comprando un instrumento de percusion con la siguiente informacion:");
                            listadoInstrumentos.informacion("Percusion",i);
                            StdOut.print("¿Esta seguro de realizar la compra? [Si / No]: ");
                            String confirmacion = StdIn.readString();
                            if(confirmacion.equalsIgnoreCase("Si")){
                                despliegueBoleta("Percusion",i);
                                instrumento.setStock(instrumento.getStock()-1);
                            }
                        }
                        case "Viento" -> {
                            StdOut.println("Esta comprando un instrumento de viento con la siguiente informacion:");
                            listadoInstrumentos.informacion("Viento",i);
                            StdOut.print("¿Esta seguro de realizar la compra? [Si / No]: ");
                            String confirmacion = StdIn.readString();
                            if(confirmacion.equalsIgnoreCase("Si")){
                                despliegueBoleta("Viento",i);
                                instrumento.setStock(instrumento.getStock()-1);
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     * Subprograma que se utiliza al momento de estar haciendo una compra, con el cual se
     * confirma si es que hay stock suficiente para realizar la misma
     * @param codigo con el cual se buscaria el instrumento
     * @return false si es que hay stock, true si es que no hay stock disponible
     */
    @Override
    public boolean verificarStock(String codigo) {
        for (int i = 0; i < listadoInstrumentos.getCantidadActual(); i++){
            Instrumento instrumento = listadoInstrumentos.obtenerInstrumento(i);
            if (codigo.equals(instrumento.getCodigo())){
                if (instrumento.getStock() > 0){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Subprograma utilizado en el termino de una compra, para poder entregar la informacion
     * final de la misma.
     * @param nombreClase utilizado para conocer que tipo de instrumento es (Cuerda, Viento, Percusion)
     * @param posicion con la cual encontraremos el instrumento en el listadoInstrumentos
     */
    @Override
    public void despliegueBoleta(String nombreClase, int posicion) {
        StdOut.println("*-----------------------------------------*");
        StdOut.println("¡Boleta de compra!");
        StdOut.println("Instrumento Vendido:");
        listadoInstrumentos.informacion(nombreClase,posicion);
        StdOut.println("Gracias por preferirnos :).");
        StdOut.println("*-----------------------------------------*");
    }

    /**
     * Subprograma con el cual se daria la informacion de todos los instrumentos
     * agregados en el listadoInstrumentos, para poder conocer cuales estan a
     * disposicion.
     */
    @Override
    public void consultarInventario() {
        String tipoBusqueda;
        StdOut.println("""
                Seleccione tipo de busqueda:
                                
                [1] Busqueda Especifica.
                [2] Consulta Completa.
                                
                """);
        tipoBusqueda = StdIn.readString();
        switch (tipoBusqueda){
            case "1" -> {
                StdOut.println("Ingrese codigo del intrumento a buscar");
                String codigo = StdIn.readString();
                boolean verificar = verificarCodigo(codigo);
                if (verificar){
                    StdOut.println("'No hay stock o no existe el instrumento que esta buscando!");
                    }
                else {
                    String auxCodigo;
                    for (int i = 0; i < listadoInstrumentos.getCantidadActual(); i++) {
                        Instrumento instrumento = listadoInstrumentos.obtenerInstrumento(i);
                        auxCodigo = instrumento.getCodigo();
                        if (codigo.equals(auxCodigo)) {
                            String nombreClase = instrumento.getClass().getName();
                            switch (nombreClase) {
                                case "Cuerda" -> listadoInstrumentos.informacion("Cuerda", i);
                                case "Percusion" -> listadoInstrumentos.informacion("Percusion", i);
                                case "Viento" -> listadoInstrumentos.informacion("Viento", i);
                            }
                            break;
                        }
                    }
                }
            }
            case "2" -> {
                for (int i = 0 ; i < listadoInstrumentos.getCantidadActual(); i++) {
                    Instrumento instrumento = listadoInstrumentos.obtenerInstrumento(i);
                    String nombreClase = instrumento.getClass().getName();
                    StdOut.println("Instrumento N°" + (i + 1));
                    switch (nombreClase) {
                        case "Cuerda" -> listadoInstrumentos.informacion("Cuerda", i);
                        case "Percusion" -> listadoInstrumentos.informacion("Percusion", i);
                        case "Viento" -> listadoInstrumentos.informacion("Viento", i);
                    }
                    StdOut.println("");
                }
            }
            default -> StdOut.println("Opcion no valida, intente nuevamente");
        }
    }

    /**
     * En este subprograma se da el cierre al codigo, guardando los datos recibidos
     * por listadoInstrumentos, haciendo la escritura del archivo y terminando el
     * proceso del codigo
     */
    @Override
    public void cierre() {

    }
}
import ucn.*;
import java.io.IOException;
import java.util.InputMismatchException;

public class interfaceImpl implements Interface {

        private final ListaInstrumentos listadoInstrumentos;

    /**
     * Subprograma utilizado para la lectura previa de archivos, añadiendo los instrumentos al inventario,
     * si es que se encuentran instrumentos con el mismo codigo, se le sumaria el stock al primero que
     * se añadio.
     */
    @Override
    public void lecturaArchivo() throws IOException {
        //El nombre csv_prueba.csv corresponde al dado por el ayudante del taller.
        ArchivoEntrada archivo1 = new ArchivoEntrada("csv_prueba.csv");
        while (!archivo1.isEndFile()) {
            Registro regEnt = archivo1.getRegistro();
            String codigo = regEnt.getString();
            double precio = regEnt.getDouble();
            int stock = regEnt.getInt();
            String nombreInst = regEnt.getString();
            //verificacion de que sea un instrumento adecuado.
            if (nombreInst.equalsIgnoreCase("Guitarra") || nombreInst.equalsIgnoreCase("Bajo") ||
                    nombreInst.equalsIgnoreCase("violin") || nombreInst.equalsIgnoreCase("Arpa")) {
                String cuerda = regEnt.getString();
                int numeroCuerdas = regEnt.getInt();
                String material = regEnt.getString();
                String tipo = regEnt.getString();
                Instrumento instrumento = new Cuerda(codigo, precio, stock, material, nombreInst, cuerda, numeroCuerdas, tipo);
                boolean verificar = verificarCodigo(codigo);
                if (!verificar) {
                    listadoInstrumentos.agregar(instrumento);
                } else {
                    for (int i = 0; i < listadoInstrumentos.getCantidadActual(); i++) {
                        //Si el codigo del instrumento no existia, se añadira a la lista.
                        Instrumento instrumentoAux = listadoInstrumentos.obtenerInstrumento(i);
                        if (codigo.equals(instrumentoAux.getCodigo())) {
                            //Si es un codigo existente se sumaran los stocks de los instrumentos.
                            instrumentoAux.setStock(instrumentoAux.getStock() + stock);
                        }
                    }
                }
            }
            //Verificacion de que sea un instrumento adecuado para la clase percusion.
            if (nombreInst.equalsIgnoreCase("Bongo") || nombreInst.equalsIgnoreCase("cajón") ||
                    nombreInst.equalsIgnoreCase("campanas tubulares") || nombreInst.equalsIgnoreCase("bombo")) {
                String tipo = regEnt.getString();
                String material = regEnt.getString();
                String altura = regEnt.getString();
                Instrumento instrumento = new Percusion(codigo, precio, stock, nombreInst, tipo, material, altura);
                boolean verificar = verificarCodigo(codigo);
                if (!verificar) {
                    listadoInstrumentos.agregar(instrumento);
                } else {
                    for (int i = 0; i < listadoInstrumentos.getCantidadActual(); i++) {
                        //Si el codigo del instrumento no existia, se añadira a la lista.
                        Instrumento instrumentoAux = listadoInstrumentos.obtenerInstrumento(i);
                        if (codigo.equals(instrumentoAux.getCodigo())) {
                            //Si es un codigo existente se sumaran los stocks de los instrumentos.
                            instrumentoAux.setStock(instrumentoAux.getStock() + stock);
                        }
                    }
                }
            }
            //Verificacion para ver si es un instrumento de viento.
            if (nombreInst.equalsIgnoreCase("Trompeta") || nombreInst.equalsIgnoreCase("Saxofon") ||
                    nombreInst.equalsIgnoreCase("Clarinete") || nombreInst.equalsIgnoreCase("Flauta traversa")) {
                String material = regEnt.getString();
                Instrumento instrumento = new Viento(codigo, precio, stock, material, nombreInst);
                boolean verificar = verificarCodigo(codigo);
                if (!verificar) {
                    listadoInstrumentos.agregar(instrumento);
                } else {
                    for (int i = 0; i < listadoInstrumentos.getCantidadActual(); i++) {
                        //Si el codigo del instrumento no existia, se añadira a la lista.
                        Instrumento instrumentoAux = listadoInstrumentos.obtenerInstrumento(i);
                        if (codigo.equals(instrumentoAux.getCodigo())) {
                            //Si es un codigo existente se sumaran los stocks de los instrumentos.
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
        //Se crea la lista al iniciar.
        this.listadoInstrumentos = new ListaInstrumentos(100);
        //Se guardan los instrumentos en la lista.
        lecturaArchivo();
        //Se llama al menu para dar inicio al codigo.
        menu();
    }

    /**
     * Subprograma que tendra el menu principal por el cual se manejara el usuario,
     * derivando a los siguientes menus y resto de codigo
     */
    @Override
    public void menu() throws IOException {
        String opcionEscogida = "";
        while (!opcionEscogida.equals("4")) {
            StdOut.println("");
            StdOut.println("""
                    ¡¡¡BIENVENIDO A BEAT THE RHYTHM!!!
                                            
                    [1] Agregar un Instrumento.
                    [2] Vender Instrumento.
                    [3] Consultar el inventario.
                                            
                    [4] Cierre.
                    """);
            StdOut.print("Escoja una opcion: ");
            opcionEscogida = StdIn.readLine();
            StdOut.println("");

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
     * Este metodo nos permite agregar instrumentos en general y deriva a los
     * siguientes subprogramas para agregar segun el tipo de instrumento que se
     * desee agregar
     */
    @Override
    public void agregarInstrumento() {
        String tipoInstrumento = "";
        while (!tipoInstrumento.equals("meme centro")) {
            StdOut.print("""
                                    
                    Tipo de instrumento a agregar:
                                   
                    [1] Cuerdas.
                    [2] Viento.
                    [3] Percusión.
                                    
                    """);
            StdOut.print("Escoja una opcion: ");
            tipoInstrumento = StdIn.readLine();
            StdOut.println("");
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
        StdOut.println("--------------------------------------------------------------------------");
        StdOut.println("¡IMPORTANTE! si ingresa un dato erroneo tendra que repetir el proceso.");
        StdOut.println("--------------------------------------------------------------------------");
        StdOut.print("");
        String codigo;
        StdOut.println("Ingrese los siguientes datos del instrumento de Cuerda.");
        //Ciclo que contiene las verificaciones para los datos del instrumento a agregar.
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
        double precio;
        int stock;
        //Verifica que el dato ingresado sea el dato correspondiente, sino devuelve al menu.
        try {
            StdOut.print("Precio: ");
            precio = StdIn.readDouble();
            StdOut.print("Stock: ");
            stock = StdIn.readInt();
        } catch (InputMismatchException exception) {
            StdOut.println("Ingrese un valor valido");
            return;
        }
        StdOut.print("Material (Madera, metal): ");
        String material = StdIn.readString();
        if (!material.equalsIgnoreCase("madera") && !material.equalsIgnoreCase("metal")) {
            StdOut.println("Opcion no valida, debe escoger entre las opciones dadas");
            return;
        }
        StdOut.print("Modelo instrumento (Guitarra, bajo, violin, arpa): ");
        String modeloInst = StdIn.readString();
        if (!modeloInst.equalsIgnoreCase("guitarra") && !modeloInst.equalsIgnoreCase("bajo") && !modeloInst.equalsIgnoreCase("violin") && !modeloInst.equalsIgnoreCase("arpa")) {
            StdOut.println("Opcion no valida, debe escoger entre las opciones dadas");
            return;
        }
        StdOut.print("Tipo de cuerda (Nylon, acero, tripa): ");
        String tipoCuerda = StdIn.readString();
        if (!tipoCuerda.equalsIgnoreCase("Nylon") && !tipoCuerda.equalsIgnoreCase("acero") && !tipoCuerda.equalsIgnoreCase("tripa")) {
            StdOut.println("Opcion no valida, debe escoger entre las opciones dadas");
            return;
        }
        int numeroCuerda;
        //Verifica que el dato ingresado sea el dato correspondiente, sino devuelve al menu.
        try {
            StdOut.print("Numero de cuerdas: ");
            numeroCuerda = StdIn.readInt();
        } catch (InputMismatchException exception) {
            StdOut.println("Ingrese un valor valido");
            return;
        }
        StdOut.print("Tipo de instrumento (Electrico, acustico): ");
        String tipo = StdIn.readString();
        if (!tipo.equalsIgnoreCase("electrico") && !tipo.equalsIgnoreCase("acustico") && !tipo.equalsIgnoreCase("acustica")) {
            StdOut.println("Opcion no valida, debe escoger entre las opciones dadas");
            return;
        }
        Instrumento instrumento = new Cuerda(codigo, precio, stock, material, modeloInst, tipoCuerda, numeroCuerda, tipo);
        //Teniendo los datos correctos agrega el instrumento a la lista.
        listadoInstrumentos.agregar(instrumento);
        StdOut.println("¡Intrumento agregado exitosamente!");
    }


    /**
     * Añade un instrumento de tipo Viento con sus respectivos atributos
     */
    public void agregarInstViento() {
        String codigo;
        StdOut.println("--------------------------------------------------------------------------");
        StdOut.println("¡IMPORTANTE! si ingresa un dato erroneo tendra que repetir el proceso.");
        StdOut.println("--------------------------------------------------------------------------");
        StdOut.println("");
        StdOut.println("Ingrese los siguientes datos del instrumento de viento.");
        //Ciclo con los verificadores para los datos del instrumento a agregar.
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
        double precio;
        int stock;
        //Verifica que el dato ingresado sea el dato correspondiente, sino devuelve al menu.
        try {
            StdOut.print("Precio: ");
            precio = StdIn.readDouble();
            StdOut.print("Stock: ");
            stock = StdIn.readInt();
        } catch (InputMismatchException exception) {
            StdOut.println("Ingrese un valor valido");
            return;
        }
        StdOut.print("Material (Madera, metal): ");
        String material = StdIn.readString();
        if (!material.equalsIgnoreCase("madera") && !material.equalsIgnoreCase("metal")) {
            StdOut.println("Opcion no valida, debe escoger entre las opciones dadas");
            return;
        }
        StdOut.print("Modelo instrumento (Trompeta, saxofon, clarinete, flauta traversa): ");
        String modeloInst = StdIn.readString();
        if (!modeloInst.equalsIgnoreCase("trompeta") && !modeloInst.equalsIgnoreCase("saxofon")
                && !modeloInst.equalsIgnoreCase("clarinete") && !modeloInst.equalsIgnoreCase("flauta traversa")) {
            StdOut.println("Opcion no valida, debe escoger entre las opciones dadas");
            return;
        }
        Instrumento instrumento = new Viento(codigo, precio, stock, material, modeloInst);
        //Agrega el instrumento con los datos ingresados
        listadoInstrumentos.agregar(instrumento);
    }



    /**
     * Añade un instrumento de tipo Persusion con sus respectivos atributos
     */
    public void agregarInstPercusion() {
        String codigo;
        StdOut.println("--------------------------------------------------------------------------");
        StdOut.println("¡IMPORTANTE! si ingresa un dato erroneo tendra que repetir el proceso.");
        StdOut.println("--------------------------------------------------------------------------");
        StdOut.println("");
        StdOut.println("Ingrese los siguientes datos del instrumento de percusion.");
        //Ciclo con los verificadores correspondientes para la clase percusion.
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
        double precio;
        int stock;
        //Verifica que el dato ingresado sea el dato correspondiente, sino devuelve al menu.
        try {
            StdOut.print("Precio: ");
            precio = StdIn.readDouble();
            StdOut.print("Stock: ");
            stock = StdIn.readInt();
        } catch (InputMismatchException exception) {
            StdOut.println("Ingrese un valor valido");
            return;
        }
        StdOut.print("Material (Madera, metal, piel): ");
        String material = StdIn.readString();
        if (!material.equalsIgnoreCase("material") && !material.equalsIgnoreCase("metal") && !material.equalsIgnoreCase("piel")) {
            StdOut.println("Opcion no valida, debe escoger entre las opciones dadas");
            return;
        }
        StdOut.print("Modelo instrumento (Bongo, cajón, campanas tubulares, bombo): ");
        String modeloInst = StdIn.readString();
        if (!modeloInst.equalsIgnoreCase("bongo") && !modeloInst.equalsIgnoreCase("cajon") && !modeloInst.equalsIgnoreCase("campanas tubulares") && !modeloInst.equalsIgnoreCase("bombo")) {StdOut.println("Opcion no valida, debe escoger entre las opciones dadas");
            return;
        }
        StdOut.print("Tipo de percusión (Membranófono, idiófono): ");
        String tipoPercusion = StdIn.readString();
        if (!tipoPercusion.equalsIgnoreCase("membranofono") && !tipoPercusion.equalsIgnoreCase("idiofono")) {
            StdOut.println("Opcion no valida, debe escoger entre las opciones dadas");
            return;
        }
        StdOut.print("Tipo de altura (Definida, indefinida): ");
        String altura = StdIn.readString();
        if (!altura.equalsIgnoreCase("definida") && !altura.equalsIgnoreCase("indefinida")) {
            StdOut.println("Opcion no valida, debe escoger entre las opciones dadas");
            return;
        }
        Instrumento instrumento = new Percusion(codigo, precio, stock, material, modeloInst, tipoPercusion, altura);
        //Agrega el instrumento con la informacion entregada.
        listadoInstrumentos.agregar(instrumento);
    }


    /**
     * subprograma que verifica que el codigo ingresado por pantalla coincida con algun
     * instrumento de la lista, devuelve un false si no lo encuentra y un true
     * si lo hace
     * @param codigo el cual se verificaria la existencia previa
     * @return true si es que exisita previamente, false si es que no existia
     */
    public boolean verificarCodigo(String codigo) {
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
        StdOut.print("Ingrese el codigo del instrumento a vender: ");
        String codigoInst = StdIn.readString();
        boolean verificar = verificarStock(codigoInst);
        if (verificar) {
            StdOut.println("No hay stock o no existe el instrumento que esta buscando!");
        } else {
            String auxCodigo;
            for (int i = 0; i < listadoInstrumentos.getCantidadActual(); i++) {
                Instrumento instrumento = listadoInstrumentos.obtenerInstrumento(i);
                auxCodigo = instrumento.getCodigo();
                if (codigoInst.equals(auxCodigo)) {
                    String nombreClase = instrumento.getClass().getName();
                    //Se ve la clase del instrumento a vender para poder entregar la informacion necesaria.
                    switch (nombreClase) {
                        case "Cuerda" -> {
                            StdOut.println("Esta comprando un instrumento de cuerdas con la siguiente informacion:");
                            listadoInstrumentos.informacion("Cuerda", i);
                            StdOut.print("¿Esta seguro de realizar la compra? [Si / No]: ");
                            String confirmacion = StdIn.readString();
                            if (confirmacion.equalsIgnoreCase("Si")) {
                                despliegueBoleta("Cuerda", i);
                                instrumento.setStock(instrumento.getStock() - 1);
                            } else if (confirmacion.equalsIgnoreCase("no")){
                                StdOut.println("Compra rechazada, vuelva pronto! :)");
                            } else{
                                StdOut.println("Confirmacion no valida, intentelo nuevamente!");
                            }
                        }
                        case "Percusion" -> {
                            StdOut.println("Esta comprando un instrumento de percusion con la siguiente informacion:");
                            listadoInstrumentos.informacion("Percusion", i);
                            StdOut.print("¿Esta seguro de realizar la compra? [Si / No]: ");
                            String confirmacion = StdIn.readString();
                            if (confirmacion.equalsIgnoreCase("Si")) {
                                despliegueBoleta("Percusion", i);
                                instrumento.setStock(instrumento.getStock() - 1);
                            } else if (confirmacion.equalsIgnoreCase("no")){
                                StdOut.println("Compra rechazada, vuelva pronto! :)");
                            } else {
                                StdOut.println("Confirmacion no valida, intente nuevamente!");
                            }
                        }
                        case "Viento" -> {
                            StdOut.println("Esta comprando un instrumento de viento con la siguiente informacion:");
                            listadoInstrumentos.informacion("Viento", i);
                            StdOut.print("¿Esta seguro de realizar la compra? [Si / No]: ");
                            String confirmacion = StdIn.readString();
                            if (confirmacion.equalsIgnoreCase("Si")) {
                                despliegueBoleta("Viento", i);
                                instrumento.setStock(instrumento.getStock() - 1);
                            }
                            else if (confirmacion.equalsIgnoreCase("no")){
                                StdOut.println("Compra rechazada, vuelva pronto! :)");
                            } else {
                                StdOut.println("Confirmacion no valida, intente nuevamente!");
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
     *
     * @param codigo con el cual se buscaria el instrumento
     * @return false si es que hay stock, true si es que no hay stock disponible
     */
    @Override
    public boolean verificarStock(String codigo) {
        for (int i = 0; i < listadoInstrumentos.getCantidadActual(); i++) {
            Instrumento instrumento = listadoInstrumentos.obtenerInstrumento(i);
            if (codigo.equals(instrumento.getCodigo())) {
                if (instrumento.getStock() > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Subprograma utilizado en el termino de una compra, para poder entregar la informacion
     * final de la misma.
     *
     * @param nombreClase utilizado para conocer que tipo de instrumento es (Cuerda, Viento, Percusion)
     * @param posicion    con la cual encontraremos el instrumento en el listadoInstrumentos
     */
    @Override
    public void despliegueBoleta(String nombreClase, int posicion) {
        StdOut.println("*-----------------------------------------*");
        StdOut.println("¡Boleta de compra!");
        StdOut.println("Instrumento Vendido:");
        listadoInstrumentos.informacion(nombreClase, posicion);
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
        StdOut.println("");
        StdOut.println("""
                
                Seleccione tipo de busqueda:
                                
                [1] Busqueda Especifica.
                [2] Consulta Completa.
                
                """);
        StdOut.print("Escoja una opcion: ");
        tipoBusqueda = StdIn.readString();
        StdOut.println("");
        switch (tipoBusqueda) {
            case "1" -> {
                StdOut.print("Ingrese codigo del intrumento a buscar: ");
                String codigo = StdIn.readString();
                boolean verificar = verificarCodigo(codigo);
                if (!verificar) {
                    StdOut.println("'No hay stock o no existe el instrumento que esta buscando!");
                } else {
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
                for (int i = 0; i < listadoInstrumentos.getCantidadActual(); i++) {
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
    public void cierre() throws IOException {
        ArchivoSalida archivoFinal = new ArchivoSalida("csv_prueba.csv");
        for (int i = 0; i < listadoInstrumentos.getCantidadActual(); i++) {
            Instrumento instrumento = listadoInstrumentos.obtenerInstrumento(i);
            String claseInst = instrumento.getClass().getName();
            switch (claseInst){
                case "Cuerda" -> {
                    Registro regSal = new Registro(8);
                    Cuerda inst = (Cuerda) listadoInstrumentos.obtenerInstrumento(i);
                    regSal.agregarCampo(inst.getCodigo());
                    regSal.agregarCampo(inst.getPrecio());
                    regSal.agregarCampo(inst.getStock());
                    regSal.agregarCampo(inst.getModeloInst());
                    regSal.agregarCampo(inst.getCuerda());
                    regSal.agregarCampo(inst.getNumeroCuerda());
                    regSal.agregarCampo(inst.getMaterial());
                    regSal.agregarCampo(inst.getTipo());
                    archivoFinal.writeRegistro(regSal);
                }
                case "Viento" -> {
                    Registro regSal = new Registro(5);
                    Viento inst = (Viento) listadoInstrumentos.obtenerInstrumento(i);
                    regSal.agregarCampo(inst.getCodigo());
                    regSal.agregarCampo(inst.getPrecio());
                    regSal.agregarCampo(inst.getStock());
                    regSal.agregarCampo(inst.getModeloInst());
                    regSal.agregarCampo(inst.getMaterial());
                    archivoFinal.writeRegistro(regSal);
                }
                case "Percusion" -> {
                    Registro regSal = new Registro(7);
                    Percusion inst = (Percusion) listadoInstrumentos.obtenerInstrumento(i);
                    regSal.agregarCampo(inst.getCodigo());
                    regSal.agregarCampo(inst.getPrecio());
                    regSal.agregarCampo(inst.getStock());
                    regSal.agregarCampo(inst.getModeloInst());
                    regSal.agregarCampo(inst.getTipo());
                    regSal.agregarCampo(inst.getMaterial());
                    regSal.agregarCampo(inst.getAltura());
                    archivoFinal.writeRegistro(regSal);
                }
            }
        }
        archivoFinal.close();
        StdOut.println("Muchas gracias por preferirnos, vuelva pronto.");
    }
}
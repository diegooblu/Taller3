import ucn.StdIn;
import ucn.StdOut;

import java.util.Random;

public class interfaceImpl implements Interface{

    private ListaInstrumentos listadoInstrumentos;

    public interfaceImpl() {
        this.listadoInstrumentos = new ListaInstrumentos(100);
        menu();
    }

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
        StdOut.print("Modelo instrumento (Bongó, cajón, campanas tubulares, bombo): ");
        String modeloInst = StdIn.readString();
        StdOut.print("Tipo de percusión (Membranófono, idiófono): ");
        String tipoPercusion = StdIn.readString();
        StdOut.print("Tipo de altura (Definida, indefinida): ");
        String altura = StdIn.readString();
        Instrumento instrumento = new Percusion(codigo, precio, stock, material, modeloInst, tipoPercusion, altura);
        listadoInstrumentos.agregar(instrumento);
    }

    public boolean verificarCodigo (String codigo) {
        int largoLista = listadoInstrumentos.getCantidadActual();
        String auxCodigo = "";
        for (int i = 0; i < largoLista; i++) {
            Instrumento instrumento = listadoInstrumentos.obtenerInstrumento(i);
            auxCodigo = instrumento.getCodigo();
            if (codigo.equals(auxCodigo)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void vender() {
        StdOut.println("Ingrese el codigo del instrumento a vender: ");
        String codigoInst = StdIn.readString();
        boolean verificar = verificarStock(codigoInst);
        if (verificar) {
            StdOut.println("'No hay stock o no existe el instrumento que esta buscando!");
        } else {
            int largoLista = listadoInstrumentos.getCantidadActual();
            String auxCodigo = "";
            for (int i = 0; i < largoLista; i++) {
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
                            StdOut.print("¿Esta seguro de realizar la compra? [Si / No]");
                            String confirmacion = StdIn.readString();
                            if(confirmacion.equalsIgnoreCase("Si")){
                                despliegueBoleta("Percusion",i);
                                instrumento.setStock(instrumento.getStock()-1);
                            }
                        }
                        case "Viento" -> {
                            StdOut.println("Esta comprando un instrumento de viento con la siguiente informacion:");
                            listadoInstrumentos.informacion("Viento",i);
                            StdOut.print("¿Esta seguro de realizar la compra? [Si / No]");
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

    @Override
    public boolean verificarStock(String codigo) {
        return false;
    }

    @Override
    public void despliegueBoleta(String nombreClase, int posicion) {
        StdOut.println("*-----------------------------------------*");
        StdOut.println("¡Boleta de compra!");
        StdOut.println("Instrumento Vendido:");
        listadoInstrumentos.informacion(nombreClase,posicion);
        StdOut.println("Gracias por preferirnos :).");
        StdOut.println("*-----------------------------------------*");
    }

    @Override
    public void consultarInventario() {

    }

    @Override
    public void cierre() {

    }
}

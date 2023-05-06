import ucn.StdIn;
import ucn.StdOut;
public class interfaceImpl implements Interface{

    @Override
    public void menu() {
        int opcionEscogida = 0;
        while(opcionEscogida != 4) {
            StdOut.println("""
                    ¡¡¡BIENVENIDO A BEAT THE RHYTHM!!!
                                            
                    [1] Agregar un Instrumento.
                    [2] Vender Instrumento.
                    [3] Consultar el inventario.
                                            
                    [4] Cierre.
                    """);
            StdOut.print("Escoja una opcion");
            opcionEscogida = StdIn.readInt();

            switch (opcionEscogida) {
                case 1 -> agregarInstrumento();
                case 2 -> vender();
                case 3 -> consultarInventario();
                case 4 -> cierre();
                default -> StdOut.println("Opcion no valida, por favor intente nuevamenrte");
            }
        }
    }

    @Override
    public void agregarInstrumento() {

    }

    @Override
    public void vender() {

    }


    @Override
    public boolean verificarStock(int codigo) {
        return false;
    }

    @Override
    public void despliegueBoleta(int codigo) {

    }

    @Override
    public void consultarInventario() {

    }


    @Override
    public void cierre() {

    }
}

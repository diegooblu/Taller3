import java.io.IOException;

public interface Interface {

   /**
    * despliega el menu de acciones de el programa
    * que serian: Agregar Instrumento,Vender instrumento,
    * consultar inventario, Cierre.
    */
   void menu() throws IOException;

   /**
    * Metodo que lee archivo y agrega instrumentos a la listas
    */
   void lecturaArchivo() throws IOException;

   /**
    * Metodo que agrega un nstrumento a la lsta con sus debidos
    * atributos dependiendo de que tipo de instrumento de hable(viento, cuerda, percusion)
    */
   void agregarInstrumento();
   
   /**
    * Metodo el cual busca un intrumento y revisa su stock
    * si hay disponible, lo vende y resta 1 al stock actual del instrumento
    */

   void vender();

   /**
    * busca en la lista de instrumentos el Stock del instrumento cuyo codigo
    * es leido de pantalla
    * @param codigo con el cual se buscaria el instrumento
    * @return false si es que hay stock
    */
   boolean verificarStock(String codigo);

   /**
    * al realizar una venta, se utiliza este metodo para desplegar por pantalla
    * que producto fue vendido y cual fue su valor
    * @param nombreClase para identificar el tipo de instrumento que se esta vendiendo
    * @param posicion para encontrar en la lista la posicion del instrumento consultado
    */
   void despliegueBoleta(String nombreClase, int posicion);

   /**
    * Este metodo busca despliega el inventario de un instrumento en especifico
    * y da los detalles de este
    */
   void consultarInventario();

   /**
    * Cierra el programa y deja un mensaje al usuario
    */
   void cierre() throws IOException;
}
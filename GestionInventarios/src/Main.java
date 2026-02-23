import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Clase principal del sistema de gestión de inventario.
 * Contiene la rutina main() y el menú de interacción por consola (CLI).
 * Permite al usuario ejecutar las operaciones sobre la lista enlazada de productos.
 */
public class Main {

    // BufferedReader para capturar datos ingresados por el usuario desde consola
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // Instancia de la estructura dinámica (lista enlazada simple)
    private static ListaProductos lista = new ListaProductos();

    /**
     * Método principal del programa.
     * Invoca el menú para iniciar la interacción con el usuario.
     */
    public static void main(String[] args) {
        menu();
    }

    /**
     * Método que despliega el menú principal del sistema.
     * Permite al usuario seleccionar las distintas operaciones disponibles
     * para gestionar los productos dentro de la lista enlazada.
     */
    public static void menu() {

        int opcion = 0;

        // Ciclo do-while que mantiene activo el sistema hasta que el usuario decida salir
        do {
            try {
                System.out.println("\n===== SISTEMA DE GESTION DE INVENTARIO =====");
                System.out.println("1. Insertar producto al inicio");
                System.out.println("2. Insertar producto al final");
                System.out.println("3. Modificar producto");
                System.out.println("4. Eliminar producto");
                System.out.println("5. Agregar imagen a producto");
                System.out.println("6. Mostrar lista");
                System.out.println("7. Reporte de costos");
                System.out.println("8. Vaciar lista");
                System.out.println("9. Salir");
                System.out.print("Seleccione una opcion: ");

                // Se convierte la entrada String en entero
                opcion = Integer.parseInt(br.readLine());

                // Estructura de control que dirige la ejecución según la opción elegida
                switch (opcion) {

                    case 1:
                        insertarProducto(true);  // Inserta al inicio
                        break;

                    case 2:
                        insertarProducto(false); // Inserta al final
                        break;

                    case 3:
                        modificarProducto();
                        break;

                    case 4:
                        eliminarProducto();
                        break;

                    case 5:
                        agregarImagen();
                        break;

                    case 6:
                        lista.imprimirLista();
                        break;

                    case 7:
                        lista.imprimirReporteCostos();
                        break;

                    case 8:
                        lista.vaciar();
                        break;

                    case 9:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opcion invalida.");
                }

            } catch (NumberFormatException e) {
                // Manejo de error si el usuario ingresa un valor que no es numérico
                System.out.println("Error: Debe ingresar un numero valido.");
            } catch (IOException e) {
                // Manejo de error general de entrada/salida
                System.out.println("Error de entrada de datos.");
            }

        } while (opcion != 9); // El programa continúa hasta que el usuario seleccione salir
    }

    /**
     * Método encargado de solicitar los datos de un producto
     * y agregarlo a la lista enlazada, ya sea al inicio o al final.
     *
     * @param alInicio Indica si el producto se insertará al inicio (true)
     *                 o al final (false) de la lista.
     */
    private static void insertarProducto(boolean alInicio) throws IOException {

        System.out.print("Nombre: ");
        String nombre = br.readLine();

        System.out.print("Precio: ");
        double precio = Double.parseDouble(br.readLine());

        System.out.print("Categoria: ");
        String categoria = br.readLine();

        System.out.print("Fecha de vencimiento (Enter si no aplica): ");
        String fecha = br.readLine();

        // Si el usuario no ingresa fecha, se asigna null
        if (fecha.isBlank()) {
            fecha = null;
        }

        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(br.readLine());

        // Se crea un nuevo nodo con los datos ingresados
        NodoProducto nuevo = new NodoProducto(nombre, precio, categoria, fecha, cantidad);

        // Se inserta en la posición correspondiente
        if (alInicio) {
            lista.insertarAlInicio(nuevo);
        } else {
            lista.insertarAlFinal(nuevo);
        }

        System.out.println("Producto agregado correctamente.");
    }

    /**
     * Permite modificar los datos de un producto existente en la lista.
     * El usuario puede dejar campos sin modificar según las condiciones
     * establecidas en la clase ListaProductos.
     */
    private static void modificarProducto() throws IOException {

        System.out.print("Nombre del producto a modificar: ");
        String nombre = br.readLine();

        System.out.print("Nuevo nombre (Enter para no cambiar): ");
        String nuevoNombre = br.readLine();

        System.out.print("Nuevo precio (0 para no cambiar): ");
        double nuevoPrecio = Double.parseDouble(br.readLine());

        System.out.print("Nueva categoria (Enter para no cambiar): ");
        String nuevaCategoria = br.readLine();

        System.out.print("Nueva cantidad (-1 para no cambiar): ");
        int nuevaCantidad = Integer.parseInt(br.readLine());

        // Se invoca el método correspondiente en la estructura
        lista.modificarProducto(
                nombre,
                nuevoNombre,
                nuevoPrecio,
                nuevaCategoria,
                nuevaCantidad
        );
    }

    /**
     * Permite eliminar un producto de la lista enlazada
     * utilizando su nombre como criterio de búsqueda.
     */
    private static void eliminarProducto() throws IOException {

        System.out.print("Nombre del producto a eliminar: ");
        String nombre = br.readLine();

        lista.eliminarPorNombre(nombre);
    }

    /**
     * Permite agregar una nueva imagen (ruta) a un producto existente.
     * La ruta debe corresponder a un directorio del proyecto.
     */
    private static void agregarImagen() throws IOException {

        System.out.print("Nombre del producto: ");
        String nombre = br.readLine();

        System.out.print("Ruta de la imagen: ");
        String ruta = br.readLine();

        lista.agregarImagenAProducto(nombre, ruta);
    }
}
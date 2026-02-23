/**
 * Clase ListaProductos.
 *
 * Representa la estructura de datos dinámica implementada
 * como una lista enlazada simple para almacenar productos.
 *
 * Permite realizar operaciones típicas como inserción,
 * eliminación, modificación, recorrido y generación
 * de reportes sobre los productos almacenados.
 */
public class ListaProductos {

    // Referencia al primer nodo de la lista
    private NodoProducto cabeza;

    // Variable que mantiene el control del tamaño actual de la lista
    private int tamanio;

    /**
     * Constructor de la lista.
     * Inicializa la cabeza en null indicando que la lista comienza vacía.
     */
    public ListaProductos() {
        cabeza = null;
        tamanio = 0;
    }

    /**
     * Verifica si la lista se encuentra vacía.
     * @return true si no hay elementos, false en caso contrario.
     */
    public boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Devuelve la cantidad de nodos actualmente almacenados.
     */
    public int getTamanio() {
        return tamanio;
    }

    // =========================
    // Operaciones de inserción
    // =========================

    /**
     * Inserta un nodo al inicio de la lista enlazada.
     * El nuevo nodo pasa a ser la nueva cabeza.
     */
    public void insertarAlInicio(NodoProducto nodo) {
        nodo.setSiguiente(cabeza);
        cabeza = nodo;
        tamanio++;
    }

    /**
     * Inserta un nodo al final de la lista enlazada.
     * Recorre la lista hasta el último nodo y enlaza el nuevo nodo.
     */
    public void insertarAlFinal(NodoProducto nodo) {
        if (estaVacia()) {
            cabeza = nodo;
        } else {
            NodoProducto actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nodo);
        }
        tamanio++;
    }

    // =========================
    // Operaciones de búsqueda
    // =========================

    /**
     * Busca un producto por su nombre dentro de la lista.
     * La comparación se realiza sin distinguir mayúsculas/minúsculas.
     *
     * @param nombre Nombre del producto a buscar.
     * @return El nodo encontrado o null si no existe.
     */
    public NodoProducto buscarPorNombre(String nombre) {
        NodoProducto actual = cabeza;
        while (actual != null) {
            if (actual.getNombre().equalsIgnoreCase(nombre)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    // =========================
    // Operaciones de modificación
    // =========================

    /**
     * Modifica los atributos de un producto existente.
     * Solo actualiza los valores que cumplen las condiciones indicadas.
     */
    public boolean modificarProducto(String nombre, String nuevoNombre, double nuevoPrecio,
                                     String nuevaCategoria, int nuevaCantidad) {
        NodoProducto nodo = buscarPorNombre(nombre);

        if (nodo == null) {
            System.out.println("Producto no encontrado.");
            return false;
        }

        if (nuevoNombre != null && !nuevoNombre.isBlank())
            nodo.setNombre(nuevoNombre);

        if (nuevoPrecio > 0)
            nodo.setPrecio(nuevoPrecio);

        if (nuevaCategoria != null && !nuevaCategoria.isBlank())
            nodo.setCategoria(nuevaCategoria);

        if (nuevaCantidad >= 0)
            nodo.setCantidad(nuevaCantidad);

        System.out.println("Producto modificado correctamente.");
        return true;
    }

    /**
     * Agrega una nueva imagen (ruta) a un producto existente.
     */
    public boolean agregarImagenAProducto(String nombre, String rutaImagen) {
        NodoProducto nodo = buscarPorNombre(nombre);

        if (nodo == null) {
            System.out.println("Producto no encontrado.");
            return false;
        }

        nodo.agregarImagen(rutaImagen);
        System.out.println("Imagen agregada al producto.");
        return true;
    }

    // =========================
    // Operaciones de eliminación
    // =========================

    /**
     * Elimina el primer nodo de la lista.
     */
    public NodoProducto eliminarAlInicio() {
        if (estaVacia()) {
            System.out.println("La lista esta vacia.");
            return null;
        }

        NodoProducto eliminado = cabeza;
        cabeza = cabeza.getSiguiente();
        tamanio--;

        System.out.println("Producto eliminado del inicio.");
        return eliminado;
    }

    /**
     * Elimina el último nodo de la lista.
     */
    public NodoProducto eliminarAlFinal() {
        if (estaVacia()) {
            System.out.println("La lista esta vacia.");
            return null;
        }

        NodoProducto eliminado;

        if (cabeza.getSiguiente() == null) {
            eliminado = cabeza;
            cabeza = null;
        } else {
            NodoProducto actual = cabeza;
            while (actual.getSiguiente().getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            eliminado = actual.getSiguiente();
            actual.setSiguiente(null);
        }

        tamanio--;
        System.out.println("Producto eliminado del final.");
        return eliminado;
    }

    /**
     * Elimina un producto utilizando su nombre como criterio de búsqueda.
     */
    public boolean eliminarPorNombre(String nombre) {

        if (estaVacia()) {
            System.out.println("La lista esta vacia.");
            return false;
        }

        // Caso especial: el elemento a eliminar es la cabeza
        if (cabeza.getNombre().equalsIgnoreCase(nombre)) {
            eliminarAlInicio();
            return true;
        }

        NodoProducto actual = cabeza;

        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getNombre().equalsIgnoreCase(nombre)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamanio--;
                System.out.println("Producto eliminado.");
                return true;
            }
            actual = actual.getSiguiente();
        }

        System.out.println("Producto no encontrado.");
        return false;
    }

    // =========================
    // Otras operaciones auxiliares
    // =========================

    /**
     * Vacía completamente la lista enlazada.
     */
    public void vaciar() {
        cabeza = null;
        tamanio = 0;
        System.out.println("Lista vaciada.");
    }

    /**
     * Recorre la lista enlazada e imprime la información
     * de cada producto almacenado.
     */
    public void imprimirLista() {
        if (estaVacia()) {
            System.out.println("La lista esta vacia.");
            return;
        }

        NodoProducto actual = cabeza;
        int posicion = 1;

        while (actual != null) {
            System.out.println("\nProducto " + posicion + ":");
            System.out.println(actual.toString());
            actual = actual.getSiguiente();
            posicion++;
        }
    }

    /**
     * Genera un reporte de costos.
     * Calcula el costo total individual por producto
     * (precio * cantidad) y el costo total acumulado
     * de toda la lista.
     */
    public void imprimirReporteCostos() {
        if (estaVacia()) {
            System.out.println("La lista esta vacia.");
            return;
        }

        double costoAcumulado = 0;
        NodoProducto actual = cabeza;

        while (actual != null) {
            double costoTotal = actual.getPrecio() * actual.getCantidad();
            costoAcumulado += costoTotal;

            System.out.println("Producto: " + actual.getNombre() +
                    " - Costo total: " + costoTotal);

            actual = actual.getSiguiente();
        }

        System.out.println("Costo total acumulado: " + costoAcumulado);
    }
}
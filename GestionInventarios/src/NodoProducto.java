import java.util.ArrayList;

/**
 * Clase NodoProducto.
 * Representa un nodo dentro de la lista enlazada simple.
 *
 * Cada nodo almacena la información completa de un producto
 * y una referencia al siguiente nodo en la lista.
 *
 * Esta clase cumple una doble función:
 * 1. Actúa como nodo dentro de la estructura dinámica.
 * 2. Contiene los datos asociados a un producto del inventario.
 */
public class NodoProducto {

    // Atributos del producto
    private String nombre;
    private double precio;
    private String categoria;
    private String fechaVencimiento;
    private int cantidad;

    // Lista dinámica que almacena las rutas de imágenes asociadas al producto
    private ArrayList<String> listaImagenes;

    // Referencia al siguiente nodo en la lista enlazada
    private NodoProducto siguiente;

    /**
     * Constructor de la clase NodoProducto.
     * Inicializa los atributos del producto y crea la lista de imágenes.
     *
     * @param nombre Nombre del producto.
     * @param precio Precio unitario del producto.
     * @param categoria Categoría a la que pertenece el producto.
     * @param fechaVencimiento Fecha de vencimiento (puede ser null si no aplica).
     * @param cantidad Cantidad disponible del producto.
     */
    public NodoProducto(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.listaImagenes = new ArrayList<>(); // Inicialización de la lista dinámica
        this.siguiente = null; // Inicialmente no apunta a ningún otro nodo
    }

    // =========================
    // Métodos getters y setters
    // =========================

    // Devuelve el nombre del producto
    public String getNombre() { return nombre; }

    // Modifica el nombre del producto
    public void setNombre(String nombre) { this.nombre = nombre; }

    // Devuelve el precio del producto
    public double getPrecio() { return precio; }

    // Modifica el precio del producto
    public void setPrecio(double precio) { this.precio = precio; }

    // Devuelve la categoría del producto
    public String getCategoria() { return categoria; }

    // Modifica la categoría del producto
    public void setCategoria(String categoria) { this.categoria = categoria; }

    // Devuelve la fecha de vencimiento
    public String getFechaVencimiento() { return fechaVencimiento; }

    // Modifica la fecha de vencimiento
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    // Devuelve la cantidad disponible
    public int getCantidad() { return cantidad; }

    // Modifica la cantidad disponible
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    // Devuelve la lista de imágenes asociadas al producto
    public ArrayList<String> getListaImagenes() { return listaImagenes; }

    /**
     * Agrega una nueva ruta de imagen a la lista dinámica del producto.
     *
     * @param ruta Ruta donde se encuentra almacenada la imagen.
     */
    public void agregarImagen(String ruta) {
        listaImagenes.add(ruta);
    }

    // Devuelve la referencia al siguiente nodo
    public NodoProducto getSiguiente() { return siguiente; }

    // Establece la referencia al siguiente nodo
    public void setSiguiente(NodoProducto siguiente) { this.siguiente = siguiente; }

    /**
     * Método toString().
     * Devuelve una representación textual del producto,
     * utilizada para mostrar la información en consola.
     */
    @Override
    public String toString() {
        return "Nombre: " + nombre +
                "\nPrecio: " + precio +
                "\nCategoria: " + categoria +
                "\nFecha de vencimiento: " + (fechaVencimiento != null ? fechaVencimiento : "No aplica") +
                "\nCantidad: " + cantidad +
                "\nImagenes: " + listaImagenes;
    }
}
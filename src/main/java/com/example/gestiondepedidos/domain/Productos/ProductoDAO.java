package com.example.gestiondepedidos.domain.Productos;

/**
 * La interfaz ProductoDAO contiene los métodos que hemos utilizado para acceder a los datos de los productos.
 */
public interface ProductoDAO {
    /**
     * @param id Este id lo utilizamos para saber que producto solicitamos.
     * @return Devuelve un objeto producto según el id.
     */
    Producto getProduct(Long id);
}

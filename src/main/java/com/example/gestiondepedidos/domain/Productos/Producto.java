package com.example.gestiondepedidos.domain.Productos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * La clase Producto tiene un id de producto, su nombre, el precio y la cantidad disponible del producto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto implements Serializable {
    private Long id;
    private String nombre;
    private String precio;
    private int cantidad_disponible;
}

package com.example.gestiondepedidos.Productos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto implements Serializable {
    private Long id;
    private String nombre;
    private String precio;
    private int cantidad_disponible;
}

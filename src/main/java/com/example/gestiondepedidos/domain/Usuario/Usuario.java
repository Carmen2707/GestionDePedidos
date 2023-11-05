package com.example.gestiondepedidos.domain.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * La clase Usuario contiene los datos de un usuario. Cada usuario tiene su id, nombre, contraseña y email.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {
    private Long id;
    private String nombre;
    private String contraseña;
    private String email;


}

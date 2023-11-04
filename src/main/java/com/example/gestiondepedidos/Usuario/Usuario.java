package com.example.gestiondepedidos.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {
    private Long id;
    private String nombre;
    private String contraseña;
    private String email;


}

package com.example.gestiondepedidos.domain.Pedido;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * La clase Pedido representa los pedidos que el usuario registrado ha realizado. En sus datos se encuentra el id del pedido,
 * el código, la fecha en la que lo realizó, el usuario que lo realizó y el total.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido implements Serializable {
    private Long id;
    private String código;
    private Date fecha;
    private int usuario;
    private int total;
}

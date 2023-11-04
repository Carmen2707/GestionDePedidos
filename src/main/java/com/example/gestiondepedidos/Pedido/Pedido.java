package com.example.gestiondepedidos.Pedido;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

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

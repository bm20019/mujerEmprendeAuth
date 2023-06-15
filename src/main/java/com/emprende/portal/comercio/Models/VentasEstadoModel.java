package com.emprende.portal.comercio.Models;

import java.io.Serializable;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;

@Entity
@Table(name = "ventas_estado", catalog = "", schema = "comercios")
public class VentasEstadoModel implements Serializable{

    private static final long serialVersionUID = 1L;

    public VentasEstadoModel() {
    }

    @Id
    @Getter
    @Setter
    @Column(name = "id_venta_estado")
    private Long idVentaEstado;

    @Getter
    @Setter
    @Column(name = "nombre")
    private String nombre;

}

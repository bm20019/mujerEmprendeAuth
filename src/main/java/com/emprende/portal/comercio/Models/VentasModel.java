package com.emprende.portal.comercio.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "ventas", catalog = "", schema = "comercios")
public class VentasModel implements Serializable{

    private static final long serialVersionUID = 1L;

    public VentasModel() {
    }

    @Id
    @Getter
    @Setter
    @Column(name = "id_venta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private UsuariosModel idUsuario;

    @Getter
    @Setter
    @Column(name = "fecha_anula")
    private Date fechaNula;

    @Getter
    @Setter
    @Column(name = "fecha_pago")
    private Date fechaPago;

    @Getter
    @Setter
    @Column(name = "observaciones")
    private String observaciones;

    @Getter
    @Setter
    @Column(name = "created")
    private LocalDate created;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_comercio", referencedColumnName = "id_comercio")
    private ComerciosModel idComercio;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_venta_estado", referencedColumnName = "id_venta_estado")
    private VentasEstadoModel idVentaEstado;

}

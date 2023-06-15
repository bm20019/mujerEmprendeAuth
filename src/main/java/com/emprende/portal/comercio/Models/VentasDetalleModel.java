package com.emprende.portal.comercio.Models;

import java.io.Serializable;
import java.time.LocalDate;

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
@Table(name = "ventas_detalle", catalog = "", schema = "comercios")
public class VentasDetalleModel implements Serializable{

    private static final long serialVersionUID = 1L;

    public VentasDetalleModel() {
    }

    @Id
    @Getter
    @Setter
    @Column(name = "id_venta_detalle")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVentaDetalle;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
    private VentasModel idVenta;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_catalogo", referencedColumnName = "id_catalogo")
    private CatalogosModel idCatalogos;

    @Getter
    @Setter
    @Column(name = "cantidad")
    private Long cantidad;

    @Getter
    @Setter
    @Column(name = "precio")
    private Double precio;

    @Getter
    @Setter
    @Column(name = "total")
    private Double total;

    @Getter
    @Setter
    @Column(name = "created")
    private LocalDate created;

}

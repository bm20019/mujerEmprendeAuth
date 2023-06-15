package com.emprende.portal.comercio.Models;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "catalogos", catalog = "", schema = "comercios")

public class CatalogosModel {

    private static final long serialVersionUID = 1L;
    public CatalogosModel() {
    }
    @Id
    @Getter
    @Setter
    @Column(name = "id_catalogo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCatalogos;

    @Getter
    @Setter
    @Column(name = "nombre")
    private String nombre;

    @Getter
    @Setter
    @Column(name = "descripcion")
    private String descripcion;

    @Getter
    @Setter
    @Column(name = "precio")
    private Double precio;

    @Getter
    @Setter
    @Column(name = "imagen")
    private String imagen;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_comercio", referencedColumnName = "id_comercio")
    private ComerciosModel idComercio;

    @Getter
    @Setter
    @Column(name = "activo")
    private int activo;

    @Getter
    @Setter
    @Column(name = "created")
    private LocalDate created;

}

package com.emprende.portal.comercio.Repository;

import com.emprende.portal.comercio.Models.VentasDetalleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ventasDetalleRepository extends JpaRepository<VentasDetalleModel, Long> {
}
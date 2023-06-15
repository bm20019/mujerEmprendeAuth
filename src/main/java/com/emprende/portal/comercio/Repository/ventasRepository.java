package com.emprende.portal.comercio.Repository;

import com.emprende.portal.comercio.Models.VentasModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ventasRepository extends JpaRepository<VentasModel, Long> {

    public Optional<VentasModel> findByIdVenta(Long codigo);
    public List<VentasModel> findByFechaNulaIsNull(); // Recupera todas las ventas no anuladas
    public List<VentasModel> findByFechaNulaIsNotNull(); // Recupera todas las ventas anuladas

}
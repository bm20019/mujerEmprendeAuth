package com.emprende.portal.comercio.Repository;

import com.emprende.portal.comercio.Models.CatalogosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface catalogosRepository extends JpaRepository<CatalogosModel, Long> {
    @Query(value = "select * from comercios.catalogos c where c.id_comercio = :idComercio", nativeQuery = true)
    public List<CatalogosModel> findCatalogosByIdComercio(@Param("idComercio") Long idComercio);
}

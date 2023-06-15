package com.emprende.portal.comercio.Repository;

import com.emprende.portal.comercio.Models.ComerciosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface comerciosRepository extends JpaRepository<ComerciosModel, Long> {
}

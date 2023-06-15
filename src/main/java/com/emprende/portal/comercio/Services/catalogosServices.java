package com.emprende.portal.comercio.Services;

import com.emprende.portal.comercio.Models.CatalogosModel;
import com.emprende.portal.comercio.dto.responseDto;

public interface catalogosServices {
	public responseDto getCatalogosId(Long codigo);
    public responseDto getProductoComercio(Long codigo);
    public responseDto updateCatalogos(CatalogosModel catalogos, Long codigo);
    public responseDto saveCatalogos(CatalogosModel catalogos);
}

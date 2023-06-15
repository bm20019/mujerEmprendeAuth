package com.emprende.portal.comercio.Services;

import com.emprende.portal.comercio.Models.CatalogosModel;
import com.emprende.portal.comercio.Repository.catalogosRepository;
import com.emprende.portal.comercio.dto.responseDto;
import com.emprende.portal.comercio.dto.responseDtoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class catalogosServicesImpl implements catalogosServices{
	responseDto rsp = new responseDto();

    @Autowired
    catalogosRepository catalogosRepo;

    @Override
    public responseDto getCatalogosId(Long codigo){
        try {
            Optional<CatalogosModel> catalogosOptional = catalogosRepo.findById(codigo);
            if (catalogosOptional.isPresent()) {
            	CatalogosModel cata = catalogosOptional.get();
                rsp.setResponse("Catalogo encontrado",cata, responseDtoEnum.OK);
            } else {
                rsp.setResponse("Catalogo no encontrado", responseDtoEnum.NO_CONTENT);
            }
        } catch (Exception e) {
            rsp.setResponse("Error al buscar el catalogo", responseDtoEnum.INTERNAL_SERVER_ERROR);
        }
        return rsp;
    }
    @Override
    public responseDto getProductoComercio(Long codigo){
        try {
            List<CatalogosModel> catalogosList = catalogosRepo.findCatalogosByIdComercio(codigo);
            if (!catalogosList.isEmpty()) {
                rsp.setResponse("Catalogo encontrado", catalogosList, responseDtoEnum.OK);
            } else {
                rsp.setResponse("Catalogo no encontrado", responseDtoEnum.NO_CONTENT);
            }
        } catch (Exception e) {
            rsp.setResponse("Erro al buscar el catalogo", e.getMessage(), responseDtoEnum.INTERNAL_SERVER_ERROR);
        }
        return rsp;
    }
    @Override
    public responseDto updateCatalogos(CatalogosModel cata, Long codigo){
        try {    
            Optional<CatalogosModel> catalogosOptional = catalogosRepo.findById(codigo);
            if (catalogosOptional.isPresent()) {
            	CatalogosModel venta = catalogosOptional.get();
                venta.setNombre(cata.getNombre());
                venta.setDescripcion(cata.getDescripcion());
                venta.setPrecio(cata.getPrecio());
                venta.setImagen(cata.getImagen());        
                venta.setActivo(cata.getActivo());
                rsp.setResponse("Catalogo actualizado correctamente", venta, responseDtoEnum.OK);
                catalogosRepo.save(venta);
            } else {
                rsp.setResponse("Catalogo no encontrado", responseDtoEnum.NO_CONTENT);
            }
        } catch (Exception e) {
            rsp.setResponse("Error al actualizar el catalogo",e.getMessage(), responseDtoEnum.INTERNAL_SERVER_ERROR);
        }
        return rsp;
    }
    @Override
    public responseDto saveCatalogos(CatalogosModel cata){
        try {
            rsp.setResponse("Catalogo guardado correctamente",cata, responseDtoEnum.OK);
            catalogosRepo.save(cata);
        } catch (Exception e) {
            rsp.setResponse("Erro al guardar el catalogo", e.getMessage(), responseDtoEnum.INTERNAL_SERVER_ERROR);
        } 
        return rsp;
    }
}

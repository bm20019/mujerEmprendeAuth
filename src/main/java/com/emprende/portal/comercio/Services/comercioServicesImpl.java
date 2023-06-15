package com.emprende.portal.comercio.Services;

import com.emprende.portal.comercio.Models.ComerciosModel;
import com.emprende.portal.comercio.Repository.comerciosRepository;
import com.emprende.portal.comercio.dto.responseDto;
import com.emprende.portal.comercio.dto.responseDtoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class comercioServicesImpl implements comercioServices{
	responseDto rsp = new responseDto();
	
	@Autowired
	comerciosRepository comercioRepo;

	@Override
	public responseDto getComerciosId(Long codigo) {
		try {
			Optional<ComerciosModel> comercioOptional = comercioRepo.findById(codigo);
			if(comercioOptional.isPresent()) {
				ComerciosModel comercio = comercioOptional.get();
				return new responseDto("Comercio encontrado", comercio, responseDtoEnum.OK);
			} else {
				rsp.setResponse("Comercio no encontrado", responseDtoEnum.NO_CONTENT);
			}
		} catch (Exception e) {
			rsp.setResponse("Error al buscar el comercio", e.getMessage(), responseDtoEnum.INTERNAL_SERVER_ERROR);
		}
		return rsp;
	}
	@Override
	public responseDto getAllComercios() {
		try {
			List<ComerciosModel> comercioList = comercioRepo.findAll();

			if(!comercioList.isEmpty()) {
				rsp.setResponse("Comercio encontrado", comercioList, responseDtoEnum.OK);
			} else {
				rsp.setResponse("Comercio no encontrado", responseDtoEnum.NO_CONTENT);
			}
		} catch (Exception e) {
			rsp.setResponse("Error al buscar la comercio", e.getMessage(), responseDtoEnum.INTERNAL_SERVER_ERROR);
		}
		return rsp;
	}
	@Override
	public responseDto updateComercios(ComerciosModel comercios, Long codigo) {
		try {
			Optional<ComerciosModel> comercioOptional = comercioRepo.findById(codigo);
            if (comercioOptional.isPresent()) {
                ComerciosModel comercio = comercioOptional.get();
                comercio.setNombre(comercios.getNombre());
                comercio.setDescripcion(comercios.getDescripcion());
                comercio.setLogo(comercios.getLogo());
                comercio.setActivo(comercios.getActivo());
				rsp.setResponse("Comercio actualizado correctamente",comercio, responseDtoEnum.OK);
                comercioRepo.save(comercio);
            } else {
				rsp.setResponse("Comercio no encontrada",responseDtoEnum.NO_CONTENT);
            }
		} catch (Exception e) {
			rsp.setResponse("Error al actualizar el comercio", responseDtoEnum.INTERNAL_SERVER_ERROR);
		}
		return rsp;
	}
	@Override
	public responseDto saveComercios(ComerciosModel comercios) {
		try {
			rsp.setResponse("Comercio guardado correctamente",comercios, responseDtoEnum.CREATED);
            comercioRepo.save(comercios);
		} catch (Exception e) {
			rsp.setResponse("Error al guardar el comercio", e.getMessage(), responseDtoEnum.INTERNAL_SERVER_ERROR);
		}
		return rsp;
	}
	public responseDto getProductosPorComercio(Long comercioId) {
		Optional<ComerciosModel> comercioOptional = comercioRepo.findById(comercioId);

		if (comercioOptional.isPresent()) {
			ComerciosModel comercio = comercioOptional.get();
			rsp.setResponse("Catalogo encontrado.", comercio.getProductos(), responseDtoEnum.OK);
		}
		return rsp;
	}
}
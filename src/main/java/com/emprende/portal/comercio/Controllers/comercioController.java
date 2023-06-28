package com.emprende.portal.comercio.Controllers;

import com.emprende.portal.comercio.Models.ComerciosModel;
import com.emprende.portal.comercio.Services.comercioServices;
import com.emprende.portal.comercio.dto.responseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("comercio/comercio")
@CrossOrigin(origins = "http://localhost:4200")
public class comercioController {
	@Autowired
	comercioServices comercio;

	@RequestMapping(method = RequestMethod.POST, value ="/save")
	public responseDto saveComercio(@RequestBody ComerciosModel comercios,@RequestParam(name = "created", defaultValue = "#{T(java.time.LocalDate).now()}", required = true) LocalDate created) {
		comercios.setCreated(created);
		return comercio.saveComercios(comercios);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search/{codigo}")
	public responseDto getComerciosId(@PathVariable Long codigo) {
		return comercio.getComerciosId(codigo);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search")
	public responseDto getAllComercios() {
		return comercio.getAllComercios();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/update/{codigo}")
	public responseDto updateComercios(@RequestBody ComerciosModel comercios, @PathVariable Long codigo) {
		return comercio.updateComercios(comercios, codigo);
	}

	@RequestMapping("/{comercioId}/productos")
	public responseDto getProductosPorComercio(@PathVariable Long comercioId) {
		return comercio.getProductosPorComercio(comercioId);
	}
}

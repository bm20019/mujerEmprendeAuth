package com.emprende.portal.comercio.Controllers;

import com.emprende.portal.comercio.Models.CatalogosModel;
import com.emprende.portal.comercio.Services.catalogosServices;
import com.emprende.portal.comercio.dto.responseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("comercio/catalogos")
@CrossOrigin(origins = "http://localhost:4200")
public class catalogosController {
    @Autowired
    catalogosServices catalogosservice;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public responseDto saveCatalogos(@RequestBody CatalogosModel cata, @RequestParam(name = "created", defaultValue = "#{T(java.time.LocalDate).now()}", required = true) LocalDate created){
        cata.setCreated(created);
        return catalogosservice.saveCatalogos(cata);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{codigo}")
    public responseDto getCatalogosId(@PathVariable Long codigo){
        return catalogosservice.getCatalogosId(codigo);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/productcomercio/{codigo}")
    public responseDto getProductoComercio(@PathVariable Long codigo){
        return catalogosservice.getProductoComercio(codigo);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/update/{codigo}")
    public responseDto updateCatalogos(@RequestBody CatalogosModel cata, @PathVariable Long codigo){
        return catalogosservice.updateCatalogos(cata, codigo);
    }
}
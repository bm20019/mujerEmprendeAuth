package com.emprende.portal.comercio.Controllers;

import com.emprende.portal.comercio.Models.VentasDetalleModel;
import com.emprende.portal.comercio.Services.ventasDetalleServices;
import com.emprende.portal.comercio.dto.responseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("comercio/ventasdetalle")
@CrossOrigin(origins = "http://localhost:4200")
public class ventasDetalleController {

    @Autowired
    ventasDetalleServices ventaDetalle;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public responseDto saveVentasDetalle(@RequestBody VentasDetalleModel ventas, @RequestParam(name = "created", defaultValue = "#{T(java.time.LocalDate).now()}", required = true) LocalDate created){
        ventas.setCreated(created);
        return ventaDetalle.saveVentasDetalle(ventas);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{codigo}")
    public responseDto getVentasId(@PathVariable Long codigo){
        return ventaDetalle.getVentasDetalleId(codigo);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/update/{codigo}")
    public responseDto updateVentas(@RequestBody VentasDetalleModel ventas, @PathVariable Long codigo){
        return ventaDetalle.updateVentasDetalle(ventas, codigo);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/searchAll/{codigo}")
    public responseDto getAllVentas(@PathVariable Long codigo){
        return ventaDetalle.getAllVentasDetalle(codigo);
    }

}

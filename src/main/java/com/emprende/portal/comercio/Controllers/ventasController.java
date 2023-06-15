package com.emprende.portal.comercio.Controllers;

import com.emprende.portal.comercio.Models.VentasModel;
import com.emprende.portal.comercio.Services.ventasServices;
import com.emprende.portal.comercio.dto.responseDto;
import com.emprende.portal.comercio.dto.responseDtoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("comercio/ventas")
@CrossOrigin(origins = "http://localhost:4200")
public class ventasController {

    @Autowired
    ventasServices venta;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public responseDto saveVentas(@RequestBody VentasModel ventas, @RequestParam(name = "created", defaultValue = "#{T(java.time.LocalDate).now()}", required = true) LocalDate created){
        ventas.setCreated(created);
        return venta.saveVentas(ventas);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{codigo}")
    public responseDto getVentasId(@PathVariable Long codigo){
        return venta.getVentasId(codigo);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/update/{codigo}")
    public responseDto updateVentas(@RequestBody VentasModel ventas, @PathVariable Long codigo){
        return venta.updateVentas(ventas, codigo);
    }
    @RequestMapping(method = RequestMethod.PUT,value = "/updateestado/{codigo}")
    public responseDto updateEstado(@RequestBody VentasModel ventas, @PathVariable Long codigo){
        return venta.updateEstado(ventas, codigo);
    }
    @RequestMapping("/{ventaId}/anular")
    public responseDto anularVenta(@PathVariable Long ventaId, @RequestBody VentasModel request) {
        if (venta.anularVenta(ventaId, request.getFechaNula(), request.getObservaciones()).getCodigo() == 200) {
            return new responseDto("Venta anulada exitosamente", responseDtoEnum.OK);
        } else {
            return new responseDto("Error al anular venta", responseDtoEnum.BAD_REQUEST);
        }
    }

}

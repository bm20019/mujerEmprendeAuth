package com.emprende.portal.comercio.Services;

import com.emprende.portal.comercio.Models.VentasModel;
import com.emprende.portal.comercio.Repository.ventasRepository;
import com.emprende.portal.comercio.dto.responseDto;
import com.emprende.portal.comercio.dto.responseDtoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ventasServicesImpl implements ventasServices {

    responseDto rsp = new responseDto();

    @Autowired
    ventasRepository ventaRepo;

    @Override
    public responseDto getVentasId(Long codigo){
        try {
            Optional<VentasModel> ventaOptional = ventaRepo.findById(codigo);
            if (ventaOptional.isPresent()) {
                VentasModel venta = ventaOptional.get();
                rsp.setResponse("Venta encontrada", venta, responseDtoEnum.OK);
            } else {
                rsp.setResponse("Venta no encontrada", responseDtoEnum.NOT_FOUND);
            }
        } catch (Exception e) {
            rsp.setResponse("Error al buscar la venta", e.getMessage(),responseDtoEnum.INTERNAL_SERVER_ERROR);
        }
        return rsp;
    }

    @Override
    public responseDto updateVentas(VentasModel ventas, Long codigo){
        try {    
            Optional<VentasModel> ventaOptional = ventaRepo.findById(codigo);
            if (ventaOptional.isPresent()) {
                VentasModel venta = ventaOptional.get();
                venta.setFechaNula(ventas.getFechaNula());
                venta.setFechaPago(ventas.getFechaPago());
                venta.setObservaciones(ventas.getObservaciones());
                ventaRepo.save(venta);
                rsp.setResponse("Venta actualizada correctamente", venta, responseDtoEnum.OK);
            } else {
                rsp.setResponse("Venta no enccontrada", responseDtoEnum.NOT_FOUND);
            }
        } catch (Exception e) {
            rsp.setResponse("Error al actualizar la venta", e.getMessage(), responseDtoEnum.INTERNAL_SERVER_ERROR);
        }
        return rsp;
    }

    @Override
    public responseDto saveVentas(VentasModel ventas){
        try {
            ventaRepo.save(ventas);
            rsp.setResponse("Venta guardada correctamente", ventas, responseDtoEnum.OK);
        } catch (Exception e) {
            rsp.setResponse("Error al guarda la venta", e.getMessage(), responseDtoEnum.INTERNAL_SERVER_ERROR);
        } 
        return rsp;
    }

    @Override
    public responseDto updateEstado(VentasModel ventas, Long codigo){        
        try {
            Optional<VentasModel> ventaOptional = ventaRepo.findById(codigo);
            if (ventaOptional.isPresent()) {
                VentasModel venta = ventaOptional.get();
                venta.setIdVentaEstado(ventas.getIdVentaEstado());
                ventaRepo.save(venta);
                rsp.setResponse("Estado de Venta actualizada correctamente",venta, responseDtoEnum.OK);
            } else {
                rsp.setResponse("Venta no encontrada", responseDtoEnum.NOT_FOUND);
            }
        } catch (Exception e) {
            rsp.setResponse("Error al actualizar la venta", responseDtoEnum.BAD_REQUEST);
        }
        return rsp;
    }

    @Override
    public responseDto anularVenta(Long ventaId, Date fechaNula, String observaciones) {
        Optional<VentasModel> ventaOptional = ventaRepo.findById(ventaId);

        if (ventaOptional.isPresent()) {
            VentasModel venta = ventaOptional.get();
            venta.setFechaNula(fechaNula);
            venta.setObservaciones(observaciones);
            ventaRepo.save(venta);
            return new responseDto("Venta Anulada",responseDtoEnum.OK);
        }
        return new responseDto("La Venta no ha sido anulada",responseDtoEnum.BAD_REQUEST);
    }

    @Override
    public responseDto marcarVentaComoPagada(Long ventaId, Date fechaPago) {
        Optional<VentasModel> ventaOptional = ventaRepo.findById(ventaId);

        if (ventaOptional.isPresent()) {
            VentasModel venta = ventaOptional.get();
            venta.setFechaPago(fechaPago);
            ventaRepo.save(venta);
            return new responseDto("Venta ha sido marcada como pagada",venta, responseDtoEnum.OK);
        }
        return new responseDto("No se ha podido marcar venta como pagada", responseDtoEnum.BAD_REQUEST);
    }
}

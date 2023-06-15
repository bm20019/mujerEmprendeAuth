package com.emprende.portal.comercio.Services;

import com.emprende.portal.comercio.Models.VentasDetalleModel;
import com.emprende.portal.comercio.Repository.ventasDetalleRepository;
import com.emprende.portal.comercio.dto.responseDto;
import com.emprende.portal.comercio.dto.responseDtoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ventasDetalleServicesImpl implements ventasDetalleServices {

    responseDto rsp = new responseDto();

    @Autowired
    ventasDetalleRepository ventaDetalleRepo;

    @Override
    public responseDto getAllVentasDetalle(Long VentaId) {
        try {
            List<VentasDetalleModel> ventaOptional = ventaDetalleRepo.findAll();
            if (!ventaOptional.isEmpty()) {
                rsp.setResponse("Venta encontrada", ventaOptional, responseDtoEnum.OK);
            } else {
                rsp.setResponse("Venta no encontrada", responseDtoEnum.NO_CONTENT);
            }
        } catch (Exception e) {
            rsp.setResponse("Error al buscar la venta",e.getMessage(),responseDtoEnum.INTERNAL_SERVER_ERROR);
        }
        return rsp;
    }

    @Override
    public responseDto getVentasDetalleId(Long ventaDetalleId) {
        try {
            Optional<VentasDetalleModel> ventaOptional = ventaDetalleRepo.findById(ventaDetalleId);
            if (ventaOptional.isPresent()) {
                VentasDetalleModel ventaDetalle = ventaOptional.get();
                rsp.setResponse("Venta encontrada",ventaDetalle,responseDtoEnum.OK);
            } else {
                rsp.setResponse("Venta no encontrada",responseDtoEnum.NO_CONTENT);
            }
        } catch (Exception e) {
            rsp.setResponse("Error al buscar la venta", e.getMessage(),responseDtoEnum.INTERNAL_SERVER_ERROR);
        }
        return rsp;
    }

    @Override
    public responseDto updateVentasDetalle(VentasDetalleModel ventaDetalle, Long codigo) {
        try {    
            Optional<VentasDetalleModel> ventaOptional = ventaDetalleRepo.findById(codigo);
            if (ventaOptional.isPresent()) {
                VentasDetalleModel venta = ventaOptional.get();
                venta.setIdCatalogos(ventaDetalle.getIdCatalogos());
                venta.setCantidad(ventaDetalle.getCantidad());  
                venta.setPrecio(ventaDetalle.getPrecio());
                venta.setTotal(ventaDetalle.getTotal());
                rsp.setResponse("Venta actualizada correctamente",venta,responseDtoEnum.OK);
                ventaDetalleRepo.save(venta);
            } else {
                rsp.setResponse("Venta no encontrada",responseDtoEnum.NO_CONTENT);
            }
        } catch (Exception e) {
            rsp.setResponse("Error al actualizar la venta",e.getMessage(),responseDtoEnum.INTERNAL_SERVER_ERROR);
        }
        return rsp;
    }

    @Override
    public responseDto saveVentasDetalle(VentasDetalleModel ventaDetalle) {
        try {
            rsp.setResponse("Venta guardada correctamente",ventaDetalle, responseDtoEnum.OK);
            ventaDetalleRepo.save(ventaDetalle);
        } catch (Exception e) {
            rsp.setResponse("Error al guardar la venta",e.getMessage(),responseDtoEnum.INTERNAL_SERVER_ERROR);
        } 
        return rsp;
    }
}

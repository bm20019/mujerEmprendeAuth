package com.emprende.portal.comercio.Services;

import com.emprende.portal.comercio.Models.VentasDetalleModel;
import com.emprende.portal.comercio.dto.responseDto;

public interface ventasDetalleServices {
    public responseDto getAllVentasDetalle(Long idVenta);
    public responseDto getVentasDetalleId(Long id);
    public responseDto updateVentasDetalle(VentasDetalleModel venta, Long id);
    public responseDto saveVentasDetalle(VentasDetalleModel venta);
}

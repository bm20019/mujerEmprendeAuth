package com.emprende.portal.comercio.dto;

import lombok.Data;
import java.io.*;

@Data
public class responseDto<T> implements Serializable {
    private int codigo;
    private String mensaje;
    private Object respuesta;
    private responseDtoEnum rspEnum;
    public responseDto(){

    }

    public responseDto(String mensaje, Object respuestas, responseDtoEnum rspEnum){
        this.codigo = rspEnum.getCode();
        this.mensaje = String.format("[%s]: %s", rspEnum.name(), mensaje);
        this.respuesta = respuestas;
        this.rspEnum = rspEnum;
    }
    public responseDto(String mensaje, responseDtoEnum rspEnum){
        this.codigo = rspEnum.getCode();
        this.mensaje = String.format("[%s]: %s", rspEnum.name(), mensaje);
        this.respuesta = null;
        this.rspEnum = rspEnum;
    }
    public void setResponse(String mensaje, Object respuesta,  responseDtoEnum rspEnum){
        clear();
        this.codigo = rspEnum.getCode();
        this.mensaje = String.format("[%s]: %s", rspEnum.name(), mensaje);
        this.respuesta = respuesta;
        this.rspEnum = rspEnum;
    }
    public void setResponse(String mensaje,  responseDtoEnum rspEnum){
        clear();
        this.codigo = rspEnum.getCode();
        this.mensaje = String.format("[%s]: %s", rspEnum.name(), mensaje);
        this.rspEnum = rspEnum;
    }
    private void clear(){
        this.respuesta = null;
        this.mensaje = "";
        this.codigo = -1;
    }
}

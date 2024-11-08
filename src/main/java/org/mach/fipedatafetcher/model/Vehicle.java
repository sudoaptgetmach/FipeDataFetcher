package org.mach.fipedatafetcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vehicle {
    @JsonProperty("TipoVeiculo")
    private int tipoVeiculo;

    @JsonProperty("Valor")
    private String valor;

    @JsonProperty("Marca")
    private String marca;

    @JsonProperty("Modelo")
    private String modelo;

    @JsonProperty("AnoModelo")
    private int anoModelo;

    @JsonProperty("Combustivel")
    private String combustivel;

    @JsonProperty("CodigoFipe")
    private String codigoFipe;

    @JsonProperty("MesReferencia")
    private String mesReferencia;

    @JsonProperty("SiglaCombustivel")
    private String siglaCombustivel;

    @Override
    public String toString() {
        return "\nMarca ='" + marca + '\'' +
                ", \nModelo ='" + modelo + '\'' +
                ", \nMesReferencia ='" + mesReferencia + '\'' +
                ", \nCodigoFipe ='" + codigoFipe + '\'' +
                ", \nCombustivel ='" + combustivel + '\'' +
                ", \nSiglaCombustivel ='" + siglaCombustivel + '\'' +
                ", \nValor ='" + valor + '\'' +
                ", \nAnoModelo =" + anoModelo +
                ", \nTipoVeiculo =" + tipoVeiculo;
    }
}
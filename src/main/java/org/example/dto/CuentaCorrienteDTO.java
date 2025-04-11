package org.example.dto;

public class CuentaCorrienteDTO {
    private double saldoInicial;
    private double giroDescubierto;

    private CuentaCorrienteDTO(Builder builder) {
        this.saldoInicial = builder.saldoInicial;
        this.giroDescubierto = builder.giroDescubierto;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public double getGiroDescubierto() {
        return giroDescubierto;
    }

    public static class Builder {
        private double saldoInicial;
        private double giroDescubierto;

        public Builder setSaldoInicial(double saldoInicial) {
            this.saldoInicial = saldoInicial;
            return this;
        }

        public Builder setGiroDescubierto(double giroDescubierto) {
            this.giroDescubierto = giroDescubierto;
            return this;
        }

        public CuentaCorrienteDTO build() {
            return new CuentaCorrienteDTO(this);
        }
    }
}

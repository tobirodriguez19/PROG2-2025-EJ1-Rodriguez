package org.example.dto;

public class CajaDeAhorroDTO {
    private double saldoInicial;

    private CajaDeAhorroDTO(Builder builder) {
        this.saldoInicial = builder.saldoInicial;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public static class Builder {
        private double saldoInicial;

        public Builder setSaldoInicial(double saldoInicial) {
            this.saldoInicial = saldoInicial;
            return this;
        }

        public CajaDeAhorroDTO build() {
            return new CajaDeAhorroDTO(this);
        }
    }
}

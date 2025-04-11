package org.example.dto;

public class CuentaCorriente extends Cuenta implements IGestionSaldo {
    private double giroDescubierto;
    private int operaciones;

    public CuentaCorriente(double saldoInicial, double giroDescubierto) {
        this.saldo = saldoInicial;
        this.giroDescubierto = giroDescubierto;
    }

    public synchronized boolean agregarSaldo(double monto) {
        if (monto <= 0) return false;
        saldo += monto;
        operaciones++;
        return true;
    }

    public synchronized boolean quitarSaldo(double monto) {
        if (monto <= 0) return false;
        if ((saldo - monto) >= -giroDescubierto) {
            saldo -= monto;
            operaciones++;
            return true;
        }
        return false;
    }

    public synchronized double getSaldo() {
        return saldo;
    }

    public synchronized int getOperaciones() {
        return operaciones;
    }
}

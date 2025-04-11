package org.example.dto;

public class CajaDeAhorro extends Cuenta implements IGestionSaldo {
    private int operaciones;

    public CajaDeAhorro(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized boolean agregarSaldo(double monto) {
        if (monto <= 0) return false;
        saldo += monto;
        operaciones++;
        return true;
    }

    public synchronized boolean quitarSaldo(double monto) {
        if (monto <= 0) return false;
        if (monto <= saldo) {
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

package org.example.services;

import org.example.dto.IGestionSaldo;

import java.util.ArrayList;
import java.util.List;

public class ServicioCuenta {
    private static volatile ServicioCuenta instancia;
    private final List<IGestionSaldo> cuentas;

    private ServicioCuenta() {
        cuentas = new ArrayList<>();
    }

    public static ServicioCuenta getInstancia() {
        if (instancia == null) {
            synchronized (ServicioCuenta.class) {
                if (instancia == null) {
                    instancia = new ServicioCuenta();
                }
            }
        }
        return instancia;
    }

    public synchronized int agregarCuenta(IGestionSaldo cuenta) {
        cuentas.add(cuenta);
        return cuentas.size() - 1;
    }

    public boolean agregarSaldo(int cuenta, double monto) {
        IGestionSaldo c = obtenerCuenta(cuenta);
        return c != null && c.agregarSaldo(monto);
    }

    public boolean quitarSaldo(int cuenta, double monto) {
        IGestionSaldo c = obtenerCuenta(cuenta);
        return c != null && c.quitarSaldo(monto);
    }


    private IGestionSaldo obtenerCuenta(int index) {
        synchronized (cuentas) {
            if (index >= 0 && index < cuentas.size()) {
                return cuentas.get(index);
            }
            return null;
        }
    }

    public List<IGestionSaldo> obtenerCuentas() {
        return cuentas;
    }
}

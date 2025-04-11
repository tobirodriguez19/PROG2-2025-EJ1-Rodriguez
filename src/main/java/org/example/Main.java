package org.example;

import org.example.dto.CajaDeAhorro;
import org.example.dto.CuentaCorriente;
import org.example.dto.IGestionSaldo;
import org.example.services.ServicioCuenta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        int totalCuentas = 10;
        int totalOperaciones = 10000;

        ServicioCuenta servicio = ServicioCuenta.getInstancia();
        Random random = new Random();

        // Crear cuentas
        for (int i = 0; i < totalCuentas; i++) {
            double saldoInicial = random.nextDouble() * 1000;

            if (random.nextBoolean()) {
                servicio.agregarCuenta(new CajaDeAhorro(saldoInicial));
            } else {
                double giroDescubierto = random.nextDouble() * 500;
                servicio.agregarCuenta(new CuentaCorriente(saldoInicial, giroDescubierto));
            }
        }

        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<?>> futures = new ArrayList<>();

        // Enviar operaciones al executor
        for (int i = 0; i < totalOperaciones; i++) {
            Future<?> future = executor.submit(() -> {
                int cuentaId = random.nextInt(totalCuentas);
                double monto = 1 + random.nextInt(100);
                boolean agregar = random.nextBoolean();

                if (agregar) {
                    servicio.agregarSaldo(cuentaId, monto);
                } else {
                    servicio.quitarSaldo(cuentaId, monto);
                }
            });
            futures.add(future);
        }

        // Esperar que todas las tareas finalicen
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        System.out.println("Resultados Finales:");
        List<IGestionSaldo> cuentas = servicio.obtenerCuentas();

        for (int i = 0; i < cuentas.size(); i++) {
            IGestionSaldo cuenta = cuentas.get(i);
            double saldo = cuenta.getSaldo();
            int operaciones = cuenta.getOperaciones();

            System.out.println("Cuenta #" + i + ": Saldo = $" + String.format("%.2f", saldo) + " | Operaciones = " + operaciones);
        }
    }
}

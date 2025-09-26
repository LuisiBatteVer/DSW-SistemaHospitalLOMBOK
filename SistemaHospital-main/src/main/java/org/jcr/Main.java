package org.jcr;

import org.jcr.entidades.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        // 1. Crear hospital
        Hospital hospital = new Hospital("Hospital Central", "Av. Siempre Viva 123", "1234-5678");

        // 2. Crear departamentos
        Departamento cardiologia = new Departamento("Cardiología", EspecialidadMedica.CARDIOLOGIA);
        Departamento pediatria = new Departamento("Pediatría", EspecialidadMedica.PEDIATRIA);

        hospital.agregarDepartamento(cardiologia);
        hospital.agregarDepartamento(pediatria);

        // 3. Crear salas
        Sala salaCardio = cardiologia.crearSala("C101", "Consulta");
        Sala salaPedia = pediatria.crearSala("P201", "Consulta");

        // 4. Crear médicos
        Medico drPerez = new Medico(
                "Juan",
                "Perez",
                "12345678",
                LocalDate.of(1980, 5, 10),
                TipoSangre.A_POSITIVO,
                "MP-12345",
                EspecialidadMedica.CARDIOLOGIA
        );

        Medico draLopez = new Medico(
                "Ana",
                "Lopez",
                "87654321",
                LocalDate.of(1990, 8, 20),
                TipoSangre.B_POSITIVO,
                "MP-54321",
                EspecialidadMedica.PEDIATRIA
        );

        cardiologia.agregarMedico(drPerez);
        pediatria.agregarMedico(draLopez);

        // 5. Crear pacientes
        Paciente paciente1 = new Paciente(
                "Carlos",
                "Gomez",
                "11111111",
                LocalDate.of(2000, 1, 15),
                TipoSangre.O_POSITIVO,
                "555-1234",
                "Calle Falsa 123"
        );

        Paciente paciente2 = new Paciente(
                "Laura",
                "Martinez",
                "22222222",
                LocalDate.of(2010, 6, 5),
                TipoSangre.AB_NEGATIVO,
                "555-5678",
                "Calle Verdadera 456"
        );

        hospital.agregarPaciente(paciente1);
        hospital.agregarPaciente(paciente2);

        // 6. Crear CitaManager
        CitaManager citaManager = new CitaManager();

        // 7. Programar citas
        try {
            Cita cita1 = citaManager.programarCita(
                    paciente1,
                    drPerez,
                    salaCardio,
                    LocalDateTime.of(2025, 10, 1, 10, 0),
                    new BigDecimal("1500")
            );

            Cita cita2 = citaManager.programarCita(
                    paciente2,
                    draLopez,
                    salaPedia,
                    LocalDateTime.of(2025, 10, 1, 11, 0),
                    new BigDecimal("1200")
            );

            System.out.println("Citas programadas correctamente:");
            System.out.println(cita1);
            System.out.println(cita2);

        } catch (CitaException e) {
            System.err.println("Error al programar cita: " + e.getMessage());
        }

        // 8. Imprimir estructura del hospital
        System.out.println("\nDepartamentos y salas:");
        hospital.getDepartamentos().forEach(dep -> {
            System.out.println("Departamento: " + dep.getNombre());
            System.out.println("  Salas: " + dep.getSalas());
            System.out.println("  Médicos: " + dep.getMedicos());
        });

        System.out.println("\nPacientes registrados:");
        hospital.getPacientes().forEach(System.out::println);

        // 9. Imprimir citas por paciente
        System.out.println("\nCitas por paciente:");
        hospital.getPacientes().forEach(p -> {
            System.out.println(p.getNombreCompleto() + ": " + p.getCitas());
        });

        // 10. Imprimir citas por médico
        System.out.println("\nCitas por médico:");
        System.out.println(drPerez.getNombreCompleto() + ": " + citaManager.getCitasPorMedico(drPerez));
        System.out.println(draLopez.getNombreCompleto() + ": " + citaManager.getCitasPorMedico(draLopez));

        // 11. Imprimir citas por sala
        System.out.println("\nCitas por sala:");
        System.out.println(salaCardio.getNumero() + ": " + citaManager.getCitasPorSala(salaCardio));
        System.out.println(salaPedia.getNumero() + ": " + citaManager.getCitasPorSala(salaPedia));
    }
}

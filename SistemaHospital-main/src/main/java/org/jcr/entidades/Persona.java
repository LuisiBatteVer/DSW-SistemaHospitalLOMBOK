package org.jcr.entidades;

// Lombok se aplica: genera getters básicos automáticos.
// No usé @ToString porque hice uno manual para controlar la salida y mostrar tipoSangre con su descripción.

import lombok.Getter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter  // Genera automáticamente los getters simples
public abstract class Persona implements Serializable {
    protected final String nombre;
    protected final String apellido;
    protected final String dni;
    protected final LocalDate fechaNacimiento;
    protected final TipoSangre tipoSangre;

    public Persona(String nombre, String apellido, String dni, LocalDate fechaNacimiento, TipoSangre tipoSangre) {
        this.nombre = validarString(nombre, "El nombre no puede ser nulo ni vacío");
        this.apellido = validarString(apellido, "El apellido no puede ser nulo ni vacío");
        this.dni = validarDni(dni);
        this.fechaNacimiento = Objects.requireNonNull(fechaNacimiento, "La fecha de nacimiento no puede ser nula");
        this.tipoSangre = Objects.requireNonNull(tipoSangre, "El tipo de sangre no puede ser nulo");
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public int getEdad() {
        return LocalDate.now().getYear() - fechaNacimiento.getYear();
    }

    // 🔹 Ahora son protected para que Paciente y Medico puedan usarlos
    protected String validarString(String valor, String mensajeError) {
        Objects.requireNonNull(valor, mensajeError);
        if (valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensajeError);
        }
        return valor;
    }

    protected String validarDni(String dni) {
        Objects.requireNonNull(dni, "El DNI no puede ser nulo");
        if (!dni.matches("\\d{7,8}")) {
            throw new IllegalArgumentException("El DNI debe tener 7 u 8 dígitos");
        }
        return dni;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", tipoSangre=" + tipoSangre.getDescripcion() +
                '}';
    }
}

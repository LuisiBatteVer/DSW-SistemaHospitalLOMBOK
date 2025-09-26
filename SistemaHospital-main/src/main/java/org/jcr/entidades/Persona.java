package org.jcr.entidades;
//No usé @ToString porque es personalizado: incluye llamada anidada a getDescripcion() en tipoSangre (para salida legible) y todos los campos para depuración completa; @ToString generaría genérico, pero aquí es óptimo manual para control (evita exposición sensible en subclases): No usé @ToString porque es personalizado: incluye llamada anidada a getDescripcion() en tipoSangre (para salida legible) y todos los campos para depuración completa; @ToString generaría genérico, pero aquí es óptimo manual para control (evita exposición sensible en subclases).
//se aplico lombok. Se puede aplicar Lombok de forma efectiva en clases abstractas con herencia: Usé @Getter a nivel de clase porque hay múltiples getters repetitivos y básicos para los campos protegidos/finales (getNombre(), getApellido(), getDni(), getFechaNacimiento(), getTipoSangre()), que solo retornan el valor sin lógica adicional
import lombok.Getter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter  // Genera automáticamente los getters simples para los campos: getNombre(), getApellido(), getDni(), getFechaNacimiento(), getTipoSangre()
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

    private String validarString(String valor, String mensajeError) {
        Objects.requireNonNull(valor, mensajeError);
        if (valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensajeError);
        }
        return valor;
    }

    private String validarDni(String dni) {
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
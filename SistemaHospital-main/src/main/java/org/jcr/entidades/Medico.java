//Se puede aplicar Lombok para getters simples en herencia: Usé @Getter a nivel de clase porque hay getters repetitivos y básicos para matricula, especialidad y departamento (solo retornan el campo, sin lógica extra).
//Esto reduce código en una subclase de Persona (asumiendo Persona tiene sus propios getters para nombre/apellido/etc.), donde el acceso a atributos médicos (e.g., especialidad para matching con salas en citas) es común. @Getter se hereda y no interfiere con superclase.
//Esto reduce código en una subclase de Persona (asumiendo Persona tiene sus propios getters para nombre/apellido/etc.), donde el acceso a atributos médicos (e.g., especialidad para matching con salas en citas) es común. @Getter se hereda y no interfiere con superclase.
//crea Matricula (delegando validación) y valida especialidad (crítico para lógica hospitalaria, e.g., evitar médicos sin especialidad en programarCita).
//@AllArgsConstructor no manejaría bien la llamada a super ni la creación de objetos anidados como Matricula
//etc
package org.jcr.entidades;

import lombok.Getter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter  // Genera automáticamente los getters simples para los campos: getMatricula(), getEspecialidad(), getDepartamento()
public class Medico extends Persona implements Serializable {
    private final Matricula matricula;
    private final EspecialidadMedica especialidad;
    private Departamento departamento;
    private final List<Cita> citas = new ArrayList<>();

    public Medico(String nombre, String apellido, String dni, LocalDate fechaNacimiento,
                  TipoSangre tipoSangre, String numeroMatricula, EspecialidadMedica especialidad) {
        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);
        this.matricula = new Matricula(numeroMatricula);
        this.especialidad = Objects.requireNonNull(especialidad, "La especialidad no puede ser nula");
    }

    public void setDepartamento(Departamento departamento) {
        if (this.departamento != departamento) {
            this.departamento = departamento;
        }
    }

    public void addCita(Cita cita) {
        this.citas.add(cita);
    }

    // Getter manual para lista preserva inmutabilidad y copia defensiva
    public List<Cita> getCitas() {
        return Collections.unmodifiableList(new ArrayList<>(citas));
    }

    @Override
    public String toString() {
        return "Medico{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", especialidad=" + especialidad.getDescripcion() +
                ", matricula=" + matricula.getNumero() +
                '}';
    }
}
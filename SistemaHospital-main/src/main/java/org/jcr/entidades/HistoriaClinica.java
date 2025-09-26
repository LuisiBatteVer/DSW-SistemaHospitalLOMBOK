//Se puede aplicar Lombok para getters simples, pero no para todo: Usé @Getter a nivel de clase porque hay getters repetitivos y básicos para los campos final (getNumeroHistoria(), getPaciente(), getFechaCreacion()) que solo devuelven el campo sin lógica adicional.
//No se aplicó en constructor, métodos agregar ni toString*: El constructor tiene validaciones (Objects.requireNonNull) y lógica de generación (generarNumeroHistoria() y LocalDateTime.now())
//Los métodos agregar* son de negocio con validaciones
//No usé @ToString porque es personalizado: llama a getNombreCompleto() en paciente (método anidado) y excluye listas sensibles (evita exponer datos médicos privados en logs); @ToString generaría una salida genérica que podría incluir listas, rompiendo privacidad y formato.
package org.jcr.entidades;

import lombok.Getter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter  // Genera automáticamente los getters simples para los campos final: getNumeroHistoria(), getPaciente(), getFechaCreacion()
public class HistoriaClinica implements Serializable {
    private final String numeroHistoria;
    private final Paciente paciente;
    private final LocalDateTime fechaCreacion;
    private final List<String> diagnosticos = new ArrayList<>();
    private final List<String> tratamientos = new ArrayList<>();
    private final List<String> alergias = new ArrayList<>();

    public HistoriaClinica(Paciente paciente) {
        this.paciente = Objects.requireNonNull(paciente, "El paciente no puede ser nulo");
        this.fechaCreacion = LocalDateTime.now();
        this.numeroHistoria = generarNumeroHistoria();
    }

    private String generarNumeroHistoria() {
        return "HC-" + paciente.getDni() + "-" + fechaCreacion.getYear();
    }

    public void agregarDiagnostico(String diagnostico) {
        if (diagnostico != null && !diagnostico.trim().isEmpty()) {
            diagnosticos.add(diagnostico);
        }
    }

    public void agregarTratamiento(String tratamiento) {
        if (tratamiento != null && !tratamiento.trim().isEmpty()) {
            tratamientos.add(tratamiento);
        }
    }

    public void agregarAlergia(String alergia) {
        if (alergia != null && !alergia.trim().isEmpty()) {
            alergias.add(alergia);
        }
    }

    // Getters manuales para listas preservan inmutabilidad (unmodifiableList)
    public List<String> getDiagnosticos() {
        return Collections.unmodifiableList(diagnosticos);
    }

    public List<String> getTratamientos() {
        return Collections.unmodifiableList(tratamientos);
    }

    public List<String> getAlergias() {
        return Collections.unmodifiableList(alergias);
    }

    @Override
    public String toString() {
        return "HistoriaClinica{" +
                "numeroHistoria='" + numeroHistoria + '\'' +
                ", paciente=" + paciente.getNombreCompleto() +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}
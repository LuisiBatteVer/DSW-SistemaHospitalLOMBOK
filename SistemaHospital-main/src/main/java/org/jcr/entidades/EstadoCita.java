package org.jcr.entidades;

import lombok.Getter;

@Getter  // Genera automáticamente el getter para el campo 'descripcion' (getDescripcion())
public enum EstadoCita {
    PROGRAMADA("Programada"),
    EN_CURSO("En Curso"),
    COMPLETADA("Completada"),
    CANCELADA("Cancelada"),
    NO_ASISTIO("No Asistió");

    private final String descripcion;

    EstadoCita(String descripcion) {
        this.descripcion = descripcion;
    }

    // El getter se genera automáticamente por @Getter
}
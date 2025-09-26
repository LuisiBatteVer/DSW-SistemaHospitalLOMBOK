//Se puede aplicar Lombok de forma limitada pero efectiva en enums: Usé @Getter porque el enum tiene un campo final (descripcion) con un getter simple (getDescripcion() que solo retorna el campo).

package org.jcr.entidades;

import lombok.Getter;

@Getter  // Genera automáticamente el getter para el campo 'descripcion' (getDescripcion())
public enum EspecialidadMedica {
    CARDIOLOGIA("Cardiología"),
    NEUROLOGIA("Neurología"),
    PEDIATRIA("Pediatría"),
    TRAUMATOLOGIA("Traumatología"),
    GINECOLOGIA("Ginecología"),
    UROLOGIA("Urología"),
    OFTALMOLOGIA("Oftalmología"),
    DERMATOLOGIA("Dermatología"),
    PSIQUIATRIA("Psiquiatría"),
    MEDICINA_GENERAL("Medicina General"),
    CIRUGIA_GENERAL("Cirugía General"),
    ANESTESIOLOGIA("Anestesiología");

    private final String descripcion;

    EspecialidadMedica(String descripcion) {
        this.descripcion = descripcion;
    }

    // El getter se genera automáticamente por @Getter
}
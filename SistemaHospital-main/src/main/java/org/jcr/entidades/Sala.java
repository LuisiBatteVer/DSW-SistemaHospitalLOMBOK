package org.jcr.entidades;

import lombok.Getter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter  // Genera automáticamente los getters simples para los campos: getNumero(), getTipo(), getDepartamento()
public class Sala implements Serializable {
    private final String numero;
    private final String tipo;
    private final Departamento departamento;
    private final List<Cita> citas = new ArrayList<>();

    public Sala(String numero, String tipo, Departamento departamento) {
        this.numero = validarString(numero, "El número de sala no puede ser nulo ni vacío");
        this.tipo = validarString(tipo, "El tipo de sala no puede ser nulo ni vacío");
        this.departamento = Objects.requireNonNull(departamento, "El departamento no puede ser nulo");
    }

    public void addCita(Cita cita) {
        this.citas.add(cita);
    }

    // Getter manual para lista preserva inmutabilidad y copia defensiva
    public List<Cita> getCitas() {
        return Collections.unmodifiableList(new ArrayList<>(citas));
    }

    private String validarString(String valor, String mensajeError) {
        Objects.requireNonNull(valor, mensajeError);
        if (valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensajeError);
        }
        return valor;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "numero='" + numero + '\'' +
                ", tipo='" + tipo + '\'' +
                ", departamento=" + departamento.getNombre() +
                '}';
    }
}
package org.jcr.entidades;
//removí validarString de Paciente y actualicé el constructor para usar el heredado de Persona. Asumo que Persona lo provee como protected
import lombok.Getter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter  // Genera automáticamente los getters simples para los campos: getHistoriaClinica(), getTelefono(), getDireccion(), getHospital()
public class Paciente extends Persona implements Serializable {
    private final HistoriaClinica historiaClinica;
    private final String telefono;
    private final String direccion;
    private Hospital hospital;
    private final List<Cita> citas = new ArrayList<>();

    public Paciente(String nombre, String apellido, String dni, LocalDate fechaNacimiento,
                    TipoSangre tipoSangre, String telefono, String direccion) {
        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);
        // Usa el método heredado de Persona (protected), evitando duplicación
        this.telefono = validarString(telefono, "El teléfono no puede ser nulo ni vacío");
        this.direccion = validarString(direccion, "La dirección no puede ser nula ni vacía");
        this.historiaClinica = new HistoriaClinica(this);
    }

    public void setHospital(Hospital hospital) {
        if (this.hospital != hospital) {
            if (this.hospital != null) {
                this.hospital.getInternalPacientes().remove(this);
            }
            this.hospital = hospital;
            if (hospital != null) {
                hospital.getInternalPacientes().add(this);
            }
        }
    }

    public void addCita(Cita cita) {
        this.citas.add(cita);
    }

    // Getter manual para lista preserva inmutabilidad y copia defensiva
    public List<Cita> getCitas() {
        return Collections.unmodifiableList(new ArrayList<>(citas));
    }

    // REMOVIDO: private String validarString(...) – Ahora heredado de Persona para evitar clash y duplicación

    @Override
    public String toString() {
        return "Paciente{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", tipoSangre=" + tipoSangre.getDescripcion() +
                '}';
    }
}
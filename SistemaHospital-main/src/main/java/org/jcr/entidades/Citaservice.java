//No se puede aplicar Lombok de forma significativa: Las interfaces en Java no tienen campos, constructores ni métodos
//con implementación (salvo defaults en Java 8+).
// Lombok está diseñado para reducir boilerplate en clases (como getters, setters, constructores),
// pero no genera código en interfaces porque no hay nada que "repetir" o automatizar aquí.
// Intentar anotaciones como @Getter o @ToString en una interfaz no tiene efecto
// (Lombok las ignora o las aplica solo si hay campos, lo cual no aplica).

package org.jcr.entidades;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

interface CitaService {
    Cita programarCita(Paciente paciente, Medico medico, Sala sala,
                       LocalDateTime fechaHora, BigDecimal costo) throws CitaException;

    List<Cita> getCitasPorPaciente(Paciente paciente);

    List<Cita> getCitasPorMedico(Medico medico);

    List<Cita> getCitasPorSala(Sala sala);

    void guardarCitas(String filename) throws IOException;

    void cargarCitas(String filename, Map<String, Paciente> pacientes,
                     Map<String, Medico> medicos, Map<String, Sala> salas)
            throws IOException, ClassNotFoundException, CitaException;
}
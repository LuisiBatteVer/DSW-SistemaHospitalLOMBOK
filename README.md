# Proyecto Sistema de Gestión Hospitalaria

SistemaHospital-main > src/main > java/org/jcr 

## Descripción
Este proyecto es un **sistema de gestión hospitalaria** desarrollado en Java.  
Permite manejar **hospitales, departamentos, médicos, pacientes, salas y citas médicas**, con validaciones de negocio, historial clínico y programación de turnos.

## Objetivo: Aplicar lombok cuando sea posible a cada clase del proyecto.
---

## Organización del proyecto
 Se organiza en paquetes
org.jcr.entidades → Contiene todas las clases del dominio (Hospital, Paciente, Medico, Cita, Sala, Departamento, HistoriaClinica, enums, excepciones, servicios)
org.jcr → Contiene el main de la aplicación


### Clases principales
- **Hospital**: Maneja departamentos y pacientes.  
- **Departamento**: Agrupa médicos y salas según la especialidad.  
- **Medico**: Hereda de Persona, tiene matrícula, especialidad y lista de citas.  
- **Paciente**: Hereda de Persona, tiene historia clínica, teléfono, dirección y citas.  
- **Cita**: Representa un turno médico, asociado a paciente, médico y sala.  
- **Sala**: Contiene citas programadas y pertenece a un departamento.  
- **HistoriaClinica**: Almacena diagnósticos, tratamientos y alergias del paciente.  
- **Enums**: `EspecialidadMedica`, `EstadoCita`, `TipoSangre`.  
- **Excepciones y Servicios**: `CitaException`, `CitaService`, `CitaManager`.

---

## Uso del Main
El `Main` permite:
1. Crear un **Hospital** con departamentos y salas.  
2. Crear **Médicos y Pacientes**, asignándolos a departamentos/hospital.  
3. Programar **Citas** validando disponibilidad de médicos y salas.  
4. Mostrar la información por consola y generar CSV de las citas.
   

## Criterios utilizados en el proyecto
Validación de negocio:Validaciones de nulos, formatos de DNI y matrícula,Disponibilidad de médicos y salas,Evitar duplicados en listas y relaciones bidireccionales.
Inmutabilidad parcial: Campos final para datos inmutables (nombres, fechas, matrículas),Listas internas expuestas solo como unmodifiableList


## Uso de Lombok:
Solo en getters simples (@Getter) y enums.
No en constructores con validación, toString() personalizados, ni setters con lógica de negocio.


## Separación de responsabilidades:
CitaManager maneja lógica de programación de citas.
Entidades se centran en almacenar datos y relaciones.


## Persistencia CSV:
Permite guardar y cargar citas usando archivos CSV.

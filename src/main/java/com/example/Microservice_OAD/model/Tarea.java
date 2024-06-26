package com.example.Microservice_OAD.model;

import javax.persistence.*;

import java.time.OffsetDateTime;

// create table TODOUSER.Tarea (
//     IdTarea number GENERATED by DEFAULT as IDENTITY,
//     IdDesarrollador number,
//     IdProyecto number,
//     IdSprint number,
//     Titulo varchar2(50),
//     Descripcion varchar2(100),
//     FechaInicio date,
//     FechaFin date,
//     CreadoPor varchar2(20),
//     Responsable varchar2(20),
//     Estatus number,
//     Prioridad varchar2(10),
//     IsActive number(1),
//     primary key (IdTarea),
//     constraint fk_tarea_desarrollador foreign key (IdDesarrollador) references Desarrollador(IdDesarrollador),
//     constraint fk_tarea_proyecto foreign key (IdProyecto) references Proyecto(IdProyecto),
//     constraint fk_tarea_sprint foreign key (IdSprint) references Sprint(IdSprint)
// );

@Entity
@Table(name = "Tarea", schema = "TODOUSER")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDTAREA")
    private int IdTarea;

    @ManyToOne
    @JoinColumn(name = "IDDESARROLLADOR", nullable = false)
    private Desarrollador desarrollador;

    @ManyToOne
    @JoinColumn(name = "IDPROYECTO")
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "IDSPRINT")
    private Sprint sprint;

    @Column(name = "TITULO", length = 50, nullable = false)
    private String titulo;

    @Column(name = "DESCRIPCION", length = 100, nullable = false)
    private String descripcion_tarea;

    @Column(name = "FECHAINICIO")
    private OffsetDateTime fechaInicio;

    @Column(name = "FECHAFIN")
    private OffsetDateTime fechaFin_tarea;

    @Column(name = "CREADOPOR", length = 20)
    private String creadoPor;

    @Column(name = "RESPONSABLE", length = 20)
    private String responsable;

    @Column(name = "ESTATUS")
    private int estatus_tarea;

    @Column(name = "PRIORIDAD", length = 10)
    private String prioridad_tarea;

    @Column(name = "ISACTIVE")
    private boolean isActive;

    public Tarea() {

    }

    public Tarea(int IdTarea, Desarrollador desarrollador, Proyecto proyecto, Sprint sprint, String titulo, String descricion_tarea, OffsetDateTime fechaInicio, OffsetDateTime fechaFin_tarea, String creadoPor, String responsable, int estatus_tarea, String prioridad_tarea, boolean isActive) {
        this.IdTarea = IdTarea;
        this.desarrollador = desarrollador;
        this.proyecto = proyecto;
        this.sprint = sprint;
        this.titulo = titulo;
        this.descripcion_tarea = descricion_tarea;
        this.fechaInicio = fechaInicio;
        this.fechaFin_tarea = fechaFin_tarea;
        this.creadoPor = creadoPor;
        this.responsable = responsable;
        this.estatus_tarea = estatus_tarea;
        this.prioridad_tarea = prioridad_tarea;
        this.isActive = isActive;
    }

    public int getIdTarea() {
        return IdTarea;
    }

    public void setIdTarea(int IdTarea) {
        this.IdTarea = IdTarea;
    }

    public Desarrollador getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(Desarrollador desarrollador) {
        this.desarrollador = desarrollador;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion_tarea;
    }

    public void setDescripcion(String descripcion_tarea) {
        this.descripcion_tarea = descripcion_tarea;
    }

    public OffsetDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(OffsetDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public OffsetDateTime getFechaFinTarea() {
        return fechaFin_tarea;
    }

    public void setFechaFinTarea(OffsetDateTime fechaFin_tarea) {
        this.fechaFin_tarea = fechaFin_tarea;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Integer getEstatusTarea() {
        return estatus_tarea;
    }

    public void setEstatusTarea(Integer estatus_tarea) {
        this.estatus_tarea = estatus_tarea;
    }

    public String getPrioridadTarea() {
        return prioridad_tarea;
    }

    public void setPrioridad(String prioridad_tarea) {
        this.prioridad_tarea = prioridad_tarea;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "ID=" + IdTarea +
                ", descripcion='" + descripcion_tarea + '\'' +
                ", fecha de  inicio" + fechaInicio + 
                ", Terminada=" + isActive + 
                '}';
    }

}

package com.example.Microservice_OAD.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PROYECTO")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPROYECTO")
    private int IdProyecto;

    @ManyToOne
    @JoinColumn(name = "IDMANAGER")
    private Manager manager;

    @Column(name = "TITULO", length = 50)
    private String titulo;

    @Column(name = "DESCRIPCION", length = 100)
    private String descripcion_proyecto;

    @Column(name = "FECHAINICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.DATE)
    private Date fechaFin_proyecto;

    @Column(name = "ESTATUS")
    private int estatus_proyecto;

    @Column(name = "ISACTIVE")
    private boolean isActive_proyecto;

    public Proyecto() {

    }

    public Proyecto(int IdProyecto, Manager manager, String titulo, String descripcion_proyecto, Date fechaInicio, Date fechaFin_proyecto, int estatus_proyecto, boolean isActive_proyecto) {
        this.IdProyecto = IdProyecto;
        this.manager = manager;
        this.titulo = titulo;
        this.descripcion_proyecto = descripcion_proyecto;
        this.fechaInicio = fechaInicio;
        this.fechaFin_proyecto = fechaFin_proyecto;
        this.estatus_proyecto = estatus_proyecto;
        this.isActive_proyecto = isActive_proyecto;
    }

    public int getIdProyecto() {
        return IdProyecto;
    }

    public void setIdProyecto(int IdProyecto) {
        this.IdProyecto = IdProyecto;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcionProyecto() {
        return descripcion_proyecto;
    }

    public void setDescripcionProyecto(String descripcion_proyecto) {
        this.descripcion_proyecto = descripcion_proyecto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinProyecto() {
        return fechaFin_proyecto;
    }

    public void setFechaFinProyecto(Date fechaFin_proyecto) {
        this.fechaFin_proyecto = fechaFin_proyecto;
    }

    public Integer getEstatus() {
        return estatus_proyecto;
    }

    public void setEstatus(int estatus_proyecto) {
        this.estatus_proyecto = estatus_proyecto;
    } 

    public Boolean getIsActive() {
        return isActive_proyecto;
    }

    public void setIsActive(boolean isActive_proyecto) {
        this.isActive_proyecto = isActive_proyecto;
    }
}

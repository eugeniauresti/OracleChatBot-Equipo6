package com.example.Microservice_OAD.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SPRINT")
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDSPRINT")
    private int IdSprint;

    @ManyToOne
    @JoinColumn(name = "IDPROYECTO")
    private Proyecto proyecto;

    @Column(name = "FechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio_sprint;

    @Column(name = "FechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin_sprint;

    @Column(name = "IsActive")
    private boolean isActive_sprint;
    
    public Sprint() {

    }

    public Sprint(int IdSprint, Proyecto proyecto, Date fechaInicio_sprint, Date fechaFin_sprint, boolean isActive_sprint) {
        this.IdSprint = IdSprint;
        this.proyecto = proyecto;
        this.fechaInicio_sprint = fechaInicio_sprint;
        this.fechaFin_sprint = fechaFin_sprint;
        this.isActive_sprint = isActive_sprint;
    }

    public int getIdSprint() {
        return IdSprint;
    }

    public void setIdSprint(int IdSprint) {
        this.IdSprint = IdSprint;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Date getFechaInicioSprint() {
        return fechaInicio_sprint;
    }

    public void setFechaInicioSprint(Date fechaInicio_sprint) {
        this.fechaInicio_sprint = fechaFin_sprint;
    }

    public Date getFechaFinSprint() {
        return fechaFin_sprint;
    }

    public void setFechaFinSprint(Date fechaFin_sprint) {
        this.fechaFin_sprint = fechaFin_sprint;
    }


}
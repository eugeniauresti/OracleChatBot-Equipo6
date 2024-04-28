package com.example.Microservice_OAD.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "DESARROLLADOR")
public class Desarrollador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDDESARROLLADOR")
    private int IdDesarrollador;

    @ManyToOne
    @JoinColumn(name = "IDUSUARIO")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "IDMANAGER")
    private Manager manager;

    @Column(name = "AREATRABAJANDO", length = 20)
    private String areaTrabajando;

    @Column(name = "SALARIO", precision = 10, scale = 2)
    private BigDecimal salario;

    @Column(name = "EXPERIENCIA", length = 50)
    private String experiencia;

    @Column(name = "TIPOEMPLEADO", length = 25)
    private String tipoEmpleado;

    public Desarrollador() {

    }

    public Desarrollador(int IdDesarrollador, Usuario usuario, Manager manager, String areaTrabajando, BigDecimal salario, String experiencia, String tipoEmpleado) {
        this.IdDesarrollador = IdDesarrollador;
        this.usuario = usuario;
        this.manager = manager;
        this.areaTrabajando = areaTrabajando;
        this.salario = salario;
        this.experiencia = experiencia;
        this.tipoEmpleado = tipoEmpleado;
    }

    public int getIdDesarrollador() {
        return IdDesarrollador;
    }

    public void setIdDesarrollador(int IdDesarrollador) {
        this.IdDesarrollador = IdDesarrollador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getAreaTrabajando() {
        return areaTrabajando;
    }

    public void setAreaTrabajando(String areaTrabajando) {
        this.areaTrabajando = areaTrabajando;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
}

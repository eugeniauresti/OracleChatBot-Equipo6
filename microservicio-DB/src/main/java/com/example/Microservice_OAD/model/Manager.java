package com.example.Microservice_OAD.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "MANAGER")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDMANAGER")
    private int IdManager;

    @OneToOne
    @JoinColumn(name = "IDUSUARIO", nullable = false)
    private Usuario usuario;

    @Column(name = "SALARIO", precision = 10, scale = 2)
    private BigDecimal salario;
    
    public Manager() {

    }

    public Manager(int IdManager, Usuario usuario, BigDecimal salario) {
        this.IdManager = IdManager;
        this.usuario = usuario;
        this.salario = salario;
    }

    public int getIdManager() {
        return IdManager;
    }

    public void setIdManager(int IdManager) {
        this.IdManager = IdManager;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
}

package com.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="curso")
public class Curso {
    
    @Id
    @Column(name="cod_curs")
    private Integer codigo;
    @Column(name="nom_curs")
    private String nombre;
    @Column(name="hor_curs")
    private Integer horas;
    @Column(name="valor_curs")
    private Double valor;
    @ManyToOne
    @JoinColumn(name="cod_prof")
    private Profesor profesor;
    
    @JoinTable(
        name = "estudiante_curso",
        joinColumns = @JoinColumn(name = "cod_curs", nullable = false),
        inverseJoinColumns = @JoinColumn(name="cod_est", nullable = false)
    )
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Estudiante> estudiantes;
   
    
    public void addEstudiante(Estudiante estidiante){
        if(this.estudiantes == null){
            this.estudiantes = new ArrayList();
        }
        
        this.estudiantes.add(estidiante);
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Curso{" + "codigo=" + codigo + ", nombre=" + nombre + ", horas=" + horas + ", valor=" + valor + ", profesor=" + profesor + '}';
    }
    
}

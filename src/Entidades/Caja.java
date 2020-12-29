/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author irisa
 */
@Entity
@Table(name = "caja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caja.findAll", query = "SELECT c FROM Caja c"),
    @NamedQuery(name = "Caja.findByFecha", query = "SELECT c FROM Caja c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Caja.findByHora", query = "SELECT c FROM Caja c WHERE c.hora = :hora"),
    @NamedQuery(name = "Caja.findByMonto", query = "SELECT c FROM Caja c WHERE c.monto = :monto"),
    @NamedQuery(name = "Caja.findByIdRegistroCaja", query = "SELECT c FROM Caja c WHERE c.idRegistroCaja = :idRegistroCaja")})
public class Caja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @Column(name = "monto")
    private double monto;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRegistroCaja")
    private Integer idRegistroCaja;
    @JoinColumn(name = "idEmpleado", referencedColumnName = "idEmpleado")
    @ManyToOne(optional = false)
    private Empleado idEmpleado;

    public Caja() {
    }

    public Caja(Integer idRegistroCaja) {
        this.idRegistroCaja = idRegistroCaja;
    }

    public Caja(Integer idRegistroCaja, Date fecha, Date hora, double monto) {
        this.idRegistroCaja = idRegistroCaja;
        this.fecha = fecha;
        this.hora = hora;
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Integer getIdRegistroCaja() {
        return idRegistroCaja;
    }

    public void setIdRegistroCaja(Integer idRegistroCaja) {
        this.idRegistroCaja = idRegistroCaja;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistroCaja != null ? idRegistroCaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caja)) {
            return false;
        }
        Caja other = (Caja) object;
        if ((this.idRegistroCaja == null && other.idRegistroCaja != null) || (this.idRegistroCaja != null && !this.idRegistroCaja.equals(other.idRegistroCaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Caja[ idRegistroCaja=" + idRegistroCaja + " ]";
    }
    
}

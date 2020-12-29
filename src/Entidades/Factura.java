/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author irisa
 */
@Entity
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByNumFactura", query = "SELECT f FROM Factura f WHERE f.numFactura = :numFactura"),
    @NamedQuery(name = "Factura.findByFecha", query = "SELECT f FROM Factura f WHERE f.fecha = :fecha"),
    @NamedQuery(name = "Factura.findByTotalVenta", query = "SELECT f FROM Factura f WHERE f.totalVenta = :totalVenta"),
    @NamedQuery(name = "Factura.findByHora", query = "SELECT f FROM Factura f WHERE f.hora = :hora")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numFactura")
    private Integer numFactura;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalVenta")
    private BigDecimal totalVenta;
    @Basic(optional = false)
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @JoinColumn(name = "nitCliente", referencedColumnName = "nitCliente")
    @ManyToOne
    private Cliente nitCliente;
    @JoinColumn(name = "idEmpleado", referencedColumnName = "idEmpleado")
    @ManyToOne(optional = false)
    private Empleado idEmpleado;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "numFactura")
    private Detallefactura detallefactura;
    @OneToMany(mappedBy = "idFactura")
    private List<Registroventa> registroventaList;

    public Factura() {
    }

    public Factura(Integer numFactura) {
        this.numFactura = numFactura;
    }

    public Factura(Integer numFactura, Date hora) {
        this.numFactura = numFactura;
        this.hora = hora;
    }

    public Integer getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(Integer numFactura) {
        this.numFactura = numFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(BigDecimal totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Cliente getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(Cliente nitCliente) {
        this.nitCliente = nitCliente;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Detallefactura getDetallefactura() {
        return detallefactura;
    }

    public void setDetallefactura(Detallefactura detallefactura) {
        this.detallefactura = detallefactura;
    }

    @XmlTransient
    public List<Registroventa> getRegistroventaList() {
        return registroventaList;
    }

    public void setRegistroventaList(List<Registroventa> registroventaList) {
        this.registroventaList = registroventaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numFactura != null ? numFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.numFactura == null && other.numFactura != null) || (this.numFactura != null && !this.numFactura.equals(other.numFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Factura[ numFactura=" + numFactura + " ]";
    }
    
}

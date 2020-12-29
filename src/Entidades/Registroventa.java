/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author irisa
 */
@Entity
@Table(name = "registroventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registroventa.findAll", query = "SELECT r FROM Registroventa r"),
    @NamedQuery(name = "Registroventa.findByIdVenta", query = "SELECT r FROM Registroventa r WHERE r.idVenta = :idVenta")})
public class Registroventa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idVenta")
    private Integer idVenta;
    @JoinColumn(name = "idFactura", referencedColumnName = "numFactura")
    @ManyToOne
    private Factura idFactura;

    public Registroventa() {
    }

    public Registroventa(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Factura getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Factura idFactura) {
        this.idFactura = idFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVenta != null ? idVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registroventa)) {
            return false;
        }
        Registroventa other = (Registroventa) object;
        if ((this.idVenta == null && other.idVenta != null) || (this.idVenta != null && !this.idVenta.equals(other.idVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Registroventa[ idVenta=" + idVenta + " ]";
    }
    
}

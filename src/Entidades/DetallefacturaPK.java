/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author edu
 */
@Embeddable
public class DetallefacturaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idDetalle")
    private int idDetalle;
    @Basic(optional = false)
    @Column(name = "numFactura")
    private int numFactura;

    public DetallefacturaPK() {
    }

    public DetallefacturaPK(int idDetalle, int numFactura) {
        this.idDetalle = idDetalle;
        this.numFactura = numFactura;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDetalle;
        hash += (int) numFactura;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallefacturaPK)) {
            return false;
        }
        DetallefacturaPK other = (DetallefacturaPK) object;
        if (this.idDetalle != other.idDetalle) {
            return false;
        }
        if (this.numFactura != other.numFactura) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DetallefacturaPK[ idDetalle=" + idDetalle + ", numFactura=" + numFactura + " ]";
    }
    
}

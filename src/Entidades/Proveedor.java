/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author irisa
 */
@Entity
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByIdProveedor", query = "SELECT p FROM Proveedor p WHERE p.idProveedor = :idProveedor"),
    @NamedQuery(name = "Proveedor.findByNombreProveedor", query = "SELECT p FROM Proveedor p WHERE p.nombreProveedor = :nombreProveedor"),
    @NamedQuery(name = "Proveedor.findByNitProveedor", query = "SELECT p FROM Proveedor p WHERE p.nitProveedor = :nitProveedor"),
    @NamedQuery(name = "Proveedor.findByTelProveedor", query = "SELECT p FROM Proveedor p WHERE p.telProveedor = :telProveedor"),
    @NamedQuery(name = "Proveedor.findByDirProveedor", query = "SELECT p FROM Proveedor p WHERE p.dirProveedor = :dirProveedor")})
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProveedor")
    private Integer idProveedor;
    @Basic(optional = false)
    @Column(name = "nombreProveedor")
    private String nombreProveedor;
    @Basic(optional = false)
    @Column(name = "nitProveedor")
    private long nitProveedor;
    @Basic(optional = false)
    @Column(name = "telProveedor")
    private long telProveedor;
    @Basic(optional = false)
    @Column(name = "dirProveedor")
    private String dirProveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
    private List<Producto> productoList;

    public Proveedor() {
    }

    public Proveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Proveedor(Integer idProveedor, String nombreProveedor, long nitProveedor, long telProveedor, String dirProveedor) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.nitProveedor = nitProveedor;
        this.telProveedor = telProveedor;
        this.dirProveedor = dirProveedor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public long getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(long nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public long getTelProveedor() {
        return telProveedor;
    }

    public void setTelProveedor(long telProveedor) {
        this.telProveedor = telProveedor;
    }

    public String getDirProveedor() {
        return dirProveedor;
    }

    public void setDirProveedor(String dirProveedor) {
        this.dirProveedor = dirProveedor;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreProveedor;
    }
    
}

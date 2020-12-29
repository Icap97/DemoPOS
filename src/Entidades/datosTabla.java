/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.math.BigDecimal;

/**
 *
 * @author edu
 */
public class datosTabla {
     //"Cantidad","Nombre","Descripcion","P. Unidad","SubTotal"
    
    //String codigo;
    int cantidad;
    //String nombre;
    Producto producto;

    
    //String descripcion;
    //BigDecimal precio;
    BigDecimal subTotal;

    //public String getCodigo() {
      //  return codigo;
    //}

   // public void setCodigo(String codigo) {
     //   this.codigo = codigo;
    //}
public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

   // public String getNombre() {
     //   return nombre;
    //}

    //public void setNombre(String nombre) {
      //  this.nombre = nombre;
    //}

   /* public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }*/

    //public BigDecimal getPrecio() {
     //   return precio;
    //}

   // public void setPrecio(BigDecimal precio) {
   //     this.precio = precio;
   // }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
    
}

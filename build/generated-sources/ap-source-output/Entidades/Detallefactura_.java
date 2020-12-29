package Entidades;

import Entidades.DetallefacturaPK;
import Entidades.Factura;
import Entidades.Producto;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-22T18:46:53")
@StaticMetamodel(Detallefactura.class)
public class Detallefactura_ { 

    public static volatile SingularAttribute<Detallefactura, BigDecimal> precio;
    public static volatile SingularAttribute<Detallefactura, Integer> idDetalle;
    public static volatile SingularAttribute<Detallefactura, Integer> cantidad;
    public static volatile SingularAttribute<Detallefactura, Factura> numFactura;
    public static volatile SingularAttribute<Detallefactura, Producto> idProducto;
    public static volatile SingularAttribute<Detallefactura, DetallefacturaPK> detalleFacturaPK;

}
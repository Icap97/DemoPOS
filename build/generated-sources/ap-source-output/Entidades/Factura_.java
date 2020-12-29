package Entidades;

import Entidades.Cliente;
import Entidades.Detallefactura;
import Entidades.Empleado;
import Entidades.Registroventa;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-22T18:46:52")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile ListAttribute<Factura, Registroventa> registroventaList;
    public static volatile SingularAttribute<Factura, Date> fecha;
    public static volatile SingularAttribute<Factura, Empleado> idEmpleado;
    public static volatile SingularAttribute<Factura, Date> hora;
    public static volatile SingularAttribute<Factura, Detallefactura> detallefactura;
    public static volatile SingularAttribute<Factura, BigDecimal> totalVenta;
    public static volatile SingularAttribute<Factura, Integer> numFactura;
    public static volatile SingularAttribute<Factura, Cliente> nitCliente;

}
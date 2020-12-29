package Entidades;

import Entidades.Caja;
import Entidades.Factura;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-22T18:46:53")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, Integer> idEmpleado;
    public static volatile SingularAttribute<Empleado, String> apellidoEmpleado;
    public static volatile ListAttribute<Empleado, Factura> facturaList;
    public static volatile SingularAttribute<Empleado, String> contrasena;
    public static volatile SingularAttribute<Empleado, String> nombreEmpleado;
    public static volatile SingularAttribute<Empleado, String> tipoEmpleado;
    public static volatile ListAttribute<Empleado, Caja> cajaList;

}
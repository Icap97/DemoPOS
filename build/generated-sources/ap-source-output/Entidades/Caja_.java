package Entidades;

import Entidades.Empleado;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-22T18:46:53")
@StaticMetamodel(Caja.class)
public class Caja_ { 

    public static volatile SingularAttribute<Caja, Date> fecha;
    public static volatile SingularAttribute<Caja, Double> monto;
    public static volatile SingularAttribute<Caja, Empleado> idEmpleado;
    public static volatile SingularAttribute<Caja, Date> hora;
    public static volatile SingularAttribute<Caja, Integer> idRegistroCaja;

}
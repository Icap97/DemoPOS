/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Factura;
import java.util.ArrayList;
import java.util.List;
import Entidades.Caja;
import Entidades.Empleado;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author irisa
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("posVersion4PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) throws PreexistingEntityException, Exception {
        if (empleado.getFacturaList() == null) {
            empleado.setFacturaList(new ArrayList<Factura>());
        }
        if (empleado.getCajaList() == null) {
            empleado.setCajaList(new ArrayList<Caja>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Factura> attachedFacturaList = new ArrayList<Factura>();
            for (Factura facturaListFacturaToAttach : empleado.getFacturaList()) {
                facturaListFacturaToAttach = em.getReference(facturaListFacturaToAttach.getClass(), facturaListFacturaToAttach.getNumFactura());
                attachedFacturaList.add(facturaListFacturaToAttach);
            }
            empleado.setFacturaList(attachedFacturaList);
            List<Caja> attachedCajaList = new ArrayList<Caja>();
            for (Caja cajaListCajaToAttach : empleado.getCajaList()) {
                cajaListCajaToAttach = em.getReference(cajaListCajaToAttach.getClass(), cajaListCajaToAttach.getIdRegistroCaja());
                attachedCajaList.add(cajaListCajaToAttach);
            }
            empleado.setCajaList(attachedCajaList);
            em.persist(empleado);
            for (Factura facturaListFactura : empleado.getFacturaList()) {
                Empleado oldIdEmpleadoOfFacturaListFactura = facturaListFactura.getIdEmpleado();
                facturaListFactura.setIdEmpleado(empleado);
                facturaListFactura = em.merge(facturaListFactura);
                if (oldIdEmpleadoOfFacturaListFactura != null) {
                    oldIdEmpleadoOfFacturaListFactura.getFacturaList().remove(facturaListFactura);
                    oldIdEmpleadoOfFacturaListFactura = em.merge(oldIdEmpleadoOfFacturaListFactura);
                }
            }
            for (Caja cajaListCaja : empleado.getCajaList()) {
                Empleado oldIdEmpleadoOfCajaListCaja = cajaListCaja.getIdEmpleado();
                cajaListCaja.setIdEmpleado(empleado);
                cajaListCaja = em.merge(cajaListCaja);
                if (oldIdEmpleadoOfCajaListCaja != null) {
                    oldIdEmpleadoOfCajaListCaja.getCajaList().remove(cajaListCaja);
                    oldIdEmpleadoOfCajaListCaja = em.merge(oldIdEmpleadoOfCajaListCaja);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpleado(empleado.getIdEmpleado()) != null) {
                throw new PreexistingEntityException("Empleado " + empleado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getIdEmpleado());
            List<Factura> facturaListOld = persistentEmpleado.getFacturaList();
            List<Factura> facturaListNew = empleado.getFacturaList();
            List<Caja> cajaListOld = persistentEmpleado.getCajaList();
            List<Caja> cajaListNew = empleado.getCajaList();
            List<String> illegalOrphanMessages = null;
            for (Factura facturaListOldFactura : facturaListOld) {
                if (!facturaListNew.contains(facturaListOldFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Factura " + facturaListOldFactura + " since its idEmpleado field is not nullable.");
                }
            }
            for (Caja cajaListOldCaja : cajaListOld) {
                if (!cajaListNew.contains(cajaListOldCaja)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Caja " + cajaListOldCaja + " since its idEmpleado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Factura> attachedFacturaListNew = new ArrayList<Factura>();
            for (Factura facturaListNewFacturaToAttach : facturaListNew) {
                facturaListNewFacturaToAttach = em.getReference(facturaListNewFacturaToAttach.getClass(), facturaListNewFacturaToAttach.getNumFactura());
                attachedFacturaListNew.add(facturaListNewFacturaToAttach);
            }
            facturaListNew = attachedFacturaListNew;
            empleado.setFacturaList(facturaListNew);
            List<Caja> attachedCajaListNew = new ArrayList<Caja>();
            for (Caja cajaListNewCajaToAttach : cajaListNew) {
                cajaListNewCajaToAttach = em.getReference(cajaListNewCajaToAttach.getClass(), cajaListNewCajaToAttach.getIdRegistroCaja());
                attachedCajaListNew.add(cajaListNewCajaToAttach);
            }
            cajaListNew = attachedCajaListNew;
            empleado.setCajaList(cajaListNew);
            empleado = em.merge(empleado);
            for (Factura facturaListNewFactura : facturaListNew) {
                if (!facturaListOld.contains(facturaListNewFactura)) {
                    Empleado oldIdEmpleadoOfFacturaListNewFactura = facturaListNewFactura.getIdEmpleado();
                    facturaListNewFactura.setIdEmpleado(empleado);
                    facturaListNewFactura = em.merge(facturaListNewFactura);
                    if (oldIdEmpleadoOfFacturaListNewFactura != null && !oldIdEmpleadoOfFacturaListNewFactura.equals(empleado)) {
                        oldIdEmpleadoOfFacturaListNewFactura.getFacturaList().remove(facturaListNewFactura);
                        oldIdEmpleadoOfFacturaListNewFactura = em.merge(oldIdEmpleadoOfFacturaListNewFactura);
                    }
                }
            }
            for (Caja cajaListNewCaja : cajaListNew) {
                if (!cajaListOld.contains(cajaListNewCaja)) {
                    Empleado oldIdEmpleadoOfCajaListNewCaja = cajaListNewCaja.getIdEmpleado();
                    cajaListNewCaja.setIdEmpleado(empleado);
                    cajaListNewCaja = em.merge(cajaListNewCaja);
                    if (oldIdEmpleadoOfCajaListNewCaja != null && !oldIdEmpleadoOfCajaListNewCaja.equals(empleado)) {
                        oldIdEmpleadoOfCajaListNewCaja.getCajaList().remove(cajaListNewCaja);
                        oldIdEmpleadoOfCajaListNewCaja = em.merge(oldIdEmpleadoOfCajaListNewCaja);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleado.getIdEmpleado();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getIdEmpleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Factura> facturaListOrphanCheck = empleado.getFacturaList();
            for (Factura facturaListOrphanCheckFactura : facturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Factura " + facturaListOrphanCheckFactura + " in its facturaList field has a non-nullable idEmpleado field.");
            }
            List<Caja> cajaListOrphanCheck = empleado.getCajaList();
            for (Caja cajaListOrphanCheckCaja : cajaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Caja " + cajaListOrphanCheckCaja + " in its cajaList field has a non-nullable idEmpleado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Empleado findEmpleado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

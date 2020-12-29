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
import Entidades.Cliente;
import Entidades.Empleado;
import Entidades.Detallefactura;
import Entidades.Factura;
import Entidades.Registroventa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author irisa
 */
public class FacturaJpaController implements Serializable {

    public FacturaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("posVersion4PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) throws PreexistingEntityException, Exception {
        if (factura.getRegistroventaList() == null) {
            factura.setRegistroventaList(new ArrayList<Registroventa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente nitCliente = factura.getNitCliente();
            if (nitCliente != null) {
                nitCliente = em.getReference(nitCliente.getClass(), nitCliente.getNitCliente());
                factura.setNitCliente(nitCliente);
            }
            Empleado idEmpleado = factura.getIdEmpleado();
            if (idEmpleado != null) {
                idEmpleado = em.getReference(idEmpleado.getClass(), idEmpleado.getIdEmpleado());
                factura.setIdEmpleado(idEmpleado);
            }
            Detallefactura detallefactura = factura.getDetallefactura();
            if (detallefactura != null) {
                detallefactura = em.getReference(detallefactura.getClass(), detallefactura.getIdDetalle());
                factura.setDetallefactura(detallefactura);
            }
            List<Registroventa> attachedRegistroventaList = new ArrayList<Registroventa>();
            for (Registroventa registroventaListRegistroventaToAttach : factura.getRegistroventaList()) {
                registroventaListRegistroventaToAttach = em.getReference(registroventaListRegistroventaToAttach.getClass(), registroventaListRegistroventaToAttach.getIdVenta());
                attachedRegistroventaList.add(registroventaListRegistroventaToAttach);
            }
            factura.setRegistroventaList(attachedRegistroventaList);
            em.persist(factura);
            if (nitCliente != null) {
                nitCliente.getFacturaList().add(factura);
                nitCliente = em.merge(nitCliente);
            }
            if (idEmpleado != null) {
                idEmpleado.getFacturaList().add(factura);
                idEmpleado = em.merge(idEmpleado);
            }
            if (detallefactura != null) {
                Factura oldNumFacturaOfDetallefactura = detallefactura.getNumFactura();
                if (oldNumFacturaOfDetallefactura != null) {
                    oldNumFacturaOfDetallefactura.setDetallefactura(null);
                    oldNumFacturaOfDetallefactura = em.merge(oldNumFacturaOfDetallefactura);
                }
                detallefactura.setNumFactura(factura);
                detallefactura = em.merge(detallefactura);
            }
            for (Registroventa registroventaListRegistroventa : factura.getRegistroventaList()) {
                Factura oldIdFacturaOfRegistroventaListRegistroventa = registroventaListRegistroventa.getIdFactura();
                registroventaListRegistroventa.setIdFactura(factura);
                registroventaListRegistroventa = em.merge(registroventaListRegistroventa);
                if (oldIdFacturaOfRegistroventaListRegistroventa != null) {
                    oldIdFacturaOfRegistroventaListRegistroventa.getRegistroventaList().remove(registroventaListRegistroventa);
                    oldIdFacturaOfRegistroventaListRegistroventa = em.merge(oldIdFacturaOfRegistroventaListRegistroventa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFactura(factura.getNumFactura()) != null) {
                throw new PreexistingEntityException("Factura " + factura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getNumFactura());
            Cliente nitClienteOld = persistentFactura.getNitCliente();
            Cliente nitClienteNew = factura.getNitCliente();
            Empleado idEmpleadoOld = persistentFactura.getIdEmpleado();
            Empleado idEmpleadoNew = factura.getIdEmpleado();
            Detallefactura detallefacturaOld = persistentFactura.getDetallefactura();
            Detallefactura detallefacturaNew = factura.getDetallefactura();
            List<Registroventa> registroventaListOld = persistentFactura.getRegistroventaList();
            List<Registroventa> registroventaListNew = factura.getRegistroventaList();
            List<String> illegalOrphanMessages = null;
            if (detallefacturaOld != null && !detallefacturaOld.equals(detallefacturaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Detallefactura " + detallefacturaOld + " since its numFactura field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (nitClienteNew != null) {
                nitClienteNew = em.getReference(nitClienteNew.getClass(), nitClienteNew.getNitCliente());
                factura.setNitCliente(nitClienteNew);
            }
            if (idEmpleadoNew != null) {
                idEmpleadoNew = em.getReference(idEmpleadoNew.getClass(), idEmpleadoNew.getIdEmpleado());
                factura.setIdEmpleado(idEmpleadoNew);
            }
            if (detallefacturaNew != null) {
                detallefacturaNew = em.getReference(detallefacturaNew.getClass(), detallefacturaNew.getIdDetalle());
                factura.setDetallefactura(detallefacturaNew);
            }
            List<Registroventa> attachedRegistroventaListNew = new ArrayList<Registroventa>();
            for (Registroventa registroventaListNewRegistroventaToAttach : registroventaListNew) {
                registroventaListNewRegistroventaToAttach = em.getReference(registroventaListNewRegistroventaToAttach.getClass(), registroventaListNewRegistroventaToAttach.getIdVenta());
                attachedRegistroventaListNew.add(registroventaListNewRegistroventaToAttach);
            }
            registroventaListNew = attachedRegistroventaListNew;
            factura.setRegistroventaList(registroventaListNew);
            factura = em.merge(factura);
            if (nitClienteOld != null && !nitClienteOld.equals(nitClienteNew)) {
                nitClienteOld.getFacturaList().remove(factura);
                nitClienteOld = em.merge(nitClienteOld);
            }
            if (nitClienteNew != null && !nitClienteNew.equals(nitClienteOld)) {
                nitClienteNew.getFacturaList().add(factura);
                nitClienteNew = em.merge(nitClienteNew);
            }
            if (idEmpleadoOld != null && !idEmpleadoOld.equals(idEmpleadoNew)) {
                idEmpleadoOld.getFacturaList().remove(factura);
                idEmpleadoOld = em.merge(idEmpleadoOld);
            }
            if (idEmpleadoNew != null && !idEmpleadoNew.equals(idEmpleadoOld)) {
                idEmpleadoNew.getFacturaList().add(factura);
                idEmpleadoNew = em.merge(idEmpleadoNew);
            }
            if (detallefacturaNew != null && !detallefacturaNew.equals(detallefacturaOld)) {
                Factura oldNumFacturaOfDetallefactura = detallefacturaNew.getNumFactura();
                if (oldNumFacturaOfDetallefactura != null) {
                    oldNumFacturaOfDetallefactura.setDetallefactura(null);
                    oldNumFacturaOfDetallefactura = em.merge(oldNumFacturaOfDetallefactura);
                }
                detallefacturaNew.setNumFactura(factura);
                detallefacturaNew = em.merge(detallefacturaNew);
            }
            for (Registroventa registroventaListOldRegistroventa : registroventaListOld) {
                if (!registroventaListNew.contains(registroventaListOldRegistroventa)) {
                    registroventaListOldRegistroventa.setIdFactura(null);
                    registroventaListOldRegistroventa = em.merge(registroventaListOldRegistroventa);
                }
            }
            for (Registroventa registroventaListNewRegistroventa : registroventaListNew) {
                if (!registroventaListOld.contains(registroventaListNewRegistroventa)) {
                    Factura oldIdFacturaOfRegistroventaListNewRegistroventa = registroventaListNewRegistroventa.getIdFactura();
                    registroventaListNewRegistroventa.setIdFactura(factura);
                    registroventaListNewRegistroventa = em.merge(registroventaListNewRegistroventa);
                    if (oldIdFacturaOfRegistroventaListNewRegistroventa != null && !oldIdFacturaOfRegistroventaListNewRegistroventa.equals(factura)) {
                        oldIdFacturaOfRegistroventaListNewRegistroventa.getRegistroventaList().remove(registroventaListNewRegistroventa);
                        oldIdFacturaOfRegistroventaListNewRegistroventa = em.merge(oldIdFacturaOfRegistroventaListNewRegistroventa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getNumFactura();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getNumFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Detallefactura detallefacturaOrphanCheck = factura.getDetallefactura();
            if (detallefacturaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the Detallefactura " + detallefacturaOrphanCheck + " in its detallefactura field has a non-nullable numFactura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente nitCliente = factura.getNitCliente();
            if (nitCliente != null) {
                nitCliente.getFacturaList().remove(factura);
                nitCliente = em.merge(nitCliente);
            }
            Empleado idEmpleado = factura.getIdEmpleado();
            if (idEmpleado != null) {
                idEmpleado.getFacturaList().remove(factura);
                idEmpleado = em.merge(idEmpleado);
            }
            List<Registroventa> registroventaList = factura.getRegistroventaList();
            for (Registroventa registroventaListRegistroventa : registroventaList) {
                registroventaListRegistroventa.setIdFactura(null);
                registroventaListRegistroventa = em.merge(registroventaListRegistroventa);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Factura;
import Entidades.Registroventa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author irisa
 */
public class RegistroventaJpaController implements Serializable {

    public RegistroventaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("posVersion4PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registroventa registroventa) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura idFactura = registroventa.getIdFactura();
            if (idFactura != null) {
                idFactura = em.getReference(idFactura.getClass(), idFactura.getNumFactura());
                registroventa.setIdFactura(idFactura);
            }
            em.persist(registroventa);
            if (idFactura != null) {
                idFactura.getRegistroventaList().add(registroventa);
                idFactura = em.merge(idFactura);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistroventa(registroventa.getIdVenta()) != null) {
                throw new PreexistingEntityException("Registroventa " + registroventa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroventa registroventa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroventa persistentRegistroventa = em.find(Registroventa.class, registroventa.getIdVenta());
            Factura idFacturaOld = persistentRegistroventa.getIdFactura();
            Factura idFacturaNew = registroventa.getIdFactura();
            if (idFacturaNew != null) {
                idFacturaNew = em.getReference(idFacturaNew.getClass(), idFacturaNew.getNumFactura());
                registroventa.setIdFactura(idFacturaNew);
            }
            registroventa = em.merge(registroventa);
            if (idFacturaOld != null && !idFacturaOld.equals(idFacturaNew)) {
                idFacturaOld.getRegistroventaList().remove(registroventa);
                idFacturaOld = em.merge(idFacturaOld);
            }
            if (idFacturaNew != null && !idFacturaNew.equals(idFacturaOld)) {
                idFacturaNew.getRegistroventaList().add(registroventa);
                idFacturaNew = em.merge(idFacturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registroventa.getIdVenta();
                if (findRegistroventa(id) == null) {
                    throw new NonexistentEntityException("The registroventa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroventa registroventa;
            try {
                registroventa = em.getReference(Registroventa.class, id);
                registroventa.getIdVenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroventa with id " + id + " no longer exists.", enfe);
            }
            Factura idFactura = registroventa.getIdFactura();
            if (idFactura != null) {
                idFactura.getRegistroventaList().remove(registroventa);
                idFactura = em.merge(idFactura);
            }
            em.remove(registroventa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registroventa> findRegistroventaEntities() {
        return findRegistroventaEntities(true, -1, -1);
    }

    public List<Registroventa> findRegistroventaEntities(int maxResults, int firstResult) {
        return findRegistroventaEntities(false, maxResults, firstResult);
    }

    private List<Registroventa> findRegistroventaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registroventa.class));
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

    public Registroventa findRegistroventa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registroventa.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroventaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registroventa> rt = cq.from(Registroventa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

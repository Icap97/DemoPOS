/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Entidades.Detallefactura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Factura;
import Entidades.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author irisa
 */
public class DetallefacturaJpaController implements Serializable {

    public DetallefacturaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("posVersion4PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallefactura detallefactura) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        Factura numFacturaOrphanCheck = detallefactura.getNumFactura();
        if (numFacturaOrphanCheck != null) {
            Detallefactura oldDetallefacturaOfNumFactura = numFacturaOrphanCheck.getDetallefactura();
            if (oldDetallefacturaOfNumFactura != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Factura " + numFacturaOrphanCheck + " already has an item of type Detallefactura whose numFactura column cannot be null. Please make another selection for the numFactura field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura numFactura = detallefactura.getNumFactura();
            if (numFactura != null) {
                numFactura = em.getReference(numFactura.getClass(), numFactura.getNumFactura());
                detallefactura.setNumFactura(numFactura);
            }
            Producto idProducto = detallefactura.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getIdProducto());
                detallefactura.setIdProducto(idProducto);
            }
            em.persist(detallefactura);
            if (numFactura != null) {
                numFactura.setDetallefactura(detallefactura);
                numFactura = em.merge(numFactura);
            }
            if (idProducto != null) {
                idProducto.getDetallefacturaList().add(detallefactura);
                idProducto = em.merge(idProducto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallefactura detallefactura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefactura persistentDetallefactura = em.find(Detallefactura.class, detallefactura.getIdDetalle());
            Factura numFacturaOld = persistentDetallefactura.getNumFactura();
            Factura numFacturaNew = detallefactura.getNumFactura();
            Producto idProductoOld = persistentDetallefactura.getIdProducto();
            Producto idProductoNew = detallefactura.getIdProducto();
            List<String> illegalOrphanMessages = null;
            if (numFacturaNew != null && !numFacturaNew.equals(numFacturaOld)) {
                Detallefactura oldDetallefacturaOfNumFactura = numFacturaNew.getDetallefactura();
                if (oldDetallefacturaOfNumFactura != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Factura " + numFacturaNew + " already has an item of type Detallefactura whose numFactura column cannot be null. Please make another selection for the numFactura field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (numFacturaNew != null) {
                numFacturaNew = em.getReference(numFacturaNew.getClass(), numFacturaNew.getNumFactura());
                detallefactura.setNumFactura(numFacturaNew);
            }
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getIdProducto());
                detallefactura.setIdProducto(idProductoNew);
            }
            detallefactura = em.merge(detallefactura);
            if (numFacturaOld != null && !numFacturaOld.equals(numFacturaNew)) {
                numFacturaOld.setDetallefactura(null);
                numFacturaOld = em.merge(numFacturaOld);
            }
            if (numFacturaNew != null && !numFacturaNew.equals(numFacturaOld)) {
                numFacturaNew.setDetallefactura(detallefactura);
                numFacturaNew = em.merge(numFacturaNew);
            }
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getDetallefacturaList().remove(detallefactura);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getDetallefacturaList().add(detallefactura);
                idProductoNew = em.merge(idProductoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallefactura.getIdDetalle();
                if (findDetallefactura(id) == null) {
                    throw new NonexistentEntityException("The detallefactura with id " + id + " no longer exists.");
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
            Detallefactura detallefactura;
            try {
                detallefactura = em.getReference(Detallefactura.class, id);
                detallefactura.getIdDetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallefactura with id " + id + " no longer exists.", enfe);
            }
            Factura numFactura = detallefactura.getNumFactura();
            if (numFactura != null) {
                numFactura.setDetallefactura(null);
                numFactura = em.merge(numFactura);
            }
            Producto idProducto = detallefactura.getIdProducto();
            if (idProducto != null) {
                idProducto.getDetallefacturaList().remove(detallefactura);
                idProducto = em.merge(idProducto);
            }
            em.remove(detallefactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallefactura> findDetallefacturaEntities() {
        return findDetallefacturaEntities(true, -1, -1);
    }

    public List<Detallefactura> findDetallefacturaEntities(int maxResults, int firstResult) {
        return findDetallefacturaEntities(false, maxResults, firstResult);
    }

    private List<Detallefactura> findDetallefacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallefactura.class));
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

    public Detallefactura findDetallefactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallefactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallefacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallefactura> rt = cq.from(Detallefactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

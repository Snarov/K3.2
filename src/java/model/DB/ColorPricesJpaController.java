/**
 * БГУИР Кафедра экономической информатики Курсовой проект ВСРПП Снаров Иван гр. 272303
 */
package model.DB;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.DB.exceptions.NonexistentEntityException;
import model.DB.exceptions.PreexistingEntityException;

/**
 *
 * @author kiskin
 */
public class ColorPricesJpaController implements Serializable {

	public ColorPricesJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(ColorPrices colorPrices) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(colorPrices);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findColorPrices(colorPrices.getColor()) != null) {
				throw new PreexistingEntityException("ColorPrices " + colorPrices + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(ColorPrices colorPrices) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			colorPrices = em.merge(colorPrices);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Object id = colorPrices.getColor();
				if (findColorPrices(id) == null) {
					throw new NonexistentEntityException("The colorPrices with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroy(Object id) throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			ColorPrices colorPrices;
			try {
				colorPrices = em.getReference(ColorPrices.class, id);
				colorPrices.getColor();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The colorPrices with id " + id + " no longer exists.", enfe);
			}
			em.remove(colorPrices);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<ColorPrices> findColorPricesEntities() {
		return findColorPricesEntities(true, -1, -1);
	}

	public List<ColorPrices> findColorPricesEntities(int maxResults, int firstResult) {
		return findColorPricesEntities(false, maxResults, firstResult);
	}

	private List<ColorPrices> findColorPricesEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(ColorPrices.class));
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

	public ColorPrices findColorPrices(Object id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(ColorPrices.class, id);
		} finally {
			em.close();
		}
	}

	public int getColorPricesCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<ColorPrices> rt = cq.from(ColorPrices.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}

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

/**
 *
 * @author kiskin
 */
public class WalletsJpaController implements Serializable {

	public WalletsJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Wallets wallets) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(wallets);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Wallets wallets) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			wallets = em.merge(wallets);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = wallets.getId();
				if (findWallets(id) == null) {
					throw new NonexistentEntityException("The wallets with id " + id + " no longer exists.");
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
			Wallets wallets;
			try {
				wallets = em.getReference(Wallets.class, id);
				wallets.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The wallets with id " + id + " no longer exists.", enfe);
			}
			em.remove(wallets);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Wallets> findWalletsEntities() {
		return findWalletsEntities(true, -1, -1);
	}

	public List<Wallets> findWalletsEntities(int maxResults, int firstResult) {
		return findWalletsEntities(false, maxResults, firstResult);
	}

	private List<Wallets> findWalletsEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Wallets.class));
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

	public Wallets findWallets(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Wallets.class, id);
		} finally {
			em.close();
		}
	}

	public int getWalletsCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Wallets> rt = cq.from(Wallets.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}

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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.DB.exceptions.NonexistentEntityException;

/**
 *
 * @author kiskin
 */
public class ModelsJpaController implements Serializable {

	public ModelsJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Models models) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(models);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Models models) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			models = em.merge(models);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = models.getId();
				if (findModels(id) == null) {
					throw new NonexistentEntityException("The models with id " + id + " no longer exists.");
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
			Models models;
			try {
				models = em.getReference(Models.class, id);
				models.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The models with id " + id + " no longer exists.", enfe);
			}
			em.remove(models);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Models> findModelsEntities() {
		return findModelsEntities(true, -1, -1);
	}

	public List<Models> findModelsEntities(int maxResults, int firstResult) {
		return findModelsEntities(false, maxResults, firstResult);
	}

	private List<Models> findModelsEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Models.class));
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

	public Models findModels(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Models.class, id);
		} finally {
			em.close();
		}
	}

	public int getModelsCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Models> rt = cq.from(Models.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

	public Models findModelByName(String carName) {
		EntityManager em = getEntityManager();
		try {
			CriteriaBuilder builder = emf.getCriteriaBuilder();
			CriteriaQuery<Models> criteria = builder.createQuery(Models.class);
			Root<Models> modelsRoot = criteria.from(Models.class);
			criteria.select(modelsRoot);
			criteria.where(builder.equal(modelsRoot.get(Models_.name), carName));
			List<Models> models = em.createQuery(criteria).getResultList();

			if (models.size() > 0) {
				return models.get(0);
			} else {
				return null;
			}
		} finally {
			em.close();
		}
	}
	
}

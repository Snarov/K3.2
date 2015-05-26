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
public class UsersJpaController implements Serializable {

	public UsersJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Users users) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(users);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Users users) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			users = em.merge(users);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = users.getId();
				if (findUsers(id) == null) {
					throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
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
			Users users;
			try {
				users = em.getReference(Users.class, id);
				users.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
			}
			em.remove(users);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Users> findUsersEntities() {
		return findUsersEntities(true, -1, -1);
	}

	public List<Users> findUsersEntities(int maxResults, int firstResult) {
		return findUsersEntities(false, maxResults, firstResult);
	}

	private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Users.class));
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

	public Users findUsers(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Users.class, id);
		} finally {
			em.close();
		}
	}

	public Users findUserByName(String name) {
		EntityManager em = getEntityManager();
		try {
			CriteriaBuilder builder = emf.getCriteriaBuilder();
			CriteriaQuery<Users> criteria = builder.createQuery(Users.class);
			Root<Users> usersRoot = criteria.from(Users.class);
			criteria.select(usersRoot);
			criteria.where(builder.equal(usersRoot.get(Users_.name), name));
			List<Users> users = em.createQuery(criteria).getResultList();

			if (users.size() > 0) {
				return users.get(0);
			} else {
				return null;
			}
		} finally {
			em.close();
		}
	}

	public int getUsersCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Users> rt = cq.from(Users.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}

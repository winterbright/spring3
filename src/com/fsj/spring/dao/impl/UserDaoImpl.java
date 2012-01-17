package com.fsj.spring.dao.impl;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fsj.spring.dao.IUserDao;
import com.fsj.spring.model.TUser;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements IUserDao {
	
	private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	protected void initDao() {
	}

	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#save(com.fsj.spring.model.TUser)
	 */
	public void save(TUser transientInstance) {
		log.debug("saving TUser instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#delete(com.fsj.spring.model.TUser)
	 */
	public void delete(TUser persistentInstance) {
		log.debug("deleting TUser instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#findById(java.lang.Integer)
	 */
	public TUser findById(java.lang.Integer id) {
		log.debug("getting TUser instance with id: " + id);
		try {
			TUser instance = (TUser) getHibernateTemplate().get(
					"com.fsj.spring.model.TUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#findByExample(com.fsj.spring.model.TUser)
	 */
	public List findByExample(TUser instance) {
		log.debug("finding TUser instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TUser as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#findByName(java.lang.Object)
	 */
	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#findByAge(java.lang.Object)
	 */
	public List findByAge(Object age) {
		return findByProperty(AGE, age);
	}

	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#findByDeptId(java.lang.Object)
	 */
	public List findByDeptId(Object deptId) {
		return findByProperty(DEPT_ID, deptId);
	}

	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all TUser instances");
		try {
			String queryString = "from TUser";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#merge(com.fsj.spring.model.TUser)
	 */
	public TUser merge(TUser detachedInstance) {
		log.debug("merging TUser instance");
		try {
			TUser result = (TUser) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#attachDirty(com.fsj.spring.model.TUser)
	 */
	public void attachDirty(TUser instance) {
		log.debug("attaching dirty TUser instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#attachClean(com.fsj.spring.model.TUser)
	 */
	public void attachClean(TUser instance) {
		log.debug("attaching clean TUser instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IUserDao getFromApplicationContext(ApplicationContext ctx) {
		return (IUserDao) ctx.getBean("TUserDAO");
	}
}
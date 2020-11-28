package com.luminous.integration.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luminous.integration.dao.DigimonsDAO;
import com.luminous.integration.model.Digimon;
import com.luminous.integration.model.Favorite;
import com.luminous.integration.util.HibernateUtil;

@Repository
public class DefaultDigimonsDAO implements DigimonsDAO {
	private SessionFactory sessionFactory;
	
	public DefaultDigimonsDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
		
	}

	@Override
	public List<Digimon> addAll(List<Digimon> digimons) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		digimons.stream().forEach(session::save);
		session.getTransaction().commit();
		session.close();
		return digimons;
	}

	@Override
	public List<Digimon> getAllDigimon() {

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Digimon> cq = cb.createQuery(Digimon.class);

			Root<Digimon> rootEntry = cq.from(Digimon.class);
			CriteriaQuery<Digimon> all = cq.select(rootEntry);

			TypedQuery<Digimon> allQuery = session.createQuery(all);
			List<Digimon> digimons = allQuery.getResultList();
			session.getTransaction().commit();
			return digimons;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Digimon getDigimonByName(String name) {

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Digimon digimon = session
					.createQuery("SELECT a FROM Digimon a where name='" + name + "'", Digimon.class).getSingleResult();

			session.getTransaction().commit();
			return digimon;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Digimon getDigimonByLevel(String level) {

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			Digimon digimon = session
					.createQuery("SELECT a FROM Digimon a where level='" + level + "'", Digimon.class).getSingleResult();

			session.getTransaction().commit();

			return digimon;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Digimon updateDigimon(Digimon digimon) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.saveOrUpdate(digimon);
		session.getTransaction().commit();
		session.close();
		return digimon;

	}

	@Override
	public void deleteDigimon(long id) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("delete Digimon where id= :value");
		query.setParameter("value", id);

		query.executeUpdate();

		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Favorite> getAllFavorites() {

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Favorite> cq = cb.createQuery(Favorite.class);

			Root<Favorite> rootEntry = cq.from(Favorite.class);
			CriteriaQuery<Favorite> all = cq.select(rootEntry);

			TypedQuery<Favorite> allQuery = session.createQuery(all);
			List<Favorite> favorites = allQuery.getResultList();
			session.getTransaction().commit();
			return favorites;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Favorite getFavoriteByName(String name) {
		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			Favorite favorites = session
					.createQuery("SELECT a FROM Favorite a where name='" + name + "'", Favorite.class).getSingleResult();

			session.getTransaction().commit();

			return favorites;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Favorite getFavoriteByLevel(String level) {

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			Favorite favorites = session
					.createQuery("SELECT a FROM Favorite a where level='" + level + "'", Favorite.class)
					.getSingleResult();

			session.getTransaction().commit();

			return favorites;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Favorite updateFavorite(Favorite favorite) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(favorite);
		session.getTransaction().commit();
		session.close();
		return favorite;
	}

	@Override
	public void deleteFavorite(long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("delete Favorite where id= :value");
		query.setParameter("value", id);

		query.executeUpdate();

		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Digimon addDigimon(Digimon digimon) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(digimon);
		session.getTransaction().commit();
		session.close();
		return digimon;
	}

	@Override
	public Favorite addFavorite(Digimon digimon) {
		Favorite favorite=new Favorite(digimon.getName(), digimon.getImg(), digimon.getLevel());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(favorite);
		session.getTransaction().commit();
		session.close();
		return favorite;
	}

	@Override
	public Digimon getDigimonByID(long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Digimon digimon = session.get(Digimon.class, id);
		session.getTransaction().commit();
		session.close();
		return digimon;
	}

	@Override
	public Favorite getFavoriteByID(long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Favorite favorite = session.get(Favorite.class, id);
		session.getTransaction().commit();
		session.close();
		return favorite;
	}
	@Override
	public void clearAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String stringQuery = "DELETE FROM Favorite";
		Query query = session.createQuery(stringQuery);
		query.executeUpdate();
		
		
		
		String stringQuery2 = "DELETE FROM Digimon";
		Query query2 = session.createQuery(stringQuery2);
		query2.executeUpdate();

		

		session.getTransaction().commit();
		session.close();
		
	}

}

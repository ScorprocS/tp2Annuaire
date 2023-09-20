package fr.mns.jee.tp2Annuaire.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import fr.mns.jee.tp2Annuaire.model.City;
import fr.mns.jee.tp2Annuaire.model.Gender;
import fr.mns.jee.tp2Annuaire.model.Person;

public class PersonDao extends AbstractDao<Person> {
	private EntityManager entityManager;

	public PersonDao() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tp2Annuaire");
		entityManager = entityManagerFactory.createEntityManager();

	}

	@Override
	public Person find(Long id) {
		return entityManager.find(Person.class, id);
	}

	@Override
	public List<Person> findAll() {
		TypedQuery<Person> query = entityManager.createQuery("select person from Person person",Person.class);
		return query.getResultList();

	}

	@Override
	public Person create(Person entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Person update(Person entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	public void delete(Person entity) {
		entityManager.remove(entity);
	}
	
	public List<Person> findAllOver20Y(){
		LocalDate ld=LocalDate.of(2003, 9, 20);
		
	
		TypedQuery<Person> query = entityManager.createQuery("select person from Person person where (person.birthDate>? )",Person.class);
		query.setParameter(0, ld);
		return query.getResultList();
	}
	
	public List<Person> findallCriteria( Gender gender) {
	     
	      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	      CriteriaQuery<Person> q = cb.createQuery(Person.class);
	      Root<Person> person = q.from(Person.class);
	      q.select(person);
	      List<Predicate> predicates = new ArrayList<>();
	      predicates.add(cb.equal(person.get("gender"), gender)); //nani
	    
	      q.where(predicates.toArray(predicates.toArray(new Predicate[1])));
	      cb.desc(person.get("firstname"));
	      TypedQuery<Person> query = entityManager.createQuery(q);
	      List<Person> results = query.getResultList();
	      return results;
	   }

	/*
	@Override*/
	public List<Person> findAllByCityName(String name){
		TypedQuery<Person> query = entityManager.createQuery("select person from Person person where pers",Person.class);
		return query.getResultList();
	}

	
	public static List<Person> findAllAccordingToACity(EntityManager em, City city) {
		   TypedQuery<Person> query = 
				   em.createQuery("select person from Person person inner join person.adress a inner join a.city c where c.name = :cityName",Person.class);
		   query.setParameter("cityName", city.getName());
		   return query.getResultList();
		}

	@Override
	public Person delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}

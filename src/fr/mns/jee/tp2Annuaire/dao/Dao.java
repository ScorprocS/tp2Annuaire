package fr.mns.jee.tp2Annuaire.dao;

import java.io.Serializable;
import java.util.List;

import fr.mns.jee.tp2Annuaire.model.Person;

public interface Dao<T extends Serializable> {
	T find(Long id);
	List<T> findAll();
	T create(T entity);
	T update(T entity);
	T delete(Long id);
	void delete(T entity);



	
}

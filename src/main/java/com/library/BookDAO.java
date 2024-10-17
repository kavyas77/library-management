package com.library;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped

public class BookDAO {
	
	@PersistenceContext
    EntityManager em;
	
	@Transactional
    public void persist(Book book) {
        em.persist(book);  
    }
	
	public List<Book> listAll() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
	
	
	public Book findById(Long id) {
        return em.find(Book.class, id);
    }
	
	@Transactional
	public void delete(Book book) {
        em.remove(em.contains(book) ? book : em.merge(book));
    }
	
	@Transactional
	public Book update(Book book) {
        return em.merge(book);
    }
}
	
	
	
	
	
	
	
	
	
	
	
	
	
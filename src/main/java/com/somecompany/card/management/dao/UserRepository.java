package com.somecompany.card.management.dao;

import com.somecompany.card.management.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserRepository {
    private EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    public User getById(long id) {
        return getSession().find(User.class, id);
    }

    private Session getSession() {
        return em.unwrap(Session.class);
    }
}

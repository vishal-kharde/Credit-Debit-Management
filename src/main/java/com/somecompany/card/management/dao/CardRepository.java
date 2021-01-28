package com.somecompany.card.management.dao;

import com.somecompany.card.management.entity.Card;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CardRepository {

    private EntityManager em;

    public CardRepository(EntityManager entityManager) {
        this.em = entityManager;
    }

    public List<Card> getCardsByUserId(long userId) {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Card> criteriaQuery = criteriaBuilder.createQuery(Card.class);
        Root<Card> root = criteriaQuery.from(Card.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("cardHolder"), userId));
        return getSession().createQuery(criteriaQuery).getResultList();
    }

    public Card getById(long id) {
        return getSession().find(Card.class, id);
    }

    public Card update(Card card) {
        return (Card) getSession().merge(card);
    }

    public List<Card> getAll() {
        return getSession().createQuery("from Card").list();
    }

    private Session getSession() {
        return em.unwrap(Session.class);
    }

}

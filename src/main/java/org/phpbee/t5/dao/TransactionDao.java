package org.phpbee.t5.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.phpbee.t5.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by alexk on 11/10/16.
 */
@Repository("transactionDao")
public class TransactionDao {


    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Transaction findById(Long id) {
        //return (Transaction) sessionFactory.getCurrentSession().getNamedQuery("Transaction.findById").uniqueResult();

        sessionFactory.getCurrentSession();

        return new Transaction(5);
    }
}

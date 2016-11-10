package org.phpbee.t5;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.phpbee.t5.dao.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {
    @Bean
    public TransactionDao transactionDao() {
        return new TransactionDao();
    }

//    @Bean
//    public HibernateTemplate hibernateTemplate() {
//        return new HibernateTemplate(sessionFactory());
//    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://docker:3306/t5_test");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        return dataSource;
    }
//
//    @Bean
//    public SessionFactory sessionFactory() {
//        return new LocalSessionFactoryBuilder(getDataSource())
//                .addAnnotatedClasses(Transaction.class)
//                .setProperty("hibernate.show_sql", "true")
//                .setProperty("hibernate.current_session_context_class", "thread")
//                .buildSessionFactory();
//    }
//
//    @Bean
//    public HibernateTransactionManager hibTransMan(){
//        return new HibernateTransactionManager(sessionFactory());
//    }


    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan(new String[] { "org.phpbee.t5" });
        //sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

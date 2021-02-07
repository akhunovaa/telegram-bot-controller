package com.botmasterzzz.controller.dao.impl;

import com.botmasterzzz.controller.dao.MessagesDAO;
import com.botmasterzzz.controller.entity.TelegramMessagesEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Repository
public class TelegramMessagesDAOImpl implements MessagesDAO {

    private final SessionFactory sessionFactory;

    public TelegramMessagesDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void messageAdd(TelegramMessagesEntity telegramMessagesEntity) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(telegramMessagesEntity);
            tx.commit();
        } catch (Exception he) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @SuppressWarnings({"deprecation", "unchecked"})
    @Override
    public List<TelegramMessagesEntity> getMessageList() {
        List<TelegramMessagesEntity> telegramMessagesEntityList;
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(TelegramMessagesEntity.class);
        telegramMessagesEntityList = criteria.list();
        session.close();
        return telegramMessagesEntityList;
    }

    @SuppressWarnings({"deprecation", "unchecked"})
    @Override
    public List<TelegramMessagesEntity> getMessageList(String icriteria) {
        Date minDate = Date.from(Instant.now().truncatedTo(ChronoUnit.DAYS));
        Date maxDate = new Date();

        List<TelegramMessagesEntity> telegramMessagesEntityList;
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(TelegramMessagesEntity.class);
        criteria.add(Restrictions.ilike("replyMarkup", icriteria, MatchMode.ANYWHERE));
        criteria.add(Restrictions.ilike("replyMarkup", "query", MatchMode.ANYWHERE));
        criteria.add(Restrictions.between("audWhenCreate", minDate, maxDate));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        telegramMessagesEntityList = criteria.list();
        session.close();
        return telegramMessagesEntityList;
    }
}

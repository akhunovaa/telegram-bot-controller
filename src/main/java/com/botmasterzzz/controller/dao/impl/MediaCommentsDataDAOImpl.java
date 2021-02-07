package com.botmasterzzz.controller.dao.impl;

import com.botmasterzzz.controller.dao.MediaCommentsDataDAO;
import com.botmasterzzz.controller.entity.MediaCommentsDataEntity;
import com.botmasterzzz.controller.entity.TelegramBotUserEntity;
import com.botmasterzzz.controller.entity.TelegramUserMediaEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MediaCommentsDataDAOImpl implements MediaCommentsDataDAO {

    private final SessionFactory sessionFactory;

    public MediaCommentsDataDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void commentAdd(MediaCommentsDataEntity mediaCommentsDataEntity) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(mediaCommentsDataEntity);
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
    public List<MediaCommentsDataEntity> getCommentsForMedia(Long mediaFileId, int offset, int limit) {
        List<MediaCommentsDataEntity> mediaCommentsDataEntityList;
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(MediaCommentsDataEntity.class);
        criteria.createAlias("telegramUserMediaEntity", "media");
        criteria.add(Restrictions.eq("media.id", mediaFileId));
        criteria.addOrder(Order.desc("audWhenCreate"));
        criteria.setFirstResult(offset);
        criteria.setMaxResults(limit);
        mediaCommentsDataEntityList = criteria.list();
        session.close();
        return mediaCommentsDataEntityList;
    }

    @SuppressWarnings({"deprecation", "unchecked"})
    @Override
    public List<TelegramBotUserEntity> getMediaCommentedUsers(TelegramUserMediaEntity telegramUserMediaEntity) {
        List<TelegramBotUserEntity> telegramBotUserEntityList;
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(MediaCommentsDataEntity.class);
        criteria.add(Restrictions.eq("telegramUserMediaEntity", telegramUserMediaEntity));
        criteria.setProjection(Projections.distinct(Projections.property("telegramBotUserEntity")));
        telegramBotUserEntityList = criteria.list();
        session.close();
        return telegramBotUserEntityList;
    }
}

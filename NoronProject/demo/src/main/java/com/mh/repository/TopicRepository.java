package com.mh.repository;

import com.tej.JooQDemo.jooq.sample.model.Tables;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Topic;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TopicRepository implements ITopicRepository{

    private final DSLContext dslContext;

    public TopicRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public void insertTopic(Topic topic) {
        LocalDate time = LocalDateTime.now().toLocalDate();
        dslContext.insertInto(Tables.TOPIC,Tables.TOPIC.NAME_TOPIC,Tables.TOPIC.CREATE_AT,Tables.TOPIC.UPDATE_AT,Tables.TOPIC.DELETE_AT)
                .values(topic.getNameTopic(),time,time,time)
                .execute();
    }

}

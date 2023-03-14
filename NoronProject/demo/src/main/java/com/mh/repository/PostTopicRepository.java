package com.mh.repository;

import com.mh.dto.PostTopicDTO;
import com.tej.JooQDemo.jooq.sample.model.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PostTopicRepository implements IPostTopicRepository {

    private final DSLContext dslContext;

    public PostTopicRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public void insertPostTopic(int postId, int topicId) {
        dslContext.insertInto(Tables.POST_TOPIC, Tables.POST_TOPIC.POST_ID, Tables.POST_TOPIC.TOPIC_ID)
                .values(postId, topicId)
                .execute();
    }

    @Override
    public List<PostTopicDTO> getListTopicByPostId(List<Integer> postId) {
        return dslContext.select(Tables.POST_TOPIC.POST_ID,
                        Tables.POST_TOPIC.TOPIC_ID,
                        Tables.TOPIC.NAME_TOPIC)
                .from(Tables.POST_TOPIC)
                .innerJoin(Tables.TOPIC)
                .on(Tables.POST_TOPIC.TOPIC_ID.eq(Tables.TOPIC.ID))
                .where(Tables.POST_TOPIC.POST_ID.in(postId))
                .fetchInto(PostTopicDTO.class);
    }
}

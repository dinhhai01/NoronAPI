package com.mh.repository;
;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Topic;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITopicRepository {

    void insertTopic(Topic topic);


}

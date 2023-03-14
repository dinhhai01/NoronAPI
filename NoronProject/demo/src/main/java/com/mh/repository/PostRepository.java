package com.mh.repository;


import com.mh.template.RxTemplate;
import com.mh.utils.DateTime;
import com.tej.JooQDemo.jooq.sample.model.Tables;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Post;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Topic;
import com.tej.JooQDemo.jooq.sample.model.tables.records.PostTopicRecord;
import io.reactivex.Single;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static com.mh.template.RxTemplate.rxSchedulerIo;

@Repository
public class PostRepository implements IPostRepository {

    private final DSLContext dslContext;


    public PostRepository(DSLContext dslContext) {
        this.dslContext = dslContext;


    }

    @Override
    public Map<Post, List<Topic>> insertPost(Post post, List<Topic> topics) {
        List<Map<Post, List<Topic>>> list = new ArrayList<>();
        dslContext.transaction(out -> {
            DSLContext context = DSL.using(out);
            List<Topic> topicPoJo = new ArrayList<>();
            Post post1 = Objects.requireNonNull(context.insertInto(Tables.POST,
                            Tables.POST.TITLE,
                            Tables.POST.CONTENT,
                            Tables.POST.TYPE_POST,
                            Tables.POST.VIEW_POST,
                            Tables.POST.CREATED_AT,
                            Tables.POST.UPDATE_AT,
                            Tables.POST.DELETE_AT,
                            Tables.POST.USER_ID)
                    .values(post.getTitle(),
                            post.getContent(),
                            post.getTypePost(),
                            post.getViewPost(),
                            DateTime.time,
                            DateTime.time,
                            DateTime.time,
                            post.getUserId())
                    .returning(Tables.POST.ID, Tables.POST.TITLE, Tables.POST.CONTENT, Tables.POST.TYPE_POST, Tables.POST.VIEW_POST)
                    .fetchOne()).into(Post.class);

            List<InsertSetMoreStep<PostTopicRecord>> insertSetMoreSteps = topics.stream()
                    .map(topic -> context
                            .insertInto(Tables.POST_TOPIC)
                            .set(Tables.POST_TOPIC.POST_ID, post1.getId())
                            .set(Tables.POST_TOPIC.TOPIC_ID, topic.getId()))
                    .collect(Collectors.toList());
            context.batch(insertSetMoreSteps).execute();

            topicPoJo = insertSetMoreSteps.stream()
                    .map(insertSetMoreStep -> {
                        return topics.stream()
                                .filter(topic -> topic.getId() == insertSetMoreStep.getBindValues().get(1))
                                .limit(1)
                                .collect(Collectors.toList()).get(0);
                    })
                    .collect(Collectors.toList());
            Map<Post, List<Topic>> map = new HashMap<>();
            map.put(post1, topicPoJo);
            list.add(map);
        });

        return list.get(0);
    }

    @Override
    public List<Post> getAllPosts(Pageable pageable) {

        List<Post> posts = dslContext.select()
                .from(Tables.POST)
                .limit(pageable.getPageSize()).offset(pageable.getOffset())
                .fetchInto(Post.class);

        return posts;
    }

    @Override
    public Single<List<Post>> getPosts(Pageable pageable) {
        return rxSchedulerIo(() -> dslContext.select()
                .from(Tables.POST)
                .limit(pageable.getPageSize()).offset(pageable.getOffset())
                .fetchInto(Post.class));
    }

    @Override
    public Post updateTypeById(int id, String type) {
        List<Post> l = new ArrayList<Post>();
        dslContext.transaction(out -> {
            DSLContext context = DSL.using(out);
            context.update(Tables.POST)
                    .set(Tables.POST.TYPE_POST, type)
                    .where(Tables.POST.ID.eq(id))
                    .execute();

            Post post = context.selectFrom(Tables.POST)
                    .where(Tables.POST.ID.eq(id))
                    .fetchOneInto(Post.class);
            l.add(post);
        });
        return l.get(0);
    }

    @Override
    public int getViewById(int id) {
        int num = dslContext.select(Tables.POST.VIEW_POST)
                .from(Tables.POST)
                .where(Tables.POST.ID.eq(id))
                .execute();
        return num;
    }

    @Override
    public Post updateContentById(int id, String content) {
        List<Post> l = new ArrayList<Post>();
        dslContext.transaction(out -> {
            DSLContext context = DSL.using(out);
            context.update(Tables.POST)
                    .set(Tables.POST.CONTENT, content)
                    .where(Tables.POST.ID.eq(id))
                    .execute();

            Post post = context.selectFrom(Tables.POST)
                    .where(Tables.POST.ID.eq(id))
                    .fetchOneInto(Post.class);
            l.add(post);
        });
        return l.get(0);
    }

    @Override
    public Post updateTitleById(int id, String title) {
        List<Post> l = new ArrayList<>();
        dslContext.transaction(out -> {
            DSLContext context = DSL.using(out);
            context.update(Tables.POST)
                    .set(Tables.POST.TITLE, title)
                    .where(Tables.POST.ID.eq(id))
                    .execute();

            Post post = context.selectFrom(Tables.POST)
                    .where(Tables.POST.ID.eq((id)))
                    .fetchOneInto(Post.class);
            l.add(post);
        });
        return l.get(0);
    }

    @Override
    public Post updateViewById(int id) {
        List<Post> l = new ArrayList<Post>();
        dslContext.transaction(out -> {
            DSLContext context = DSL.using(out);
            context.update(Tables.POST)
                    .set(Tables.POST.VIEW_POST, Tables.POST.VIEW_POST.add(1))
                    .where(Tables.POST.ID.eq(id))
                    .execute();

            Post post = context.selectFrom(Tables.POST)
                    .where(Tables.POST.ID.eq(id))
                    .fetchOneInto(Post.class);
            l.add(post);
        });
        return l.get(0);
    }


}

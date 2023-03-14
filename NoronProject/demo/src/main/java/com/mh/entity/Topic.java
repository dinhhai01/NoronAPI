package com.mh.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "Topic")
public class Topic implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_topic")
    private String nameTopic;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date updateAt;

    @Column(name = "delete_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date deleteAt;

    @OneToMany(mappedBy = "topic")
    private List<PostTopic> postTopics;
}

package com.mh.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Post_Topic", uniqueConstraints = @UniqueConstraint(columnNames = {"post_id","topic_id"}))
public class PostTopic implements Serializable {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
}

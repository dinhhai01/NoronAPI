package com.mh.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Vote")
public class Vote implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    @Column(name = "user_id")
    private int userId;

    @OneToMany(mappedBy = "vote")
    private List<VoteComment> voteComments;

    @OneToMany(mappedBy = "vote")
    private List<VotePost> votePosts;
}

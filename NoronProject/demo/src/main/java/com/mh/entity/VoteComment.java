package com.mh.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Vote_Comment", uniqueConstraints = @UniqueConstraint(columnNames = {"comment_id","vote_id"}))
public class VoteComment implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "vote_id")
    private Vote vote;
}

package com.petlife.forum.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "forum")
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增鍵
    @Column(name = "forum_id", updatable = false ) // updatable = false --> update SQL 不包括此欄位
    private Integer forumId;
    
//    @OneToMany(mappedBy = forum,cascade = CascadeType.ALL)    
//    private Set<Article> articles;
    
    @Column(name = "sort_name")
    private String sortName;

    public Forum() {
        super();
    }

    public Forum(Integer forumId, String sortName) {
        super();
        this.forumId = forumId;
        this.sortName = sortName;
    }

    // Getters and Setters
    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }
}

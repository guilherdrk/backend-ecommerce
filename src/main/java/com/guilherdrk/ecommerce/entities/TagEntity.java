package com.guilherdrk.ecommerce.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_tags")
public class TagEntity {

    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(name = "name_tag", unique = true)
    private String nameTag;

    public TagEntity() {}

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getNameTag() {
        return nameTag;
    }

    public void setNameTag(String nameTag) {
        this.nameTag = nameTag;
    }
}

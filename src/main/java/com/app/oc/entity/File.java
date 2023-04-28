package com.app.oc.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class File {

    @Id
    @Column(nullable = false, name = "storefile")
    private String storeFile;

    private Integer datePath;

    @ManyToOne
    @JoinColumn(name ="item_seq")
    private Item item;

    @Column(name ="filename")
    private String fileName;
}

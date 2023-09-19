package com.app.oc.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 상품 사진 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {
    @Id
    private String storefile;
    private String datePath;
    private String filename;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;


    public File(String storefile, String datePath,String filename) {
        this.storefile = storefile;
        this.datePath = datePath;
        this.filename = filename;
    }

    public void changeItem(Item item) {
        this.item = item;
    }

    @Builder
    public File(String storefile, String datePath, Item item,String filename) {
        this.storefile = storefile;
        this.datePath = datePath;
        this.filename = filename;
        this.item = item;
    }
}

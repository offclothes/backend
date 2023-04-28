package com.app.oc.entity;/*
package com.app.oc.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * 의류 카테고리 우선 선택지를 정하는 방향으로 하는건데.
 *//*


@Entity
@Getter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_num")
    private String categoryNum;

    private int tier;
    private String cateName;

    @ManyToOne
    @JoinColumn(name = "cate_parent")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
*/

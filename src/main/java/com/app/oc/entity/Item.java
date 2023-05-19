package com.app.oc.entity;

import com.app.oc.dto.shoppingmal.ItemFileRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static com.app.oc.entity.SellState.*;

@Entity
@Table(name = "item")
@ToString(of = {"itemId","itemTitle","price","Out","content"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="item_seq" )
    private Long itemId;
    private String itemTitle;
    private Integer price;

    //판매상태
    @Enumerated(EnumType.STRING)
    private SellState sellState;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopId")
    private ShoppingMal shoppingMal;


    @OneToMany(mappedBy = "storefile", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<File> files = new ArrayList<>();

    private Integer category;


    //연관 관계 매서드
    public void setFile(File file) {
        this.files.add(file);
        file.changeItem(this);
    }

    // 양방향 매핑
    public void setShoppingMal(ShoppingMal shoppingMal) {
        this.shoppingMal = shoppingMal;
        shoppingMal.getItems().add(this);
    }


    
    //셍성 메서드
    public static Item createItem(ItemFileRequestDto itemFileRequestDto, ShoppingMal shoppingMal) {

        Item item = new Item();
        item.itemTitle =itemFileRequestDto.getItemTitle();
        item.sellState = 판매중;
        item.price = itemFileRequestDto.getPrice();
        item.category= itemFileRequestDto.getCategory();
        item.content = itemFileRequestDto.getContent();
        item.setShoppingMal(shoppingMal);
        return item;
    }

    /**
     * ITem 수정
     */
    public Item Itemupdate(ItemFileRequestDto itemFileRequestDto) {
        this.content = itemFileRequestDto.getContent();
        this.itemTitle = itemFileRequestDto.getItemTitle();
        this.price = itemFileRequestDto.getPrice();
        this.sellState = itemFileRequestDto.getSellState();
        return this;
    }




}


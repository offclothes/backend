package com.app.oc.service;

import com.app.oc.dto.shoppingmal.DetailItemDto;
import com.app.oc.dto.shoppingmal.MainItemDto;
import com.app.oc.dto.shoppingmal.ItemFileRequestDto;
import com.app.oc.dto.fileDto.UploadFile;
import com.app.oc.entity.*;
import com.app.oc.repository.*;
import com.app.oc.util.FileStore;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ItemService {
    private final FileStore FileStore;
    private final FileService fileService;


    private final FileRepository fileRepository;
    private final ShopRepository shopRepositroy;
    private final ItemRepository ItemRepository;

    @Autowired
    HttpSession session;



    /**
     * Item 등록 / 수정
     */
    public String saveItem(ItemFileRequestDto itemFileRequestDto) throws IOException {

        System.out.println("itemFileRequestDto = " + itemFileRequestDto);
        log.info("cate  = {}", itemFileRequestDto.getCategory());

        //shoppingMal 조회 - 매핑 - 수정
        ShoppingMal shoppingMal = shopRepositroy.findById(itemFileRequestDto.getShopId()).orElseThrow();

        //등록인지 수정인지 구분
        String name = "";
        
        //shop id
        //Item 등록
        Item item = null;
        if (itemFileRequestDto.getItemId() == null) {
            log.info("=========등록 ==========");

            item = Item.createItem(itemFileRequestDto, shoppingMal);
            log.info("item {} = ", item);

            name = "등록";



        } else { //update
            log.info("=========수정 ==========");
            name = "수정";


            //item_id
            item = ItemRepository.findById(itemFileRequestDto.getItemId()).
                    orElseThrow(() -> new IllegalArgumentException("상품이 없습니다."));

            //item update
           item.Itemupdate(itemFileRequestDto);


            //기존 파일 삭제
            List<File> oldFiles = fileService.fileFindPerItem(item.getItemId());
            if (oldFiles != null) {
                for (File file : oldFiles) { //파일 삭제
                    fileService.fileOneDelete(new UploadFile(file.getStorefile(), file.getDatePath(), file.getFilename()));
                }
            }


        }


        //파일 insert
        UploadFile thumb = FileStore.storeFile(itemFileRequestDto.getThumb(), true);
        List<UploadFile> files = FileStore.storeFiles(itemFileRequestDto.getImageFiles());

        files.add(thumb);

        for (UploadFile file : files) {
            log.info("file = ",file);
            File fileOne = file.toEntity(file);

            //File 연관관계 매핑
            item.setFile(fileOne);

            ItemRepository.save(item);
        }

        return name;
    }

    /**
     * Item 찾기
    
     *
     * @param id
     * @return
     */
    public Item findByItem(Long id) {
        return ItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("상품이 없습니다."));
    }


    /**
     * 썸네일만 추출(페이징)
     *
     * @param id
     * @return
     */
    //여러개(썸네일만)
    public Page<MainItemDto>  findByShopITem(Long id, Pageable  pageable) {

        //item 페이징 List
        List<Item> getItems = ItemRepository.getcontent(id, pageable);


        //id들로만 한번에
        List<Long> collectItemIds = getItems.stream().map(item -> item.getItemId()).collect(Collectors.toList());

        List<File> fileIn = fileRepository.findFileIn(collectItemIds);
        List<UploadFile> uploadFiles = fileIn.stream().map(file -> new UploadFile(file)).collect(Collectors.toList());

        Map<Long, UploadFile> fileMap = new HashMap<>();
        for (UploadFile uploadFile : uploadFiles) {
            if (uploadFile.getStoreFileName().startsWith("s_")) { //썸네일만
                fileMap.put(uploadFile.getItem_seq(), uploadFile);
            }
        }

        log.info("map : {}", fileMap);

        List<MainItemDto> items = getMainItemDtos(getItems, fileMap);

        //페이징으로 변환
        Page<MainItemDto> mainItemDtos = ItemRepository.shopMainItems(items, id, pageable);

        log.info("items :{} ", items);
        return mainItemDtos;

    }

    private static List<MainItemDto> getMainItemDtos(List<Item> getItems, Map<Long, UploadFile> fileMap) {
        return getItems.stream().map(item -> {
            MainItemDto mainItemDto = new MainItemDto(item);
            mainItemDto.setUploadFile(fileMap.get(item.getItemId()));
            return mainItemDto;
        }).collect(Collectors.toList());
    }

    /**
     * Item 상세페이지
     *
     * Myshop인지 확인
     * :세션으로 가지고 온 Id(자기ID)와 shop memberID와 비교
     */
    public DetailItemDto findDetailOne(Long id) {
        //Item
        Item itemOne = ItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("아이템이 없습니다."));
        log.info("itemOne : {}  ",itemOne.toString());
        log.info("itemOne.getSellState() = {}",itemOne.getSellState());


        Long shopId = itemOne.getShoppingMal().getShopId();
        log.info("shopId : {}", shopId);

        ShoppingMal findShop = shopRepositroy.findById(shopId).orElseThrow(() -> new IllegalArgumentException("shop이 없습니다."));

        DetailItemDto detailItemDto = new DetailItemDto(itemOne,findShop.getShopName());


        String memberId = findShop.getMember().getMemberId();
        log.info("memberId : {}",memberId);

        //세션에서 로그인한 Id가지고 오기
        String sessionId = (String) session.getAttribute("id");
        log.info("sessionId", sessionId);

        //shop의 MemberID와 세션에서 가지고온 Id가 일치할 시 => true
        if (memberId.equals(sessionId)) {
            detailItemDto.setMyshop(true);
        } else {
            detailItemDto.setMyshop(false);
        }




        List<File> files = fileService.fileFindPerItem(id);

        files.forEach(file -> itemOne.setFile(file)); //연관관계 매핑(file)연관관계

        List list = new ArrayList<>();
        files.stream().forEach(file -> {
            UploadFile uploadFile = new UploadFile(file);

            if (file.getStorefile().startsWith("s_")) {
                detailItemDto.setThumb(uploadFile);
                    }
            else { // 그 외 파일들은 null일 수 있어서
                if (file != null) {
                    list.add(uploadFile);
                }
            }
        });


        //file이 있을 경우
        if (!list.isEmpty()) {
            detailItemDto.setImageFiles(list);
        }

        log.info("detailItemDto : {} ",detailItemDto);

        return detailItemDto;

    }


    /**
     * Item삭제
     * @param id item
     */
    public void DeleteOneItem(Long id) {
        Item item = findByItem(id);

        //file 삭제
        List<File> files = fileService.fileFindPerItem(id);

        files.forEach(file -> item.setFile(file)); //연관관계 매핑(file)연관관계

        //item 삭제
        ItemRepository.deleteById(id);
    }


}




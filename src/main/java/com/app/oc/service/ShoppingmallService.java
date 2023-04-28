package com.app.oc.service;

import com.app.oc.dto.SellerRequestDto;
import com.app.oc.dto.SellerResponseDto;
import com.app.oc.dto.ShopIntroductionRequestDto;
import com.app.oc.dto.ShopIntroductionResponseDto;
import com.app.oc.entity.Approval;
import com.app.oc.entity.Member;
import com.app.oc.entity.Role;
import com.app.oc.entity.Shoppingmall;

import com.app.oc.repository.ShoppingmallRepository;
import com.app.oc.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShoppingmallService {

    private final ShoppingmallRepository shoppingmallRepository;

    private final EmailUtil emailUtil;

    // 프론트에서 입점 신청정보랑 memberId를 묶어서 보내줄 수 있나?
    // 그럼 memberId를 프론트가 가지고 있어야 할거 같은데.

    /**
     * 입점 신청 기능 --> 이메일로 보내는 부분 구현(EmailUtilImpl.java 참조)
     * @param requestDto
     * @return
     */
    @Transactional
    public SellerResponseDto sellerSignup(SellerRequestDto requestDto) {

        Map<String, Object> result = emailUtil.sendEmail(requestDto.getEmail());

        if(result.get("resultCode").equals(200))  {
            Member findMember = shoppingmallRepository.findMemberByMemberId(requestDto.getMemberId());
            findMember.setRole(Role.SELLER);
//        requestDto.setMemberId(findMember.getMemberId());
            shoppingmallRepository.saveMember(findMember);
            System.out.println("findMember.getRole() = " + findMember.getRole());

            Shoppingmall shoppingmall = requestDto.toShoppingmall();
            shoppingmall.setMember(findMember);
            System.out.println("shoppingmall.getMember() = " + shoppingmall.getMember());
            shoppingmall.setApproval(Approval.Y);
            shoppingmallRepository.save(shoppingmall);


            return SellerResponseDto.of(findMember, shoppingmall);
        } else {
            throw new RuntimeException("입점 신청 접수가 안됐습니다. 한 번 더 입력해주세요");
        }
    }

    //매장 정보 작성 하는 메서드, 불러오는 메서드.
    //매장 정보 작성 메서드
    @Transactional
    public ShopIntroductionResponseDto saveIntroduction(Long shopId, MultipartFile shopLogo,
                                                           ShopIntroductionRequestDto requestDto) {

        Shoppingmall findShop = shoppingmallRepository.findByShopId(shopId);

        findShop.setShopLogo(encodeImageToBase64(shopLogo));
        findShop.setStyle(requestDto.getStyle());
        findShop.setContent(requestDto.getContent());

        shoppingmallRepository.save(findShop);

        Shoppingmall newFindShop = shoppingmallRepository.findByShopId(shopId);

        return ShopIntroductionResponseDto.introductionResponseDto(newFindShop);
    }
    @Transactional
    public ShopIntroductionResponseDto getIntroduction(Long shopId) {

        return ShopIntroductionResponseDto.introductionResponseDto(shoppingmallRepository.findByShopId(shopId));
    }

    @SneakyThrows
    private byte[] encodeImageToBase64(MultipartFile image) {
        byte[] encodedImage = new byte[0];

        if (image != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            encodedImage = encoder.encode(image.getBytes());
        }
        return encodedImage;
    }
}

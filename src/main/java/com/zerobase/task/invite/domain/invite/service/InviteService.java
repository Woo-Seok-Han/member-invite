package com.zerobase.task.invite.domain.invite.service;

import ch.qos.logback.core.spi.ErrorCodes;
import com.zerobase.task.invite.api.common.model.constant.ErrorCode;
import com.zerobase.task.invite.api.invite.dto.InviteRequest;
import com.zerobase.task.invite.domain.common.util.RandomCode;
import com.zerobase.task.invite.domain.invite.constant.InviteStatus;
import com.zerobase.task.invite.domain.invite.persistence.InviteRepository;
import com.zerobase.task.invite.domain.invite.persistence.entity.Invite;
import com.zerobase.task.invite.domain.member.constant.MemberRank;
import com.zerobase.task.invite.domain.member.persistence.MemberRepository;
import com.zerobase.task.invite.domain.member.persistence.entity.Member;
import com.zerobase.task.invite.global.error.exception.BusinessException;
import com.zerobase.task.invite.infra.mail.MailService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InviteService {

    private final MemberRepository memberRepository;
    private final InviteRepository inviteRepository;
    private final MailService mailService;

    public Invite getInvite(final Long inviteId) {
        return inviteRepository.findById(inviteId).orElse(null);
    }

    /**
     * 초대한 사람의 정보를 조회한다 유효성 검사 (그룹 매니저만이 회원을 초대한다) 회원 객체 생성 (InviteRequest -> Member Entity 로 변환) 임시
     * 상태의 회원을 저장한다 초대를 생성한다 (초대자, 참여자, 초대 상태, 초대 링크,)
     *
     * @param inviteRequest
     */
//    @Transactional
//    public Invite createInvite(final InviteRequest inviteRequest) {
//        verifyInviteRequest(inviteRequest);
//
//        // 초대 생성시 초기에는 임시 회원으로 생성
//        Member tempMember = new Member(
//            inviteRequest.getName(),
//            inviteRequest.getAge(),
//            inviteRequest.getPhoneNumber(),
//            inviteRequest.getEmail()
//        );
//
//        Member savedMember = memberRepository.save(tempMember);
//
//        Long inviterId = inviteRequest.getInviterMemberId();
//        Long participantId = savedMember.getMemberId();
//        String subject = "초대장 입니다.";
//        String code = RandomCode.getRandomCode();
//
//        // 초대 메일 발송
//        mailService.sendMail(
//            inviteRequest.getEmail(),
//            subject,
//            getInviteMailBody(
//                inviteRequest.getEmail(),
//                inviteRequest.getName(),
//                code
//            )
//        );
//
//        return inviteRepository.save(new Invite(inviterId, participantId, code));
//    }

    public String getInviteMailBody(String email, String name, String code) {
        StringBuilder builder = new StringBuilder();
        return builder.append("<h2>Hello</h2> ")
            .append(name)
            .append("<h1>Please click link for your invitation.</h1>")
            .append("http://localhost:8080/invite/verify?email=")
            .append(email)
            .append("&code=")
            .append(code)
            .toString();
    }

    private void verifyInviteRequest(final InviteRequest inviteRequest) {
        // 그룹 매니저만이 회원을 초대 할 수 있다
        Member member = memberRepository.findById(inviteRequest.getInviterMemberId())
            .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

        if (member.getMemberRank() != MemberRank.MANAGER) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED_INVITER_TYPE);
        }
    }

    /**
     * 회원의 상태를 update 한다 (임시 회원 활성화) 초대의 상태를 update 한다 (초대 만료)
     */
    @Transactional
    public Invite acceptInvite(final String email, final String code) {
        Member invitedMember = memberRepository.findByEmail(email)
            .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

        Invite invite = inviteRepository.findByParticipantMemberId(invitedMember.getMemberId())
            .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_INVITE));

        verifyInvite(invite, code);

        // 회원 상태 정규 회원으로 변경
        invitedMember.updateRegular();
        // 초대 수락에 따른 초대 만료 처리
        invite.expire();

        return invite;
    }

    public void verifyInvite(Invite invite, final String code) {
        // 승인 가능 상태가 아니면 예외 발생
        if (invite.getInviteStatus() != InviteStatus.VALID) {
            throw new BusinessException(ErrorCode.ALREADY_VERIFIED);
        } else if (invite.getVerifyExpiredAt().isBefore(LocalDateTime.now())) {
            throw new BusinessException(ErrorCode.EXPIRED_INVITE);
        } else if (!invite.getVerificationCode().equals(code)) {
            throw new BusinessException(ErrorCode.INVALID_INVITE);
        }
    }

    /**
     * 추가 예정 : 동적으로 초대 수락 url 가져오기
     *
     * @param participantId
     */
    public void getInviteUrl(String participantId) {
        //        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        //
        //        // Url 리스트를 Map 형식으로 가져오기
        //        Map<RequestMappingInfo, HandlerMethod> handlerMap =
        // handlerMapping.getHandlerMethods();
        //        Iterator<RequestMappingInfo> it = handlerMap.keySet().iterator();
        //
        //        List<Map<String, Object>> mappingInfoList = new ArrayList<Map<String, Object>>();
        //        RequestMappingInfo requestMappingInfo = null;
        //        Set<String> patterns;
        //        Object[] sArr;
        //        String url, beanName;
        //
        //        while(it.hasNext()) {
        //            requestMappingInfo = it.next();
        //            patterns = requestMappingInfo.getPatternsCondition().getPatterns();
        //            RequestMethod requestMethod = requestMappingInfo.getMethodsCondition()
        //                    .getMethods()
        //                    .stream()
        //                    .filter(requestMethod -> HttpMethod.PUT.matches(requestMethod.name()))
        //                    .findAny().get();
        //
        //            if(!patterns.isEmpty()) {
        //                sArr = patterns.toArray();
        //                if(sArr.length == 1) {
        //                    // annotaion에 지정된 URL 값
        //                    url      = (String) sArr[0];
        //                    // URL이 지정되어있는 컨트롤러 이름
        //                    beanName = (String) handlerMap.get(requestMappingInfo).getBean();
        //
        //                    Map<String,Object> map = new HashMap<String,Object>();
        //                    map.put("ID", beanName.replace("Controller", ""));
        //                    map.put("URL", url);
        //                    mappingInfoList.add(map);
        //                }
        //            }
        //        }
        //
        //        return mappingInfoList;
    }
}

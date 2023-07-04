package com.zerobase.task.invite.domain.invite.service;

import com.zerobase.task.invite.api.invite.dto.InviteRequest;
import com.zerobase.task.invite.domain.member.constant.MemberRank;
import com.zerobase.task.invite.global.error.exception.BusinessException;
import com.zerobase.task.invite.api.common.model.constant.ErrorCode;
import com.zerobase.task.invite.domain.invite.persistence.InviteRepository;
import com.zerobase.task.invite.domain.member.persistence.MemberRepository;
import com.zerobase.task.invite.domain.invite.persistence.entity.Invite;
import com.zerobase.task.invite.domain.member.persistence.entity.Member;
import com.zerobase.task.invite.domain.invite.constant.InviteStatus;
import com.zerobase.task.invite.domain.member.constant.MemberStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class InviteService {

    private final MemberRepository memberRepository;
    private final InviteRepository inviteRepository;

    public Invite getInvite(Long inviteId) {
        return inviteRepository.findById(inviteId).orElse(null);
    }

    /**
     * 초대한 사람의 정보를 조회한다 유효성 검사 (그룹 매니저만이 회원을 초대한다) 회원 객체 생성 (InviteRequest -> Member Entity 로 변환) 임시
     * 상태의 회원을 저장한다 초대를 생성한다 (초대자, 참여자, 초대 상태, 초대 링크,)
     *
     * @param inviteRequest
     */
    @Transactional
    public Invite createInvite(InviteRequest inviteRequest) {
        // 그룹 매니저만이 회원을 초대 할 수 있다
        Member member = memberRepository.findById(inviteRequest.getInviterMemberId())
            .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

        if (member.getMemberRank() != MemberRank.MANAGER) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED_INVITER_TYPE);
        }

        // 초대 생성시 초기에는 임시 회원으로 생성
        Member tempMember = new Member(
            inviteRequest.getName(),
            inviteRequest.getAge(),
            inviteRequest.getPhoneNumber(),
            inviteRequest.getEmail()
        );

        Member savedMember = memberRepository.save(tempMember);

        // 초대 생성
        // 매핑되어 있는 초대 링크 생성( invite url ) - 추가 개발 예정
        Long inviterId = inviteRequest.getInviterMemberId();
        Long participantId = savedMember.getMemberId();
        String inviteUrl = "http://localhost:8080/invite/" + participantId;

        Invite invite = new Invite(
            inviterId,
            participantId,
            inviteUrl
        );

        return inviteRepository.save(invite);
    }

    /**
     * 회원의 상태를 update 한다 (임시 회원 활성화) 초대의 상태를 update 한다 (초대 만료)
     */
    @Transactional
    public Invite acceptInvite(Long invite_id) {
        // 회원 조회
        Invite invite = inviteRepository.findById(invite_id)
            .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

        // 승인 가능 상태가 아니면 예외 발생
        if (invite.getInviteStatus() == InviteStatus.EXPIRED) {
            throw new BusinessException(ErrorCode.INVALID_INVITE);
        }

        Member member = memberRepository.findById(invite.getParticipantMemberId())
            .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

        // 회원 상태 정규 회원으로 변경
        member.updateRegular();
        // 초대 수락에 따른 초대 만료 처리
        invite.expire();

        return invite;
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

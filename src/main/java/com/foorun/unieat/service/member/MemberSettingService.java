package com.foorun.unieat.service.member;

import com.foorun.unieat.annotation.Validation;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.dto.setting.MemberSettingNicknamePayload;
import com.foorun.unieat.domain.member.dto.setting.MemberSettingNotificationPayload;
import com.foorun.unieat.domain.member.dto.setting.MemberSettingPasswordPayload;
import com.foorun.unieat.domain.member.dto.setting.MemberSettingProfilePayload;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberSettingService {
    private final MemberRepository memberRepository;

    @Transactional
    public void nickname(MemberUserDetails memberUserDetails, MemberSettingNicknamePayload memberSettingNicknamePayload) {
        MemberJpo memberJpo = ensureMember(memberUserDetails);
        memberJpo.changeNickname(memberSettingNicknamePayload.getNickname());
    }

    @Transactional
    public void profile(MemberUserDetails memberUserDetails, MemberSettingProfilePayload memberSettingProfilePayload) {
        MemberJpo memberJpo = ensureMember(memberUserDetails);
        memberJpo.changeProfile(memberSettingProfilePayload.getFileId());
    }

    @Transactional
    public void notification(MemberUserDetails memberUserDetails, MemberSettingNotificationPayload memberSettingNotificationPayload) {
        MemberJpo memberJpo = ensureMember(memberUserDetails);
        memberJpo.setAgreeNotification(memberSettingNotificationPayload.isEnable());
    }

    @Validation
    @Transactional
    public void password(MemberUserDetails memberUserDetails, MemberSettingPasswordPayload memberSettingPasswordPayload) {
        MemberJpo memberJpo = ensureMember(memberUserDetails);
        memberJpo.changePassword(memberSettingPasswordPayload.getPassword());
    }

    @Transactional
    public void withdraw(MemberUserDetails memberUserDetails) {
        MemberJpo memberJpo = ensureMember(memberUserDetails);
        memberJpo.withdraw();
    }

    public MemberJpo ensureMember(MemberUserDetails memberUserDetails) {
        return memberRepository.findById(memberUserDetails.getId())
                .orElseThrow(UniEatNotFoundException::new);
    }
}

package wendy.study.jpashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wendy.study.jpashop.model.Member;
import wendy.study.jpashop.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    //회원등록
    @Transactional
    public Long join(Member member) {

        //중복 회원 검증
        validateMember(member);

        Member save = memberRepository.save(member);
        return save.getId();
    }

    //회원 한명 조회
    public Member findMember(Long memberId) {
        return memberRepository
                .findById(memberId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
    }

    //회원 목록 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //중복회원 검증
    private void validateMember(Member member) {
        memberRepository
                .findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 가입 된 회원입니다.");
                });
    }
}

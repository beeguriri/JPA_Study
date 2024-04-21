package wendy.study.jpashop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wendy.study.jpashop.model.Member;
import wendy.study.jpashop.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public Member findMember(@PathVariable("memberId") Long memberId) {
        return memberService.findMember(memberId);
    }

    @GetMapping("/all")
    public List<Member> findMembers(){
        return memberService.findMembers();
    }

    @PostMapping("/join")
    public Long join(@RequestBody Member member){
        return memberService.join(member);
    }
}
package wendy.study.jpashop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wendy.study.jpashop.model.Member;
import wendy.study.jpashop.params.MemberJoinParam;
import wendy.study.jpashop.params.UpdateMemberParams;
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

    @GetMapping("/")
    public Member findMemberByName(@RequestParam("name") String name) {
        return memberService.findMemberByName(name);
    }

    @GetMapping("/all")
    public List<Member> findMembers(){
        return memberService.findMembers();
    }

    @PostMapping("/join")
    public Long join(@RequestBody MemberJoinParam params){
        return memberService.join(params.toEntity());
    }

    @PutMapping("/{memberId}/edit")
    public void edit(@PathVariable("memberId") Long memberId,
                     @RequestBody UpdateMemberParams params) {
        memberService.updateMember(memberId, params);
    }
}

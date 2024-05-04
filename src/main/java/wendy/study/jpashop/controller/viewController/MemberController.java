package wendy.study.jpashop.controller.viewController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import wendy.study.jpashop.dto.MemberFormDto;
import wendy.study.jpashop.model.Member;
import wendy.study.jpashop.service.MemberService;

import java.util.List;

@Controller
@RequestMapping("v2/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("")
    public String memberList(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/list";
    }

    @GetMapping("/new")
    public String addMember(Model model) {
        model.addAttribute("memberForm", new MemberFormDto());
        return "members/memberForm";
    }

    @PostMapping("/new")
    public String addMember(@Valid @ModelAttribute("memberForm") MemberFormDto form, BindingResult result) {
        if (result.hasErrors()) {
            log.error("error:::{}", result);
            return "members/memberForm";
        }

        log.info("form::: {}", form);
        try {
            Long joinId = memberService.join(form.toEntity());
            log.info("회원 가입 완료::: {}", joinId);
        } catch (Exception e) {
            //글로벌 에러 만들어 줌!!
            result.reject("duplicated name", "동일한 이름의 회원이 존재합니다.");
            log.error("error:::{}", e.getMessage());
            return "members/memberForm";
        }

        return "redirect:/";
    }

    @GetMapping("/update/{memberId}")
    public String updateMember(@PathVariable Long memberId, Model model) {
        Member member = memberService.findMember(memberId);
        model.addAttribute("memberForm", MemberFormDto.toMemberFormDto(member));
        return "members/memberForm";
    }

    @PostMapping("/update/{memberId}")
    public String updateMember(@PathVariable Long memberId, @Valid @ModelAttribute("memberForm") MemberFormDto form, BindingResult result) {
        if (result.hasErrors()) {
            log.error("error:::{}", result);
            return "members/memberForm";
        }

        log.info("form data::: {}", form);
        try {
            memberService.updateMember(memberId, form.toEntity());
            log.info("정보 수정 완료::: {}", memberId);
        } catch (Exception e) {
            //글로벌 에러 만들어 줌!!
            result.reject("duplicated name", "동일한 이름의 회원이 존재합니다.");
            log.error("error:::{}", e.getMessage());
            return "members/memberForm";
        }

        return "redirect:/";
    }

    //js 알람창 처리를 위하여 responseEntity로 return
    @GetMapping("/delete/{memberId}")
    public @ResponseBody ResponseEntity<String> deleteMember(@PathVariable Long memberId) {
        try {
            memberService.deleteMember(memberId);
        } catch (Exception e) {
            log.error("error:::{}", e.getMessage());
            //ajax로 error 처리
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package wendy.study.jpashop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import wendy.study.jpashop.model.Member;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional //test 실행 후 롤백
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    @Rollback(false) //test 실행 후 롤백하지 않음
    void join() {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberService.findMember(savedId));
    }

    @Test
    void duplicateMember() {
        //given
        //이미 kim이라는 멤버가 가입되어 있음
        Member member = new Member();
        member.setName("kim");

        //when
        //then
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member);
        });
    }
}
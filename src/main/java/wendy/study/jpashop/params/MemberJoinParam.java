package wendy.study.jpashop.params;

import lombok.Data;
import wendy.study.jpashop.model.Address;
import wendy.study.jpashop.model.Member;
import wendy.study.jpashop.model.type.RoleType;

@Data
public class MemberJoinParam {
    private String name;
    private Address address;
    private RoleType roleType = RoleType.USER;

    //params 객체를 member 객체로 변환해주는 메서드
    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .address(this.address)
                .roleType(this.roleType)
                .build();
    }
}

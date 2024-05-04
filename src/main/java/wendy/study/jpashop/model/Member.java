package wendy.study.jpashop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import wendy.study.jpashop.model.type.RoleType;
import wendy.study.jpashop.params.UpdateMemberParam;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString(exclude = {"orders"}, callSuper = true)
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(nullable = false, length = 10, unique = true)
    private String name;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @JsonIgnore
    @OneToMany(mappedBy = "member") //member(1) : order(n)
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member (String name, Address address, RoleType roleType) {
        this.name = name;
        this.address = address;
        this.roleType = roleType;
    }

    public void updateMember(UpdateMemberParam params) {
        this.name = params.getName() == null ? name : params.getName();
        this.address = params.getAddress() == null ? address : params.getAddress();
    }

    //수정 v2
    public void updateMember(Member member) {
        this.name = member.getName() == null ? name : member.getName();
        this.address = member.getAddress() == null ? address : member.getAddress();
    }
}

package wendy.study.jpashop.model;

import jakarta.persistence.*;
import lombok.*;
import wendy.study.jpashop.model.type.RoleType;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @OneToMany(mappedBy = "member") //member(1) : order(n)
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member (String name, Address address, RoleType roleType) {
        this.name = name;
        this.address = address;
        this.roleType = roleType;
    }
}

package wendy.study.jpashop.model;

import jakarta.persistence.*;
import lombok.Data;
import wendy.study.jpashop.model.type.RoleType;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    private String city;
    private String street;
    private String zipcode;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @OneToMany(mappedBy = "member") //member(1) : order(n)
    private List<Order> orders = new ArrayList<>();

}

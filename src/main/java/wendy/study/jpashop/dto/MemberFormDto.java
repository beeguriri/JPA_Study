package wendy.study.jpashop.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import wendy.study.jpashop.model.Address;
import wendy.study.jpashop.model.Member;
import wendy.study.jpashop.model.type.RoleType;

@Data
@NoArgsConstructor
@Slf4j
public class MemberFormDto {
    private Long memberId;
    @NotEmpty(message = "이름은 필수 입니다.")
    private String name;
    private String city;
    private String street;
    @Pattern(regexp = "^[0-9]{5}$", message = "우편번호는 5자리 숫자입니다.")
    private String zipcode;

    @Builder
    public MemberFormDto(Long id, String name, String city, String street, String zipcode) {
        this.memberId = id;
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    //dto 객체를 member 객체로 변환해주는 메서드
    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .address(Address.builder().city(this.city).street(this.street).zipcode(this.zipcode).build())
                .roleType(RoleType.USER)
                .build();
    }

    //Member 객체를 dto 객체로 변환해주는 메서드
    public static MemberFormDto toMemberFormDto(Member member) {
        log.info("member form dto:::{}", member);
        return MemberFormDto.builder()
                .id(member.getId())
                .name(member.getName())
                .city(member.getAddress() == null ? null : member.getAddress().getCity())
                .street(member.getAddress() == null ? null : member.getAddress().getStreet())
                .zipcode(member.getAddress() == null ? null : member.getAddress().getZipcode())
                .build();
    }
}

package wendy.study.jpashop.params;

import lombok.Data;
import wendy.study.jpashop.model.Address;

@Data
public class UpdateMemberParam {
    private String name;
    private Address address;
}

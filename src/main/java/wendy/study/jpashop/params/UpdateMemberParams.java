package wendy.study.jpashop.params;

import lombok.Data;
import wendy.study.jpashop.model.Address;

@Data
public class UpdateMemberParams {
    private String name;
    private Address address;
}

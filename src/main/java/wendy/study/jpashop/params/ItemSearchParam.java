package wendy.study.jpashop.params;

import lombok.Data;
import wendy.study.jpashop.model.type.DType;

@Data
public class ItemSearchParam {
    private String name;
    private DType dType;
}

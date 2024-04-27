package wendy.study.jpashop.model.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wendy.study.jpashop.model.Item;

@Data
@Entity
@DiscriminatorValue("ALBUM")
@EqualsAndHashCode(callSuper = true)
public class Album extends Item {

    private String artist;
    private String etc;
}

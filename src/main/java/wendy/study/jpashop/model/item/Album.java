package wendy.study.jpashop.model.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import wendy.study.jpashop.model.Item;

@Entity
@DiscriminatorValue("ALBUM")
public class Album extends Item {

    private String artist;
    private String etc;
}

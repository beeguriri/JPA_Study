package wendy.study.jpashop.params;

import lombok.Data;

@Data
public class UpdateItemParams {
    private String name;
    private int price;
    private int stockQuantity;
    //album
    private String artist;
    private String etc;
    //book
    private String author;
    private String isbn;
    //movie
    private String director;
    private String actor;
}

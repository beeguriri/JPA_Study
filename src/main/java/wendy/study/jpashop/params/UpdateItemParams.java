package wendy.study.jpashop.params;

import lombok.Data;

@Data
public class UpdateItemParams {
    private String name;
    private Integer price;
    private Integer stockQuantity;
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

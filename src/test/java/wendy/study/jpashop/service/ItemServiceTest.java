package wendy.study.jpashop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wendy.study.jpashop.exception.NotEnoughStockException;
import wendy.study.jpashop.model.Item;
import wendy.study.jpashop.model.item.Album;
import wendy.study.jpashop.model.item.Book;
import wendy.study.jpashop.model.item.Movie;
import wendy.study.jpashop.params.AlbumParams;
import wendy.study.jpashop.params.BookParams;
import wendy.study.jpashop.params.MovieParams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired ItemService itemService;

    @Test
//    @Rollback(false)
    void insertItem() {
        //given
        BookParams params = new BookParams();
        params.setName("book1");
        params.setPrice(10000);
        params.setStockQuantity(100);
        params.setAuthor("author1");
        params.setIsbn("aaaa1111");

        Book book = params.toEntity();

        //when
        Long itemId = itemService.insertItem(book);

        //then
        assertEquals(book, itemService.findItem(itemId));
    }

    @Test
//    @Rollback(false)
    void insertItem2() {
        //given
        MovieParams params = new MovieParams();
        params.setName("movie1");
        params.setPrice(15000);
        params.setStockQuantity(5);
        params.setDirector("director1");
        params.setActor("actor1");

        Movie movie = params.toEntity();

        //when
        Long itemId = itemService.insertItem(movie);

        //then
        assertEquals(movie, itemService.findItem(itemId));
    }

    @Test
//    @Rollback(false)
    void insertItem3() {
        //given
        AlbumParams params = new AlbumParams();
        params.setName("album");
        params.setPrice(7500);
        params.setStockQuantity(10000);
        params.setArtist("artist1");
        params.setEtc(null);

        Album album = params.toEntity();

        //when
        Long itemId = itemService.insertItem(album);

        //then
        assertEquals(album, itemService.findItem(itemId));
    }

    @Test
    void findNotExistItem() {

        //given
        Long id = 7L;
        //when
        //then
        assertThrows(IllegalStateException.class, () -> {
            itemService.findItem(id);
        });
    }

    @Test
    void addStock() {

        //given
        Item item = itemService.findItem(1L);
        int now = item.getStockQuantity();

        //when
        item.addStock(100);

        //then
        assertEquals(now+100, item.getStockQuantity());

    }

    @Test
    void removeStock() {

        //given
        Item item = itemService.findItem(1L);
        int now = item.getStockQuantity();

        //when
        item.removeStock(50);

        //then
        assertEquals(now-50, item.getStockQuantity());

    }

    @Test
    void removeStockException() {

        //given
        Item item = itemService.findItem(1L);

        //when
        //then
        assertThrows(NotEnoughStockException.class, () -> {
            item.removeStock(101);
        });
    }
}
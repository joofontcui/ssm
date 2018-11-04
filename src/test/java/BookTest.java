import com.joofont.entity.Book;
import com.joofont.service.BookService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author cui jun on 2018/10/28.
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml", "classpath:spring/spring-service.xml"})
public class BookTest {

    private static Logger logger = Logger.getLogger(BookTest.class);

    @Autowired
    private BookService bookService;

    @Test
    public void testGetBook() {
        Book book = bookService.getById(11);
        logger.info("[book]:{}" + book);
    }
}

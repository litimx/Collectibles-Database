package collections.data;

import collections.models.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CollectionJdbcTemplateRepositoryTest {

    @Autowired
    CollectionJdbcTemplateRepository repository;

    @Autowired
    AppUserJdbcTemplateRepository userRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    static boolean hasSetup = false;

    @BeforeEach
    void setUp(){
        if(!hasSetup){
            hasSetup = true;
            jdbcTemplate.update( "call set_known_good_state();");
        }
    }
    @Test
    void findAllByUserId() {
        List<Collection> all = repository.findAllByUserId(1);
        assertTrue(all.size() >= 1);
    }

    @Test
    void findById() {
        Collection actual = new Collection();
        actual.setId(3);
        actual.setName("testThree");
        actual.setValue(BigDecimal.valueOf(2123.78));
        actual.setUser(userRepository.findById(2));
        BigDecimal rounded = actual.getValue().setScale(2, RoundingMode.HALF_UP);

        Collection expected = repository.findById(3);
        expected.setValue(rounded);
        assertEquals(actual,expected);
    }

    @Test
    void addCollection() {
        Collection collection = new Collection();
        collection.setName("expected");
        collection.setValue(BigDecimal.valueOf(10));
        collection.setUser(userRepository.findById(1));

        repository.addCollection(collection);
        assertEquals(4,collection.getId());
    }

    @Test
    void updateCollection() {
        Collection collection = new Collection();
        collection.setId(2);
        collection.setName("expected");
        collection.setValue(BigDecimal.valueOf(10));
        collection.setUser(userRepository.findById(1));

        assertTrue(repository.updateCollection(collection));
    }

    @Test
    void shouldNotUpdateMissingCollection() {
        Collection collection = new Collection();
        collection.setId(0);
        collection.setName("expected");
        collection.setValue(BigDecimal.valueOf(10));
        collection.setUser(userRepository.findById(1));

        assertFalse(repository.updateCollection(collection));
    }

    @Test
    void deleteCollection() {
        assertTrue(repository.deleteCollection(1));
    }

    @Test
    void shouldNotDeleteMissingCollection() {
        assertFalse(repository.deleteCollection(0));
    }
}
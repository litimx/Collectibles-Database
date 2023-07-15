package collections.data;

import collections.data.mappers.CollectionMapper;
import collections.models.Collection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
@Repository
public class CollectionJdbcTemplateRepository implements CollectionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final AppUserJdbcTemplateRepository appUserJdbcTemplateRepository;

    // The UserRepository should be injected into the constructor
    // along with the JdbcTemplate.
    public CollectionJdbcTemplateRepository(JdbcTemplate jdbcTemplate, AppUserJdbcTemplateRepository appUserJdbcTemplateRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.appUserJdbcTemplateRepository = appUserJdbcTemplateRepository;
    }

    @Override
    @Transactional
    public List<Collection> findAllByUserId(int userId) {
        final String sql = "select collection_id, app_user_id, `name`, `value` from collection where app_user_id = ?;";
        return jdbcTemplate.query(sql, new CollectionMapper(appUserJdbcTemplateRepository), userId);
    }

    @Override
    @Transactional
    public Collection findById(int collectionId) {
        final String sql = "select collection_id, app_user_id, `name`, `value` from collection where collection_id = ?;";
        Collection result = jdbcTemplate.query(sql, new CollectionMapper(appUserJdbcTemplateRepository), collectionId)
                .stream()
                .findAny().orElse(null);
        return result;
    }

    @Override
    @Transactional
    public Collection addCollection(Collection collection) {
        final String sql = "insert into collection (app_user_id, `name`, `value`) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update( connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, collection.getUser().getAppUserId());
                    ps.setString(2, collection.getName());
                    ps.setBigDecimal(3, collection.getValue());
                    return ps;
                    },keyHolder);

        if(rowsAffected <= 0){
            return null;
        }

        collection.setId(keyHolder.getKey().intValue());
        return collection;
    }

    @Override
    @Transactional
    public boolean updateCollection(Collection collection) {
        final String sql = "update collection set "
                + "app_user_id = ?, "
                + "`name` = ?, "
                + "`value` = ? "
                + "where collection_id = ?";

        return jdbcTemplate.update(sql, collection.getUser().getAppUserId(), collection.getName(), collection.getValue() , collection.getId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteCollection(int collectionId) {
        final String sql = "delete from collection where collection_id = ?";
        return jdbcTemplate.update(sql, collectionId) > 0;
    }
}
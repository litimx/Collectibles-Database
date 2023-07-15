package collections.data.mappers;

import collections.models.Collection;
import collections.data.AppUserJdbcTemplateRepository;
import collections.security.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionMapper implements RowMapper<Collection> {
    private AppUserJdbcTemplateRepository appUserJdbcTemplateRepository;

    public CollectionMapper(AppUserJdbcTemplateRepository appUserJdbcTemplateRepository) {
        this.appUserJdbcTemplateRepository = appUserJdbcTemplateRepository;
    }

    @Override
    public Collection mapRow(ResultSet resultSet, int i) throws SQLException {
        Collection collection = new Collection();
        collection.setId(resultSet.getInt("collection_id"));
        int userId = resultSet.getInt("app_user_id");
        AppUser user = appUserJdbcTemplateRepository.findById(userId);
        collection.setUser(user);
        collection.setName(resultSet.getString("name"));
        collection.setValue(resultSet.getBigDecimal("value"));
        return collection;
    }
}

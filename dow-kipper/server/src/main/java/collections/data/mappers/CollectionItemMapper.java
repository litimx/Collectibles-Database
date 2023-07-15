package collections.data.mappers;

import collections.data.CollectionJdbcTemplateRepository;
import collections.data.ItemJdbcTemplateRepository;
import collections.models.CollectionItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionItemMapper implements RowMapper<CollectionItem> {

    private CollectionJdbcTemplateRepository collectionRepository;
    private ItemJdbcTemplateRepository itemRepository;

    public CollectionItemMapper(CollectionJdbcTemplateRepository collectionRepository, ItemJdbcTemplateRepository itemRepository) {
        this.collectionRepository = collectionRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public CollectionItem mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        CollectionItem collectionItem = new CollectionItem();
        collectionItem.setCollection(collectionRepository.findById(resultSet.getInt("collection_id")));
        collectionItem.setItem(itemRepository.findById(resultSet.getInt("item_id")));
        collectionItem.setSold(resultSet.getBoolean("is_sold"));
        collectionItem.setListedPrice(resultSet.getBigDecimal("listed_price"));
        return collectionItem;
    }
}

package collections.data;

import collections.data.mappers.CollectionItemMapper;
import collections.models.CollectionItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class CollectionItemJdbcTemplateRepository implements CollectionItemRepository{
    private final JdbcTemplate jdbcTemplate;

    private final CollectionJdbcTemplateRepository collectionRepository;

    private final ItemJdbcTemplateRepository itemRepository;

    public CollectionItemJdbcTemplateRepository(JdbcTemplate jdbcTemplate, CollectionJdbcTemplateRepository collectionRepository, ItemJdbcTemplateRepository itemRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.collectionRepository = collectionRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public boolean add(CollectionItem collectionItem) {
        final String sql = "insert into collection_item (collection_id, item_id, is_sold, listed_price) values(?, ?, ?, ?)";

        return jdbcTemplate.update( connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setInt(1, collectionItem.getCollection().getId());
                    ps.setInt(2, collectionItem.getItem().getId());
                    ps.setBoolean(3, collectionItem.isSold());
                    ps.setBigDecimal(4, collectionItem.getListedPrice());
                    return ps;
                }) > 0;
    }

    @Override
    @Transactional
    public boolean update(CollectionItem collectionItem) {
        final String sql = "update collection_item set listed_price = ?, is_sold = ? " +
                "where collection_id = ? and item_id = ?;";
        return jdbcTemplate.update( connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBigDecimal(1, collectionItem.getListedPrice());
            ps.setBoolean(2, collectionItem.isSold());
            ps.setInt(3, collectionItem.getCollection().getId());
            ps.setInt(4, collectionItem.getItem().getId());
            return ps;
            }) > 0;
    }

    @Override
    @Transactional
    public boolean delete(int collectionId, int itemId) {
        final String sql = "delete from collection_item "
                + "where collection_id = ? and item_id = ?;";
        return jdbcTemplate.update(sql,collectionId,itemId) > 0;
    }

    @Override
    public CollectionItem getCollectionItem(int collectionId, int itemId){
        final String sql = "select * from collection_item where collection_id = ? and item_id = ?;";
        return jdbcTemplate.query(sql, new CollectionItemMapper(collectionRepository,itemRepository),collectionId,itemId)
                .stream().findFirst().orElse(null);
    }
    @Override
    public List<CollectionItem> getCollectionItemByCollectionId(int collectionId){
        final String sql = "select * from collection_item where collection_id = ?";
        return jdbcTemplate.query(sql, new CollectionItemMapper(collectionRepository, itemRepository),collectionId);
    }
}

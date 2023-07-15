package collections.data;

import collections.data.mappers.ItemMapper;
import collections.models.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ItemJdbcTemplateRepository implements ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Item> rowMapper = new ItemMapper();
    public ItemJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Item> findAll() {
        String sql = "select item_id, " +
                "`name`, " +
                "`value`," +
                "grade " +
                "from item;";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    @Transactional
    public Item findById(int itemId) {
        final String sql = "select item_id,`name`, `value`, grade from item where item_id = ?;";
        Item result = jdbcTemplate.query(sql, rowMapper, itemId)
                .stream()
                .findAny().orElse(null);
        return result;
    }


    @Override
    @Transactional
    public Item addItem(Item item) {
        final String sql = "insert into item (`name`, `value`, grade) values (?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,item.getName());
                ps.setBigDecimal(2,item.getValue());
                ps.setString(3,item.getGrade().toString());
                return ps;
                }, keyHolder);
        if(rowsAffected <= 0){
            return null;
        }

        item.setId(keyHolder.getKey().intValue());
        return item;
    }

    @Override
    @Transactional
    public boolean updateItem(Item item) {
        final String sql = "update item set "
                + "`name` = ?, "
                + "`value` = ?, "
                + "grade = ? "
                + "where item_id = ?";
        return jdbcTemplate.update(sql, item.getName(), item.getValue(), item.getGrade().toString(), item.getId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteItem(int itemId) {
        final String sql = "delete from item where item_id = ?";
        return jdbcTemplate.update(sql, itemId) > 0;
    }
}
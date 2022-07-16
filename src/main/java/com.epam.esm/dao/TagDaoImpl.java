package com.epam.esm.dao;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.model.Tag;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class TagDaoImpl implements TagDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Tag> getAllTags() {
        return jdbcTemplate.query("SELECT * FROM tag", new TagMapper());
    }

    @Override
    public Tag getTagById(Integer id) {
        return jdbcTemplate.query("SELECT * FROM tag WHERE id = ?", new Object[] {id}, new TagMapper())
                .stream().findAny().orElseThrow(() -> new TagNotFoundException(id));
    }

    @Override
    public boolean updateTag(Tag tag) {
        jdbcTemplate.update("UPDATE tag SET name=? WHERE id=?", tag.getName(), tag.getId());
        return true;
    }

    @Override
    public boolean createTag(Tag tag) {
       jdbcTemplate.update("INSERT INTO tag  VALUES ( ? )", tag.getName());
       return true;
    }

    @Override
    public boolean deleteTag(Tag tag) {
        jdbcTemplate.update("DELETE FROM tag WHERE id=?", tag.getId());
        return false;
    }

    // Instead of using this RowMapper<Tag>, you can use BeanPropertyRowMapper<>(Tag.class) in arguments of query() method
    private static final class TagMapper implements RowMapper<Tag> {
        public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
            Tag tag = new Tag();
            tag.setId(rs.getInt("id"));
            tag.setName(rs.getString("name"));
            return tag;
        }
    }
}

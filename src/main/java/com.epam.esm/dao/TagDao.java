package com.epam.esm.dao;

import com.epam.esm.model.Tag;

import java.util.List;

public interface TagDao {

    Tag getTagById(Integer id);

    List<Tag> getAllTags();

    boolean deleteTag(Tag tag);

    boolean updateTag(Tag tag);

    boolean createTag(Tag tag);
}

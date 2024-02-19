package ua.com.alevel.service.impl;

import ua.com.alevel.db.DBResource;
import ua.com.alevel.entity.Group;
import ua.com.alevel.service.GroupService;

import java.util.Collection;

public class GroupServiceImpl implements GroupService {

    private final DBResource dbResource = DBResource.getInstance();

    @Override
    public void create(Group entity) {
        dbResource.createGroup(entity);
    }

    @Override
    public void update(Group entity) {
        dbResource.updateGroup(entity);
    }

    @Override
    public void delete(String id) {
        dbResource.deleteGroup(id);
    }

    @Override
    public Group findById(String id) {
        return dbResource.findGroupById(id);
    }

    @Override
    public Collection<Group> findAll() {
        return dbResource.findAllGroups();
    }
}

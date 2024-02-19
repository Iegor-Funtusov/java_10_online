package ua.com.alevel.db;

import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.GroupStudent;
import ua.com.alevel.entity.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public final class DBResource {

    List<Group> groupList = new ArrayList<>();
    List<Student> studentList = new ArrayList<>();
    List<GroupStudent> groupStudentList = new ArrayList<>();

    private static final DBResource instance = new DBResource();

    private DBResource() {}

    public static DBResource getInstance() {
        return instance;
    }

    public void createGroup(Group group) {
        group.setId(UUID.randomUUID().toString());
        groupList.add(group);
    }

    public void updateGroup(Group group) {
        for (Group group1 : groupList) {
            if (group1.getId().equals(group.getId())) {
                group1 = group;
            }
        }
    }

    public void deleteGroup(String id) {
        groupList.removeIf(group -> group.getId().equals(id));
    }

    public Group findGroupById(String id) {
        return groupList.stream().filter(group -> group.getId().equals(id)).findFirst().get();
    }

    public Collection<Group> findAllGroups() {
        return groupList;
    }
}

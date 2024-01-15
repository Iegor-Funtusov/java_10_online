package ua.com.alevel.service;

import ua.com.alevel.db.StudentGroupRelation;
import ua.com.alevel.entity.Group;

import java.util.List;

public class GroupService {

    private final StudentGroupRelation relation = StudentGroupRelation.getInstance();

    public void create(Group group) {
        relation.createGroup(group);
    }

    public void attachStudent(String studentId, String groupId) {
        relation.attachStudentForGroup(studentId, groupId);
    }

    public List<Group> allGroups() {
        return relation.allGroups();
    }
}

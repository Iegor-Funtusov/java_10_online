package ua.com.alevel.db;

import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;
import ua.com.alevel.entity.StudentGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Singleton
public final class StudentGroupRelation {

    private static StudentGroupRelation instance;
    private List<Student> students = new ArrayList<>();
    private List<Group> groups = new ArrayList<>();
    private List<StudentGroup> studentGroups = new ArrayList<>();

    private StudentGroupRelation() { }

    public static StudentGroupRelation getInstance() {
        if (instance == null) {
            instance = new StudentGroupRelation();
        }
        return instance;
    }

    public void createStudent(Student student) {
        student.setId(generateIdForStudent());
        students.add(student);
    }

    public void createGroup(Group group) {
        group.setId(generateIdForGroup());
        groups.add(group);
    }

    public void attachStudentForGroup(String studentId, String groupId) {
        if (studentGroups
                .stream()
                .noneMatch(
                        sg -> sg.getStudentId().equals(studentId) && sg.getGroupId().equals(groupId))
        ) {
            StudentGroup studentGroup = new StudentGroup();
            studentGroup.setStudentId(studentId);
            studentGroup.setGroupId(groupId);
            studentGroups.add(studentGroup);
        }
    }

    public List<Student> findAllStudentsByGroup(String groupId) {
        List<String> studentIdList = studentGroups
                .stream()
                .filter(sg -> sg.getGroupId().equals(groupId))
                .map(StudentGroup::getStudentId)
                .toList();

        List<Student> list = new ArrayList<>();

        for (int i = 0; i < students.size(); i++) {
            for (int i1 = 0; i1 < studentIdList.size(); i1++) {
                Student student = students.get(i);
                if (studentIdList.get(i1).equals(student.getId())) {
                    list.add(student);
                }
            }
        }

        return list;
    }

    public List<Student> allStudents() {
        return students;
    }

    public List<Group> allGroups() {
        return groups;
    }

    private String generateIdForStudent() {
        String id = UUID.randomUUID().toString();
        if (students.stream().anyMatch(s -> s.getId().equals(id))) {
            return generateIdForStudent();
        }
        return id;
    }

    private String generateIdForGroup() {
        String id = UUID.randomUUID().toString();
        if (groups.stream().anyMatch(s -> s.getId().equals(id))) {
            return generateIdForGroup();
        }
        return id;
    }
}

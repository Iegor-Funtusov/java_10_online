package ua.com.alevel.struct;

import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.GroupService;

public class StructBaseEintity {

    private BaseEntity[] entities = new BaseEntity[10];

    public void add(Object entity) {

        int i = 10;
        long l = (long) i;

        if (entity instanceof BaseEntity) {
//            entities[index] = (BaseEntity) entity;
        }
    }

    private void test() {
        StructBaseEintity structBaseEintity = new StructBaseEintity();
        structBaseEintity.add(new Student());
        structBaseEintity.add(new Student());
        structBaseEintity.add(new Group());
        structBaseEintity.add(new GroupService());
    }
}

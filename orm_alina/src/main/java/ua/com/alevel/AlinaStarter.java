package ua.com.alevel;

import ua.com.alevel.builder.TableBuilder;
import ua.com.alevel.factory.EntityFactory;

public class AlinaStarter {

    public static void start(Class<?> mainClass) {
        EntityFactory entityFactory = new EntityFactory(mainClass);
        TableBuilder tableBuilder = new TableBuilder(mainClass);
//        tableBuilder.createTables(entityFactory.getCreateTableQueries());
    }
}

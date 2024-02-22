package ua.com.alevel.factory;

public enum FileType {

    STUDENTS_JSON("students.json"),
    STUDENTS_CSV("students.csv");

    private final String type;

    FileType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

package ua.com.alevel;

public enum FileType {

    FILE_NAME("test.txt"),
    DIR_NAME("test1"),
    DIRS_NAME("test1/test2/test3");

    private final String path;

    FileType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

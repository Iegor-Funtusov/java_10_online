package ua.com.alevel.factory;

public enum ResourceType {
    CSV("csv"),
    JSON("json");

    private final String type;

    ResourceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

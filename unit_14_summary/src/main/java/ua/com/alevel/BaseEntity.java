package ua.com.alevel;

public sealed class BaseEntity permits Student {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static abstract sealed class InnerBaseEntity permits InnerBaseEntity2 {

    }

    private final class InnerBaseEntity2 extends InnerBaseEntity {}
}

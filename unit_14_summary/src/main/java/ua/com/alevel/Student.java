package ua.com.alevel;

public final class Student extends BaseEntity {

    private String name;

     Student() {}

    public String getName() {
        final String blaBla = "vdsgffsdg";
        this.setName(blaBla);
        return name;
    }

    public void setName(final String name) {
//         name = "dljgasdjlhvgaslvfghasldfyg";
        this.name = name;
    }
}

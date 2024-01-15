package ua.com.alevel;

public class Test_One { // Not a valid

    private String test_one; // Not a valid
    private String testOne; // Valid
    private String testOne1; // Not a valid
    private String testOne2; // Not a valid
//    private String _; // Not a valid

    private String test_one() { // Not a valid
        return test_one;
    }

    /*
    vjh;jvhs;df
    fgha;fkjgh;dfg
    fghk;dfsgjh;dfs
    **/
    private String testOne() { // Valid
        return testOne; //viufg'sadg
    }

    private String TestOne(String s) { // Not a valid
        return testOne;
    }

    private String TestOne(String s, String sOne) {
        return testOne; // Not a valid
    }
}

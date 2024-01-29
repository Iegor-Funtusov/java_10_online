package ua.com.alevel;

import ua.com.alevel.lambda.MathSumTest;
import ua.com.alevel.list.ListTest;
import ua.com.alevel.map.MapTest;

import java.util.ArrayList;
import java.util.List;

public class CollectionsMain {
    public static void main(String[] args) {
        // Java collection framework
//        new ListTest().test();
//        new MapTest().test();

//        Tree<Integer> integerTree = new Tree<>();
//
//        integerTree.add(4);
//        integerTree.add(8);
//        integerTree.add(1);
//        integerTree.add(8);
//        integerTree.add(4);
//        integerTree.add(88);
//        integerTree.add(34);
//
//        System.out.println("integerTree = " + integerTree);


        MathSumTest mathSumTest = new MathSumTest();
        mathSumTest.test();
    }
}

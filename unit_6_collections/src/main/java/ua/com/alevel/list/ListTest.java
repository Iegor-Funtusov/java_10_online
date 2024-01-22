package ua.com.alevel.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListTest {

    private final List<Integer> arrayList = new ArrayList<>();
    private final List<Integer> linkedList = new LinkedList<>();
    private final int size = 100_000;

    public void test() {
        add();
//        get();
//        update();
        delete();
    }

    private void add() {
        System.out.println("ListTest.add");
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arrayList.add(i); // O(1)
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("arrayList end = " + end);

        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            linkedList.add(i); // O(n) -> O(1)
        }
        end = System.currentTimeMillis() - start;
        System.out.println("linkedList end = " + end);
    }

    private void get() {
        System.out.println("ListTest.get");
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arrayList.get(i); // O(1)
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("arrayList get = " + end);

        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            linkedList.get(i); // O(n)
        }
        end = System.currentTimeMillis() - start;
        System.out.println("linkedList get = " + end);
    }

    private void update() {
        System.out.println("ListTest.update");
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arrayList.set(i, 10); // O(1)
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("arrayList update = " + end);

        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            linkedList.set(i, 10); // O(n)
        }
        end = System.currentTimeMillis() - start;
        System.out.println("linkedList update = " + end);
    }

    private void delete() {
//        arrayList.clear();
        System.out.println("ListTest.delete");
        long start = System.currentTimeMillis();
        Iterator<Integer> arrayIterator = arrayList.iterator();
        while (arrayIterator.hasNext()) { // O(n)
            Integer i = arrayIterator.next();
            arrayIterator.remove();
        }
//        for (int i = 0; i < size; i++) {
//            arrayList.remove(i);
//        }
        long end = System.currentTimeMillis() - start;
        System.out.println("arrayList delete = " + end);

        start = System.currentTimeMillis();
        Iterator<Integer> linkIterator = linkedList.iterator();
        while (linkIterator.hasNext()) { // O(1)
            Integer i = linkIterator.next();
            linkIterator.remove();
        }
//        for (int i = 0; i < size; i++) {
//            linkedList.remove(i);
//        }
        end = System.currentTimeMillis() - start;
        System.out.println("linkedList delete = " + end);
    }
}

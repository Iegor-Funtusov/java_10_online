package ua.com.alevel.lambda;

import ua.com.alevel.Student;

import java.util.Comparator;

public class MathSumTest {

    public void test() {
        MathSum impl1 = new MathSumImpl();
//        MathSumImpl impl2 = new MathSumImpl();

        this.sum(impl1); // 1 - real implements

        MathSum impl2 = new MathSum() {
            @Override
            public int sum(int a, int b) {
                return a + b;
            }
        }; // 2 - anonim class
        this.sum(impl2);

        MathSum impl3 = (a, b) -> a + b; // lambda
        this.sum(impl3);

        BlaBlaBla blaBlaBla = (s, a, sa) -> {
            System.out.println("a = " + a);
            };

    }

    private void sum(MathSum mathSum) {
        System.out.println("mathSum = " + mathSum.sum(10, 4));
    }

    private MathSum getMathSum() {
        return new MathSum() {
            @Override
            public int sum(int a, int b) {
                return 0;
            }
        };
    }

    private Comparator<Student> getStudentComparator() {
        return new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return 0;
            }
        };
    }
}

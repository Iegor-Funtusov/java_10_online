package ua.com.alevel;

public class Tree<E extends Comparable<E>> {
    private Node<E> root;

    public void add(E value) {
        root = add(value, root);
    }

    private Node<E> add(E value, Node<E> current) {
        if (current == null) {
            return new Node<>(value);
        }
        if (value.compareTo(current.value) < 0) {
            current.left = add(value, current.left);
        }
        if (value.compareTo(current.value) > 0) {
            current.right = add(value, current.right);
        }
        return current;
    }

    private class Node<E> {
        E value;
        Node<E> left;
        Node<E> right;

        Node(E value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }
}

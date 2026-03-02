import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private final Node sentinel;
    private int size;

    public class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node (T value, Node prev, Node next) {
            this.prev = prev;
            item = value;
            this.next = next;
        }
    }
    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        if (size == 0) {
            sentinel.next = new Node(x, sentinel, sentinel.next);
            sentinel.prev = sentinel.next;
        } else {
            Node currNode = new Node(x, sentinel, sentinel.next);
            sentinel.next.prev = currNode;
            sentinel.next = currNode;
        }
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if (size == 0) {
            sentinel.prev = new Node(x, sentinel.prev, sentinel);
            sentinel.next = sentinel.prev;
        } else {
            Node currNode = new Node(x, sentinel.prev, sentinel);
            sentinel.prev.next = currNode;
            sentinel.prev = currNode;
        }
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        if (size == 0) {
            return returnList;
        } else {
            Node currNode = sentinel.next;
            while (currNode != sentinel) {
                returnList.add(currNode.item);
                currNode = currNode.next;
            }
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            Node firstNode = sentinel.next;
            sentinel.next = firstNode.next;
            firstNode.next.prev = sentinel;
            size -= 1;
            return firstNode.item;
        }
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            Node lastNode = sentinel.prev;
            sentinel.prev = lastNode.prev;
            lastNode.prev.next = sentinel;
            size -= 1;
            return lastNode.item;
        }
    }

    @Override
    public T get(int index) {
        if ((size - 1) < index || index < 0) {
            return null;
        } else {
            Node currNode = sentinel.next;
            for (int i = 0; i < index; i += 1) {
                currNode = currNode.next;
            }
            return currNode.item;
        }
    }

    @Override
    public T getRecursive(int index) {
        Node currNode = sentinel.next;
        return getRecursiveHelper(index, currNode);
    }

    private T getRecursiveHelper(int index, Node currNode) {
        if ((size - 1) < index || index < 0) {
            return null;
        }
        if (index == 0) {
            return currNode.item;
        }
        else {
            return getRecursiveHelper(index - 1, currNode.next);
        }
    }
}

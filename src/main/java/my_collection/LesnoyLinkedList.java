package my_collection;

public class LesnoyLinkedList<E> {

    private LesnoyNode<E> head;
    private LesnoyNode<E> tail;
    private int size;

    public LesnoyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public LesnoyLinkedList(E element) {
        head = new LesnoyNode<>(element);
        tail = head;
        size = 1;
    }

    public void add(E element) {
        if (head == null) {
            head = new LesnoyNode<>(element);
            tail = head;
        } else {
            LesnoyNode<E> lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            LesnoyNode<E> newElement = new LesnoyNode<>(element);
            lastNode.next = newElement;
            newElement.previous = lastNode;
            tail = lastNode;
        }
        size++;
    }

    public E get(int index) {
        if (index <= 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        LesnoyNode<E> current = head;
        for (int i = 1; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    public E removeByValue(E element) {
        int index = indexOf(element);
        if (index == -1) {
            return null;
        }
        return remove(index);
    }

    public E remove(int index) {
        if (index <= 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        LesnoyNode<E> current = head;
        for (int i = 1; i < index; i++) {
            current = current.next;
        }
        if (current.previous != null) {
            LesnoyNode<E> previous = current.previous;
            previous.next = current.next;
        } else {
            head = head.next;
        }
        if (current.next != null) {
            LesnoyNode<E> next = current.next;
            next.previous = current.previous;
        } else {
            tail = tail.previous;
        }
        size--;
        return current.element;
    }

    public int indexOf(E element) {
        LesnoyNode<E> current = head;
        for (int i = 1; i < size + 1; i++) {
            if (current.element.equals(element)) {
                return i;
            } else {
                current = current.next;
            }
        }
        return -1;
    }

    public E getFirst() {
        return head == null ? null : head.element;
    }

    public E getLast() {
        return tail == null ? null : tail.element;
    }

    public int getSize() {
        return size;
    }

    private static class LesnoyNode<E> {
        private E element;
        private LesnoyNode<E> next;
        private LesnoyNode<E> previous;

        public LesnoyNode (E element) {
            this.element = element;
            this.next = null;
            this.previous = null;
        }

        @Override
        public String toString() {
            return "{" + element + "}";
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        LesnoyNode<E> current = head;
        while (current != null) {
            sb.append(current.element);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}

package my_collection;

public class LesnoyList<E> {

    private final int DEFAULT_CAPACITY = 10;

    private Object[] items;
    private int capacity;
    private int size;

    public LesnoyList() {
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
        items = new Object[this.capacity];
    }

    public LesnoyList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        items = new Object[this.capacity];
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void add(E e) {
        if (size == capacity) {
            resize();
        }
        items[size++] = e;
    }

    public E get(int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) items[--index];
    }

    public void remove(int index) {
        items[--index] = null;
        if (index < size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }
        size--;
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    private void resize() {
        capacity = (int) (capacity * 1.5);
        Object[] newItems = new Object[capacity];
        System.arraycopy(items, 0, newItems, 0, size);
        this.items = newItems;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(items[i]);
            if (i < size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}

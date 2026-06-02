package Model;

import java.util.ArrayList;
import java.util.List;

public class MyMinHeap<T extends Comparable<T>> {
    private List<T> heap;

    public MyMinHeap() {
        this.heap = new ArrayList<>();
    }

    public MyMinHeap(List<T> list) {
        this.heap = new ArrayList<>(list);
        heapify();
    }

    public void insert(T val) {
        heap.add(val);
        heapUp(heap.size() - 1);
    }

    private void heapify() {
        int lastNonLeafNode = (heap.size() / 2) - 1;
        for (int i = lastNonLeafNode; i >= 0; i--) {
            heapDown(i);
        }
    }

    public T extractMin() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        T minVal = heap.get(0);
        T lastVal = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastVal);
            heapDown(0);
        }

        return minVal;
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    // Restores min-heap property: bubble up while SMALLER than parent
    private void heapUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            // If current node is SMALLER than parent (result < 0), swap them
            if (heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // Restores min-heap property: push down while LARGER than smallest child
    private void heapDown(int index) {
        int size = heap.size();

        while (index < size) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;

            // Check if left child is smaller
            if (leftChild < size && heap.get(leftChild).compareTo(heap.get(smallest)) < 0) {
                smallest = leftChild;
            }

            // Check if right child is smaller
            if (rightChild < size && heap.get(rightChild).compareTo(heap.get(smallest)) < 0) {
                smallest = rightChild;
            }

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
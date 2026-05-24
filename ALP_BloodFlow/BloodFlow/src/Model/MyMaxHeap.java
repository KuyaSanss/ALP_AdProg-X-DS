package Model;

import java.util.ArrayList;
import java.util.List;

public class MyMaxHeap<T extends Comparable<T>> {
    private List<T> heap;

    public MyMaxHeap() {
        this.heap = new ArrayList<>();
    }

    public void insert(T val) {
        heap.add(val);
        heapUp(heap.size() - 1);
    }

    public MyMaxHeap(List<T> list) {
        this.heap = new ArrayList<>(list);
        heapify();
    }

    private void heapify() {
        int lastNonLeafNode = (heap.size() / 2) - 1;
        
        for (int i = lastNonLeafNode; i >= 0; i--) {
            heapDown(i);
        }
    }

    public T extractMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        T maxVal = heap.get(0);
        T lastVal = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastVal);
            heapDown(0);
        }

        return maxVal;
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

    // Restores max-heap property using compareTo
    private void heapUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            // If current node is LARGER than parent (result > 0), swap them
            if (heap.get(index).compareTo(heap.get(parentIndex)) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // Restores max-heap property using compareTo
    private void heapDown(int index) {
        int size = heap.size();

        while (index < size) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;

            // Check if left child is larger
            if (leftChild < size && heap.get(leftChild).compareTo(heap.get(largest)) > 0) {
                largest = leftChild;
            }

            // Check if right child is larger
            if (rightChild < size && heap.get(rightChild).compareTo(heap.get(largest)) > 0) {
                largest = rightChild;
            }

            if (largest != index) {
                swap(index, largest);
                index = largest;
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

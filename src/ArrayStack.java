package src;

import java.util.Iterator;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

public class ArrayStack<E extends Cloneable> implements Stack<E>, Iterable<E> {
    private Object[] array;
    private int top;
    private int capacity;

    public ArrayStack(int capacity) {
        if (capacity < 0) {
            throw new NegativeCapacityException();
        }
        this.capacity = capacity;
        this.array = new Object[capacity];
        this.top = -1;
    }

    @Override
    public void push(E element) {
        if (isFull()) {
            throw new StackOverflowException();
        }
        top++;
        array[top] = element;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        @SuppressWarnings("unchecked")
        E element = (E) array[top];
        array[top] = null;
        top--;
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        @SuppressWarnings("unchecked")
        E element = (E) array[top];
        return element;
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    private boolean isFull() {
        return top == capacity - 1;
    }

    @Override
    public ArrayStack<E> clone() {
        try {
            ArrayStack<E> clonedStack = (ArrayStack<E>) super.clone();
            clonedStack.array = array.clone();

            for (int i = 0; i <= top; i++) {
                try {
                    Method cloneMethod = array[i].getClass().getDeclaredMethod("clone");
                    cloneMethod.setAccessible(true);
                    clonedStack.array[i] = cloneMethod.invoke(array[i]);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    return null;
                    // Handle exceptions if necessary
                    // Return null or perform any appropriate error handling
                }
            }

            return clonedStack;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator<E>();
    }

    private class StackIterator<E> implements Iterator<E> {
        private int currentIndex = top;

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = (E) array[currentIndex];
            currentIndex--;
            return element;
        }
    }
}
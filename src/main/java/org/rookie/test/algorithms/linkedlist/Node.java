package org.rookie.test.algorithms.linkedlist;

/**
 * 链表节点定义
 */
public class Node<T> {

    public Node(){}
    public Node(T val) {
        this.setVal(val);
    }
    Node next;

    private T val;

    public void setVal(T val) {
        this.val = val;
    }

    public T getVal() {
        return val;
    }
}

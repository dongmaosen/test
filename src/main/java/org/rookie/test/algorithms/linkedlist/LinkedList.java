package org.rookie.test.algorithms.linkedlist;

import java.util.List;

/**
 * 单向链表
 */
public class LinkedList <T> {
    /**
     * 头指针
     */
    Node<T> head;

    /**
     * 添加一个节点到头部
     */
    public void add(Node<T> node) {
        if (head == null) {
            head = node;
            return;
        }
        node.next = head;
        head = node;
    }

    /**
     * 链表长度
     * @return 链表中节点个数
     */
    public int size() {
        Node countPointer = head;
        int counter = 0;
        while (countPointer != null) {
            counter++;
            countPointer = countPointer.next;
        }
        return counter;
    }
    /**
     * 删除与给定值相等的节点
     * @return 移除的节点
     */
    public Node<T> remove(Node<T> node) {
        T val = node.getVal();
        Node<T> tmp = head;
        Node<T> pre = null;
        while (tmp != null) {
            T nodeVal = tmp.getVal();
            //找到那个值
            if (nodeVal != null && nodeVal.equals(val)) {
                //找到值，要把链表的这个节点拆出来，重组链表
                if (pre == null) {
                    //头节点就是要找的
                    head = head.next;
                } else {
                    pre.next = tmp.next;
                }
                tmp.next = null;
                return tmp;
            }
            pre = tmp;
            tmp = tmp.next;
        }
        return null;
    }

    /**
     * 顺序返回链表的值
     * @return
     */
    public String toString() {
        if (head == null) {
            return "";
        }
        Node<T> tmp = head;
        StringBuilder linkedValStr = new StringBuilder();
        while (tmp != null) {
            linkedValStr.append(tmp.getVal());
            tmp = tmp.next;
            if (tmp != null) {
                linkedValStr.append("->");
            }
        }
        return linkedValStr.toString();
    }
    /**
     * 单链表反转(引入两个辅助指针)
     */
    public void revert() {
        //没有节点或者只有一个节点，不用进行任何操作
        if (head == null || head.next == null) {
            return;
        }
        int first = 1;
        Node<T> pre = head;
        head=head.next;
        Node<T> after = head.next;
        do {
            if (first == 1) {
                pre.next = null;
            }
            head.next = pre;
            pre = head;
            head = after;
            after = after.next;
            first++;
        } while (after != null);
        head.next = pre;
    }

    /**
     * 检查单向链表中是否有环的存在(快慢指针法)
     * @return true-存在环
     */
    public boolean checkCircle() {
        if (head == null) {
            return false;
        }
        Node<T> low,fast;
        low = head;
        fast = head;
        while ((fast = fast.next) != null) {
            fast = fast.next;
            if (fast != null && (fast = fast.next) != null) {
                low = low.next;
            }
            if (low != null && fast != null && fast.getVal().equals(low.getVal())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除单向链表中倒数第n个结点（也是通过快慢节点的思路，两个节点之间保持固定的距离）
     */
    public Node<T> getLastN(int n) {
        Node fast = head, slow = head;
        for (int i = 0; i < n; i++) {
            if (fast != null) {
                fast = fast.next;
            } else {
                return slow;
            }
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 返回链表中的中间节点
     * @return
     */
    public Node<T> getMiddleNode() {
        Node fast = head, slow = head;
        //少于3个节点，认为没有中间节点
        if (fast == null || fast.next == null || fast.next.next == null) {
            return null;
        }
        do {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
                slow = slow.next;
            } else {
                return slow;
            }
        } while (fast != null);

        return slow;
    }

    /**
     * 清空链表
     */
    public void clear() {
        head = null;
    }

    /**
     * 测试构造一个环
     */
    public void constructCircle() {
        Node<Integer> node_1 = new Node<>(1);
        Node<Integer> node_2 = new Node<>(2);
        Node<Integer> node_3 = new Node<>(3);
        Node<Integer> node_4 = new Node<>(4);
        Node<Integer> node_5 = new Node<>(5);
        Node<Integer> node_6 = new Node<>(6);
        Node<Integer> node_7 = new Node<>(7);
        Node<Integer> node_8 = new Node<>(8);
        Node<Integer> node_9 = new Node<>(9);
        head = (Node<T>) node_1;
        node_1.next = node_2;
        node_2.next = node_3;
        node_3.next = node_4;
        node_4.next = node_5;
        node_5.next = node_6;
        node_6.next = node_7;
        node_7.next = node_8;
        node_8.next = node_9;
        node_9.next = node_3;
    }
}

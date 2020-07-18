package org.rookie.test.algorithms;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.rookie.test.algorithms.linkedlist.LinkedList;
import org.rookie.test.algorithms.linkedlist.Node;

import java.io.IOException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LinkedListTest {

    LinkedList<Integer> linkedList = new LinkedList<Integer>();

    @Before
    public void testAdd() throws IOException {
        System.out.println("testAdd in");
        Node<Integer> node1 = new Node<Integer>();
        node1.setVal(1);
        Node<Integer> node2 = new Node<Integer>();
        node2.setVal(2);
        Node<Integer> node3 = new Node<Integer>();
        node3.setVal(3);
        Node<Integer> node4 = new Node<Integer>();
        node4.setVal(4);
        Node<Integer> node5 = new Node<Integer>();
        node5.setVal(5);
        linkedList.add(node5);
        linkedList.add(node4);
        linkedList.add(node3);
        linkedList.add(node2);
        linkedList.add(node1);
        System.out.println("初始内容：" + linkedList.toString());
        System.out.println("初始长度：" + linkedList.size());
        System.out.println("testAdd out");
    }

//    @Test
    public void testRemove() {
        System.out.println("testRemove in");
        Node<Integer> node = new Node<Integer>();
        node.setVal(2);
        linkedList.remove(node);
        System.out.println("移除后内容：" + linkedList.toString());
        System.out.println("移除后长度：" + linkedList.size());
        linkedList.revert();
        System.out.println("反转后内容：" + linkedList.toString());
        linkedList.add(node);
        System.out.println("在头部添加：" + linkedList.toString());
        System.out.println("是否存在环：" + linkedList.checkCircle());
        System.out.println("testRemove out");
    }
//    @Test
    public void testCircle() {
        System.out.println("testCircle in");
        linkedList.clear();
        linkedList.constructCircle();
        System.out.println(linkedList.checkCircle());
        linkedList.clear();
        System.out.println("testCircle out");
    }
    @Test
    public void testGetMiddle() {
        System.out.println("testGetMiddle in");
        Node node = linkedList.getMiddleNode();
        System.out.println(node == null ? "-" : node.getVal());
        System.out.println(linkedList.getLastN(2).getVal());
        System.out.println("testGetMiddle out");
    }
}

package com.sen.playground.datastructure;

/**
 * Single linked list and its operations
 */

public class MyLink {

    /**
     * Define a single node
     */
    private class Node {
       Node next = null;
       int data;
       public Node(int data) {
           this.data = data;
       }
   }

    private Node head = null;
    /**
     * Create and append a node to tail
     */
    private void add(int d)  {
        Node newNode = new Node(d);
        if(null==head) {
            head = newNode;
            return;
        }

        Node tmp = head;
        while(null!=tmp.next) {
            tmp = tmp.next;
        }
        tmp.next = newNode;
    }

    private void print(String str) {
        System.out.println(str);
    }
    /**
     * Delete node at a named index
     */
    public void remove(int index) {

        if(null==head) {
            print("can not remove, link is empty");
            return;
        }

        if(size()<index || index < 1) {
            print("can not remove, index is greater than link size or index is less than 1, index:" + index);
            return;
        }

        if(index==1) {
            head = head.next;
            System.out.println("head is removed");
            return;
        }

        int i=1;
        Node tmp = head;
        while(null!=tmp.next) {
            i++;
            Node current = tmp;
            tmp=tmp.next;
            if(i==index) {
                current.next = tmp.next;
                print("removed node, data:"+tmp.data);
                tmp=null;//free memory;
                return;
            }
        }
    }


    public int size() {
        int count = 1;
        if(null==head) {
            return 0;
        }

        Node tmp = head;
        while(null!=tmp.next) {
            count++;
            tmp=tmp.next;
        }

        return count;
    }

    public void printList() {
        Node tmp = head;
        while (tmp != null) {
            System.out.println(tmp.data);
            tmp = tmp.next;
        }
    }

    /**
     * 链表反转
     *
     * @return
     */
    public Node reverseIteratively() {
        Node pReversedHead = head;
        Node pNode = head;
        Node pPrev = null;
        while (pNode != null) {
            Node pNext = pNode.next;
            if (pNext == null) {
                pReversedHead = pNode;
            }
            pNode.next = pPrev;
            pPrev = pNode;
            pNode = pNext;
        }
        this.head = pReversedHead;
        return this.head;
    }

    /**
     * Use two pointers, one is faster, because it moves two steps each round, the other is normal pointer
     * it moves one step forward each round, when the faster reaches the end, the normal pointer is the mid node
     * @return
     */
    public int searchMid() {
        Node p=head, q=head;
        while(p.next!=null && q.next!=null && q.next.next !=null) {
            p = p.next;
            q = q.next.next;
        }
        System.out.println("Mid:"+q.data);
        return p.data;

    }

    /**
     * Use two pointers, p1 and p2, p2 go k steps forward and then p1 p2 move forward together, when p2 reaches the end,
     * p1 is the k to the end element
     * @return
     */
    public int kToTheEnd(int k) {

        if(k<1 || k > this.size())
            return 0;

        Node p1=head, p2=head;

        for(int i=0; i<k; i++) {
            p2=p2.next;
        }

        while(null!=p2.next) {
            p1=p1.next;
            p2=p2.next;
        }

        print("k to end, k:" + k + " data: " + p1.data);
        return p1.data;

    }

    public static void main (String[] args) {
        MyLink list = new MyLink();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println("linkLength:" + list.size());
        System.out.println("head.data:" + list.head.data);
        list.printList();
        //list.remove(2);
        //System.out.println("After deleteNode(4):");
        //list.printList();

//        list.reverseIteratively();
//        list.print("After reverse:");
//        list.printList();

        list.searchMid();

        list.kToTheEnd(3);


    }

}

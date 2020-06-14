package com.sen.playground.algorithm;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class MergeSortLinkedList {

    // merge sort
    public static ListNode mergeSortList(ListNode head) {

        if (head == null || head.next == null)
            return head;

        // count total number of elements
        int count = 0;
        ListNode p = head;
        while (p != null) {
            count++;
            p = p.next;
        }

        // break up to two list
        int middle = count / 2;

        ListNode l = head, r = null;
        ListNode p2 = head;
        int countHalf = 0;
        while (p2 != null) {
            countHalf++;
            ListNode next = p2.next;

            if (countHalf == middle) {
                p2.next = null;
                r = next;
            }
            p2 = next;
        }

        // now we have two parts l and r, recursively sort them
        ListNode h1 = mergeSortList(l);
        ListNode h2 = mergeSortList(r);

        // merge together
        ListNode merged = recursiveMergeTwoLists(h1, h2);

        return merged;
    }

    public static ListNode merge(ListNode l, ListNode r) {
        ListNode p1 = l;
        ListNode p2 = r;

        ListNode fakeHead = new ListNode(100);
        ListNode pNew = fakeHead;

        while (p1 != null || p2 != null) {

            if (p1 == null) {
                pNew.next = new ListNode(p2.val);
                p2 = p2.next;
                pNew = pNew.next;
            } else if (p2 == null) {
                pNew.next = new ListNode(p1.val);
                p1 = p1.next;
                pNew = pNew.next;
            } else {
                if (p1.val < p2.val) {
                    // if(fakeHead)
                    pNew.next = new ListNode(p1.val);
                    p1 = p1.next;
                    pNew = pNew.next;
                } else if (p1.val == p2.val) {
                    pNew.next = new ListNode(p1.val);
                    pNew.next.next = new ListNode(p1.val);
                    pNew = pNew.next.next;
                    p1 = p1.next;
                    p2 = p2.next;

                } else {
                    pNew.next = new ListNode(p2.val);
                    p2 = p2.next;
                    pNew = pNew.next;
                }
            }
        }

        // printList(fakeHead.next);
        return fakeHead.next;
    }

    /**
     * Algorithm

     First, we set up a false "prehead" node that allows us to easily return the head of the merged list later.
     We also maintain a prev pointer, which points to the current node for which we are considering adjusting its next pointer.
     Then, we do the following until at least one of l1 and l2 points to null:
     if the value at l1 is less than or equal to the value at l2, then we connect l1 to the previous node and increment l1.
     Otherwise, we do the same, but for l2. Then, regardless of which list we connected, we increment prev to keep it one step
     behind one of our list heads.

     After the loop terminates, at most one of l1 and l2 is non-null. Therefore (because the input lists were in sorted order),
     if either list is non-null, it contains only elements greater than all of the previously-merged elements.
     This means that we can simply connect the non-null list to the merged list and return it.
     * @param l1
     * @param l2
     * @return
     */

    public static ListNode optiMergeTwoLists(ListNode l1, ListNode l2) {
        // maintain an unchanging reference to node ahead of the return node.
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // exactly one of l1 and l2 can be non-null at this point, so connect
        // the non-null list to the end of the merged list.
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }

    /**
     * We model the above recurrence directly, first accounting for edge cases. Specifically,
     * if either of l1 or l2 is initially null, there is no merge to perform,
     * so we simply return the non-null list. Otherwise, we determine which of l1 and l2 has a smaller head,
     * and recursively set the next value for that head to the next merge result. Given that both lists are null-terminated,
     * the recursion will eventually terminate.
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode recursiveMergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = recursiveMergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = recursiveMergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(16);
        ListNode n2 = new ListNode(15);
        ListNode n3 = new ListNode(14);

        ListNode n4 = new ListNode(0);
        ListNode n5 = new ListNode(12);
        ListNode n6 = new ListNode(9);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        n1 = mergeSortList(n1);

        printList(n1);
    }

    public static void printList(ListNode x) {
        if(x != null){
            System.out.print(x.val + " ");
            while (x.next != null) {
                System.out.print(x.next.val + " ");
                x = x.next;
            }
            System.out.println();
        }

    }
}
package com.pong.algorithm;

import com.pong.algorithm.utils.ListNode;

public class RemoveRepeat {
    public static ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) return pHead;
        ListNode node = pHead;
        int temp = node.val;
        ListNode resultNode = new ListNode(0);
        ListNode beginNode = resultNode;
        while (node != null) {
            if (node.next != null && node.next.val == temp) {
                node = node.next;
                temp = node.val;
            } else if (node.next != null && node.next.val != temp) {
                resultNode.next = node;
                node = node.next;
                temp = node.val;
            }
        }
        return beginNode.next;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(0);
        System.out.println(deleteDuplication(node.makeNode(new int[]{1, 2, 3, 3, 4, 4, 5})));
    }
}

package com.pong.algorithm;

import com.pong.algorithm.utils.ListNode;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class ListNodeNotAgain {
    public ListNode deleteDuplication(ListNode pHead){
        if(pHead == null) return null;
        if (pHead.next == null) return pHead;
        Set<Integer> set = new LinkedHashSet<>();
        ListNode node = pHead;
        while(node!=null) {
            set.add(node.val);
            node = node.next;
        }
        ListNode node2 = new ListNode(0);
        ListNode temp;
        Iterator ite = set.iterator();
        while (ite.hasNext()) {
            temp = new ListNode((int)ite.next());
            node2.next = temp;
            node2 = node2.next;
        }
        return node2.next;
    }
}

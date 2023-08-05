package org.example;

public class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        for(;fast != null || fast.next != null;) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

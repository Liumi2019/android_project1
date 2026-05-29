package com.example.app;

public class ListNode {
    public int val;

    public ListNode next;

    public ListNode() {
        val = 0;
        next = null;
    }

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }
}

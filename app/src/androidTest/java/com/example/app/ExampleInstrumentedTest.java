package com.example.app;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String TAG = "ExampleInstrumentedTest_MY";
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String packageName = appContext.getPackageName();
        assertEquals("com.example.app", packageName);
        Log.i(TAG, packageName);


        ListNode head10 = createNode(5, 0, 2);
        head10 = revalList(head10);
        printAllList(head10);
        head10 = revalList2(head10);
        printAllList(head10);

//        // 合并两个链表
//        ListNode head2 = createNode(5, 1, 2);
//        printAllList(head2);
//        ListNode head = comList(head1, head2);
//        printAllList(head);
//        ListNode head = sortList(head1);
//        printAllList(head);

//        // 交叉点
//        ListNode node1 = head1;
//        ListNode node2 = head2;
//        while (node1 != node2) {
//            node1 = (node1 == null) ? head2 : node1.next;
//            node2 = (node2 == null) ? head1 : node2.next;
//        }
//        if (node1 == null) {
//            Log.e(TAG, "result: null");
//        } else {
//            Log.e(TAG, "result: " + node1.val);
//        }
    }

    @Test
    public void runAll() {
        ListNode head1 = createNode(2, 50, 1);
        ListNode head2 = createNode(3, 3, 1);
        ListNode head3 = createNode(4, 49, 10);
        ListNode head4 = createNode(4, 8, 2);
        ListNode head5 = createNode(4, 8, 2);
        printAllList(head1);
//        printAllList(head2);
        printAllList(head3);

        // 1. 查找链表中心
//        ListNode midNode1 = findMidListNode(head1, false);
//        printAllList(midNode1);
//        midNode1 = findMidListNode(head1, true);
//        printAllList(midNode1);

        // 2. 链表长度
//        int size = getListSize(head1);
//        printStr("list size: " + size);

        // 3. 链表反转
//        ListNode reversNode1 = reversList(head1);
//        printAllList(reversNode1);
//        reversNode1 = reversList(null);
//        printAllList(reversNode1);
//        reversNode1 = reversList(head3);
//        printAllList(reversNode1);
//        ListNode reversNode1 =  reversList(head3, 1);
//        printAllList(reversNode1);
//        reversNode1 =  reversList(head4, 2);
//        printAllList(reversNode1);
//        reversNode1 =  reversList(head5, 3);
//        printAllList(reversNode1);
//        reversNode1 =  reversList(head3, 5);
//        printAllList(reversNode1);
//        reversNode1 =  reversList(head4, 2, 4);
//        printAllList(reversNode1);
//        reversNode1 =  reversList(head3, 2, 3);
//        printAllList(reversNode1);

        // 4. 链表拼接

        ListNode newHead = combineList(head1, head3);
        printAllList(newHead);

    }

    private ListNode findMidListNode(ListNode head, boolean isPreHead) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slowNoe = head;
        ListNode fastNode = head;
        if (isPreHead) {
            fastNode = head.next;
        }

        while(fastNode != null && fastNode.next != null) {
            fastNode = fastNode.next.next;
            slowNoe = slowNoe.next;
        }

        return slowNoe;
    }

    private int getListSize(ListNode head) {
        int size = 0;
        if (head == null) {
            return size;
        }

        ListNode tempNode = head;
        while (tempNode != null) {
            size++;
            tempNode = tempNode.next;
        }
        return size;
    }

    private ListNode reversList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode preNode = null;
        ListNode curNode = head;
        ListNode nextNode = null;

        while (curNode != null) {
            nextNode = curNode.next;

            curNode.next = preNode;

            preNode = curNode;
            curNode = nextNode;
        }

        return preNode;
    }

    /**
     * 已知 list 长度 大于等于 n
     */
    private ListNode reversList(ListNode head, int n) {
        if (n <= 1 || head == null || head.next == null) {
            return head;
        }

        ListNode preNode = null;
        ListNode curNode = head;
        ListNode nextNode = null;
        while(n > 0 && curNode != null) {
            n--;
            nextNode = curNode.next;

            curNode.next = preNode;

            preNode = curNode;
            curNode = nextNode;
        }

        head.next = curNode;
        return preNode;
    }

    /**
     * 已知链表 size 大于等于n, n 大于 m ，m 大于等于 1
     */
    private ListNode reversList(ListNode head, int m, int n) {
        ListNode preNode = null;
        ListNode nextNode = null;
        ListNode curNode = head;
        if (m == 1) {
            while(n > 0) {
                n--;
                nextNode = curNode.next;

                curNode.next = preNode;

                preNode = curNode;
                curNode = nextNode;
            }
            head.next = curNode;
            return preNode;
        }

        int temp = m;
        while(temp > 1) {
            temp--;
            preNode = curNode;
            curNode = curNode.next;
        }

        ListNode subPreNode = null;
        while(n > m) {
            n--;
            nextNode = curNode.next;

            curNode.next = subPreNode;

            subPreNode = curNode;
            curNode = nextNode;
        }

        preNode.next.next = nextNode;
        preNode.next = subPreNode;
        return head;
    }

    private ListNode combineList(ListNode head1, ListNode head2) {
        ListNode newHead = new ListNode();
        ListNode tailNode = new ListNode();

        ListNode tempNode = newHead;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                tempNode.next = head1;
                tailNode.next = head2;
                head1 = head1.next;
            } else {
                tempNode.next = head2;
                tailNode.next = head1;
                head2 = head2.next;
            }

            tempNode = tempNode.next;
        }

        if (tailNode.next == null) {
            return newHead.next;
        }

        tempNode.next = tailNode.next;
        return newHead.next;
    }

    private ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 找到中间节点 f 先走
        ListNode slowNose = head;
        ListNode fastNode = head.next;
        while (fastNode != null && fastNode.next != null) {
            slowNose = slowNose.next;
            fastNode = fastNode.next.next;
        }

        ListNode tempHead =  slowNose.next;
        slowNose.next = null;

        ListNode leftHead = sortList(head);
        ListNode rightHead = sortList(tempHead);

        return comList(leftHead, rightHead);
    }

    // 从小到大排列
    private ListNode comList(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0, null);

        ListNode tempList1 = l1;
        ListNode tempList2 = l2;

        ListNode temp = head;
        while(tempList1 != null && tempList2 != null) {
            if (tempList1.val > tempList2.val) {
                temp.next = tempList2;
                tempList2 = tempList2.next;
            } else {
                temp.next = tempList1;
                tempList1 = tempList1.next;
            }
            temp = temp.next;
        }

        if (tempList1 == null) {
            temp.next = tempList2;
        }

        if (tempList2 == null) {
            temp.next = tempList1;
        }
        return head.next;
    }

    private ListNode createNode(int maxNUm, int data0, int sp) {
        ListNode head = new ListNode(data0);

        ListNode tempHead = head;
        ListNode temp = null;
        int data = data0;
        for (int i = 1; i < maxNUm; i++) {
            data += sp;
            temp = new ListNode(data, null);
            tempHead.next = temp;
            tempHead = tempHead.next;
        }
        return head;
    }

    private void printAllList(ListNode head) {
        while (head != null) {
            Log.e(TAG, "val: " + head.val);
            head = head.next;
        }

        Log.e(TAG, "down ...");
    }

    private void printStr(String s) {
        Log.e(TAG, "str: " + s);
    }

    private ListNode revalList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode preNode = null;
        ListNode curNode = head;
        ListNode nextNode = null;

        while(curNode != null) {
            nextNode = curNode.next;

            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }

        return preNode;
    }

    private ListNode revalList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode tail = revalList2(head.next);
        head.next.next = head;
        head.next = null;
        return tail;
    }

    @Test
    public void runTest2() {
        ListNode head = createNode(4, 0, 1);
        head.next.next.val = 1;
        head.next.next.next.val = 0;

//        ListNode head2 = createNode(3, 0, 1);
//        printStr("size: " + getListSize(head));
//        printStr("size: " + getListSize(null));

        ListNode midNode = getMidNode(head);
        printStr("mid val:" + midNode.val);
//        midNode = getMidNode(head2);
//        printStr("mid val:" + midNode.val);

        ListNode tempNode = head;

        ListNode headR = reverList(midNode);
        ListNode temp1Node = headR;

        while (temp1Node != null) {
            if (temp1Node.val != tempNode.val) {
                printStr("val: false");
            }
            tempNode = tempNode.next;
            temp1Node = temp1Node.next;
        }
        printStr("val: true");
//        reverList(headR);
        printAllList(head);
    }

    private ListNode reverList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode preNode = null;
        ListNode curNode = head;
        ListNode nextNode = null;
        while(curNode != null) {
            nextNode = curNode.next;

            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return preNode;
    }

    private ListNode getMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slowNode = head;
        ListNode fastNode = head;

        while(fastNode != null && fastNode.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        return slowNode;
    }

    @Test
    public void runTest3() {
        String str = "cdjdcdjdkd12j3caba";
        printStr("size: " + str.length());

//        printStr("max sub size: " + getMaxSubSize(str));
        printStr("max sub2 size: " + getMaxSubSize2(str));
    }

    private int getMaxSubSize(String str) {
        int size = str.length();
        if (size < 2) {
            return size;
        }

        int maxIndex = size - 1;
        int maxSize = 1;
        for (int charIndexPre = 0; charIndexPre < maxIndex; charIndexPre++) {
            char charPre = str.charAt(charIndexPre);
            for (int charIndexNext = charIndexPre + 1; charIndexNext < size; charIndexNext++) {
                if (charPre == str.charAt(charIndexNext)) {
                    String subStr = str.substring(charIndexPre + 1, charIndexNext);
                    printStr("sub str: " + subStr);
                    if (isSubStr(subStr) && subStr.length() + 2 > maxSize) {
                        maxSize = subStr.length() + 2;
                        maxIndex = size - maxSize;
                    }
                }
            }
        }
        return maxSize;
    }

    private Boolean isSubStr(String subStr) {
        int size = subStr.length();
        if (size < 2) {
            return true;
        }

        int midIndex = (size + 1) / 2;
        for (int i = 0, j = size - 1; i < midIndex; i++, j--) {
            if (subStr.charAt(i) != subStr.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    private int MaxSize = 1;
    private int getMaxSubSize2(String str) {
        int size = str.length();
        printStr("sub size: " + size);
        if (size < 2) {
            return size;
        }
        if (size <= MaxSize) {
            return MaxSize;
        }
        if (isSubStr(str)) {
            MaxSize = size;
            return MaxSize;
        }

        int sizeLeft = getMaxSubSize2(str.substring(1, size));
        int sizeRight = getMaxSubSize2(str.substring(0, size - 1));
        MaxSize = Math.max(sizeLeft, sizeRight);
        return MaxSize;
    }
}
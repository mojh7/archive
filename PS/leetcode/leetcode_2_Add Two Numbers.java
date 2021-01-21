/*
 * 2020-10-21
 * https://leetcode.com/problems/add-two-numbers/
 *
 * leetcode 첫 문제 풀이
 * 
 * 주어진 싱글 링크드 리스트 l1과 l2를 각각 int형으로 만들어서 더하고 값을 일의 자리 부터
 * 리턴할 싱글 링크드 리스트에 add해서 반환하는 문제인가 했으나 링크드 리스트의 범위가 1 ~ 100인 것을 확인
 * long을 써도 초과가 되므로 l1과 l2를 int형이나 long이나 하나의 변수에 담아서 + 연산을 하는 것이 아닌
 * l1과 l2 node를 순차적으로 탐색해서 리턴할 싱글 링크드 리스트에 add 해야됨. + 연산시 받아올림인
 * carry가 발생하는 경우를 생각해서 짜면 됨.

 * 문제 풀이시 주어진 ListNode class를 감싼 주석을 제거하면 에러가 발생함
 * 릿코드에서 푸는 방법 자체가 코드 제출 시 ListNode가 이미 만들어진 상태에서 유저가 추가로 제출한
 * 풀이코드를 합쳐서 돌리게 되므로 에러가 발생했다. 주석이 감싸진 ListNode class는 건들이지 않고
 * 참고하여 문제를 풀면 된다.
 *
 * l1 == null 같은 연산에서는 에러가 발생하지 않았는데 비슷한 조건문에서 null 에러가 발생했다.
 * 알고보니 l1 = l1.next; 나 l1.next != null 이런 경우에 발생했는데 코딩했던 구조상
 * l1 = null 이 들어가게되면 l1.next를 참조 할 수 없기에 에러가 발생했다.
 */

// Definition for singly-linked list.
/*
public class ListNode {
    int val;
    ListNode next;
    ListNode() {
    }
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val; this.next = next;
    }
}
*/

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode curNode = new ListNode();
        ListNode head = curNode;
        
        int carrying = 0;
        int addedValue;
        
        while(l1 != null || l2 != null || carrying == 1) {
            curNode.next = new ListNode();
            curNode = curNode.next;
            
            addedValue = 0;
            if(l1 != null) {
                addedValue += l1.val;
                l1 = l1.next;
            }
            if(l2 != null) {
                addedValue += l2.val;
                l2 = l2.next;
            }
            addedValue += carrying;
            carrying = addedValue / 10;
            curNode.val = addedValue % 10;
        }
        
        return head.next;
    }
}
/*
 * 2020-10-22
 * https://leetcode.com/problems/trim-a-binary-search-tree/
 *
 * Time Limit Exceeded가 발생했는데 입력으로 주어진 root를 순회하다가
 * node의 val값이 low <= val <= high일 경우 리턴할 트리인 retRoot에 addNode를 할 때
 * node를 처음 추가할 때, 즉 retRoot가 null일 때 root에 node를 새로 만들고 addNode 함수를
 * 빠져 나오도록 해야되는데 코드 수정시 return을 뺐어서 그 다음 현재 node의 val값과 비교하여
 * 탐색하는 while문에서 무한 루프가 걸려서 에러가 발생했었다.
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
    TreeNode retRoot;
    
    public TreeNode trimBST(TreeNode root, int low, int high) {
        retRoot = null;
        preOrderTraverse(root, low, high);
        return retRoot;
    }
    
    public void preOrderTraverse(TreeNode curr, int low, int high) {
        if(low <= curr.val && curr.val <= high) {
            addNode(curr.val);
        }
        
        if(curr.left != null) {
            preOrderTraverse(curr.left, low, high);
        }
        if(curr.right != null) {
            preOrderTraverse(curr.right, low, high);
        }
    }
    
    public void addNode(int val) {
        if(retRoot == null) {
            retRoot = new TreeNode(val);
            return;
        }
        
        TreeNode curr = retRoot;
        while(true) {
            if(curr.val > val) {
                if(curr.left == null) {
                    curr.left = new TreeNode(val);
                    break;
                }
                curr = curr.left;
            } else if(curr.val < val) {
                if(curr.right == null) {
                    curr.right = new TreeNode(val);
                    break;
                }
                curr = curr.right;
            }
        }
    }
}
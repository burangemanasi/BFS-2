//993. Cousins in Binary Tree - https://leetcode.com/problems/cousins-in-binary-tree/description/
//Time Complexity: O(n)
//Space Complexity: 2* n/2 ~ O(n)

//BFS
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        //base case
        if(root == null){
            return false;
        }
        Queue<TreeNode> q = new LinkedList<>(); //ACTUAL QUEUE
        Queue<TreeNode> qp = new LinkedList<>(); //PARENT QUEUE
        q.add(root);
        qp.add(null);
        TreeNode xp = null;
        TreeNode yp = null;
        boolean x_found=false, y_found=false;

        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                TreeNode curr = q.poll();
                TreeNode curr_parent = qp.poll();
                //match curr values with x and y and store xp and yp
                if(curr.val == x){
                    x_found = true;
                    xp = curr_parent;
                }
                if(curr.val == y){
                    y_found = true;
                    yp = curr_parent;
                }
                //if curr has left and right, add to q and qp for further processing
                if(curr.left != null){
                    q.add(curr.left);
                    qp.add(curr);
                }
                if(curr.right != null){
                    q.add(curr.right);
                    qp.add(curr);
                }
            }
            //if both x and y found, parents shouldn't be the same
            if(x_found && y_found) return xp != yp;
            //either of x and y found, return false
            if(x_found || y_found) return false;
        }
        return true;
    }
}

//BFS - Single Queue
//Time Complexity: O(n)
//Space Complexity: n/2 ~ O(n)

class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        //base case
        if(root == null){
            return false;
        }
        //actual queue
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        boolean x_found=false, y_found=false;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                TreeNode curr = q.poll();
                //match curr values with x and y
                if(curr.val == x){
                    x_found = true;
                }
                if(curr.val == y){
                    y_found = true;
                }
                //check if x and y are siblings and return false
                if(curr.left != null && curr.right != null){
                    if(curr.left.val == x && curr.right.val == y) return false;
                    if(curr.left.val == y && curr.right.val == x) return false;
                }
                //add left and right for further processing
                if(curr.left != null){
                    q.add(curr.left);
                }
                if(curr.right != null){
                    q.add(curr.right);
                }
            }
            //if both x and y found at the same level and are not siblings -> true
            if(x_found && y_found) return true;
            //if either x or y found at the same level -> false
            if(x_found || y_found) return false;
        }
        return true;
    }
}

//DFS
class Solution {
    int x_level;
    int y_level;
    TreeNode xp; //X_PARENT
    TreeNode yp; //Y_PARENT
    public boolean isCousins(TreeNode root, int x, int y) {
        this.x_level = -1;
        this.y_level = -1;
        this.xp = null;
        this.yp = null;

        helper(root, x, y, 0, null);

        return x_level == y_level && xp != yp;
    }

    private void helper(TreeNode root, int x, int y, int depth, TreeNode parent){
        //base case
        if(root == null){
            return;
        }
        //if x found, update level and parent
        if(root.val == x){
            x_level = depth;
            xp = parent;
        }
        //if y found, update level and parent
        if(root.val == y){
            y_level = depth;
            yp = parent;
        }
        //recurse on left and right child
        helper(root.left, x, y, depth+1, root);
        helper(root.right, x, y, depth+1, root);
    }
}
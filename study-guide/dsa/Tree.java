import java.util.LinkedList;
import java.util.Queue;

public class Tree {
    public TreeNode root;

    public Tree(TreeNode root) {
        this.root = root;
    }

    public void dfs(TreeNode cur) {
        System.out.println(cur.data + " ");
        for (TreeNode child : cur.children) {
            dfs(child);
        }
    }

    public void bfs() {
        TreeNode cur = this.root;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(cur);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.println(cur.data + " ");
            queue.addAll(cur.children);
        }
    }

}

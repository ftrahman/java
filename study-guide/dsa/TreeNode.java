import java.util.ArrayList;

public class TreeNode {
    public Object data;
    public ArrayList<TreeNode> children;

    public TreeNode(Object data) {
        this.children = new ArrayList<TreeNode>();
        this.data = data;
    }

    public void addChild(Object data) {
        this.children.add(new TreeNode(data));
    }

    public void addChild(TreeNode child) {
        this.children.add(child);
    }

    public void removeChild(TreeNode child) {
        if (this.children.isEmpty())
            return;
        else if (this.children.contains(child)) {
            this.children.remove(child);
        } else {
            for (TreeNode cur : this.children) {
                cur.removeChild(child);
            }
        }
    }

    public void removeChild(Object data) {
        if (this.children.isEmpty())
            return;
        for (TreeNode cur : this.children) {
            if (cur.data == data) {
                this.children.remove(cur);
                return;
            }
        }
        for (TreeNode cur : this.children) {
            cur.removeChild(data);
        }
    }
}

public class BST {
    public BST left;
    public BST right;
    public int data;
    public int depth;

    public BST(int data, int depth) {
        this.left = null;
        this.right = null;
        this.data = data;
        this.depth = depth;
    }

    public BST(int data) {
        this.left = null;
        this.right = null;
        this.data = data;
        this.depth = 0;
    }

    public void insert(int data) {
        if (this.data > data) {
            if (this.left == null) {
                this.left = new BST(data, this.depth + 1);
            } else {
                this.left.insert(data);
            }
        } else {
            if (this.right == null) {
                this.right = new BST(data, this.depth + 1);
            } else {
                this.right.insert(data);
            }
        }
    }

    public BST getNodeByValue(int data) {
        if (this.data == data) {
            return this;
        } else if (this.data > data && this.left != null) {
            return this.left.getNodeByValue(data);
        } else if (this.data < data && this.right != null) {
            return this.right.getNodeByValue(data);
        }
        return null;
    }

}

package tree;

class TreeNode{
    int data;
    TreeNode left, right;

    public TreeNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    @Override
    public String toString() {
        return ""+ data + "";
    }
}
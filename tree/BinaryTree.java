package tree;


import java.util.*;

class  BinaryTree {
    private TreeNode root;
    public BinaryTree(){

    }
    public BinaryTree(int key) {
        root = new TreeNode(key);
    }


    public void printAllPaths(){
        printAllPaths(root, new StringBuilder());
    }

    public void _printAllPaths(){
        printAllPaths(root,new java.util.LinkedList<>());
    }
    private void printAllPaths(TreeNode root, java.util.LinkedList<TreeNode> paths){
        if(root == null){
            return;
        }
        paths.add(root);
        if(root.left ==null && root.right ==null){
            System.out.println(paths);
        }
        if(root.left!=null){
            printAllPaths(root.left, paths);
            paths.removeLast();
        }
        if(root.right!=null){
            printAllPaths(root.right, paths);
            paths.removeLast();
        }
    }
    private void printAllPaths(TreeNode root, StringBuilder paths){
        if(root == null){
            return;
        }
        paths.append(root).append(",");
        if(root.left ==null && root.right ==null){
            System.out.println(paths.substring(0,paths.lastIndexOf(",")));
        }

        if(root.left!=null){
            printAllPaths(root.left, paths);
            paths.deleteCharAt(paths.length()-1);
            int st = paths.lastIndexOf(",");
            paths.setLength(st);
            paths.append(",");
        }
        if(root.right!=null){
            printAllPaths(root.right, paths);
            paths.deleteCharAt(paths.length()-1);
            int st = paths.lastIndexOf(",") - 1;
            paths.setLength(st);
            paths.append(",");
        }
    }
    public void levelOrderTraversal(){
        levelOrderTraversal(root);
    }
    private void levelOrderTraversal(TreeNode root){
        Queue<TreeNode> q = new ArrayDeque<>();
        if(root == null) return;
        int current = 1;
        int next =0;
        q.add(root);
        while(!q.isEmpty()){
            TreeNode temp = q.remove();
            current--;
            if(temp!=null){
                System.out.print(temp.data+" ");
                if(temp.left!=null){
                    next++;
                    q.add(temp.left);
                }
                if(temp.right!=null){
                    next++;
                    q.add(temp.right);
                }
            }

            if(current == 0){
                int swap = current;
                current = next;
                next = swap;
                System.out.println();
            }
        }
    }

    public void reverseLevelOrderTraversal(){
        reverseLevelOrderTraversal(root);
    }

    private void reverseLevelOrderTraversal(TreeNode root){
        Queue<TreeNode> q = new ArrayDeque<>();
        Stack<String> st = new Stack<>();
        StringBuilder builder = new StringBuilder();
        if(root == null) return;
        int current = 1;
        int next =0;
        q.add(root);
        while(!q.isEmpty()){
            TreeNode temp = q.remove();
            current--;
            if(temp!=null){
                builder.append(temp.data+" ");
                if(temp.left!=null){
                    next++;
                    q.add(temp.left);
                }
                if(temp.right!=null){
                    next++;
                    q.add(temp.right);
                }
            }

            if(current == 0){
                int swap = current;
                current = next;
                next = swap;
                st.push(builder.toString());
                builder.setLength(0);
            }
        }

        while(!st.empty()){
            System.out.println(st.pop());
        }
    }

    public int distance(int K1, int K2){
        TreeNode lca = lca(root, K1, K2);
        return 0;
    }

    public void verticalSum(){
        Map<Integer,Data> hash = new LinkedHashMap<>();
        verticalSum(root, 0, hash);
        for(Integer key : hash.keySet() ){
            System.out.println("hashKey="+key+"::"+ hash.get(key));
        }
        System.out.println();
    }

    private void verticalSum(TreeNode root, int k, Map<Integer,Data> hash){
        if(root!=null){
            verticalSum(root.left, k-1, hash);
            if(hash.get(k) == null){
                Data d = new Data();
                d.sum = root.data;
                d.keys= root.data+",";
                hash.put(k, d);

            }else{
                Data d = hash.get(k);
                d.sum+=root.data;
                d.keys+=root.data+",";
                hash.put(k, d);
            }
            verticalSum(root.right, k+1, hash);
        }
    }

    public void printInRange(int p, int q){
        printInRange(root, p, q);
    }
    private void printInRange(TreeNode root,int p, int q){
        if(root == null) return;
        if(root.data >= p && root.data <=q){
            System.out.print(root.data+ " ");
        }
        printInRange(root.left, p, q);
        printInRange(root.right, p, q);
    }
    public boolean isBinaryTree(){
        return isBinaryTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private boolean isBinaryTree(TreeNode root, int min, int max){
        if(root ==null){
            return true;
        }else if(root.data > min && root.data < max &&
                isBinaryTree(root.left,min, root.data) &&
                isBinaryTree(root.right, root.data, max)){
            return true;
        }else
            return false;
    }
    public int height(){
        return height(root);
    }
    private int height(TreeNode root){
        if(root == null){
            return 0;
        }else {
            int lHeight = height(root.left);
            int rHeight = height(root.right);
            return 1 + Math.max(lHeight, rHeight);
        }
    }
    public void insert(int key){
        if(root == null){
            root = new TreeNode(key);
            return;
        }else{
            TreeNode node = root;
            TreeNode parent = null;
            while(node!=null){
                parent = node;
                if(key < node.data){
                    node = node.left;
                }else if(key > node.data){
                    node = node.right;
                }
            }
            TreeNode temp = new TreeNode(key);
            if(key < parent.data ){
                parent.left = temp;
            }else{
                parent.right = temp;
            }
        }
    }
    public TreeNode search(int key){
        TreeNode temp = root;
        while(temp != null){
            if(temp.data == key){
                return temp;
            }else if(key < temp.data){
                temp = temp.left;
            }else{
                temp = temp.right;
            }
        }
        return temp;
    }
    public TreeNode minimum(){
        return minimum(root);
    }
    public TreeNode maximum(){
        return maximum(root);
    }
    private TreeNode minimum(TreeNode node){
        TreeNode temp = node;
        while(temp.left != null){
            temp = temp.left;
        }
        return temp;
    }
    public TreeNode maximum(TreeNode node){
        TreeNode temp = node;
        while(temp.right != null){
            temp = temp.right;
        }
        return temp;
    }
    public int lca(int p, int q){
        TreeNode lca = lca(root, p, q);
        if (lca == null){
            return -999;
        }else
            return lca.data;
    }
    private TreeNode lca(TreeNode root, int p, int q){
        if(root == null) return null;
        if(Math.max(p,q) < root.data){
            return lca(root.left, p,q);
        }else if(Math.min(p,q) > root.data){
            return lca(root.right, p,q);
        }else{
            return root;
        }
    }
    public TreeNode inorderSuccessor(int key){
        TreeNode node = search(key);
        if(node.right != null){
            return minimum(node.right);
        }else{
            TreeNode successor = null;
            TreeNode _root = root;
            while(_root!=null){
                if(node.data < _root.data){
                    successor = _root;
                    _root = _root.left;
                }else if(node.data > _root.data){
                    _root = _root.right;
                }else
                    break;
            }
            if( node.data > successor.data)
                return successor;
            else{
                return null;
            }
        }
    }
    public void delete(int key){
        root = _delete(root, key);
    }
    private TreeNode _delete(TreeNode root, int key) {
        if (root == null)  return root; //empty tree
        if (key < root.data)  // go left
            root.left = _delete(root.left, key);
        else if (key > root.data) // go right
            root.right = _delete(root.right, key);
        else{
            if (root.left == null) // only right child
                return root.right;
            else if (root.right == null) //only left child
                return root.left;
            root.data = minimum(root.right).data; // when both the children are present
            root.right = _delete(root.right, root.data);
        }
        return root;
    }

    public void formattedTree(){
        int height = height();
        for(int i =0;i<height ; i++){

        }
    }

    public void build(int A[]){
        root = build(A, 0, A.length-1);
    }

    private TreeNode build(int A[], int low, int high){
        if(low > high) return null;
        int mid = (low + high)/2;
        TreeNode root = new TreeNode(A[mid]);
        root.left  =  build(A,low, mid - 1);
        root.right = build(A,mid + 1, high);
        return root;
    }
    public void clear(){
        root = null;
    }
    public void preorder(boolean iterative){
        if(iterative)
            iterative_preoorder(root);
        else{
            System.out.println("Recursive");
            preorder(root);
        }
        System.out.println();
    }
    private void iterative_preoorder(TreeNode root){
        System.out.println("Iterative");
        if(root==null) return;
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while(!st.empty()){
            TreeNode node = st.pop();
            System.out.print(node.data+" ");
            if(node.right!=null){
                st.push(node.right);
            }
            if(node.left!=null){
                st.push(node.left);
            }
        }
    }
    private void preorder(TreeNode root){
        if(root!=null){
            System.out.print(root.data + ",");
            preorder(root.left);
            preorder(root.right);
        }
    }
    public void inorder(boolean iterative){
        if(iterative){
            iterative_inorder(root);
        }else
            _inorder(root);
        System.out.println();
    }
    private void _inorder(TreeNode root) {
        if(root!=null){
            _inorder(root.left);
            System.out.print(root.data + " ");
            _inorder(root.right);
        }
    }
    private void iterative_inorder(TreeNode root){
        if(root == null) return;
        Stack<TreeNode> st = new Stack<>();
        TreeNode temp = root;
        while(temp!=null){
            st.push(temp);
            temp = temp.left;
        }
        while(!st.isEmpty()){
            TreeNode temp1 = st.pop();
            System.out.print(temp1.data+ " ");
            if(temp1.right!=null){
                temp1 = temp1.right;
                while(temp1!=null){
                    st.push(temp1);
                    temp1 = temp1.left;
                }
            }

        }
    }

    public void postorder(boolean iterative){
        if(iterative){
            postorder(root);
        }else
            iterative_postorder(root);
        System.out.println();
    }
    private void postorder(TreeNode root){
        if(root!=null){
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    private void iterative_postorder(TreeNode root) {
        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        st1.push(root);
        while(!st1.empty()){
            TreeNode node = st1.pop();
            st2.push(node);
            if(node.left!=null){
                st1.push(node.left);
            }
            if(node.right!=null){
                st1.push(node.right);
            }
        }

        while(!st2.empty()){
            System.out.print(st2.pop().data + " ");
        }
    }


    public void mirrorTree(){
        mirrorTree(root);
    }

    private void mirrorTree(TreeNode root) {
        if (root == null) return;
        TreeNode temp;
        temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);
    }

    public void printNodesAtKDistance(int k){
        printNodesAtKDistance(root, k);
    }

    private void printNodesAtKDistance(TreeNode root, int k){
        if(root == null) return;
        if(k == 0){
            System.out.print(root.data+ " ");
            return;
        }
        printNodesAtKDistance(root.left,  k-1);
        printNodesAtKDistance(root.right, k-1);
    }

    public boolean compareTrees(BinaryTree other){
        return compareTrees(root, other.root);
    }

    private boolean compareTrees(TreeNode N1, TreeNode N2){
        if(N1 == null && N2 == null) return true;
        if(N1 == null || N2 == null) return false;
        return N1.data == N2.data  &&
                compareTrees(N1.left, N2.left) &&
                compareTrees(N1.right, N2.right);
    }

    public int countLeafNodes(){
        return countLeafNodes(root);
    }

    private int countLeafNodes(TreeNode root){
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        return countLeafNodes(root.left) + countLeafNodes(root.right);
    }

    public int sumOfAllNodes(){
        return sumOfAllNodes(root);
    }

    private int sumOfAllNodes(TreeNode root){
        if(root == null) return 0;
        return root.data + sumOfAllNodes(root.left) + sumOfAllNodes(root.right);
    }

    public int oddEvenDiff(){
        return oddEvenDiff(root);
    }

    private int oddEvenDiff(TreeNode root){
        if(root == null) return 0;
        if(root.data %2 ==0){
            return oddEvenDiff(root.left)+oddEvenDiff(root.right) - root.data;
        }else{
            return oddEvenDiff(root.left)+oddEvenDiff(root.right) + root.data;
        }
    }
    public boolean areMirrorTrees(BinaryTree other){
        return areMirrorTrees(root, other.root);
    }

    private boolean areMirrorTrees(TreeNode root, TreeNode other) {
        if(root == null && other == null) return true;
        if(root == null || other == null) return false;
        return root.left == other.right && root.right == other.left
                && areMirrorTrees(root.left,other.right)
                && areMirrorTrees(root.right, other.left);
    }

    public void _levelOrderTraversal(){
        _levelOrderTraversal(root);
    }

    private void _levelOrderTraversal(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        if(root == null) return;
        queue.add(root);
        while(true){
            int count = queue.size();
            if(queue.size() == 0){
                break;
            }
            while(count > 0){
                TreeNode temp = queue.remove();
                System.out.print(temp.data+ "  ");
                if(temp.left!=null){
                    queue.add(temp.left);
                }
                if(temp.right!=null){
                    queue.add(temp.right);
                }
                count--;
            }
            System.out.println();
        }
    }

    public BinaryTree constructTreeFromInPre(int I[], int P[]){
        TreeNode root = constructTreeFromInPre(I, P, 0,I.length-1,0);
        BinaryTree answer = new BinaryTree();
        answer.root = root;
        return answer;
    }
    private TreeNode constructTreeFromInPre(int[] I, int[] P, int S, int E, Integer preIdx) {
        if(S > E) return null;
        TreeNode root = new TreeNode(P[preIdx++]);
        if( S == E) return root;
        int idx = search(I,S, E, root.data);
        root.left = constructTreeFromInPre( I,  P, S, idx - 1, preIdx);
        root.right = constructTreeFromInPre(I,  P, idx + 1, E, preIdx);
        return root;
    }

    private int search(int[] inorder, int S, int E, int data) {
        for(int i = S;i<=E;i++){
            if(inorder[i] ==  data){
                return i;
            }
        }
        return -1;
    }

}


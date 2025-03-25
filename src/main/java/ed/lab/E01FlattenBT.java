package ed.lab;

public class E01FlattenBT {

    public void flatten(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            flatten(root.left);

            TreeNode<Integer> aux = root.right;
            root.right = root.left;
            root.left = null;

            TreeNode<Integer> current = root.right;
            while (current.right != null) {
                current = current.right;
            }

            current.right = aux;
        }

        flatten(root.right);
    }
}

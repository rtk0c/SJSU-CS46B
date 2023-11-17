package trees;

import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class FamilyTree {
    private static class TreeNode {
        private String name;
        private TreeNode parent;
        private ArrayList<TreeNode> children;

        TreeNode(String name) {
            this.name = name;
            children = new ArrayList<>();
        }

        String getName() {
            return name;
        }

        void addChild(TreeNode childNode) {
            // Add childNode to this node's children list. Also
            // set childNode's parent to this node.
            children.add(childNode);
            childNode.parent = this;
        }


        // Searches subtree at this node for a node
        // with the given name. Returns the node, or null if not found.
        TreeNode getNodeWithName(String targetName) {
            // Does this node have the target name?
            if (name.equals(targetName))
                return this;

            // No, recurse. Check all children of this node.
            for (TreeNode child : children) {
                // If child.getNodeWithName(targetName) returns a non-null node,
                // then that's the node we're looking for. Return it.
                TreeNode res = child.getNodeWithName(targetName);
                if (res != null) return res;
            }

            // Not found anywhere.
            return null;
        }

        // Returns a list of ancestors of this TreeNode, starting with this node’s parent and
        // ending with the root. Order is from recent to ancient.
        ArrayList<TreeNode> collectAncestorsToList() {
            var ancestors = new ArrayList<TreeNode>();

            // Collect ancestors of this TreeNode into the array list. HINT: going up
            // the nodes of a tree is like traversing a linked list. If that isn’t clear,
            // draw a tree, mark any leaf node, and then mark its ancestors in order from
            // recent to ancient. Expect a question about this on the final exam.

            TreeNode curr = parent;
            while (curr != null) {
                ancestors.add(curr);
                curr = curr.parent;
            }

            return ancestors;
        }

        public String toString() {
            return toStringWithIndent("");
        }

        private String toStringWithIndent(String indent) {
            var s = new StringBuilder(indent + name + "\n");
            indent += "  ";
            for (TreeNode childNode : children)
                s.append(childNode.toStringWithIndent(indent));
            return s.toString();
        }
    }

    private TreeNode root;

    //
    // Displays a file browser so that user can select the family tree file.
    //
    public FamilyTree() throws IOException, TreeException {
        // User chooses input file. This block doesn't need any work.
        FileNameExtensionFilter filter =
                new FileNameExtensionFilter("Family tree text files", "txt");
        File dirf = new File("data");
        if (!dirf.exists())
            dirf = new File(".");
        JFileChooser chooser = new JFileChooser(dirf);
        chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
            System.exit(1);
        File treeFile = chooser.getSelectedFile();

        // Parse the input file. Create a FileReader that reads treeFile. Create a BufferedReader
        // that reads from the FileReader.
        try (var fr = new FileReader(treeFile);
             var br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null)
                addLine(line);
        }
    }


    //
    // Line format is "parent:child1,child2 ..."
    // Throws TreeException if line is illegal.
    //
    private void addLine(String line) throws TreeException {
        // Extract parent and array of children.
        int colonIndex = line.indexOf(':');
        if (colonIndex < 0)
            throw new TreeException("Invalid line format: expected colon, found none");
        // The substring of line that starts at char #0 and ends just before colonIndex.Check the API for class java.util.String, method substring(),if you need guidance.
        String parent = line.substring(0, colonIndex);
        // The substring of line that starts just after colonIndex and goes through the end of the line. You'll use a different version of substring().
        String childrenString = line.substring(colonIndex + 1);
        // Call childrenString.split(). Check the API for details. The result will be an array of strings, with the separating commas thrown away.
        String[] childrenArray = childrenString.split(",");

        // Find parent node. If root is null then the tree is empty and the
        // parent node must be constructed. Otherwise, the parent node should be
        // somewhere in the tree.
        TreeNode parentNode;
        if (root == null)
            parentNode = root = new TreeNode(parent);
        else {
            parentNode = root.getNodeWithName(parent);
            if (parentNode == null)
                // If the parent node wasn't found, there must have been something wrong in the  data file. Throw an exception.
                throw new TreeException("Invalid format: a nonexistent parent node '" + parent + "' is referenced");
        }

        // Add child nodes to parentNode.
        for (String s : childrenArray) {
            parentNode.addChild(new TreeNode(s));
        }
    }


    // Returns the "deepest" node that is an ancestor of the node named name1, and also is an
    // ancestor of the node named name2.
    //
    // "Depth" of a node is the "distance" between that node and the root. The depth of the root is 0. The
    // depth of the root's immediate children is 1, and so on.
    //
    TreeNode getMostRecentCommonAncestor(String name1, String name2) throws TreeException {
        // Get nodes for input names.
        TreeNode node1 = root.getNodeWithName(name1);
        if (node1 == null)
            throw new TreeException("'" + name1 + "' not found within the family tree.");
        TreeNode node2 = root.getNodeWithName(name2);
        if (node2 == null)
            throw new TreeException("'" + name2 + "' not found within the family tree.");

        // Get ancestors of node1 and node2.
        var ancestorsOf1 = node1.collectAncestorsToList();
        var ancestorsOf2 = node2.collectAncestorsToList();

        /*
        // Check members of ancestorsOf1 in order until you find a node that is also
        // an ancestor of 2.
        for (TreeNode n1 : ancestorsOf1)
            if (ancestorsOf2.contains(n1))
                return n1;
        // No common ancestor.
        return null;
        */
        // Optimized version:
        // Since the tree only has one root, any two nodes must share a non-empty common initial sequence of ancestor nodes
        // (in this case, from the back of each ArrayList, since TreeNode#collectAncestorsToList() produces the list "closest first")
        // Then the common ancestor between node1 and node2 with the largest depth must the last item in the common initial sequence
        TreeNode lastCIS = null; // _C_ommon _I_nitial _S_equence
        // Iterate both arrays backwards
        for (int i1 = ancestorsOf1.size() - 1, i2 = ancestorsOf2.size() - 1;
             i1 >= 0 && i2 >= 0;
             --i1, --i2) {
            var cis1 = ancestorsOf1.get(i1);
            var cis2 = ancestorsOf2.get(i2);
            // Yes, we want reference equality here
            if (cis1 != cis2)
                break;
            lastCIS = cis1;
        }
        return lastCIS;
    }

    public String toString() {
        return "Family Tree:\n\n" + root;
    }

    public static void main(String[] args) {
        try {
            FamilyTree tree = new FamilyTree();
            System.out.println("Tree:\n" + tree + "\n**************\n");
            TreeNode ancestor = tree.getMostRecentCommonAncestor("Bilbo", "Frodo");
            System.out.println("Most recent common ancestor of Bilbo and Frodo is " + ancestor.getName());
        } catch (IOException x) {
            System.out.println("IO trouble: " + x.getMessage());
        } catch (TreeException x) {
            System.out.println("Input file trouble: " + x.getMessage());
        }
    }
}

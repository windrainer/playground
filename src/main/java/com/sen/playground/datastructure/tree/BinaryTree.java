package com.sen.playground.datastructure.tree;

import java.util.LinkedList;
import java.util.Stack;

public class BinaryTree {
    private Node root = null;

    BinaryTree(int value) {
        root = new Node(value);
        root.leftChild = null;
        root.rightChild = null;
    }

    /**
     * Find a node with specified value
     *
     * @param value
     * @return
     */
    public Node findKey(int value) {
        Node current = root;

        while (true) {
            if (value == current.value) {
                return current;
            } else if (value < current.value) {
                current = current.leftChild;
            } else if (value > current.value) {
                current = current.rightChild;
            }
            if (current == null) {
                return null;
            }
        }
    }

    /**
     * Insert a node
     */
    public Node insert(int value) {

        Node node = new Node(value);
        if (root == null) {
            root = node;
            root.leftChild = null;
            root.rightChild = null;
            return root;
        } else {
            Node current = root;
            Node parent = null;

            while (true) {
                if (value < current.value) {
                    parent = current;
                    current = current.leftChild;
                    if (null == current) {
                        parent.leftChild = new Node(value);
                        return parent.leftChild;
                    }
                } else if (value > current.value) {
                    parent = current;
                    current = current.rightChild;
                    if (null == current) {
                        parent.rightChild = new Node(value);
                        return parent.rightChild;
                    }
                } else {
                    System.out.println("Found node with same value, abort");
                    return null;
                }
            }
        }
    }

    /**
     * Recursive implementation of binary tree traversal
     */
    public void inOrderTraverse() {
        System.out.print("LDR(recursive):");
        inOrderTraverse(root);
        System.out.println();

    }

    private void inOrderTraverse(Node node) {
        if (null == node) {
            return;
        }
        inOrderTraverse(node.leftChild);
        node.display();
        inOrderTraverse(node.rightChild);
    }

    /**
     * LDR non recursive implementation
     * 1) for any current node,if it's not null, it's pushed into stack. Then the left child is assigned to current.
     * Repeat this procedure unil current becomes null.
     * <p>
     * 2) if left child is null, pop node from stack, after display the node value, assign the right child to current.
     * <p>
     * 3) repeat step 1 and 2 until current is null and stack becomes empty.
     */
    public void inOrderByStack() {

        System.out.print("LDR(non recursive):");

        Stack<Node> stack = new Stack<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.leftChild;
            }

            if (!stack.isEmpty()) {
                current = stack.pop();
                current.display();
                current = current.rightChild;
            }
        }
        System.out.println();

    }

    /**
     * recursive implementation of DLR
     */
    public void preOrderTraverse() {
        System.out.print("DLR(recursive):");
        preOrderTraverse(root);
        System.out.println();
    }

    private void preOrderTraverse(Node node) {
        if (node == null) {
            return;
        }

        node.display();
        preOrderTraverse(node.leftChild);
        preOrderTraverse(node.rightChild);
    }

    /**
     * DLR non recursive
     * 1) for any one current node, if it is not null, display the value and push into stack, then assign leftchild as current
     * repeat this until current becomes null
     * <p>
     * 2) if leftchild is null, pop node from stack, assign the right child to current
     * <p>
     * 3) repeat step 1 and 2 until current is null and stack becomes empty
     */
    public void preOrderByStack() {
        System.out.print("LRD(non recursive):");
        Stack<Node> stack = new Stack<>();
        Node current = root;
        while (null != current || !stack.isEmpty()) {
            while (null != current) {
                current.display();
                stack.push(current);
                current = current.leftChild;
            }

            if (!stack.isEmpty()) {
                current = stack.pop();
                current = current.rightChild;
            }
        }

        System.out.println();
    }


    /**
     * LRD
     */
    public void postOrderTraverse() {
        System.out.print("RDL(recursive)");
        postOrderTraverse(root);
        System.out.println();
    }

    private void postOrderTraverse(Node node) {
        if (node == null)
            return;

        postOrderTraverse(node.leftChild);
        postOrderTraverse(node.rightChild);
        node.display();
    }

    /**
     * RDL non recursive
     * 1) declare current node as root, for any current node, push this node to stack, then assign it's leftchild to current
     * repeat until current reaches null
     * <p>
     * 2) if stack is not empty, peek node in stack,  assign its right child to current,
     * if its right child was scanned(last step was identified by preNode) or it's null, or the current is null, pop the node ,display it, assign to preNode
     */
    public void postOrderByStack() {
        System.out.print("RLD(Non recursive):");
        Stack<Node> stack = new Stack<>();
        Node current = root;
        Node preNode = null;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.leftChild;
            }

            if (!stack.isEmpty()) {
                current = stack.peek().rightChild;
                if (current == null || current == preNode) {
                    current = stack.pop();
                    current.display();
                    preNode = current;
                    current = null;
                }
            }
        }

        System.out.println();

    }

    public int getMinValue() {
        Node current = root;
        while(true) {
            if(current.leftChild == null) {
                return current.value;
            }

            current = current.leftChild;
        }
    }

    public boolean delete(int value) {
        Node current = root;
        Node parent = null;
        boolean isLeftChild = true;  //node to be deleted belongs to the left tree of its parent node

        while(true) {
            if(value == current.value) {
                break;
            } else if (value < current.value) {
                isLeftChild = true;
                parent = current;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                parent = current;
                current = current.rightChild;
            }

            if(current == null) {
                return false;
            }
        }

        //1) The node to be deleted is a leaf node
        if(current.leftChild == null && current.rightChild == null) {
            //if deleting root node
            if(current  == root) {
                root = null;
            } else {
                //if this leaf node is parent node's left child, make parent.leftChild null;
                if(isLeftChild) {
                    parent.leftChild=null;
                } else {
                    //if it's right child , make parent right child null
                    parent.rightChild =null;
                }
            }
        }
        //2) the node to be deleted has one left child
        else if( current.rightChild==null) {
            if(current == root) {
                root = current.leftChild;
            } else {
                //if delete node is parent node's left child, then attach the delete node's leftchild to parent node
                if(isLeftChild) {
                    parent.leftChild = current.leftChild;
                } else {
                    parent.rightChild = current.rightChild;
                }
            }
        }
        //3 ) the node to be deleted has one right child
        else if(current.leftChild == null) {
            if(current == root) {
                root = current.rightChild;
            } else {
                //if deleting node is parent node's left child, make the node's right child as parent's node's left child
                if(isLeftChild) {
                    parent.leftChild = current.rightChild;
                } else {
                    parent.rightChild = current.rightChild;
                }
            }
        }

        //4) the node to be deleted has two children, we need to find its successor (后续节点)

        else {
            Node successor = getSuccessor(current);
            //if the deleted node is root, then make successor the root
            if(current == root) {
                root = successor;
            } else {
                //if the deleted node is parent node's left child, make successor the left child of parent's
                if(isLeftChild) {
                    parent.leftChild = successor;
                } else {
                    parent.rightChild = successor;
                }
            }
        }

        current = null;
        return true;
    }
    /**
    //因为二叉搜索树是有序的，我们要找的节点在这棵树上，而且这个节点要比被删除节点的左节点大，比右节点小。
     在删除节点的右节点为根的子树上一支向左找，找到的最小节点就是后继节点
    */
    private Node getSuccessor(Node delNode) {
        Node successor = delNode;
        Node successorParent = null;
        Node current = delNode.rightChild;

        while(current!=null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }

        //if the successor is not deleted node's right child
        if(successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }

        //in any circumstances, we need to assign deleted node's leftChild to successor's leftchild
        successor.leftChild = delNode.leftChild;
        return successor;
    }

    /**
     * Breadth first traversal uses queue
     */
    public void BFTraversal() {
        LinkedList<Node> queue = new LinkedList<>();
        if(null!=root) {
            queue.addFirst(root);
            while(!queue.isEmpty());
                Node node = queue.pollLast();
                System.out.print(node.value + "\t");

                //if left tree is not null, add into queue
                if(node.leftChild!=null) {
                    queue.addFirst(node.leftChild);
                }

                if(node.rightChild!=null) {
                    queue.addFirst(node.rightChild);
                }
        }

    }

    /**
     * Depth first traversal uses stack
     */
    public void depthOrderTraversal() {
        if(root==null) {
            System.out.println("empty tree");
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.value + "\t");

            if(node.rightChild!=null) {
                stack.push(node.rightChild);
            }

            if(node.leftChild!=null) {
                stack.push(node.leftChild);
            }
        }

        System.out.println();
    }

}

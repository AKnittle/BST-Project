// The test harness will belong to the following package; the BST
// implementation will belong to it as well. In addition, the BST
// implementation will specify package access for the inner node class
// and all data members in order that the test harness may have access
// to them.
//

package Minor.P2.DS;

// BST<> provides a generic implementation of a binary search tree
//
// BST<> implementation constraints:
// - The tree uses package access for root, and for the node type.
// - The node type uses package access for its data members.
// - The tree never stores two objects for which compareTo() returns 0.
// - All tree traversals are performed recursively.
// - Optionally, the BST<> employs a pool of deleted nodes.
// If so, when an insertion is performed, a node from the pool is used
// unless the pool is empty, and when a deletion is performed, the
// (cleaned) deleted node is added to the pool, unless the pool is
// full. The maximum size of the pool is set via the constructor.
//
// User data type (T) constraints:
// - T implements compareTo() and equals() appropriately
// - compareTo() and equals() are consistent; that is, compareTo()
// returns 0 in exactly the same situations equals() returns true
// ALL METHOD NAMES AND MOST COMMENTS COME FROM CLASS MATERIAL.
// -------------------------------------------------------------------------
/**
 * A BST that uses comparable. NOTE: most code in here is being reused from the
 * 2114 lab based on BST
 *
 * @param <T>
 * @author AndrewK
 * @version Feb 8, 2015
 */
public class BST<T extends Comparable<? super T>>
{

    // -------------------------------------------------------------------------
    /**
     * A Class for the BinaryNode
     *
     * @author AndrewK/McQuain
     * @version Feb 8, 2015
     */
    class BinaryNode
    {
        /**
         * element of the node
         */
        T          element;
        /**
         * binary node on the left
         */
        BinaryNode left;
        /**
         * binary node on the right
         */
        BinaryNode right;


        // ----------------------------------------------------------
        /**
         * Create a new BinaryNode object with no children
         *
         * @param elem
         */
        // Initialize a childless binary node.
        // Pre: elem is not null
        // Post: (in the new node)
        // element == elem
        // left == right == null
        public BinaryNode(T elem)
        {
            element = elem;
            left = null;
            right = null;
        }


        // Initialize a binary node with children.
        // Pre: elem is not null
        // Post: (in the new node)
        // element == elem
        // left == lt, right == rt
        // ----------------------------------------------------------
        /**
         * Create a new BinaryNode object with filled in child nodes
         *
         * @param elem
         * @param lt
         * @param rt
         */
        public BinaryNode(T elem, BinaryNode lt, BinaryNode rt)
        {

            element = elem; // the data in the node
            left = lt; // pointer to the left child
            right = rt; // pointer to the right child
        }


        // ----------------------------------------------------------
        /**
         * Set this node's left child.
         *
         * @param value
         *            the node to point to as the left child.
         */
        public void setLeft(BinaryNode value)
        {
            // sets node to the left
            left = value;
        }


        // ----------------------------------------------------------
        /**
         * Set this node's right child.
         *
         * @param value
         *            the node to point to as the right child.
         */
        public void setRight(BinaryNode value)
        {
            // sets node to the right
            right = value;
        }
    }

    /**
     * pointer to root node, if present
     */
    BinaryNode root;
    /**
     * pointer to first node in the pool
     */
    BinaryNode pool;
    /**
     * size limit for node pool
     */
    int        pSize;
    /**
     * shows current size of pool without having to use traversals
     */
    int        currentPSize;


    // Initialize empty BST with no node pool.
    // Pre: none
    // Post: (in the new tree)
    // root == null, pool == null, pSize = 0
    // ----------------------------------------------------------
    /**
     * Create a new BST object with no root, pull, and a pSize of 0
     */
    public BST()
    {
        root = null;
        pool = null;
        pSize = 0;
        currentPSize = 0;
    }


    // Initialize empty BST with a node pool of up to pSize nodes.
    // Pre: none
    // Post: (in the new tree)
    // root == null, pool = null, pSize == Sz
    // ----------------------------------------------------------
    /**
     * Create a new BST object with a defined pSize
     *
     * @param Sz
     */
    public BST(int Sz)
    {
        root = null;
        pool = null;
        // TODO: initialize pool, add binary nodes to it. use a loop
        pSize = Sz;
        currentPSize = 0;
    }


    // ----------------------------------------------------------
    /**
     * Expands the pool as elements are removed from the tree
     *
     * @param newNode
     */
    public void poolExpand(BinaryNode newNode)
    {
        newNode.element = null;
        // check to see if the pool is empty
        if (pSize == 0)
        {
            // increase currentPSize
            currentPSize++;
            // make pool the new node
            pool = newNode;
            return;
        }
        // if the pool is at max capacity
        else if (pSize == currentPSize)
        {
            // set newNode's right to pool's right
            newNode.right = pool.right;
            // set pool to newNode, and let the old pool node be taken to the
            // garbage
            pool = newNode;
            return;
        }
        else
        {
            // gets the old pool node and set's it to the the new node's right
            newNode.right = pool;
            // now set the new node to be the new pool node
            pool = newNode;
            currentPSize++;
            return;
        }
    }


    // ----------------------------------------------------------
    /**
     * Restrict the node pool and return the top node
     *
     * @return old pool head node
     */
    public BinaryNode poolRestrict()
    {
        if (pSize == 0)
        {
            return null;
        }
        BinaryNode tempNode;
        // only one node in pool
        if (currentPSize == 1)
        {
            // set up the pointer
            tempNode = pool;
            // get rid of the pointer to the old pool node
            pool = null;
            // decrease size
            currentPSize--;
            return tempNode;
        }
        else
        {
            // set up pointer to the old pool node
            tempNode = pool;
            // move the pool pointer up by 1
            pool = pool.right;
            currentPSize--;
            return tempNode;
        }
    }


    // ----------------------------------------------------------
    /**
     * A recursive method that helps the find() method. Code taken from 2114 lab
     * This method was called from the find method, because the element was not
     * null. The tree will be searched through until either a match is found or
     * no match is found. If the match is found return the pointer to the
     * element, otherwise return null
     *
     * @param x
     * @param node
     * @return BinaryNode
     */
    public BinaryNode findHelper(T x, BinaryNode node)
    {
        if (node == null)
        {
            return null; // Not found
        }
        else if (x.compareTo(node.element) < 0)
        {
            // Search in the left subtree
            return findHelper(x, node.left);
        }
        else if (x.compareTo(node.element) > 0)
        {
            // Search in the right subtree
            return findHelper(x, node.right);
        }
        else
        {
            return node; // Match
        }
    }


    // ----------------------------------------------------------
    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param node
     *            the node that roots the tree.
     * @return node containing the smallest item.
     */
    private BinaryNode findMin(BinaryNode node)
    {
        // check to see if null or not
        if (node == null)
        {
            return node;
        }
        // check to see if at the end of the subtree
        else if (node.left != null && node.left.left == null)
        {
            // returns the min's parent's parent's node
            return node;
        }
        else
        {
            // recursive call
            return findMin(node.left);
        }
    }


    // ----------------------------------------------------------
    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param node
     *            the node that roots the tree.
     * @return node containing the largest item.
     */
    private BinaryNode findMax(BinaryNode node)
    {
        if (node == null)
        {
            // no match
            return node;
        }
        else if (node.right == null)
        {
            // returns the max node
            return node;
        }
        else
        {
            // recursive call
            return findMax(node.right);
        }
    }


    // ----------------------------------------------------------
    /**
     * Internal method to insert a value into a subtree.
     *
     * @param x
     *            the item to insert.
     * @param node
     *            the node that roots the subtree.
     * @return the new root of the subtree.
     * @throws RuntimeException
     */
    // exception
    public BinaryNode insertHelper(T x, BinaryNode node)
        throws RuntimeException
    {
        // if node is null than the root of the tree is null, or "the leaf" of
        // the parent node is null meaning a new node which will be the leaf
        // node can be made
        if (node == null)
        {
            // System.out.println("derp: " + x);
            // Empty location a new node can be made
            return new BinaryNode(x);
        }
        else if (x.compareTo(node.element) < 0)
        {
            // System.out.println("left: " + x);
            // x is less than
            node.setLeft(insertHelper(x, node.left));
        }
        else if (x.compareTo(node.element) > 0)
        {
            // System.out.println("right: " + x);
            // x is greater than
            node.setRight(insertHelper(x, node.right));
        }
        else
        {
            throw new RuntimeException();
        }
        // return node if exact match
        return node;
    }


    // Return true iff BST contains no nodes.
    // Pre: none
    // Post: the binary tree is unchanged
    // ----------------------------------------------------------
    /**
     * Shows if the BST is empty. A simple check to see if the root is null
     * should show if it is empty or not. If the root is null the tree must be
     * empty
     *
     * @return boolean
     */
    public boolean isEmpty()
    {
        return (root == null);
    }


    // Return pointer to matching data element, or null if no matching
    // element exists in the BST. "Matching" should be tested using the
    // data object's compareTo() method.
    // Pre: x is null or points to a valid object of type T
    // Post: the binary tree is unchanged
    // ----------------------------------------------------------
    /**
     * First check to see if the element being searched for is null or not. If
     * not use the findHelper method to do all the "heavy lifting" of the method
     *
     * @param x
     * @return T
     */
    public T find(T x)
    {
        // System.out.println("SEARCHING...");
        // possibly use a field to compare things to
        if (x == null)
        {
            // can't find a null value
            return null;
        }
        else
        {
            // Helper method to go through the tree
            BinaryNode temp = findHelper(x, root);
            if (temp != null)
            {
                // element found
                return temp.element;
            }
            // element could not be found
            return null;
        }
    }


    // Insert element x into BST, unless it is already stored. Return true
    // if insertion is performed and false otherwise.
    // Pre: x is null or points to a valid object of type T
    // Post: the binary tree contains x
    // ----------------------------------------------------------
    // TODO: INSERT WILL NOT WORK ENTIRELY WITHOUT POOL
    /**
     * returns true or false depending on if an insert was successful or not
     *
     * @param x
     * @return boolean
     */
    public boolean insert(T x)
    {
        BinaryNode checker = null;
        if (x == null)
        {
            // null no need to insert
            return false;
        }
        // checks to see if a string or not
        else if (String.class.isInstance(x))
        {
            // sets the root if the tree is empty
            checker = insertHelper(x, root);
            root = checker;
            // x is already in the tree no need to insert
            return true;
        }
        // only works on primitive types
        else
        {
            // sets the root if the tree is empty
            try
            {
                checker = insertHelper(x, root);
            }
            catch (RuntimeException e)
            {
                return false;
            }
            if (checker != null)
            {
                if (root != null)
                {
                    return true;
                }
                poolRestrict();
                root = checker;
                return true;
            }
            // x is already in the tree no need to insert
            return false;
        }
    }


    // Delete element matching x from the BST, if present. Return true if
    // matching element is removed from the tree and false otherwise.
    // Pre: x is null or points to a valid object of type T
    // Post: the binary tree does not contain x
    // ----------------------------------------------------------
    /**
     * Removes an element from the tree. returns false if there's no element to
     * remove or the element being removed is null.
     *
     * @param x
     * @return boolean
     */
    public boolean remove(T x)
    {
        if (x == null)
        {
            // null no need to remove
            return false;
        }
        // checks to see if a string or not
        else if (String.class.isInstance(x))
        {
            // helper method call
            root = removeHelper(x, root);
            return true;
        }
        // check to see if element is in the tree
        if (find(x) != null)
        {
            // helper method call
            root = removeHelper(x, root);
            return true;
        }
        // element is not even in the tree, can not remove.
        return false;
    }


    // ----------------------------------------------------------
    /**
     * recursive method that removes a node and has cases for each case.
     *
     * @param x
     * @param node
     * @return BinaryNode
     */
    public BinaryNode removeHelper(T x, BinaryNode node)
    {
        // This local variable will contain the new root of the subtree,
        // if the root needs to change.
        BinaryNode result = node;
        // If there's no more of subtree to examine
        if (node == null)
        {
            return null;
        }
        // if value should be to the left of the root
        if (x.compareTo(node.element) < 0)
        {
            node.setLeft(removeHelper(x, node.left));
        }
        // if value should be to the right of the root
        else if (x.compareTo(node.element) > 0)
        {
            node.setRight(removeHelper(x, node.right));
        }
        // If value is on the current node
        else
        {
            // If there are two children
            if (node.left != null && node.right != null)
            {
                // special case for remove, node has either
                // right node's left node is null
                if (node.right.left == null)
                {
                    // found the min (rMin) value as tempMin
                    BinaryNode tempMin = node.right;
                    // adjust pointers
                    tempMin.left = node.left;
                    node.right = null;
                    node.left = null;
                    poolExpand(node);
                    return tempMin;
                }
                else
                {
                    // get the right min's parent
                    BinaryNode tempParent = findMin(node.right);
                    // get the actual min node
                    BinaryNode tempMin = tempParent.left;
                    // adjust pointers
                    tempParent.setLeft(tempMin.right);
                    tempMin.setRight(node.right);
                    tempMin.setLeft(node.left);
                    node.right = null;
                    node.left = null;
                    poolExpand(node);
                    return tempMin;
                }
            }
            // If there is only one child on the left
            else if (node.left != null)
            {
                result = node.left;
                node.left = null;
                poolExpand(node);
            }
            // If there is only one child on the right
            else
            {
                result = node.right;
                node.right = null;
                poolExpand(node);
            }
        }
        // satisfies the method with return statement
        return result;
    }


    // Remove from the tree all values y such that y > x, according to
    // compareTo().
    // Pre: x is null or points to a valid object of type T
    // Post: if the tree contains no value y such that compareTo()
    // indicates y > x
    // ----------------------------------------------------------
    /**
     * Removes all nodes whose elements are greater than x
     *
     * @param x
     */
    public void cap(T x)
    {
        if (x == null)
        {
            // can't remove null values
            return;
        }
        else
        {
            // call for the helper method, starts at the root of the tree
            capHelper(x, root);
        }
    }


    // ----------------------------------------------------------
    /**
     * Helper method that removes all nodes that are bigger than x
     *
     * @param x
     * @param node
     */
    public void capHelper(T x, BinaryNode node)
    {
        BinaryNode maxNode = findMax(node);
        // if the max value of the tree is not bigger than x then there is
        // nothing left to remove.
        while (maxNode != null && x.compareTo(maxNode.element) < 0)
        {
            remove(maxNode.element);
            // moves through the tree, and while loop
            maxNode = findMax(root);
        }
        return;
    }


    // Return the tree to an empty state.
    // Pre: none
    // Post: the binary tree contains no elements
    // ----------------------------------------------------------
    /**
     * set root equal to null, let the garbage collector do the work
     */
    public void clear()
    {
        // Praise be to the Java garbage collector
        root = null;
    }


    // Return true iff other is a BST that has the same physical structure
    // and stores equal data values in corresponding nodes. "Equal" should
    // be tested using the data object's equals() method.
    // Pre: other is null or points to a valid BST<> object, instantiated
    // on the same data type as the tree on which equals() is invoked
    // Post: both binary trees are unchanged
    public boolean equals(Object other)
    {
        // check for a BST
        if (other == null || !this.getClass().isInstance(other))
        {
            // can't compare null values or anything other than trees
            return false;
        }
        @SuppressWarnings("unchecked")
        BST<T> compare = (BST<T>)other;
        // call helper method
        return equalsHelper(compare.root, root);
    }


    /**
     * Traverses both trees at the same time as one another. if there's a
     * difference between each tree's current node then the trees are not equal.
     * Otherwise both trees are equal to each other.
     *
     * @param nodeA
     * @param nodeB
     * @return boolean
     */
    private boolean equalsHelper(BinaryNode nodeA, BinaryNode nodeB)
    {
        // check to see if either nodes are null without the other node being
        // null as well
        if (nodeA == null && nodeB != null)
        {
            // one null while the other was not
            return false;
        }
        else if (nodeA != null && nodeB == null)
        {
            // one null while the other was not
            return false;
        }
        // both nodes are either null or initialized
        else
        {
            // check to see if both nodes are null
            if (nodeA != null && nodeB != null)
            {

                // neither nodes are null
                if (nodeA.element.compareTo(nodeB.element) != 0)
                {
                    // However, neither of the nodes contain the same element
                    return false;
                }
                else
                {
                    // Recursive call. returns true after full traversal of
                    // every node matching
                    return equalsHelper(nodeA.left, nodeB.left)
                        && equalsHelper(nodeA.right, nodeB.right);
                }
            }
            // both nodes are null, which means the nodes are technically the
            // same
            return true;
        }
    }


    // Return number of levels in the tree. (An empty tree has 0 levels.)
    // Pre: tree is a valid BST<> object
    // Post: the binary tree is unchanged
    // ----------------------------------------------------------
    /**
     * Returns the number of levels in the tree
     *
     * @return int
     */
    public int levels()
    {
        // check if empty or not
        if (isEmpty())
        {
            // empty tree means no levels
            return 0;
        }
        else
        {
            // helper method call
            return levelsHelper(root);
        }
    }


    // ----------------------------------------------------------
    /**
     * Finds the max level of the tree
     *
     * @param node
     * @return count
     */
    public int levelsHelper(BinaryNode node)
    {
        if (node == null)
        {
            // null node reached. end of tree (leaf)
            return 0;
        }
        else
        {
            // We made it to a new level. now recursively call the method and
            // find the max level of the tree. Avoids over counting by checking
            // the max value of each return
            return 1 + Math.max(
                levelsHelper(node.left),
                levelsHelper(node.right));
        }
    }
}
// On my honor:
//
// - I have not discussed the Java language code in my program with
// anyone other than my instructor or the teaching assistants
// assigned to this course.
//
// - I have not used Java language code obtained from another student,
// or any other unauthorized source, either modified or unmodified.
//
// - If any Java language code or documentation used in my program
// was obtained from another source, such as a text book or course
// notes, that has been clearly noted with a proper citation in
// the comments of my program.
//
// - I have not designed this program in such a way as to defeat or
// interfere with the normal operation of the Curator System.
//
// Andrew Knittle
// I have been given permission of using extension days. Current due date: ???

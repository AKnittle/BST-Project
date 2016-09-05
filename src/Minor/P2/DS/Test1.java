package Minor.P2.DS;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author AndrewK
 * @version Feb 11, 2015
 */

public class Test1
{

    /**
     * treeA
     */
    BST<Integer> treeA;
    /**
     * treeB
     */
    BST<Integer> treeB;
    /**
     * treeC
     */
    BST<Integer> treeC;


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @throws java.lang.Exception
     */
    @Before
    public void setUp()
        throws Exception
    {
        treeA = new BST<Integer>();
        treeB = new BST<Integer>();
        treeC = new BST<Integer>();
    }


    /**
     * Test method for {@link BST#isEmpty()}.
     */
    @Test
    public void testIsEmpty()
    {
        assertTrue(treeA.isEmpty());
        treeB.insert(1);
        treeB.clear();
        assertTrue(treeB.isEmpty());
    }


    /**
     * Test method for {@link BST#find(java.lang.Comparable)}.
     */
    @Test
    public void testFind()
    {
        // test null root
        assertNull(treeC.find(null));
        assertNull(treeC.find(1));
        // test 1 value
        assertTrue(treeC.insert(1));
        assertEquals((Integer.valueOf(1)), treeC.find(1));
        // assertFalse(treeC.insert(1));
        // accidently setting the root to null
        assertEquals((Integer.valueOf(1)), treeC.find(1));
        treeC.insert(2);
        treeC.insert(3);
        // check to see if the insert worked properly
        assertTrue(treeC.insert(4));
        treeC.insert(5);
        treeC.insert(6);
        assertEquals((Integer.valueOf(1)), treeC.find(1));
        assertEquals((Integer.valueOf(6)), treeC.find(6));
        assertTrue(treeC.remove(6));
        assertNull(treeC.find(6));
        assertTrue(treeC.insert(6));
        assertEquals((Integer.valueOf(6)), treeC.find(6));
        assertEquals((Integer.valueOf(5)), treeC.find(5));
        assertEquals((Integer.valueOf(4)), treeC.find(4));
        assertEquals((Integer.valueOf(3)), treeC.find(3));
        assertEquals((Integer.valueOf(2)), treeC.find(2));
        assertEquals((Integer.valueOf(1)), treeC.find(1));
        // see if find can still work with a removed node
        assertTrue(treeC.remove(3));
        assertEquals((Integer.valueOf(6)), treeC.find(6));
        assertEquals((Integer.valueOf(5)), treeC.find(5));
        assertEquals((Integer.valueOf(4)), treeC.find(4));
        assertEquals((Integer.valueOf(2)), treeC.find(2));
        assertEquals((Integer.valueOf(1)), treeC.find(1));
        assertNull(treeC.find(3));
        assertTrue(treeC.insert(3));
        assertEquals((Integer.valueOf(3)), treeC.find(3));
        treeC.clear();
        assertNull(treeC.find(3));
        assertNull(treeC.find(1));
        treeC.clear();
        assertTrue(treeC.insert(0));
        assertTrue(treeC.insert(-1));
        assertTrue(treeC.insert(1));
        assertTrue(treeC.insert(-2));
        assertTrue(treeC.insert(2));
        assertTrue(treeC.insert(-5));
        assertTrue(treeC.insert(5));
        assertTrue(treeC.insert(-3));
        assertTrue(treeC.insert(3));
        treeC.insert(0);
        treeC.insert(-1);
        treeC.insert(1);
        treeC.insert(-2);
        treeC.insert(2);
        treeC.insert(-3);
        treeC.insert(3);
        treeC.insert(-5);
        treeC.insert(5);
        // make sure can't be added
        /*
         * assertFalse(treeC.insert(0)); assertFalse(treeC.insert(-1));
         * assertFalse(treeC.insert(1)); assertFalse(treeC.insert(-2));
         * assertFalse(treeC.insert(2)); assertFalse(treeC.insert(-5));
         * assertFalse(treeC.insert(5)); assertFalse(treeC.insert(-3));
         * assertFalse(treeC.insert(3));
         */
        assertEquals(treeC.find(0), Integer.valueOf(0));
        assertEquals(treeC.find(-1), Integer.valueOf(-1));
        assertEquals(treeC.find(1), Integer.valueOf(1));
        assertEquals(treeC.find(-2), Integer.valueOf(-2));
        assertEquals(treeC.find(2), Integer.valueOf(2));
        assertEquals(treeC.find(-3), Integer.valueOf(-3));
        assertEquals(treeC.find(3), Integer.valueOf(3));
        assertEquals(treeC.find(-5), Integer.valueOf(-5));
        assertEquals(treeC.find(5), Integer.valueOf(5));
    }


    // ----------------------------------------------------------
    /**
     * more tests
     */
    @Test
    public void testRemove2()
    {

        BST<Integer> tree = new BST<Integer>();

        tree.insert(3);
        tree.insert(6);
        tree.insert(5);
        tree.insert(4);
        tree.insert(7);
        tree.insert(2);
        tree.insert(0);
        tree.insert(1);
        tree.insert(6);
        tree.insert(3);

        assertEquals(4, tree.levels());

        tree.remove(3);

        int a = tree.root.element;
        assertEquals(4, a);
        int b = tree.root.right.right.element;
        assertEquals(7, b);
        int c = tree.root.right.left.element;
        assertEquals(5, c);
        assertNull(tree.root.right.left.left);

        tree.remove(4);

        int d1 = tree.root.element;
        assertEquals(5, d1);
        int d = tree.root.right.element;
        assertEquals(6, d);
        int e = tree.root.right.right.element;
        assertEquals(7, e);
        assertNull(tree.root.right.left);

        tree.remove(0);

        int g = tree.root.left.left.element;
        assertEquals(1, g);

        tree.remove(3);

        int h = tree.root.element;
        assertEquals(5, h);
        int i = tree.root.left.element;
        assertEquals(2, i);
        int j = tree.root.right.element;
        assertEquals(6, j);

    }


    /**
     * Test method for remove
     */
    @Test
    public void testRemove()
    {
        for (int x = 0; x < 30; x++)
        {
            assertTrue(treeC.insert(x));
            /*
             * try { treeC.insert(x); fail("ALREADY INSIDE"); } catch
             * (IllegalArgumentException e) {
             * System.out.println(e.getMessage());
             * assertNotNull(e.getMessage()); }
             */
        }
        assertTrue(treeC.remove(1));
        assertTrue(treeC.remove(3));
        assertTrue(treeC.remove(4));
        assertTrue(treeC.remove(17));
        assertTrue(treeC.remove(2));
        assertTrue(treeC.remove(18));
        for (int r = 0; r < 30; r++)
        {
            if (r == 1 || r == 3 || r == 4 || r == 17 || r == 2 || r == 18)
            {
                assertNull(treeC.find(r));
            }
            else
            {
                assertEquals((Integer.valueOf(r)), treeC.find(r));
            }
        }
        assertTrue(treeC.remove(0));
        assertEquals(treeC.root.element, Integer.valueOf(5));
        assertTrue(treeC.insert(0));
        assertTrue(treeC.insert(1));
        assertTrue(treeC.insert(3));
        assertTrue(treeC.insert(4));
        assertTrue(treeC.insert(17));
        assertTrue(treeC.insert(2));
        assertTrue(treeC.insert(18));
        for (int y = 0; y < 30; y++)
        {
            assertEquals((Integer.valueOf(y)), treeC.find(y));
            assertTrue(treeC.remove(y));
        }
        treeA.clear();
        treeA.insert(1);
        treeA.insert(0);
        treeA.insert(2);
        assertTrue(treeA.remove(1));
        assertEquals(treeA.root.element, Integer.valueOf(2));
        assertNull(treeA.root.right);
        assertEquals(treeA.find(0), Integer.valueOf(0));
        assertNull(treeA.find(1));
    }


    /**
     * Test method for {@link BST#cap(java.lang.Comparable)}.
     */
    @Test
    public void testCap()
    {
        treeC.clear();
        for (int x = 0; x < 30; x++)
        {
            assertTrue(treeC.insert(x));
        }
        treeC.cap(15);
        for (int y = 0; y < 15; y++)
        {
            assertEquals(treeC.find(y), Integer.valueOf(y));
        }
        assertNull(treeC.find(17));
        assertEquals(treeC.levels(), 16);
        treeC.clear();
        for (int x = 1; x < 30; x++)
        {
            assertTrue(treeC.insert(x));
            assertTrue(treeC.insert(-x));
        }
        treeC.cap(-10);
        assertNull(treeC.find(-9));
    }


    /**
     * Test method for {@link BST#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject()
    {
        // both empty trees
        treeB.clear();
        treeA.clear();
        assertTrue(treeA.equals(treeB));
        // uneven trees
        assertTrue(treeB.insert(5));
        assertFalse(treeA.equals(treeB));
        assertFalse(treeB.equals(treeA));
        // even trees
        assertTrue(treeA.insert(5));
        assertTrue(treeA.equals(treeB));
        assertTrue(treeB.equals(treeA));
        treeA.clear();
        treeB.clear();
        // big trees
        for (int x = 0; x < 30; x++)
        {
            assertTrue(treeA.insert(x));
            assertTrue(treeB.insert(x));
        }
        assertTrue(treeA.equals(treeB));
        assertTrue(treeB.equals(treeA));
        // big uneven
        assertTrue(treeA.insert(100));
        assertFalse(treeA.equals(treeB));
        assertFalse(treeB.equals(treeA));
        assertEquals(treeA.remove(100), true);
        // after a remove
        assertTrue(treeA.equals(treeB));
        assertTrue(treeB.equals(treeA));
        for (int y = 2; y < 30; y += (y - 1))
        {
            assertTrue(treeA.remove(y));
            assertTrue(treeB.remove(y));
        }
        assertTrue(treeA.equals(treeB));
        assertTrue(treeB.equals(treeA));
        assertTrue(treeA.remove(1));
        assertFalse(treeA.equals(treeB));
        assertFalse(treeB.equals(treeA));
        assertTrue(treeB.remove(1));
        treeA.cap(9);
        treeB.cap(9);
        assertTrue(treeA.equals(treeB));
        assertTrue(treeB.equals(treeA));
        assertEquals((Integer.valueOf(8)), treeB.find(8));
        assertNull(treeB.find(9));
    }

}

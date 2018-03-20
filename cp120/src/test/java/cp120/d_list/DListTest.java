package cp120.d_list;

import static org.junit.Assert.*;

import org.junit.Test;

public class DListTest {

    DList testDList = new DList();
    DNode testDNode = new DNode();
    
    @Test
    public void testDList() {
        assertTrue(testDList instanceof DList);
    }

    @Test
    public void testAddHead() {
        DNode node = new DNode();
        testDList.addHead(node);
        assertEquals(testDList.getHead(),node);
    }

    @Test
    public void testAddTail() {
        DNode node = new DNode();
        testDList.addTail(node);
        assertEquals(testDList.getTail(),node);
    }

    @Test
    public void testGetHead() {
        DNode node = new DNode();
        testDList.addHead(node);
        assertEquals(testDList.getHead(),node);
    }

    @Test
    public void testGetTail() {
        DNode node = new DNode();
        testDList.addTail(node);
        assertEquals(testDList.getTail(),node);
    }

    @Test
    public void testRemoveHead() {
        DNode node = new DNode();
        testDList.addHead(node);
        assertEquals(testDList.getHead(),node);
        
        testDList.removeHead();
        assertNotEquals(testDList.getHead(),node);
    }

    @Test
    public void testRemoveTail() {
        DNode node = new DNode();
        testDList.addTail(node);
        assertEquals(testDList.getTail(),node);
        
        testDList.removeTail();
        assertNotEquals(testDList.getTail(),node);
    }

    @Test
    public void testRemoveAll() {
        for (int i= 0; i < 5; i ++) {
            DNode node = new DNode(new Integer(i));
            testDList.addTail(node);
        }
        testDList.removeAll();
        assertTrue(testDList.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        for (int i= 0; i < 5; i ++) {
            DNode node = new DNode(new Integer(i));
            testDList.addTail(node);
        }
        assertFalse(testDList.isEmpty());
        testRemoveAll();
        assertTrue(testDList.isEmpty());
    }

    @Test
    public void testSize() {
        int expSize = 0;
        testDList.removeAll();
        for (int i= 0; i < 5; i ++) {
            DNode node = new DNode(new Integer(i));
            testDList.addTail(node);
        }
        int listSize = testDList.size();
        assertTrue(listSize==5);
    }

    
}

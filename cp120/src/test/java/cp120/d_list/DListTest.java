package cp120.d_list;

import static org.junit.Assert.*;

import org.junit.Test;

import cp120.assignments.geo_shape.GeoShape;

public class DListTest {

    DList testDList = new DList();
    DNode<?> testDNode = new DNode<Object>();
    
    @Test
    public void testDList() {
        assertTrue(testDList instanceof DList);
    }

    @Test
    public void testAddHead() {
        DNode<GeoShape> node = new DNode<GeoShape>();
        testDList.addHead(node);
        assertEquals(testDList.getHead(),node);
    }

    @Test
    public void testAddTail() {
        DNode<GeoShape> node = new DNode<GeoShape>();
        testDList.addTail(node);
        assertEquals(testDList.getTail(),node);
    }

    @Test
    public void testGetHead() {
        DNode<GeoShape> node = new DNode<GeoShape>();
        testDList.addHead(node);
        assertEquals(testDList.getHead(),node);
    }

    @Test
    public void testGetTail() {
        DNode<GeoShape> node = new DNode<GeoShape>();
        testDList.addTail(node);
        assertEquals(testDList.getTail(),node);
    }

    @Test
    public void testRemoveHead() {
        DNode<GeoShape> node = new DNode<GeoShape>();
        testDList.addHead(node);
        assertEquals(testDList.getHead(),node);
        
        testDList.removeHead();
        assertNotEquals(testDList.getHead(),node);
    }

    @Test
    public void testRemoveTail() {
        DNode<GeoShape> node = new DNode<GeoShape>();
        testDList.addTail(node);
        assertEquals(testDList.getTail(),node);
        
        testDList.removeTail();
        assertNotEquals(testDList.getTail(),node);
    }

    @Test
    public void testRemoveAll() {
        for (int i= 0; i < 5; i ++) {
            DNode<GeoShape> node = new DNode<GeoShape>(null);
            testDList.addTail(node);
        }
        testDList.removeAll();
        assertTrue(testDList.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        for (int i= 0; i < 5; i ++) {
            DNode<GeoShape> node = new DNode<GeoShape>(null);
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
            DNode<GeoShape> node = new DNode<GeoShape>(null);
            testDList.addTail(node);
        }
        int listSize = testDList.size();
        assertTrue(listSize==5);
    }

    
}

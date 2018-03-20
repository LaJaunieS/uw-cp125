package cp120.d_list;

import static org.junit.Assert.*;
import static org.junit.rules.ExpectedException.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DNodeTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    DList testDList = new DList();
    
    @Test
    public void testDNodeObject() {
        DNode node = new DNode();
        assertTrue(node instanceof Object);
    }

    @Test
    public void testIsEnqueued() {
        DNode node = new DNode();
        assertFalse(node.isEnqueued());
        testDList.addTail(node);
        assertTrue(node.isEnqueued());
    }

    @Test
    public void testGetData() {
        DNode node = new DNode("Test Data");
        assertEquals("Test Data",node.getData());
    }

    @Test
    public void testSetData() {
        DNode node = new DNode();
        assertEquals(null,node.getData());
        node.setData(new String("String"));
        assertEquals("String", node.getData());
     }

    @Test
    public void testAddAfter() {
        DNode thisNode = new DNode();
        DNode nextNode = new DNode();
        thisNode.addAfter(nextNode);
        assertEquals(nextNode,thisNode.getNext());
        //test that exceptiont thrown
        try {
            thisNode.addAfter(nextNode);
        } catch (IllegalArgumentException ex) {
            thrown.expect(IllegalArgumentException.class);
            throw new IllegalArgumentException();
        }
    }
    
    @Test
    public void testAddBefore() {
        DNode thisNode = new DNode();
        DNode prevNode = new DNode();
        thisNode.addBefore(prevNode);
        assertEquals(prevNode,thisNode.getPrevious());
        //test that exception thrown
        try {
            thisNode.addBefore(prevNode);
        } catch (IllegalArgumentException ex) {
            thrown.expect(IllegalArgumentException.class);
            throw new IllegalArgumentException();
        }
    }

    @Test
    public void testRemove() {
        DNode node = new DNode();
        //add/enqueue node to list and test enqueued state
        //could test isEnqueued but this test more directly tests
        //the functionality of remove()
        testDList.addTail(node);
        assertTrue(node.getNext()!=node);
        assertTrue(node.getPrevious()!=node);
        //remove/unenqueue node from list and test enqueued state
        node.remove();
        assertTrue(node.getNext()==node);
        assertTrue(node.getPrevious()==node);
    }

    @Test
    public void testGetNext() {
        DNode node = new DNode();
        DNode nextNode = new DNode();
        node.addAfter(nextNode);
        assertEquals(nextNode,node.getNext());
    }

    @Test
    public void testGetPrevious() {
        DNode node = new DNode();
        DNode prevNode = new DNode();
        node.addAfter(prevNode);
        assertEquals(prevNode,node.getPrevious());
    }

}

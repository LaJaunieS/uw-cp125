package cp120.d_list;


/**A <code>DNode</code> instance which implements a doubly-linked list. 
 * Represents the root <code>DNode</code> containing other <code>DNode</code>s 
 * enqueued in this list. If this <code>DList</code> contains other 
 * <code>DNode</code>s, <code>flink</code> and <code>blink</code>
 * will refer to the head and tail, respectively. If this <code>DList</code>
 * does not contain other <code>DNode</code>s, <code>flink</code> and 
 * <code>blink</code> will both refer to this <code>DList</code>, as the root.
 * @author slajaunie
 *
 */
public class DList extends DNode {
    //flink and blink point to the head and tail of the list, respectively
    //methods for empty lists do not return null, they return the list itself.
    //flink and blink methods are not null either, they point to the list itself
        
    /**Default constructor. Constructs an empty list, with forward and backward
     * links referring to itself, and with <code>null</code> data.
     */
    public DList() {
        super();
    }
    
    /**Adds the given <code>DNode</code> to the head of this <code>DList</code>
     * @param node the <code>DNode</code> to be added
     */
    public void addHead(DNode node) {
        this.addAfter(node);
    }
    
    /**Adds the given <code>DNode</code> to the tail of this <code>DList</code>
     * @param node the <code>DNode</code> to be added
     */
    public void addTail(DNode node) {
        this.addBefore(node);
    }
    
    /**Getter method for the head of the <code>DList</code>. Returns this list as
     * the root if the <code>DList</code> does not contain any <code>DNode</code>s.
     * @return the <code>DNode</code> at the head of the <code>DList</code> or the
     * <code>DList</code> itself if this <code>DList</code> does not contain any 
     * <code>DNode</code>s.
     */
    public DNode getHead() {
        return getNext();
    }
    
    /**Getter method for the tail of the <code>DList</code>. Returns this list as
     * the root if the <code>DList</code> does not contain any <code>DNode</code>s.
     * @return the <code>DNode</code> at the tail of the <code>DList</code> or the
     * <code>DList</code> itself if this <code>DList</code> does not contain any 
     * <code>DNode</code>s.
     */
    public DNode getTail() {
        return getPrevious();
    }
    
    /**Removes the head from the <code>DList</code> and dereferences that
     * <code>DNode</code> from the list and adjacent <code>DNode</code>s. If
     * the <code>DList</code> is empty, returns this <code>DList</code> as the 
     * root.
     * @return the removed <code>DNode</code>, or this <code>DList</code> as the
     * root.
     */
    public DNode removeHead() {
        //gets flink, since list is the root, refers forward to first item in
        //list
        //if list is empty, returns root
        DNode head = getNext();
        return head.remove();
    }
    
    /**Removes the tail from the <code>DList</code> and dereferences that
     * <code>DNode</code> from the list and adjacent <code>DNode</code>s. If
     * the <code>DList</code> is empty, returns this <code>DList</code> as the 
     * root.
     * @return the removed <code>DNode</code>, or this <code>DList</code> as the
     * root.
     */
    public DNode removeTail() {
        //gets this.blink - since the root, refers back to last item in list
        DNode tail = getPrevious();
        return tail.remove();
    }
    
    
    /**Removes all <code>DNode</code>s from this <code>DList</code>.
     * 
     */
    public void removeAll() {
        //while given node doesn't refer to itself (i.e., dequeued or the root),
        //remove it
        DNode node;
        //System.out.println((node=getNext()) ==this);
        while ( (node=getNext()) != this) {
            node.remove();
        }
        
    }
    
    
    /**Determines if <code>DList</code> is empty, and returns a corresponding
     * <code>boolean</code>.
     * @return <code>true</code> if <code>DList</code> is empty, <code>false</code>
     * if it is not.
     */
    public boolean isEmpty() {
        //if node refers to itself, it is empty
        return ( this.getHead() == this && this.getTail() == this);
    }
    
    /**Gets the size of the <code>DList</code>
     * @return an <code>int</code> value representing the number of 
     * <code>DNode</code>s contained in the <code>DList</code> 
     */
    public int size() {
        int count = 0;
        DNode node = getHead();
        while ( (node != this)) {
            ++count;
            node = node.getNext();
        }
        return count;
    }
    
    
}


package cp120.d_list;

/**Encapsulates a node, containing data and links to adjacent nodes in a 
 * <code>DList</code>, if this <code>DNode</code> is enqueued. If it is not 
 * enqueued, this <code>DNode</code>'s links refer to itself. If this <code>DNode</code>
 * is enqueued at the head of a <code>DList</code>, its backwards link <code>blink</code>
 * refers to the root/<code>DList</code> node. If this <code>DNode</code> is enqueued at
 *  the tail of the <code>DList</code>, its forwards link <code>flink</code> refers to
 *  the root/<code>DList</code> node. If it is the only <code>DNode</code> in the 
 *  <code>DList</code> its links <code>flink</code> and <code>blink</code> both refer
 *  to the root/<code>DList</code> node. 
 * @author slajaunie
 *
 */
public class DNode {
    private Object data;
    //link to prev linked item in list
    private DNode blink;
    //link to next linked item in list
    private DNode flink;
    
    
    
    //new node is unenqueued by default(next and prev node refer to this node)
    /**Creates a new instance of <code>DNode</code> with data <code>Object</code>.
     * @param data the <code>Object</code> that is the data contained within the 
     * <code>DNode</code> instance.
     */
    public DNode(Object data) {
        blink = this;
        flink = this;
        this.data = data;
    }
    
    
    /**Creates a new instance of <code>DNode</code> with <code>null</code> data.
     */
    public DNode() {
        this(null);
    }
    
    //is enqueued if flink/blink refers to a node other than itself
    /**Returns a <code>boolean</code> indicating whether this <code>DNode</code>
     * is enqueued.
     * @return <code>false</code> if this <code>DNode</code> is not enqueued; 
     * <code>true</code> if this <code>DNode</code> is enqueued.
     */
    public boolean isEnqueued() {
        return this != this.flink;
    }
    
    /**Getter method for the data stored on this <code>DNode</code>
     * @return <code>Object</code> data stored on this <code>DNode</code>
     */
    public Object getData() {
        return this.data;
    }
    
    /**Setter method for the data stored on this <code>DNode</code>
     * @param data the <code>Object</code> data to be stored on this <code>DNode</code>.
     */
    public void setData(Object data) {
        this.data = data;
    }
    
    
    /**Inserts the given <code>DNode</code> instance after this <code>DNode</code>.
     * @param node the <code>DNode</code> to be inserted after this <code>DNode</code>.
     * @return the <code>DNode</code> to be inserted
     * @throws IllegalArgumentException if the given <code>DNode</code> is already 
     * enqueued
     */
    public DNode addAfter(DNode node) throws IllegalArgumentException {
        if (node.isEnqueued()) {
            throw new IllegalArgumentException();
        } else {
            node.blink = this;
            node.flink = this.flink;
            //**vvv**//
            //set newly-adjacent nodes links to this node
            this.flink = node;
            node.flink.blink = node;
        }
        return node;
    }
    
    /**Inserts the given <code>DNode</code> instance before this <code>DNode</code>.
     * @param node the <code>DNode</code> to be inserted before this <code>DNode</code>.
     * @return the <code>DNode</code> to be inserted
     * @throws IllegalArgumentException if the given <code>DNode</code> is already 
     * enqueued
     */
    public DNode addBefore(DNode node) throws IllegalArgumentException {
        if (node.isEnqueued()) {
            throw new IllegalArgumentException();
        } else {
            node.blink = this.blink;
            node.flink = this;
            //**VVV***//
            //set newly-adjacent nodes links to this node
            this.blink.flink = node;
            this.blink = node;
            
            
        }
        return node;
    }
    
    /**Dequeues this <code>DNode</code> and de-references it and adjacent nodes.
     * @return this <code>DNode</code>
     */
    public DNode remove() {
        blink.flink= flink;
        flink.blink = blink;
        flink = this;
        blink = this;
        
        return this;
    }
    
    /**Gets the <code>DNode</code> instance inserted after this <code>DNode</code>
     * @return the <code>DNode</code> instance inserted after this <code>DNode</code>
     */
    public DNode getNext() {
        return this.flink;
    }
    
    /**Gets the <code>DNode</code> instance inserted before this <code>DNode</code>
     * @return the <code>DNode</code> instance before after this <code>DNode</code>
     */
    public DNode getPrevious() {
        return this.blink;
    }
    
       
    
}

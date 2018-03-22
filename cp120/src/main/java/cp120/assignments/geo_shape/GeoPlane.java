package cp120.assignments.geo_shape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import abbot.finder.ComponentNotFoundException;
import abbot.finder.Matcher;
import abbot.finder.MultipleComponentsFoundException;
import abbot.finder.matchers.ClassMatcher;
import cp120.d_list.DList;
import cp120.d_list.DNode;



/**Contains an instance of <code>DList</code>. The <code>DList</code>
 * will maintain a collection of <code>DNodes</code>, of which the data stored
 * within the <code>DNode</code> will be a <code>GeoShape</code>. The 
 * <code>GeoPlane</code>will then represent the plane on which the 
 * <code>GeoShapes</code> contained as data within the individual 
 * <code>DNodes</code> will be rendered.
  
 * @author slajaunie
 *
 */
public class GeoPlane implements Runnable {
    /*List holding all nodes containing shapes to be included on the plane*/
    private DList list = new DList();
    
    /*The surface pane- a JPanel*/
    private DrawingSurface surface = new GeoPlane.DrawingSurface();
    
    /*The JFrame that will hold this instance of DrawingSurface*/
    private JFrame frame = new JFrame();
    
    /**Creates a new instance of <code>GeoPlane</code>. Chains to the one parameter
     * method, setting this new instance of <code>GeoPlane</code>'s new instance of 
     * <code>Color</code> to a default color.
     */
    public GeoPlane() {
        this(new Color(.5f,.5f,.5f));
    }
    
    /**Creates an instance of <code>GeoPlane</code>. The constructor sets the background
     * for the drawing surface.
     * @param color the color of the background of this <code>GeoPlane</code>'s instance
     * of <code>DrawingSurface</code>
     */
    public GeoPlane(Color color) {
        /*constructor sets the background for the drawing surface*/
        surface.setBackground(color);
     }
    
    /** instantiates a JFrame, sets a DrawingSurface as its content pane
        packs frame and makes it visible
     * @see java.lang.Runnable#run()
     */
    public void run() {
        frame.setContentPane(surface);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Instantiates runnable, and launches in a new thread
     */
    public void show() {
        new Thread(this, "GeoPlane Thread").start();
        run();
    }
    
    
    /**Encapsulates a drawing surface on which the <code>GeoShape</code> 
     * instances will be rendered. 
     * @author slajaunie
     *
     */
    public class DrawingSurface extends JPanel {
        public DrawingSurface() {
            Dimension size = new Dimension(500,500);
            setPreferredSize(size);
        }
        
        
        /** (non-Javadoc)
         * Overrides <code>paintComponent</code>. Method iterates through the 
         * <code>DNode</code>s contained in the instance of <code>DList</code>, 
         * and renders the <code>GeoShape</code> instance contained in each 
         * <code>DNode</code>'s data. Call's <code>GeoShape</code> instance's
         * <code>draw</code> method, which configures the <code>Graphics</code>
         * context and draws the appropriate shape. 
         * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
         * @param graphics the graphics context that will be cloned and used to render a
         * new <code>Shape</code> with the properties of the given
         * <code>GeoShape</code>s
         */
        @Override
        public void paintComponent(Graphics graphics) {
            /*Call superclass method to confirm renders correctly*/
            super.paintComponent(graphics);
            /*instantiate a new graphics context*/
            Graphics2D gtx = (Graphics2D) graphics.create();
            DNode node = list.getHead();
            GeoShape shape = (GeoShape) node.getData();
            
            while (node != list) {
                shape = (GeoShape) node.getData();
                shape.draw(gtx);
                node = node.getNext();
            }
            
        }
    }
    
    
    /**adds a <code>DNode</code> instance containing the given <code>GeoShape</code>
     * to the tail of the <code>DList</code> instance.
     * @param shape the instance of <code>GeoShape</code> to be added a new 
     * <code>DNode</code> instance which is then added to the tail of <code>DList</code>
     */
    public void addShape(GeoShape shape) {
        DNode node = new DNode(shape);
        list.addTail(node);
    }
    
    /**Getter method for the <code>DList</code> instance
     * @return the <code>DList</code>
     */
    public DList getList() {
        return list;
    }
    
    /**Removes a <code>GeoShape</code> instance, as well as its corresponding
     * <code>DNode</code>, from the tail of the <code>DList</code>. If the 
     * <code>DNode</code> is found within the <code>DList</code>, it is removed
     * and the method returns <code>true</code>. If the <code>DNode</code>'s shape 
     * is not found, the methods returns <code>false</code>.
     * @param shape the <code>GeoShape</code> instance to be located and removed.
     * @return a boolean- <code>true</code> if the <code>GeoShape</code> instance is
     * located and removed from <code>DList</code>, <code>false</code> if the <code>
     * GeoShape</code> is not located.
     */
    public boolean removeShape(GeoShape shape) {
        //returns true if node is found and removed, false otherwise
        boolean bool = false;
        DNode tail = list.getTail();
        int limit = list.size();
        //run for loop instead of while loop to account for shape not
        //necessarily being in list
        for (int i = 0 ; i < limit; i++) {
            if (!tail.getData().equals(shape)) {
                tail = list.getNext();
            } else {
                list.removeTail();
                bool = true;
                i=limit;
                
            }
        }
        return bool;
        
    }
        
    /**Traverses the <code>DList</code> instance, calling <code>draw()</code> for
     * each shape
     */
    public void redraw() {
        surface.repaint();
    }
    
    /**Getter method for this instance of <code>DrawingSurface</code>-- mostly
     * for testing/test coverage.
     * @return this instance of <code>DrawingSurface</code>
     */
    public DrawingSurface getDrawingSurface() {
        return this.surface;
    }
    
    
    /**Setter method for this instance of <code>DrawingSurface</code> -- mostly
     * for testing/test coverage
     * @param surface an instance of <code>DrawingSurface</code>
     */
    public void setDrawingSurface(GeoPlane.DrawingSurface surface) {
        this.surface = surface;
        /*run with new surface context*/
        this.redraw();
    }
    
    /**Getter method for this instane of <code>JFrame</code> -- mostly for testing/
     * test coverage
     * @return this instance of <code>JFrame</code>.
     */
    public JFrame getFrame() {
        return this.frame;
    }
    
    /**Setter method for this instance of <code>DrawingSurface</code> -- mostly
     * for testing/test coverage. 
     * @param frame an instance of <code>JFrame</code>
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
        /*run with new frame context*/
        this.redraw();
    }
    
}

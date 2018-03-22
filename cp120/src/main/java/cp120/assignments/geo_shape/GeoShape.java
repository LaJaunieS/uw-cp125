package cp120.assignments.geo_shape;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**Superclass for other shape classes in this package. It contains fields
 * for storing a shape-origin <code>GeoPoint</code> and a color.
 * @author slajaunie
 *
 */
public abstract class GeoShape {
    public static final GeoPoint DEFAULT_ORIGIN = new GeoPoint(0,0);
    public static final Color DEFAULT_COLOR = null;
    public static final Color DEFAULT_EDGE_COLOR = Color.BLUE;
    public static final double DEFAULT_EDGE_WIDTH = 1;
    private double edgeWidth = DEFAULT_EDGE_WIDTH;
    private Color edgeColor = DEFAULT_EDGE_COLOR;
    
    /*The starting point of the GeoShape*/
    private GeoPoint origin;
    
    /*The color (or edgeColor, depending on what type of shape is
     * instantiated) of the GeoShape*/
    private Color color;
    
        
    /**Abstract method that will be implemented by subclasses of this GeoShape.
     * @param gtx a <code>Graphics2D</code> object that will be handled by subclasses 
     * of this GeoShape
     * @return a <code>Shape</code> object that will be rendered in the given Graphics
     * context
     */
    public abstract Shape draw(Graphics2D gtx);

    /**Constructs a new <code>GeoShape</code> with the given parameters
     * @param origin a <code>GeoPoint</code> that is the origin of the newly-
     * constructed GeoShape. <code>origin</code> can not be <code>null</code>.
     * @param color a <code>Color</code> that is the color of the newly-constructed
     * GeoShape. <code>color</code> may be null.
     * @throws IllegalArgumentException if <code>origin</code> is null.
     */
    public GeoShape(GeoPoint origin,Color color) throws IllegalArgumentException{
        //color may be null
        if (color == null)
            this.color = DEFAULT_COLOR;
        else 
            this.color = color;
      //origin may not be null
        if (origin == null)
            throw new IllegalArgumentException();
        else 
            this.origin = origin;
        
    }
    
    /**Renders a given <code>shape</code> on the given Graphics2D context. 
     * If the color of the given <code>shape</code> is not null, the 
     * given Graphics2D context will fill a new Shape configured with the properties
     * of this <code>GeoShape</code>. If the edgeColor of the given
     * <code>shape</code> is not null, will draw a new Shape configured with 
     * the properties of this <code>GeoShape</code>, including an edge for the new Shape. 
     * @param shape a <code>Shape</code> whose properties will be rendered
     * using the properties of this <code>GeoShape</code>
     * @param gtx the Graphics Context for rendering the new <code>Shape</code>
     */
    public void draw(Shape shape, Graphics2D gtx) {
        /*if a fill color is specified, fill arg shape*/
        if ( this.color != null) {
            gtx.setColor(this.getColor());
            gtx.fill(shape);
        } 
        
        /*if an edge is specified, draw the arg shape*/
        if (this.edgeColor != null && this.edgeWidth > 0) {
            BasicStroke basicStroke = new BasicStroke((float) this.getEdgeWidth());
            gtx.setStroke(basicStroke);
            gtx.setColor(this.getEdgeColor());
            gtx.draw(shape); 
        }
        
    }

    /**Tests equality of this <code>GeoShape</code> and a given <code>GeoShape</code>.
     * The conditions for returning a boolean <code>true</code> are as follows:
     * <ol>
     * <li>If <code>that</code> is null, returns <code>false</code></li>
     * <li>If both this <code>GeoShape</code> and <code>that</code> point to the
     * same object, returns <code>true</code>.</li>
     * <li>If all fields in this <code>GeoShape</code> and <code>that</code> are equal,
     * returns <code>true</code>. Note that the origin fields in both this 
     * <code>GeoShape</code> and <code>that</code> do not need to point to the same 
     * instance of <code>GeoPoint</code> to be equal. The two <code>GeoPoint</code>s simply
     * need to point to the same x and y coordinates for this <code>GeoShape</code> 
     * and <code>that</code> in order to be equal.</li></ol>     * 
     * @param that a second instance of <code>GeoShape</code> to be tested against this
     * <code>GeoShape</code> for equality
     * @param epsilon a <code>double</code> that is to be the delta against the
     * <code>double</code> value fields of both this <code>GeoShape</code> and Shape
     * <code>GeoShape</code> (including the x and y coordinate fields of each respective
     * <code>GeoShape</code>'s <code>GeoPoint</code>s.
     * @return a boolean <code>true</code> if <code>that</code> is equal to this 
     * <code>GeoShape</code>
     */
    public boolean equals(GeoShape that, double epsilon) {
        boolean equal = false;
        if (that == null) {
            /*if other is null*/
            
        } else if (this == that) {
            /*if fields point to same object*/
            equal=true;
        } else if (!(that instanceof GeoShape)) {
            
        } else {
            /*test if fields are equal*/
            GeoShape other = that; 
            boolean sameOriginXco = this.equalsDouble(this.getOrigin().getXco(),
                                                    that.getOrigin().getXco(),
                                                    epsilon);
            boolean sameOriginYco = this.equalsDouble(this.getOrigin().getYco(),
                                                    that.getOrigin().getYco(),
                                                    epsilon);
            boolean sameColor = this.equalsObject(this.getColor(), other.getColor());
            boolean sameEdgeWidth = this.equalsDouble(this.getEdgeWidth(), 
                                                    other.getEdgeWidth(), epsilon);
            boolean sameEdgeColor = this.equalsObject(this.getEdgeColor(),
                                                    other.getEdgeColor());
            equal = sameOriginXco && sameOriginYco && sameColor && sameEdgeWidth && sameEdgeColor;
            
        }
        
        return equal; 
    }
    
    public boolean equalsDouble(double db1, double db2, double epsilon) {
        boolean equal = false;
        boolean fieldsEqual = (Math.abs((db1-db2)) < epsilon);
        if (fieldsEqual)
            equal=true;
        
        return equal;
    }
    
    public boolean equalsObject(Object obj1, Object obj2) {
        /**
         * 
        If obj11 == obj2 return true.
        Else if one of the objects is null, return false.
        Else determine equality using obj1.equals( obj2 ).
         */
        boolean equal = false;
        if (obj1 == obj2)
            equal = true;
        else if (obj1==null || obj2==null)
            equal = false;
        else 
            equal = obj1.equals(obj2);
        
        return equal;
    }
    
    /**Sets the width of the edge of this <code>GeoShape</code> to the 
     * given width
     * @param edgeWidth a <code>double</code> that is the width of this
     * <code>GeoShape</code>
     */
    public void setEdgeWidth(double edgeWidth) {
        this.edgeWidth = edgeWidth;
    }
    
    /**Sets the GeoPoint that is the origin of the shape
     * @param origin a <code>GeoPoint</code> that is to be the origin of the
     * shape
     * @throws IllegalArgumentException if <code>origin</code> is null
     */
    public void setOrigin(GeoPoint origin) throws IllegalArgumentException {
        if (origin == null)
            throw new IllegalArgumentException();
        else
            this.origin = origin;
    }

    /**Sets Edge Color of this <code>GeoShape</code> to the given
     * <code>Color</code>
     * @param color the <code>Color</code> that will be the color of 
     * this <code>GeoShape</code>
     */
    public void setEdgeColor(Color color) {
        this.edgeColor = color;
    }

    /**Sets the Edge color and width of this <code>GeoShape</code>
     * @param color the <code>Color</code> that will be the color of this 
     * <code>GeoShape</code>
     * @param width a <code>double</code> value that will be the color this 
     * <code>GeoShape</code>
     */
    public void setEdge(Color color, double width) {
        this.edgeColor = color;
        this.edgeWidth = width;
    }
    
    /**Sets a <code>null</code> color if no Color is passed to the method
    */
    public void setColor() {
        this.color = null;
    }
     
    /**Sets color to the given Color
    * @param color the Color of the instantiated shape
    */
    public void setColor(Color color) {
        this.color = color;
    }
     
    /**Gets the GeoPoint that is the origin of the shape
    * @return the origin as <code>GeoPoint</code>
    */
    public GeoPoint getOrigin() {
        return origin;
    }

    /**Gets the Edge Color of this <code>GeoShape</code>
     * @return the <code>Color</code> of this <code>GeoShape</code>
     */
    public Color getEdgeColor() {
        return this.edgeColor;
    }
    
    /**Gets the width of this <code>GeoShape</code>'s edge
     * @return a <code>double</code> value that is the width of this 
     * <code>GeoShape</code>'s edge
     */
    public double getEdgeWidth() {
        return this.edgeWidth;
    }
    
    /**Gets the Color that is to be the color of the shape
     * @return the <code>Color</code> of the shape
     */
    public Color getColor() {
        return this.color;
    }

    /** Overrides the <code>toString</code> method in the <code>Object</code> superclass.
     * @see java.lang.Object#toString()
     * @return a <code>String</code> in the format "origin=(xco,yco),color=#ffffff" if
     * <code>color</code> is not null, and "origin=(xco,yco),color=null" if <code>color</code>
     * is null
     */
    @Override
    public String toString() {
        String hexColor = new String();
        String edgeColor = new String();
        if (this.color == null)
            hexColor = "null";
        else 
            hexColor = "#" + Integer.toHexString(this.color.getRGB()).substring(2);
            edgeColor = "#" + Integer.toHexString(this.edgeColor.getRGB()).substring(2);
        return String.format("origin=%s,color=%s,edgeColor=%s,edgeWidth=%.4f",
                            this.origin.toString(),hexColor,edgeColor,this.edgeWidth);
    }
    
}

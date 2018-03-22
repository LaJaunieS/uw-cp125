package cp120.assignments.geo_shape;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

/**Encapsulates a graphical representation of a Rectangle shape on an
 * instance of <code>DrawingSurface</code>
 * @author slajaunie
 *
 */
public class GeoRectangle extends GeoShape {
    /*the width of the rectangle*/
    private double width;
    /*the height of the rectangle*/
    private double height;
    
    /**super constructor throws IllegalArgumentException if null origin*/
    /**Constructs a new GeoRectangle, initialized to the default origin (0,0) and 
     * the default Color (Color.BLUE)
     * @param width - the width of the newly-constructed GeoRectangle
     * @param height- the height of the newly-constructed GeoRectangle
     */
    public GeoRectangle(double width,double height) {
        this(DEFAULT_ORIGIN,DEFAULT_COLOR,width,height);
    }
    
    /**Constructs a new GeoRectangle, initialized to the default Color (Color.BLUE)
     * @param origin - the <code>GeoPoint</code> object setting an origin for newly- 
     * constructed <code>GeoRectangle</code>
     * @param width - the width of the newly-constructed GeoRectangle
     * @param height- the height of the newly-constructed GeoRectangle
     */
    public GeoRectangle(GeoPoint origin, double width, double height) {
        this(origin,DEFAULT_COLOR,width,height);
    }
    
    /**Constructs a new GeoRectangle, initialized to the provided parameters
     * @param origin the <code>GeoPoint</code> object setting an origin for the newly-
     * constructed GeoRectangle
     * @param color  the <code>Color</code> object setting the color for the
     * newly-constructed GeoRectangle
     * @param width - the width of the newly-constructed GeoRectangle
     * @param height- the height of the newly-constructed GeoRectangle
     */
    public GeoRectangle(GeoPoint origin, Color color, double width, double height) {
        super(origin,color);
        this.width = width;
        this.height = height;
    }
    
    /** Tests equality of this <code>GeoRectangle</code> and a given 
     * <code>GeoShape</code>.
     * Overrides the <code>equals</code> method of <code>GeoShape</code>, providing
     * conditions specifically for an instance of <code>GeoRectangle</code>
     * (specifcally, this method returns false if <code>shape</code> is not an instance
     * of <code>GeoRectangle</code>
     * @see cp120.assignments.geo_shape.GeoShape#equals
     * (cp120.assignments.geo_shape.GeoShape, double)
     * @param shape an instance of <code>GeoShape</code> that is to be tested against this
     * <code>GeoRectangle</code>
     * @param epsilon a <code>double</code> that is to be the delta against the
     * <code>double</code> value fields of both this <code>GeoRectangle</code> and Shape
     * <code>GeoShape</code>
     * @return a boolean <code>true</code> if <code>shape</code> is equal to this 
     * <code>GeoRectangle</code>
     */
    @Override
    public boolean equals(GeoShape shape, double epsilon) {
        boolean equal = false;
        if (!(shape instanceof GeoRectangle))
            ;
        else {
            /*cast to GeoRectangle to get to getWidth/Height methods*/
            GeoRectangle other = (GeoRectangle) shape;
            boolean sameWidth = this.equalsDouble(this.getWidth(),
                    other.getWidth(),epsilon);
            boolean sameHeight = this.equalsDouble(this.getHeight(), 
                    other.getHeight(), epsilon);
            boolean sameColor = this.equalsObject(this.getColor(), other.getColor());
            equal = super.equals(shape,epsilon) && sameWidth && sameHeight && sameColor;
        }
        return equal;
    }
    
    /**Returns the area of this GeoRectangle (w*h)
     * @return a <code>double</code> that is the area of this GeoRectangle
     */
    public double area() {
        return this.height*this.width;
    }
    
    /**Returns the perimeter of this GeoRectangle (w+w+h+h)
     * @return a <code>double</code> that is the perimeter of this GeoRectangle
     */
    public double perimeter() {
        return (2*this.height)+(2*this.width);
    }
    
    /**Sets the width of the Rectangle shape
     * @param width of the shape
     */
    public void setWidth(double width) {
        this.width = width;
    }
    
    /**Sets the height of the Rectangle shape
     * @param height of the shape
     */
    public void setHeight(double height) {
        this.height = height;
    }
    
    /**Gets the width of the Rectangle shape
     * @return the <code>double</code> value of the width of the shape
     */
    public double getWidth() {
        return this.width;
    }
    
    /**Gets the height of the Rectangle shape
     * @return the <code>double</code> value of the width of the shape
     */
    public double getHeight() {
        return this.height;
    }
    
    /** Overrides and calls the <code>draw</code> method of <code>GeoShape</code>,
     * providing rendering configurations specifically for an instance of 
     * <code>GeoRectangle</code>
     * @see cp120.assignments.geo_shape.GeoShape#draw(java.awt.Graphics2D)
     * @param gtx the {@link Graphics2D} context that will contain the rendered shape
     * @return the instance of {@link Rectangle2D} that will be rendered (mostly 
     * in order to access properties of the shape during testing)
     */
    @Override
    public Shape draw(Graphics2D gtx) {
        double xco = this.getOrigin().getXco();
        double yco = this.getOrigin().getYco();
        Rectangle2D rect = new Rectangle2D.Double(  xco,
                                                    yco,
                                                    this.getWidth(),
                                                    this.getHeight() );
        super.draw(rect, gtx);
        return rect;
    }
    
    /** Overrides the <code>toString</code> method in the <code>GeoShape</code>
     * superclass
     * @see cp120.assignments.geo_shape.GeoShape#toString()
     * @return a <code>String</code> in the format "origin=(xco,yco),color=#cccccc,
     * width=width,height=height" 
     */
    @Override
    public String toString() {
        return String.format("%s,width=%.4f,height=%.4f",
                            super.toString(),this.width,this.height);
     }
    

}

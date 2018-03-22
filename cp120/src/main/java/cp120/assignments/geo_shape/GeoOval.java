package cp120.assignments.geo_shape;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

/**Encapsulates a graphical representation of a Rectangle shape on an
 * instance of <code>DrawingSurface</code>
 * @author slajaunie
 *
 */
public class GeoOval extends GeoRectangle {
    
    /**Constructs a new GeoOval, initialized to the default origin (0,0) and 
     * the default Color (Color.BLUE)
     * @param width - the width of the newly-constructed GeoOval
     * @param height- the height of the newly-constructed GeoOval
     */
    public GeoOval(double width,double height) {
        this(DEFAULT_ORIGIN,DEFAULT_COLOR,width,height);
    };
    
    /**Constructs a new GeoOval, initialized to the default Color (Color.BLUE)
     * @param origin - the <code>GeoPoint</code> object setting an origin for newly- 
     * constructed <code>GeoOval</code>
     * @param width - the width of the newly-constructed GeoOval
     * @param height- the height of the newly-constructed GeoOval
     */
    public GeoOval(GeoPoint origin,double width,double height) {
        this(origin,DEFAULT_COLOR,width,height);
    }
    
    /**Constructs a new GeoOval, initialized to the provided parameters
     * @param origin the <code>GeoPoint</code> object setting an origin for the newly-
     * constructed <code>GeoOval</code>
     * @param color  the <code>Color</code> object setting the color for this GeoOval
     * @param width - the width of the newly-constructed GeoOval
     * @param height- the height of the newly-constructed GeoOval
     */
    public GeoOval(GeoPoint origin, Color color, double width, double height) {
        super(origin,color,width,height);
    }
    
    /** Overrides and calls the <code>draw</code> method of <code>GeoRectangle</code>,
     * providing rendering configurations specifically for an instance of 
     * <code>GeoOval</code>
     * @see cp120.assignments.geo_shape.GeoRectangle#draw(java.awt.Graphics2D)
     * @param gtx the {@link Graphics2D} context that will contain the rendered shape
     * @return the instance of {@link Arc2D} that will be rendered (mostly 
     * in order to access properties of the shape during testing)
     */
    @Override
    public Shape draw(Graphics2D gtx) {
        Arc2D.Double arc = new Arc2D.Double(this.getOrigin().getXco(),
                                            this.getOrigin().getYco(),
                                            this.getWidth(),
                                            this.getHeight(),
                                            45,
                                            360,
                                            Arc2D.CHORD);
        super.draw(arc, gtx);
        return arc;
        }
    
    /** Tests equality of this <code>GeoOval</code> and a given 
     * <code>GeoShape</code>.
     * Overrides the <code>equals</code> method of <code>GeoRectangle</code>, providing
     * conditions specifically for an instance of <code>GeoOval</code>
     * (specifcally, this method returns false if <code>shape</code> is not an instance
     * of <code>GeoOval</code>
     * @see cp120.assignments.geo_shape.GeoRectangle#equals
     * (cp120.assignments.geo_shape.GeoShape, double)
     * @param shape an instance of <code>GeoShape</code> that is to be tested against this
     * <code>GeoOval</code>
     * @param epsilon a <code>double</code> that is to be the delta against the
     * <code>double</code> value fields of both this <code>GeoObval</code> and Shape
     * <code>GeoShape</code>
     * @return a boolean <code>true</code> if <code>shape</code> is equal to this 
     * <code>GeoOval</code>
     */
    @Override 
    public boolean equals(GeoShape shape, double epsilon) {
        boolean equal = false;
        if (!(shape instanceof GeoOval))
            equal = false;
        else {
            equal = super.equals(shape,epsilon);
        }
        
        return equal;
    }
    
    public double perimeter() {
        /*
         *            _______________
         * p = 2pi*  /__a^2__+__b^2__ 
         *          V       2
         *  Breaking up into separate fields for better clarity/error-checking
         *  
         */
        double wHalf = this.getWidth()/2;
        double hHalf = this.getHeight()/2;
        double sqs =  Math.sqrt( ( Math.pow(wHalf, 2) + Math.pow(hHalf, 2) ) / 2 );
        double pi2 = Math.PI*2;
        double perimeter = pi2*sqs;
        
        return perimeter;
    }
    
    public double area() {
        /*
         * a = ab*pi
         */
        double wHalf = this.getWidth()/2;
        double hHalf = this.getHeight()/2;
        double area = (wHalf*hHalf)*Math.PI;
        
        return area;
    }
}

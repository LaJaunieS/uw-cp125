package cp120.assignments.geo_shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

/**Encapsulates a graphical representation of a line on an instance of 
 * <code>DrawingSurface</code>
 * @author slajaunie
 *
 */
public class GeoLine extends GeoShape {
    /**the starting point/origin of this GeoLine*/ 
    private GeoPoint start = this.getOrigin();
    /**the endpoint of this GeoLine*/
    private GeoPoint end = new GeoPoint(0,0);
                
    /**Instantiates a new <code>GeoLine</code>. Chains to the two-parameter
     * constructor of the superclass <code>GeoShape</code> and sets the 
     * starting and ending <code>GeoPoints</code>.
     * @param origin a <code>GeoPoint</code> that is the origin/starting point
     * of this <code>GeoLine</code>.
     * @param color a <code>Color</code> that will be the color of this 
     * <code>GeoLine</code> 
     * @param end a <code>GeoPoint</code> that is the end point
     * of this <code>GeoLine</code>.
     */
    public GeoLine(GeoPoint origin,Color color,GeoPoint end) {
        super(origin,color);
            this.start = origin;
            this.end = end;
            this.setEdgeColor(color);
        }
    
    /**Instantiates a new <code>GeoLine</code>. Chains to the three-parameter
     * constructor, with the <code>Color</code> of the <code>GeoLine</code> is
     * <code>null</code>.
     * @param start a <code>GeoPoint</code> that is the origin/starting point
     * of this <code>GeoLine</code>.
     * @param end a <code>GeoPoint</code> that is the end point
     * of this <code>GeoLine</code>.
     */
    public GeoLine(GeoPoint start, GeoPoint end) {
        this(start,null,end);
    }
    
    /** Tests equality of this <code>GeoLine</code> and a given <code>GeoShape</code>.
     * Overrides the <code>equals</code> method of <code>GeoShape</code>, providing
     * conditions specifically for an instance of <code>GeoLine</code> (specifcally,
     * this method returns false if <code>shape</code> is not an instance
     * of <code>GeoLine</code>
     * @see cp120.assignments.geo_shape.GeoShape#equals
     * (cp120.assignments.geo_shape.GeoShape, double)
     * @param shape an instance of <code>GeoShape</code> that is to be tested against this
     * <code>GeoLine</code>
     * @param epsilon a <code>double</code> that is to be the delta against the
     * <code>double</code> value fields of both this <code>GeoLine</code> and 
     * <code>shape</code>
     * @return a boolean <code>true</code> if <code>shape</code> is equal to this 
     * <code>GeoLine</code>
     */
    @Override
    public boolean equals(GeoShape shape, double epsilon) {
        boolean equal = false;
        if (!(shape instanceof GeoLine))
            ;
        else {
            GeoLine other = (GeoLine) shape;
            boolean endsXcoEqual = this.equalsDouble(this.getEnd().getXco(), 
                                                     other.getEnd().getXco(), epsilon);
            boolean endsYcoEqual = this.equalsDouble(this.getEnd().getYco(), 
                                                    other.getEnd().getYco(), epsilon);
            
            equal = super.equals(shape, epsilon) && endsXcoEqual && endsYcoEqual;
        }
        return equal;
    }
    
    /** Overrides and calls the <code>draw</code> method of <code>GeoShape</code>,
     * providing rendering configurations specifically for an instance of 
     * <code>GeoLine</code>
     * @see cp120.assignments.geo_shape.GeoShape#draw(java.awt.Graphics2D)
     * @param gtx the {@link Graphics2D} context that will contain the rendered shape
     * @return the instance of {@link Line2D} that will be rendered (mostly 
     * in order to access properties of the shape during testing)
     */
    public Shape draw(Graphics2D gtx) {
            double startX = start.getXco();
            double startY = start.getYco();
            double endX = end.getXco();
            double endY = end.getYco();
            Line2D line = new Line2D.Double(startX,startY,endX,endY);
            gtx.setColor(this.getColor());
            /**calls graphics context's own draw method with the created shape using
            the settings of the current Graphics2D context*/
            super.draw(line,gtx);
            return line;
        
    }
    
    /**Calculates the length of this <code>GeoLine</code>, using the <code>distance</code>
     * method of the <code>start</code> instance of <code>GeoPoint</code>
     * @return a <code>double</code> that is the length of the line
     */
    public double length() {
        return start.distance(end);
    }
    
    /**Calculates the slope of the line, using the equation (y1-y2)/(x1-x2)
     * @return a double that represents the slope of this <code>GeoLine</code>
     */
    public double slope() {
        double startX = start.getXco();
        double startY = start.getYco();
        double endX = end.getXco();
        double endY = end.getYco();
    
        return (startY-endY)/(startX-endX);
    }
    
    /**Sets the origin/starting <code>GeoPoint</code> of this <code>GeoLine</code>
     * @param start an origin/starting <code>GeoPoint</code> of this <code>GeoLine</code>
     */
    public void setStart(GeoPoint start) {
        this.setOrigin(start);
    }
    
    /**Sets the end <code>GeoPoint</code> of this <code>GeoLine</code>
     * @param end an end <code>GeoPoint</code> of this <code>GeoLine</code>
     */
    public void setEnd(GeoPoint end) {
        this.end = end;
    }
    
    /**Overrides the <code>setColor</code> method of <code>GeoShape</code> to set
     * the <code>edgeColor</code> property of this <code>GeoLine</code> 
     * to the given color.
     * @see cp120.assignments.geo_shape.GeoShape#setColor(java.awt.Color)
     * @param color a color of this <code>GeoLine</code> 
     */
    @Override
    public void setColor(Color color) {
        this.setEdgeColor(color);
    }
    
    /**Gets the origin/starting <code>GeoPoint</code> of this <code>GeoLine</code>
     * @return the origin/starting <code>GeoPoint</code> of this <code>GeoLine</code>
     */
    public GeoPoint getStart() {
        return this.getOrigin();
    }
    
    /**Gets the end <code>GeoPoint</code> of this <code>GeoLine</code>
     * @return the end <code>GeoPoint</code> of this <code>GeoLine</code>
     */
    public GeoPoint getEnd() {
        return this.end;
    }
    
    /**Gets the <code>edgeColor</code> of this <code>GeoLine</code>
     * @see cp120.assignments.geo_shape.GeoShape#getColor()
     * @return the <code>edgeColor</code> of this <code>GeoLine</code>
     */
    @Override
    public Color getColor() {
        return this.getEdgeColor();
    }
    
    /** Overrides the <code>toString</code> method in the <code>GeoShape</code>
     * superclass
     * @see cp120.assignments.geo_shape.GeoShape#toString()
     * @return a <code>String</code> in the format "origin=(xco,yco),color=#cccccc,
     * width=width,height=height" 
     */
    @Override
    public String toString() {
        return String.format("%s,endpoint=(%.4f,%.4f)",
                            super.toString(),this.getEnd().getXco(),this.getEnd().getYco());
     }
    
    
}

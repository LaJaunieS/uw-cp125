package cp120.assignments.geo_shape;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class GeoRectangleTest {

    GeoRectangle gr = new GeoRectangle(45,45);
    
    @Test 
    public void testTwoParamConstructor() {
        assertTrue(gr instanceof GeoRectangle);
    }
    
    @Test
    public void testThreeParamConstructor() {
        GeoPoint gp = new GeoPoint(0,0);
        GeoRectangle gr2 = new GeoRectangle(gp,45,45);
        assertTrue(gr instanceof GeoRectangle);
    }
    
    @Test
    public void testFourParamConstructor() {
        GeoPoint gp = new GeoPoint(0,0);
        GeoRectangle gr2 = new GeoRectangle(gp,Color.RED,45,45);
        assertTrue(gr instanceof GeoRectangle);
    }
    
    @Test
    public void testEquals() {
        GeoRectangle go = new GeoRectangle(new GeoPoint(0,0),Color.RED,45,45);
        /*Test if obj2 is not an instance of GeoRectangle*/
        assertFalse(go.equals(new GeoOval(45,45),.00001));
        
        /*Test returns true if heights are equal*/
        assertTrue(go.equals(new GeoRectangle(new GeoPoint(0,0),Color.RED,45,45),.0001));
        assertFalse(go.equals(new GeoRectangle(new GeoPoint(0,0),Color.RED,44,45),.0001));
        
        /*Test returns true if widths are equal*/
        assertTrue(go.equals(new GeoRectangle(new GeoPoint(0,0),Color.RED,45,45),.0001));
        assertFalse(go.equals(new GeoRectangle(new GeoPoint(0,0),Color.RED,45,46),.0001));
        
        /*Test returns true if Colors are equal*/
        assertTrue(go.equals(new GeoRectangle(new GeoPoint(0,0),Color.RED,45,45),.0001));
        assertFalse(go.equals(new GeoRectangle(new GeoPoint(0,0),Color.CYAN,45,45),.0001));
    }
    
    @Test
    public void testArea() {
        //h and w are 45.0
        double expectedArea = 2025;
        assertEquals(expectedArea,gr.area(),.000001);
    }
    
    @Test public void testPerimeter() {
        double expectedPerimeter = 180;
        assertEquals(expectedPerimeter,gr.perimeter(),.000001);
    }
    
    @Test
    //covers both draw() and toString(),since draw just prints outs toString()
    //output
    public void testDraw() {
        GeoPlane gp = new GeoPlane();
        GeoRectangle gr = new GeoRectangle(new GeoPoint(0,0),45.12345,45.12345);
        /*Shape2D object to be rendered*/
        Rectangle2D rect= null;
        /*Graphics context (created with gp.show())*/
        Graphics2D gtx = null;
        double epsilon = .000001;
        gp.addShape(gr);
        gp.show();
        /*capture the Graphics context to be tested*/
        gtx = (Graphics2D) gp.getDrawingSurface().getGraphics();
        rect = (Rectangle2D) gr.draw(gtx);
        gtx.setColor(Color.RED);
        
        /*Test properties of the rendered Shape object*/
        /*X and Y coordinates of origin point correctly applied from 
        * constructor
        */
        assertEquals(0.0,rect.getX(),epsilon);
        assertEquals(0.0,rect.getY(),epsilon);
        /*Height and width values correctly applied from constructor*/
        assertEquals(45.12345,rect.getHeight(),epsilon);
        assertEquals(45.12345,rect.getWidth(),epsilon);
        /*Color correctly applied from constructor*/
        assertEquals(Color.RED,gtx.getColor());
    }

    @Test
    public void testGetWidth() {
        gr.setWidth(45);
        Double widthDouble = gr.getWidth();
        Double epsilon = .000001;
        assertTrue((45-widthDouble)<epsilon);
    }

    @Test
    public void testSetWidth() {
        gr.setWidth(45);
        Double widthDouble = gr.getWidth();
        Double epsilon = .000001;
        assertTrue((45-widthDouble)<epsilon);
    }

    @Test
    public void testGetHeight() {
        gr.setHeight(45);
        Double heightDouble = gr.getHeight();
        Double epsilon = .000001;
        assertTrue((45-heightDouble)<epsilon);
    }

    @Test
    public void testSetHeight() {
        gr.setHeight(45);
        Double heightDouble = gr.getHeight();
        Double epsilon = .000001;
        assertTrue((45-heightDouble)<epsilon);
    }
    
    @Test
    public void testToString() {
        /*Condition- if color is null*/
        String expectedString = "origin=(0.0000,0.0000),color=null," 
      + "edgeColor=#0000ff,edgeWidth=1.0000,width=45.0000,height=45.0000";
        assertEquals(expectedString,gr.toString());
        
        /*Condition- if color is given*/
        GeoRectangle gr2 = new GeoRectangle(new GeoPoint(0,0),Color.CYAN,45,45);
        String expectedStringNullColor = "origin=(0.0000,0.0000),color=#00ffff," 
        + "edgeColor=#0000ff,edgeWidth=1.0000,width=45.0000,height=45.0000";
        assertEquals(expectedStringNullColor,gr2.toString());
              
    }

}

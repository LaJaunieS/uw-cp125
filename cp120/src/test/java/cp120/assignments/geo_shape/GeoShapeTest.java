package cp120.assignments.geo_shape;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GeoShapeTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    GeoRectangle gr = new GeoRectangle(45, 45);
    GeoPoint gp = new GeoPoint(45,45);
    Color color = new Color(255);
    
    //test constructor- should throw IllegalArgumentException if origin
    //is null
    @Test
    public void testConstructor() {
        GeoRectangle gr = new GeoRectangle(45,45);
        assertTrue(gr instanceof GeoRectangle);
        /*Test constructor where color is null*/
        GeoRectangle grNullColor = new GeoRectangle(new GeoPoint(0,0),null,45, 45);
        assertEquals(null,grNullColor.getColor());
        
        /*Test constructor where color is given*/
        GeoRectangle grColor = new GeoRectangle(new GeoPoint(0,0),Color.BLUE,45,45);
        assertEquals("For grColor shape: ", Color.BLUE,grColor.getColor());
        
        /*Test constructor where null given for origin- throws exception*/
        try {
            GeoRectangle grEx = new GeoRectangle(null,null,45,45);
        } catch (IllegalArgumentException ex) {
            thrown.expect(IllegalArgumentException.class);
            throw new IllegalArgumentException();
        }
    }
    
    @Test
    public void testDraw() {
      //Condition- if color is given
        GeoRectangle gr = new GeoRectangle(new GeoPoint(0,0),Color.BLUE,45,45);
        String expectedString = "origin=(0.0000,0.0000),color=#0000ff," 
        + "edgeColor=#0000ff,edgeWidth=1.0000,"
        + "width=45.0000,height=45.0000";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        System.out.println(gr.toString());
        assertEquals(expectedString + System.lineSeparator(),baos.toString());
        
        //Condition- if color is null
        GeoRectangle gr2 = new GeoRectangle(new GeoPoint(0,0),null,45,45);
        String expectedStringNullColor = "origin=(0.0000,0.0000),color=null," 
                + "edgeColor=#0000ff,edgeWidth=1.0000,"
                + "width=45.0000,height=45.0000";
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos2));
        System.out.println(gr2.toString());
        assertEquals(expectedStringNullColor + System.lineSeparator(),baos2.toString());
    }
    
    @Test
    public void testEquals() {
        double epsilon = .000001;
        
        /*Testing equals should return false if other is null*/
        assertFalse(new GeoOval(new GeoPoint(50,50),Color.RED,20,6).equals(null));
        assertTrue(!(new GeoOval(new GeoPoint(50,50),Color.RED,20,6).equals(null)));
        
        /*Testing equals should return false if other is not an instance of GeoShape*/
        assertFalse(new GeoOval(new GeoPoint(50,50),Color.RED,20,6).equals(new Object()));
        
        /*Testing equals should return true if other points to same instance
         * of GeoShape
         */
        GeoOval goThis = new GeoOval(new GeoPoint(50,50),Color.RED,20,6);
        GeoOval goSameInstance = goThis;
        assertTrue(goThis.equals(goSameInstance));
        
        /*Testing equals should return true if same origin coordinates, all
         * other fields equal
         */
        GeoOval go = new GeoOval(new GeoPoint(50,50),Color.RED,20,6);
        assertTrue(go.equals(new GeoOval(new GeoPoint(50,50),Color.RED,20,6),epsilon));
        assertFalse(go.equals(new GeoOval(new GeoPoint(0,50),Color.RED,20,6),epsilon));
        assertFalse(go.equals(new GeoOval(new GeoPoint(50,0),Color.RED,20,6),epsilon));
        
        /*Testing equals should return true if same Color value, all
         * other fields equal
         */
        GeoOval go2 = new GeoOval(new GeoPoint(50,50),Color.RED,20,6);
        assertTrue(go2.equals(new GeoOval(new GeoPoint(50,50),Color.RED,20,6),epsilon));
        assertFalse(go2.equals(new GeoOval(new GeoPoint(0,50),Color.BLUE,20,6),epsilon));
        
        /*Testing equals should return true if same height value, all
         * other fields equal
         */
        GeoOval go3 = new GeoOval(new GeoPoint(50,50),Color.RED,20,6);
        assertTrue(go3.equals(new GeoOval(new GeoPoint(50,50),Color.RED,20,6),epsilon));
        assertFalse(go3.equals(new GeoOval(new GeoPoint(50,50),Color.BLUE,18,6),epsilon));
        
        /*Testing equals should return true if same width value, all
         * other fields equal
         */
        GeoOval go4 = new GeoOval(new GeoPoint(50,50),Color.RED,20,6);
        assertTrue(go4.equals(new GeoOval(new GeoPoint(50,50),Color.RED,20,6),epsilon));
        assertFalse(go4.equals(new GeoOval(new GeoPoint(50,50),Color.RED,20,7),epsilon));
        
        /*Testing equals should return true if same edgeColor value, all
         * other fields equal
         */
        GeoOval go5 = new GeoOval(new GeoPoint(50,50),Color.RED,20,6);
        go5.setEdgeColor(Color.CYAN);
        GeoOval go6= new GeoOval(new GeoPoint(50,50),Color.RED,20,6);
        go6.setEdgeColor(Color.CYAN);
        GeoOval go7= new GeoOval(new GeoPoint(50,50),Color.RED,20,6);
        go7.setEdgeColor(Color.BLACK);
        assertTrue(go5.equals(go6,epsilon));
        assertFalse(go5.equals(go7,epsilon));
        
        /*Testing equals should return true if same edgeWidth value, all
         * other fields equal
         */
        GeoOval go8 = new GeoOval(new GeoPoint(50,50),Color.RED,20,6);
        go8.setEdgeWidth(10);
        GeoOval go9 = new GeoOval(new GeoPoint(50,50),Color.RED,20,6);
        go9.setEdgeWidth(10);
        GeoOval go10 = new GeoOval(new GeoPoint(50,50),Color.RED,20,6);
        go10.setEdgeWidth(11);
        assertTrue(go8.equals(go9,epsilon));
        assertFalse(go8.equals(go10,epsilon));
        
    }

    @Test
    public void testEqualsObject() {
        /* 
       If obj11 == obj2 return true.
       Else if one of the objects is null, return false.
       Else determine equality using obj1.equals( obj2 ).
        */
        /*Test if obj2 (GeoPoint below) points to same instance of obj1*/
        GeoPoint gp = new GeoPoint(0,0);
        GeoOval go = new GeoOval(gp,Color.RED,45,45);
        GeoOval go2 = new GeoOval(gp,Color.RED,45,45);
        assertTrue(go.equalsObject(go.getOrigin(), go2.getOrigin()));
        
        /*Test if either object is null, return false*/
        assertFalse(go.equalsObject(go.getOrigin(), null));
        assertFalse(go.equalsObject(null, go.getOrigin()));
        

    }
    
    @Test
    public void testEqualsDouble() {
        GeoOval go = new GeoOval(45.12345,45.12345);
        GeoOval go2 = new GeoOval(45.123456,45.123456);
        assertFalse(go.equalsDouble(go.getHeight(), go2.getHeight(),.000001));
        assertTrue(go.equalsDouble(go.getHeight(), go2.getHeight(),.00001));
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
    
    @Test
    public void testGetOrigin() {
        gr.setOrigin(gp);
        assertEquals(gp,gr.getOrigin());
    }

    @Test
    public void testSetOrigin() {
        gr.setOrigin(gp);
        assertEquals(gp,gr.getOrigin());
        
        try {
            gr.setOrigin(null);
        } catch (IllegalArgumentException ex) {
            thrown.expect(IllegalArgumentException.class);
            throw new IllegalArgumentException();
        }
    }

    @Test
    public void testGetColor() {
        gr.setColor();
        assertEquals(null,gr.getColor());
    }

    @Test
    public void testSetColor() {
        gr.setColor();
        assertEquals(null,gr.getColor());
    }

    @Test
    public void testSetColorColor() {
        gr.setColor(color);
        assertEquals(color,gr.getColor());
    }

    @Test
    public void testSetEdgeWidth() {
        gr.setEdgeColor(Color.BLUE);
        assertEquals(Color.BLUE,gr.getEdgeColor());
    }
    
    @Test
    public void testGetEdgeWidth() {
        double edgeWidth = 5.0;
        gr.setEdgeWidth(edgeWidth);
        assertEquals(5.0,gr.getEdgeWidth(),.0001);
     
    }
    
    @Test
    public void testSetEdge() {
        gr.setEdge(Color.CYAN, 5.0);
        assertEquals(gr.getEdgeColor(),Color.CYAN);
        assertEquals(gr.getEdgeWidth(),5.0,.0001);
    }
}

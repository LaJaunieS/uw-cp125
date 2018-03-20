package cp120.assignments.geo_shape;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeoPointTest {
    GeoPoint gp = new GeoPoint(45,45);
    
    //Test toString
    //Test equals
    @Test
    public void testToString() {
        GeoPoint gp = new GeoPoint(3.12345,3.12345);
        GeoPoint gpAbs = new GeoPoint(3,4);
        String firstExpectedString = "(3.1235,3.1235)";
        String secondExpectedString = "(3.0000,4.0000)";
        assertEquals(firstExpectedString,gp.toString());
        assertEquals(secondExpectedString,gpAbs.toString());
        
    }
    
    @Test
    public void testEquals() {
        GeoPoint gpNull = null;
        GeoPoint gp1 = new GeoPoint(0,0);
        GeoPoint gpSameInstance = gp1;
        GeoPoint gpOtherDifferentFields = new GeoPoint(1,3);
        GeoPoint gpOtherDiffFieldsTestEpsilon = new GeoPoint(.0001,.0001);
        
        //other is null, should return false
        assertFalse(gp1.equals(null,.0001));
        
        //other points to same instance of this GeoPoint, should return true
        assertTrue(gp1.equals(gpSameInstance,.0001));
        
        //other is 1: not null; 2: not pointing to same object; 
        //3: fields are different but are close to epsilon- should return false
        assertFalse(gp1.equals(gpOtherDiffFieldsTestEpsilon,.00001));
        
        //other is 1: not null; 2: not pointing to same object; 
        //3: fields are equal- should return true
        assertTrue(gp1.equals(new GeoPoint(0,0),.00001));
    }
    
    @Test
    public void testGeoPoint() {
        assertTrue(gp instanceof GeoPoint);
    }
    
    @Test
    public void testGetXco() {
        gp.setXco(45);
        Double xcoDouble = gp.getXco();
        Double epsilon = .000001;
        assertTrue((45-xcoDouble)<epsilon);
    }

    @Test
    public void testSetXco() {
        gp.setXco(45);
        Double xcoDouble = gp.getXco();
        Double epsilon = .000001;
        assertTrue((45-xcoDouble)<epsilon);
    }

    @Test
    public void testGetYco() {
        gp.setYco(45);
        Double ycoDouble = gp.getYco();
        Double epsilon = .000001;
        assertTrue((45-ycoDouble)<epsilon);
    }

    @Test
    public void testSetYco() {
        gp.setYco(45);
        Double ycoDouble = gp.getYco();
        Double epsilon = .000001;
        assertTrue((45-ycoDouble)<epsilon);
    }

    @Test
    public void testDistance() {
        GeoPoint gp1 = new GeoPoint(-2,1);
        GeoPoint gp2 = new GeoPoint(4,3);
        double expDistance = 6.32455;
        assertEquals(expDistance,gp1.distance(gp2),.0001);
    }
}

package haya4.tools.jpmesh;

import static org.junit.Assert.*;

import java.awt.geom.Rectangle2D;

import org.geotools.geometry.DirectPosition2D;
import org.junit.Test;

public class JpmeshTest {
    
    @Test
    public void getPositionTest() {
    	DirectPosition2D ret = Jpmesh.getPosition(null);
    	assertNull(ret);
    	
    	ret = Jpmesh.getPosition("53394526 ");
    	assertNull(ret);
    	
    	ret = Jpmesh.getPosition("53394526");
    	assertNotNull(ret);
    	assertEquals(35.7d, ret.getY(), 0.000001d);
    	assertEquals(139.7d, ret.getX(), 0.000001d);
    }

    @Test
    public void getCenterTest() {
    	DirectPosition2D center = Jpmesh.getCenterPosition("53394526");
    	assertNotNull(center);
    	assertEquals(35.7041666d, center.getY(), 0.000001d);
    	assertEquals(139.70625d, center.getX(), 0.000001d);
    }

    @Test
    public void getRectangleTest() {
    	Rectangle2D rect = Jpmesh.getRectangle("53394526");
    	assertNotNull(rect);
    	assertEquals(35.7d, rect.getY(), 0.000001d);
    	assertEquals(139.7d, rect.getX(), 0.000001d);
    	assertEquals(0.0083333d, rect.getHeight(), 0.000001d);
    	assertEquals(0.0125, rect.getWidth(), 0.000001d);
    }
}

package pobj.tme6.notation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javafx.scene.paint.Color;
import java.io.ByteArrayOutputStream;

import org.junit.Test;

import pobj.tme6.BoundContext;
import pobj.tme6.IContext;


public class Question4NotationBound {

	// Common for all tests
	
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	
	// Specific
	
	
	// Test de BoundContext isolé
	
	@Test public void testClass() {
		BoundContext ctx = new BoundContext();
		assertTrue(ctx instanceof IContext);
		
	}
	
	@Test public void test1() {
		BoundContext ctx = new BoundContext();
		ctx.drawLine(0,0,100,200,Color.BLACK);
		assertEquals(0, ctx.getMinX());
		assertEquals(0, ctx.getMinY());
		assertEquals(100, ctx.getMaxX());
		assertEquals(200, ctx.getMaxY());
	}
	
	@Test public void test2() {
		BoundContext ctx = new BoundContext();
		ctx.drawLine(10,120, 20,110,Color.BLACK);
		assertEquals(10,ctx.getMinX());
		assertEquals(110,ctx.getMinY());
		assertEquals(20,ctx.getMaxX());
		assertEquals(120,ctx.getMaxY());
	}
	
	@Test public void test3() {
		BoundContext ctx = new BoundContext();
		ctx.drawLine(10,10,20,20,Color.BLACK);
		ctx.drawLine(100,100,90,90,Color.BLACK);
		assertEquals(10, ctx.getMinX());
		assertEquals(10, ctx.getMinY());
		assertEquals(100, ctx.getMaxX());
		assertEquals(100, ctx.getMaxY());
	}	
}

package pobj.partiel2012nov.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestQ8 {

	@Test
	public void testEval() {
		Map<String,Integer> env1 = env1();		
		assertEquals(0, env1.size());
		
		Map<String,Integer> env2 = env2();		
		assertEquals(2, env2.size());
		Integer vx = env2.get("x");
		assertNotNull(vx);
		assertEquals((Integer)10, vx);
		Integer vy = env2.get("y");
		assertNotNull(vy);
		assertEquals((Integer)20, vy);
				
		Map<String,Integer> env3 = env3();		
		assertEquals(1, env3.size());
		assertEquals(new Integer(9), env3.get("z"));		
	}


	public static Map<String,Integer> env1() { return new HashMap<String,Integer>(); }
	public  static Map<String,Integer> env2() { 
		Map<String,Integer> env = new HashMap<String,Integer>();
		env.put("x", 10);
		env.put("y", 20);
		return env;
	}
	public  static Map<String,Integer> env3() { 
		Map<String,Integer> env = new HashMap<String,Integer>();
		env.put("z", 9);
		return env;
	}
}

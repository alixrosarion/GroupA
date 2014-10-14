package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentTest
{
	@Test
	public void testToString()
	{
		Argument tester = new Argument("length");
		assertEquals("length", tester.toString());
	}
	@Test
	public void testToStringWithOverloadedConstructor()
	{
		Argument tester = new Argument("length","Integer","The length of the box");
		assertEquals("length Integer The length of the box", tester.toString());	
	}
	
	@Test
	public void testOverloadedConstructor()
	{
		Argument tester = new Argument("length", "Integer", "the height of the box");
		assertEquals("length", tester.getTitle());
		assertEquals("Integer", tester.getType());
		assertEquals("the height of the box", tester.getDescription());
	}
	
	@Test
	public void testEquals()
	{
		Argument tester = new Argument("length");
		Argument tester1 = new Argument("length");
		assertEquals(tester, tester1);	
	}
	
	@Test
	public void testHashCode()
	{
		Argument tester = new Argument("length");
		Argument tester1 = new Argument("length");
		assertEquals(tester.hashCode(), tester1.hashCode());	
	}
}
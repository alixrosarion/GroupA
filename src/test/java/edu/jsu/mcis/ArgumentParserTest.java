package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest {
	
	@Test
	public void testAddSingleArgument () {
		ArgumentParser tester = new ArgumentParser();
		assertEquals(0, tester.getNumArguments());
		tester.addArgument("length");
		assertEquals(1, tester.getNumArguments());
	}
	
	@Test
	public void testAddMultipleArguments () {
		ArgumentParser tester = new ArgumentParser();
		assertEquals(0, tester.getNumArguments());
		tester.addArgument("length");
		tester.addArgument("width");
		tester.addArgument("height");
		assertEquals(3, tester.getNumArguments());
	}
	
	@Test
	public void testParseSingleArgument() {
		ArgumentParser tester = new ArgumentParser();
		tester.addArgument("length");
		try{
		tester.parse("VolCalc 7");
			assertEquals("7", tester.getArgumentValue("length"));
		}catch(TooManyArgValuesException | NotEnoughArgValuesException e){
			assertTrue(false);
			}
	}
	
	@Test
	public void testVolCalc() {
		ArgumentParser tester = new ArgumentParser();
		tester.addArgument("length");
		tester.addArgument("width");
		tester.addArgument("height");
		try
		{
			tester.parse("VolCalc 7 5 2");
			//assertTrue(false);
		}catch(NotEnoughArgValuesException  | TooManyArgValuesException e){
			assertTrue(false);
		}
		assertEquals("7", tester.getArgumentValue("length"));
		assertEquals("5", tester.getArgumentValue("width"));
		assertEquals("2", tester.getArgumentValue("height"));
	}
	
	@Test
	public void testTooManyArguments() {
		ArgumentParser tester = new ArgumentParser();
		tester.addArgument("length");
		tester.addArgument("width");
		tester.addArgument("height");
		try
		{
			tester.parse("VolCalc 7 5 2 10");
			assertTrue(false);
		}catch(TooManyArgValuesException | NotEnoughArgValuesException e){
			assertEquals("10", tester.getUnmatched());
		}
	}
	
	@Test
	public void testNotEnoughArguments() {
		ArgumentParser tester = new ArgumentParser();
		tester.addArgument("length");
		tester.addArgument("width");
		tester.addArgument("height");
		try
		{
			tester.parse("VolCalc 7 5");
			assertTrue(false);
		}catch(NotEnoughArgValuesException  | TooManyArgValuesException e){
			assertEquals("height", tester.getUnmatched());
		}
	}

	//@Test
	public void testHelpOptionReads(){
		ArgumentParser tester = new ArgumentParser();
		tester.addArgument("length");
		tester.addArgument("width");
		tester.addArgument("height");

		try
		{
			tester.parse("VolCalc 7 5 2 -h");
		}catch(NotEnoughArgValuesException | TooManyArgValuesException e)
		{
			assertTrue(false);
		}
//		assertEquals("usage: java VolCalc length width height \nCalculate the volume of a box\nPositional Arguments:\nlength\t\tthe length of the box\n width\t\tthe width of the box\n height\t\ttheheight of the box", tester.getOptionalArgument("-h"));
	}
	
	@Test
	public void testOptionText(){
		ArgumentParser tester = new ArgumentParser();
		tester.addArgument("length");
		tester.addArgument("width");
		tester.addArgument("height");
		try
		{
			tester.parse("VolCalc -h");
			//assertTrue(false);
		}catch(NotEnoughArgValuesException  | TooManyArgValuesException e){
			//assertTrue(false);
		}
		assertEquals("usage: java VolCalc length width height \nCalculate the volume of a box\nPositional Arguments:\nlength\t\tthe length of the box\n width\t\tthe width of the box\n height\t\tthe height of the box", tester.getHelpText());
	}
	
	
	@Test
	public void testgetArgumentType()
	{
		ArgumentParser tester = new ArgumentParser();
		tester.addArgument("length");
		tester.addArgumentType("Integer");
		tester.addArgument("width");
		tester.addArgumentType("Float");
		tester.addArgument("height");
		tester.addArgumentType("Boolean");
		tester.addArgument("dog");
		tester.addArgumentType("String");
		assertEquals("Integer", tester.getArgumentType("length"));
		assertEquals("Float", tester.getArgumentType("width"));
		assertEquals("Boolean", tester.getArgumentType("height"));
		assertEquals("String", tester.getArgumentType("dog"));
	}
	
	@Test
	public void testParseType()
	{
		ArgumentParser tester = new ArgumentParser();
		tester.addArgument("length");
		tester.addArgumentType("Integer");
		tester.addArgument("width");
		tester.addArgumentType("Float");
		tester.addArgument("height");
		tester.addArgumentType("Boolean");
		tester.addArgument("dog");
		tester.addArgumentType("String");
		try
		{
			tester.parseType("VolCalc 7 5.2 false");
		}catch(NotEnoughArgValuesException  | TooManyArgValuesException e){
			assertTrue(false);
		}
		
	}
	
}
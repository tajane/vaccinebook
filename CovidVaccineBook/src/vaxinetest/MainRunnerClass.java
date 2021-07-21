package vaxinetest;
import java.io.IOException;
import org.testng.TestNG;

public class MainRunnerClass 
{

	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		 
    	 
		TestNG testNG = new TestNG();
	    testNG.setTestClasses(
	      new Class[] { 
	    		  VaccineClass.class });

	    testNG.run();

	}

}

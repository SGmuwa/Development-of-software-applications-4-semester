public class Calc_Test {
	public static void main(String[] args)
	{
		Calc exmpl = new Calc(false);
		log(true, "step 1");
		log(true, "step 1.1");
		test_Add(exmpl);
		log(true, "step 1.2");
		test_Sub(exmpl);
		log(true, "step 1.3");
		test_Mul(exmpl);
		log(true, "step 1.4");
		test_Div(exmpl);
		log(true, "step 2");
		exmpl.SetFloatIsEnabled(true);
		log(true, "step 2.1");
		test_Add(exmpl);
		log(true, "step 2.2");
		test_Sub(exmpl);
		log(true, "step 2.3");
		test_Mul(exmpl);
		log(true, "step 2.4");
		test_Div(exmpl);
		log(true, "end.");
	}
	
	public static void log(boolean IsStatusGood, String message) {
		if(IsStatusGood) System.out.println(message);
		else {
			System.out.println("Error: " + message);
			System.err.println(message);
		}
	}

	public static void test_Add(Calc exmpl) {
		if(exmpl.FloatIsEnabled()) {
			if(exmpl.addFloat(1.5f, 2.5f) == 4.0f) log(true, "addFloat: ok");
			else log(false, "addFloat: failed");

			if(exmpl.addFloat(1.0f, 2.0f) == 3.0f) log(true, "addFloat: ok");
			else log(false, "addFloat: Test failed");

			if(exmpl.addFloat(0.0f, 0.0f) == 0.0f) log(true, "addFloat: ok");
			else log(false, "addFloat: Test failed");

			if(exmpl.addFloat(-5.0f, 4.0f) == -1.0f) log(true, "addFloat: ok");
			else log(false, "addFloat: Test failed");
		}
		else
		{
			if(exmpl.addFloat(1.5f, 2.5f) == 4.0f) log(false, "addFloat: failed");
			else log(true, "addFloat: ok");
		}
		
		if(exmpl.add(1, 2) == 3) log(true, "add: ok");
		else log(false, "add: Test failed");

		if(exmpl.add(0, 0) == 0) log(true, "add: ok");
		else log(false, "add: Test failed");

		if(exmpl.add(-5, 4) == -1) log(true, "add: ok");
		else log(false, "add: Test failed");
	}
	
	public static void test_Sub(Calc exmpl){
		if (exmpl.FloatIsEnabled())
		{
			if(exmpl.subFloat(2.5f, 1.5f) == 1.0f) log(true, "subFloat: ok");
			else log(false, "subFloat: failed");

			if(exmpl.subFloat(1.0f, 2.0f) == -1.0f) log(true, "subFloat: ok");
			else log(false, "subFloat: Test failed");

			if(exmpl.subFloat(0.0f, 0.0f) == 0.0f) log(true, "subFloat: ok");
			else log(false, "subFloat: Test failed");

			if(exmpl.subFloat(-5.0f, 4.0f) == -9.0f) log(true, "subFloat: ok");
			else log(false, "subFloat: Test failed");
		}
		else
		{
			if(exmpl.subFloat(1.5f, 2.5f) == -1.0f) log(false, "subFloat: failed");
			else log(true, "subFloat: ok");
		}

		if(exmpl.sub(2, 1) == 1) log(true, "sub: ok");
		else log(false, "sub: Test failed");

		if(exmpl.sub(0, 0) == 0) log(true, "sub: ok");
		else log(false, "sub: Test failed");

		if(exmpl.sub(-5, 4) == -9) log(true, "sub: ok");
		else log(false, "sub: Test failed");
	}
	
	public static void test_Mul(Calc exmpl) {
		if (exmpl.FloatIsEnabled())
		{
			if(exmpl.mulFloat(2.5f, 5.0f) == 12.5f) log(true, "mulFloat: ok");
			else log(false, "mulFloat: failed");

			if(exmpl.mulFloat(1.0f, 2.0f) == 2.0f) log(true, "mulFloat: ok");
			else log(false, "mulFloat: Test failed");

			if(exmpl.mulFloat(0.0f, 0.0f) == 0.0f) log(true, "mulFloat: ok");
			else log(false, "mulFloat: Test failed");

			if(exmpl.mulFloat(-5.0f, 4.0f) == -20.0f) log(true, "mulFloat: ok");
			else log(false, "mulFloat: Test failed");
		}
		else
		{
			if(exmpl.mulFloat(1.5f, 2.5f) == 3.75f) log(false, "mulFloat: failed");
			else log(true, "mulFloat: ok");
		}
		if(exmpl.mul(2, 1) == 2) log(true, "mul: ok");
		else log(false, "mul: test failed");

		if(exmpl.mul(0, 0) == 0) log(true, "mul: ok");
		else log(false, "mul: Test failed");

		if(exmpl.mul(-5, 4) == -20) log(true, "mul: ok");
		else log(false, "mul: Test failed");
	}
	
	public static void test_Div(Calc exmpl){
		if (exmpl.FloatIsEnabled())
		{
			if(exmpl.divFloat(8.2f, 4.1f) == 2.0f) log(true, "divFloat: ok");
			else log(false, "divFloat: failed");

			if(exmpl.divFloat(1.0f, 2.0f) == 0.5f) log(true, "divFloat: ok");
			else log(false, "divFloat: Test failed");

			if(((Float)exmpl.divFloat(0.0f, 0.0f)).isNaN()) log(true, "divFloat: ok");
			else log(false, "divFloat: Test failed");

			if(exmpl.divFloat(-5.0f, 4.0f) == -1.25f) log(true, "divFloat: ok");
			else log(false, "divFloat: Test failed");
		}
		else
		{
			if(exmpl.divFloat(1.5f, 2.5f) == 0.6f) log(false, "divFloat: failed");
			else log(true, "divFloat: ok");
		}
		
		if (exmpl.div(8, 2) == 4) log(true, "div: ok");
		else log(false, "div: test failed");

		try {
			exmpl.div(0, 0);
			log(false, "div: failed: div on zero.");
		}
		catch (Throwable t) { log(false, "div on zero: ok. Error log: " + t.getLocalizedMessage()); }

		if(exmpl.div(-5, 4) == -1) log(true, "div: ok");
		else log(false, "div: Test failed");
	}
}

public class Calc {
	
	Calc() { this(false); }
	Calc(boolean FloatIsEnabled) { _floatIsEnabled = false; }

	void SetFloatIsEnabled(boolean value) { _floatIsEnabled = value; }

	private boolean _floatIsEnabled;

	boolean FloatIsEnabled() { return _floatIsEnabled; }

	public int add(int a, int b){ return a+b; }
	public float addFloat(float a, float b){ return _floatIsEnabled ? a+b : Float.NaN; }
		
	public int sub(int a, int b){ return a-b; }
	public float subFloat(float a, float b){ return _floatIsEnabled ? a-b : Float.NaN; }
		
	public int mul(int a, int b){ return a*b; }
	public float mulFloat(float a, float b){ return _floatIsEnabled ? a*b : Float.NaN; }
		
	public int div(int a, int b) throws ArithmeticException { return a/b; }
	public float divFloat(float a, float b){ return _floatIsEnabled ? a/b : Float.NaN; }
	
	
}
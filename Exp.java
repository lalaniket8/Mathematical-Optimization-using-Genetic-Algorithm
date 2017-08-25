import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Exp {

	private String exp;//stores expression (f(x)) which is in terms of y = f(x) and objective fun (f(x,y)) in terms of z = f(x,y)
	
	//if expression is y=f(x), isfx flag is set true
	//if expression is x=f(y), isfx flag is set false
	private Boolean isfx = true;

	//if expression is objective fun, then isObj flag is set true
	//otherwise false
	private Boolean isObjFun = false;
	
	//get expression
	public String getExp() {
		return exp;
	}
	
	//initialize exp and isfx
	public void setExp(String exp,Boolean isfx,Boolean isObjFun) {
		this.exp = exp;
		this.isfx = isfx;
		this.isObjFun = isObjFun;
	}
	
	@Override
	public String toString() {
		return this.exp;
	}
	
	/*
	 *Expression is a String.
	 *To evaluate such an expression we use javascript engine from ScriptEngine Library 	
	 */
	private ScriptEngineManager mgr = new ScriptEngineManager();
    private ScriptEngine engine = mgr.getEngineByName("JavaScript");
	    
    //addValue() gives value to a variable in the expression
    //key = variable in expression
    //value = its value
    public void addValue(String key, Object value) {
    	engine.put(key,value);
    }
    
    public Boolean getisfxFlag() {
    	return this.isfx;
    }
    
    public Boolean getisObjFunFlag() {
    	return this.isObjFun;
    }
    
    public Boolean satisfy(double x, double y) {
    	if(isfx)
		{this.addValue("x", x);
		
		 if(y <= this.solve())
			 return true;
		 else
			 return false;
		}
	else
	{this.addValue("y", y);
	//System.out.println(this.exp+":"+x+","+y+"="+this.solve());
	if(x <= this.solve())
		 return true;
	 else
		 return false;
	}
    	
    }
    
    public ArrayList<SimplePoint> getCoordsSet(double lowerLimit, double upperLimit, double step) {
		if(!isObjFun)
		{
		ArrayList<SimplePoint> coordsSet = new ArrayList<SimplePoint>();
		for(double i = lowerLimit; i<upperLimit; i+=step) {
			if(isfx)
				{this.addValue("x", i);
				 SimplePoint sp = new SimplePoint();
				 sp.x = i;
				 sp.y = this.solve();
				 coordsSet.add(sp);
				}
			else
			{this.addValue("y", i);
			SimplePoint sp = new SimplePoint();
			 sp.x = this.solve();
			 sp.y = i;
			 coordsSet.add(sp);
			}
		}
    	return coordsSet;
		}
		else
			return null;
    }
	    
    public double solve(double x, double y) {
    	if(isObjFun){
    		this.addValue("x", x);
    		this.addValue("y", y);
    		return this.solve();
    		}
    	else
    		return 0;
    }
    
    //return double value of expression after substituting all key with value obtained from addValue() method
    public double solve() {
	    try {
	    	
	    	Object res = engine.eval(exp);
	    	if(res instanceof Integer) 
	    	{return (double)((Integer) res).intValue();}
	    	else
	    		return (double) res;
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
    }
	
}

package simulator;

public class UninitializedValuesException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String exceptionMsg;
	UninitializedValuesException(String exceptionMsg){
		this.exceptionMsg = "Protected data members of generation class not initalized: "+exceptionMsg;
	}
	
	@Override
	public String toString() {
	return this.exceptionMsg;
}
}

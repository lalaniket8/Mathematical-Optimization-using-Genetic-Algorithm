
public class UninitializedValuesException extends Exception{
	String exceptionMsg;
	UninitializedValuesException(String exceptionMsg){
		this.exceptionMsg = "Protected data members of generation class not initalized: "+exceptionMsg;
	}
	
	@Override
	public String toString() {
	return this.exceptionMsg;
}
}

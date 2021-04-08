package simulator;

/*
 * PointUsageException Class is user defined exception that is thrown 
 * by Point Class
 */
public class PointUsageException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String exceptionMsg;

	PointUsageException(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	@Override
	public String toString() {
		return this.exceptionMsg;
	}
}

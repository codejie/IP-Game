package jie.android.ip.screen.box;

public class BoxException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public static final int E_TRAY_OUT = 10;
	public static final int E_TRAY_MISSING = 11;
	public static final int E_BLOCK_NOTFOUND = 100;
	public static final int E_BLOCK_NOTROOM = 101;
	public static final int E_DIRECTION_UNSUPPORT = 200;
	
	private final int error;
	
	public BoxException(int error) {
		super();
		this.error = error;
	}
	
	public BoxException(int error, final String what) {
		super(what);
		this.error = error;
	}
	
	public final int error() {
		return error;
	}
	
	public final String what() {
		return super.getMessage();
	}
	
	public final String toString() {
		if (what() != null) {
			return String.format("BoxException(%d) : %s", error, what());
		} else {
			return String.format("BoxException(%d) : null");
		}
	}
}

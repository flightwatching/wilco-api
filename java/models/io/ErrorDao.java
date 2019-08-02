package models.io;


public class ErrorDao {
	public final int code;
	public final String msg;
	public final String url;	
	
	public ErrorDao( int code, String message, String url) {
		this.code = code;
		msg = message;
		this.url = url; 
	}
	
	public ErrorDao( int code, String message) {
		this(code, message, null);
	}
	
	public ErrorDao( int code) {
		this(code, null, null);
	}
	
	
}
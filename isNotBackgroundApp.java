import java.lang.RuntimeException;

public class isNotBackgroundApp extends RuntimeException{
	public isNotBackgroundApp(){
		super("non-background apps cannot use getData()");
	}
}

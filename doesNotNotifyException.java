//import java.lang.Exception;
import java.lang.RuntimeException;

public class doesNotNotifyException extends RuntimeException{
	public doesNotNotifyException(){
		super("non-notifying apps cannot use properties of notifying apps");
	}
}

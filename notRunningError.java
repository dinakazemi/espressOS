import java.lang.RuntimeException;

public class notRunningError extends RuntimeException{
	public notRunningError(){
		super("a non-running application cannot send notification");
	}
}

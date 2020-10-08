import java.util.*;

public class Observer {
	private Set userThreads = new HashSet();

	public void addObserver( UserThread userThread ){
		this.userThreads.add( userThread );
	}

	public void removeObserver( UserThread userThread ){
		this.userThreads.remove( userThread );
	}

	public void notifyObservers( Message message, UserThread excludeUser ){
		Iterator iterator = userThreads.iterator();
		while (iterator.hasNext()) {
			UserThread user = (UserThread) iterator.next();
			if (user != excludeUser) {
				user.sendMessage(message);
			}
		}
	}
}

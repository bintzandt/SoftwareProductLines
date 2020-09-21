package chat.server;

import java.util.*;
import chat.messages.Message;

public class Observer {
	private Set<UserThread> userThreads = new HashSet<>();

	public void addObserver( UserThread userThread ){
		this.userThreads.add( userThread );
	}

	public void removeObserver( UserThread userThread ){
		this.userThreads.remove( userThread );
	}

	public void notifyObservers( Message message, UserThread excludeUser ){
		for (UserThread user : userThreads) {
			if (user != excludeUser) {
				user.sendMessage(message);
			}
		}
	}
}

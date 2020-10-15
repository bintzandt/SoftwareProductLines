public class Foo {
	// global variables to be set with aspect
	static int lockId;
	static int i;
	// debug param
	private final static boolean debug = true;
	
	/**
	 * This method is needed as the method from the assignment has an
	 * invalid method signature to be a  main method
	 */
	public static void main(String[] args) {
		Locker locker = new Locker();
		main("I am the parameter passed!", locker);
	}

	/**
	 * This is the actual method from the assignment
	 */
	public static void main(String parameter, Locker locker) {
//		int lockId = locker.lock();
//		try {
		int i = parameter.length();
		firstOperation(i);
//			log("First operation executed with parameter " + i);
		secondOperation(i);
			
//		} finally {
//		locker.unlock(lockId);
//		}

	}
	
	public static void firstOperation(int i) {
		float abc = 4 / 0;
		if (debug)
			System.out.println("firstOperation(" + i + ")");
	}
	
	public static void secondOperation(int i) {
		if (debug)
			System.out.println("secondOperation(" + i + ")");
	}
	
	public static void log(String s) {
		System.out.println(s);
	}

}

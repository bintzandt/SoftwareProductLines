
public aspect FooAspect {
	
	// original line 3 replacement	
	before(String parameter, Locker locker): execution(void Foo.main(String, Locker)) && args(parameter, locker) {
			int lockId = locker.lock();
			Foo.lockId = lockId;
	}
	
	// original line 7 replacement
	after(): execution(void Foo.firstOperation(int)) {
		Foo.log("First operation executed with parameter " + Foo.i);
	}
	
	// original line 10 replacement	
	after(String parameter, Locker locker) throwing(Exception e):
		execution(void Foo.main(String, Locker)) && args(parameter, locker)  {
			System.out.println("Throwing exception");
			locker.unlock(Foo.lockId);
	}
	
}

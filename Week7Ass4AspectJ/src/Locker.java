
public class Locker {
	
	public int lock() {
		System.out.println("Locker is locked!");
		return 42;
	}
	
	public void unlock(int id) {
		System.out.println("Locker " + id + " is unlocked!");
	}
	
}

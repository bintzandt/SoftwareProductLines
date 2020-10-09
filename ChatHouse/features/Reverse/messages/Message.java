public class Message {
	
	public void encrypt() {
		this.isEncrypted = true;
		this.m = new StringBuilder(new String(this.m)).reverse().toString().toCharArray();
	}
	
	public void decrypt() {
		if (this.isEncrypted) {
			this.m = new StringBuilder(new String(this.m)).reverse().toString().toCharArray();
		}
	}

}
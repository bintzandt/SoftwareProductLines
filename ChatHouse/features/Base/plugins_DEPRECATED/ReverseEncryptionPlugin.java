public class ReverseEncryptionPlugin extends Plugin {
	
	public ReverseEncryptionPlugin() {
		this.serverPlugin = new ServerReverseEncryptionPlugin();
		this.clientPlugin = new ClientReverseEncryptionPlugin();
	}
	
	public class ClientReverseEncryptionPlugin extends ClientPlugin {
		public void encryptMessage( Message message ){
			message.setEncrypted(true);
			message.m = new StringBuilder(new String(message.m)).reverse().toString().toCharArray();
		}
		
		public void decryptMessage(Message message ){
			if (message.isEncrypted) {
				String text = new String(message.m);
				message.m = new StringBuilder(text).reverse().toString().toCharArray();
			}
		}
	}
	
	public class ServerReverseEncryptionPlugin extends ServerPlugin {}

}

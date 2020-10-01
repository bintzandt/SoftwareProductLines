public class ReverseEncryptionPlugin extends Plugin {
	
	public ReverseEncryptionPlugin() {
		this.serverPlugin = new ServerReverseEncryptionPlugin();
		this.clientPlugin = new ClientReverseEncryptionPlugin();
	}
	
	public class ClientReverseEncryptionPlugin extends ClientPlugin {
		public String encryptMessage( String messageBody, Message message ){
			return new StringBuilder(new String(messageBody)).reverse().toString();
		}
		
		public String decryptMessage( String messageBody, Message message ){
			return messageBody;
		}
	}
	
	public class ServerReverseEncryptionPlugin extends ServerPlugin {}

}

public class ROTEncryptionPlugin extends Plugin {
	
	public ROTEncryptionPlugin() {
		this.clientPlugin = new ClientROTEncryptionPlugin();
		this.serverPlugin = new ServerROTEncryptionPlugin();
	}
	
	public class ClientROTEncryptionPlugin extends ClientPlugin {
		public void encryptMessage( Message message ){
//			message.setEncrypted(true);
			char[] m_encrypted = message.m.clone();
			for (int i = 0; i < m_encrypted.length; i++) {
				char c = m_encrypted[i];
				if (c >= 'a' && c <= 'm' || c >= 'A' && c <= 'M') {
					m_encrypted[i] = (char) (c + 13);
				} else if (c >= 'n' && c <= 'z' || c >= 'N' && c <= 'Z') {
					m_encrypted[i] = (char) (c - 13);
				}
			}
			message.m = m_encrypted;
		}
		
		public void decryptMessage(Message message ){
			if (message.isEncrypted) {
				char[] m_encrypted = message.m.clone();
				for (int i = 0; i < m_encrypted.length; i++) {
					char c = m_encrypted[i];
					if (c >= 'a' && c <= 'm' || c >= 'A' && c <= 'M') {
						m_encrypted[i] = (char) (c + 13);
					} else if (c >= 'n' && c <= 'z' || c >= 'N' && c <= 'Z') {
						m_encrypted[i] = (char) (c - 13);
					}
				}
				message.m = m_encrypted;
			}
		}
	}
	
	public class ServerROTEncryptionPlugin extends ServerPlugin {}

}

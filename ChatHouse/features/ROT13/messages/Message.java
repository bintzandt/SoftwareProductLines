public class Message {

	public void encrypt() {
		this.isEncrypted = true;
		char[] m_encrypted = this.m.clone();
		for (int i = 0; i < m_encrypted.length; i++) {
			char c = m_encrypted[i];
			if (c >= 'a' && c <= 'm' || c >= 'A' && c <= 'M') {
				m_encrypted[i] = (char) (c + 13);
			} else if (c >= 'n' && c <= 'z' || c >= 'N' && c <= 'Z') {
				m_encrypted[i] = (char) (c - 13);
			}
		}
		this.m = m_encrypted;
	}
	
	public void decrypt() {
		if (this.isEncrypted) {
			char[] m_encrypted = this.m.clone();
			for (int i = 0; i < m_encrypted.length; i++) {
				char c = m_encrypted[i];
				if (c >= 'a' && c <= 'm' || c >= 'A' && c <= 'M') {
					m_encrypted[i] = (char) (c + 13);
				} else if (c >= 'n' && c <= 'z' || c >= 'N' && c <= 'Z') {
					m_encrypted[i] = (char) (c - 13);
				}
			}
			this.m = m_encrypted;
		}
	}
	
}
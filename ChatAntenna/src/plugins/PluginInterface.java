public interface PluginInterface {
	public interface ServerPluginInterface {
		public default void onClientConnect( UserThread newUser ){}
		public default void onClientConnected( UserThread newUser ){}
	}

	public interface ClientPluginInterface {
		public default void afterClientCreation( Client client ){}
		public default String changeMessage( String messageBody, Message message ){ return messageBody; }
		public default String encryptMessage( String messageBody, Message message ){ return messageBody; }
		public default String decryptMessage( String messageBody, Message message ){ return messageBody; }
	}
}
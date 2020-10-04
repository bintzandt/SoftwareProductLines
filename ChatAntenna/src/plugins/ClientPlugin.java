public abstract class ClientPlugin {
	public void afterClientCreation( Client client, WriteThread writeThread ){}
	public String changeMessage( String messageBody, Message message ){ return messageBody; }
	public String encryptMessage( String messageBody, Message message ){ return messageBody; }
	public String decryptMessage( String messageBody, Message message ){ return messageBody; }
}

public abstract class ClientPlugin {
	public void afterClientCreation( Client client, WriteThread writeThread ){}
	public void changeMessage( Message message ){ }
	public void encryptMessage( Message message ){}
	public void decryptMessage( Message message ){ }
}

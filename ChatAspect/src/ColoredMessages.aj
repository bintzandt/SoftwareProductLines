public aspect ColoredMessages {
	pointcut P1( Message m ): execution(String Message.getMessageBody()) && this(m);
	
	String around(Message m): P1( m ){
		return m.color + proceed(m) + Color.RESET;
	}
}
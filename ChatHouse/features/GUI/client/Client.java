import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {
	private JFrame frame;
	private JLabel inputLabel;
	private JTextField inputField;
	private JTextArea outputField;

	private boolean enterPressed;
	
	public Client(String hostname, int port) {

		// Creating the Frame
		frame = new JFrame("Chat Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);

		// Creating the panel at bottom and adding components
		JPanel panel = new JPanel();
		inputLabel = new JLabel("Enter text");
		inputField = new JTextField(20);

		Action action = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				enterPressed = true;
			}
		};
		inputField.addActionListener(action);

		panel.add(inputLabel);
		panel.add(inputField);

		// Text Area at the Center
		outputField = new JTextArea();
		outputField.setLineWrap(true);
		JScrollPane outputPane = new JScrollPane(outputField);

		// Adding Components to the frame.
		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		frame.getContentPane().add(BorderLayout.CENTER, outputPane);

		frame.setVisible(true);

		enterPressed = false;
	}
	
	public String viewWaitForInput( String question ) {
		inputLabel.setText(question);
		try {
			while (!enterPressed) {
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enterPressed = false;
		String inputText = inputField.getText();
		inputField.setText("");
		viewOutput(question + inputText);
		return inputText;
	}
	
	public void viewOutput(String message) {
		outputField.append(message + "\n");
	}
}
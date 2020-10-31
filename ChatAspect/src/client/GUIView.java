import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUIView implements ViewInterface {

	JFrame frame;
	JLabel inputLabel;
	JTextField inputField;
	JTextArea outputField;

	boolean enterPressed;

	public GUIView() {
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

	public String waitForInput( String question ) {
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
		output(question + inputText);
		return inputText;
	}
	
	public void output( String message ){
		outputField.append(message + "\n");
	}
}

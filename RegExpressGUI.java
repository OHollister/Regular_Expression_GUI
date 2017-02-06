//Olga Hollister

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import javax.swing.border.Border;

public class RegExpressGUI extends JFrame implements ActionListener, KeyListener {
             // Declaring private variables
	private JLabel l1 = new JLabel("Enter  a File Name: ");
	private JLabel l2 = new JLabel("Enter an Expression: ");
	private JLabel l3 = new JLabel("Document :");
	private JButton button = new JButton("Search");
	private JTextField takeFileName = new JTextField(15);
	private JTextField takeExpression = new JTextField(15);
	private JTextArea matchInfo = new JTextArea();
	private JTextArea textOut = new JTextArea();
	private JPanel jp = new JPanel();
	private Border border = BorderFactory.createLineBorder(Color.BLACK);
	private final static String newline = "\n";// '\n' character represents newlines
	String match = "";// word to be found
	int cnt = 0;// count for matches

	public RegExpressGUI() {
		super("Search WORD Application");
		setLayout(new BorderLayout());
		// set editability and wrapStyleWord
		matchInfo.setEditable(false);
		matchInfo.setWrapStyleWord(true);
		textOut.setEditable(false);
		textOut.setLineWrap(true);
		textOut.setWrapStyleWord(true);

                            // set colors for JLabels
		l1.setForeground(Color.red);
		l2.setForeground(Color.red);
		l3.setForeground(Color.white);
		l1.setBackground(Color.black); // set background color for the label
		l1.setOpaque(true); // a component must be opaque for its background to be effective
		l2.setBackground(Color.black); // set background color for the label
		l2.setOpaque(true); 							
		l3.setBackground(Color.black); // set background color for the label
		l3.setOpaque(true); 
		
                             // set borders:
		l1.setBorder(BorderFactory.createLineBorder(Color.red));
		l2.setBorder(BorderFactory.createLineBorder(Color.red));
		matchInfo.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		textOut.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		// set JScrollPane:
		JScrollPane areaScrollPane1 = new JScrollPane(matchInfo);
		JScrollPane areaScrollPane2 = new JScrollPane(textOut);

		// set layOut and organize components
		jp.setLayout(new GridLayout(4, 2));
		jp.add(l1);
		jp.add(takeFileName);
		jp.add(l2);
		jp.add(takeExpression);
		jp.add(button);
		jp.add(areaScrollPane1);
		jp.add(l3);
		jp.add(areaScrollPane2);
                              add(jp, BorderLayout.CENTER);
		button.addActionListener(this);
		button.addKeyListener(this);

	}

	


public void doAction() throws FileNotFoundException {
    try {
	      String file = (takeFileName.getText());// getting a file name from a user
	      File mf = new File(file);
	      Scanner sc = new Scanner(mf);// scanner for the file
	      Pattern p = Pattern.compile(takeExpression.getText().trim());// getting expression 
															while (sc.hasNext()) {
				String output = sc.nextLine();
				textOut.append(output + newline);// output the file
				Matcher m = p.matcher(output);
				while (m.find()) {
	                        cnt++;
					int start = m.start();
					int end = m.end();
					match = output.substring(start, end);
                              matchInfo.append("\nMatch: " + cnt);
					matchInfo.append("\n WORD is: " + match);
					matchInfo.append("\n Start :" + start + " End:                          
			" + end+ "\n");  }}
							
	} catch (PatternSyntaxException ex) {
			matchInfo.append("Incorrect Regex syntax");
		}
	}

	public void actionPerformed(ActionEvent e) {

		try {
			doAction();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {

			try {
				doAction();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace(); }}}
			


	public void keyTyped(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}

	public static void main(String[] args) {
		RegExpressGUI f = new RegExpressGUI();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 300);
		f.setVisible(true);
	}
}

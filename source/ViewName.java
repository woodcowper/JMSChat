package zad2;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ViewName extends JFrame {
	private Chat chat; 
	private JPanel namePanel;
	private JLabel nameLabel;
	private JTextField nameField;
	private JButton jb;
	public ViewName(Chat sc){
		this.chat = sc;
		
		
		 nameLabel = new JLabel("Enter your name", SwingConstants.CENTER);
		 namePanel = new JPanel();
		 nameField = new JTextField(SwingConstants.CENTER);
		nameField.setPreferredSize(new Dimension(100,20));
		 jb = new JButton("OK");
		jb.setPreferredSize(new Dimension(40,20));
		jb.setMargin(new Insets(0,0,0,0));
	
		   ActionListener actions = new ActionListener()
		    {
		        @Override
		        public void actionPerformed(ActionEvent ae)
		        {
		            if (ae.getSource() == jb)
		            {
		            	if(!(nameField.getText().matches("[a-zA-Z_0-9]+"))) {
		            		JOptionPane.showMessageDialog(namePanel,"Wrong user name!\n Try again!");
		            	}else {
		            	chat.openChat(nameField.getText());
						dispose();
		            	}
		            }
		            else if (ae.getSource() == nameField)
		            {
		                jb.doClick();
		            }
		        }
		    }; 
		    
			
		
		nameField.addActionListener(actions);
		jb.addActionListener(actions);
		
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		namePanel.add(jb);
		namePanel.setPreferredSize(new Dimension(200,60));
		this.add(namePanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	
	

}

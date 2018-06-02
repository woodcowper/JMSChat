package zad2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.jms.JMSException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class ViewChat extends JFrame {
	
	private JTextArea chatArea; 
	private JTextField chatField;
	private JButton  sendButton;
	private JPanel panelChat; 
	private Chat chat;
	
	ViewChat(Chat sc){
		this.chat = sc;
		
		panelChat = new JPanel();
		panelChat.setPreferredSize(new Dimension(220,200));
		
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		chatArea.setWrapStyleWord(true);
		chatArea.setLineWrap(true);
		JScrollPane scrollForChatArea = new JScrollPane(chatArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollForChatArea.setPreferredSize(new Dimension(230,170));
		chatField = new JTextField();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		chatField.setBorder(border);
		chatField.setPreferredSize(new Dimension(100,20));
		
		sendButton = new JButton("Send");
		sendButton.setPreferredSize(new Dimension(40,20));
		sendButton.setMargin(new Insets(0,0,0,0));
		
		
		ActionListener actions = new ActionListener()
	    {
	        @Override
	        public void actionPerformed(ActionEvent ae)
	        {
	            if (ae.getSource() == sendButton)
	            {
	            	
	            	try {
	            		if(!(chatField.getText().equals("")))
	    				chat.sendMsg(chatField.getText());
	    				} catch ( JMSException e1) {
	    					e1.printStackTrace();
	    				}
	    				chatField.setText("");
	                
	            }
	            else if (ae.getSource() == chatField)
	            {
	                sendButton.doClick();
	            }
	        }
	    }; 
		
		
		
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent 	windowEvent) {
				dispose();
			}
			
			public void windowOpened( WindowEvent e){ 
			    chatField.requestFocus();
			  } 
			
		});
		
		
		sendButton.addActionListener(actions);
		chatField.addActionListener(actions);
		
		panelChat.add(scrollForChatArea);
		panelChat.add(chatField);
		panelChat.add(sendButton);

		this.add(panelChat);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	
	
	public void setText( String msg) {
		 Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		chatArea.append("["+sdf.format(cal.getTime())+"]"+msg+'\n');
	}
	

}

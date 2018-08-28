import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/**
 * GUI for DES
 * @author MItchell Sheep
 * @since 4/16/2018
 * @version 2.0
 *
 */
 
public class Out extends JFrame
{
	private JLabel label = new JLabel("Enter 10 bit Key:");
	private JTextField text = new JTextField("0000000000");
	private JLabel label2 = new JLabel("Enter 8 bit message(one ASCII):");
	private JTextField text2 = new JTextField("Ex. A");
	private JTextField text3 = new JTextField("");
	private JLabel label3 = new JLabel("Key K1: ");
	private JLabel label4 = new JLabel("Key K2: ");
	private JLabel label5 = new JLabel("After Permutation: ");
	private JLabel label6 = new JLabel("Before Swap: ");
	private JLabel label7 = new JLabel("After Swap: ");
	private JLabel label8 = new JLabel("Before IP Inverse: ");
	private JLabel label9 = new JLabel("Encrypted Message: ");
	private JLabel labelA = new JLabel("Enter encrypted message:");
	private JTextField textA = new JTextField("0000000000");
	private JLabel labelB = new JLabel("After Permutation: ");
	private JLabel labelC = new JLabel("Before Swap: ");
	private JLabel labelD = new JLabel("After Swap: ");
	private JLabel labelE = new JLabel("Before Extraction Permutation: ");
	private JLabel labelF = new JLabel("After Extraction Permutation: ");
	private JLabel labelG = new JLabel("Decrypted Message: ");
	private JLabel labelH = new JLabel("Decrypted PlainText: ");
	private JLabel build = new JLabel("");
	private JLabel queue= new JLabel("Queue:");
	private JButton button = new JButton("Encrypt");
	private JButton button2 = new JButton("Decrypt");
	private JButton button3 = new JButton("Reset");
	private JButton button4 = new JButton("Decrypt Queue");
	private Border border = BorderFactory.createLineBorder(Color.BLACK, 4);
	private Border border2 = BorderFactory.createLineBorder(Color.WHITE, 2);
	private int K;
	private SDES A;
	private int[] m;
	private int[] k;
	private ArrayList<String> d = new ArrayList<String>();;

 
	public Out()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800, 1000));
		setLayout(null);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("DES Encrypt/Decrypt");
		Image icon = Toolkit.getDefaultToolkit().getImage("enc.ico");
		setIconImage(icon);
		
		
		//Encrypt label setup
		label.setBorder(border);
		label.setBounds(0, 50, 200, 50);
		text.setBounds(225, 50, 250, 50);
		text.setFont(text.getFont().deriveFont(18f));
		label2.setBorder(border);
		label2.setBounds(0, 100, 200, 50);
		text2.setBounds(225, 100, 250, 50);
		text2.setFont(text2.getFont().deriveFont(18f));
		text3.setBounds(150, 450, 325, 50);
		text3.setFont(text2.getFont().deriveFont(18f));
		label3.setBorder(border);
		label3.setBounds(0, 150, 475, 50);
		label4.setBorder(border);
		label4.setBounds(0, 200, 475, 50);
		label5.setBorder(border);
		label5.setBounds(0, 250, 475, 50);
		label6.setBorder(border);
		label6.setBounds(0, 300, 475, 50);
		label7.setBorder(border);
		label7.setBounds(0, 350, 475, 50);
		label8.setBorder(border);
		label8.setBounds(0, 400, 475, 50);
		label9.setBorder(border);
		label9.setForeground(Color.YELLOW);
		label9.setBounds(0, 450, 475, 50);
		
		//decrypt label setup
		labelA.setBorder(border);
		labelA.setBounds(0, 550, 200, 50);
		textA.setBounds(225, 550, 250, 50);
		textA.setFont(text.getFont().deriveFont(18f));
		labelB.setBorder(border);
		labelB.setBounds(0, 600, 475, 50);
		labelC.setBorder(border);
		labelC.setBounds(0, 650, 475, 50);
		labelD.setBorder(border);
		labelD.setBounds(0, 700, 475, 50);
		labelE.setBorder(border);
		labelE.setBounds(0, 750, 475, 50);
		labelF.setBorder(border);
		labelF.setBounds(0, 800, 475, 50);
		labelG.setBorder(border);
		labelG.setForeground(Color.CYAN);
		labelG.setBounds(0, 850, 475, 50);
		labelH.setBorder(border);
		labelH.setBounds(0, 900, 475, 50);
		
		
		button.setBounds(275, 10, 250, 25);
		button.setFont(button.getFont().deriveFont(18f));
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		         //get Key
		          K = Integer.parseInt(text.getText(),2);
		          A = new SDES(K);
		          // get Message
		          String s = text2.getText();
		          m = runDES.toBits(s);
		          //K1
		          label3.setText(label3.getText() + "	"+ SDES.printData( A.K1, 8));
		          //K2
		          label4.setText(label4.getText() + "	"+ SDES.printData( A.K2, 8));
		        	int h = A.encrypt(m[0]);
		          label5.setText(label5.getText() + "	"+ A.permute);
		          label6.setText(label6.getText() + "	"+ A.fK);
		          label7.setText(label7.getText() + "	"+ A.sw);
				  label8.setText(label8.getText() + "	"+ A.iP);
		          //Encrypted Message
				  String j = SDES.printData(h, 8);

		        	  d.add(j);
		        	  build.setText(build.getText() + ", "+ j);
				  text3.setText(j);
				
			}
		});
		
		button4.setBounds(275, 510, 250, 25);
		button4.setFont(button.getFont().deriveFont(18f));
		button4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				int[] p= new int[d.size()];
				for (int i = 0; i < d.size(); i++){
					
				 if (d.get(i) != null) {
					int con = Integer.parseInt(d.get(i));
					System.out.println(con);
					char out = runDES.bitsToString(A.decrypt(con));
					labelG.setText(labelG.getText() + SDES.printData(out, 8));
			          
			        labelH.setText(labelH.getText() + out);
				 }
				}

		          //dec meesage
				labelB.setText(labelB.getText() + A.permute);
				labelC.setText(labelC.getText() + A.fK);
				labelD.setText(labelD.getText() + A.sw);
				labelE.setText(labelE.getText() + A.bP);
				labelF.setText(labelF.getText() + A.aP);
				
			}
		});
		
		
		build.setBounds(485, 300, 300, 50);
		build.setBorder(border2);
		build.setBounds(485, 300, 300, 50);
		build.setBorder(border2);

		button3.setBounds(650, 450, 100, 50);
		button3.setFont(button.getFont().deriveFont(18f));
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				Out J = new Out();
			}
		});
		button2.setBounds(15, 510, 250, 25);
		button2.setFont(button.getFont().deriveFont(18f));
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = textA.getText();
				int val = Integer.parseInt(s, 2);
				int p = A.decrypt(val);
		         //dec meesage
				labelB.setText(labelB.getText() + A.permute);
				labelC.setText(labelC.getText() + A.fK);
				labelD.setText(labelD.getText() + A.sw);
				labelE.setText(labelE.getText() + A.bP);
				labelF.setText(labelF.getText() + A.aP);
		          labelG.setText(labelG.getText() + SDES.printData(p, 8));
		          char out = runDES.bitsToString(p);
		          //plaintext out
		          String g = labelH.getText() + out;
		          labelH.setText(g);
				
			}
		});
		
		
		
		
		
		//encrpyt
		add(label);
		add(text);
		add(label2);
		add(text2);
		add(label3);
		add(label4);
		add(label5);
		add(label6);
		add(label7);
		add(label8);
		add(label9);
		//decrypt
		add(labelA);
		add(textA);
		add(labelB);
		add(labelC);
		add(labelD);
		add(labelE);
		add(labelF);
		add(labelG);
		add(labelH);
		add(text3);
		add(build);
		
		//buttons
		add(button, BorderLayout.CENTER);
		add(button2, BorderLayout.CENTER);
		add(button3, BorderLayout.CENTER);
		add(button4, BorderLayout.CENTER);
 
		pack();//adjust frame size
		setVisible(true);
	}
}
 

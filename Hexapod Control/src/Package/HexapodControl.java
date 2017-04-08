package Package;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HexapodControl {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HexapodControl window = new HexapodControl();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HexapodControl() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("Desktop.background"));
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
			     JavaSerial.endConnection();
			   }
			  });
		frame.getContentPane().setLayout(null);
		
		JButton btnUp = new JButton("up");
		btnUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JavaSerial.writeByte('1');
			
			}
		});
		
		btnUp.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnUp.setBounds(105, 67, 117, 61);
		frame.getContentPane().add(btnUp);
		
		JButton btnDown = new JButton("down");
		btnDown.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnDown.setBounds(105, 213, 117, 61);
		frame.getContentPane().add(btnDown);
		
		
		JButton btnLeft = new JButton("left");
		btnLeft.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnLeft.setBounds(17, 140, 117, 61);
		frame.getContentPane().add(btnLeft);
		
		
		JButton btnRight = new JButton("right");
		btnRight.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnRight.setBounds(190, 140, 117, 61);
		frame.getContentPane().add(btnRight);
		
		
		JLabel lblNewLabel = new JLabel("Hexapo Control");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblNewLabel.setBounds(36, 6, 262, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JavaSerial.initialize();
	}
	

}

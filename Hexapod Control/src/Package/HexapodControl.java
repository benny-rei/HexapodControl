package Package;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class HexapodControl {

	private JFrame frame;
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnLeft;
	private JButton btnRight;
	public static boolean moveFertig = false;

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
		
		int timerDelay = 1;
	      final Timer timer = new Timer(timerDelay , new ActionListener() {

	         @Override
	         public void actionPerformed(ActionEvent e) {
	        	 //System.out.println("timer");
	        	 
	        	 if(JavaSerial.isReady()){
	        		 moveFertig = true;
	        	 }else{
	        		 moveFertig = false;
	        	 }
	        	 
	        	 if(moveFertig){
	        	 	JavaSerial.writeByte('F');
	        	 	moveFertig = false;
	        	 }
	    
	        }
	      });

		
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
		
		btnUp = new JButton("up");
		ButtonModel btnUpModel = btnUp.getModel();
		btnUp.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				 if (btnUpModel.isPressed() && !timer.isRunning()) {
		               timer.start();
		               JavaSerial.writeByte('F');
		         } else if (!btnUpModel.isPressed() && timer.isRunning()) {
		               timer.stop();
		         }
				
			}
		});
		btnUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("F gesendet");
				//JavaSerial.writeByte('F');
				System.out.println("Abgeschlossen");
				
			}
		});
		
		btnUp.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnUp.setBounds(105, 67, 117, 61);
		frame.getContentPane().add(btnUp);
		
		btnDown = new JButton("down");
		btnDown.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnDown.setBounds(105, 213, 117, 61);
		frame.getContentPane().add(btnDown);
		
		
		btnLeft = new JButton("left");
		btnLeft.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnLeft.setBounds(17, 140, 117, 61);
		frame.getContentPane().add(btnLeft);
		
		
		btnRight = new JButton("right");
		btnRight.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnRight.setBounds(190, 140, 117, 61);
		frame.getContentPane().add(btnRight);
		
		
		JLabel lblNewLabel = new JLabel("Hexapo Control");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblNewLabel.setBounds(36, 6, 262, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnConnect = new JButton("connect");
		btnConnect.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JavaSerial.initialize();
			}
		});
		btnConnect.setBounds(352, 282, 117, 29);
		frame.getContentPane().add(btnConnect);

	}
	
	
}

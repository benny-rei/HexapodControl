package Package;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import jssc.SerialPort;
import jssc.SerialPortException;

///////////////////////////////////////////////////////
//
// Fernsteuerung eines Arduino mit HC-05 / 06
// 
///////////////////////////////////////////////////////



public class JavaSerial {
	
	// Wartezeit in Sekunden, um auf Arduino zu warten
	final static int SECS = 5;
	static SerialPort serialPort = new SerialPort("/dev/tty.HC-06-DevB");

	
	public static void initialize(){
		/*
		 * try { // Port öffnen: serialPort.openPort();
		 * System.out.println("Verbindung geöffnet"); // Kommunikationsparameter
		 * einstellen: serialPort.setParams(SerialPort.BAUDRATE_9600,
		 * SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
		 * SerialPort.PARITY_NONE);
		 * 
		 * // SECS Sekunden auf Arduino-Bereitschaft warten: for (int i = 0; i <
		 * SECS; i++) { System.out.print("."); Thread.sleep(1000); // 1 sec
		 * Pause } System.out.println("... fertig");
		 * 
		 * System.out.println("Und jetzt sende ich:"); for (int i = 1; i <= 5;
		 * i++) { System.out.println("Iteration " + i); // e senden
		 * serialPort.writeBytes("e".getBytes());
		 * System.out.println("e (ein) gesendet"); Thread.sleep(1000); // a
		 * senden serialPort.writeBytes("a".getBytes());
		 * System.out.println("a (aus) gesendet"); Thread.sleep(1000); } // Alle
		 * Schotten dicht: serialPort.closePort(); } catch (SerialPortException
		 * ex) { System.out.println(ex); } catch (InterruptedException ex) {
		 * System.out.println(ex); }
		 */
		try{
			if(!serialPort.isOpened()){ 
				serialPort.openPort();
				System.out.println("geöffnet");
			}
			System.out.println("Verbindung geöffnet");
			// Kommunikationsparameter einstellen:
			serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
	
			// SECS Sekunden auf Arduino-Bereitschaft warten:
			for (int i = 0; i < SECS; i++) {
				System.out.print(".");
				Thread.sleep(1000); // 1 sec Pause
			}
			System.out.println("... fertig");
	
			System.out.println("Initialisierung abgeschlossen");
			
		} catch (SerialPortException ex) {
			System.out.println(ex);
		} catch (InterruptedException ex) {
			System.out.println(ex);
		}
		
	}

	public static void writeByte(char c) {
		String ausgangsByte = ""+c;

		try {
			
			// byte/char senden
			serialPort.writeBytes(ausgangsByte.getBytes());
			System.out.println("byte gesendet");
			//Thread.sleep(1000);
			
			String s = "";
			
			/*while(!s.contains("ready")){
				s="";
				byte bytes[] = serialPort.readBytes();
				
				if(bytes == null) return;
				
				for(byte b : bytes) s += (char) b;
				
				System.out.println("Habe den String erhalten: "+s);
				
			}*/
						
			HexapodControl.moveFertig = true;
		} catch (SerialPortException ex) {
			System.out.println(ex);
		}
		
		
	}
	
	public static void isReady(){
		String s = "";
		
		
		s="";
		byte bytes[];
		try {
			bytes = serialPort.readBytes();
		
			
			if(bytes == null) return;
			
			for(byte b : bytes) s += (char) b;
			
			System.out.println("Habe den String erhalten: "+s);
			
			HexapodControl.moveFertig=true;
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	public static void endConnection(){
		try {
			if(!serialPort.isOpened()) serialPort.closePort();
			System.out.println("Connection beendet");
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
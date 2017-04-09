package Package;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JavaSerial.initialize();
		
		
		for(int i = 0; i<10; i++){
			Scanner scan = new Scanner(System.in);
			System.out.println("Eingabe: ");
			String b = scan.next();
			
	
				
			//JavaSerial.writeByte(b.charAt(0));
			JavaSerial.writeByte('F');
		}
		
		JavaSerial.endConnection();
	}

}

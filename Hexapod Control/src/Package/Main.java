package Package;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JavaSerial.initialize();
		
		
		
		for(int i = 0; i<= 100; i++){
			
			JavaSerial.writeByte('1');
			
		}	
	
		JavaSerial.endConnection();
	}

}

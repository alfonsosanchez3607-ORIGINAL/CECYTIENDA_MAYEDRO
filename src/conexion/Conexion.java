package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
public class Conexion {
	
	Connection cx=null;
	public Connection conectar(){
		try {
			cx=DriverManager.getConnection("jdbc:mysql://localhost/puntodeventa","root","");
			System.out.println("LIKE");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cx;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

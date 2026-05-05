package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Entidades.Ventas;
import conexion.Conexion;
public class DataVentas {
	Conexion c = new Conexion();
	public int insertarVenta(Ventas v) {
	    PreparedStatement ps = null;
	    int ultimoId = 0;
	    try {
	        ps = c.conectar().prepareStatement("INSERT INTO ventas VALUES(null,null,?,?,?)",
	        Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, v.getCliente());
	        ps.setDouble(2, v.getTotal());
	        ps.setString(3, v.getMetodo());
	        int fila = ps.executeUpdate();
            if (fila > 0) {
            	try (ResultSet generatedKeys=ps.getGeneratedKeys()){
            		if (generatedKeys.next()) {
            			ultimoId =generatedKeys.getInt(1);
            			System.out.println("Ultomo ID insertado:"+ ultimoId);
            		}else {
            			System.out.println("No se pudo obtener el ID generado");
            		}
            		}
            	}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return ultimoId;
	}
}


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diana
 */
class conexion {
        
    public Connection Conectar(){
	Connection cn = null;
        try{
            //Establecemos la conexion
          String conecctionUrl = "jdbc:sqlserver://DESKTOP-VAFKAHK:1433;databaseName=SOLHARD";
            //String conecctionUrl = "DESKTOP-VAFKAHK;databaseName=SOLHARD;integratedSecurity=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(conecctionUrl, "sa", "123");
            System.out.println("Conexion Exitosa!");
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error de Conexion!");
        }
        return cn;
    }
    
    public void InsertarDatos(String nombre, String email){
	Connection cn = Conectar();
        try{
	   PreparedStatement pst = cn.prepareStatement("INSERT INTO usuarios (nombre,email) VALUES (?,?)");
	   pst.setString(1,nombre);
	   pst.setString(2,email);
	   pst.execute();
	}catch(SQLException ex){
	   System.out.println("Error al insertar Registros");
	}
    }
    
    public ResultSet SeleccionarTodo(String consulta){
	Connection cn = Conectar();
	Statement st;
	ResultSet rs = null;
	try{
	   st = cn.createStatement();
	   rs = st.executeQuery(consulta);
	}catch(SQLException ex){
	   System.out.println("Error al realizar la consulta");
	}
	return rs;
    }
    public ResultSet SeleccionarUno(String consulta,String nombre){
	Connection cn;
	PreparedStatement pst;
	ResultSet rs = null;
	try{
	   cn = Conectar();
	   pst = cn.prepareStatement(consulta+"=?");
           pst.setString(1,nombre);
	   rs = pst.executeQuery();
	}catch(Exception e){	}
	return rs;
    }
    public void InsertarDatos(String consulta,String nombre, String email){
	Connection cn = Conectar();
        try{
	   PreparedStatement pst = cn.prepareStatement(consulta+"(nombre,email) VALUES (?,?)");
           pst.setString(1,nombre);
	   pst.setString(2,email);
	   pst.execute();
	}catch(SQLException ex){
	   System.out.println("Error al insertar Registros");
	}
    }
    public ResultSet EliminarUno(String consulta,String nombre){
	Connection cn;
	PreparedStatement pst;
	ResultSet rs = null;
	try{
	   cn = Conectar();
	   pst = cn.prepareStatement(consulta+" WHERE NOM_CT =?");
           pst.setString(1,nombre);
	   rs = pst.executeQuery();
	}catch(Exception e){	}
	return rs;
    }
    
}

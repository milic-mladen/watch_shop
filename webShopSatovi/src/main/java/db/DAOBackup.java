package db;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;




public class DAOBackup {

	DbManager db = new DbManager();
	
	//DEFINICIJU SVIH SQL UPITA KOJE KORISTIMO NAD BAZOM 
     private static String PRIKAZ_SVIH_AUTOMOBILA = "SELECT * FROM automobili";
	private static String OBRISI_AUTO = "DELETE FROM automobili WHERE id_auta=?";
	private static String PRIKAZ_AUTA_PO_IDU = "SELECT * FROM automobili WHERE id_auta=?";
	private static String IZMENI_AUTO = "UPDATE automobili SET marka =?, cena=?,godiste=? WHERE id_auta=?";
	private static String PRIKAZ_AUTA_PO_ZADATIM_CENAMA ="SELECT * FROM automobili WHERE cena>=? AND cena<=?";
	
	
	
	
	
	
	
	
	
	
	
	
	//DEFINICIJA METODA - SVAKI GORE DEFINISAN UPIT CE IMATI SVOJU METODU 
     
     
     /*
     
	public ArrayList<Auto> prikazSvihAutomobila(){
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		conn = db.getConnection();
		ArrayList<Auto> listaSvihAutomobila = new ArrayList<Auto>();
		
		try {
			
		   pst = conn.prepareStatement(PRIKAZ_SVIH_AUTOMOBILA);
			
			//executeQuery vraca rezultat iz baze podataka 
	    	result = pst.executeQuery();
			
	    	while (result.next()) {
				
	    		Auto  auto = new Auto();
				auto.setMarka(result.getString("marka"));
				auto.setCena(result.getDouble("cena"));
				auto.setGodiste(result.getInt("godiste"));
			    auto.setId(result.getInt("id_auta"));
			    
			   
				listaSvihAutomobila.add(auto);
			}
	    	
	    	return listaSvihAutomobila;
						
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}finally {
			// zatvaranje konekcije i zatvarenje prepare statement-a
			db.closeConnection(conn);
			db.closePrepareStmt(pst);
			
		}
				
	}
	
	
	public void obrisiAuto(int id) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		
		try {
			conn = db.getConnection();
			  pst = conn.prepareStatement(OBRISI_AUTO);
			 
			  pst.setInt(1, id);
			  pst.execute();
			  
				
			} catch (Exception e) {
				e.printStackTrace();
				
				
			}finally {
				// zatvaranje konekcije i zatvarenje prepare statement-a
				db.closeConnection(conn);
				db.closePrepareStmt(pst);
				
			}
		
	}
	
	
	public Auto vratiAutoPoIdu (int id) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		
		Auto auto = null;
		try {
			conn = db.getConnection();
			  pst = conn.prepareStatement(PRIKAZ_AUTA_PO_IDU);
			 
			  pst.setInt(1, id);
			  pst.execute();
			  
			  result = pst.getResultSet();
			  
			  if(result.next()) {
				    auto = new Auto();
				    auto.setMarka(result.getString("marka"));
					auto.setCena(result.getDouble("cena"));
					auto.setGodiste(result.getInt("godiste"));
				    auto.setId(result.getInt("id_auta"));	  
			  }
			  
				
			} catch (Exception e) {
				e.printStackTrace();
				
				
			}finally {
				// zatvaranje konekcije i zatvarenje prepare statement-a
				db.closeConnection(conn);
				db.closePrepareStmt(pst);				
			}
		return auto;
		
	}
	
	
   public String izmeniAuto(Auto a) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		
		try {
			conn = db.getConnection();
			  pst = conn.prepareStatement(IZMENI_AUTO);
			 
			  pst.setString(1, a.getMarka());
			  pst.setDouble(2, a.getCena());
			  pst.setInt(3, a.getGodiste());
			  pst.setInt(4, a.getId());
			  pst.execute();
			  
				
			  return "Uspesna izmena";
			  
			  
			} catch (Exception e) {
				e.printStackTrace();
				return "";
				
			}finally {
				// zatvaranje konekcije i zatvarenje prepare statement-a
				db.closeConnection(conn);
				db.closePrepareStmt(pst);
				
			}
		
	}
   
   public ArrayList<Auto> prikazAutomobilaPoCeni(double cenaMin, double cenaMax){
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		conn = db.getConnection();
		ArrayList<Auto> listaFiltrirtanihAutomobila = new ArrayList<Auto>();
		
		try {
			
		   pst = conn.prepareStatement(PRIKAZ_AUTA_PO_ZADATIM_CENAMA);
		   
		   pst.setDouble(1, cenaMin);
		   pst.setDouble(2, cenaMax);

			//executeQuery vraca rezultat iz baze podataka 
	    	result = pst.executeQuery();
			
	    	while (result.next()) {
				
	    		Auto  auto = new Auto();
				auto.setMarka(result.getString("marka"));
				auto.setCena(result.getDouble("cena"));
				auto.setGodiste(result.getInt("godiste"));
			    auto.setId(result.getInt("id_auta"));
			    
			   
			    listaFiltrirtanihAutomobila.add(auto);
			}
	    	
	    	return listaFiltrirtanihAutomobila;
						
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}finally {
			// zatvaranje konekcije i zatvarenje prepare statement-a
			db.closeConnection(conn);
			db.closePrepareStmt(pst);
			
		}
				
	}
	*/
	

}

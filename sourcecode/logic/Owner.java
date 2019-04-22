package logic;

import java.util.ArrayList;

import data.DatabaseManager;
import data.TempHomestayRecord;

public class Owner {
	private String ownerID;
	private String name;
	private String password;
	private DatabaseManager db = new DatabaseManager(); //xinyong

	public Owner(String i, String n, String p) {
		ownerID = i;
		name = n;
		password = p;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Homestay> getAllHomestay() { //xinyong
		// TODO Auto-generated method stub
				 ArrayList<Homestay> ownerHomestayList = new ArrayList<Homestay>();
					
				 ArrayList<TempHomestayRecord> ownerHomestayRegister = db.getAllHomestay();
				 
				 for(TempHomestayRecord ownerHomestayRecord : ownerHomestayRegister){
					 
					 if (ownerHomestayRecord.getOwnerID().equals(this.getID())){
						 Homestay homestay = new Homestay(ownerHomestayRecord.getHomestayID(), ownerHomestayRecord.getName(),ownerHomestayRecord.getOwnerID(),ownerHomestayRecord.getAddress(),ownerHomestayRecord.getPrice(),ownerHomestayRecord.getType(), ownerHomestayRecord.getPhoto());
						 ownerHomestayList.add(homestay);
					 }
				 }
				 
				 return ownerHomestayList;
	}
	
	private String getID() { //xinyong
		// TODO Auto-generated method stub
		return ownerID;
	}
}

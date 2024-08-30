import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Bids extends ActionSupport {
	private ArrayList<Bids> myBids;
	private ArrayList<Bids> itemBids;
	private String itemName;
	private String bidder;
	private double price;

	// Getters & Setters
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBidder() {
		return bidder;
	}

	public void setBidder(String bidder) {
		this.bidder = bidder;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<Bids> getMyBids() {
		return myBids;
	}

	public ArrayList<Bids> getMyitemBids() {
		return itemBids;
	}

	public String viewAllBids() {
	
	    try {
	        itemBids = fetchAllBids();

	        return "SUCCESS";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "ERROR";
	    }
	}

	public String viewMyBids() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String currentUser = (String) session.get("currentUser");
		try {

			myBids = fetchMyBids(currentUser);

			return "SUCCESS";
		} catch (Exception e) {

			e.printStackTrace();
			return "ERROR";
		}
	}

	public String submitBid() {
		
		 if (price <= 0) {
		        return "ERROR";
		    }
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop?serverTimezone=UTC",
				"username", "password")) {
			try (PreparedStatement insertBid = connection
					.prepareStatement("INSERT INTO bids (item_name, bidder, price) VALUES (?, ?, ?)")) {
				
				insertBid.setString(1, itemName);
				insertBid.setString(2, bidder);
				insertBid.setDouble(3, price);

				int rowsUpdated = insertBid.executeUpdate();

				if (rowsUpdated > 0 ) {
					return "SUCCESS";
				} else {
					return "ERROR";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERROR";
		}
	}

	public ArrayList<Bids> fetchMyBids(String bidder) throws SQLException {
		ArrayList<Bids> bids = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop?serverTimezone=UTC",
				"username", "password")) {
			try (PreparedStatement myBids = connection.prepareStatement("SELECT * FROM bids WHERE bidder = ?")) {
				myBids.setString(1, bidder);
			
				ResultSet resultSet = myBids.executeQuery();

				while (resultSet.next()) {
					String itemName = resultSet.getString("item_name");
					double price = resultSet.getDouble("price");

					Bids bid = new Bids();
					bid.setItemName(itemName);
					bid.setBidder(bidder); 
					bid.setPrice(price);
					bids.add(bid);
					
				}
			}
		}

		return bids;
	}

	public ArrayList<Bids> fetchAllBids() throws SQLException {
	    ArrayList<Bids> bids = new ArrayList<>();

	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop?serverTimezone=UTC",
	            "username", "password")) {
	        try (PreparedStatement viewBids = connection.prepareStatement("SELECT * FROM bids")) {
	            ResultSet resultSet = viewBids.executeQuery();

	            while (resultSet.next()) {
	                Bids bid = new Bids();
	                bid.setBidder(resultSet.getString("bidder"));
	                bid.setPrice(resultSet.getDouble("price"));
	                bid.setItemName(resultSet.getString("item_name"));

	                bids.add(bid);
	            }
	        }
	    }

	    return bids;
	}


}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionSupport;

public class Items extends ActionSupport {
	private ArrayList<Items> itemList;
	private String seller;
	private String itemName;
	private String description;

	// Returns when add item btn clicked
	public String viewAddItems() {
		return "SUCCESS";
	}

	// Returns the arraylist of items
	public ArrayList<Items> getItemList() {
		return itemList;
	}

	// Getters & Setters
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String viewAllItems() {
		try {

			itemList = fetchItems();

			return "SUCCESS";
		} catch (Exception e) {

			e.printStackTrace();
			return "ERROR";
		}
	}

	public String addItems() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			if (itemName == null || description == null || itemName.trim().isEmpty() || description.trim().isEmpty()) {
				return "ERROR";
			}

			try (Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/eshop?serverTimezone=UTC", "username", "password")) {
				try (PreparedStatement createItem = connection
						.prepareStatement("INSERT INTO items (item_name, seller, description) VALUES (?, ?, ?)")) {
					createItem.setString(1, itemName);
					createItem.setString(2, seller);
					createItem.setString(3, description);
					int rowsUpdated = createItem.executeUpdate();
					return "SUCCESS";
				}
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return "ERROR";
		}
	}

	// Collects all items from database
	public ArrayList<Items> fetchItems() throws SQLException {
		ArrayList<Items> items = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop?serverTimezone=UTC",
				"username", "password")) {
			try (PreparedStatement viewItem = connection.prepareStatement("SELECT * FROM items")) {
				ResultSet resultSet = viewItem.executeQuery();

				while (resultSet.next()) {
					String itemName = resultSet.getString("item_name");
					String seller = resultSet.getString("seller");
					String desc = resultSet.getString("description");

					Items item = new Items();
					item.setItemName(itemName);
					item.setSeller(seller);
					item.setDescription(desc);
					items.add(item);
				}
			}
		}

		return items;
	}

}

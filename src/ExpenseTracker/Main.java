package ExpenseTracker;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JToggleButton;
import javax.swing.JEditorPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import javax.swing.border.CompoundBorder;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldPrice;
	private JTextArea textAreaDescription;
	private JTable table;
	private DefaultTableModel model;
	private String selectedCategory = "None";
	private JLabel lblTotalCategory;
	private JLabel lblTotalExpense;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 953, 663);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(11, 72, 66));
		contentPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(204, 176, 21)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_5.setBackground(new Color(204, 176, 51));
		panel_5.setBounds(351, 344, 240, 32);
		contentPane.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblList = new JLabel("Expense List");
		lblList.setBounds(2, -2, 235, 32);
		panel_5.add(lblList);
		lblList.setForeground(new Color(0, 0, 0));
		lblList.setHorizontalAlignment(SwingConstants.CENTER);
		lblList.setFont(new Font("Tahoma", Font.BOLD, 19));

		JScrollPane scrollPaneTable = new JScrollPane();
		scrollPaneTable.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3));
		scrollPaneTable.getViewport().setBackground(new Color(217, 207, 185));
		scrollPaneTable.setBounds(116, 382, 720, 180);
		contentPane.add(scrollPaneTable);

		String[] columns = { "ID", "Category", "Price", "Description", "Date" };
		model = new DefaultTableModel(columns, 0) { // Program çalışırken değerlerin üstüne tıklanıp değiştirilmemesi
													// için.
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);
		table.setBorder(new LineBorder(new Color(204, 176, 21), 2));
		table.setFont(new Font("Tahoma", Font.BOLD, 10));
		table.setBackground(new Color(227, 225, 210)); // hücre arka planı
		table.setForeground(Color.BLACK); // yazı rengi
		table.setSelectionBackground(new Color(204, 176, 21));
		table.setSelectionForeground(Color.BLACK);
		scrollPaneTable.setViewportView(table);
		table.getTableHeader().setForeground(Color.BLACK);
		table.getTableHeader().setBackground(new Color(217, 207, 185));
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));

		lblTotalCategory = new JLabel("Total in Category (None): 0 TL");
		lblTotalCategory.setForeground(new Color(204, 176, 21));
		lblTotalCategory.setBounds(116, 567, 341, 30);
		lblTotalCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblTotalCategory);

		lblTotalExpense = new JLabel("Total Expense: 0 TL");
		lblTotalExpense.setBounds(680, 567, 216, 30);
		lblTotalExpense.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalExpense.setForeground(new Color(255, 226, 159));
		contentPane.add(lblTotalExpense);

		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel.setBounds(5, 4, 929, 53);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTitle = new JLabel("T-rax: Expense Tracking System");
		lblTitle.setBounds(217, 9, 505, 34);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTitle);
		lblTitle.setBackground(new Color(227, 225, 210));
		lblTitle.setForeground(new Color(204, 176, 21));
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 30));

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBorder(
				new CompoundBorder(new LineBorder(new Color(204, 176, 51), 4), new LineBorder(new Color(0, 0, 0), 5)));
		desktopPane.setBackground(new Color(217, 207, 185));
		desktopPane.setBounds(582, 68, 281, 269);
		contentPane.add(desktopPane);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_3.setBackground(new Color(204, 176, 51));
		panel_3.setBounds(63, 16, 155, 30);
		desktopPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblDetails = new JLabel("Expense Details");
		lblDetails.setBounds(12, -1, 144, 32);
		panel_3.add(lblDetails);
		lblDetails.setForeground(new Color(0, 0, 0));
		lblDetails.setFont(new Font("Tahoma", Font.BOLD, 16));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBackground(new Color(204, 176, 51));
		panel_1.setBounds(23, 59, 78, 25);
		desktopPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(11, 1, 63, 25);
		panel_1.add(lblPrice);
		lblPrice.setBackground(new Color(204, 176, 51));
		lblPrice.setFont(new Font("Sans Serif Collection", Font.BOLD, 19));
		lblPrice.setForeground(new Color(0, 0, 0));

		textFieldPrice = new JTextField();
		textFieldPrice.setBounds(111, 59, 139, 25);
		desktopPane.add(textFieldPrice);
		textFieldPrice.setBackground(new Color(227, 225, 210));
		textFieldPrice.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 2));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBackground(new Color(204, 176, 51));
		panel_2.setBounds(79, 98, 122, 25);
		desktopPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblDesc = new JLabel("Description:");
		lblDesc.setBounds(10, 0, 110, 25);
		panel_2.add(lblDesc);
		lblDesc.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDesc.setForeground(new Color(0, 0, 0));

		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(new Color(255, 255, 255));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdd.setBounds(95, 216, 88, 22);
		desktopPane.add(btnAdd);
		btnAdd.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 1));

		textAreaDescription = new JTextArea();
		textAreaDescription.setBounds(42, 133, 196, 75);
		desktopPane.add(textAreaDescription);
		textAreaDescription.setBackground(new Color(227, 225, 210));
		textAreaDescription.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 2));

		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBorder(
				new CompoundBorder(new LineBorder(new Color(204, 176, 51), 4), new LineBorder(new Color(0, 0, 0), 5)));
		desktopPane_1.setBackground(new Color(217, 207, 185));
		desktopPane_1.setBounds(97, 68, 260, 269);
		contentPane.add(desktopPane_1);
		desktopPane_1.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_4.setBounds(76, 16, 106, 34);
		panel_4.setBackground(new Color(204, 176, 51));
		desktopPane_1.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblCategories = new JLabel("Categories");
		lblCategories.setBounds(9, -4, 93, 42);
		panel_4.add(lblCategories);
		lblCategories.setForeground(new Color(0, 0, 0));
		lblCategories.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton btnNewButton = new JButton("Food");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(74, 66, 108, 26);
		desktopPane_1.add(btnNewButton);
		btnNewButton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 1));
		btnNewButton.addActionListener(e -> {
			selectedCategory = "Food";
			updateTotals();
		});

		JButton btnNewButton_1 = new JButton("Transport");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(74, 101, 108, 26);
		desktopPane_1.add(btnNewButton_1);
		btnNewButton_1.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 1));
		btnNewButton_1.addActionListener(e -> {
			selectedCategory = "Transport";
			updateTotals();
		});

		JButton btnNewButton_2 = new JButton("Entertainment");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(74, 136, 108, 26);
		desktopPane_1.add(btnNewButton_2);
		btnNewButton_2.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 1));
		btnNewButton_2.addActionListener(e -> {
			selectedCategory = "Entertainment";
			updateTotals();
		});

		JButton btnNewButton_3 = new JButton("Education");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.setBounds(74, 172, 108, 26);
		desktopPane_1.add(btnNewButton_3);
		btnNewButton_3.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 1));
		btnNewButton_3.addActionListener(e -> {
			selectedCategory = "Education";
			updateTotals();
		});

		JButton btnNewButton_4 = new JButton("Other");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		btnNewButton_4.setBounds(74, 206, 108, 26);
		desktopPane_1.add(btnNewButton_4);
		btnNewButton_4.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 1));
		btnNewButton_4.addActionListener(e -> {
			selectedCategory = "Other";
			updateTotals();
		});

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Main.class.getResource("/src1/money.png")));
		lblNewLabel.setBounds(400, 218, 123, 108);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Main.class.getResource("/src1/basket.png")));
		lblNewLabel_1.setBounds(400, 73, 132, 135);
		contentPane.add(lblNewLabel_1);

		JButton btnDeleteAll = new JButton("Delete All");
		btnDeleteAll.setForeground(new Color(227, 225, 210));
		btnDeleteAll.setBounds(186, 348, 98, 22);
		contentPane.add(btnDeleteAll);
		btnDeleteAll.setBackground(new Color(111, 45, 47));
		btnDeleteAll.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 1));
		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getRowCount() == 0) {
					JOptionPane.showMessageDialog(Main.this, "The table is already empty.");
					return;
				}

				int answer = JOptionPane.showConfirmDialog(Main.this,
						"WARNING! All your data will be permanently deleted. Are you sure?", "Delete Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {

					SqlConnection conn = new SqlConnection();
					String sql = "TRUNCATE TABLE expenses";
					try (Connection connection = conn.getConnection();
							PreparedStatement pstmt = connection.prepareStatement(sql)) {
						pstmt.executeUpdate();
						JOptionPane.showMessageDialog(Main.this, "All data has been successfully deleted.");
						loadDataFromDatabase();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnDeleteAll.setFont(new Font("Tahoma", Font.BOLD, 12));

		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(111, 45, 47));
		btnDelete.setBounds(678, 348, 88, 22);
		contentPane.add(btnDelete);
		btnDelete.setBackground(new Color(227, 225, 210));
		btnDelete.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 1));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					javax.swing.JOptionPane.showMessageDialog(Main.this,
							"Please select the expense you want to delete from the table.", "Selection not made",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				int answer = JOptionPane.showConfirmDialog(Main.this, "Are you sure you want to delete?",
						"Deletion Confirmation", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					Object id = model.getValueAt(selectedRow, 0);
					int deleteId = (int) id; // Javaya bunun bir int olduğunu kanıtlıyoruz.

					SqlConnection conn = new SqlConnection();
					String sql = "DELETE FROM expenses WHERE id = ?";

					try (Connection connection = conn.getConnection();
							PreparedStatement pstmt = connection.prepareStatement(sql)) {
						pstmt.setInt(1, deleteId);
						pstmt.executeUpdate();
						JOptionPane.showMessageDialog(Main.this, "It has been successfully deleted.");

						loadDataFromDatabase();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if (answer == JOptionPane.NO_OPTION) {
					return;
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));

		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String stringPrice = textFieldPrice.getText(); // Price kısmında girilen değer
				String stringDescription = textAreaDescription.getText(); // Description kısmına girilen değer.

				if (stringPrice.isEmpty()) {
					JOptionPane.showMessageDialog(Main.this, "Please enter price!");
				} else {
					try {
						double Price = Double.parseDouble(stringPrice.replace(",", ".")); // Price'u double'a
																							// dönüştürdük. Ayrıca
																							// kullanıcı virgülle
																							// girerse hata almaması
																							// için nokta olarak
																							// çevirir.

						// Add butonuna basınca SQL bağlanma kısmı
						SqlConnection conn = new SqlConnection();
						String sql = "INSERT INTO expenses (category, Price,description) VALUES (?, ?, ?)";
						try (Connection connection = conn.getConnection();
								PreparedStatement pstmt = connection.prepareStatement(sql)) {
							pstmt.setString(1, selectedCategory);
							pstmt.setDouble(2, Price);
							pstmt.setString(3, stringDescription);

							int result = pstmt.executeUpdate();
							if (result > 0) {
								System.out.println("Succesfully added to database!");
								JOptionPane.showMessageDialog(Main.this, "Expense added successfully!");
								loadDataFromDatabase();
								updateTotals();

								// Add butonuna tıklayınca kutucukların temizlenmesini sağlayan kod.
								textFieldPrice.setText("");
								textAreaDescription.setText("");
							}

						} catch (Exception ex) {
							ex.printStackTrace();
						}

					} catch (NumberFormatException exc) {
						JOptionPane.showMessageDialog(Main.this, "Invalid price format"); // Kullanıcı sayı dışında
																							// başka bir şey girerse.
					}
				}

			}

		});

		loadDataFromDatabase();
	}

	public void updateTotals() {
		double totalExp = 0;
		double catTotal = 0;

		for (int i = 0; i < model.getRowCount(); i++) {
			double val = Double.parseDouble(model.getValueAt(i, 2).toString());
			totalExp += val;

			if (model.getValueAt(i, 1).toString().equals(selectedCategory)) {
				catTotal += val;
			}
		}

		lblTotalExpense.setText("Total Expense: " + totalExp + " TL");
		lblTotalCategory.setText("Total in " + selectedCategory + ": " + catTotal + " TL");
	}

	public void loadDataFromDatabase() { // Eclipse'i açınca JTable'daki verilerin gitmemesi için yazdığımız fonksiyon.
		model.setRowCount(0);
		String sql = "SELECT id, category, price, description, expense_date FROM expenses";
		SqlConnection conn = new SqlConnection();

		try (Connection connection = conn.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);
				java.sql.ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("id");
				String cat = rs.getString("category");
				double amt = rs.getDouble("price");
				String desc = rs.getString("description");
				java.sql.Date date = rs.getDate("expense_date");

				model.addRow(new Object[] { id, cat, amt, desc, date });
			}
			updateTotals();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
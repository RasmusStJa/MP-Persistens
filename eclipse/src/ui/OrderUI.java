package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.CustomerController;
import controller.OrderController;
import controller.ProductController;
import db.CustomerDB;
import db.DataAccessException;
import db.OrderDB;
import db.ProductDB;
import model.Customer;
import model.OrderLineItem;
import model.Product;
import model.SaleOrder;

public class OrderUI extends JFrame {
	private OrderController orderCtrl;
	
	private JTextField txtPhone;
	private JTextField txtProductNo;
	private JTextField txtQuantity;
	private JTable tableOrderLines;
	private DefaultTableModel tableModel;
	private JLabel lblCustomerName;
	private JLabel lblTotalValue;
	private JButton btnConfirmOrder;
	
	public static void main(String[] args) {
		try {
			CustomerDB customerDB = new CustomerDB();
			ProductDB productDB = new ProductDB();
			OrderDB orderDB = new OrderDB();
			CustomerController customerCtrl = new CustomerController(customerDB);
			ProductController productCtrl = new ProductController(productDB);
			OrderController orderCtrl = new OrderController(customerCtrl, productCtrl, orderDB);
			OrderUI frame = new OrderUI(orderCtrl);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public OrderUI(OrderController orderCtrl) {
		this.orderCtrl = orderCtrl;
		this.orderCtrl.createOrder();
		
		setTitle("Western Style Ltd. - Opret Salgsordre");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 100));
		contentPane.setLayout(new BorderLayout(0, 10));
		setContentPane(contentPane);
		
//		---------- TOP ----------
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel lblTitle = new JLabel("Opret ny salgsordre");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		topPanel.add(lblTitle, gbc);
		
		JLabel lblPhone = new JLabel("Kunde Tlf.nr:");
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    topPanel.add(lblPhone, gbc);
	    txtPhone = new JTextField();
	    txtPhone.setColumns(12);
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    topPanel.add(txtPhone, gbc);
		
		JButton btnFindCustomer = new JButton("Søg kunde");
		gbc.gridx = 2;
		gbc.gridy = 1;
		topPanel.add(btnFindCustomer, gbc);
		
		lblCustomerName = new JLabel("Kunde ikke angivet");
		lblCustomerName.setFont(new Font("Tahoma", Font.ITALIC, 12));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		topPanel.add(lblCustomerName, gbc);
		
//		---------- CENTER ----------
		
		JPanel centerPanel = new JPanel(new BorderLayout(0, 5));
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		JPanel inputProductPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		inputProductPanel.add(new JLabel("Vare nr: "));
		txtProductNo = new JTextField(8);
		inputProductPanel.add(txtProductNo);
		inputProductPanel.add(new JLabel("Antal: "));
		txtQuantity = new JTextField(4);
		inputProductPanel.add(txtQuantity);
		
		JButton btnAddLine = new JButton("Tilføj vare");
		inputProductPanel.add(btnAddLine);
		centerPanel.add(inputProductPanel, BorderLayout.NORTH);
		
		String[] collumnNames = {
				"Vare nr",
				"Produkt navn",
				"Antal",
				"Enheds pris",
				"Subtotal"
		};
		
		tableModel = new DefaultTableModel(collumnNames, 0);
		tableOrderLines = new JTable(tableModel);
		
		JScrollPane scrollPane = new JScrollPane(tableOrderLines);
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		
//		---------- BOTTOM ----------
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		
		lblTotalValue = new JLabel("Total: 0,00 DKK");
		lblTotalValue.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalValue.setHorizontalAlignment(SwingConstants.LEFT);
		bottomPanel.add(lblTotalValue, BorderLayout.WEST);
		
		btnConfirmOrder = new JButton("Bekræft ordre");
		btnConfirmOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		bottomPanel.add(btnConfirmOrder, BorderLayout.EAST);
		
//		---------- HANDLERS ----------

		btnFindCustomer.addActionListener(e -> {
			String phone = txtPhone.getText().trim();
			if(!phone.isEmpty()) {
				try {
					Customer c = orderCtrl.enterCustomerInfo(phone);
					if (c != null) {
						lblCustomerName.setText("Kunde: " + c.getName() + " (" + c.getType() + ")");
			            updateTotalDisplay();
					} else {
						JOptionPane.showMessageDialog(this, "Kunde med tlf. nr. " + phone + " blev ikke fundet.", "Kunde ikke fundet", JOptionPane.WARNING_MESSAGE);
			            lblCustomerName.setText("Kunde: Ikke angivet (Anonym)");
					}
				} catch (DataAccessException ex) {
					JOptionPane.showMessageDialog(this, "Fejl ved opslag i databasen: " + ex.getMessage(), "Databasefejl", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnAddLine.addActionListener(e -> {
			try {
				int prodNo = Integer.parseInt(txtProductNo.getText().trim());
				int qty = Integer.parseInt(txtQuantity.getText().trim());
		        Product p = orderCtrl.enterProductNumber(prodNo);
		        if (p != null) {
		        	OrderLineItem item = orderCtrl.enterQuantity(qty);
		        	if (item != null) {
		        		tableModel.addRow(new Object[] {
		        				p.getProductNumber(), p.getName(), item.getQuantity(), item.getUnitPrice(), item.getSubtotal()
		        		});
		        		txtProductNo.setText("");
		                txtQuantity.setText("");
		                txtProductNo.requestFocus();
		                updateTotalDisplay();
		        	}
		        } else {
		        	JOptionPane.showMessageDialog(this, "Produkt #" + prodNo + " blev ikke fundet i databasen.", "Produkt ikke fundet", JOptionPane.WARNING_MESSAGE);
		        }
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Indtast venligst gyldige tal i Varenr og Antal.", "Ugyldigt input", JOptionPane.ERROR_MESSAGE);
		      } catch (DataAccessException ex) {
		        JOptionPane.showMessageDialog(this, "Fejl ved tilføjelse af vare: " + ex.getMessage(), "Databasefejl", JOptionPane.ERROR_MESSAGE);
		      }
		});
		
		btnConfirmOrder.addActionListener(e -> {
			try {
				SaleOrder confirmedOrder = orderCtrl.confirmOrder();
				if (confirmedOrder != null) {
					JOptionPane.showMessageDialog(this, "Ordren er oprettet og gemt i databasen!\n\n" + 
					"Tildelt Ordrenummer: " + confirmedOrder.getOrderNo() + "\n" + 
					"Totalbeløb: " + confirmedOrder.getTotal() + " DKK", "Ordre Bekræftet", JOptionPane.INFORMATION_MESSAGE);
					tableModel.setRowCount(0);
					txtPhone.setText("");
					lblCustomerName.setText("Kunde: Ikke angivet");
			        orderCtrl.createOrder();
			        updateTotalDisplay();

				}
			} catch (DataAccessException ex) {
				JOptionPane.showMessageDialog(this, "Kunne ikke gemme ordren i databasen: " + ex.getMessage(), "Databasefejl", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	
	private void updateTotalDisplay() {
		if (orderCtrl.getCurrentOrder() != null) {
			double total = orderCtrl.getCurrentOrder().getTotal();
			lblTotalValue.setText(String.format("Total: " + total + "DKK"));
		}
	}
}








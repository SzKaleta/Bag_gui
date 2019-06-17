package Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import Bag_application.*;



public class ProductsWindow extends JFrame{

	private ProductsRead productsSolver = new ProductsRead();
	private JPanel contentPane;
	private int numberOfBags = 0;
	 
	ProductsWindow() throws IOException {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds((screen.width-622)/2, (screen.height-820)/2, 622, 820);
				setResizable(false);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(0, 0, 622, 520));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				setTitle("Bags calculation");
					JLabel lblProducts = new JLabel("Products: ");
					JComboBox<String> prodList = new JComboBox();
					productsSolver.readProducts();
						for (Product p : productsSolver.getProductsList())
						{
							prodList.addItem(new String(p.toString()));
						}
				
					JComboBox<String> cartList = new JComboBox();	
					JTextField weight= new JTextField();
					weight.setEditable(true);
					JButton btnProd = new JButton("Add product to the cart");
						btnProd.addActionListener(new ActionListener()
						{
							@Override
							public void actionPerformed(ActionEvent e) 
							{
								if(weight.getText().equals(""))
								{
									JOptionPane.showMessageDialog(null, "You need to choose maximum weight of one bag!");
								}
								else 
								{					
									if(Double.valueOf(weight.getText())>=productsSolver.getProductsList().get(prodList.getSelectedIndex()).getWeight())
									{
										productsSolver.getItemsList().add(productsSolver.getProductsList().get(prodList.getSelectedIndex()));
										cartList.addItem(prodList.getSelectedItem().toString());
									}
									else
									{
										JOptionPane.showMessageDialog(null, "This item is too heavy for your bag!");
									}
								}
								
							}
						});
					JLabel lblCart = new JLabel("Products in your cart: ");
					JLabel lblNumber = new JLabel("Number of bags: " + numberOfBags);	
					JLabel lblWeight = new JLabel("Maximum weight of one bag (gr): ");
					JLabel lblContent = new JLabel("Content of the bags: ");
					JTextArea bagsItems = new JTextArea();
					JScrollPane scroll = new JScrollPane(bagsItems);
					JButton btnWeight = new JButton("Calculate number of bags");
						btnWeight.addActionListener(new ActionListener()
						{
							@Override
							public void actionPerformed(ActionEvent e) 
							{
								if(productsSolver.getItemsList().isEmpty())
								{
									JOptionPane.showMessageDialog(null, "No products in your cart!");
		
								}
								else 
								{					
									productsSolver.bagSolver.solve(productsSolver.getItemsList(), Double.valueOf(weight.getText()));
									numberOfBags=productsSolver.bagSolver.getBagsList().size();
									lblNumber.setText("Number of bags: " + numberOfBags);
									for(int i=0; i<productsSolver.bagSolver.getBagsList().size() ; i++)
									{
										bagsItems.setText(bagsItems.getText()+productsSolver.bagSolver.toString(i));
									}
								}
								
							}
						});
					
					JButton btnRefresh = new JButton("Refresh");
					btnRefresh.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e) 
						{													
							productsSolver.setItemsList(new ArrayList<Product>());
							productsSolver.bagSolver= new BagAlgorithm();
							
							numberOfBags=0;
							lblNumber.setText("Number of bags: " + numberOfBags);
							weight.setText("");
							bagsItems.setText("");
							cartList.removeAllItems();							
						}
					});
						

				
					lblProducts.setBounds(280, 14, 70, 30);				
					prodList.setBounds(85, 44, 450, 30);
					btnProd.setBounds(160, 100, 300, 30);
					lblWeight.setBounds(220, 150, 450, 30);
					weight.setBounds(260, 190, 100, 30);
					lblCart.setBounds(250, 230, 200, 20);
					cartList.setBounds(85, 250, 450, 30);
					btnWeight.setBounds(210, 290, 200, 30);
					lblNumber.setBounds(260, 330, 200, 30);
					lblContent.setBounds(260, 380, 200, 20);
					bagsItems.setBounds(85, 400, 455, 300);
					scroll.setBounds(85, 400, 455, 300);
					btnRefresh.setBounds(260, 720, 100, 20);
					
					
					contentPane.add(btnRefresh);
					contentPane.add(lblContent);
					contentPane.add(scroll);
					contentPane.add(lblNumber);
					contentPane.add(lblCart);
					contentPane.add(btnWeight);		
					contentPane.add(weight);
					contentPane.add(lblWeight);	
					contentPane.add(prodList);
					contentPane.add(lblProducts);
					contentPane.add(cartList);		
					contentPane.add(btnProd);	
		}
	
}

package Frame;

import java.awt.EventQueue;
import java.io.IOException;



public class RunFrame {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		@Override
			public void run() {
			ProductsWindow frame;
			try {
				frame = new ProductsWindow();
				frame.setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			}
		});
	}

}

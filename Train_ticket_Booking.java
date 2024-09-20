package train_ticket_bookig;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.JSeparator;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;

public class Train_ticket_Booking {

	private JFrame frmTrainTicketBooking;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Train_ticket_Booking window = new Train_ticket_Booking();
					window.frmTrainTicketBooking.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Train_ticket_Booking() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTrainTicketBooking = new JFrame();
		frmTrainTicketBooking.setEnabled(false);
		frmTrainTicketBooking.setTitle("Train Ticket Booking");
		frmTrainTicketBooking.setBounds(100, 100, 731, 413);
		frmTrainTicketBooking.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTrainTicketBooking.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ticket Booking");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(37, 42, 134, 44);
		frmTrainTicketBooking.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(37, 98, 60, 17);
		frmTrainTicketBooking.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("New label");
		lblNewLabel_1_1.setBounds(37, 127, 60, 17);
		frmTrainTicketBooking.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("New label");
		lblNewLabel_1_2.setBounds(37, 156, 60, 17);
		frmTrainTicketBooking.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("New label");
		lblNewLabel_1_3.setBounds(37, 184, 60, 17);
		frmTrainTicketBooking.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("New label");
		lblNewLabel_1_4.setBounds(37, 213, 60, 17);
		frmTrainTicketBooking.getContentPane().add(lblNewLabel_1_4);
	}
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Caculator {
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Caculator");

		//  pane's layout
		Container cp = frame.getContentPane();
		cp.setLayout(new GridLayout(2, 5));

		// create button

		final JTextField num1 = new JTextField("    12");
		final JButton btext1 	= new JButton("");
		final JTextField num2 = new JTextField("    2");
		final JButton btext2 	= new JButton(" = ");
		final JButton bout 	= new JButton("");
		
		JButton badd 	= new JButton(" + ");
		JButton bmin 	= new JButton(" - ");
		JButton bmul 	= new JButton(" * ");
		JButton bdiv 	= new JButton(" / ");
		JButton bequ 	= new JButton(" OK ");

		// add buttons
		cp.add(num1);
		cp.add(btext1);
		cp.add(num2);
		cp.add(btext2);
		cp.add(bout);

		cp.add(badd);
		cp.add(bmin);
		cp.add(bmul);
		cp.add(bdiv);
		cp.add(bequ);
		
		// show the window
		frame.pack();
		frame.setVisible(true);

		// set the basic info of the frame	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(500, 250);
/*
		// event listening
		ActionListener command = new CommandAction(); // command from the user about + - * /
		badd.addActionListener(command);
		bsub.addActionListener(command);
		bmul.addActionListener(command);
		bdiv.addActionListener(command);

		ActionListener calculate = new CalculateAction(); // command from the user about Output the caculating result 
		bequ.addActionListener(calculate);
*/
		badd.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
			  		String commandRev = e.getActionCommand();
					btext1.setText(commandRev);
			  }
		});
		
		bmin.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
			  		String commandrev = e.getActionCommand();
					btext1.setText(commandrev);
			  }
		});

		bmul.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
			  		String commandrev = e.getActionCommand();
					btext1.setText(commandrev);
			  }
		});

		bdiv.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
			  		String commandrec = e.getActionCommand();
					btext1.setText(commandrec);
			  }
		});

		// deal with the caculate command
		bequ.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
				  String commandRecEqu = btext1.getActionCommand();
				  String numstrl = num1.getText().trim();
				  String numstrr = num2.getText().trim();
				  double numl = Double.parseDouble(numstrl);
				  double numr = Double.parseDouble(numstrr);
				  double output = 0;

				  // caculate the result of the command
				  if(commandRecEqu.equals(" + ")) {
					 output = numl + numr;
				  } else if (commandRecEqu.equals(" - ")) {
					 output = numl - numr;
				  } else if (commandRecEqu.equals(" * ") ) {
					 output = numl * numr;
				  } else if (commandRecEqu.equals(" / ")) {
					 output = numl / numr;
				  }
				  bout.setText("" + output);
			  }
			
		});
		
	}

	// main function
	public static void main(String[] args) {
		Runnable tr = new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		};
		javax.swing.SwingUtilities.invokeLater(tr);
	}
}

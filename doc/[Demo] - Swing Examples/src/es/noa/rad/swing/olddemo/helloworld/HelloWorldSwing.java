package es.noa.rad.swing.olddemo.helloworld;

 import
  java.awt.Dimension;
 import
  javax.swing.JFrame;
 import
  javax.swing.JLabel;    

 public class HelloWorldSwing {
  /**
    * Create the GUI and show it.  For thread safety,
    * this method should be invoked from the
    * event-dispatching thread.
    */
  private static void createAndShowGUI(
          final String nameParam) {
   //Make sure we have nice window decorations.
   JFrame.setDefaultLookAndFeelDecorated(true);

   //Create and set up the window.
   JFrame frame = new JFrame("HelloWorldSwing");
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   //Add the ubiquitous "Hello World" label.
   JLabel label = new JLabel("Hello World: " + nameParam);
   frame.getContentPane().add(label);
   frame.setMinimumSize(new Dimension(800, 600));
   //Display the window.
   frame.pack();
   frame.setVisible(true);
  }

  public static void main(String[] args) {
   //Schedule a job for the event-dispatching thread:
   //creating and showing this application's GUI.
   javax.swing.SwingUtilities.invokeLater(
    new Runnable() {
     public void run() {
      if(args.length > 0) {    	 
       createAndShowGUI(args[0]);
      } else {
       createAndShowGUI("");    	  
      }
     }
    }
   );
  }
 }

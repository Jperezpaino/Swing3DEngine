package es.noa.rad.swing;

import java.awt.EventQueue;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import es.noa.rad.data.dto.PersonDTO;
import java.awt.BorderLayout;

public class WindowsLaught {

 private JFrame frame;

 /**
  * Launch the application.
  */
 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     WindowsLaught window = new WindowsLaught();
     window.frame.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }

 /**
  * Create the application.
  */
 public WindowsLaught() {
  initialize();
 }

 /**
  * Initialize the contents of the frame.
  */
 private void initialize() {
  frame = new JFrame();
  frame.setBounds(100, 100, 450, 300);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  PersonDTO newPersonDTO = new PersonDTO("Juan", "Gonzalez", "sanchez", new Date(), new Date());
  Person panel = new Person(newPersonDTO);
  frame.getContentPane().add(panel, BorderLayout.CENTER);
 }

}

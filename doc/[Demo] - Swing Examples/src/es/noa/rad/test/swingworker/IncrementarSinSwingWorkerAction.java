package es.noa.rad.test.swingworker;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
 
@SuppressWarnings("serial")
public class IncrementarSinSwingWorkerAction extends AbstractAction{
 
 private EjemploFrame ejemploFrame;
 
 public IncrementarSinSwingWorkerAction(EjemploFrame ejemploSinSwingWorkerFrame){
  this.ejemploFrame = ejemploSinSwingWorkerFrame;
 }
 
 public void actionPerformed(ActionEvent arg0) {
  int ite = 0;
  while (ite < EjemploFrame.MAX_ITE){
   ite = ite + 1;
   this.ejemploFrame.getTextField().setText("" + ite);
  }
  
 } 
}
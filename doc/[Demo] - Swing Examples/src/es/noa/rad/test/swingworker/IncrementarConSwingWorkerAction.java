package es.noa.rad.test.swingworker;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.SwingWorker;
 
@SuppressWarnings("serial")
public class IncrementarConSwingWorkerAction extends AbstractAction{
 
 private EjemploFrame ejemploFrame;
 
 public IncrementarConSwingWorkerAction(EjemploFrame ejemploSinSwingWorkerFrame){
  this.ejemploFrame = ejemploSinSwingWorkerFrame;
 }
 
 public void actionPerformed(ActionEvent arg0) {
  final SwingWorker worker = new SwingWorker(){
 
   @Override
   protected Object doInBackground() throws Exception {
    int ite = 0;
    while (ite < EjemploFrame.MAX_ITE){
     ite = ite + 1;
     ejemploFrame.getTextField().setText("" + ite);
    } 
    return null;
   } 
  };
  worker.execute();
 }
}
package es.noa.rad.test.aplication;

 import
  java.awt.GridBagConstraints;
import
  java.awt.GridBagLayout;
import
  java.awt.LayoutManager;

import
  javax.swing.JButton;
import javax.swing.JLabel;
import
  javax.swing.JPanel;
import
  javax.swing.border.Border;
import
  javax.swing.border.EmptyBorder;

 public class SimplePanel extends JPanel {
  private static final long serialVersionUID = 1L;
  private Border componentMargin;  
  private LayoutManager componentLayout;   
  // Definimos los componentes.
  private JLabel searchTitleLabel;

  private JLabel searchInfoLabel;

  private JLabel patterTitleLabel;

  private JLabel patterInfoLabel;
  
  private JButton patterChooseButton;
  private JButton scopeChooseButton;
  
  public SimplePanel() {
   super();

   // Definimos el margen exterior del panel.
   this.componentMargin = new EmptyBorder(6, 6, 6, 6);
   this.setBorder(this.componentMargin);
   
   // Definimos el layout que utilizara el panel (GridBagLayout).
   this.componentLayout = new GridBagLayout();
   this.setLayout(this.componentLayout);
   
   // Utilizamso el m�todo de inicializaci�n.
   this.startPanel();
  }

  private void startPanel() {
   GridBagConstraints layoutConstraints = new GridBagConstraints();
   /*
    * Comenzamos a colocar elemento a elemento de izquierda a derecha y de
    * arriba a abajo.
    */
   
   
   
   
   /*
    * Definimos es componente.
    */
   this.searchTitleLabel = new JLabel("Containing text:");
   //lbText.setMinimumSize(new Dimension(lbText.getFontMetrics(lbText.getFont()).stringWidth(this.message), lbText.getSize().height));
   //lbText.setLabelFor(pwd); 

   /*
    * Colocamos la celda (0, 0) con un 'span' horizontal de 2 celdas, y que al
    * redimensionar crezca de manera horizontal.
    */
   layoutConstraints.gridx = 0;
   layoutConstraints.gridy = 0;
   layoutConstraints.gridwidth = 2;  
   layoutConstraints.fill = GridBagConstraints.HORIZONTAL;   
   layoutConstraints.weightx = 1.0; // Duda
   
   /*
    * A�adimos el componente al 'Layout', con las propiedades definidas.
    */
   this.add(this.searchTitleLabel, layoutConstraints);
   
   
   
   
   
   
   
   
   
   
   /*
    * Definimos es componente.
    */
   this.searchInfoLabel = new JLabel("(* = any String, ? = any Character, \\ = scape for literals: * \\ ?)");
   this.patterTitleLabel = new JLabel("File name patterns (separated by comma):");
   this.patterInfoLabel = new JLabel("(* = any String, ? = any Character, !x = excluding x)");
   
   
   
   /*
    * A�adimos el componente al 'Layout', con las propiedades definidas.
    */
   this.add(this.searchInfoLabel, layoutConstraints);
   
   
   
   this.patterChooseButton = new JButton("Choose...");   
   this.scopeChooseButton = new JButton("Choose...");
   //this.restoreButton = new JButton(imageIconRestore);
   //this.restoreButton.setMnemonic(KeyEvent.VK_R);
   //this.restoreButton.setToolTipText(Messages.getString("Wizard.restaurar.description")); //$NON-NLS-1$
   //this.restoreButton.getAccessibleContext().setAccessibleName(this.restoreButton.getToolTipText());   
  }
  
 }

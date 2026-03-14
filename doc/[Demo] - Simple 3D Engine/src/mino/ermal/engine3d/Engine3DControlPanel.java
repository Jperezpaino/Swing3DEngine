package mino.ermal.engine3d;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mino.ermal.engine3d.matrix.RotationMatrix3D;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: Jun 15, 2008
 * Time: 2:15:11 PM
 *
 * This is the user interface panel where controls are put to interact with the user
 * and with the Engine3DRenderPanel
 *
 */
public class Engine3DControlPanel extends JSplitPane
  implements ItemListener, MouseMotionListener, MouseListener {
    private Engine3DRenderPanel engine3dRenderPanel;
    private JPanel controlPanel;

    //renderer options panel
    private JPanel rendererOptionPanel;
    private JPanel panelCruzeta;
    private JCheckBox enableFilled;
    private JCheckBox enableWireframed;
    private JCheckBox enableCoords;

    //object selection panel
    private JComboBox selection;

    private Point startPoint; //used to save the previous point when the mouse is draged
    private Point endPoint; //used to save the last point when the mouse is draged

    /**
     * Class constructor, initializes the user interface components
     * and adds the main content pane to itself
     */
    public Engine3DControlPanel() {
        initUserInterface();
    }

    /**
     * All user interface component initialization and configuration
     * is done here.
     */
    private void initUserInterface(){
        engine3dRenderPanel = new Engine3DRenderPanel();
        engine3dRenderPanel.addMouseListener(this);
        engine3dRenderPanel.addMouseMotionListener(this);

        // renderer options panel
        enableFilled = new JCheckBox("Filled", engine3dRenderPanel.getFilled());
        enableFilled.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                engine3dRenderPanel.setFilled(enableFilled.isSelected());
                engine3dRenderPanel.render();
            }
        });
        enableWireframed = new JCheckBox("Wireframed", engine3dRenderPanel.getWireframed());
        enableWireframed.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                engine3dRenderPanel.setWireframed(enableWireframed.isSelected());
                engine3dRenderPanel.render();
            }
        });
        enableCoords = new JCheckBox("Show Coordinate Axes", engine3dRenderPanel.getRenderAxis());
        enableCoords.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                engine3dRenderPanel.setRenderAxis(enableCoords.isSelected());
                engine3dRenderPanel.render();
            }
        });
        rendererOptionPanel = new JPanel(new GridLayout(3, 1));
        rendererOptionPanel.add(enableFilled);
        rendererOptionPanel.add(enableWireframed);
        rendererOptionPanel.add(enableCoords);

        panelCruzeta = new JPanel(new GridLayout(3, 3, 5, 5));
        // Crear botones
        JButton btnArriba = new JButton("↑");
        JButton btnIzquierda = new JButton("←");
        JButton btnCentro = new JButton(""); // Espacio vacío en el centro
        JButton btnDerecha = new JButton("→");
        JButton btnAbajo = new JButton("↓");
        
        // Añadir botones al panel en forma de cruz
        panelCruzeta.add(new JLabel());   // Espacio vacío arriba izquierda
        panelCruzeta.add(btnArriba);      // Arriba
        panelCruzeta.add(new JLabel());   // Espacio vacío arriba derecha
        panelCruzeta.add(btnIzquierda);   // Izquierda
        panelCruzeta.add(btnCentro);      // Centro (vacío)
        panelCruzeta.add(btnDerecha);     // Derecha
        panelCruzeta.add(new JLabel());   // Espacio vacío abajo izquierda
        panelCruzeta.add(btnAbajo);       // Abajo
        panelCruzeta.add(new JLabel());   // Espacio vacío abajo derecha
        
        // Agregar acciones a los botones
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                double angleMultiplyFactor=0.05;
                
                if (source.getText().equals("↑")) {
                  double angleX = angleMultiplyFactor * -1.0;
                  engine3dRenderPanel.rotate(new RotationMatrix3D(-angleX, 0, 0));
                } else if (source.getText().equals("↓")) {
                  double angleX = angleMultiplyFactor * 1.0;
                  engine3dRenderPanel.rotate(new RotationMatrix3D(-angleX, 0, 0));
                } else if (source.getText().equals("←")) {
                  double angleY = angleMultiplyFactor * -1.0;
                  engine3dRenderPanel.rotate(new RotationMatrix3D(0, angleY, 0));
                } else if (source.getText().equals("→")) {
                  double angleY = angleMultiplyFactor * 1.0;
                  engine3dRenderPanel.rotate(new RotationMatrix3D(0, angleY, 0));
                }
            }
        };

        btnArriba.addActionListener(listener);
        btnIzquierda.addActionListener(listener);
        btnDerecha.addActionListener(listener);
        btnAbajo.addActionListener(listener);
        
        //object selection panel
        selection = new JComboBox();
        selection.setSize(100, 50);
        Iterator iterator = engine3dRenderPanel.getShapeList();
        while (iterator.hasNext()) {
            selection.addItem(iterator.next());
        }
        selection.addItemListener(this);

        //control panel
        controlPanel = new JPanel();
        controlPanel.setBackground(Color.lightGray);
        controlPanel.add(selection);
        controlPanel.add(rendererOptionPanel);
        controlPanel.add(panelCruzeta);
        
        //main content split pane
        setDividerSize(3);
        setResizeWeight(1.0);
        setAutoscrolls(false);
        setContinuousLayout(true);
        setLastDividerLocation(-1);
        setRequestFocusEnabled(true);
        //add renderer panel on the left
        setLeftComponent(engine3dRenderPanel);
        //add the control panel on the right
        setRightComponent(controlPanel);

    }



    public void itemStateChanged(ItemEvent itemEvent) {
        Object o = itemEvent.getItem();
        engine3dRenderPanel.select((String) o);
    }

    private void rotateFromDrag(){
        double angleMultiplyFactor=0.02;
        double angleY=angleMultiplyFactor*(endPoint.getX()-startPoint.getX());
        double angleX=angleMultiplyFactor*(endPoint.getY()-startPoint.getY());
        engine3dRenderPanel.rotate(new RotationMatrix3D(-angleX, angleY, 0));
    }
    private void scaleFromDrag(){
        double scaleMultiplyFactor=0.01;
        double scaleFactor=scaleMultiplyFactor*(endPoint.getY()-startPoint.getY());
        engine3dRenderPanel.scale(1+scaleFactor);
    }

    /**
     * Invoked when the mouse button is pressed
     * @param event mouse button pressed event
     */
    public void mousePressed(MouseEvent event) {
        startPoint=event.getPoint();
    }

    /**
     * Invoked when the mouse is draged
     * @param event mouse draged event
     */
    public void mouseDragged(MouseEvent event) {
        endPoint=event.getPoint();
        
        if((event.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK){
            rotateFromDrag();
        }else if((event.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK){
            scaleFromDrag();
        }

        startPoint=endPoint;
    }

    /*
    The following methods are implemented from the MouseMotionListener and
    MouseListener interfaces.
    All of them are empty because we ignore other mouse events except for the two
    methods above
     */


    public void mouseMoved(MouseEvent event) {
        //not needed
    }

    public void mouseClicked(MouseEvent event) {
        //not needed
    }

    public void mouseEntered(MouseEvent event) {
        //not needed
    }

    public void mouseExited(MouseEvent event) {
        //not needed
    }

    public void mouseReleased(MouseEvent event) {
        //not needed
    }

}

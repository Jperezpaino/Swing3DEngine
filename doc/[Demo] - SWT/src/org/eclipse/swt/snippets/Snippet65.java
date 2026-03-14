package org.eclipse.swt.snippets;

 import org.eclipse.swt.*;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.*;
 import org.eclipse.swt.layout.*;
 import org.eclipse.swt.widgets.*;
 import org.eclipse.nebula.widgets.datechooser.*;
 import org.eclipse.jface.viewers.CheckStateChangedEvent;
 import org.eclipse.jface.viewers.CheckboxTableViewer;
 import org.eclipse.jface.viewers.ICheckStateListener;
 
 public class Snippet65 {
  private DateChooserCombo dateChooserFechaDeFirma;
  private DateChooserCombo dateChooserFechaDeEntradaEnVigor;
  private DateChooserCombo dateChooserFechaVencimiento;
  private DateChooserCombo dateChooserFechaPreavisoResolver;
  private DateChooserCombo dateChooserFechaDerechoProrroga;  
  private Button checkSinFechaDeFirma;
  private Button checkProrrogar;
  private Button checkDesistimientoUnilateral;
  private Text textDuracionInicialAnos;
  private Text textDuracionInicialMeses;
  private Text textDuracionInicialDias;
  private Text textPlazoPreavisoAnos;
  private Text textPlazoPreavisoMeses;
  private Text textPlazoPreavisoDias;
  private Text textPlazoDerechoProrrogaAnos;
  private Text textPlazoDerechoProrrogaMeses;
  private Text textPlazoDerechoProrrogaDias;
  private Text textDesistimientoUnilateralAnos;
  private Text textDesistimientoUnilateralMeses;
  private Text textDesistimientoUnilateralDias;   
  private Text textObservaciones;
  private Button ButtonCrear;
  private Button ButtonModificar;
  private Button ButtonEliminar;  
  
  final VerifyBigDecimalListener verifyBigDecimalListener = new VerifyBigDecimalListener(9, 2);
  
  protected void createContents() {
   Display display = new Display ();
   final Shell shell = new Shell (display);
   

   
  //Label label = new Label (shell, SWT.WRAP);
//  label.setText ("This is a long text string that will wrap when the dialog is resized.");
//  
//  List list = new List (shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
//  list.add("Item 1");
//  list.add("Item 2");
//  
//  Button button1 = new Button (shell, SWT.PUSH);
//  button1.setText ("OK");
//  
//  Button button2 = new Button (shell, SWT.PUSH);
//  button2.setText ("Cancel");

  // Establecemos el layout y los margenes de la ventana
  final int insetX = 10;
  final int insetY = 10;
  FormLayout formLayout = new FormLayout();
  formLayout.marginWidth = insetX;
  formLayout.marginHeight = insetY;
  shell.setLayout (formLayout);

  // Definimos el grupo de componentes correspondientes a 'Firma'.
  final Group groupFirmaContrato
   = ComponentsFactory.newGroup(shell, Messages.ContratosAltaPestanaFechasAlertaFirma);
  groupFirmaContrato.setLayout(new FormLayout());
  final FormData formDataGroupFirmaContrato = new FormData();
  formDataGroupFirmaContrato.left = new FormAttachment(0, 0);
  formDataGroupFirmaContrato.right = new FormAttachment(100, 0);
  formDataGroupFirmaContrato.top = new FormAttachment(0, 0);
  groupFirmaContrato.setLayoutData(formDataGroupFirmaContrato);
  
  // Primera Fila del Grupo de componentes correspondientes a 'Firma'.
  final Composite compositeRowOneGroupFirmaContrato
   = ComponentsFactory.newComposite(groupFirmaContrato);
  compositeRowOneGroupFirmaContrato.setLayout(new FormLayout());
  //compositeRowOneGroupFirmaContrato.setBackground(display.getSystemColor(SWT.COLOR_RED));
  final FormData formDataCompositeRowOneGroupFirmaContrato = new FormData();
  formDataCompositeRowOneGroupFirmaContrato.top = new FormAttachment(0, 5);
  formDataCompositeRowOneGroupFirmaContrato.left = new FormAttachment(0, 0);
  formDataCompositeRowOneGroupFirmaContrato.right = new FormAttachment(100, 0);
  formDataCompositeRowOneGroupFirmaContrato.bottom = new FormAttachment(100, -5);
  compositeRowOneGroupFirmaContrato.setLayoutData(formDataCompositeRowOneGroupFirmaContrato);
  
  // Etiqueta "Fecha de firma"
  final Label labelFechaDeFirma = ComponentsFactory.newLabel(
   compositeRowOneGroupFirmaContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaFirmaFechaFirma
    + Messages.Marca_Obligatorio + Messages.Marca_FinCampo);
  final FormData formDataLabelFechaDeFirma = new FormData();
  formDataLabelFechaDeFirma.top
   = new FormAttachment(50, -labelFechaDeFirma.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelFechaDeFirma.left = new FormAttachment(0, 10);
  formDataLabelFechaDeFirma.width
   = labelFechaDeFirma.computeSize(SWT.DEFAULT, SWT.DEFAULT).x; 
  labelFechaDeFirma.setLayoutData(formDataLabelFechaDeFirma);
  
  // Campo "Fecha de firma"
  this.dateChooserFechaDeFirma
   = ComponentsFactory.newDateChooserCombo(compositeRowOneGroupFirmaContrato);
  final FormData formDataDateChooserFechaDeFirma = new FormData();
  formDataDateChooserFechaDeFirma.top
   = new FormAttachment(50, -this.dateChooserFechaDeFirma.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);   
  formDataDateChooserFechaDeFirma.left = new FormAttachment(labelFechaDeFirma, 10);
  formDataDateChooserFechaDeFirma.width = 100;
  this.dateChooserFechaDeFirma.setLayoutData(formDataDateChooserFechaDeFirma);

  // Check "Sin fecha de firma"
  this.checkSinFechaDeFirma = ComponentsFactory.newCheckButton(
   compositeRowOneGroupFirmaContrato, "");   
  final FormData formDataCheckSinFechaDeFirma = new FormData();
  formDataCheckSinFechaDeFirma.top
   = new FormAttachment(50, -this.checkSinFechaDeFirma.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataCheckSinFechaDeFirma.left = new FormAttachment(this.dateChooserFechaDeFirma, 10);
  this.checkSinFechaDeFirma.setLayoutData(formDataCheckSinFechaDeFirma);
  
  // Etiqueta "Sin fecha de firma"
  final Label labelSinFechaDeFirma
   = ComponentsFactory.newLabel(compositeRowOneGroupFirmaContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaFirmaFechaFirmaCheck);
  final FormData formDataLabelSinFechaDeFirma = new FormData();
  formDataLabelSinFechaDeFirma.top
   = new FormAttachment(50, -labelSinFechaDeFirma.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelSinFechaDeFirma.left = new FormAttachment(this.checkSinFechaDeFirma, 5);
  formDataLabelSinFechaDeFirma.width
   = labelSinFechaDeFirma.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;  
  labelSinFechaDeFirma.setLayoutData(formDataLabelSinFechaDeFirma);
  
  
  
  // Espaciador
  final Label labelFechaDeEntradaEnVigorEspaciador = ComponentsFactory.newLabel(
    compositeRowOneGroupFirmaContrato, SWT.LEFT, "");
  final FormData formDataLabelFechaDeEntradaEnVigorEspaciador = new FormData();
  formDataLabelFechaDeEntradaEnVigorEspaciador.top
   = new FormAttachment(50, -labelFechaDeEntradaEnVigorEspaciador .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelFechaDeEntradaEnVigorEspaciador.right = new FormAttachment(100, 0);
  labelFechaDeEntradaEnVigorEspaciador.setLayoutData(formDataLabelFechaDeEntradaEnVigorEspaciador);
  
  
  // Campo "Fecha de entrada en vigor"
  this.dateChooserFechaDeEntradaEnVigor
   = ComponentsFactory.newDateChooserCombo(compositeRowOneGroupFirmaContrato);
  final FormData formDatadateChooserFechaDeEntradaEnVigor = new FormData();
  formDatadateChooserFechaDeEntradaEnVigor.top
   = new FormAttachment(50, -this.dateChooserFechaDeEntradaEnVigor.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDatadateChooserFechaDeEntradaEnVigor.right = new FormAttachment(labelFechaDeEntradaEnVigorEspaciador, -10);
  formDatadateChooserFechaDeEntradaEnVigor.width = 100;   
  this.dateChooserFechaDeEntradaEnVigor.setLayoutData(formDatadateChooserFechaDeEntradaEnVigor);
  
  // Etiqueta "Fecha de entrada en vigor"
  final Label labelFechaDeEntradaEnVigor = ComponentsFactory.newLabel(
   compositeRowOneGroupFirmaContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaFirmaFechaEntradaVigor
    + Messages.Marca_Obligatorio + Messages.Marca_FinCampo);
  final FormData formDataLabelFechaDeEntradaEnVigor = new FormData();
  formDataLabelFechaDeEntradaEnVigor.top
   = new FormAttachment(50, -labelFechaDeEntradaEnVigor.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelFechaDeEntradaEnVigor.right = new FormAttachment(this.dateChooserFechaDeEntradaEnVigor, -10);
  formDataLabelFechaDeEntradaEnVigor.width
   = labelFechaDeEntradaEnVigor.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
  labelFechaDeEntradaEnVigor.setLayoutData(formDataLabelFechaDeEntradaEnVigor);

  
  
  
  
  
  
  
  
  
  
  
  // Definimos el grupo de componentes correspondientes a 'Duración'.
  final Group groupDuracionContrato
   = ComponentsFactory.newGroup(shell, Messages.ContratosAltaPestanaFechasAlertaDuracion);
  groupDuracionContrato.setLayout(new FormLayout());
  final FormData formDataGroupDuracionContrato = new FormData();
  formDataGroupDuracionContrato.left = new FormAttachment(0, 0);
  formDataGroupDuracionContrato.right = new FormAttachment(100, 0);
  formDataGroupDuracionContrato.top = new FormAttachment(groupFirmaContrato, 10);
  formDataGroupDuracionContrato.bottom = new FormAttachment(100, 0);
  groupDuracionContrato.setLayoutData(formDataGroupDuracionContrato); 
  
  // Primera Fila del Grupo de componentes correspondientes a 'Duración'.
  final Composite compositeRowOneGroupDuracionContrato
   = ComponentsFactory.newComposite(groupDuracionContrato);
  compositeRowOneGroupDuracionContrato.setLayout(new FormLayout());
  //compositeRowOneGroupDuracionContrato.setBackground(display.getSystemColor(SWT.COLOR_GREEN));
  final FormData formDataCompositeRowOneGroupDuracionContrato = new FormData();
  formDataCompositeRowOneGroupDuracionContrato.top = new FormAttachment(0, 5);
  formDataCompositeRowOneGroupDuracionContrato.left = new FormAttachment(0, 0);
  formDataCompositeRowOneGroupDuracionContrato.right = new FormAttachment(100, 0);
  compositeRowOneGroupDuracionContrato.setLayoutData(formDataCompositeRowOneGroupDuracionContrato);
  
  // Etiqueta "Duración inicial"
  final Label labelDuracionInicial = ComponentsFactory.newLabel(
   compositeRowOneGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionInicial
   + Messages.Marca_FinCampo);
  final FormData formDataLabelDuracionInicial = new FormData();
  formDataLabelDuracionInicial.top
   = new FormAttachment(50, -labelDuracionInicial.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelDuracionInicial.left = new FormAttachment(0, 10);
  formDataLabelDuracionInicial.width = 154;
  /* = labelPlazoDerechoProrroga.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;*/ 
  labelDuracionInicial.setLayoutData(formDataLabelDuracionInicial);
  
  // Campo de Texto "Duración inicial - Años"
  this.textDuracionInicialAnos = ComponentsFactory.newTextBigDecimal(
   compositeRowOneGroupDuracionContrato, verifyBigDecimalListener);
  final FormData formDataTextDuracionInicialAnos = new FormData();
  formDataTextDuracionInicialAnos.top
   = new FormAttachment(50, -this.textDuracionInicialAnos.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataTextDuracionInicialAnos.left = new FormAttachment(labelDuracionInicial, 10);
  formDataTextDuracionInicialAnos.width = 20;
  this.textDuracionInicialAnos.setLayoutData(formDataTextDuracionInicialAnos);
  this.textDuracionInicialAnos.addFocusListener(
   new FocusListener() {
    @Override
    public void focusGained(final FocusEvent focusEvent) {
     final Text text  = (Text) focusEvent.widget;
     text.selectAll();
    }
    @Override
    public void focusLost(final FocusEvent focusEvent) {}
   }
  );
  this.textDuracionInicialAnos.addMouseListener(
   new MouseListener() {
    @Override
    public void mouseDoubleClick(final MouseEvent mouseEvent) {}
    @Override
    public void mouseDown(final MouseEvent mouseEvent) {
     final Text text = (Text) mouseEvent.widget;
     text.selectAll();
    }
    @Override
    public void mouseUp(final MouseEvent mouseEvent) {}
   }
  );

  // Etiqueta "Duración inicial - Años"
  final Label labelDuracionInicialAnos = ComponentsFactory.newLabel(
    compositeRowOneGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionAnos);
  final FormData formDataLabelDuracionInicialAnos = new FormData();
  formDataLabelDuracionInicialAnos.top
   = new FormAttachment(50, -labelDuracionInicialAnos .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelDuracionInicialAnos.left = new FormAttachment(this.textDuracionInicialAnos, 5);
  formDataLabelDuracionInicialAnos.width
   = labelDuracionInicialAnos.computeSize(SWT.DEFAULT, SWT.DEFAULT).x; 
  labelDuracionInicialAnos.setLayoutData(formDataLabelDuracionInicialAnos);
  
  // Campo de Texto "Duración inicial - Meses"
  this.textDuracionInicialMeses = ComponentsFactory.newTextBigDecimal(
   compositeRowOneGroupDuracionContrato, verifyBigDecimalListener);
  final FormData formDataTextDuracionInicialMeses = new FormData();
  formDataTextDuracionInicialMeses.top
   = new FormAttachment(50, -this.textDuracionInicialMeses.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataTextDuracionInicialMeses.left = new FormAttachment(labelDuracionInicialAnos, 10);
  formDataTextDuracionInicialMeses.width = 20;
  this.textDuracionInicialMeses.setLayoutData(formDataTextDuracionInicialMeses);
  this.textDuracionInicialMeses.addFocusListener(
   new FocusListener() {
    @Override
    public void focusGained(final FocusEvent focusEvent) {
     final Text text  = (Text) focusEvent.widget;
     text.selectAll();
    }
    @Override
    public void focusLost(final FocusEvent focusEvent) {}
   }
  );
  this.textDuracionInicialMeses.addMouseListener(
   new MouseListener() {
    @Override
    public void mouseDoubleClick(final MouseEvent mouseEvent) {}
    @Override
    public void mouseDown(final MouseEvent mouseEvent) {
     final Text text = (Text) mouseEvent.widget;
     text.selectAll();
    }
    @Override
    public void mouseUp(final MouseEvent mouseEvent) {}
   }
  );

  // Etiqueta "Duración inicial - Meses"
  final Label labelDuracionInicialMeses = ComponentsFactory.newLabel(
    compositeRowOneGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionMeses);
  final FormData formDataLabelDuracionInicialMeses = new FormData();
  formDataLabelDuracionInicialMeses.top
   = new FormAttachment(50, -labelDuracionInicialMeses .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelDuracionInicialMeses.left = new FormAttachment(this.textDuracionInicialMeses, 5);
  formDataLabelDuracionInicialMeses.width
   = labelDuracionInicialMeses.computeSize(SWT.DEFAULT, SWT.DEFAULT).x; 
  labelDuracionInicialMeses.setLayoutData(formDataLabelDuracionInicialMeses);
  
  // Campo de Texto "Duración inicial - Días"
  this.textDuracionInicialDias = ComponentsFactory.newTextBigDecimal(
   compositeRowOneGroupDuracionContrato, verifyBigDecimalListener);
  final FormData formDataTextDuracionInicialDias = new FormData();
  formDataTextDuracionInicialDias.top
   = new FormAttachment(50, -this.textDuracionInicialDias.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataTextDuracionInicialDias.left = new FormAttachment(labelDuracionInicialMeses, 10);
  formDataTextDuracionInicialDias.width = 20;
  this.textDuracionInicialDias.setLayoutData(formDataTextDuracionInicialDias);
  this.textDuracionInicialDias.addFocusListener(
   new FocusListener() {
    @Override
    public void focusGained(final FocusEvent focusEvent) {
     final Text text  = (Text) focusEvent.widget;
     text.selectAll();
    }
    @Override
    public void focusLost(final FocusEvent focusEvent) {}
   }
  );
  this.textDuracionInicialDias.addMouseListener(
   new MouseListener() {
    @Override
    public void mouseDoubleClick(final MouseEvent mouseEvent) {}
    @Override
    public void mouseDown(final MouseEvent mouseEvent) {
     final Text text = (Text) mouseEvent.widget;
     text.selectAll();
    }
    @Override
    public void mouseUp(final MouseEvent mouseEvent) {}
   }
  );

  // Etiqueta "Duración inicial - Días"
  final Label labelDuracionInicialDias = ComponentsFactory.newLabel(
    compositeRowOneGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionDias);
  final FormData formDataLabelDuracionInicialDias = new FormData();
  formDataLabelDuracionInicialDias.top
   = new FormAttachment(50, -labelDuracionInicialDias .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelDuracionInicialDias.left = new FormAttachment(this.textDuracionInicialDias, 5);
  formDataLabelDuracionInicialDias.width
   = labelDuracionInicialDias.computeSize(SWT.DEFAULT, SWT.DEFAULT).x; 
  labelDuracionInicialDias.setLayoutData(formDataLabelDuracionInicialDias);
  
  // Espaciador
  final Label labelDuracionFechaVencimientoEspaciador = ComponentsFactory.newLabel(
    compositeRowOneGroupDuracionContrato, SWT.LEFT, "");
  final FormData formDataLabelDuracionFechaVencimientoEspaciador = new FormData();
  formDataLabelDuracionFechaVencimientoEspaciador.top
   = new FormAttachment(50, -labelDuracionFechaVencimientoEspaciador .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelDuracionFechaVencimientoEspaciador.right = new FormAttachment(100, 0);
  labelDuracionFechaVencimientoEspaciador.setLayoutData(formDataLabelDuracionFechaVencimientoEspaciador);
  
  // Campo "Fecha de Vencimiento"
  this.dateChooserFechaVencimiento
   = ComponentsFactory.newDateChooserCombo(compositeRowOneGroupDuracionContrato);
  final FormData formDatadateChooserFechaVencimiento = new FormData();
  formDatadateChooserFechaVencimiento.top
   = new FormAttachment(50, -this.dateChooserFechaVencimiento.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDatadateChooserFechaVencimiento.right = new FormAttachment(labelDuracionFechaVencimientoEspaciador, -10);
  formDatadateChooserFechaVencimiento.width = 100;   
  this.dateChooserFechaVencimiento.setLayoutData(formDatadateChooserFechaVencimiento);
  
  // Etiqueta "Fecha de Vencimiento"
  final Label labelDuracionFechaVencimiento = ComponentsFactory.newLabel(
    compositeRowOneGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionFechaVencimiento
   + Messages.Marca_FinCampo);
  final FormData formDataLabelDuracionFechaVencimiento = new FormData();
  formDataLabelDuracionFechaVencimiento.top
   = new FormAttachment(50, -labelDuracionFechaVencimiento .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelDuracionFechaVencimiento.right = new FormAttachment(this.dateChooserFechaVencimiento, -10);
  formDataLabelDuracionFechaVencimiento.width = 170;
  /* = labelDuracionFechaDerechoProrroga.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;*/ 
  labelDuracionFechaVencimiento.setLayoutData(formDataLabelDuracionFechaVencimiento);

  
  /* Añadir Boton. */
  
  
  
  
  
  
  
  
  
  // Segunda Fila del Grupo de componentes correspondientes a 'Duración'.
  final Composite compositeRowTwoGroupDuracionContrato
   = ComponentsFactory.newComposite(groupDuracionContrato);
  compositeRowTwoGroupDuracionContrato.setLayout(new FormLayout());
  //compositeRowTwoGroupDuracionContrato.setBackground(display.getSystemColor(SWT.COLOR_BLUE));  
  final FormData formDataCompositeRowTwoGroupDuracionContrato = new FormData();
  formDataCompositeRowTwoGroupDuracionContrato.top = new FormAttachment(compositeRowOneGroupDuracionContrato, 5);
  formDataCompositeRowTwoGroupDuracionContrato.left = new FormAttachment(0, 0);
  formDataCompositeRowTwoGroupDuracionContrato.right = new FormAttachment(100, 0);
  //TODO formDataCompositeRowTwoGroupDuracionContrato.bottom = new FormAttachment(50, 0);
  compositeRowTwoGroupDuracionContrato.setLayoutData(formDataCompositeRowTwoGroupDuracionContrato);
  
  // Check "Prorrogar"
  this.checkProrrogar = ComponentsFactory.newCheckButton(
    compositeRowTwoGroupDuracionContrato, "");   
  final FormData formDataCheckProrrogar = new FormData();
  formDataCheckProrrogar.top
   = new FormAttachment(50, -this.checkProrrogar.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataCheckProrrogar.left = new FormAttachment(0, 10);
  this.checkProrrogar.setLayoutData(formDataCheckProrrogar);
  
  // Etiqueta "Prorrogar"
  final Label labelProrrogar
   = ComponentsFactory.newLabel(compositeRowTwoGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionProrrogar);
  final FormData formDataLabelProrrogar = new FormData();
  formDataLabelProrrogar.top
   = new FormAttachment(50, -labelProrrogar.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelProrrogar.left = new FormAttachment(this.checkProrrogar, 5);
  formDataLabelProrrogar.width
   = labelProrrogar.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;  
  labelProrrogar.setLayoutData(formDataLabelProrrogar);
  

  
  
  
  
  
  
  
  
  
  
  
  // Tercera Fila del Grupo de componentes correspondientes a 'Duración'.
  final Composite compositeRowThreeGroupDuracionContrato
   = ComponentsFactory.newComposite(groupDuracionContrato);
  compositeRowThreeGroupDuracionContrato.setLayout(new FormLayout());
  //compositeRowThreeGroupDuracionContrato.setBackground(display.getSystemColor(SWT.COLOR_YELLOW));  
  final FormData formDataCompositeRowThreeGroupDuracionContrato = new FormData();
  formDataCompositeRowThreeGroupDuracionContrato.top = new FormAttachment(compositeRowTwoGroupDuracionContrato, 5);
  formDataCompositeRowThreeGroupDuracionContrato.left = new FormAttachment(0, 0);
  formDataCompositeRowThreeGroupDuracionContrato.right = new FormAttachment(100, 0);
  compositeRowThreeGroupDuracionContrato.setLayoutData(formDataCompositeRowThreeGroupDuracionContrato);
  
  
  
  // Espaciador
  final Label labelBotonesEspaciador = ComponentsFactory.newLabel(
    compositeRowThreeGroupDuracionContrato, SWT.LEFT, "");
  final FormData formDataLabelBotonesEspaciador = new FormData();
  formDataLabelBotonesEspaciador.top
   = new FormAttachment(50, -labelBotonesEspaciador .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelBotonesEspaciador.right = new FormAttachment(100, 0);
  labelBotonesEspaciador.setLayoutData(formDataLabelBotonesEspaciador);
  
  // Boton "Eliminar"
  this.ButtonEliminar = ComponentsFactory.newButton(
   compositeRowThreeGroupDuracionContrato,
   Messages.ContratosAltaPestanaFechasAlertaDuracionButtomEliminar, null /*,
   SWTSFIResourceFactory.getImage(ImagesAjenosMaestros.ICO_ELIMINAR)*/);
  final FormData formDataButtonEliminar = new FormData();
  formDataButtonEliminar.top
   = new FormAttachment(50, -this.ButtonEliminar.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);  
  formDataButtonEliminar.right = new FormAttachment(labelBotonesEspaciador, -10);
  formDataButtonEliminar.width = 60;
  this.ButtonEliminar.setLayoutData(formDataButtonEliminar);
  
  // Boton "Modificar"
  this.ButtonModificar = ComponentsFactory.newButton(
   compositeRowThreeGroupDuracionContrato,
   Messages.ContratosAltaPestanaFechasAlertaDuracionButtomModificar, null /*,
   SWTSFIResourceFactory.getImage(ImagesAjenosMaestros.ICO_ELIMINAR)*/);
  final FormData formDataButtonModificar = new FormData();
  formDataButtonModificar.top
   = new FormAttachment(50, -this.ButtonModificar.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);  
  formDataButtonModificar.right = new FormAttachment(this.ButtonEliminar, -5);
  formDataButtonModificar.width = 60;
  this.ButtonModificar.setLayoutData(formDataButtonModificar);
  
  // Boton "Crear"
  this.ButtonCrear = ComponentsFactory.newButton(
   compositeRowThreeGroupDuracionContrato,
   Messages.ContratosAltaPestanaFechasAlertaDuracionButtomCrear, null /*,
   SWTSFIResourceFactory.getImage(ImagesAjenosMaestros.ICO_ELIMINAR)*/);
  final FormData formDataButtonCrear = new FormData();
  formDataButtonCrear.top
   = new FormAttachment(50, -this.ButtonCrear.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);  
  formDataButtonCrear.right = new FormAttachment(this.ButtonModificar, -5);
  formDataButtonCrear.width = 60;
  this.ButtonCrear.setLayoutData(formDataButtonCrear);

  
  
  /*this.btnEliminar
  .addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(final SelectionEvent e) {
          final ComandoDeleteDescripcionEmpaquetadoIdioma comandoDeleteDescripcionEmpaquetadoIdioma = SFIResourceFactory
                  .getBean(ComandoDeleteDescripcionEmpaquetadoIdioma.class);
          comandoDeleteDescripcionEmpaquetadoIdioma.execute();
      }
  });*/
  
  
  
  
  // Cuarta Fila del Grupo de componentes correspondientes a 'Duración'.
  final Composite compositeRowFourthGroupDuracionContrato
   = ComponentsFactory.newComposite(groupDuracionContrato);
  compositeRowFourthGroupDuracionContrato.setLayout(new FormLayout());
  compositeRowFourthGroupDuracionContrato.setBackground(display.getSystemColor(SWT.COLOR_GRAY));  
  final FormData formDataCompositeRowFourthGroupDuracionContrato = new FormData();
  formDataCompositeRowFourthGroupDuracionContrato.top = new FormAttachment(compositeRowThreeGroupDuracionContrato, 10);
  formDataCompositeRowFourthGroupDuracionContrato.left = new FormAttachment(0, 10);
  formDataCompositeRowFourthGroupDuracionContrato.right = new FormAttachment(100, -10);
  compositeRowFourthGroupDuracionContrato.setLayoutData(formDataCompositeRowFourthGroupDuracionContrato);
  

  // Separador
  final Label labelSeparator
   = ComponentsFactory.newLabel(compositeRowFourthGroupDuracionContrato, SWT.HORIZONTAL | SWT.SEPARATOR, "");
  final FormData formDataLabelSeparator = new FormData();
  formDataLabelSeparator.top
   = new FormAttachment(0, -labelSeparator.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelSeparator.left = new FormAttachment(100, 0);
  formDataLabelSeparator.height = 2;
  labelSeparator.setLayoutData(formDataLabelSeparator);



  
  
  
  
  // Quinta Fila del Grupo de componentes correspondientes a 'Duración'.
  final Composite compositeRowFifthGroupDuracionContrato
   = ComponentsFactory.newComposite(groupDuracionContrato);
  compositeRowFifthGroupDuracionContrato.setLayout(new FormLayout());
  //compositeRowFifthGroupDuracionContrato.setBackground(display.getSystemColor(SWT.COLOR_GREEN));
  final FormData formDataCompositeRowFifthGroupDuracionContrato = new FormData();
  formDataCompositeRowFifthGroupDuracionContrato.top = new FormAttachment(compositeRowFourthGroupDuracionContrato, 10);
  formDataCompositeRowFifthGroupDuracionContrato.left = new FormAttachment(0, 0);
  formDataCompositeRowFifthGroupDuracionContrato.right = new FormAttachment(100, 0);
  compositeRowFifthGroupDuracionContrato.setLayoutData(formDataCompositeRowFifthGroupDuracionContrato);
  
  // Etiqueta "Plazo preaviso"
  final Label labelPlazoPreaviso = ComponentsFactory.newLabel(
   compositeRowFifthGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionPlazoPreaviso
   + Messages.Marca_FinCampo);
  final FormData formDataLabelPlazoPreaviso = new FormData();
  formDataLabelPlazoPreaviso.top
   = new FormAttachment(50, -labelPlazoPreaviso.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelPlazoPreaviso.left = new FormAttachment(0, 10);
  formDataLabelPlazoPreaviso.width = 154;
  /* = labelPlazoDerechoProrroga.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;*/ 
  labelPlazoPreaviso.setLayoutData(formDataLabelPlazoPreaviso);
  
  // Campo de Texto "Plazo preaviso - Años"
  this.textPlazoPreavisoAnos = ComponentsFactory.newTextBigDecimal(
   compositeRowFifthGroupDuracionContrato, verifyBigDecimalListener);
  final FormData formDataTextPlazoPreavisoAnos = new FormData();
  formDataTextPlazoPreavisoAnos.top
   = new FormAttachment(50, -this.textPlazoPreavisoAnos.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataTextPlazoPreavisoAnos.left = new FormAttachment(labelPlazoPreaviso, 10);
  formDataTextPlazoPreavisoAnos.width = 20;
  this.textPlazoPreavisoAnos.setLayoutData(formDataTextPlazoPreavisoAnos);
  this.textPlazoPreavisoAnos.addFocusListener(
   new FocusListener() {
    @Override
    public void focusGained(final FocusEvent focusEvent) {
     final Text text  = (Text) focusEvent.widget;
     text.selectAll();
    }
    @Override
    public void focusLost(final FocusEvent focusEvent) {}
   }
  );
  this.textPlazoPreavisoAnos.addMouseListener(
   new MouseListener() {
    @Override
    public void mouseDoubleClick(final MouseEvent mouseEvent) {}
    @Override
    public void mouseDown(final MouseEvent mouseEvent) {
     final Text text = (Text) mouseEvent.widget;
     text.selectAll();
    }
    @Override
    public void mouseUp(final MouseEvent mouseEvent) {}
   }
  );

  // Etiqueta "Plazo preaviso - Años"
  final Label labelPlazoPreavisoAnos = ComponentsFactory.newLabel(
    compositeRowFifthGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionAnos);
  final FormData formDataLabelPlazoPreavisoAnos = new FormData();
  formDataLabelPlazoPreavisoAnos.top
   = new FormAttachment(50, -labelPlazoPreavisoAnos .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelPlazoPreavisoAnos.left = new FormAttachment(this.textPlazoPreavisoAnos, 5);
  formDataLabelPlazoPreavisoAnos.width
   = labelPlazoPreavisoAnos.computeSize(SWT.DEFAULT, SWT.DEFAULT).x; 
  labelPlazoPreavisoAnos.setLayoutData(formDataLabelPlazoPreavisoAnos);
  
  // Campo de Texto "Plazo preaviso - Meses"
  this.textPlazoPreavisoMeses = ComponentsFactory.newTextBigDecimal(
   compositeRowFifthGroupDuracionContrato, verifyBigDecimalListener);
  final FormData formDataTextPlazoPreavisoMeses = new FormData();
  formDataTextPlazoPreavisoMeses.top
   = new FormAttachment(50, -this.textPlazoPreavisoMeses.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataTextPlazoPreavisoMeses.left = new FormAttachment(labelPlazoPreavisoAnos, 10);
  formDataTextPlazoPreavisoMeses.width = 20;
  this.textPlazoPreavisoMeses.setLayoutData(formDataTextPlazoPreavisoMeses);
  this.textPlazoPreavisoMeses.addFocusListener(
   new FocusListener() {
    @Override
    public void focusGained(final FocusEvent focusEvent) {
     final Text text  = (Text) focusEvent.widget;
     text.selectAll();
    }
    @Override
    public void focusLost(final FocusEvent focusEvent) {}
   }
  );
  this.textPlazoPreavisoMeses.addMouseListener(
   new MouseListener() {
    @Override
    public void mouseDoubleClick(final MouseEvent mouseEvent) {}
    @Override
    public void mouseDown(final MouseEvent mouseEvent) {
     final Text text = (Text) mouseEvent.widget;
     text.selectAll();
    }
    @Override
    public void mouseUp(final MouseEvent mouseEvent) {}
   }
  );

  // Etiqueta "Plazo preaviso - Meses"
  final Label labelPlazoPreavisoMeses = ComponentsFactory.newLabel(
    compositeRowFifthGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionMeses);
  final FormData formDataLabelPlazoPreavisoMeses = new FormData();
  formDataLabelPlazoPreavisoMeses.top
   = new FormAttachment(50, -labelPlazoPreavisoMeses .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelPlazoPreavisoMeses.left = new FormAttachment(this.textPlazoPreavisoMeses, 5);
  formDataLabelPlazoPreavisoMeses.width
   = labelPlazoPreavisoMeses.computeSize(SWT.DEFAULT, SWT.DEFAULT).x; 
  labelPlazoPreavisoMeses.setLayoutData(formDataLabelPlazoPreavisoMeses);
  
  // Campo de Texto "Plazo preaviso - Días"
  this.textPlazoPreavisoDias = ComponentsFactory.newTextBigDecimal(
   compositeRowFifthGroupDuracionContrato, verifyBigDecimalListener);
  final FormData formDataTextPlazoPreavisoDias = new FormData();
  formDataTextPlazoPreavisoDias.top
   = new FormAttachment(50, -this.textPlazoPreavisoDias.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataTextPlazoPreavisoDias.left = new FormAttachment(labelPlazoPreavisoMeses, 10);
  formDataTextPlazoPreavisoDias.width = 20;
  this.textPlazoPreavisoDias.setLayoutData(formDataTextPlazoPreavisoDias);
  this.textPlazoPreavisoDias.addFocusListener(
   new FocusListener() {
    @Override
    public void focusGained(final FocusEvent focusEvent) {
     final Text text  = (Text) focusEvent.widget;
     text.selectAll();
    }
    @Override
    public void focusLost(final FocusEvent focusEvent) {}
   }
  );
  this.textPlazoPreavisoDias.addMouseListener(
   new MouseListener() {
    @Override
    public void mouseDoubleClick(final MouseEvent mouseEvent) {}
    @Override
    public void mouseDown(final MouseEvent mouseEvent) {
     final Text text = (Text) mouseEvent.widget;
     text.selectAll();
    }
    @Override
    public void mouseUp(final MouseEvent mouseEvent) {}
   }
  );

  // Etiqueta "Plazo preaviso - Días"
  final Label labelPlazoPreavisoDias = ComponentsFactory.newLabel(
    compositeRowFifthGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionDias);
  final FormData formDataLabelPlazoPreavisoDias = new FormData();
  formDataLabelPlazoPreavisoDias.top
   = new FormAttachment(50, -labelPlazoPreavisoDias .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelPlazoPreavisoDias.left = new FormAttachment(this.textPlazoPreavisoDias, 5);
  formDataLabelPlazoPreavisoDias.width
   = labelPlazoPreavisoDias.computeSize(SWT.DEFAULT, SWT.DEFAULT).x; 
  labelPlazoPreavisoDias.setLayoutData(formDataLabelPlazoPreavisoDias);
  
  // Espaciador
  final Label labelPlazoPreavisoDiasEspaciador = ComponentsFactory.newLabel(
    compositeRowFifthGroupDuracionContrato, SWT.LEFT, "");
  final FormData formDataLabelPlazoPreavisoDiasEspaciador = new FormData();
  formDataLabelPlazoPreavisoDiasEspaciador.top
   = new FormAttachment(50, -labelPlazoPreavisoDiasEspaciador .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelPlazoPreavisoDiasEspaciador.right = new FormAttachment(100, 0);
  labelPlazoPreavisoDiasEspaciador.setLayoutData(formDataLabelPlazoPreavisoDiasEspaciador);

  // Campo "Fecha de preaviso para resolver"
  this.dateChooserFechaPreavisoResolver
   = ComponentsFactory.newDateChooserCombo(compositeRowFifthGroupDuracionContrato);
  final FormData formDatadateChooserFechaPreavisoResolver = new FormData();
  formDatadateChooserFechaPreavisoResolver.top
   = new FormAttachment(50, -this.dateChooserFechaPreavisoResolver.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDatadateChooserFechaPreavisoResolver.right = new FormAttachment(labelPlazoPreavisoDiasEspaciador, -10);
  formDatadateChooserFechaPreavisoResolver.width = 100;   
  this.dateChooserFechaPreavisoResolver.setLayoutData(formDatadateChooserFechaPreavisoResolver);
  
  // Etiqueta "Fecha de preaviso para resolver"
  final Label labelDuracionFechaPreavisoResolver = ComponentsFactory.newLabel(
    compositeRowFifthGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionFechaPreavisoResolver
   + Messages.Marca_FinCampo);
  final FormData formDataLabelDuracionFechaPreavisoResolver = new FormData();
  formDataLabelDuracionFechaPreavisoResolver.top
   = new FormAttachment(50, -labelDuracionFechaPreavisoResolver .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelDuracionFechaPreavisoResolver.right = new FormAttachment(this.dateChooserFechaPreavisoResolver, -10);
  formDataLabelDuracionFechaPreavisoResolver.width = 170;
  /* = labelDuracionFechaDerechoProrroga.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;*/ 
  labelDuracionFechaPreavisoResolver.setLayoutData(formDataLabelDuracionFechaPreavisoResolver);
  

  /* Añadir Boton. */
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  // Sexta Fila del Grupo de componentes correspondientes a 'Duración'.
  final Composite compositeRowSixthGroupDuracionContrato
   = ComponentsFactory.newComposite(groupDuracionContrato);
  compositeRowSixthGroupDuracionContrato.setLayout(new FormLayout());
  //compositeRowSixthGroupDuracionContrato.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
  final FormData formDataCompositeRowSixthGroupDuracionContrato = new FormData();
  formDataCompositeRowSixthGroupDuracionContrato.top = new FormAttachment(compositeRowFifthGroupDuracionContrato, 5);
  formDataCompositeRowSixthGroupDuracionContrato.left = new FormAttachment(0, 0);
  formDataCompositeRowSixthGroupDuracionContrato.right = new FormAttachment(100, 0);
  compositeRowSixthGroupDuracionContrato.setLayoutData(formDataCompositeRowSixthGroupDuracionContrato);
  
  // Etiqueta "Plazo de derecho a prorroga"
  final Label labelPlazoDerechoProrroga = ComponentsFactory.newLabel(
   compositeRowSixthGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionPlazoDerechoProrroga
   + Messages.Marca_FinCampo);
  final FormData formDataLabelPlazoDerechoProrroga = new FormData();
  formDataLabelPlazoDerechoProrroga.top
   = new FormAttachment(50, -labelPlazoDerechoProrroga.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelPlazoDerechoProrroga.left = new FormAttachment(0, 10);
  formDataLabelPlazoDerechoProrroga.width = 154;
  /* = labelPlazoDerechoProrroga.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;*/ 
  labelPlazoDerechoProrroga.setLayoutData(formDataLabelPlazoDerechoProrroga);
  
  // Campo de Texto "Plazo de derecho a prorroga - Años"
  this.textPlazoDerechoProrrogaAnos = ComponentsFactory.newTextBigDecimal(
   compositeRowSixthGroupDuracionContrato, verifyBigDecimalListener);
  final FormData formDataTextPlazoDerechoProrrogaAnos = new FormData();
  formDataTextPlazoDerechoProrrogaAnos.top
   = new FormAttachment(50, -this.textPlazoDerechoProrrogaAnos.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataTextPlazoDerechoProrrogaAnos.left = new FormAttachment(labelPlazoDerechoProrroga, 10);
  formDataTextPlazoDerechoProrrogaAnos.width = 20;
  this.textPlazoDerechoProrrogaAnos.setLayoutData(formDataTextPlazoDerechoProrrogaAnos);
  this.textPlazoDerechoProrrogaAnos.addFocusListener(
   new FocusListener() {
    @Override
    public void focusGained(final FocusEvent focusEvent) {
     final Text text  = (Text) focusEvent.widget;
     text.selectAll();
    }
    @Override
    public void focusLost(final FocusEvent focusEvent) {}
   }
  );
  this.textPlazoDerechoProrrogaAnos.addMouseListener(
   new MouseListener() {
    @Override
    public void mouseDoubleClick(final MouseEvent mouseEvent) {}
    @Override
    public void mouseDown(final MouseEvent mouseEvent) {
     final Text text = (Text) mouseEvent.widget;
     text.selectAll();
    }
    @Override
    public void mouseUp(final MouseEvent mouseEvent) {}
   }
  );

  // Etiqueta "Plazo de derecho a prorroga - Años"
  final Label labelPlazoDerechoProrrogaAnos = ComponentsFactory.newLabel(
    compositeRowSixthGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionAnos);
  final FormData formDataLabelPlazoDerechoProrrogaAnos = new FormData();
  formDataLabelPlazoDerechoProrrogaAnos.top
   = new FormAttachment(50, -labelPlazoDerechoProrrogaAnos .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelPlazoDerechoProrrogaAnos.left = new FormAttachment(this.textPlazoDerechoProrrogaAnos, 5);
  formDataLabelPlazoDerechoProrrogaAnos.width
   = labelPlazoDerechoProrrogaAnos.computeSize(SWT.DEFAULT, SWT.DEFAULT).x; 
  labelPlazoDerechoProrrogaAnos.setLayoutData(formDataLabelPlazoDerechoProrrogaAnos);
  
  // Campo de Texto "Plazo de derecho a prorroga - Meses"
  this.textPlazoDerechoProrrogaMeses = ComponentsFactory.newTextBigDecimal(
   compositeRowSixthGroupDuracionContrato, verifyBigDecimalListener);
  final FormData formDataTextPlazoDerechoProrrogaMeses = new FormData();
  formDataTextPlazoDerechoProrrogaMeses.top
   = new FormAttachment(50, -this.textPlazoDerechoProrrogaMeses.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataTextPlazoDerechoProrrogaMeses.left = new FormAttachment(labelPlazoDerechoProrrogaAnos, 10);
  formDataTextPlazoDerechoProrrogaMeses.width = 20;
  this.textPlazoDerechoProrrogaMeses.setLayoutData(formDataTextPlazoDerechoProrrogaMeses);
  this.textPlazoDerechoProrrogaMeses.addFocusListener(
   new FocusListener() {
    @Override
    public void focusGained(final FocusEvent focusEvent) {
     final Text text  = (Text) focusEvent.widget;
     text.selectAll();
    }
    @Override
    public void focusLost(final FocusEvent focusEvent) {}
   }
  );
  this.textPlazoDerechoProrrogaMeses.addMouseListener(
   new MouseListener() {
    @Override
    public void mouseDoubleClick(final MouseEvent mouseEvent) {}
    @Override
    public void mouseDown(final MouseEvent mouseEvent) {
     final Text text = (Text) mouseEvent.widget;
     text.selectAll();
    }
    @Override
    public void mouseUp(final MouseEvent mouseEvent) {}
   }
  );

  // Etiqueta "Plazo de derecho a prorroga - Meses"
  final Label labelPlazoDerechoProrrogaMeses = ComponentsFactory.newLabel(
    compositeRowSixthGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionMeses);
  final FormData formDataLabelPlazoDerechoProrrogaMeses = new FormData();
  formDataLabelPlazoDerechoProrrogaMeses.top
   = new FormAttachment(50, -labelPlazoDerechoProrrogaMeses .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelPlazoDerechoProrrogaMeses.left = new FormAttachment(this.textPlazoDerechoProrrogaMeses, 5);
  formDataLabelPlazoDerechoProrrogaMeses.width
   = labelPlazoDerechoProrrogaMeses.computeSize(SWT.DEFAULT, SWT.DEFAULT).x; 
  labelPlazoDerechoProrrogaMeses.setLayoutData(formDataLabelPlazoDerechoProrrogaMeses);
  
  // Campo de Texto "Plazo de derecho a prorroga - Días"
  this.textPlazoDerechoProrrogaDias = ComponentsFactory.newTextBigDecimal(
   compositeRowSixthGroupDuracionContrato, verifyBigDecimalListener);
  final FormData formDataTextPlazoDerechoProrrogaDias = new FormData();
  formDataTextPlazoDerechoProrrogaDias.top
   = new FormAttachment(50, -this.textPlazoDerechoProrrogaDias.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataTextPlazoDerechoProrrogaDias.left = new FormAttachment(labelPlazoDerechoProrrogaMeses, 10);
  formDataTextPlazoDerechoProrrogaDias.width = 20;
  this.textPlazoDerechoProrrogaDias.setLayoutData(formDataTextPlazoDerechoProrrogaDias);
  this.textPlazoDerechoProrrogaDias.addFocusListener(
   new FocusListener() {
    @Override
    public void focusGained(final FocusEvent focusEvent) {
     final Text text  = (Text) focusEvent.widget;
     text.selectAll();
    }
    @Override
    public void focusLost(final FocusEvent focusEvent) {}
   }
  );
  this.textPlazoDerechoProrrogaDias.addMouseListener(
   new MouseListener() {
    @Override
    public void mouseDoubleClick(final MouseEvent mouseEvent) {}
    @Override
    public void mouseDown(final MouseEvent mouseEvent) {
     final Text text = (Text) mouseEvent.widget;
     text.selectAll();
    }
    @Override
    public void mouseUp(final MouseEvent mouseEvent) {}
   }
  );

  // Etiqueta "Plazo de derecho a prorroga - Días"
  final Label labelPlazoDerechoProrrogaDias = ComponentsFactory.newLabel(
    compositeRowSixthGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionDias);
  final FormData formDataLabelPlazoDerechoProrrogaDias = new FormData();
  formDataLabelPlazoDerechoProrrogaDias.top
   = new FormAttachment(50, -labelPlazoDerechoProrrogaDias .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelPlazoDerechoProrrogaDias.left = new FormAttachment(this.textPlazoDerechoProrrogaDias, 5);
  formDataLabelPlazoDerechoProrrogaDias.width
   = labelPlazoDerechoProrrogaDias.computeSize(SWT.DEFAULT, SWT.DEFAULT).x; 
  labelPlazoDerechoProrrogaDias.setLayoutData(formDataLabelPlazoDerechoProrrogaDias);
  
  // Espaciador
  final Label labelDuracionDerechoProrrogaEspaciador = ComponentsFactory.newLabel(
    compositeRowSixthGroupDuracionContrato, SWT.LEFT, "");
  final FormData formDataLabelDuracionDerechoProrrogaEspaciador = new FormData();
  formDataLabelDuracionDerechoProrrogaEspaciador.top
   = new FormAttachment(50, -labelDuracionDerechoProrrogaEspaciador .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelDuracionDerechoProrrogaEspaciador.right = new FormAttachment(100, 0);
  labelDuracionDerechoProrrogaEspaciador.setLayoutData(formDataLabelDuracionDerechoProrrogaEspaciador);
  
  // Campo "Fecha de derecho a prorroga"
  this.dateChooserFechaDerechoProrroga
   = ComponentsFactory.newDateChooserCombo(compositeRowSixthGroupDuracionContrato);
  final FormData formDatadateChooserFechaDerechoProrroga = new FormData();
  formDatadateChooserFechaDerechoProrroga.top
   = new FormAttachment(50, -this.dateChooserFechaDerechoProrroga.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDatadateChooserFechaDerechoProrroga.right = new FormAttachment(labelDuracionDerechoProrrogaEspaciador, -10);
  formDatadateChooserFechaDerechoProrroga.width = 100;   
  this.dateChooserFechaDerechoProrroga.setLayoutData(formDatadateChooserFechaDerechoProrroga);
  
  // Etiqueta "Fecha de derecho a prorroga"
  final Label labelDuracionFechaDerechoProrroga = ComponentsFactory.newLabel(
    compositeRowSixthGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionFechaDerechoProrroga
   + Messages.Marca_FinCampo);
  final FormData formDataLabelDuracionFechaDerechoProrroga = new FormData();
  formDataLabelDuracionFechaDerechoProrroga.top
   = new FormAttachment(50, -labelDuracionFechaDerechoProrroga .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelDuracionFechaDerechoProrroga.right = new FormAttachment(this.dateChooserFechaDerechoProrroga, -10);
  formDataLabelDuracionFechaDerechoProrroga.width = 170;
  /* = labelDuracionFechaDerechoProrroga.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;*/ 
  labelDuracionFechaDerechoProrroga.setLayoutData(formDataLabelDuracionFechaDerechoProrroga);

  
  /* Añadir Boton. */
  
  
  
  
  
  
  
  
  
  
  // Septima Fila del Grupo de componentes correspondientes a 'Duración'.
  final Composite compositeRowSeventhGroupDuracionContrato
   = ComponentsFactory.newComposite(groupDuracionContrato);
  compositeRowSeventhGroupDuracionContrato.setLayout(new FormLayout());
  //compositeRowSeventhGroupDuracionContrato.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
  final FormData formDataCompositeRowSeventhGroupDuracionContrato = new FormData();
  formDataCompositeRowSeventhGroupDuracionContrato.top = new FormAttachment(compositeRowSixthGroupDuracionContrato, 5);
  formDataCompositeRowSeventhGroupDuracionContrato.left = new FormAttachment(0, 0);
  formDataCompositeRowSeventhGroupDuracionContrato.right = new FormAttachment(100, 0);
  compositeRowSeventhGroupDuracionContrato.setLayoutData(formDataCompositeRowSeventhGroupDuracionContrato);
  
  
  
  
//Check "Desistimiento unilateral"
 this.checkDesistimientoUnilateral = ComponentsFactory.newCheckButton(
   compositeRowSeventhGroupDuracionContrato, "");   
 final FormData formDataCheckDesistimientoUnilateral = new FormData();
 formDataCheckDesistimientoUnilateral.top
  = new FormAttachment(50, -this.checkDesistimientoUnilateral.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
 formDataCheckDesistimientoUnilateral.left = new FormAttachment(0, 10);
 this.checkDesistimientoUnilateral.setLayoutData(formDataCheckDesistimientoUnilateral);
 
 // Etiqueta "Desistimiento unilateral"
 final Label labelDesistimientoUnilateral
  = ComponentsFactory.newLabel(compositeRowSeventhGroupDuracionContrato, SWT.LEFT,
  Messages.ContratosAltaPestanaFechasAlertaDuracionDesistimientoUnilateral);
 final FormData formDataLabelDesistimientoUnilateral = new FormData();
 formDataLabelDesistimientoUnilateral.top
  = new FormAttachment(50, -labelDesistimientoUnilateral.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
 formDataLabelDesistimientoUnilateral.left = new FormAttachment(this.checkDesistimientoUnilateral, 5);
 formDataLabelDesistimientoUnilateral.width = 136;
  /*= labelDesistimientoUnilateral.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;*/  
 labelDesistimientoUnilateral.setLayoutData(formDataLabelDesistimientoUnilateral);
 
  // Campo de Texto "Desistimiento unilateral - Años"
  this.textDesistimientoUnilateralAnos = ComponentsFactory.newTextBigDecimal(
   compositeRowSeventhGroupDuracionContrato, verifyBigDecimalListener);
  final FormData formDataTextDesistimientoUnilateralAnos = new FormData();
  formDataTextDesistimientoUnilateralAnos.top
   = new FormAttachment(50, -this.textDesistimientoUnilateralAnos.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataTextDesistimientoUnilateralAnos.left = new FormAttachment(labelDesistimientoUnilateral, 10);
  formDataTextDesistimientoUnilateralAnos.width = 20;
  this.textDesistimientoUnilateralAnos.setLayoutData(formDataTextDesistimientoUnilateralAnos);
  this.textDesistimientoUnilateralAnos.addFocusListener(
   new FocusListener() {
    @Override
    public void focusGained(final FocusEvent focusEvent) {
     final Text text  = (Text) focusEvent.widget;
     text.selectAll();
    }
    @Override
    public void focusLost(final FocusEvent focusEvent) {}
   }
  );
  this.textDesistimientoUnilateralAnos.addMouseListener(
   new MouseListener() {
    @Override
    public void mouseDoubleClick(final MouseEvent mouseEvent) {}
    @Override
    public void mouseDown(final MouseEvent mouseEvent) {
     final Text text = (Text) mouseEvent.widget;
     text.selectAll();
    }
    @Override
    public void mouseUp(final MouseEvent mouseEvent) {}
   }
  );

  
  
  
  // Etiqueta "Desistimiento unilateral - Años"
  final Label labelDesistimientoUnilateralAnos = ComponentsFactory.newLabel(
    compositeRowSeventhGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionAnos);
  final FormData formDataLabelDesistimientoUnilateralAnos = new FormData();
  formDataLabelDesistimientoUnilateralAnos.top
   = new FormAttachment(50, -labelDesistimientoUnilateralAnos .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelDesistimientoUnilateralAnos.left = new FormAttachment(this.textDesistimientoUnilateralAnos, 5);
  formDataLabelDesistimientoUnilateralAnos.width
   = labelDesistimientoUnilateralAnos.computeSize(SWT.DEFAULT, SWT.DEFAULT).x; 
  labelDesistimientoUnilateralAnos.setLayoutData(formDataLabelDesistimientoUnilateralAnos);
  
  // Campo de Texto "Desistimiento unilateral - Meses"
  this.textDesistimientoUnilateralMeses = ComponentsFactory.newTextBigDecimal(
   compositeRowSeventhGroupDuracionContrato, verifyBigDecimalListener);
  final FormData formDataTextDesistimientoUnilateralMeses = new FormData();
  formDataTextDesistimientoUnilateralMeses.top
   = new FormAttachment(50, -this.textDesistimientoUnilateralMeses.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataTextDesistimientoUnilateralMeses.left = new FormAttachment(labelDesistimientoUnilateralAnos, 10);
  formDataTextDesistimientoUnilateralMeses.width = 20;
  this.textDesistimientoUnilateralMeses.setLayoutData(formDataTextDesistimientoUnilateralMeses);
  this.textDesistimientoUnilateralMeses.addFocusListener(
   new FocusListener() {
    @Override
    public void focusGained(final FocusEvent focusEvent) {
     final Text text  = (Text) focusEvent.widget;
     text.selectAll();
    }
    @Override
    public void focusLost(final FocusEvent focusEvent) {}
   }
  );
  this.textDesistimientoUnilateralMeses.addMouseListener(
   new MouseListener() {
    @Override
    public void mouseDoubleClick(final MouseEvent mouseEvent) {}
    @Override
    public void mouseDown(final MouseEvent mouseEvent) {
     final Text text = (Text) mouseEvent.widget;
     text.selectAll();
    }
    @Override
    public void mouseUp(final MouseEvent mouseEvent) {}
   }
  );

  // Etiqueta "Desistimiento unilateral - Meses"
  final Label labelDesistimientoUnilateralMeses = ComponentsFactory.newLabel(
    compositeRowSeventhGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionMeses);
  final FormData formDataLabelDesistimientoUnilateralMeses = new FormData();
  formDataLabelDesistimientoUnilateralMeses.top
   = new FormAttachment(50, -labelDesistimientoUnilateralMeses .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelDesistimientoUnilateralMeses.left = new FormAttachment(this.textDesistimientoUnilateralMeses, 5);
  formDataLabelDesistimientoUnilateralMeses.width
   = labelDesistimientoUnilateralMeses.computeSize(SWT.DEFAULT, SWT.DEFAULT).x; 
  labelDesistimientoUnilateralMeses.setLayoutData(formDataLabelDesistimientoUnilateralMeses);
  
  // Campo de Texto "Desistimiento unilateral - Días"
  this.textDesistimientoUnilateralDias = ComponentsFactory.newTextBigDecimal(
   compositeRowSeventhGroupDuracionContrato, verifyBigDecimalListener);
  final FormData formDataTextDesistimientoUnilateralDias = new FormData();
  formDataTextDesistimientoUnilateralDias.top
   = new FormAttachment(50, -this.textDesistimientoUnilateralDias.computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataTextDesistimientoUnilateralDias.left = new FormAttachment(labelDesistimientoUnilateralMeses, 10);
  formDataTextDesistimientoUnilateralDias.width = 20;
  this.textDesistimientoUnilateralDias.setLayoutData(formDataTextDesistimientoUnilateralDias);
  this.textDesistimientoUnilateralDias.addFocusListener(
   new FocusListener() {
    @Override
    public void focusGained(final FocusEvent focusEvent) {
     final Text text  = (Text) focusEvent.widget;
     text.selectAll();
    }
    @Override
    public void focusLost(final FocusEvent focusEvent) {}
   }
  );
  this.textDesistimientoUnilateralDias.addMouseListener(
   new MouseListener() {
    @Override
    public void mouseDoubleClick(final MouseEvent mouseEvent) {}
    @Override
    public void mouseDown(final MouseEvent mouseEvent) {
     final Text text = (Text) mouseEvent.widget;
     text.selectAll();
    }
    @Override
    public void mouseUp(final MouseEvent mouseEvent) {}
   }
  );

  // Etiqueta "Desistimiento unilateral - Días"
  final Label labelDesistimientoUnilateralDias = ComponentsFactory.newLabel(
    compositeRowSeventhGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionDias);
  final FormData formDataLabelDesistimientoUnilateralDias = new FormData();
  formDataLabelDesistimientoUnilateralDias.top
   = new FormAttachment(50, -labelDesistimientoUnilateralDias .computeSize(SWT.DEFAULT, SWT.DEFAULT).y / 2);
  formDataLabelDesistimientoUnilateralDias.left = new FormAttachment(this.textDesistimientoUnilateralDias, 5);
  formDataLabelDesistimientoUnilateralDias.width
   = labelDesistimientoUnilateralDias.computeSize(SWT.DEFAULT, SWT.DEFAULT).x; 
  labelDesistimientoUnilateralDias.setLayoutData(formDataLabelDesistimientoUnilateralDias);
  
  
  
  // Octava Fila del Grupo de componentes correspondientes a 'Duración'.
  final Composite compositeRowEighthGroupDuracionContrato
   = ComponentsFactory.newComposite(groupDuracionContrato);
  compositeRowEighthGroupDuracionContrato.setLayout(new FormLayout());
  //compositeRowEighthGroupDuracionContrato.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
  final FormData formDataCompositeRowEighthGroupDuracionContrato = new FormData();
  formDataCompositeRowEighthGroupDuracionContrato.top = new FormAttachment(compositeRowSeventhGroupDuracionContrato, 5);
  formDataCompositeRowEighthGroupDuracionContrato.left = new FormAttachment(0, 0);
  formDataCompositeRowEighthGroupDuracionContrato.right = new FormAttachment(100, 0);
  formDataCompositeRowEighthGroupDuracionContrato.bottom = new FormAttachment(100, 0);
  compositeRowEighthGroupDuracionContrato.setLayoutData(formDataCompositeRowEighthGroupDuracionContrato);
  
  // Etiqueta "Observaciones"
  final Label labelObservaciones = ComponentsFactory.newLabel(
   compositeRowEighthGroupDuracionContrato, SWT.LEFT,
   Messages.ContratosAltaPestanaFechasAlertaDuracionObservaciones
   + Messages.Marca_FinCampo);
  final FormData formDataLabelObservaciones = new FormData();
  formDataLabelObservaciones.top
   = new FormAttachment(0, 0);
  formDataLabelObservaciones.left = new FormAttachment(0, 10);
  formDataLabelObservaciones.width = 80;
  /* = labelObservaciones.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;*/ 
  labelObservaciones.setLayoutData(formDataLabelObservaciones);
  
  // Campo de Texto "Observaciones"
  this.textObservaciones = ComponentsFactory.newText(
   compositeRowEighthGroupDuracionContrato,
   SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
  this.textObservaciones.setTextLimit(200);
  final FormData formDataTextObservaciones = new FormData();
  formDataTextObservaciones.top = new FormAttachment(0);
  formDataTextObservaciones.left = new FormAttachment(labelObservaciones, 10);
  formDataTextObservaciones.right = new FormAttachment(100, -10);
  formDataTextObservaciones.bottom = new FormAttachment(100, -10);
  formDataTextObservaciones.height = 60;
  this.textObservaciones.setLayoutData(formDataTextObservaciones);
  
  
  
  
  // Obtenemos el tamaño que tiene el texto de la etiqueta. 
  /*Point size = label.computeSize(SWT.DEFAULT, SWT.DEFAULT);  
  final FormData labelData = new FormData (size.x / 2, SWT.DEFAULT);  
  labelData.left = new FormAttachment(0, 0); // Por la izquierda ocupamos un 0% sin margen.
  labelData.right = new FormAttachment(100, 0); // Por la derecha ocupamos un 100% sin margen.
  labelData.top = new FormAttachment(groupDuracionContrato, insetY);
  labelData.width = size.x;
  label.setLayoutData (labelData);
  
  FormData listData = new FormData();
  listData.left = new FormAttachment(0, 0);
  listData.right = new FormAttachment(100, 0);
  listData.top = new FormAttachment(label, insetY);
  listData.bottom = new FormAttachment(button2, -insetY);
  list.setLayoutData (listData);
  
  FormData button2Data = new FormData();
  button2Data.right = new FormAttachment(100, 0);
  button2Data.bottom = new FormAttachment(100, 0);
  button2Data.width = 50;
  button2.setLayoutData (button2Data);

  FormData button1Data = new FormData();
  button1Data.right = new FormAttachment(button2, -insetX);
  button1Data.bottom = new FormAttachment(100, 0);
  button1Data.width = 50;
  button1.setLayoutData (button1Data);*/



 shell.pack ();
 shell.open ();
 while (!shell.isDisposed ()) {
  if (!display.readAndDispatch ()) display.sleep ();
 }
 display.dispose ();
}

  protected void createFirstGroup() {
   
  }

  
  protected void createSecondGroup() {
   
  }  
  
  public static void main (String [] args) {
   new Snippet65().createContents();
  }
}
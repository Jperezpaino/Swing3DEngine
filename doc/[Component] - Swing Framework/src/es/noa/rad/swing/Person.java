package es.noa.rad.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;

public class Person extends JPanel {

 private BindingGroup m_bindingGroup;
 private es.noa.rad.data.dto.PersonDTO personDTO = new es.noa.rad.data.dto.PersonDTO();
 private JTextField firstSurnameJTextField;
 private JTextField nameJTextField;
 private JTextField secondSurnameJTextField;

 public Person(es.noa.rad.data.dto.PersonDTO newPersonDTO) {
  this();
  setPersonDTO(newPersonDTO);
 }

 public Person() {
  GridBagLayout gridBagLayout = new GridBagLayout();
  gridBagLayout.columnWidths = new int[] { 120, 220, 0 };
  gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
  gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0E-4 };
  gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0E-4 };
  setLayout(gridBagLayout);

  JLabel firstSurnameLabel = new JLabel("FirstSurname:");
  GridBagConstraints labelGbc_0 = new GridBagConstraints();
  labelGbc_0.anchor = GridBagConstraints.WEST;
  labelGbc_0.insets = new Insets(10, 10, 10, 10);
  labelGbc_0.gridx = 0;
  labelGbc_0.gridy = 0;
  add(firstSurnameLabel, labelGbc_0);

  firstSurnameJTextField = new JTextField();
  GridBagConstraints componentGbc_0 = new GridBagConstraints();
  componentGbc_0.insets = new Insets(10, 10, 10, 10);
  componentGbc_0.fill = GridBagConstraints.HORIZONTAL;
  componentGbc_0.gridx = 1;
  componentGbc_0.gridy = 0;
  add(firstSurnameJTextField, componentGbc_0);

  JLabel nameLabel = new JLabel("Name:");
  GridBagConstraints labelGbc_1 = new GridBagConstraints();
  labelGbc_1.anchor = GridBagConstraints.WEST;
  labelGbc_1.insets = new Insets(10, 10, 10, 10);
  labelGbc_1.gridx = 0;
  labelGbc_1.gridy = 1;
  add(nameLabel, labelGbc_1);

  nameJTextField = new JTextField();
  GridBagConstraints componentGbc_1 = new GridBagConstraints();
  componentGbc_1.insets = new Insets(10, 10, 10, 10);
  componentGbc_1.fill = GridBagConstraints.HORIZONTAL;
  componentGbc_1.gridx = 1;
  componentGbc_1.gridy = 1;
  add(nameJTextField, componentGbc_1);

  JLabel secondSurnameLabel = new JLabel("SecondSurname:");
  GridBagConstraints labelGbc_2 = new GridBagConstraints();
  labelGbc_2.anchor = GridBagConstraints.WEST;
  labelGbc_2.insets = new Insets(10, 10, 10, 10);
  labelGbc_2.gridx = 0;
  labelGbc_2.gridy = 2;
  add(secondSurnameLabel, labelGbc_2);

  secondSurnameJTextField = new JTextField();
  GridBagConstraints componentGbc_2 = new GridBagConstraints();
  componentGbc_2.insets = new Insets(10, 10, 10, 10);
  componentGbc_2.fill = GridBagConstraints.HORIZONTAL;
  componentGbc_2.gridx = 1;
  componentGbc_2.gridy = 2;
  add(secondSurnameJTextField, componentGbc_2);

  if(personDTO != null) {
   m_bindingGroup = initDataBindings();
  }
 }

  protected BindingGroup initDataBindings() {
   BeanProperty<es.noa.rad.data.dto.PersonDTO, java.lang.String> nameProperty = BeanProperty.create("name");   
   BeanProperty<javax.swing.JTextField, java.lang.String> nameTextProperty = BeanProperty.create("text");
   AutoBinding<es.noa.rad.data.dto.PersonDTO, java.lang.String, javax.swing.JTextField, java.lang.String> nameAutoBinding
    = Bindings.createAutoBinding(
     AutoBinding.UpdateStrategy.READ_WRITE, personDTO, nameProperty, nameJTextField, nameTextProperty);
   nameAutoBinding.bind();

   BeanProperty<es.noa.rad.data.dto.PersonDTO, java.lang.String> firstSurnameProperty = BeanProperty.create("firstSurname");   
   BeanProperty<javax.swing.JTextField, java.lang.String> firstSurnameTextProperty = BeanProperty.create("text");
   
   AutoBinding< es.noa.rad.data.dto.PersonDTO, java.lang.String, javax.swing.JTextField, java.lang.String> firstSurnameAutoBinding
    = Bindings.createAutoBinding(
     AutoBinding.UpdateStrategy.READ_WRITE, personDTO, firstSurnameProperty, firstSurnameJTextField, firstSurnameTextProperty);
   firstSurnameAutoBinding.bind();
  
   BeanProperty<es.noa.rad.data.dto.PersonDTO, java.lang.String> secondSurnameProperty = BeanProperty.create("secondSurname");   
   BeanProperty<javax.swing.JTextField, java.lang.String> secondSurnameTextProperty = BeanProperty.create("text");
   
   AutoBinding<es.noa.rad.data.dto.PersonDTO, java.lang.String, javax.swing.JTextField, java.lang.String> secondSurnameAutoBinding
    = Bindings.createAutoBinding(
     AutoBinding.UpdateStrategy.READ_WRITE, personDTO, secondSurnameProperty, secondSurnameJTextField, secondSurnameTextProperty);
   secondSurnameAutoBinding.bind();
   
   BindingGroup bindingGroup = new BindingGroup();
   bindingGroup.addBinding(nameAutoBinding);
   bindingGroup.addBinding(firstSurnameAutoBinding);
   bindingGroup.addBinding(secondSurnameAutoBinding);
   
   return bindingGroup;
  }

  public es.noa.rad.data.dto.PersonDTO getPersonDTO() {
   return personDTO;
  }

  public void setPersonDTO(es.noa.rad.data.dto.PersonDTO newPersonDTO) {
   setPersonDTO(newPersonDTO, true);
  }

  public void setPersonDTO(es.noa.rad.data.dto.PersonDTO newPersonDTO, boolean update) {
   this.personDTO = newPersonDTO;
   if(update) {
    if(m_bindingGroup != null) {
     m_bindingGroup.unbind();
     m_bindingGroup = null;
    }
    if(personDTO != null) {
     m_bindingGroup = initDataBindings();
    }
   }
  }
 
}

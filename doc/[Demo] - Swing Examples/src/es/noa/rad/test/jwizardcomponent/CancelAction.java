package es.noa.rad.test.jwizardcomponent;


 public abstract class CancelAction implements Action {
  JWizardComponents wizardComponents;

  public CancelAction(JWizardComponents wizardComponents) {
    this.wizardComponents = wizardComponents;
  }

 }
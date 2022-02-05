package at.fhv;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class CalculateRemunerationDelegate implements JavaDelegate {
    private Double remunerationAmount = 0.0;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Double damageAmount = (Double) execution.getVariable("damageAmount");
        String damageType = (String) execution.getVariable("damageType");

        CalculateRemuneration calculator = new CalculateRemuneration(damageType, damageAmount);
        remunerationAmount = calculator.calculateRemuneration();

        execution.setVariable("remunerationAmount", remunerationAmount);
    }


}

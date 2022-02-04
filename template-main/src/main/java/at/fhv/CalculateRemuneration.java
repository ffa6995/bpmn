package at.fhv;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.FileValue;

import javax.inject.Named;

@Named
public class CalculateRemuneration implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Double damageAmount = (Double) execution.getVariable("damageAmount");
        String damageType = (String) execution.getVariable("damageType");

        Double remunerationAmount = 0.0;
        Double fixedRate = 0.0;

        switch (damageType) {
            case "carScratch":
                fixedRate = 0.95;
                remunerationAmount = damageAmount * fixedRate;
                break;
            case "stormDamage":
                fixedRate = 0.92;
                remunerationAmount = damageAmount * fixedRate;
                break;
            case "gearboxDamage":
                fixedRate = 0.87;
                remunerationAmount = damageAmount * fixedRate;
                break;
            case "totalDamage":
                fixedRate = 0.80;
                remunerationAmount = damageAmount * fixedRate;
                break;
            case "multipleCollision":
                fixedRate = 0.75;
                remunerationAmount = damageAmount * fixedRate;
                break;
            default:
                break;

        }
        execution.setVariable("remunerationAmount", remunerationAmount);
    }
}

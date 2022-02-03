package at.fhv;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import javax.inject.Named;

@Named
public class CreateRemuneration implements JavaDelegate {

    public int remunerationNr = 1;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

    }
}

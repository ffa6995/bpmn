package at.fhv;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.FileValue;

import javax.inject.Named;
import java.util.logging.Logger;

@Named
public class ArchiveRemuneration implements JavaDelegate {
    private final Logger LOGGER = Logger.getLogger(ArchiveRemuneration.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {

//        Boolean shouldFail = (Boolean) execution.getVariable("shouldFail");
        FileValue invoiceDocumentVar  = execution.getVariableTyped("damageReport");

        System.out.println(invoiceDocumentVar.getFilename());
//
//        if(shouldFail != null && shouldFail) {
//            throw new ProcessEngineException("Could not archive invoice...");
//        }
//        else {
//            LOGGER.info("\n\n  ... Now archiving invoice "+execution.getVariable("invoiceNumber")
//                    +", filename: "+invoiceDocumentVar.getFilename()+" \n\n");
//        }
    }
}

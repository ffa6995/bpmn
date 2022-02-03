package at.fhv;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import javax.inject.Named;

@Named
public class CreateRemuneration implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        // read camunda variables
        String customer = (String) execution.getVariable("customer");
        String damageType = (String) execution.getVariable("damageType");
        Integer damageAmount = (Integer) execution.getVariable("damageAmount");
        boolean approved = (Boolean) execution.getVariable("approved");

        if (approved) {
            Path currentRelativePath = Paths.get("");
            String stringPath = currentRelativePath.toRealPath().toString();
            stringPath = stringPath.substring(0, stringPath.lastIndexOf("\\") + 1);

            String documentName = customer + "-remuneration" + ".pdf";
            String pdfDocPath = stringPath + "\\template-main\\remunerations\\" + documentName;

            boolean checkIfExist = new File(pdfDocPath).exists();

            if (checkIfExist) {
                documentName = documentName.replaceFirst("[.][^.]+$", "") + "-1.pdf";
                pdfDocPath = pdfDocPath.replaceFirst("[.][^.]+$", "") + "-1.pdf";
            }

            // set variable to camunda cockpit
            execution.setVariable("pdfDocPath", pdfDocPath);
            execution.setVariable("documentName", documentName);

            CreatePDFDocument.createFile(pdfDocPath, customer, damageType, damageAmount);

            System.out.println(pdfDocPath);
        }
    }
}

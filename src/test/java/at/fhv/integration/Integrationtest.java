package at.fhv.integration;

import at.fhv.CalculateRemuneration;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.scenario.ProcessScenario;
import org.camunda.bpm.scenario.Scenario;
import org.camunda.bpm.scenario.delegate.MockedCallActivityDelegate;
import org.camunda.bpm.scenario.delegate.TaskDelegate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.withVariables;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Integrationtest {
    @Autowired
    RuntimeService runtimeService;

    private static final String PROCESSID = "NoticeOfDamage";
    private static final String PROCESSID2 = "HandleCase";
    private CalculateRemuneration calculator = mock(CalculateRemuneration.class);

    private ProcessScenario insuranceScenario = mock(ProcessScenario.class);
    private ProcessScenario handleCaseScenario = mock(ProcessScenario.class);

    @Test
    public void NoticeOfDamageNotApproved() {
        when(insuranceScenario.waitsAtUserTask("recordDamageCase")).thenReturn(task ->
                task.complete(withVariables("name", "Fabian", "damageType", "Car Scratch", "damageAmount", 5000)));

        when(insuranceScenario.waitsAtUserTask("examineDamage")).thenReturn(task ->
                task.complete(withVariables("approver", "Superior")));

        when(insuranceScenario.waitsAtUserTask("approveCase")).thenReturn(task ->
                task.complete(withVariables("approved", false)));

        when(insuranceScenario.waitsAtUserTask("archiveCase")).thenReturn(TaskDelegate::complete);

        when(insuranceScenario.waitsAtUserTask("sendRefusal")).thenReturn(TaskDelegate::complete);

        Scenario scenario = Scenario.run(insuranceScenario)
                .startBy(() -> runtimeService.startProcessInstanceByKey(PROCESSID,
                        withVariables("customer", "Fabian", "damageType", "Car Scratch", "damageAmount", 5000)))
                .execute();

        verify(insuranceScenario).hasStarted("StartEvent_1");
        verify(insuranceScenario, never()).hasStarted("handleCase");
        verify(insuranceScenario, never()).hasStarted("sendConfirmation");
        verify(insuranceScenario).hasCompleted("recordDamageCase");
        verify(insuranceScenario).hasCompleted("examineDamage");
        verify(insuranceScenario).hasCompleted("approveCase");
        verify(insuranceScenario).hasCompleted("archiveCase");
        verify(insuranceScenario).hasCompleted("sendRefusal");
        verify(insuranceScenario).hasFinished("Event_0x5woi7");
    }

    @Test
    public void NoticeOfDamageApproved() {
        when(insuranceScenario.waitsAtUserTask("recordDamageCase")).thenReturn(task ->
                task.complete(withVariables("name", "Yannick", "damageType", "Storm Damage", "damageAmount", 500.0)));
        when(insuranceScenario.waitsAtUserTask("examineDamage")).thenReturn(task ->
                task.complete(withVariables("approver", "Assesor")));
        when(insuranceScenario.waitsAtUserTask("approveCase")).thenReturn(task -> task.complete(withVariables("approved", true)));
        when(insuranceScenario.runsCallActivity("handleCase")).thenReturn(Scenario.use(handleCaseScenario));
        when(handleCaseScenario.waitsAtUserTask("calculateRemuneration")).thenReturn(task -> task.complete(withVariables("remunerationAmount", 460.0)));
        when(handleCaseScenario.waitsAtUserTask("createRemuneration")).thenReturn(TaskDelegate::complete);
        when(handleCaseScenario.waitsAtUserTask("signRemuneration")).thenReturn(TaskDelegate::complete);
        when(handleCaseScenario.waitsAtUserTask("prepareBankTransfer")).thenReturn(TaskDelegate::complete);
        when(insuranceScenario.waitsAtUserTask("sendConfirmation")).thenReturn(TaskDelegate::complete);

        Scenario scenario = Scenario.run(insuranceScenario)
                .startBy(() -> runtimeService.startProcessInstanceByKey(PROCESSID,
                        withVariables("customer", "Yannick", "damageType",
                                "Storm Damage", "damageAmount", 500.0)))
                .execute();

        verify(insuranceScenario).hasStarted("StartEvent_1");
        verify(insuranceScenario).hasCompleted("recordDamageCase");
        verify(insuranceScenario).hasCompleted("examineDamage");
        verify(insuranceScenario).hasCompleted("approveCase");
        verify(insuranceScenario).hasCompleted("handleCase");
        verify(handleCaseScenario).hasCompleted("calculateRemuneration");
        verify(handleCaseScenario).hasCompleted("createRemuneration");
        verify(handleCaseScenario).hasCompleted("signRemuneration");
        verify(handleCaseScenario).hasCompleted("prepareBankTransfer");
        verify(insuranceScenario, never()).hasStarted("archiveCase");
        verify(insuranceScenario, never()).hasStarted("sendRefusal");
        verify(insuranceScenario).hasFinished("Event_0j75mh6");
    }

    @Test
    public void HandleCase() {
        when(handleCaseScenario.waitsAtUserTask("calculateRemuneration")).thenReturn(task -> task.complete(withVariables("remunerationAmount", 460.0)));
        when(handleCaseScenario.waitsAtUserTask("createRemuneration")).thenReturn(TaskDelegate::complete);
        when(handleCaseScenario.waitsAtUserTask("signRemuneration")).thenReturn(TaskDelegate::complete);
        when(handleCaseScenario.waitsAtUserTask("prepareBankTransfer")).thenReturn(TaskDelegate::complete);

        Scenario scenario = Scenario.run(handleCaseScenario)
                .startBy(() -> runtimeService.startProcessInstanceByKey(PROCESSID2,
                        withVariables("customer", "Frederick",
                                "damageType", "Storm Damage",
                                "damageAmount", 500.0, "approved", true,
                                "approver", "Assesor")))
                .execute();

        verify(handleCaseScenario).hasCompleted("calculateRemuneration");
        verify(handleCaseScenario).hasCompleted("createRemuneration");
        verify(handleCaseScenario).hasCompleted("signRemuneration");
        verify(handleCaseScenario).hasCompleted("prepareBankTransfer");
        verify(handleCaseScenario).hasFinished("Event_1yi1hum");
    }
}

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Integrationtest {

//    private static final String PROCESS_ID = "bookstore_order";
//
//    @Autowired
//    RuntimeService runtimeService;
//
//    @Autowired
//    TaskService taskService;
//
//    private CalculateRemunerationDelegate calculateRemuerationScenario = mock(CalculateRemunerationDelegate.class);
//    private CreateRemuneration createRemunerationScenario = mock(CreateRemuneration.class);
//    private CalculateRemuneration calculator = mock(CalculateRemuneration.class);
//
//    @Test
//    public void testInStockPath() {
//        when(warehouse.inStock("hp7", 1)).thenReturn(true);
//        when(bookstoreOrderScenario.waitsAtUserTask("check_money_transfer_user")).thenReturn(TaskDelegate::complete);
//
//        Scenario scenario = Scenario.run(bookstoreOrderScenario)
//                .startBy(() -> runtimeService.startProcessInstanceByKey(PROCESS_ID,
//                        withVariables("book_name", "hp7", "amount", 1L)))
//                .execute();
//        verify(bookstoreOrderScenario).hasStarted("order_received_event");
//        verify(bookstoreOrderScenario).hasFinished("check_book_availability_service");
//        verify(bookstoreOrderScenario, never()).hasStarted("request_delivery_time_user");
//        verify(bookstoreOrderScenario).hasCompleted("send_book_service");
//        verify(bookstoreOrderScenario).hasCompleted("inform_customer_about_delivery_confirmation_service");
//        verify(bookstoreOrderScenario).hasFinished("customer_received_book_end_event");
//    }
//
//    @Test
//    public void testNotInStockOrderCancelledPath() {
//        when(warehouse.inStock("hp7", 3)).thenReturn(false);
//        when(bookstoreOrderScenario.waitsAtUserTask("request_delivery_time_user"))
//                .thenReturn(task -> task.complete(withVariables("delay_in_days", 8L)));
//        when(bookstoreOrderScenario.runsCallActivity("ask_customer_for_approval_subprocess"))
//                .thenReturn(Scenario.use(askCustomerApprovalScenario));
//        when(askCustomerApprovalScenario.waitsAtUserTask("answer_from_customer_user"))
//                .thenReturn(task -> task.complete(withVariables("approval", false)));
//
//        Scenario scenario = Scenario.run(bookstoreOrderScenario)
//                .startBy(() -> runtimeService.startProcessInstanceByKey(PROCESS_ID,
//                        withVariables("book_name", "hp7", "amount", 3L)))
//                .execute();
//        verify(bookstoreOrderScenario).hasStarted("order_received_event");
//        verify(bookstoreOrderScenario).hasFinished("check_book_availability_service");
//        verify(bookstoreOrderScenario).hasCompleted("make_order_decision_decision_matrix");
//        verify(bookstoreOrderScenario, never()).hasStarted("order_from_supplier_user");
//        verify(bookstoreOrderScenario, never()).hasStarted("check_money_transfer_user");
//        verify(askCustomerApprovalScenario).hasCompleted("request_to_proceed_order_despite_delay_service");
//        verify(bookstoreOrderScenario).hasFinished("order_cancelled_end_event");
//        verify(bookstoreOrderScenario, never()).hasFinished("customer_received_book_end_event");
//    }
//
//    @Test
//    public void testNotInStockOrderApprovedByCustomerPath() {
//        when(warehouse.inStock("hp7", 3)).thenReturn(false);
//        when(bookstoreOrderScenario.waitsAtUserTask("request_delivery_time_user"))
//                .thenReturn(task -> task.complete(withVariables("delay_in_days", 8L)));
//        when(bookstoreOrderScenario.runsCallActivity("ask_customer_for_approval_subprocess"))
//                .thenReturn(Scenario.use(askCustomerApprovalScenario));
//        when(askCustomerApprovalScenario.waitsAtUserTask("answer_from_customer_user"))
//                .thenReturn(task -> task.complete(withVariables("approval", true)));
//        when(bookstoreOrderScenario.waitsAtUserTask("order_from_supplier_user")).thenReturn(TaskDelegate::complete);
//        when(bookstoreOrderScenario.waitsAtUserTask("confirm_order_arrival_warehouse_user")).thenReturn(TaskDelegate::complete);
//        when(bookstoreOrderScenario.waitsAtUserTask("check_money_transfer_user")).thenReturn(TaskDelegate::complete);
//
//        Scenario scenario = Scenario.run(bookstoreOrderScenario)
//                .startBy(() -> runtimeService.startProcessInstanceByKey(PROCESS_ID,
//                        withVariables("book_name", "hp7", "amount", 3L)))
//                .execute();
//        verify(bookstoreOrderScenario).hasStarted("order_received_event");
//        verify(bookstoreOrderScenario).hasFinished("check_book_availability_service");
//        verify(bookstoreOrderScenario).hasCompleted("make_order_decision_decision_matrix");
//        verify(askCustomerApprovalScenario).hasCompleted("request_to_proceed_order_despite_delay_service");
//        verify(bookstoreOrderScenario, never()).hasStarted("order_cancelled_end_event");
//        verify(bookstoreOrderScenario).hasCompleted("order_from_supplier_user");
//        verify(bookstoreOrderScenario).hasCompleted("inform_customer_about_estimated_delivery_service");
//        verify(bookstoreOrderScenario).hasCompleted("confirm_order_arrival_warehouse_user");
//        verify(bookstoreOrderScenario).hasCompleted("store_book_at_warehouse_service");
//        verify(bookstoreOrderScenario).hasCompleted("check_money_transfer_user");
//        verify(bookstoreOrderScenario).hasCompleted("inform_customer_about_delivery_confirmation_service");
//        verify(bookstoreOrderScenario).hasCompleted("send_book_service");
//        verify(bookstoreOrderScenario).hasFinished("customer_received_book_end_event");
//    }
//
//    @Test
//    public void testNotInStockOrderPlacedWithoutApprovalPath() {
//        when(warehouse.inStock("hp7", 3)).thenReturn(false);
//        when(bookstoreOrderScenario.waitsAtUserTask("request_delivery_time_user"))
//                .thenReturn(task -> task.complete(withVariables("delay_in_days", 4L)));
//        when(bookstoreOrderScenario.waitsAtUserTask("order_from_supplier_user")).thenReturn(TaskDelegate::complete);
//        when(bookstoreOrderScenario.waitsAtUserTask("confirm_order_arrival_warehouse_user")).thenReturn(TaskDelegate::complete);
//        when(bookstoreOrderScenario.waitsAtUserTask("check_money_transfer_user")).thenReturn(TaskDelegate::complete);
//
//        Scenario scenario = Scenario.run(bookstoreOrderScenario)
//                .startBy(() -> runtimeService.startProcessInstanceByKey(PROCESS_ID,
//                        withVariables("book_name", "hp7", "amount", 3L)))
//                .execute();
//        verify(bookstoreOrderScenario).hasStarted("order_received_event");
//        verify(bookstoreOrderScenario).hasFinished("check_book_availability_service");
//        verify(bookstoreOrderScenario).hasCompleted("make_order_decision_decision_matrix");
//        verify(bookstoreOrderScenario, never()).hasStarted("ask_customer_for_approval_subprocess");
//        verify(bookstoreOrderScenario, never()).hasStarted("order_cancelled_end_event");
//        verify(bookstoreOrderScenario).hasCompleted("order_from_supplier_user");
//        verify(bookstoreOrderScenario).hasCompleted("inform_customer_about_estimated_delivery_service");
//        verify(bookstoreOrderScenario).hasCompleted("confirm_order_arrival_warehouse_user");
//        verify(bookstoreOrderScenario).hasCompleted("store_book_at_warehouse_service");
//        verify(bookstoreOrderScenario).hasCompleted("check_money_transfer_user");
//        verify(bookstoreOrderScenario).hasCompleted("inform_customer_about_delivery_confirmation_service");
//        verify(bookstoreOrderScenario).hasCompleted("send_book_service");
//        verify(bookstoreOrderScenario).hasFinished("customer_received_book_end_event");
//    }
}

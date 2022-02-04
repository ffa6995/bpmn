package at.fhv.cucumber;

import at.fhv.CalculateRemuneration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;
import static org.junit.Assert.*;

@CucumberContextConfiguration
@ContextConfiguration
public class StepDefinition {
    CalculateRemuneration calculator = new CalculateRemuneration("carScratch", 500);

    @Given("Damage type is carScratch")
    public void damage_type_is_car_scratch() {
        // Write code here that turns the phrase above into concrete actions
        String damageType = calculator.getDamageType();
        assert (damageType.equals("carScratch"));
    }

    @When("Damage Amount is {double}")
    public void damage_amount_is(Double amount) {
        // Write code here that turns the phrase above into concrete actions
        Double damageAmount = calculator.getDamageAmount();
        assertEquals(damageAmount, amount);
    }
    @Then("Remuneration Amount is {double}")
    public void remuneration_amount_is(Double amount) {
        // Write code here that turns the phrase above into concrete actions
        Double remunerationAmount = calculator.calculateRemuneration();
        assertEquals(remunerationAmount, amount);
    }


}

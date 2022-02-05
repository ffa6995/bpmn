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
        String damageType = calculator.getDamageType();
        assertEquals(damageType, "carScratch");
    }

    @When("Damage Amount is {double}")
    public void damage_amount_is(Double amount) {
        Double damageAmount = calculator.getDamageAmount();
        assertEquals(damageAmount, amount);
    }

    @Then("Remuneration Amount is {double}")
    public void remuneration_amount_is(Double amount) {
        Double remunerationAmount = calculator.calculateRemuneration();
        assertEquals(remunerationAmount, amount);
    }

    CalculateRemuneration calculator2 = new CalculateRemuneration("totalDamage", 8000);
    Double remuneration2 = calculator2.calculateRemuneration();

    @Given("Damage type is totalDamage")
    public void damage_type_is_total_damage() {
        String damageType = calculator2.getDamageType();
        assertEquals(damageType, "totalDamage");
    }

    @When("we have a damage Amount of {double}")
    public void damage_amount_is2(Double amount) {
        Double damageAmount = calculator2.getDamageAmount();
        assertEquals(damageAmount, amount);
    }

    @Then("fixedRate is {double}")
    public void fixed_rate_is(Double amount) {
        Double fixedRate = calculator2.getFixedRate();
        assertEquals(fixedRate, amount);
    }

    CalculateRemuneration calculator3 = new CalculateRemuneration("gearboxDamage", 0);
    Double remuneration3 = calculator3.calculateRemuneration();

    @Given("Damage amount is {double}")
    public void damage_amount_is3(Double amount) {
        Double damageAmount = calculator3.getDamageAmount();
        assertEquals(damageAmount, damageAmount);
    }

    @When("there is damage type of gearboxDamage")
    public void there_is_damage_type_of_gearbox_damage() {
        String damageType = calculator3.getDamageType();
        assertEquals(damageType, "gearboxDamage");
    }

    @Then("remuneration amount is {double}")
    public void remuneration_amount_is2(Double zero) {
        Double remunerationAmount = calculator3.getRemunerationAmount();
        assertEquals(remunerationAmount, zero);
    }


}

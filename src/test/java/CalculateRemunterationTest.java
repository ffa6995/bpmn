
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import at.fhv.CalculateRemuneration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class CalculateRemunterationTest {

    @Test
    @DisplayName("fixed Rate should be 0.92 when damageType is stormDamage")
    void testStormDamage() {
        CalculateRemuneration calculator = new CalculateRemuneration("stormDamage", 500);
        calculator.calculateRemuneration();
        Double fixedRate = calculator.getFixedRate();
        assertEquals(0.92, fixedRate);
    }

    @Test
    @DisplayName("fixed Rate should be 0.75 when damageType is multipleCollision")
    void testMultipleCollision() {
        CalculateRemuneration calculator = new CalculateRemuneration("multipleCollision", 15000);
        calculator.calculateRemuneration();
        Double fixedRate = calculator.getFixedRate();
        assertEquals(0.75, fixedRate);
    }

    @Test
    @DisplayName("remuneration should be calculated with 87% when getting gearboxDamage")
    void testGearBoxDamage() {
        CalculateRemuneration calculator = new CalculateRemuneration("gearboxDamage", 3000);
        calculator.calculateRemuneration();
        Double fixedRate = calculator.getFixedRate();
        Double expectedAmount = 3000 * 0.87;
        Double remunerationAmount = calculator.getRemunerationAmount();
        assertEquals(0.87, fixedRate);
        assertEquals(expectedAmount, remunerationAmount);
    }

    @Test
    @DisplayName("remuneration amount should be 95% of the whole damageAmount when having a car Scratch")
    void testRemunerationAmount() {
        CalculateRemuneration calculator = new CalculateRemuneration("carScratch", 500);
        calculator.calculateRemuneration();
        Double expectedAmount = 500 * 0.95;
        Double remunerationAmount = calculator.getRemunerationAmount();
        assertEquals(expectedAmount, remunerationAmount);
    }

    @Test
    @DisplayName("remuneration amount should be 0 if there is no damageType given")
    void testIfDamageTypeEmpty() {
        CalculateRemuneration calculator = new CalculateRemuneration("", 333);
        calculator.calculateRemuneration();
        Double remunerationAmount = calculator.getRemunerationAmount();
        assertEquals(0, remunerationAmount);
    }

}

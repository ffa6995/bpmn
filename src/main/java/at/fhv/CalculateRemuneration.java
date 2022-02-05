package at.fhv;

public class CalculateRemuneration {
    private String damageType = "";
    private Double damageAmount = 0.0;
    private Double remunerationAmount = 0.0;
    private Double fixedRate = 0.0;
    public CalculateRemuneration(String damageType, double damageAmount) {
        this.damageType = damageType;
        this.damageAmount = damageAmount;
    }

    public double calculateRemuneration() {
        switch (this.damageType) {
            case "carScratch":
                this.fixedRate = 0.95;
                this.remunerationAmount = this.damageAmount * this.fixedRate;
                break;
            case "stormDamage":
                this.fixedRate = 0.92;
                this.remunerationAmount = this.damageAmount * this.fixedRate;
                break;
            case "gearboxDamage":
                this.fixedRate = 0.87;
                this.remunerationAmount = this.damageAmount * this.fixedRate;
                break;
            case "totalDamage":
                this.fixedRate = 0.80;
                this.remunerationAmount = this.damageAmount * this.fixedRate;
                break;
            case "multipleCollision":
                this.fixedRate = 0.75;
                this.remunerationAmount = this.damageAmount * this.fixedRate;
                break;
            default:
                break;

        }
        return this.remunerationAmount;
    }

    public String getDamageType() {
        return damageType;
    }

    public Double getDamageAmount() {
        return damageAmount;
    }

    public Double getRemunerationAmount() {
        return remunerationAmount;
    }

    public Double getFixedRate() {
        return fixedRate;
    }
}

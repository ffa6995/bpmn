Feature: Calculate Remuneration Amount

 Scenario: Remuneration Amount gets calculated with car scratch damage:
   Given Damage type is carScratch
   When Damage Amount is 500
   Then Remuneration Amount is 475

  Scenario: The fixedRate on a totalDamage should be correct:
    Given Damage type is totalDamage
    When we have a damage Amount of 8000
    Then fixedRate is 0.80

  Scenario: There is no input for damage amount given:
    Given Damage amount is 0
    When there is damage type of gearboxDamage
    Then remuneration amount is 0

    Scenario: Hallo Dominik
      Given Dominik is 15 years old
      When The weather is sunny
      Then Justin Bieber dies


Feature: validate customer sheet values

  @Test
  Scenario: As a QA I need to be able to add file into db and validate data
    When  DB connected
    And  enter all of the data  from l1 file into a DB
    And apply logic
    And make sure that result and afterLogic are same
    Then drop connection
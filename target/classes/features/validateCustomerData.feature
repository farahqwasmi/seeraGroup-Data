Feature: validate customer sheet values

  @Farah
  Scenario: As a QA I need to be able to add file into db and validate data
    When  DB connected
    And  enter all of the data  from customer file into a DB
    And make sure that data is valid
    Then drop connection
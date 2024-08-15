Feature: Búsqueda de productos en Amazon

  #@ID_TEST_01 @PRIMEROS_CUATRO
  #Scenario: Buscar un producto por su nombre
  #  Given el usuario está en la página principal de Amazon
  #  When el usuario busca "laptop"
  #  Then los resultados de la búsqueda deben mostrar productos relacionados con "laptop"

  @ID_TEST_02 @PRIMEROS_CUATRO
  Scenario: Logueo test de prueba
    Given visualiza titulo login
    When ingreso usuario "student"
    Then ingreso clave "Password123"
    #Then los resultados de la búsqueda deben mostrar productos relacionados con "laptop"
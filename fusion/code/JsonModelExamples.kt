    
    //PERSON_START OMIT
    data class Person(
        val name: String, // HL
        val age: Int      // HL
    )
    //PERSON_END OMIT
    
    //START_NAME OMIT
    data class Name(
        val name: String
    )
    //END_NAME OMIT
    
@Test
fun `should override fields with given POJO`() {
    //START_OVR OMIT
    val basic = JsonModel.from(
        Person(
            name = "Peter Parker",
            age = 20
        )
    )

    val ageOverride = mapOf(
        "age" to 50
    )

    val result = basic with ageOverride  // HL

    assertThat(result.value).isEqualTo(
        Person(
            name = "Peter Parker",
            age = 50
        )
    )
    //END_OVR OMIT
}

@Test
fun `isComplete should be false when constructed using partial jsonText`() {
    //START_COMPOSE OMIT
    val partial = JsonModel.from<Person>(jsonText = "{\"age\": 20}") // HL

    assertThat(partial.isComplete).isFalse()

    val result = partial with Name("Bruce Wayne") // HL

    assertThat(result.value).isEqualTo(
        Person(
            name = "Bruce Wayne",
            age = 20
        )
    )
    //END_COMPOSE OMIT
}

fun jsonModelConstructionExample1() {
    //START_CONSTRUCT_JSON OMIT
    val model = JsonModel.from<Person>(
            jsonText = "{\"name\":\"Peter Parker\", \"age\":20}"
    )
    //END_CONSTRUCT_JSON OMIT
}
fun jsonModelConstructionExample2() {
    //START_CONSTRUCT_EMPTY OMIT
    val model = JsonModel.of<Person>()
    //END_CONSTRUCT_EMPTY OMIT
}

fun jsonModelConstructionExample3() {
    //START_CONSTRUCT_VALUE OMIT
    val model = JsonModel.from(
        value = Person(
            name = "Bruce Wayne",
            age = 40
        )
    )
    //END_CONSTRUCT_VALUE OMIT
}
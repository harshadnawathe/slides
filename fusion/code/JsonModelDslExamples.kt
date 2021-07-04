
//START_DOMAIN OMIT
data class CustomerName( val firstName: String, val lastName: String )
//END_DOMAIN OMIT

//START_TARGET OMIT
data class Application( val applicants: List<Applicant> ){
    data class Applicant(val name: String)
}
//END_TARGET OMIT

fun dslExample() {
//START_DSL OMIT

    val customer = CustomerName(firstName = "Clark", lastName = "Kent")

    val application = JsonModel.of<Application>{ // HL
        "applicants" having array[ // HL
            obj { // HL
                "name" having "${customer.firstName} ${customer.lastName}" // HL
            } // HL
        ] // HL
    } // HL

    assertThat(application.value).isEqualTo(
        Application(
            applicants= listOf(
                Applicant(name= "Clark Kent")
            )
        )
    )

//END_DSL OMIT
}
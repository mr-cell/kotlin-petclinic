package mr.cell.kotlinpetclinic

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ArchitectureTests {
    companion object {
        const val BASE_PACKAGE = "mr.cell.kotlinpetclinic"
        const val CORE_MODULE_PACKAGE = "$BASE_PACKAGE.core"
        const val HTTP_MODULE_PACKAGE = "$BASE_PACKAGE.http"
        const val DB_MODULE_PACKAGE = "$BASE_PACKAGE.db"
    }

    private lateinit var projectClasses: JavaClasses

    @BeforeEach
    fun beforeEach() {
        projectClasses = ClassFileImporter().importPackages(BASE_PACKAGE)
    }

    @Test
    fun shouldNotHaveAnyClassesInCoreModuleDependingOnOtherModules() {
        val rule = noClasses().that().resideInAPackage(packageMatcher(CORE_MODULE_PACKAGE))
            .should().accessClassesThat()
            .resideInAnyPackage(
                packageMatcher(DB_MODULE_PACKAGE),
                packageMatcher(HTTP_MODULE_PACKAGE)
            )

        rule.check(projectClasses)
    }

    @Test
    fun shouldNotHaveAnyClassesInDbModuleDependingOnHttpModule() {
        val rule = noClasses().that().resideInAPackage(packageMatcher(DB_MODULE_PACKAGE))
            .should().accessClassesThat()
            .resideInAPackage(packageMatcher(HTTP_MODULE_PACKAGE))

        rule.check(projectClasses)
    }

    @Test
    fun shouldNotHaveAnyClassesInHttpModuleDependingOnDbModule() {
        val rule = noClasses().that().resideInAPackage(packageMatcher(HTTP_MODULE_PACKAGE))
            .should().accessClassesThat()
            .resideInAPackage(packageMatcher(DB_MODULE_PACKAGE))

        rule.check(projectClasses)
    }

    private fun packageMatcher(fullyQualifiedPackage: String): String {
        return "$fullyQualifiedPackage.."
    }
}
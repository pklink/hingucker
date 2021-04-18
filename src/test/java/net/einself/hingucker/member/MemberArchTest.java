package net.einself.hingucker.member;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class MemberArchTest {

    private JavaClasses importedClasses;

    @BeforeEach
    void setUp() {
        importedClasses = new ClassFileImporter().importPackages("net.einself.hingucker");
    }

    @Test
    void classNamesWhichAreEndingWithMemberShouldImplementMemberInterface() {
        final var importedClasses = new ClassFileImporter().importPackages("net.einself.hingucker");

        final var rule = classes()
                .that().haveSimpleNameEndingWith("Member")
                .and().doNotBelongToAnyOf(Member.class)
                .should().implement(Member.class);

        rule.check(importedClasses);
    }

    @Test
    void classesWhichAreImplementingMemberShouldEndingMember() {
        final var rule = classes()
                .that().implement(Member.class)
                .should().haveSimpleNameEndingWith("Member");

        rule.check(importedClasses);
    }

}

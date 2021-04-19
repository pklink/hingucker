package net.einself.hingucker.core;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import net.einself.hingucker.core.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class MemberTest {

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

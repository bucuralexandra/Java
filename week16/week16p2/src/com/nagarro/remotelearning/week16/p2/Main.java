package com.nagarro.remotelearning.week16.p2;

public class Main {

    public static void main(String[] args) {

        CheckRequirements checkRequirementsForDog = new CheckRequirements("Dog");
        CheckRequirements checkRequirementsForPigeon = new CheckRequirements("Pigeon");
        CheckRequirements checkRequirementsForBird = new CheckRequirements("Bird");

        System.out.println("Dog class");
        System.out.println(checkRequirementsForDog.hasPrivateModifier() ? "Has private modifier" :
                "Does not have private modifier");
        System.out.println(checkRequirementsForDog.methodLowercase() ? "Methods start with lowercase" :
                "Methods do not start with lowercase");
        System.out.println(checkRequirementsForDog.nameLowercase() ? "Name fields start with lowercase" :
                "Name fields do not start with lowercase");
        System.out.println();

        System.out.println("Bird class");
        System.out.println(checkRequirementsForBird.hasPrivateModifier() ? "Has private modifier" :
                "Does not have private modifier");
        System.out.println(checkRequirementsForBird.methodLowercase() ? "Methods start with lowercase" :
                "Methods do not start with lowercase");
        System.out.println(checkRequirementsForBird.nameLowercase() ? "Name fields start with lowercase" :
                "Name fields do not start with lowercase");
        System.out.println();

        System.out.println("Pigeon class");
        System.out.println(checkRequirementsForPigeon.hasPrivateModifier() ? "Has private modifier" :
                "Does not have private modifier");
        System.out.println(checkRequirementsForPigeon.methodLowercase() ? "Methods start with lowercase" :
                "Methods do not start with lowercase");
        System.out.println(checkRequirementsForPigeon.nameLowercase() ? "Name fields start with lowercase" :
                "Name fields do not start with lowercase");
        System.out.println();
    }
}

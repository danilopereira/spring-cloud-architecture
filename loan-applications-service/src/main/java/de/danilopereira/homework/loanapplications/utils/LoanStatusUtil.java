package de.danilopereira.homework.loanapplications.utils;

import de.danilopereira.homework.loanapplications.model.LoanStatus;

import java.security.SecureRandom;

public final class LoanStatusUtil {

    public static LoanStatus randomLoanStatus(){
        final LoanStatus[] enumConstants = LoanStatus.class.getEnumConstants();
        final SecureRandom random = new SecureRandom();
        int x = random.nextInt(enumConstants.length);
        return enumConstants[x];
    }
}

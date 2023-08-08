package com.MundoSenai.Presenca.Service;

public class CpfValidator {

    public static boolean validateCPF(String cpf) {
        // Remove caracteres especiais do CPF (apenas os dígitos serão considerados)
        cpf = NumberCleaner.cleanNumber(cpf);

        // Verifica se o CPF possui 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se o CPF não possui todos os dígitos iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit >= 10) {
            firstDigit = 0;
        }

        // Verifica o primeiro dígito verificador
        if (cpf.charAt(9) - '0' != firstDigit) {
            return false;
        }

        // Calcula o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit >= 10) {
            secondDigit = 0;
        }

        // Verifica o segundo dígito verificador
        return cpf.charAt(10) - '0' == secondDigit;
    }
}

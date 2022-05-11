package utils;

import dao.DAOFactory;
import model.User;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

/**
 * @author Vinicius
 */
public class Validator {
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean validarCNPJ(String cnpj) {
        if ((cnpj == null) || (cnpj.length() != 14)) {
            return false;
        }
        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
    }

    public static boolean validarCPF(String cpf) {
        if ((cpf == null) || (cpf.length() != 11)) {
            return false;
        }
        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);

        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    public static boolean validarDataDMA(int d, int m, int a) {
        boolean correto = true;
        int[] dias = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (a < 0 || m < 1 || m > 12) {
            correto = false;
        } else {
            // valida o dia
            if (a % 4 == 0 && (a % 100 != 0 || a % 400 == 0)) {
                dias[1] = 29;
            }
            if (d < 1 || d > dias[m - 1]) {
                correto = false;
            }
        }
        return (correto);
    }

    public static boolean validarDataFormatada(String dataComFormato) {
        String[] data = dataComFormato.split("/");
        return (validarDataDMA(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
    }

    private static String encryptPass(String pass) {
        try {
            return Base64.getEncoder().encodeToString(
                    MessageDigest.getInstance("SHA-512").digest(
                            pass.getBytes(StandardCharsets.UTF_8)
                    ));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean login(String loginUser, char[] loginPassword) {

        String pass = encryptPass(new String(loginPassword));

        try {
            if (loginUser.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
                return false;
            }

            List<User> users = DAOFactory.getUserDAO().getAll();

            for (User user : users) {
                if (user.getUsername().equals(loginUser)) {
                    System.out.printf("usuário encontrado: %s; id %s\n", user.getUsername(), user.getId());
                    if (validatePassword(user, pass)) {
                        Config.setLoggedUser(user);
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar login.");
            e.printStackTrace();
            return false;
        }

        JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
        return false;
    }

    private static boolean validatePassword(User user, String pass) {
        return Objects.equals(pass, user.getPassword());
    }
}

package puzzle.datastructure.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * Hash Table Implementation
 *
 * Description:
 * Create a java application for banking purposes by adhering to the following constraints.
 *
 * Login Constraints:
 * - Username is required
 * - Password is required
 * - Mobile number is required
 *
 * Account Information:
 * - Name
 * - Account number
 * - Balance
 *
 * You need to develop an application that promises user will be able to login securely given his username, password
 * and mobile number. This means that your application promises that, no other user can access another user account
 * unless he/she has access to all the required fields.
 *
 * As user requires faster access to his/her bank accounts, you need to implement a hash table that takes the key as a
 * combination of username, password and mobile number (i.e credentials class object as key) and, his account
 * information object as value for that particular key.
 *
 * Your application should prompt user to sign up or sign in.
 *
 * If a combination of username, password and mobile number does not exist then you have to print -1 in place of
 * account number.
 *
 * Note: If there are duplicate combinations of username, password and mobile number then consider the first account
 * information provided and ignore the later ones.
 *
 * ------------------------------------------------------------------------------------------------
 * Input format:
 * 1st line - number (either 1->sign in, 2->sign up, 3->Terminate)
 * 2nd line - string -> username
 * 3rd line - string -> password
 * 4th line - number -> mobile number
 * 5th line - string -> account name
 * 6th line - number -> initial bank balance
 * 7th line - number -> account number
 *
 * 2nd line to 4th line are valid for sign in procedure only.
 * 2nd line to 7th line are valid for sign up procedure.
 * And for terminate option only 1st line is valid.
 *
 * Output format:
 * 1st line - number -> account number
 * Output is valid for sign in procedure only. That is print account number only when user has signed into his account.
 *
 * ------------------------------------------------------------------------------------------------
 * Example:
 * 2 (means sign up)
 * abc (username)
 * qwe (password)
 * 1234567891 (mobile)
 * upgrad (account name)
 * 10000 (initial balance)
 * 101 (account number)
 * //end of sign up - this line is not part of input
 *
 * 1 (means sign in)
 * abc (username)
 * qwe (password)
 * 1234567891 (mobile)
 * //end of sign in - this line is not part of input
 *
 * 1 (sign in)
 * qwe (username)
 * qwe (password)
 * 1234567891 (mobile)
 * //end of sign in - this line is not part of input
 *
 * 3 (end program)
 *
 * Output
 * 101 (account number for sign in input given)
 * -1 (account number does not exist for 2nd sign in user)
 * ------------------------------------------------------------------------------------------------
 */

public class BankAccount {
    static Map<Credentials, AccountInfo> database = new HashMap<>();
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int menu = scanner.nextInt();

        while (menu != 3) {
            String userName = scanner.next();
            String password = scanner.next();
            long mobileNumber = scanner.nextLong();

            Credentials credentials = new Credentials();
            credentials.setUserName(userName);
            credentials.setPassword(password);
            credentials.setMobileNumber(mobileNumber);

            //sign-in
            if (menu == 1) {
                if (database.containsKey(credentials)) {
                    AccountInfo accountInfo = database.get(credentials);
                    System.out.println(accountInfo.getAccountNumber());
                } else {
                    System.out.println(-1);
                }
                database.put(credentials, null);
            }

            //sign-up
            if (menu == 2) {
                String accountName = scanner.next();
                int accountBalance = scanner.nextInt();
                long accountNumber = scanner.nextLong();

                AccountInfo accountInfo = new AccountInfo();
                accountInfo.setAccountName(accountName);
                accountInfo.setAccountBalance(accountBalance);
                accountInfo.setAccountNumber(accountNumber);

                database.put(credentials, accountInfo);
            }
            menu = scanner.nextInt();
        }
    }
}

class Credentials {
    private String userName;
    private String password;
    private long mobileNumber;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credentials)) return false;
        Credentials that = (Credentials) o;
        return getMobileNumber() == that.getMobileNumber() &&
                Objects.equals(getUserName(), that.getUserName()) &&
                Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getPassword(), getMobileNumber());
    }
}

class AccountInfo {
    private String accountName;
    private int accountBalance;
    private long accountNumber;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
}


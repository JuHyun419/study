package easy2;

import java.util.Arrays;
import java.util.HashSet;

public class Unique_929 {

    public static int numUniqueEmails(String[] emails) {

        for (int i = 0; i < emails.length; i++) {
            String localName = emails[i].substring(0, emails[i].indexOf("@"));
            String domainName = emails[i].substring(emails[i].indexOf("@"));

            localName = removeDot(localName);
            localName = getRemovedPlusSign(localName);
            emails[i] = localName + domainName;
        }

        return new HashSet<>(Arrays.asList(emails)).size();
    }

    private static String removeDot(final String localName) {
        return localName.replaceAll("\\.", "");
    }

    private static String getRemovedPlusSign(String localName) {
        return isContainsPlusSign(localName)
                ? localName.substring(0, localName.indexOf("+"))
                : localName;
    }

    private static boolean isContainsPlusSign(final String localName) {
        return localName.contains("+");
    }

    public static void main(String[] args) {
        String[] emails = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
        System.out.println(numUniqueEmails(emails));

    }
}

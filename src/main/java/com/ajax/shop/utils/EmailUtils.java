package com.ajax.shop.utils;

import java.net.IDN;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 14.08.18
 */
public final class EmailUtils {
    public static final int MAX_DOMAIN_PART_LENGTH = 255;
    public static final String MAIL_ENCODING = "UTF-8";
    public static final String MAIL_CONTENT_TYPE = "text/html;charset=UTF-8";


    public static String toPunycode(final String address) {
        final String[] emailParts = address.split("@");
        final String localPart = emailParts[0];
        final String domainPartUnicode = emailParts[1];
        final String domainPartAscii = toASCII(domainPartUnicode);
        if (domainPartAscii.length() > MAX_DOMAIN_PART_LENGTH) {
            throw new RuntimeException("invalid email");
        }
        return localPart + "@" + domainPartAscii;
    }

    public static String toASCII(String string) {
        StringBuilder asciiString = new StringBuilder();
        int start = 0;
        int end = string.length() <= 63 ? string.length() : 63;
        while (true) {
            // IDN.toASCII only supports a max "label" length of 63 characters. Need to chunk the input in these sizes
            asciiString.append(IDN.toASCII(string.substring(start, end)));
            if (end == string.length()) {
                break;
            }
            start = end;
            end = start + 63 > string.length() ? string.length() : start + 63;
        }

        return asciiString.toString();
    }
}

package com.acuo.collateral.transform.trace.utils;

public class TraceUtils {

    private static final String DOS = "\r\n", NIX = "\n", MAC = "\r";

    public static String replaceNewLineToWindows(String original) {
        return original.replace(DOS, NIX)
                .replace(MAC, NIX)
                .replace(NIX, DOS);
    }
}

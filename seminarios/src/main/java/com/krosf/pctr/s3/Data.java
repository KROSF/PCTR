package com.krosf.pctr.s3;

public class Data {
  public static String String2sql(String s, boolean bAddQuotes, boolean bAddWildcards) {

    StringBuilder sb = new StringBuilder(s.replace("'", "''"));
    if (bAddQuotes && bAddWildcards) {
      sb.insert(0,"\'%");
      sb.append("%\'");
    } else if (bAddQuotes && !bAddWildcards) {
      sb.insert(0,"\'");
      sb.append("\'");
    } else if (!bAddQuotes && bAddWildcards) {
      sb.insert(0,"%");
      sb.append("%");
    } 
    return sb.toString();
  }

  public static int Boolean2Sql(boolean b) {
    return b ? 1 : 0;
  }

  public static void main(String[] args) {
    System.out.println(String2sql("'Smith '", true, true));
  }

}
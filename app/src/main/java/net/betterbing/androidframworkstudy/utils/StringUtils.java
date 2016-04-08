package net.betterbing.androidframworkstudy.utils;


import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Created by steven on 15/7/16.
 */
public class StringUtils {

    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    public static int indexOfAny(String str, String[] searchStrs) {
        if (str != null && searchStrs != null) {
            int sz = searchStrs.length;
            int ret = 2147483647;
            boolean tmp = false;

            for (int i = 0; i < sz; ++i) {
                int var6 = str.indexOf(searchStrs[i]);
                if (var6 != -1 && var6 < ret) {
                    ret = var6;
                }
            }

            return ret == 2147483647 ? -1 : ret;
        } else {
            return -1;
        }
    }

    public static int lastIndexOfAny(String str, String[] searchStrs) {
        if (str != null && searchStrs != null) {
            int sz = searchStrs.length;
            int ret = -1;
            boolean tmp = false;

            for (int i = 0; i < sz; ++i) {
                int var6 = str.lastIndexOf(searchStrs[i]);
                if (var6 > ret) {
                    ret = var6;
                }
            }

            return ret;
        } else {
            return -1;
        }
    }

    public static String substring(String str, int start) {
        if (str == null) {
            return null;
        } else {
            if (start < 0) {
                start += str.length();
            }

            if (start < 0) {
                start = 0;
            }

            return start > str.length() ? "" : str.substring(start);
        }
    }

    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        } else {
            if (end < 0) {
                end += str.length();
            }

            if (start < 0) {
                start += str.length();
            }

            if (end > str.length()) {
                end = str.length();
            }

            if (start > end) {
                return "";
            } else {
                if (start < 0) {
                    start = 0;
                }

                if (end < 0) {
                    end = 0;
                }

                return str.substring(start, end);
            }
        }
    }

    public static String left(String str, int len) {
        if (len < 0) {
            throw new IllegalArgumentException("Requested String length " + len + " is less than zero");
        } else {
            return str != null && str.length() > len ? str.substring(0, len) : str;
        }
    }

    public static String right(String str, int len) {
        if (len < 0) {
            throw new IllegalArgumentException("Requested String length " + len + " is less than zero");
        } else {
            return str != null && str.length() > len ? str.substring(str.length() - len) : str;
        }
    }

    public static String mid(String str, int pos, int len) {
        if (pos >= 0 && (str == null || pos <= str.length())) {
            if (len < 0) {
                throw new IllegalArgumentException("Requested String length " + len + " is less than zero");
            } else {
                return str == null ? null : (str.length() <= pos + len ? str.substring(pos) : str.substring(pos, pos + len));
            }
        } else {
            throw new StringIndexOutOfBoundsException("String index " + pos + " is out of bounds");
        }
    }

    public static String[] split(String str) {
        return split(str, (String) null, -1);
    }

    public static String[] split(String text, String separator) {
        return split(text, separator, -1);
    }

    public static String[] split(String str, String separator, int max) {
        StringTokenizer tok = null;
        if (separator == null) {
            tok = new StringTokenizer(str);
        } else {
            tok = new StringTokenizer(str, separator);
        }

        int listSize = tok.countTokens();
        if (max > 0 && listSize > max) {
            listSize = max;
        }

        String[] list = new String[listSize];
        int i = 0;
        boolean lastTokenBegin = false;

        for (int lastTokenEnd = 0; tok.hasMoreTokens(); ++i) {
            int var10;
            if (max > 0 && i == listSize - 1) {
                String endToken = tok.nextToken();
                var10 = str.indexOf(endToken, lastTokenEnd);
                list[i] = str.substring(var10);
                break;
            }

            list[i] = tok.nextToken();
            var10 = str.indexOf(list[i], lastTokenEnd);
            lastTokenEnd = var10 + list[i].length();
        }

        return list;
    }

    public static String concatenate(Object[] array) {
        return join(array, "");
    }

    public static String join(Object[] array, String separator) {
        if (separator == null) {
            separator = "";
        }

        int arraySize = array.length;
        int bufSize = arraySize == 0 ? 0 : (array[0].toString().length() + separator.length()) * arraySize;
        StringBuffer buf = new StringBuffer(bufSize);

        for (int i = 0; i < arraySize; ++i) {
            if (i > 0) {
                buf.append(separator);
            }

            buf.append(array[i]);
        }

        return buf.toString();
    }

    public static String join(Iterator iterator, String separator) {
        if (separator == null) {
            separator = "";
        }

        StringBuffer buf = new StringBuffer(256);

        while (iterator.hasNext()) {
            buf.append(iterator.next());
            if (iterator.hasNext()) {
                buf.append(separator);
            }
        }

        return buf.toString();
    }

    public static String replaceOnce(String text, String repl, String with) {
        return replace(text, repl, with, 1);
    }

    public static String replace(String text, String repl, String with) {
        return replace(text, repl, with, -1);
    }

    public static String replace(String text, String repl, String with, int max) {
        if (text == null) {
            return null;
        } else {
            StringBuffer buf = new StringBuffer(text.length());
            int start = 0;
            boolean end = false;

            int var7;
            while ((var7 = text.indexOf(repl, start)) != -1) {
                buf.append(text.substring(start, var7)).append(with);
                start = var7 + repl.length();
                --max;
                if (max == 0) {
                    break;
                }
            }

            buf.append(text.substring(start));
            return buf.toString();
        }
    }

    public static String overlayString(String text, String overlay, int start, int end) {
        return (new StringBuffer(start + overlay.length() + text.length() - end + 1)).append(text.substring(0, start)).append(overlay).append(text.substring(end)).toString();
    }

    public static String center(String str, int size) {
        return center(str, size, " ");
    }

    public static String center(String str, int size, String delim) {
        int sz = str.length();
        int p = size - sz;
        if (p < 1) {
            return str;
        } else {
            str = leftPad(str, sz + p / 2, delim);
            str = rightPad(str, size, delim);
            return str;
        }
    }

    public static String chomp(String str) {
        return chomp(str, "\n");
    }

    public static String chomp(String str, String sep) {
        int idx = str.lastIndexOf(sep);
        return idx != -1 ? str.substring(0, idx) : str;
    }

    public static String chompLast(String str) {
        return chompLast(str, "\n");
    }

    public static String chompLast(String str, String sep) {
        if (str.length() == 0) {
            return str;
        } else {
            String sub = str.substring(str.length() - sep.length());
            return sep.equals(sub) ? str.substring(0, str.length() - sep.length()) : str;
        }
    }

    public static String getChomp(String str, String sep) {
        int idx = str.lastIndexOf(sep);
        return idx == str.length() - sep.length() ? sep : (idx != -1 ? str.substring(idx) : "");
    }

    public static String prechomp(String str, String sep) {
        int idx = str.indexOf(sep);
        return idx != -1 ? str.substring(idx + sep.length()) : str;
    }

    public static String getPrechomp(String str, String sep) {
        int idx = str.indexOf(sep);
        return idx != -1 ? str.substring(0, idx + sep.length()) : "";
    }

    public static String chop(String str) {
        if ("".equals(str)) {
            return "";
        } else if (str.length() == 1) {
            return "";
        } else {
            int lastIdx = str.length() - 1;
            String ret = str.substring(0, lastIdx);
            char last = str.charAt(lastIdx);
            return last == 10 && ret.charAt(lastIdx - 1) == 13 ? ret.substring(0, lastIdx - 1) : ret;
        }
    }

    public static String chopNewline(String str) {
        int lastIdx = str.length() - 1;
        char last = str.charAt(lastIdx);
        if (last == 10) {
            if (str.charAt(lastIdx - 1) == 13) {
                --lastIdx;
            }
        } else {
            ++lastIdx;
        }

        return str.substring(0, lastIdx);
    }

    public static String escape(String str) {
        int sz = str.length();
        StringBuffer buffer = new StringBuffer(2 * sz);

        for (int i = 0; i < sz; ++i) {
            char ch = str.charAt(i);
            if (ch > 4095) {
                buffer.append("\\u" + Integer.toHexString(ch));
            } else if (ch > 255) {
                buffer.append("\\u0" + Integer.toHexString(ch));
            } else if (ch > 127) {
                buffer.append("\\u00" + Integer.toHexString(ch));
            } else if (ch < 32) {
                switch (ch) {
                    case '\b':
                        buffer.append('\\');
                        buffer.append('b');
                        break;
                    case '\t':
                        buffer.append('\\');
                        buffer.append('t');
                        break;
                    case '\n':
                        buffer.append('\\');
                        buffer.append('n');
                        break;
                    case '\u000b':
                    default:
                        if (ch > 15) {
                            buffer.append("\\u00" + Integer.toHexString(ch));
                        } else {
                            buffer.append("\\u000" + Integer.toHexString(ch));
                        }
                        break;
                    case '\f':
                        buffer.append('\\');
                        buffer.append('f');
                        break;
                    case '\r':
                        buffer.append('\\');
                        buffer.append('r');
                }
            } else {
                switch (ch) {
                    case '\"':
                        buffer.append('\\');
                        buffer.append('\"');
                        break;
                    case '\'':
                        buffer.append('\\');
                        buffer.append('\'');
                        break;
                    case '\\':
                        buffer.append('\\');
                        buffer.append('\\');
                        break;
                    default:
                        buffer.append(ch);
                }
            }
        }

        return buffer.toString();
    }

    public static String unescape(String str) {
        int sz = str.length();
        StringBuffer buffer = new StringBuffer(sz);
        StringBuffer unicode = new StringBuffer(4);
        boolean hadSlash = false;
        boolean inUnicode = false;

        for (int i = 0; i < sz; ++i) {
            char ch = str.charAt(i);
            if (inUnicode) {
                if (unicode.length() != 4) {
                    unicode.append(ch);
                    continue;
                }

                try {
                    int nfe = Integer.parseInt(unicode.toString(), 16);
                    buffer.append((char) nfe);
                    unicode.setLength(0);
                    unicode.setLength(4);
                    inUnicode = false;
                    hadSlash = false;
                } catch (NumberFormatException var9) {
//                    throw new NestableRuntimeException("Unable to parse unicode value: " + unicode, var9);
                }
            }

            if (hadSlash) {
                hadSlash = false;
                switch (ch) {
                    case '\"':
                        buffer.append('\"');
                        break;
                    case '\'':
                        buffer.append('\'');
                        break;
                    case '\\':
                        buffer.append('\\');
                        break;
                    case 'b':
                        buffer.append('\b');
                        break;
                    case 'f':
                        buffer.append('\f');
                        break;
                    case 'n':
                        buffer.append('\n');
                        break;
                    case 'r':
                        buffer.append('\r');
                        break;
                    case 't':
                        buffer.append('\t');
                        break;
                    case 'u':
                        inUnicode = true;
                        break;
                    default:
                        buffer.append(ch);
                }
            } else if (ch == 92) {
                hadSlash = true;
            } else {
                buffer.append(ch);
            }
        }

        if (hadSlash) {
            buffer.append('\\');
        }

        return buffer.toString();
    }

    public static String repeat(String str, int repeat) {
        StringBuffer buffer = new StringBuffer(repeat * str.length());

        for (int i = 0; i < repeat; ++i) {
            buffer.append(str);
        }

        return buffer.toString();
    }

    public static String rightPad(String str, int size) {
        return rightPad(str, size, " ");
    }

    public static String rightPad(String str, int size, String delim) {
        size = (size - str.length()) / delim.length();
        if (size > 0) {
            str = str + repeat(delim, size);
        }

        return str;
    }

    public static String leftPad(String str, int size) {
        return leftPad(str, size, " ");
    }

    public static String leftPad(String str, int size, String delim) {
        size = (size - str.length()) / delim.length();
        if (size > 0) {
            str = repeat(delim, size) + str;
        }

        return str;
    }

    public static String strip(String str) {
        return strip(str, (String) null);
    }

    public static String strip(String str, String delim) {
        str = stripStart(str, delim);
        return stripEnd(str, delim);
    }

    public static String[] stripAll(String[] strs) {
        return stripAll(strs, (String) null);
    }

    public static String[] stripAll(String[] strs, String delimiter) {
        if (strs != null && strs.length != 0) {
            int sz = strs.length;
            String[] newArr = new String[sz];

            for (int i = 0; i < sz; ++i) {
                newArr[i] = strip(strs[i], delimiter);
            }

            return newArr;
        } else {
            return strs;
        }
    }

    public static String stripEnd(String str, String strip) {
        if (str == null) {
            return null;
        } else {
            int end = str.length();
            if (strip == null) {
                while (end != 0 && Character.isWhitespace(str.charAt(end - 1))) {
                    --end;
                }
            } else {
                while (end != 0 && strip.indexOf(str.charAt(end - 1)) != -1) {
                    --end;
                }
            }

            return str.substring(0, end);
        }
    }

    public static String stripStart(String str, String strip) {
        if (str == null) {
            return null;
        } else {
            int start = 0;
            int sz = str.length();
            if (strip == null) {
                while (start != sz && Character.isWhitespace(str.charAt(start))) {
                    ++start;
                }
            } else {
                while (start != sz && strip.indexOf(str.charAt(start)) != -1) {
                    ++start;
                }
            }

            return str.substring(start);
        }
    }

    public static String upperCase(String str) {
        return str == null ? null : str.toUpperCase();
    }

    public static String lowerCase(String str) {
        return str == null ? null : str.toLowerCase();
    }

    public static String uncapitalise(String str) {
        return str == null ? null : (str.length() == 0 ? "" : (new StringBuffer(str.length())).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString());
    }

    public static String capitalise(String str) {
        return str == null ? null : (str.length() == 0 ? "" : (new StringBuffer(str.length())).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString());
    }

    public static String swapCase(String str) {
        if (str == null) {
            return null;
        } else {
            int sz = str.length();
            StringBuffer buffer = new StringBuffer(sz);
            boolean whitespace = false;
            boolean ch = false;
            boolean tmp = false;

            for (int i = 0; i < sz; ++i) {
                char var7 = str.charAt(i);
                char var8;
                if (Character.isUpperCase(var7)) {
                    var8 = Character.toLowerCase(var7);
                } else if (Character.isTitleCase(var7)) {
                    var8 = Character.toLowerCase(var7);
                } else if (Character.isLowerCase(var7)) {
                    if (whitespace) {
                        var8 = Character.toTitleCase(var7);
                    } else {
                        var8 = Character.toUpperCase(var7);
                    }
                } else {
                    var8 = var7;
                }

                buffer.append(var8);
                whitespace = Character.isWhitespace(var7);
            }

            return buffer.toString();
        }
    }

    public static String capitaliseAllWords(String str) {
        if (str == null) {
            return null;
        } else {
            int sz = str.length();
            StringBuffer buffer = new StringBuffer(sz);
            boolean space = true;

            for (int i = 0; i < sz; ++i) {
                char ch = str.charAt(i);
                if (Character.isWhitespace(ch)) {
                    buffer.append(ch);
                    space = true;
                } else if (space) {
                    buffer.append(Character.toTitleCase(ch));
                    space = false;
                } else {
                    buffer.append(ch);
                }
            }

            return buffer.toString();
        }
    }

    public static String uncapitaliseAllWords(String str) {
        if (str == null) {
            return null;
        } else {
            int sz = str.length();
            StringBuffer buffer = new StringBuffer(sz);
            boolean space = true;

            for (int i = 0; i < sz; ++i) {
                char ch = str.charAt(i);
                if (Character.isWhitespace(ch)) {
                    buffer.append(ch);
                    space = true;
                } else if (space) {
                    buffer.append(Character.toLowerCase(ch));
                    space = false;
                } else {
                    buffer.append(ch);
                }
            }

            return buffer.toString();
        }
    }

    public static String getNestedString(String str, String tag) {
        return getNestedString(str, tag, tag);
    }

    public static String getNestedString(String str, String open, String close) {
        if (str == null) {
            return null;
        } else {
            int start = str.indexOf(open);
            if (start != -1) {
                int end = str.indexOf(close, start + open.length());
                if (end != -1) {
                    return str.substring(start + open.length(), end);
                }
            }

            return null;
        }
    }

    public static int countMatches(String str, String sub) {
        if (sub.equals("")) {
            return 0;
        } else if (str == null) {
            return 0;
        } else {
            int count = 0;

            for (int idx = 0; (idx = str.indexOf(sub, idx)) != -1; idx += sub.length()) {
                ++count;
            }

            return count;
        }
    }

    public static boolean isAlpha(String str) {
        if (str == null) {
            return false;
        } else {
            int sz = str.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isLetter(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isWhitespace(String str) {
        if (str == null) {
            return false;
        } else {
            int sz = str.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isAlphaSpace(String str) {
        if (str == null) {
            return false;
        } else {
            int sz = str.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isLetter(str.charAt(i)) && str.charAt(i) != 32) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isAlphanumeric(String str) {
        if (str == null) {
            return false;
        } else {
            int sz = str.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isLetterOrDigit(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isAlphanumericSpace(String str) {
        if (str == null) {
            return false;
        } else {
            int sz = str.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isLetterOrDigit(str.charAt(i)) && str.charAt(i) != 32) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        } else {
            int sz = str.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isNumericSpace(String str) {
        if (str == null) {
            return false;
        } else {
            int sz = str.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != 32) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String defaultString(Object obj) {
        return defaultString(obj, "");
    }

    public static String defaultString(Object obj, String defaultString) {
        return obj == null ? defaultString : obj.toString();
    }

    public static String reverse(String str) {
        return str == null ? null : (new StringBuffer(str)).reverse().toString();
    }

    public static String reverseDelimitedString(String str, String delimiter) {
        String[] strs = split(str, delimiter);
        reverseArray(strs);
        return join((Object[]) strs, delimiter);
    }

    private static void reverseArray(Object[] array) {
        int i = 0;

        for (int j = array.length - 1; j > i; ++i) {
            Object tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            --j;
        }

    }

    public static boolean containsOnly(String str, char[] valid) {
        if (str != null && valid != null) {
            int strSize = str.length();
            int validSize = valid.length;

            for (int i = 0; i < strSize; ++i) {
                boolean contains = false;

                for (int j = 0; j < validSize; ++j) {
                    if (valid[j] == str.charAt(i)) {
                        contains = true;
                        break;
                    }
                }

                if (!contains) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

}

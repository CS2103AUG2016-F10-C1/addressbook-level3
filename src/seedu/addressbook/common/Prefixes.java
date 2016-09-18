package seedu.addressbook.common;

import java.util.Arrays;
import java.util.List;

/**
 * Container for user input prefixes.
 */
public class Prefixes {

    public static final int LENGTH_DEFAULT = 2;
    public static final int LENGTH_TAG = 3;
    
    public static final String NAME = "n/";
    public static final String PHONE = "p/";
    public static final String EMAIL = "e/";
    public static final String ADDRESS = "a/";
    public static final String ADDTAG = "ta/";
    public static final String REMOVETAG = "tr/";
    
    public static final List<String> DEFAULT_PREFIXES = Arrays.asList(NAME, PHONE, EMAIL, ADDRESS);
    public static final List<String> TAG_PREFIXES = Arrays.asList(ADDTAG, REMOVETAG);
    
}

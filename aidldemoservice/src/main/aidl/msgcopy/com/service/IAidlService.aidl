// IAidlService.aidl
package msgcopy.com.service;

// Declare any non-default types here with import statements

interface IAidlService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,double aDouble, String aString);

    void setValue(String value);

    String getValue();
}

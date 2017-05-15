// IMyAidlService.aidl
package msgcopy.com.aidl;

// Declare any non-default types here with import statements

interface IMyAidlService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int basicTypes(int anInt1, int anInt2);

    String addvalue(String value);
}

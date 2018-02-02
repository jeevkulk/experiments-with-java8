package generic;

import java.util.ArrayList;
import java.util.List;

public class MethodTypeInference<T> {

    /**
     * Copies target elements to source
     * @param sourceList
     * @param targetList
     * @param <T> : Type parameter here is optional
     */
    public <T> void copyList(List<T> sourceList, List<T> targetList) {
        sourceList.addAll(targetList);
    }

    /**
     * Creates new ArrayList and copies source and target to it
     * @param sourceList
     * @param targetList
     * @param <T> : Type parameter here is optional
     * @return
     */
    public static <T> List<T> staticCopyList(List<T> sourceList, List<T> targetList) {
        List<T> list = new ArrayList<>(sourceList);
        list.addAll(targetList);
        return list;
    }
}

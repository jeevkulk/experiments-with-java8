package puzzle.datastructure.list;

import java.util.ArrayList;

public class ReplaceElement {

    //TODO: This needs to be implemented without using new ArrayList
    public ArrayList<String> replaceEmployee(ArrayList<String> employee, String replaceThis, final String replaceWith)
    {
        ArrayList<String> employeeList = new ArrayList<>();
        for (String name : employee) {
            if (name.equals(replaceThis))
                employeeList.add(replaceWith);
            else
                employeeList.add(name);
        }
        return employeeList;
    }

}

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Combination_Sum {
    //Main Method that calls other defined functions/methods. Test Cases used are the same as the Assignment Doc.
    public static void main(String[] args) {
        System.out.println("========================");
        System.out.println("Ryan Davis");
        System.out.println("Large-Scale Programming");
        System.out.println("Assignment-1");
        System.out.println("=========================");

        ArrayList<Integer> Test_List_1 = new ArrayList<>();
        Test_List_1.add(5);
        Test_List_1.add(5);
        Test_List_1.add(15);
        Test_List_1.add(10);
        int Test_Sum_1 = 15;

        ArrayList<ArrayList<Integer>> List_of_Combinations_1 = Make_List_of_Combinations(Test_List_1, Test_Sum_1);
        Print_List_of_Combinations(Test_List_1, List_of_Combinations_1);

        ArrayList<Integer> Test_List_2 = new ArrayList<>();
        Test_List_2.add(1);
        Test_List_2.add(2);
        Test_List_2.add(3);
        Test_List_2.add(4);
        int Test_Sum_2 = 6;

        ArrayList<ArrayList<Integer>> List_of_Combinations_2 = Make_List_of_Combinations(Test_List_2, Test_Sum_2);
        Print_List_of_Combinations(Test_List_2, List_of_Combinations_2);
    }

    // Method that Compiles the List of Combination that sum up to the Test_Sum
    public static ArrayList<ArrayList<Integer>> Make_List_of_Combinations(ArrayList<Integer> Test_List, int Test_Sum) {

        ArrayList<Integer> Combinations_Tracked = new ArrayList<>();
        ArrayList<ArrayList<Integer>> List_of_Combinations = new ArrayList<>();

        for (int i = 0; i < Test_List.size(); i++)
        {
            if (Test_List.get(i) >= 0)
            {
                ArrayList<Integer> Present_Combination = new ArrayList<Integer>();
                if (Test_List.get(i) == Test_Sum)
                {
                    Present_Combination.add(i);
                    if (!List_of_Combinations.contains(Present_Combination))
                    {
                        List_of_Combinations.add(Present_Combination);
                    }
                } else {
                    int Potential_Test_Sum = Test_Sum - Test_List.get(i);
                    if ((Combinations_Tracked.contains(Potential_Test_Sum)) && (i != Combinations_Tracked.indexOf(Potential_Test_Sum)))
                    {
                        int index = Combinations_Tracked.indexOf(Potential_Test_Sum);
                        Present_Combination.add(i);
                        Present_Combination.add(index);
                        Collections.sort(Present_Combination);
                        if (!List_of_Combinations.contains(Present_Combination))
                        {
                            List_of_Combinations.add(Present_Combination);
                        }
                    } else {
                        Combinations_Tracked.add(Test_List.get(i));
                        if (Potential_Test_Sum > 0)
                        {
                            ArrayList<Integer> Duplicate_Test_List = new ArrayList<>(Test_List.size());
                            for (int Current_Item : Test_List)
                            {
                                Duplicate_Test_List.add(Current_Item);
                            }
                            Duplicate_Test_List.set(i, -1);
                            for (ArrayList<Integer> Recursed_List_Combinations : Make_List_of_Combinations(Duplicate_Test_List, Potential_Test_Sum))
                            {
                                if (!Recursed_List_Combinations.contains(i))
                                {
                                    Recursed_List_Combinations.add(i);
                                    Collections.sort(Recursed_List_Combinations);
                                    if (!List_of_Combinations.contains(Recursed_List_Combinations))
                                    {
                                        List_of_Combinations.add(Recursed_List_Combinations);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return List_of_Combinations;
    }

    //Method that Prints the List of Combinations according to the Assignment Doc.
    public static void Print_List_of_Combinations(ArrayList<Integer> Test_List, ArrayList<ArrayList<Integer>> List_of_Combinations) {

        Collections.sort(List_of_Combinations, new Comparator<ArrayList>()
        {
            public int compare(ArrayList List_1, ArrayList List_2)
            {
                return List_1.size() - List_2.size();
            }
        });

        int Index_Upper_Bound = Test_List.size() - 1;
        int Index_Digits_Upper_Bound;
        if (Index_Upper_Bound == 0)
        {
            Index_Digits_Upper_Bound = 1;
        }
        Index_Digits_Upper_Bound = 0;
        while (Index_Upper_Bound > 0)
        {
            Index_Digits_Upper_Bound += 1;
            Index_Upper_Bound /= 10;
        }

        int Number_Upper_Bound = Test_List.get(0);
        for (int i : Test_List)
        {
            if (i > Number_Upper_Bound)
            {
                Number_Upper_Bound = i;
            }
        }

        int Number_Digits_Upper_Bound;
        if (Number_Upper_Bound == 0)
        {
            Number_Digits_Upper_Bound = 1;
        }
        Number_Digits_Upper_Bound = 0;
        while (Number_Upper_Bound > 0)
        {
            Number_Digits_Upper_Bound += 1;
            Number_Upper_Bound /= 10;
        }


        System.out.println("\nCombinations for Sum:");
        System.out.println("=======================");
        for (ArrayList<Integer> Print_Combination : List_of_Combinations) {
            String Print_String_Argument = "";
            Print_String_Argument += "[";
            for (int i = 0; i < Print_Combination.size(); i++)
            {
                if (i == Print_Combination.size() - 1)
                {
                    Print_String_Argument += Print_Combination.get(i) + "]";
                } else {
                    Print_String_Argument += Print_Combination.get(i) + ", ";
                }
            }
            System.out.print(Print_String_Argument);

            System.out.print("\t=>\t");

            String Print_Placeholders;
            for(int i = 0; i < Print_Combination.size(); i++)
            {
                if (i == Print_Combination.size() - 1)
                {
                    System.out.print("input[");
                    Print_Placeholders = "%" + Index_Digits_Upper_Bound + "d] = ";
                    System.out.printf(Print_Placeholders, Print_Combination.get(i));
                    Print_Placeholders = "%" + Number_Digits_Upper_Bound + "d\n";
                    System.out.printf(Print_Placeholders, Test_List.get(Print_Combination.get(i)));
                } else {
                    System.out.print("input[");
                    Print_Placeholders = "%" + Index_Digits_Upper_Bound + "d] = ";
                    System.out.printf(Print_Placeholders, Print_Combination.get(i));
                    Print_Placeholders = "%" + Number_Digits_Upper_Bound + "d, ";
                    System.out.printf(Print_Placeholders, Test_List.get(Print_Combination.get(i)));
                }
            }
        }
    }
}

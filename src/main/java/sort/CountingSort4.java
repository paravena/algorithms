package sort;

import java.io.InputStream;
import java.util.Scanner;

public class CountingSort4 {
    public static void main(String[] args) {
        Data[] arr = readInput(System.in);
        Data[] sortedArr = sort(arr);
        printOutput(sortedArr);

    }

    private static Data[] sort(Data[] arr) {
        int L = arr.length;
        Data[] sortedArr = new Data[L];
        int index = 0;
        //count loop to sort the array to a new one
        for (int i = 0; i< 100; i++) {
            for (Data dataElement : arr) {
                if (dataElement.num == i) {
                    sortedArr[index] = new Data();
                    sortedArr[index].text = dataElement.text;
                    sortedArr[index].originalIndex = dataElement.originalIndex;
                    index++;
                }
            }
        }
        return sortedArr;
    }

    private static void printOutput(Data[] sortedArr) {
        int L = sortedArr.length;
        //build the output
        StringBuilder text = new StringBuilder();
        if(sortedArr[0].originalIndex< (L / 2)){
            text.append("-");
        } else {
            text.append(sortedArr[0].text);
        }

        for(int i=1; i<sortedArr.length; i++ ){
            if (sortedArr[i].originalIndex < (L / 2)){
                text.append(" -");
            } else {
                text.append(sortedArr[i].text);
            }
        }
        //printing
        System.out.println(text);
    }


    public static Data[] readInput(InputStream in) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Data[] result = new Data[n];
        for(int i=0; i < n; i++){
            result[i] = new Data();
            result[i].num = scan.nextInt();
            result[i].text = scan.nextLine();
            result[i].originalIndex=i;
        }
        return result;
    }
}

class Data {
    public String text;
    public int originalIndex;
    public int num;
}
/*
*Question : Implement two stack using array
*Operation : push1(int x), push2(int x), pop1(int x), pop2(int x)
*Approach : intialize an array and maxSize for both stack has 0 to mid append
* and mid to end.keep an counter to hold current array index after each
* insertion and deletion
*/
public class TwoStack {
    int[] counterArray = new int[2];
    int[] twoStackArray;
    int maxSize;
    public TwoStack(int n) {
        this.twoStackArray = new int[n];
        maxSize = twoStackArray.length/2;
    }

    public void push1(int n) {
        if(counterArray[0] >=  maxSize) {
            System.out.println("stack 1 is full of capacity");
        } else {
            twoStackArray[counterArray[0]] = n;
            counterArray[0]++;
        }
    }

    public void push2(int n) {
        if(counterArray[1] >= maxSize) {
            System.out.println("stack 1 is full of capacity");
        } else {
            twoStackArray[counterArray[1] + maxSize] = n;
            counterArray[1]++;
        }
    }

    int pop1() {
        if(counterArray[0] > 0) {
            int result = twoStackArray[counterArray[0] -1];
            counterArray[0]--;
            return result;
        } else {
            System.out.println("Stack 1 is empty");
            System.exit(1);
        }
        return 0;
    }

    int pop2() {
        if(counterArray[1] > 0) {
            int result = twoStackArray[counterArray[1] + maxSize -1];
            counterArray[1]--;
            return result;
        } else {
            System.out.println("Stack 2 is empty");
            System.exit(1);
        }
        return 0;
    }

    public void printArray() {
        int size = twoStackArray.length;
        for( int i =0; i < size; i++) {
            System.out.print(twoStackArray[i] + " ");
        }
    }

    public static void main(String[] args) {
        TwoStack objTwoStack = new TwoStack(10);
        objTwoStack.push1(10);
        objTwoStack.push1(20);
        objTwoStack.push1(30);
        objTwoStack.push2(10);
        objTwoStack.push2(30);
        objTwoStack.printArray();


    }
}

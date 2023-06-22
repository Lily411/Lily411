package Day05;

import java.util.Stack;

public class NewStack {
    public Object push;
    Stack<Character> list = new Stack<>();

    public void push(char c) {
        list.push(c);
    }

    public Character peek() {
        return list.peek();
    }

    public void pop() {
        list.pop();
    }

    public void reverse() {
        char[] c = new char[list.size()];
        int count = 0;//actual element
        for(int i = 0 ; i < c.length ; i++){
            if(list.peek()==' '){
                list.pop();
            }
            else{
                c[i] = list.pop();
                count += 1;
            }
        }
        for (int i = 0 ; i < count ; i++){
            list.push(c[i]);
        }
    }

    @Override
    public String toString() {
        return "NewStack{" +
                "list=" + list +
                '}';
    }

    public int size() {
        return list.size();
    }
}

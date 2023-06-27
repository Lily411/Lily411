package Day11;

import java.util.LinkedList;

public class Monkey {
    LinkedList<Long> items = new LinkedList<>();
    private String operator;
    private int number;
    private int divisible;
    private int ifTrue;
    private int ifFalse;

    public Monkey(String operator, int number, int divisible, int ifTrue, int ifFalse) {
        this.operator = operator;
        this.number = number;
        this.divisible = divisible;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }

    public LinkedList<Long> getItem() {
        return items;
    }

    public void setItem(LinkedList<Long> item) {
        this.items = item;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDivisible() {
        return divisible;
    }

    public void setDivisible(int divisible) {
        this.divisible = divisible;
    }

    public int getIfTrue() {
        return ifTrue;
    }

    public void setIfTrue(int ifTrue) {
        this.ifTrue = ifTrue;
    }

    public int getIfFalse() {
        return ifFalse;
    }

    public void setIfFalse(int ifFalse) {
        this.ifFalse = ifFalse;
    }

    public void addLast(long item){
        items.addLast(item);
    }

    public long getFirst(){
        return items.getFirst();
    }

    public void removeFirst(){
        items.removeFirst();
    }

    public long worrylevelPart2(long old, int lcm){
        long number = 0;

        if(this.getNumber() == -1){
            number = old;
        }
        else{
            number = this.getNumber();
        }

        if(this.getOperator().equals("*")){
            return (old * number) % lcm;
        }
        else if (this.getOperator().equals("+")){
            return (old + number) % lcm;
        }

        return 0;
    }

    public long worrylevelPart1(long old){
        long number = 0;

        if(this.getNumber() == -1){
            number = old;
        }
        else{
            number = this.getNumber();
        }

        if(this.getOperator().equals("*")){
            return (old * number) / 3;
        }
        else if (this.getOperator().equals("+")){
            return (old + number) / 3;
        }

        return 0;
    }

    public int thrownTo (long worryLevel){
        if(worryLevel%this.divisible == 0){
            return this.ifTrue;
        }
        else{
            return this.ifFalse;
        }
    }

    public int size(){
        return items.size();
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "items=" + items +
                ", operator='" + operator + '\'' +
                ", number=" + number +
                ", divisible=" + divisible +
                ", ifTrue=" + ifTrue +
                ", ifFalse=" + ifFalse +
                '}';
    }
}

package elements;

import visitor.Visitor;


public class MyElement implements Element<Visitor> {

    private String sentence;

    private MyElement(){
    }

    public MyElement(String s){
        sentence = s;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getSentence(){
        return sentence;
    }

    @Override
    public String toString(){
        return getSentence();
    }
}

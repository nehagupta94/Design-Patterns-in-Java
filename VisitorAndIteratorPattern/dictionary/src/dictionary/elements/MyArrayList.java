package elements;

import iteration.Container;
import iteration.Iterator;
import util.FileProcessor;
import visitor.Visitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyArrayList implements Element<Visitor>, Container {

    private List<Element> arrayList;
    private FileProcessor fp;

    public MyArrayList(Builder builder) {
        this.fp = builder.fp;
        this.arrayList = builder.arrayList;
    }

    @Override
    public void accept(Visitor visitor) {
        Iterator temp = getIterator();
        while(temp.hasNext()){
            Element e = (Element) temp.next();
            e.accept(visitor);
        }
    }

    @Override
    public Iterator getIterator() {
        return new ElementIterator();
    }

    private class ElementIterator implements Iterator {
        int index;
        @Override
        public boolean hasNext() {
            if(index < arrayList.size()){
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if(this.hasNext()){
                return arrayList.get(index++);
            }
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static class Builder{
        private FileProcessor fp;
        private List<Element> arrayList;

        public Builder(){
            arrayList = new ArrayList<>();
        }

        public Builder setFp(FileProcessor fp) {
            this.fp = fp;
            return this;
        }

        public Builder withFileProcessor(FileProcessor fileProcessor) {
            setFp(fileProcessor);
            return this;
        }

        public MyArrayList build()
        {
            try{
                String line = fp.poll();
                String[] sentences = line.trim().split("\\.\\s*");
                for(String s : sentences){
                    Element e = new MyElement(s);
                    arrayList.add(e);
                }
                fp.close();
            }catch (IOException e){
                System.err.println("Exception: ");
                System.out.println("Exception: "+ e.getMessage().getClass().getName());
                e.printStackTrace();
            }
            /*for(int i =0;i < arrayList.size();i++){
                Element e = arrayList.get(i);
                //System.out.println(e.toString());
            }*/
            return new MyArrayList(this);
        }
    }
}

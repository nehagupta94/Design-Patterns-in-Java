package visitor;

import results.Results;
import results.TopKFreqWordsResults;
import elements.Element;
import elements.MyElement;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class TopKMostFreqAnalyzer implements Visitor {
    private int k;
    Results topKFreqWordsResults;
    List<String> result;
    private final class TopK {
        String word;
        int frequency;
        public TopK(String wordIn, int frequencyIn) {
            this.word = wordIn;
            this.frequency = frequencyIn;
        }
    }

    public TopKMostFreqAnalyzer(String arg, Results topKFreqWordsResultsIn) {
        k = Integer.parseInt(arg);
        topKFreqWordsResults = topKFreqWordsResultsIn;
    }

    @Override
    public void visit(Element element) {
        //System.out.println("Reached here in TopK Visitor");
        result = calculateTopKFreqWords(element);
        //System.out.println("result : " + Arrays.toString(result.toArray()));
        ((TopKFreqWordsResults)topKFreqWordsResults).addResult(result);
    }

    private String[] getWordsFromElement(Element element){
        String[] temp = ((MyElement)element).getSentence().split("\\s+");
        return temp;
    }

    public List<String> calculateTopKFreqWords(Element element) {
        String[] words = getWordsFromElement(element);
        //System.out.println("words  : " + Arrays.toString(words));
        result = new ArrayList<>();
        Map<String, Integer> fmap = new HashMap();

        for(String w: words){
            fmap.put(w, fmap.getOrDefault(w, 0)+1);
        }

        PriorityQueue<TopK> pq = new PriorityQueue<>(new Comparator<TopK>(){
            @Override
            public int compare (TopK p1, TopK p2) {
                if(p1.frequency == p2.frequency)
                    return p2.word.compareTo(p1.word);
                return p1.frequency - p2.frequency;
            }
        });

        for (String key : fmap.keySet()) {
            pq.add(new TopK(key, fmap.get(key)));
            if (pq.size() > k) {
                pq.poll();
            }
        }
        while(!pq.isEmpty()) {
            result.add(0, pq.poll().word);
        }
        return result;
    }
}

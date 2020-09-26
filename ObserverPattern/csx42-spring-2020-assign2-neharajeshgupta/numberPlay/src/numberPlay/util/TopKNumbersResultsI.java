package numberPlay.util;

import java.util.List;

/**
* TopKNumbersResultsI defines an interface to be implemented by
* classes that intend to store the top K numbers when processing
* a stream of numbers.
*/
public interface TopKNumbersResultsI {
	void store(List<Double> topK);
}

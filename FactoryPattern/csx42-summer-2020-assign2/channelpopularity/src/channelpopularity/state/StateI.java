package channelpopularity.state;

import java.util.Map;

/**
 * This is just an Interface StateI which is being implemented by Abstract State and has the following methods.
 * add Video : This method is used to add video and takes Map<String,Object> as an argument.
 * remove Video : This method is used to remove video and takes same argument as add video.
 * UpdateMetrics : This method is used to update the properties of the videos which are like, views and dislikes. It also takes same arguments as add and remove video.
 * adRequest : This method is used to ask for permission for an ad to be inserted or not base on the state and it also takes the same arguments.
 * updateState : This method is used to updateState based on the popularity score.
 * calculatePopularityScore : This method is used to calculate the average popularity score of the the channel based on the likes, dislikes and views of all the videos present.
 * approveAd : This is a boolean method which just tells if Ad is being Aprroved or not. It takes the lenght of the ad as it's argument.
 */


public interface StateI {

     void addVideo(Map<String,Object> params);
     void removeVideo(Map<String,Object> params);
     void updateMetrics(Map<String,Object> params);
     void adRequest(Map<String,Object> params);
     StateName updateState();
     void calculatePopularityScore();
     boolean approveAd(int length);

}

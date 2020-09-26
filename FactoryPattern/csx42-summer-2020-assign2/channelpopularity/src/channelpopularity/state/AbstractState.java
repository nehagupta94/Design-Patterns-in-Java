package channelpopularity.state;

import channelpopularity.context.ContextI;
import channelpopularity.util.Results;

import javax.naming.spi.DirStateFactory;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractState implements StateI {

    final ContextI ctx;
    final Results res;
    final StateName name;

    public AbstractState(ContextI ctx, Results res, StateName name) {
        this.ctx = ctx;
        this.res = res;
        this.name = name;
    }

    /**
     *
     * @param params
     * @exception  Video Already Exists.
     */
    @Override
    public void addVideo(Map<String, Object> params) {
        if (ctx.getVideos().put((String) params.get("vidName"), new HashMap<>()) != null)
            throw new RuntimeException("VIDEO " + params.get("vidName") + " ALREADY EXISTS");
        res.save(name.name() + "VIDEO_ADDED::" + params.get("vidName"));
    }

    /**
     *
     * @param params
     * @exception  Video being asked to remove does not exist.
     */

    public void removeVideo(Map<String, Object> params) {
        Map<String, Integer> hs = ctx.getVideos().remove((String) params.get("vidName"));
        if (hs == null) {
            throw new RuntimeException("VIDEO DOES NOT EXIST");
        }
            int views = hs.getOrDefault("views", 0);
            int likes = hs.getOrDefault("likes", 0);
            int dislikes = hs.getOrDefault("dislikes", 0);
            int popScore = views + (2 * (likes - dislikes));

            res.save(name.name() + "__VIDEO_REMOVED::" + params.get("vidName"));

        }

    /**
     *
     * @param paramsFromLine
     * @exception  Views Value cannot be negative
     * @exception  Values should be integer.
     */

        public void updateMetrics(Map<String, Object> paramsFromLine) {
        if(!(ctx.getVideos().containsKey(paramsFromLine.get("vidName")))){
            throw new RuntimeException("VIDEO NOT PRESENT!");
        }
        Map<String, Integer> vidProp = ctx.getVideos().get((String) paramsFromLine.get("vidName"));
        int views = vidProp.getOrDefault("views", 0);
        int likes = vidProp.getOrDefault("likes", 0);
        int dislikes = vidProp.getOrDefault("dislikes", 0);

        if(views < 0 || likes < 0 || dislikes < 0){
            throw new RuntimeException("VIEWS VALUE CANNOT BE NEGATIVE!");
        }

        try{
            vidProp.put("views", views + (int) (paramsFromLine.get("views")));
            vidProp.put("likes", likes + (int) (paramsFromLine.get("likes")));
            vidProp.put("dislikes", dislikes + (int) (paramsFromLine.get("dislikes")));
        }catch (RuntimeException e){
            System.err.println("VIEWS/LIKES/DISLIKES SHOULD BE INTEGERS!");
            e.printStackTrace();
        }



        calculatePopularityScore();
        ctx.setCurrentState(updateState());

        res.save(name.name() + "__POPULARITY_SCORE_UPDATE::" + ctx.getPopularityScore());
    }

    public int calculateVideoPopularity(Map<String, Integer> video) {
        return video.getOrDefault("views", 0) + (
                2 * (video.getOrDefault("likes", 0) - (video.getOrDefault("dislikes", 0))
                ));
    }

    public void calculatePopularityScore() {
        int totalPopScore = 0;
        for (Map<String, Integer> video : ctx.getVideos().values()) {
            totalPopScore += calculateVideoPopularity(video);
        }
        ctx.setPopularityScore(totalPopScore * 1.0 / ctx.getVideos().size());
    }

    /**
     *
     * @exception  Channel belongs in no state.
     */

    @Override
    public StateName updateState() {
        double ps = ctx.getPopularityScore();
        if (ps >= 0 && ps <= 1000) {
            return StateName.UNPOPULAR;
        }
        if (ps > 1000 && ps <= 10000) {
            return StateName.MILDLY_POPULAR;
        }
        if (ps > 10000 && ps <= 100000) {
            return StateName.HIGHLY_POPULAR;
        }
        if (ps > 100000) {
            return StateName.ULTRA_POPULAR;
        }
        throw new RuntimeException("CHANNEL BELONGS IN NO STATE");
    }

    /**
     *
     * @param params
     * @exception  Lenght is negative.
     */

    @Override
    public void adRequest(Map<String, Object> params) {
        int len = (int) params.get("len");
        if(len < 0){
            throw new RuntimeException("LENGTH IS NEGATIVE!");
        }
        boolean isApproved = this.approveAd(len);
        res.save(name.name() + "__AD_REQUEST::" + (isApproved ? "APPROVED" : "REJECTED"));
    }


}

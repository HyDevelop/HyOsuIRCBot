package cc.moecraft.irc.osubot.osu.data.web;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class WebsiteUserData
{
    @SerializedName("id")
    @Expose
    public Long id;

    @SerializedName("username")
    @Expose
    public String username;

    @SerializedName("join_date")
    @Expose
    public String joinDate;

    @SerializedName("country")
    @Expose
    public Country country;

    @SerializedName("age")
    @Expose
    public Long age;

    @SerializedName("avatar_url")
    @Expose
    public String avatarUrl;

    @SerializedName("is_admin")
    @Expose
    public Boolean isAdmin;

    @SerializedName("is_supporter")
    @Expose
    public Boolean isSupporter;

    @SerializedName("is_gmt")
    @Expose
    public Boolean isGmt;

    @SerializedName("is_qat")
    @Expose
    public Boolean isQat;

    @SerializedName("is_bng")
    @Expose
    public Boolean isBng;

    @SerializedName("is_bot")
    @Expose
    public Boolean isBot;

    @SerializedName("is_active")
    @Expose
    public Boolean isActive;

    @SerializedName("interests")
    @Expose
    public Object interests;

    @SerializedName("occupation")
    @Expose
    public Object occupation;

    @SerializedName("title")
    @Expose
    public Object title;

    @SerializedName("location")
    @Expose
    public Object location;

    @SerializedName("lastvisit")
    @Expose
    public String lastvisit;

    @SerializedName("twitter")
    @Expose
    public String twitter;

    @SerializedName("lastfm")
    @Expose
    public Object lastfm;

    @SerializedName("skype")
    @Expose
    public Object skype;

    @SerializedName("website")
    @Expose
    public String website;

    @SerializedName("discord")
    @Expose
    public Object discord;

    @SerializedName("playstyle")
    @Expose
    public List<String> playstyle = null;

    @SerializedName("playmode")
    @Expose
    public String playmode;

    @SerializedName("post_count")
    @Expose
    public Long postCount;

    @SerializedName("profile_colour")
    @Expose
    public Object profileColour;

    @SerializedName("profile_order")
    @Expose
    public List<String> profileOrder = null;

    @SerializedName("cover_url")
    @Expose
    public String coverUrl;

    @SerializedName("cover")
    @Expose
    public Cover cover;

    @SerializedName("kudosu")
    @Expose
    public Kudosu kudosu;

    @SerializedName("max_friends")
    @Expose
    public Long maxFriends;

    @SerializedName("account_history")
    @Expose
    public List<Object> accountHistory = null;

    @SerializedName("active_tournament_banner")
    @Expose
    public List<Object> activeTournamentBanner = null;

    @SerializedName("badges")
    @Expose
    public List<Object> badges = null;

    @SerializedName("favourite_beatmapset_count")
    @Expose
    public List<Long> favouriteBeatmapsetCount = null;

    @SerializedName("follower_count")
    @Expose
    public List<Long> followerCount = null;

    @SerializedName("graveyard_beatmapset_count")
    @Expose
    public List<Long> graveyardBeatmapsetCount = null;

    @SerializedName("monthly_playcounts")
    @Expose
    public List<MonthlyPlaycount> monthlyPlaycounts = null;

    @SerializedName("page")
    @Expose
    public Page page;

    @SerializedName("previous_usernames")
    @Expose
    public List<String> previousUsernames = null;

    @SerializedName("ranked_and_approved_beatmapset_count")
    @Expose
    public List<Long> rankedAndApprovedBeatmapsetCount = null;

    @SerializedName("replays_watched_counts")
    @Expose
    public List<ReplaysWatchedCount> replaysWatchedCounts = null;

    @SerializedName("scores_first_count")
    @Expose
    public List<Long> scoresFirstCount = null;

    @SerializedName("statistics")
    @Expose
    public Statistics statistics;

    @SerializedName("unranked_beatmapset_count")
    @Expose
    public List<Long> unrankedBeatmapsetCount = null;

    @SerializedName("user_achievements")
    @Expose
    public List<UserAchievement> userAchievements = null;

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class Cover
    {
        @SerializedName("custom_url")
        @Expose
        public String customUrl;

        @SerializedName("url")
        @Expose
        public String url;

        @SerializedName("id")
        @Expose
        public Object id;
    }

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class Country
    {
        @SerializedName("code")
        @Expose
        public String code;

        @SerializedName("name")
        @Expose
        public String name;
    }

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class GradeCounts
    {
        @SerializedName("ss")
        @Expose
        public Long ss;

        @SerializedName("ssh")
        @Expose
        public Long ssh;

        @SerializedName("s")
        @Expose
        public Long s;

        @SerializedName("sh")
        @Expose
        public Long sh;

        @SerializedName("a")
        @Expose
        public Long a;
    }

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class Kudosu
    {
        @SerializedName("total")
        @Expose
        public Long total;

        @SerializedName("available")
        @Expose
        public Long available;
    }

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class Level
    {
        @SerializedName("current")
        @Expose
        public Long current;

        @SerializedName("progress")
        @Expose
        public Long progress;
    }

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class MonthlyPlaycount
    {
        @SerializedName("start_date")
        @Expose
        public String startDate;

        @SerializedName("count")
        @Expose
        public Long count;
    }

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class Page
    {
        @SerializedName("html")
        @Expose
        public String html;

        @SerializedName("raw")
        @Expose
        public String raw;
    }

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class Rank
    {
        @SerializedName("global")
        @Expose
        public Long global;

        @SerializedName("country")
        @Expose
        public Long country;
    }

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class ReplaysWatchedCount
    {
        @SerializedName("start_date")
        @Expose
        public String startDate;

        @SerializedName("count")
        @Expose
        public Long count;
    }

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class ScoreRanks
    {
        @SerializedName("XH")
        @Expose
        public Long xH;

        @SerializedName("SH")
        @Expose
        public Long sH;

        @SerializedName("X")
        @Expose
        public Long x;

        @SerializedName("S")
        @Expose
        public Long s;

        @SerializedName("A")
        @Expose
        public Long a;
    }

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class Statistics
    {
        @SerializedName("level")
        @Expose
        public Level level;

        @SerializedName("pp")
        @Expose
        public Double pp;

        @SerializedName("pp_rank")
        @Expose
        public Long ppRank;

        @SerializedName("ranked_score")
        @Expose
        public Long rankedScore;

        @SerializedName("hit_accuracy")
        @Expose
        public Double hitAccuracy;

        @SerializedName("play_count")
        @Expose
        public Long playCount;

        @SerializedName("play_time")
        @Expose
        public Long playTime;

        @SerializedName("total_score")
        @Expose
        public Long totalScore;

        @SerializedName("total_hits")
        @Expose
        public Long totalHits;

        @SerializedName("maximum_combo")
        @Expose
        public Long maximumCombo;

        @SerializedName("replays_watched_by_others")
        @Expose
        public Long replaysWatchedByOthers;

        @SerializedName("is_ranked")
        @Expose
        public Boolean isRanked;

        @SerializedName("grade_counts")
        @Expose
        public GradeCounts gradeCounts;

        @SerializedName("rank")
        @Expose
        public Rank rank;

        @SerializedName("scoreRanks")
        @Expose
        public ScoreRanks scoreRanks;
    }

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class UserAchievement
    {
        @SerializedName("achieved_at")
        @Expose
        public String achievedAt;

        @SerializedName("achievement_id")
        @Expose
        public Long achievementId;
    }
}

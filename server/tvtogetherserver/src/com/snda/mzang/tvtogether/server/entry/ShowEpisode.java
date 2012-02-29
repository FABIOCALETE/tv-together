package com.snda.mzang.tvtogether.server.entry;

public class ShowEpisode {
	// 直接取自己的
	String id;
	String seriesDescripton;

	// 直接取show的
	String showId;
	Integer totalEpisode;

	// 优先取自己的，自己的为空则取show的
	String episodeName;
	String episodeDescription;
	String episodeImage;
	Integer duration;

}

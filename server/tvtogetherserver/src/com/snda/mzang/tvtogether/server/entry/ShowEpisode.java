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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSeriesDescripton() {
		return seriesDescripton;
	}

	public void setSeriesDescripton(String seriesDescripton) {
		this.seriesDescripton = seriesDescripton;
	}

	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
	}

	public Integer getTotalEpisode() {
		return totalEpisode;
	}

	public void setTotalEpisode(Integer totalEpisode) {
		this.totalEpisode = totalEpisode;
	}

	public String getEpisodeName() {
		return episodeName;
	}

	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}

	public String getEpisodeDescription() {
		return episodeDescription;
	}

	public void setEpisodeDescription(String episodeDescription) {
		this.episodeDescription = episodeDescription;
	}

	public String getEpisodeImage() {
		return episodeImage;
	}

	public void setEpisodeImage(String episodeImage) {
		this.episodeImage = episodeImage;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

}

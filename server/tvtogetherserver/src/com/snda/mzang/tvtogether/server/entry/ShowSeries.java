package com.snda.mzang.tvtogether.server.entry;

public class ShowSeries {

	String id;
	String showName;
	String description;
	Integer totalEpisode;

	Integer duration;

	String showImage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTotalEpisode() {
		return totalEpisode;
	}

	public void setTotalEpisode(Integer totalEpisode) {
		this.totalEpisode = totalEpisode;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getShowImage() {
		return showImage;
	}

	public void setShowImage(String showImage) {
		this.showImage = showImage;
	}

}

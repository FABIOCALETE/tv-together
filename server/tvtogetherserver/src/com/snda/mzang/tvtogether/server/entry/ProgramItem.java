package com.snda.mzang.tvtogether.server.entry;

import java.util.Date;

public class ProgramItem {
	String id;
	String channelId;
	Date forDay;
	Date startTime;

	String showEpisodeId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Date getForDay() {
		return forDay;
	}

	public void setForDay(Date forDay) {
		this.forDay = forDay;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getShowEpisodeId() {
		return showEpisodeId;
	}

	public void setShowEpisodeId(String showEpisodeId) {
		this.showEpisodeId = showEpisodeId;
	}

}

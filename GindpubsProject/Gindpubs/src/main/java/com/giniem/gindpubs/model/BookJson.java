package com.giniem.gindpubs.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BookJson {

	private int hpub;

	private String magazineName;
	
	private String title;

	private List<String> authors;

	private List<String> creators;

	private Date date;

	private String url;

	private String cover;

	private String orientation;

	private boolean zoomable;

	private String background;

	private boolean verticalBounce;

	private int indexHeight;

	private boolean mediaDisplay;

	private String pageNumberColors;

	private String rendering;

	private boolean pageTurnTap;

	private List<String> contents;
	
	public BookJson() {
		this.hpub = 0;
		this.date = new Date();
		this.authors = new ArrayList<String>();
		this.creators = new ArrayList<String>();
		this.contents = new ArrayList<String>();
		this.title = "";
		this.url = "";
		this.cover = "";
		this.orientation = "";
		this.zoomable = false;
		this.background = "";
		this.verticalBounce = false;
		this.indexHeight = 0;
		this.mediaDisplay = false;
		this.pageNumberColors = "";
		this.rendering = "";
		this.pageTurnTap = false;
	}

	public int getHpub() {
		return hpub;
	}

	public void setHpub(int hpub) {
		this.hpub = hpub;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public boolean isZoomable() {
		return zoomable;
	}

	public void setZoomable(boolean zoomable) {
		this.zoomable = zoomable;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public boolean isVerticalBounce() {
		return verticalBounce;
	}

	public void setVerticalBounce(boolean verticalBounce) {
		this.verticalBounce = verticalBounce;
	}

	public int getIndexHeight() {
		return indexHeight;
	}

	public void setIndexHeight(int indexHeight) {
		this.indexHeight = indexHeight;
	}

	public boolean isMediaDisplay() {
		return mediaDisplay;
	}

	public void setMediaDisplay(boolean mediaDisplay) {
		this.mediaDisplay = mediaDisplay;
	}

	public String getPageNumberColors() {
		return pageNumberColors;
	}

	public void setPageNumberColors(String pageNumberColors) {
		this.pageNumberColors = pageNumberColors;
	}

	public String getRendering() {
		return rendering;
	}

	public void setRendering(String rendering) {
		this.rendering = rendering;
	}

	public boolean isPageTurnTap() {
		return pageTurnTap;
	}

	public void setPageTurnTap(boolean pageTurnTap) {
		this.pageTurnTap = pageTurnTap;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public List<String> getCreators() {
		return creators;
	}

	public void setCreators(List<String> creators) {
		this.creators = creators;
	}

	public List<String> getContents() {
		return contents;
	}

	public void setContents(List<String> contents) {
		this.contents = contents;
	}

	public String getMagazineName() {
		return magazineName;
	}

	public void setMagazineName(String magazineName) {
		this.magazineName = magazineName;
	}

	public void fromJson(final String jsonString) throws JSONException,
			ParseException {
		JSONObject json = new JSONObject(jsonString);
//		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd",
//				Locale.US);

		// The other properties are commented by now, as we are not gonna use them yet.
		
//		this.hpub = json.getInt("hpub");
//		this.title = json.getString("title");
//		this.date = sdfInput.parse(json.getString("date"));
//		this.url = json.getString("url");
//		this.cover = json.getString("cover");
		this.orientation = json.getString("orientation");
//		this.zoomable = json.getBoolean("zoomable");
//		this.background = json.getString("-baker-background");
//		this.verticalBounce = json.getBoolean("-baker-vertical-bounce");
//		this.indexHeight = json.getInt("-baker-index-height");
//		this.mediaDisplay = json.getBoolean("-baker-media-autoplay");
//		this.pageNumberColors = json.getString("-baker-page-numbers-color");
//		this.rendering = json.getString("-baker-rendering");
//		this.pageTurnTap = json.getBoolean("-baker-page-turn-tap");

//		JSONArray authors = new JSONArray(json.getString("author"));
//		JSONArray creators = new JSONArray(json.getString("creator"));
		JSONArray contents = new JSONArray(json.getString("contents"));
//		this.authors = new ArrayList<String>();
//		this.creators = new ArrayList<String>();
		this.contents = new ArrayList<String>();

//		for (int i = 0; i < authors.length(); i++) {
//			this.authors.add(authors.getString(i));
//		}
//
//		for (int i = 0; i < creators.length(); i++) {
//			this.creators.add(creators.getString(i));
//		}

		for (int i = 0; i < contents.length(); i++) {
			this.contents.add(contents.getString(i));
		}
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject result = new JSONObject();
		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd",
				Locale.US);
		
		result.put("hpub", this.hpub);
		result.put("title", this.title);
		result.put("date", sdfInput.format(this.date));
		result.put("url", this.url);
		result.put("cover", this.cover);
		result.put("orientation", this.orientation);
		result.put("zoomable", this.zoomable);
		result.put("-baker-background", this.background);
		result.put("-baker-vertical-bounce", this.verticalBounce);
		result.put("-baker-index-height", this.indexHeight);
		result.put("-baker-media-autoplay", this.mediaDisplay);
		result.put("-baker-page-numbers-color", this.pageNumberColors);
		result.put("-baker-rendering", this.rendering);
		result.put("-baker-page-turn-tap", this.pageTurnTap);
		
		JSONArray authors = new JSONArray();
		JSONArray creators = new JSONArray();
		JSONArray contents = new JSONArray();
		
		for (String author : this.authors) {
			authors.put(author);
		}
		result.put("author", authors);
		
		for (String creator : this.creators) {
			creators.put(creator);
		}
		result.put("creator", creators);
		
		for (String content : this.contents) {
			contents.put(content);
		}
		result.put("contents", contents);
		
		return result;
	}
}

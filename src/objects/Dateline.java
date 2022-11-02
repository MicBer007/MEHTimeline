package objects;

import positioning.DatelinePositioning;
import settings.Settings;
import timeline.Camera;

public class Dateline {
	
	private DatelinePositioning positioning;
	
	public Dateline(Camera camera) {
		positioning = new DatelinePositioning(camera, Settings.DATELINE_DATES);
	}

	public DatelinePositioning getPositioning() {
		return positioning;
	}

}

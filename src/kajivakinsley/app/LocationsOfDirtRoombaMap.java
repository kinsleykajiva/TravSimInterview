package kajivakinsley.app;

import java.awt.*;

import static kajivakinsley.app.MapTile.TILE_DIRT;
import static kajivakinsley.app.MapTile.TILE_EMPTY;
import static kajivakinsley.app.gui.HomeController.cleanedAreas;

public class LocationsOfDirtRoombaMap  {

	private OnUpdateImpl onUpdate;

	private MapTile[][] map;
	//private Dimensions dimensions = null;

	/**
	 * Constructor
	 *
	 * @param dimensions  Dimensions of the map
	 */
	public LocationsOfDirtRoombaMap (Dimensions dimensions ,OnUpdateImpl onUpdate) {
		//this.dimensions = dimensions;
		this.onUpdate = onUpdate;
		map = new MapTile[dimensions.x][dimensions.y];

		// Initialise map
		for (int x = 0; x < dimensions.x; x ++) {
			for (int y = 0; y < dimensions.y; y++) {
				map[x][y] = TILE_EMPTY;
			}
		}
	}

	/**
	 * Adds the dirt to a certain map tile.
	 *
	 * @param  point       Coordinates of the dirt


	 */
	public void applyDirt(Point point){
		applyDirt(point.x, point.y);
	}
	  /* Adds the dirt to a certain map coordinates
     *
		     * @param x  The x coordinate
     * @param y  The y coordinate
     */
	public void applyDirt(int x, int y){
		//cleanedAreas.add ("Creating Dirt at" + x+"x ."+y+"y" );
		System.out.println (x+"x ::Dirting on:: y"+y);
		map[x][y] = TILE_DIRT;
	}

	/**
	 * Checks if a certain tile is dirty
	 *
	 * @param  position  Position to check
	 * @return boolean   Is dirty or not
	 */
	public boolean hasDirt(Point position) {
		boolean hasDirt = false;

		if (map[position.x][position.y] == TILE_DIRT) {
			map[position.x][position.y] = TILE_EMPTY;
			onUpdate.onFoundDirt ("has dirt at " +position);
			hasDirt = true;
		}
		return hasDirt;
	}
}

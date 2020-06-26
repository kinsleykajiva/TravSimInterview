package kajivakinsley.app;

import java.awt.*;
import java.util.*;
import java.util.List;

import static kajivakinsley.app.DriverMainClass.STEPS_TAKEN;
import static kajivakinsley.app.gui.HomeController.cleanedAreas;
import static kajivakinsley.app.gui.HomeController.dirtAreasList;

public class Loader implements OnUpdateImpl{



    private Dimensions dimensions = null;
    private RoomBaMachineLoocationPosition roomBaMachineLoocationPosition = null;
    private LocationsOfDirtRoombaMap locationsOfDirtRoombaMap = null;
    private DrivingInstructions drivingInstructions = null;

    public List <Point> dirtyAreas(int number){
        Set <Point> ret = new HashSet <>  ();
        for (int x = 1 ; x < 9 ; x++) {
            for (int y = 1 ; y < 9 ; y++) {
                //System.out.println (x + " x:::: y" + y);
                Point dirt = processDirt (x ,y);
                ret.add (dirt);
            }

        }
        List <Point> retVal = new ArrayList <>  ();
        retVal.addAll (ret);
        List <Point> retVal2 = new ArrayList <>  ();
        int size = retVal.size ();
     //   System.out.println (size);
        // System.exit (0);
        for (int i = 1 ; i < number +1; i++) {
            int isize = retVal.size ();
           int pos = RandomInt2(  0,size-1);
            Point ran= retVal.get (pos );
            retVal2.add (ran);
            retVal.remove (pos);
            size--;
        }

        System.out.println (retVal2.size ());
       // System.exit (0);

        //:ToDo retun the roomba to the x1,y1 position where it was in the room
        // Remember we will need to return to the original Position
        dirtAreasList .add ("Generated " + number + "Random Tiles");
      /*  retVal2.stream ().forEach (e->
                 //                          dirtAreasList .add ("Random tile " + e.x + "x ::: y"+e.y)
                                         //  System.out.println (e.x + "x ::: y"+e.y )
        );*/


    return retVal2;

    }

    public static int RandomInt2 (  int min , int max ) {

        return new Random ().nextInt((max - min ) +1 ) ;
    }
    public static int RandomInt ( int min , int max ) {
        int bount = (( max - min ) + 1 ) < 0 ? 1 :( max - min ) + 1 ;
        bount = bount <= 0 ? 1 : bount;
        return new Random ().nextInt(bount ) + min;
    }

    void loadInits(){
        // we map the room dimensions
         dimensions = new Dimensions (processPoint(8+1,8+1));
        // Initialise the map according to the dimensions
        locationsOfDirtRoombaMap = new LocationsOfDirtRoombaMap (dimensions,this);
        cleanedAreas.add ("Created Dirt To Tiles" );

        /*current position of the Roomba Robot */
         roomBaMachineLoocationPosition = new RoomBaMachineLoocationPosition (1, 1, dimensions);

        dirtyAreas(30).forEach (e-> {
                                    locationsOfDirtRoombaMap.applyDirt ( e );
                                 var now = new RoomBaMachineLoocationPosition (e.x, e.y, dimensions);
            int stepTo = (int) Math.floor (now.distance (roomBaMachineLoocationPosition));
            STEPS_TAKEN=STEPS_TAKEN+stepTo;

                                }
        );

        // driving instructions
         drivingInstructions = processDrivingInstructions ("NNESEESWNWW");
  // create dirts

/*
        Point dirt = processDirt (1,3);
        map.applyDirt (dirt);

        Point dirt2 = processDirt (3,5);
        map.applyDirt (dirt2);*/
    }

    Point processPoint(int x_pos ,int y_pos){
        return new Point (x_pos,y_pos);
    }
    /**
     * Method to process our dirt.
     *

     * @return Point          Dirt converted to a point

     *                        coordinates
     */
    public Point processDirt(int x_po,int y_pos)  {
        Point dirtPoint = null;


            dirtPoint  = processPoint(x_po, y_pos);

        return dirtPoint;
    }
    /**
     * Method to process our driving instructions.
     *
     * @param  line           Driving instructions as a string

     * @return Point          Driving instructions converted to a point

     *                        characters
     */
    public DrivingInstructions processDrivingInstructions(String line)
            {
                DrivingInstructions drivingInstructions = new DrivingInstructions (line);

        return drivingInstructions;
    }

    /**
     * Getter for dimensions.
     * @return Dimensions   The dimensions of the map
     */
    public Dimensions getDimensions() {
        return dimensions;
    }

    /**
     * Getter for position.
     * @return Position  The position of the robot
     */
    public RoomBaMachineLoocationPosition getRoomBaMachineLoocationPosition () {
        return roomBaMachineLoocationPosition;
    }

    /**
     * Getter for the map
     * @return Map  Our map
     */
    public LocationsOfDirtRoombaMap getLocationsOfDirtRoombaMap () {
        return locationsOfDirtRoombaMap;
    }

    /**
     * Getter for the driving instructions
     * @return DrivingInstructions  Our driving instructions
     */
    public DrivingInstructions getDrivingInstructions() {
        return drivingInstructions;
    }

    @Override
    public void onDoneCleaning (String message) {

    }

    @Override
    public void onFoundDirt (String message) {

    }

    @Override
    public void onMoving (String messasge) {

    }

    @Override
    public void onDoneWithRoom (String message) {

    }
}

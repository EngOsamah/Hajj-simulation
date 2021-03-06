import java.util.ArrayList;
import java.util.Date;

public class Campaign {

    private String UID;
    //private int housingNumber;
    //private String name;
    private District hotelDistrict;

    private Route housingToDestRoute;
    private Route destToHousingRoute;

    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    private Date timeToLeaveToDest;
    private Date timeToLeaveToHousing;

    private boolean startedMoving;

    private static int numeberOfCampains;

    public Campaign(District hotelDistrict, int numberofBusses) {
        this.hotelDistrict = hotelDistrict;
        generateBusses(numberofBusses);
        generateUID();
    }

    public Campaign(District hotelDistrict, ArrayList<Vehicle> vehicles) {
        this.hotelDistrict = hotelDistrict;
        setVehicles(vehicles);
        generateUID();
    }

    public Route getHousingToDestRoute() {
        return housingToDestRoute;
    }

    public void setHousingToDestRoute(Route housingToDestRoute) {
        this.housingToDestRoute = housingToDestRoute;
    }

    public Route getDestToHousingRoute() {
        return destToHousingRoute;
    }

    public void setDestToHousingRoute(Route destToHousingRoute) {
        this.destToHousingRoute = destToHousingRoute;
        for(Vehicle vehicle : this.getVehicles()){
            vehicle.setRoute(destToHousingRoute);
        }
    }

    public District getHotelDistrict(){ return this.hotelDistrict; }

    public Date getTimeToLeaveToDest() {
        return timeToLeaveToDest;
    }

    public void setTimeToLeaveToDest(Date timeToLeaveToDest) throws OutOfSimulationTimeException {
        if(PDate.isWithInTimeline(timeToLeaveToDest, MakkahCity.getTimeManager()))
            this.timeToLeaveToDest = timeToLeaveToDest;
        else throw new OutOfSimulationTimeException();
    }

    public Date getTimeToLeaveToHousing() {
        return timeToLeaveToHousing;
    }

    public void setTimeToLeaveToHousing(Date timeToLeaveToHousing) throws OutOfSimulationTimeException {
        if(PDate.isWithInTimeline(timeToLeaveToHousing, MakkahCity.getTimeManager()))
            this.timeToLeaveToHousing = timeToLeaveToHousing;
        else throw new OutOfSimulationTimeException();
    }

    public int getNumberOfBusses() {
        int busses = 0;
        for (Vehicle vehicle : vehicles){
            if (vehicle instanceof Bus) busses++;
        }
        return busses;
    }

    public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<Vehicle> vehicles) {
		if (vehicles != null)
		this.vehicles = vehicles;
	}

	public void setStartedMoving(){
        this.startedMoving = true;
    }

    private void generateBusses(int number){
    	for (int i = 1; i <= number; i++) {
    		vehicles.add(new Bus());
    	}
    }

    private void generateUID() {
        numeberOfCampains++;
        this.UID = String.format("CAMP%04d", numeberOfCampains);
    }

    public String getUID(){
        return this.UID;
    }
}

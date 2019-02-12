/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.model;

// line 40 "../../../../../BTMS.ump"
public class RouteAssignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //RouteAssignment Associations
  private BusVehicle bus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RouteAssignment(BusVehicle aBus)
  {
    boolean didAddBus = setBus(aBus);
    if (!didAddBus)
    {
      throw new RuntimeException("Unable to create routeAssignment due to bus");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public BusVehicle getBus()
  {
    return bus;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBus(BusVehicle aBus)
  {
    boolean wasSet = false;
    if (aBus == null)
    {
      return wasSet;
    }

    BusVehicle existingBus = bus;
    bus = aBus;
    if (existingBus != null && !existingBus.equals(aBus))
    {
      existingBus.removeRouteAssignment(this);
    }
    bus.addRouteAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    BusVehicle placeholderBus = bus;
    this.bus = null;
    if(placeholderBus != null)
    {
      placeholderBus.removeRouteAssignment(this);
    }
  }

}
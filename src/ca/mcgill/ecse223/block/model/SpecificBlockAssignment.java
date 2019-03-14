/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 19 "../../../../../Block223PlayGame.ump"
public class SpecificBlockAssignment
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Autounique Attributes
  private int id;

  //SpecificBlockAssignment Associations
  private BlockAssignment blockAssignment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificBlockAssignment(BlockAssignment aBlockAssignment)
  {
    id = nextId++;
    boolean didAddBlockAssignment = setBlockAssignment(aBlockAssignment);
    if (!didAddBlockAssignment)
    {
      throw new RuntimeException("Unable to create specificBlockAssignment due to blockAssignment");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetOne */
  public BlockAssignment getBlockAssignment()
  {
    return blockAssignment;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlockAssignment(BlockAssignment aBlockAssignment)
  {
    boolean wasSet = false;
    if (aBlockAssignment == null)
    {
      return wasSet;
    }

    BlockAssignment existingBlockAssignment = blockAssignment;
    blockAssignment = aBlockAssignment;
    if (existingBlockAssignment != null && !existingBlockAssignment.equals(aBlockAssignment))
    {
      existingBlockAssignment.removeSpecificBlockAssignment(this);
    }
    blockAssignment.addSpecificBlockAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    BlockAssignment placeholderBlockAssignment = blockAssignment;
    this.blockAssignment = null;
    if(placeholderBlockAssignment != null)
    {
      placeholderBlockAssignment.removeSpecificBlockAssignment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "blockAssignment = "+(getBlockAssignment()!=null?Integer.toHexString(System.identityHashCode(getBlockAssignment())):"null");
  }
}
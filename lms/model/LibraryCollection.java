/** 
Assignment 1, Programming 2, RMIT University. 
Author: Lara Wilkinson s3342496
Date: 04/02/2014

Description:  The library collection is a collection of all the holdings in the 
library. Holdings can be added and removed from a collection and can also be 
searched on for more information, usually by passing the parameter of the
Holding Code. 
**/

package lms.model;

import java.util.*;

public class LibraryCollection {
    
    private String code;
    private String name;
    
    // This initialises the holdings array, which stores the holdings within 
    // the collection
    
    
    private Map<Integer, Holding> holdings = new TreeMap<Integer, Holding>();
    
    
    public LibraryCollection(String collectionCode, String name) {
        this.code = collectionCode;
        this.name = name;
    }
    
    
    
    /** gets the collection code
     */
    
    public String getCode () {
    	return code;
    }

    
    /** Allows for a holding to be added to the library collection. If a holding
     * is already in the collection, the method will return a false value **/
    
    public boolean addHolding(Holding holding)  {
    
        // Check to see if the collection already contains the holding using the
        // code as the key
        if (holdings.containsKey(holding.getCode())) {
            return false;
        }
        
        // Add to the collection with the code as the key and the holding as the
        // value
        else {
            holdings.put(holding.getCode(), holding);
            return true;
        }
        
    }
    
    // Returns all holdings in the collection
    
    public Map<Integer, Holding> getAllHoldings() {
            return holdings;
    }
    
    // Returns the holding requested using the holding code from the collection
    
    public Holding getHolding(int code) {
        return holdings.get(code);
    }
    
    // Removes the holding from the collection
    
    public boolean removeHolding(int code)  {
        if (holdings.get(code).isOnLoan()) {
            return false;
        }
        else {
            holdings.remove(code);
            return true;
        }
    }
    
    
    // Provides a string representation of the library collection
    
    public String toString() {
        
        if (holdings.size() != 0) {
            String holdinglist = "";
            
            Integer[] list = 
                    holdings.keySet().toArray(new Integer[holdings.size()]);
            // To get a string of holding codes for a library collection, we
            // will need to iterate through the collection 
            for (int i = 0; i < list.length; i++) {
                holdinglist += list[i] + ",";
            }
            
            return code+":"+name+":" 
                +holdinglist.substring(0, holdinglist.length()-1);
        }
        
        else {
            return code+":"+name+":";
        }
                        
    }
}

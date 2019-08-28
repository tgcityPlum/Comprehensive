package com.tgcity.refreshadapter.entity;

import java.util.List;

/**
 * implement the interface if the item is expandable
 * @author TGCity
 */
public interface IExpandable<T> {

    /**
     * isExpanded
     * @return boolean
     */
    boolean isExpanded();

    /**
     * setExpanded
     * @param expanded boolean
     */
    void setExpanded(boolean expanded);

    /**
     * getSubItems
     * @return List
     */
    List<T> getSubItems();

    /**
     * Get the level of this item. The level start from 0.
     * If you don't care about the level, just return a negative.
     * @return int
     */
    int getLevel();
}

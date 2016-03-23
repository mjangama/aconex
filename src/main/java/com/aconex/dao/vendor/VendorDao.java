package com.aconex.dao.vendor;

import com.aconex.model.vendor.Vendor;

import java.util.List;

/**
 * Created by madhuri on 3/20/16.
 */
public interface VendorDao {

    /**
     * returns all the vendors from the database
     * @return
     */
    public List<Vendor> getVendorList();

}

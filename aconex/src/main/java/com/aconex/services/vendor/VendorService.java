package com.aconex.services.vendor;

import com.aconex.model.vendor.Vendor;

import java.util.List;

/**
 * Created by madhuri on 3/21/16.
 */
public interface VendorService {

    /**
     * returns all the vendors
     * @return
     */
    public List<Vendor> getVendorList();
}

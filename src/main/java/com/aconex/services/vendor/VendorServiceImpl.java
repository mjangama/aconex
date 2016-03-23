package com.aconex.services.vendor;

import com.aconex.dao.vendor.VendorDao;
import com.aconex.model.vendor.Vendor;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by madhuri on 3/21/16.
 */
public class VendorServiceImpl implements VendorService {

    private static Logger logger = Logger.getLogger(VendorServiceImpl.class);

    private VendorDao vendorDao;

    public void setVendorDao(VendorDao projectDao) {
        this.vendorDao = projectDao;
    }

    /**
     * returns all the vendors
     * @return
     */
    public List<Vendor> getVendorList() {
        logger.info("Retrieving list of vendors");
        List<Vendor> vendorList = vendorDao.getVendorList();
        return vendorList;
    }

}

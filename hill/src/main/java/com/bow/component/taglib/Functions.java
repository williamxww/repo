package com.bow.component.taglib;

import org.springframework.util.CollectionUtils;

import com.bow.service.organization.OrganizationService;
import com.bow.service.permission.ResourceService;
import com.bow.utils.common.SpringUtils;

/**
 * 
 * @author vivid
 * 2015-2-2
 */
public class Functions {
	
	/**
	 * check whether the element in iterableCollection
	 * @param iterable
	 * @param element
	 * @return
	 */
    public static boolean in(Iterable iterable, Object element) {
        if(iterable == null) {
            return false;
        }
        return CollectionUtils.contains(iterable.iterator(), element);
    }


    /**
     * -------------------------------------------------
     *      HERE WE CAN GET SOME SERVICES				|
     * -------------------------------------------------
     */
    private static OrganizationService organizationService;
    private static ResourceService resourceService;

    public static OrganizationService getOrganizationService() {
        if(organizationService == null) {
			organizationService = SpringUtils.getApplicationContext().getBean(OrganizationService.class);
        }
        return organizationService;
    }



    public static ResourceService getResourceService() {
        if(resourceService == null) {
			resourceService = SpringUtils.getApplicationContext().getBean(ResourceService.class);
        }
        return resourceService;
    }
}


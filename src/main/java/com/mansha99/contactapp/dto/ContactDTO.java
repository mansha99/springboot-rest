package com.mansha99.contactapp.dto;
import com.mansha99.contactapp.models.Contact;

/**
 *
 * @author shivay
 */
public class ContactDTO  extends BaseDTO{
    private Contact model;

    /**
     * @return the model
     */
    public Contact getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(Contact model) {
        this.model = model;
    }
}

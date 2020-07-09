package org.example.schoology.pages.resources;

import org.example.schoology.pages.SubMenuTemplate;

import java.util.Map;

public class AddTemplatePopup extends AbstractResourcePopup{

    public SubMenuTemplate create(final Map<String, String> datatable) {
        fill(datatable);
        action.click(submitButton);
        return new SubMenuTemplate();
    }
}

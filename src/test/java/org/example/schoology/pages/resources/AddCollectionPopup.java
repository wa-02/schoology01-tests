package org.example.schoology.pages.resources;

import java.util.Map;

public class AddCollectionPopup extends AbstractResourcePopup {

    public Resources create(final Map<String, String> collectionMap) {
        fill(collectionMap);
        action.click(submitButton);
        return new Resources();

    }


}


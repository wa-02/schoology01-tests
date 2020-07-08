package org.example.schoology.pages.resources;

import java.util.Map;

public class AddCollectionPopup extends AbstractCollectionPopup {

    public Resources create(final Map<String, String> collectionMap) {
        fill(collectionMap);
        submitButton.click();
        return new Resources();

    }


}

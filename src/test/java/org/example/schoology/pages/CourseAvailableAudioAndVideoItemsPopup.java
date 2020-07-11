package org.example.schoology.pages;

import org.example.core.ui.AbstractPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class CourseAvailableAudioAndVideoItemsPopup extends AbstractPage {

    // This info should come from config file.
    public static final int DEFAULT_IMPLICIT_TIMEOUT = 15;
    public static final int MIN_IMPLICIT_TIMEOUT = 3;


    @FindBy(css = "._pendo-close-guide")
    private WebElement closeCourseAvailableAudioAndVideoItemsButton;


    public void closeCourseAvailableItemsPopup() {
        try {
            // Changing timeout
            driver.manage().timeouts().implicitlyWait(MIN_IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
            closeCourseAvailableAudioAndVideoItemsButton.click();
        } catch (NoSuchElementException e) {
            // nothing.
        } finally {
            // Restore timeout
            driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        }
    }

}

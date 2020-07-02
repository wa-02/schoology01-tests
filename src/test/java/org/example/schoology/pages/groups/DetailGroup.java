package org.example.schoology.pages.groups;

import org.example.schoology.pages.AbstractDetail;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DetailGroup extends AbstractDetail {

    public static final String XPATH_DESCRIPTION_POST_CREATED = "//p[text()='%s']";

    //@FindBy(xpath = "//body[contains(@class, 'mceContentBody')]/child::p")
    @FindBy(css = "body[class='mceContentBody ']")
    private WebElement postField;


    public void saveDescriptionPost(final String descriptionPost) {
        //wait.until(ExpectedConditions.visibilityOf(postField));
        //(JavascriptExecutor)driver.findElement(By.xpath("//body[contains(@class, 'mceContentBody')]")).se
        //postField.sendKeys(descriptionPost);

        //driver.findElement(By.xpath("//body[contains(@class, 'mceContentBody')]")).click();
        //driver.findElement(By.id("edit-body_ifr")).click();
        //driver.switchTo().activeElement().sendKeys(descriptionPost);
    }

    public String getPostByDescription(final String descriptionPost) {
        return driver.findElement(By.xpath(String.format(XPATH_DESCRIPTION_POST_CREATED, descriptionPost))).getText();
    }

}

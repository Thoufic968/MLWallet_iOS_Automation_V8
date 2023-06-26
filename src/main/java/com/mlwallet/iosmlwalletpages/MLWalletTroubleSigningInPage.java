package com.mlwallet.iosmlwalletpages;

import org.openqa.selenium.By;

public class MLWalletTroubleSigningInPage {

    public static By objTroubleSigningIn = By.xpath("//*[@name='Trouble signing in?']");
    
    public static By objTroubleSingingInPAge = By.xpath("//*[@label='Trouble Signing In']");
    
    public static By objTroubleSigningInBackArrowBtn = By.xpath("//*[@name='']");
    
    public static By objNatureOfRequest = By.xpath("//*[@name='Nature of your request']");
    
    public static By objFullNameField=By.xpath("(//*[XCUIElementTypeOther]/descendant::XCUIElementTypeOther/child::XCUIElementTypeTextField)[1]");
    
    public static By objRegisteredEmail=By.xpath("(//*[XCUIElementTypeOther]/descendant::XCUIElementTypeOther/child::XCUIElementTypeTextField)[2]");
    
    public static By objMobileNumber=By.xpath("(//*[XCUIElementTypeOther]/descendant::XCUIElementTypeOther/child::XCUIElementTypeTextField)[3]");
    
    public static By objClearFormButton = By.xpath("//*[@name='Clear form']");
    
    public static By objClearFormPopup = By.xpath("//*[@name='Clear form?']");
    
    public static By objClearFormPopupMsg = By.xpath("//*[@name='This will remove your answers from all questions and cannot be undone.']");
    
    public static By objCancelBtn = By.xpath("//*[@name='Cancel']");
    
    public static By objNextBtn = By.xpath("//*[@name='Next']");
    
    public static By objRequiredQuestion = By.xpath("//*[@name='This is a required question']");


}
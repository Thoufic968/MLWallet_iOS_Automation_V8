package com.mlwallet.iosmlwalletpages;

import org.openqa.selenium.By;

public class MLWalletRegistration {

    public static By objCreateOne = By.xpath("//*[@name=' Create one']");
    
    public static By objRegistration = By.xpath("//*[@label='Registration Number']");
    
    public static By objRegistrationBackBtn = By.xpath("//*[@name='']");
    
    public static By objOTPPageBackBtn = By.xpath("//*[@name='']");

    public static By objMobileNumberField = By.xpath("//*[@name='9XX XXX XXXX']/XCUIElementTypeTextField");
    
    public static By objConfirm = By.xpath("//*[@label='Confirm']");
    
    public static By objRegistrationPersonalInfo = By.xpath("//*[@label='Registration Personal Info']");
    
    public static By objRegistrationPersonalInfoBackBtn = By.xpath("//*[@name='']");

    public static By objPersonalInfo = By.xpath("//*[@name='LQWMWP']");
    
    public static By objFirstName = By.xpath("//*[@name='5PFQ86']");
    
    public static By objFirstNameErrorMsg = By.xpath("//*[@name='5PFQ86']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText");
    
    public static By objMiddleName = By.xpath("//*[@name='NFEGGM']");
    
    public static By objMiddleNameErrorMsg = By.xpath("//*[@name='NFEGGM']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText");
    
    public static By objLastName = By.xpath("//*[@name='Q1TDKI']");
    
    public static By objLastNameErrorMsg = By.xpath("//*[@name='Q1TDKI']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText");
    
    public static By objBirthDate = By.xpath("//*[@name='0L347S']");
    
    public static By objBirthDateErrorMsg = By.xpath("//*[@name='0L347S']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText");
    
    public static By objYearSelector(String year){
       return By.xpath("//*[@text='"+year+"']");
    }
    public static By objOkBtn = By.xpath("//*[@label='OK']");
    
    public static By objDatePickerYear = By.xpath("//*[@name='Show year picker']");
    
    public static By objEmailAddress = By.xpath("//*[@name='YDP4RE']");
    
    public static By objEmailAddressErrorMsg = By.xpath("//*[@name='YDP4RE']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText");
    
    public static By objReEnterEmailAddress = By.xpath("//*[@name='0VBCUG']");
    
    public static By objReEnterEmailAddressErrorMsg = By.xpath("//*[@name='0VBCUG']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText");
    
    public static By objPlaceOfBirth = By.xpath("//*[@name='SYFG77']");
    
    public static By objPlaceOfBirthErrorMsg = By.xpath("//*[@name='SYFG77']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText");

    public static By objNationality = By.xpath("//*[@name='9SWVJ6']");
    
    public static By objNationalityErrorMsg = By.xpath("//*[@name='9SWVJ6']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText");
    
    public static By objNationalitySearchField = By.xpath("//*[@name='Search']/XCUIElementTypeTextField");
    
    public static By objFilipino = By.xpath("//*[@name='FILIPINO']");
    
    public static By  objCivilStatus = By.xpath("(//*[@name='9SWVJ6'])[3]");
    
    public static By objCivilStatusErrorMsg = By.xpath("(//*[@name='9SWVJ6'])[3]/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText");

    public static By objSingleCivilStatus = By.xpath("(//*[@name='Single'])[2]");

    public static By objGender = By.xpath("(//*[@name='9SWVJ6'])[6]");
    
    public static By objGenderErrorMsg = By.xpath("(//*[@name='9SWVJ6'])[6]/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText");
    
    public static By objMaleGender = By.xpath("(//*[@name='Male'])[2]");
    
    public static By objRegistrationAddress = By.xpath("//*[@text='Registration Address']");
    public static By objRegistrationAddressBackBtn = By.xpath("//*[@text='Registration Address']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");


    public static By objProvince = By.xpath("(//*[@resource-id='D5ZBS3'])[1]");
    public static By ObjProvinceErrorMsg = By.xpath("((//*[@resource-id='D5ZBS3'])[2]/following-sibling::android.view.ViewGroup/child::android.widget.TextView)[1]");
    public static By objProvinceSearchField = By.xpath("//*[@class='android.widget.EditText']");
    public static By objAuroraProvince = By.xpath("//*[@text='AURORA']");


    public static By objCity = By.xpath("(//*[@resource-id='0Y7K8H'])[1]");
    public static By objCityErrorMsg = By.xpath("((//*[@resource-id='0Y7K8H'])[2]/following-sibling::android.view.ViewGroup/child::android.widget.TextView)[1]");
    public static By objMariaAurora = By.xpath("//*[@text='MARIA AURORA']");

    public static By objHouseNo = By.xpath("(//*[@resource-id='SRVX6B'])[1]");
    public static By objHouseNoErrorMsg = By.xpath("//*[@resource-id='SRVX6B']/following-sibling::android.widget.TextView");

    public static By objExistingAccErrorMsg = By.xpath("(//*[@resource-id='something']/following-sibling::android.widget.TextView)[2]");
    public static By objGOBackToLoginScreenBtn = By.xpath("//*[@resource-id='modal-confirm-button']/child::android.widget.TextView");
    public static By objTermsAndCondition = By.xpath("//*[@text='Terms and Conditions']");
    public static By objTermsAndConditionBackBtn = By.xpath("//*[@text='Terms and Conditions']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
}
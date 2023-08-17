package com.mlwallet.iosmlwalletpages;

import org.openqa.selenium.By;

public class MLWalletUseQRPage {

    public static By objUseQR = By.xpath("//*[@name='Use QR']");
    
    public static By objGenerateQRCodeToReceiveMoney = By.xpath("(//*[@name='Show QR Generate QR Code to Receive Money '])[2]");
    
    public static By objReadQRCodeToSendMoney = By.xpath("(//*[@name='Scan QR Read QR Code to Send/Pay Money '])[2]");
    
    public static By objReadQRCodeToSignInToWeb = By.xpath("(//*[@name='Scan QR Read QR Code to Sign In to Web '])[2]");
    
    public static By objUseQROptions = By.xpath("//*[@name='Use QR options']");
    
    public static By objGenerateQR = By.xpath("//*[@name='Generate QR']");
    
    public static By objQR = By.xpath("//*[XCUIElementTypeImage]/ancestor::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeOther");
    
    public static By objReceiverName = By.xpath("(//*[XCUIElementTypeImage]/ancestor::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/descendant::XCUIElementTypeStaticText)[1]");
    
    public static By objReceiverMobileNumber = By.xpath("(//*[XCUIElementTypeImage]/ancestor::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/descendant::XCUIElementTypeStaticText)[2]");

    public static By objAllowCamera = By.xpath("//*[@text='WHILE USING THE APP']");
    
    public static By objInvalidQRCodeMsg = By.xpath("//*[@name='Invalid QR code']");
    
    public static By objOKBtn = By.xpath("//*[@name='Ok' or @name='OK']");
    
    public static By objSuccess = By.xpath("//*[@text='Success']");

    public static By objUseQRBackBtn = By.xpath("//*[@name='']");
    
    public static By objGenerateQRBackBtn = By.xpath("//*[@name='']");

    public static By objReceiverNameInEnterAmount = By.xpath("//*[@name='EE1R6R']");
    
    public static By objReceiverMobileNumberInEnterAmount = By.xpath("//*[@name='6EF7FX']");
    
    public static By objAmountInputField = By.xpath("//*[@name='0.00']/XCUIElementTypeTextField");
    
    public static By objNextBtn = By.xpath("//*[@name='QNIM9U' or @name='Next']");
    
    public static By objAmountRequiredErrorMsg = By.xpath("//*[@name='Amount is required']");
    
    public static By objConfirmDetails = By.xpath("//*[@name='Confirm Details']");
    
    public static By objMLWalletBalance = By.xpath("(//*[@name='GIAOW8'])[1]");
    
    public static By objReceiverNameInConfirmDetails = By.xpath("(//*[@name='Receiver Name'])[3]");

    public static By objReceiverMobileNo= By.xpath("(//*[@name='Receiver Mobile No.'])[3]");
    
    public static By objPaymentMethod = By.xpath("(//*[@name='Payment Method'])[3]");

    public static By objAmount = By.xpath("(//*[@name='Amount to Send'])[3]");

    public static By objServiceFee = By.xpath("(//*[@name='Service Fee'])[3]");

    public static By objTotalAmount = By.xpath("(//*[@name='Total'])[3]");
    
    public static By objCancelTransaction = By.xpath("//*[@name='EX5UCX' or @label='Cancel Transaction']");
    
    public static By objSendPHPBtn = By.xpath("//*[@name='BUEMEL']");

    public static By objCameraPopup = By.xpath("//*[@name='Allow ML Wallet to take pictures and record video?']");
    
    public static By objWhileUsingApp = By.xpath("//*[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']");
    public static By objOnlyThisTime = By.xpath("//*[@resource-id='com.android.permissioncontroller:id/permission_allow_one_time_button']");
    public static By objDenyButton = By.xpath("//*[@text='Deny']");
    public static By objCameraNotAuthorized = By.xpath("//*[@text='Camera not authorized']");
    
    public static By objScanQR = By.xpath("//*[@name='Scan QR']");
    
    public static By objScanQRBackBtn = By.xpath("//*[@name='']");

    public static By objScanQRSuggestion = By.xpath("//*[@name='Make sure that the QR code is within the frame']");
    
    public static By objCameraPermissionRequiredPopup = By.xpath("//*[@resource-id='android:id/alertTitle']");
    public static By objCameraPermissionRequiredMsg = By.xpath("//*[@resource-id='android:id/message']");

}
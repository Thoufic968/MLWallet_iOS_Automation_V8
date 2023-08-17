package com.mlwallet.iosmlwalletpages;

import org.openqa.selenium.By;

public class MLWalletCashOutPage {

    public static By objCashOut = By.xpath("//*[@name='214NQL']");
    
   //======================================== ML Branch ============================================//
    public static By objToAnyMLBranch = By.xpath("(//*[@name='To any ML Branch '])[2]");
    
    public static By objCashOutToBranch = By.xpath("//*[@name='W11SDY']");   //*[@label='Cash Out To Branch']

    public static By objAmountField = By.xpath("//*[@name='0.00']/XCUIElementTypeTextField");

    public static By objNextBtn = By.xpath("//*[@name='ZAU6YS']");
    
    public static By objNextBtnThree = By.xpath("//*[@label='Next']");
    
    public static By objNextBtnFour = By.xpath("(//*[@label='Next'])[2]");
    
    public static By objNextBtn1 = By.xpath("//*[@name='confirm-transaction' or @label='Next']");
    
    public static By objNextBtnTwo = By.xpath("//*[@name='confirm-transaction' or @label='Next']");

    public static By objContinueBtn = By.xpath("//*[@label='Continue']");

    public static By objLocationPermission = By.xpath("//*[@name='Allow Once']");
    
    public static By objLocationPermission1 = By.xpath("//*[@name='Allow Once']");

    public static By objBackToHomeBtn = By.xpath("//*[@label='Back To Home']");
    
  //  public static By objBackToHomeBtn1 = By.xpath("//XCUIElementTypeOther[@name=\"secondary-action\"]");

    public static By objCreatedDate = By.xpath("//*[@name='transaction-date']");

    public static By objReferenceNumber = By.xpath("//*[@name='transaction-code']");

    public static By objReferenceNumberInCashOut = By.xpath("(//*[@name='Reference Number'])[2]");
    
    public static By objReferenceNumberInCashOut1 = By.xpath("//*[@name='Reference Number']/following-sibling::XCUIElementTypeOther");

    public static By objTransactionDetails = By.xpath("//*[@name='Transaction Details']");

   // public static By objBackArrowBtn = By.xpath("//*[@text='Transaction Details']/preceding-sibling::android.view.ViewGroup/child::android.widget.TextView");

    public static By objMaxLimitTxt = By.xpath("(//*[@name='main-modal'])[3]/child::XCUIElementTypeStaticText/following-sibling::XCUIElementTypeStaticText");

    public static By objInsufficientBalance = By.xpath("(//*[@name='main-modal'])[3]/child::XCUIElementTypeStaticText/following-sibling::XCUIElementTypeStaticText");
    
    public static By objOkBtn = By.xpath("//*[@label='Ok']");

    public static By objCashOutPageBackArrowBtn = By.xpath("//*[@name='Cash Out']/preceding-sibling::android.view.ViewGroup/child::android.widget.TextView");

    public static By objCashOutOptionsBackArrowBtn = By.xpath("//*[@name='Cash out / Withdraw']/preceding-sibling::android.view.ViewGroup/child::android.widget.TextView");

//============================================ ML Bank ===================================================================//

    public static By objToAnyBank = By.xpath("(//*[@name='To Any Bank '])[2]");

    public static By BogusBank = By.xpath("(//*[@name='BogusBank'])[2]");
    
    public static By objBogusBank1 = By.xpath("(//*[@name='BogusBank'])[3]");
    
    public static By objBogusbank2 = By.xpath("//*[@label='BogusBank']");

    public static By objBankInformation = By.xpath("//*[@label='Bank Information']");

    public static By objAccountNumberField = By.xpath("//*[@name='Account Number']/XCUIElementTypeTextField");

    public static By objFirstname = By.xpath("//*[@name='Account Holder First Name']/XCUIElementTypeTextField");

    public static By objMiddleName = By.xpath("//*[@name='Account Holder Middle Name']/XCUIElementTypeTextField");

    public static By objLastName = By.xpath("//*[@name='Account Holder Last Name']/XCUIElementTypeTextField");

    public static By objEmailAddress = By.xpath("//*[@name='Account Holder Email Address']/XCUIElementTypeTextField");

    public static By objCheckBox = By.xpath("//*[@name='BFFZ2J']/XCUIElementTypeOther");
    
    public static By objdntHaveMiddleNameText = By.xpath("//*[@name='I legally do not have a middle name']");

    public static By objConfirmBtn = By.xpath("//*[@label='Confirm']");

    public static By objTransactionNo = By.xpath("(//*[@name='Transaction Successful']/following::XCUIElementTypeOther/descendant::XCUIElementTypeStaticText)[2]");

    public static By objTransactionReferenceNo = By.xpath("(//*[@name='Reference Number']/following:: XCUIElementTypeOther/descendant::XCUIElementTypeStaticText)[1]");

    public static By objTransactionSuccessMessage = By.xpath("//*[@name='Transaction Successful']");

    public static By objTransactionReceipt = By.xpath("//*[@name='Transaction Receipt']");

  //  public static By objAccInvalidErrorMsg = By.xpath("(//XCUIElementTypeOther[@name='main-modal'])[3]/child::XCUIElementTypeStaticText/following-sibling::XCUIElementTypeStaticText");
    
    public static By objAccInvalidErrorMsg = By.xpath("(//*[XCUIElementTypeOther]/descendant::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText)[5]");
    
    public static By objOKBtnOne = By.xpath("(//*[@name='Ok'])[2]");

    public static By objDragonPayPopUpMsg = By.xpath("//*[@name='Dragon Pay charges a fee of 35.00 pesos for this transaction. Do you wish to continue with your transaction?']");

    public static By objBankMaxLimitTxt = By.xpath("//*[@name='The maximum Bank Cash-out per transaction set for your verification level is P50,000.00. Please try again.']");
    
    public static By objUpgradeNow = By.xpath("//*[@name='modal-confirm-button']");
     
    public static By objMinimumTransactionErrorMsg = By.xpath("//*[@name='The supplied amount is less than the required minimum transaction limit']");
    
    public static By objOkButtonTwo = By.xpath("//*[@name='modal-confirm-button']");

//  public static By objReceiversName = By.xpath("//*[@text='Review Transaction']");
    
    //======================+Phase2+====================
    
    public static By objSearchBank = By.xpath("//*[@name='Search Bank']/XCUIElementTypeTextField");
    
    public static By objNoRecentTransactionTxt = By.xpath("//*[@name='No Recent Transaction']");
    
    public static By objAmountRequiredErrorMsg = By.xpath("//*[@name='Amount is required']");
    
    public static By objNickName = By.xpath("//*[@name='Nickname']/XCUIElementTypeTextField");
    
    public static By objSaveRecipientBtn = By.xpath("//*[@name='Save Recipient']");
    
    public static By objSavedBackAccount = By.xpath("//*[@label='Saved Bank Accounts']");
    
    public static By objNickNameInSavedBankAcc = By.xpath("((//*[@name='View All'])[1]/following::XCUIElementTypeOther)[8]");
    
    public static By objRecipientExistMsg = By.xpath("//*[@name='Recipient already exists.']");
    
    public static By objOKButton = By.xpath("//*[@name='modal-confirm-button']");
    
    public static By objCashOutWithdraw = By.xpath("//*[@name='Cash out / Withdraw']");
    
    public static By objCashOutOptions = By.xpath("//*[@name='Cash out options']");
    
    public static By objCashOutOngoingTransaction = By.xpath("//*[@name='Ongoing Transactions']");
  
    public static By objCashOutPage = By.xpath("//*[@name='Cash Out']");
    
    public static By ObjCashOutToBranch = By.xpath("//*[@label='Cash Out To Branch']");
    
    public static By objUserName = By.xpath("(//*[@label='Cash Out To Branch']/following-sibling::XCUIElementTypeStaticText)[1]");
    
    public static By objUserMobileNumber = By.xpath("(//*[@label='Cash Out To Branch']/following-sibling::XCUIElementTypeStaticText)[2]");
    
    public static By objMLWalletBalance = By.xpath("//*[@name='3IKXKR']");
    
    public static By objCashOutInstructions=By.xpath("");
    
   //public static By objOKButton=By.xpath("");
    
}

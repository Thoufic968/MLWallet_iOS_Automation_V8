package com.mlwallet.iosmlwalletpages;

import org.openqa.selenium.By;

public class MLWalletCashInBank {

    public static By objCashIn = By.xpath("(//*[@name='Cash In'])[2]");
    
    public static By objCashInPage = By.xpath("//*[@name='Cash In']");

    public static By objMyBankAccount = By.xpath("(//*[@name='TNKQDN'])[1]");

    public static By objSelectABank = By.xpath("(//*[@name='Select a Bank'])[2]");
    
    public static By objSearchBank = By.xpath("//*[@name='Search Bank']/XCUIElementTypeTextField");
    
    public static By objSelectBankBackBtn = By.xpath("//*[@name='']");
    
    public static By objDragonPayBackBtn = By.xpath("//*[@name='']");
    
    public static By objBanks = By.xpath("//*[XCUIElementTypeScrollView]/descendant::XCUIElementTypeOther/child::XCUIElementTypeOther");
    
    public static By objNoRecentTransactionTxt = By.xpath("(//*[@name='No Recent Transaction'])[3]");
    
    public static By objTestBankOnline = By.xpath("(//*[@name='Test Bank Online'])[2]");

    public static By objDragonPay = By.xpath("(//*[@name='Dragon Pay'])[2]");

    public static By objBankCashIn = By.xpath("//*[@name='HBK90S']");

    public static By objAmountEditField = By.xpath("//*[@name='0.00']/XCUIElementTypeTextField");

    public static By objNextBtn = By.xpath("(//*[@name='Next'])[2]");  //(//*[label='Next'])[2] (//*[@name='Next'])[2]
    
    public static By objNextBtn1 = By.xpath("(//*[@label='Next'])[2]");

    public static By objServiceFeeMsg = By.xpath("(//*[@text='PHP']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView)[2]");

    public static By objDragonPayChargesMsg = By.xpath("(//*[XCUIElementTypeOther]/descendant::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText)[2]");

    public static By objContinueBtn = By.xpath("//*[@label='Continue']");

    public static By objReviewTransaction = By.xpath("(//*[@name='Review Transaction'])[2]");

    public static By objBankTransferCutOffTime = By.xpath("//*[@name='BANK TRANSFER CUT-OFF TIME']/following::XCUIElementTypeStaticText");

    public static By objReferenceNumberMsg = By.xpath("(//*[XCUIElementTypeOther]/following::XCUIElementTypeStaticText)[1]");

    public static By objLoginIdTxtField = By.xpath("//*[@name='Login Id']/following::XCUIElementTypeTextField");////*[@type ='XCUIElementTypeTextField']

    public static By objPasswordTxtField = By.xpath("//*[@type ='XCUIElementTypeSecureTextField']");

    public static By objCompleteTransactionBtn = By.xpath("//*[@label='Complete Transaction']");

    public static By objPayBtn = By.xpath("(//*[@name='Pay'])[2]");

    public static By objBankReferenceNumber = By.xpath("//XCUIElementTypeOther[@name='Payment Gateway Result']/XCUIElementTypeOther[4]/XCUIElementTypeTextField");

    public static By objStatus = By.xpath("//*[@name='Payment Gateway Result']/XCUIElementTypeOther[6]/XCUIElementTypeTextField");

    public static By objMessage = By.xpath("//*[XCUIElementTypeOther]/descendant::XCUIElementTypeTextView/child::XCUIElementTypeOther");

    public static By objSuccessMsg = By.xpath("//*[@name='Your payment was successfully processed.']");

    public static By objMinimumTransactionPopupMsg = By.xpath("(//*[XCUIElementTypeOther]/descendant::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText)[2]");
    
    public static By objOkBtn = By.xpath("//*[@label='Ok']");
    
    public static By objOkBtnOne = By.xpath("(//*[@name='Ok'])[2]");
    
    public static By objMaxLimitErrorMsg = By.xpath("//*[@name='The maximum Bank Cash-in per transaction set for your verification level is P50,000.00. Please try again.']");
    
    public static By objUpgradeNowBtn = By.xpath("//*[@label='Upgrade Now']");
    
    public static By objInvalidAmountMsg = By.xpath("//*[@name='The amount should not be less than 1']");
    
    public static By objInvalidDecimalAmountMsg = By.xpath("//*[@name='The amount must be limited to 2 decimal places']");
    
    public static By objCashInOption = By.xpath("//*[@label='Cash In options']");
    
    public static By objBranchName = By.xpath("(//*[@label='ML Branch '])[2]");
    
    public static By objReceiverName = By.xpath("//*[@name='8NNBWZ']");
    
    public static By objBankName = By.xpath("//*[@name='7NECLV']");
    
    public static By objPrincipalAmount = By.xpath("//*[@name='BJBQYQ']");
    
    public static By objServiceFee = By.xpath("//*[@name='FKA4CU']");
    
    public static By objEmail = By.xpath("//*[@name='CVI6QY']");
    
    public static By objReviewTransactionBackBtn = By.xpath("//*[@name='']");
    
    public static By objWebContinueBtn = By.xpath("//*[@name='Continue']");
    
    public static By objPendingStatus = By.xpath("(//*[@name='Recent Transactions']/parent::XCUIElementTypeOther/descendant::XCUIElementTypeOther)[5]");
    
    public static By objCancelStatus = By.xpath("(//*[@name='Recent Transactions']/parent::XCUIElementTypeOther/descendant::XCUIElementTypeOther)[5]");
    
    public static By objCancelBtn = By.xpath("//*[@label='Cancel']");
    
    public static By objInvalidAmountMsg1 = By.xpath("//*[@name='Amount is required']");
    
    public static By objMaxLimitTxt = By.xpath("//*[@name='Bank Cash-in is not allowed for customers at this verification level. Please upgrade your account to use this service.']");
    
    public static By objBankMaxLimitTxt = By.xpath("//*[@name='The maximum Bank Cash-in per transaction set for your verification level is P50,000.00. Please try again.']");
   
    public static By objCashInTransaction = By.xpath("(//*[@name='Recent Transactions']/parent::XCUIElementTypeOther/descendant::XCUIElementTypeOther)[5]");
   
    public static By objInvalidPINMsg = By.xpath("//*[@name='You have entered an invalid PIN. Please try again.']");
    
    public static By objSeconds = By.xpath("//*[@name='Did not receive OTP?']/following-sibling::XCUIElementTypeStaticText");
    
    public static By objPending = By.xpath("(//*[contains(@name,' Pending ')])");
    
    public static By objPendingVerification = By.xpath("(//*[@name='Recent Transactions']/following::XCUIElementTypeOther)[7]");
    
    public static By objTransactionHistoryBackBtn = By.xpath("//*[@name='']");
    
    public static By objAvailableBalance = By.xpath("//*[@label='Available Balance']");
    
    public static By objAnotherAccTxt = By.xpath("//*[@label='Do you have another account?']");


}

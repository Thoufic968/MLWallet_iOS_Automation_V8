package com.mlwallet.iosmlwalletpages;

import org.openqa.selenium.By;

public class SendTransferPage {

    public static By objSendTransferBtn = By.xpath("//*[@name='AY6EU5']");

    public static By objSendMoney = By.xpath("//*[@name='Send Money']");
    
    public static By ObjFavorites = By.xpath("//*[@name='XYD68Y']");
    
    public static By objCancelTransaction = By.xpath("//*[@label='Cancel']");
    
    public static By objCancelTransacttion1 = By.xpath("//*[@name='EX5UCX']");
    
    public static By objSearchFavoriteField = By.xpath("//*[@name='C3B832']");
    
    public static By objAddedFavorite = By.xpath("(//XCUIElementTypeScrollView/descendant::XCUIElementTypeOther)[5]");
    
    public static By objFavoriteReceiver = By.xpath("((//*[XCUIElementTypeScrollView])[2]/descendant::XCUIElementTypeOther)[2]");

    public static By objToAnyMLBranch = By.xpath("(//*[@label='Kwarta Padala To any ML Branch '])[2]");

    public static By objFirstname = By.xpath("//*[@name='First Name']");
    
    public static By objFirstname1 = By.xpath("//*[@name='First name must only contain letters and spaces']/preceding::XCUIElementTypeTextField");
    
    public static By objLastName1  = By.xpath("(//*[@name='Last name must only contain letters and spaces']/preceding::XCUIElementTypeTextField)[3]");

//    public static By objMiddleName = By.xpath("(//*[@class='android.widget.ImageView']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.widget.EditText)[1]");

    public static By objMiddleName = By.xpath("//*[@name='Middle Name']");

    public static By objLastName = By.xpath("//*[@name='Last Name']");

    public static By objMobileNumber = By.xpath("//*[@name='Mobile Number']");
    
    public static By objMobileNumber1 = By.xpath("(//*[@name='Mobile number is invalid']/preceding::XCUIElementTypeTextField)[4]");

    public static By objCheckBox = By.xpath("//*[@name='Receiver legally does not have middle name']/XCUIElementTypeOther");

    public static By objNextBtn = By.xpath("//*[@name='Next']");
    
    public static By objNextBtn2 = By.xpath("//*[@name='V65U0U' or @label='Next' or @name='Next']");
    
    public static By objNextBtn1 = By.xpath("(//*[@label='Next'])[2]");

    public static By objSavedRecipients = By.xpath("//*[@name='M7Q0IV' or @label='Saved Recipients']");

    public static By objKwartaPadala = By.xpath("//*[@name='Kwarta Padala']");
    
    public static By objAmountToSend = By.xpath("//*[@name='Amount to Send']");

    public static By objSelectPaymentMethod = By.xpath("//*[@name='Select Payment Method']");

    public static By objMLWalletBalance = By.xpath("(//*[XCUIElementTypeScrollView]/descendant::XCUIElementTypeOther/descendant::XCUIElementTypeOther)[1]");

    public static By objAmountTxtField = By.xpath("//*[@name='0.00']");

    public static By objConfirmDetails = By.xpath("//*[@name='Confirm Details']");

    public static By objConfirmBtn = By.xpath("(//*[@label='Confirm'])[2]");
    
    public static By objConfirmBtn1 = By.xpath("//*[@label='Confirm']");

    public static By objLocationPermission = By.xpath("//*[@name='Allow Once']");

    public static By objSendMoneySuccessful = By.xpath("//*[@name='Send Money Successful']");

    public static By objPHPAmount = By.xpath("(//*[XCUIElementTypeOther]/descendant::XCUIElementTypeStaticText)[4]");

    public static By objDate = By.xpath("(//*[XCUIElementTypeOther]/descendant::XCUIElementTypeStaticText)[5]");

    public static By objReferenceNumber = By.xpath("(//*[XCUIElementTypeOther]/descendant::XCUIElementTypeStaticText)[7]");
    
    public static By objTransactionDetails = By.xpath("//*[@label='Transaction Details']");
    
    public static By objReceiverName = By.xpath("(//*[@name='Receiver Name'])[3]");
    
    public static By objKwartaPadalaDate = By.xpath("(//*[XCUIElementTypeOther]/descendant::XCUIElementTypeStaticText)[5]");
    
    public static By objReceiverMobileNo = By.xpath("(//*[@name='Receiver Mobile No.'])[3]");
    
    public static By objPaymentMethod = By.xpath("(//*[@name='Payment Method'])[3]");
    
    public static By objAmount = By.xpath("(//*[@name='Amount to Send'])[3]");
    
    public static By objServiceFee = By.xpath("(//*[@name='Service Fee'])[3]");
    
    public static By objTotalAmount = By.xpath("(//*[@name='Total'])[3]");
    
    public static By objNewTransaction = By.xpath("//*[@name='SEQG3Z' or @label='New Transaction']");
    
    public static By objReferenceNumberInTransactionDetails = By.xpath("(//*[@name='Reference Number'])[3]");
    
    public static By objBackToHomeBtn = By.xpath("//*[@name='488LJD' or @label='Back To Home']");
    
    public static By objSelectRecipient = By.xpath("//*[@label='Select Recipient']");

    public static By objSearchRecipient = By.xpath("//*[@name='Search Recipient']/XCUIElementTypeTextField");
    
    public static By objSavedRecipientsList = By.xpath("//XCUIElementTypeScrollView/descendant::XCUIElementTypeOther/descendant::XCUIElementTypeOther");
    
    public static By objNoRecentTransactionTxt = By.xpath("(//*[@name='No Recent Transaction'])[3]");

    public static By objAddRecipient = By.xpath("//*[@name='Add Recipient']");

    public static By objNickName = By.xpath("//*[@name='Nickname']");

    public static  By ObjSaveRecipient = By.xpath("//*[@name='HY30UQ' or @label='Save Recipient']");
    
    public static By objSendWalletOptions =By.xpath("//*[@label='Send Wallet Options']");

//    public static By objSelectLastName(String sLastName,String sFirstName){
//        return By.xpath("//*[contains(@text,'"+sLastName+", "+sFirstName+"')]");
//    }
    public static  By objSelectLastName = By.xpath("(//*[XCUIElementTypeScrollView]/descendant::XCUIElementTypeOther)[3]");
	
//(//XCUIElementTypeOther[@name=""])[4]
////XCUIElementTypeOther[@name=\"\"]
    
//    public static By objBackArrow(int index) {
//    return  By.xpath("(//XCUIElementTypeOther[@name=\"\"])["+index+"]");
//    }
 
    public static By objBackArrow =By.xpath("//*[@name='']");
    
    public static By objBackArrow1 =By.xpath("(//*[@name=''])[4]");
    
    public static By objDeleteRecipient = By.xpath("//*[@label='Delete']");

    public static By objEditRecipient = By.xpath("//*[@label='Edit']");

    public static By objPopupMsg = By.xpath("(//*[XCUIElementTypeOther]/descendant::XCUIElementTypeStaticText)[7]");

    public static By objRemoveBtn = By.xpath("//*[@label='Remove']");

    public static By objContactAlreadyExistMsg = By.xpath("//*[@name='Contact already exists.']");

    public static By objOkBtn = By.xpath("//*[@label='Ok']");

    public static By objEditRecipientLastName = By.xpath("//XCUIElementTypeTextField[@name='QEWPD9']");

    public static By objFirstNameRequiredMsg = By.xpath("//*[@name='First name is required']");

    public static By objMiddleNameRequiredMsg = By.xpath("//*[@name='Middle name is required']");

    public static By objLastNameRequiredMsg = By.xpath("//*[@name='Last name is required']");

    public static By objMobileNumberRequiredMsg = By.xpath("//*[@name='Mobile number is required']");

    public static By objFirstNameErrorMsg = By.xpath("//*[@name='First name must only contain letters and spaces']");

    public static By objMiddleNameErrorMsg = By.xpath("//*[@name='Middle name must only contain letters and spaces']");

    public static By objLastNameErrorMsg = By.xpath("//*[@name='Last name must only contain letters and spaces']");

    public static By objMobileNumberErrorMsg = By.xpath("//*[@name='Mobile number is invalid']");

    public static By objReferenceNumberInTransaction = By.xpath("//*[contains(@text,'KPTN')]");

    public static By objInvalidAmountMsg = By.xpath("//*[@label='The amount should not be less than 1' or @name='The amount should not be less than 1']");

    public static By objInsufficientAmountMsg = By.xpath("//*[@name='There is insufficient balance to proceed with this transaction. Please try again.']");

    public static By objMaxLimitErrorMsg = By.xpath("//*[@name='The maximum Send Money per transaction set for your verification level is P50,000.00. Please try again.']");

//======================================== Send to Wallet User ===================================//

    public static By objToAMLWalletUser = By.xpath("(//*[@label='ML Wallet To a ML Wallet user '])[2]");

    public static By objMobileNumberField = By.xpath("//*[@name='9** **** ****']/XCUIElementTypeTextField");

    public static By objSendPHPBtn = By.xpath("//*[@name='BUEMEL']");

    public static By objSendMoneyMLWallet = By.xpath("//*[@label='Send Money - ML Wallet Successful']");

    public static By objSendMoneyMLWalletDate = By.xpath("//*[@name='AC27GS']");

    public static By objSendMoneyMLWalletPHP = By.xpath("//*[@name='JPKT96']");

    public static By objMLWalletReferenceNumber = By.xpath("(//*[@name='Transaction No.'])[3]");

    public static By objViewAllBtn = By.xpath("//*[@name='0OG887']");
    
    public static By objRecentFavorites = By.xpath("//*[@name='139KTK']");
    
    public static By objReceiver= By.xpath("//*[@label='Receiver']");

    public static By objEllipsisBtn = By.xpath("(//XCUIElementTypeOther[@name=\"\"])[2]");

    public static By objDeleteBtn = By.xpath("//*[@label='Delete']");

    public static By objSaveToMyFavorite = By.xpath("//*[@name='K6F6FC']");

    public static By objAddedToFavoritesMsg = By.xpath("//*[@name='Successfully Added to Favorites']");

    public static By objSelectFavorite = By.xpath("((//*[XCUIElementTypeScrollView])[2]/descendant::XCUIElementTypeOther)[2]");

    public static By objUnRegisteredMobNumber = By.xpath("//*[@name='Receiver not Found!']");

    public static By objFavRemovedMsg = By.xpath("//*[@name='Successfully Removed']");

    public static By objToMLWalletUser = By.xpath("//*[@label='To ML Wallet User']");
    
    public static By objErrorMsg = By.xpath("//*[@name='Send Money is not allowed for customers at this verification level. Please upgrade your account to use this service.']");
    
    public static By objSemiMaxLimit = By.xpath("//*[@name='The maximum Send Money per transaction set for your verification level is P10,000.00. Please try again.']");
    
    public static By objMaxLimitPopUp = By.xpath("//*[@name='The maximum Send Money per transaction set for your verification level is P50,000.00. Please try again.']");
    
    public static By objUpgradeNowBtn = By.xpath("(//*[@label='Upgrade Now'])[2]");
    
    public static By objInvalidPINMsg = By.xpath("//*[@name='You have entered an invalid PIN. Please try again.']");


}

package com.mlwallet.iosmlwalletpages;

import org.openqa.selenium.By;

public class MLWalletPayBillsPage {

//    public static By objPayBills = By.xpath("//*[@text='Pay Bills']");
//
//    public static By objSelectBiller = By.xpath("//*[@text='Select Biller']");
//    public static By objBillers = By.xpath("//*[@text='Billers']");
//
//    public static By objRecentTransactions = By.xpath("//*[@text='Recent Transactions']");
//
//    public static By objSavedBiller = By.xpath("//*[@text='Saved Biller']");
//
//    public static By objCategories = By.xpath("//*[@text='Categories']");
//
//    public static By objAlphabetical = By.xpath("//*[@text='Alphabetical']");
//
//    public static By objBankingAndFinance = By.xpath("//*[@text=' Banking/Finance']");
//
//    public static By objCharityAndReligious = By.xpath("//*[@text='Charity/Religious']");
//
//    public static By objCharityAndReligiousBillers = By.xpath("//*[@text='Charity/Religious']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/child::android.widget.TextView");
//
//    public static By objUtilities = By.xpath("//*[@text='Utilities']");
//
//    public static By objUtilitiesBillers = By.xpath("//*[@text='Utilities']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/child::android.widget.TextView");
//    public static By objOthers = By.xpath("//*[@text='Others']");
//
//    public static By objOthersBillers = By.xpath("//*[@text='Others']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/child::android.widget.TextView");
//
//    public static By objAirAsia = By.xpath("//*[@text='AIR ASIA BILLSPAYMENT']");
//
//    public static By objBillsPayInformation = By.xpath("//*[@text='Bills Pay Information']");
//
//    public static By objBillerNameInBillsPayInformation = By.xpath("//*[@text='Biller']/following-sibling::android.widget.EditText");
//
//    public static By objSearchBiller = By.xpath("//*[@text='Billers']/following-sibling::android.widget.EditText");
//
//    public static By objMisBillsPayBiller = By.xpath("//*[@text='MIS BILLSPAY123456']");
//
//    public static By objAccountNumberField = By.xpath("(//*[@text='Billing Information']/following-sibling::android.widget.EditText)[1]");
//
//    public static By objFirstNameField = By.xpath("(//*[@text='Billing Information']/following-sibling::android.widget.EditText)[2]");
//
//    public static By objMiddleNameField = By.xpath("(//*[@text='Billing Information']/following-sibling::android.widget.EditText)[3]");
//
//    public static By objLastnameField = By.xpath("(//*[@text='Billing Information']/following-sibling::android.widget.EditText)[4]");
//
//    public static By objAmountField = By.xpath("//*[@text='PHP']/following-sibling::android.widget.EditText");
//
//    public static By objConfirmBtn = By.xpath("//*[@text='Confirm']");
//
//    public static By objConfirmDetails = By.xpath("//*[@text='Confirm Details']");
//
//    public static By objInvalidFirstNameMsg = By.xpath("((//*[@text='Billing Information']/following-sibling::android.widget.EditText)[2]/following-sibling::android.widget.TextView)[1]");
//
//    public static By objInvalidSecondNameMsg = By.xpath("((//*[@text='Billing Information']/following-sibling::android.widget.EditText)[2]/following-sibling::android.widget.TextView)[2]");
//
//    public static By objInvalidLastName = By.xpath("((//*[@text='Billing Information']/following-sibling::android.widget.EditText)[2]/following-sibling::android.widget.TextView)[3]");
//
//    public static By objInvalidAmount = By.xpath("//*[@text='PHP']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView");
//
//    public static By objAccountNumberRequiredMsg = By.xpath("((//*[@text='Billing Information']/following-sibling::android.widget.EditText)[1]/following-sibling::android.widget.TextView)[1]");
//    public static By objFirstNameRequiredMsg = By.xpath("((//*[@text='Billing Information']/following-sibling::android.widget.EditText)[1]/following-sibling::android.widget.TextView)[2]");
//    public static By objLastNameRequiredMsg = By.xpath("((//*[@text='Billing Information']/following-sibling::android.widget.EditText)[1]/following-sibling::android.widget.TextView)[3]");
//
//    public static By objBillerName = By.xpath("(//*[@resource-id='Biller'])[2]");
//
//    public static By objAccountName = By.xpath("(//*[@resource-id='Account Name'])[2]");
//
//    public static By objAccountNumber = By.xpath("(//*[@resource-id='Account Number'])[2]");
//
//    public static By objPaymentMethod = By.xpath("(//*[@resource-id='Payment method'])[2]");
//
//    public static By objPayBtn = By.xpath("//*[contains(@text,'Pay P')]");
//
//    public static By objAddBiller = By.xpath("//*[@text='Add Biller']");
//
//    public static By objAddBillers = By.xpath("//*[@text='Add Billers']");
//
//    public static By objAddSeectedBiller = By.xpath("//*[@text='Biller']/preceding-sibling::android.view.ViewGroup");
//
//    public static By objBillerListSearchBiller = By.xpath("//*[@class='android.widget.EditText']");
//
//    public static By objAddAccountNumber = By.xpath("(//*[@class='android.widget.EditText'])[1]");
//    public static By objAddFirstName = By.xpath("(//*[@class='android.widget.EditText'])[2]");
//    public static By objAddMiddleName = By.xpath("(//*[@class='android.widget.EditText'])[3]");
//    public static By objAddLastName = By.xpath("(//*[@class='android.widget.EditText'])[4]");
//    public static By objAddNickName = By.xpath("(//*[@class='android.widget.EditText'])[5]");
//
//    public static By objAddAccountNumberRequiredMsg = By.xpath("(//*[@class='android.widget.EditText'])[1]/following-sibling::android.widget.TextView");
//    public static By objAddFirstNameRequiredMsg = By.xpath("(//*[@class='android.widget.EditText'])[2]/following-sibling::android.widget.TextView");
//    public static By ObjAddLastNameRequiredMsg = By.xpath("(//*[@class='android.widget.EditText'])[3]/following-sibling::android.widget.TextView");
//
//    public static By objProceedBtn = By.xpath("//*[@text='Proceed']");
    
  
    		
    public static By objPayBills = By.xpath("//*[@label='Pay Bills' or @name='2IZN4I']");

    public static By objSelectBiller = By.xpath("//*[@label='Select Biller']");
    
    public static By objBillers = By.xpath("//*[@label='Billers']");
    
    public static By objBiller = By.xpath("//*[@label='Biller ']");
    
    public static By objBillerInformation = By.xpath("//*[@label='Biller Information']");

    public static By objRecentTransactions = By.xpath("//*[@label='Recent Transactions']");

    public static By objSavedBiller = By.xpath("(//*[@name='Saved Biller'])[2]");
    
  //  public static By objSavedBiller1 = By.xpath("//*[@name='Saved Billers']");

    public static By objSavedBillers = By.xpath("//*[@label='Saved Billers']");

    public static By objCategories = By.xpath("//*[@label='Categories']");

   public static By objAlphabetical = By.xpath("//*[@label='Alphabetical']");
    
    public static By objZelement = By.xpath("//*[@name='Categories Alphabetical']/following-sibling::XCUIElementTypeOther/descendant::XCUIElementTypeScrollView/child::XCUIElementTypeOther/child::XCUIElementTypeOther");
    
   // public static By objZelement = By.xpath("(//*[@name='ZOE BROADCASTING'])[3]");

    public static By objBankingAndFinance = By.xpath("//*[@label=' Banking/Finance ']");

    public static By objCharityAndReligious = By.xpath("(//*[@label='Charity/Religious '])[3]");
    
    public static By objCharityAndReligious1 = By.xpath("(//*[@name='CBN ASIA / 700 CLUB']/preceding-sibling::XCUIElementTypeOther)[1]");
    
    public static By objCharityAndReligiousBillers = By.xpath("//*[@name='Charity/Religious ']/following-sibling::XCUIElementTypeOther");
    
    public static By objUtilities = By.xpath("(//*[@name='Utilities '])[3]");
    
    public static By objUtilities1 = By.xpath("(//*[@name='ANECO']/preceding-sibling::XCUIElementTypeOther)[2]");
    
    public static By objUtilitiesBillers = By.xpath("//*[@name='Utilities ']/following-sibling::XCUIElementTypeOther");
    
    public static By objOthers = By.xpath("(//*[@name='Others '])[3]");
    
    public static By objOthers1 = By.xpath("(//*[@name='AAI LOGISTICS-BLACK ARROW']/preceding-sibling::XCUIElementTypeOther)[1]");
    
    public static By objOthersBillers = By.xpath("//*[@name='Others ']/following-sibling::XCUIElementTypeOther");
    
    public static By objAirAsia = By.xpath("//*[@text='AIR ASIA BILLSPAYMENT']");
    
    public static By objBillsPayInformation = By.xpath("//*[@label='Bills Pay Information']");
    
    public static By objBillerNameInBillsPayInformation = By.xpath("//*[@name='B0384I']");
    
    public static By objSearchBiller = By.xpath("//*[@name='Search biller ...']/XCUIElementTypeTextField");
    
    public static By objSearchBillerInSavedBillers = By.xpath("//*[@class='android.widget.EditText']");
    
    public static By objMisBillsPayBiller = By.xpath("(//*[@name='MIS BILLSPAY NEW TEST'])[2]");
    
    public static By objMisBillsPayBiller1 = By.xpath("//*[@name='B0384I']");
    
    public static By objEditBtn = By.xpath("(//*[@name='Edit'])[2]");
    
    public static By objAccountNumberField = By.xpath("//*[@name='Account Number']/XCUIElementTypeTextField");
    
  
    public static By objFirstNameField = By.xpath("//*[@name='First Name']/XCUIElementTypeTextField");
    
    public static By objFirstNameField1 = By.xpath("(//*[@name='First name must only contain letters and spaces']/preceding-sibling::XCUIElementTypeOther/child::XCUIElementTypeTextField)[2]");
    
    //*[@name='First name must only contain letters and spaces']/preceding-sibling::XCUIElementTypeOther/child::XCUIElementTypeTextField)[2]
    
    public static By objMiddleNameField = By.xpath("//*[@name='Middle Name']/XCUIElementTypeTextField");
    
    public static By objMiddleNameField1 = By.xpath("(//*[@name='Middle name must only contain letters and spaces']/preceding-sibling::XCUIElementTypeOther/child::XCUIElementTypeTextField)[3]");
    
    
    public static By objLastnameField = By.xpath("//*[@name='Last Name']/XCUIElementTypeTextField");
    
    public static By objLastnameField1 = By.xpath("(//*[@name='Last name must only contain letters and spaces']/preceding-sibling::XCUIElementTypeOther/child::XCUIElementTypeTextField)[4]");
    
    public static By objNickNameField = By.xpath("(//*[@text='Billing Information']/following-sibling::android.widget.EditText)[5]");
    
    public static By objAmountField = By.xpath("//*[@name='0.00']/XCUIElementTypeTextField");
    
    public static By objConfirmBtn = By.xpath("(//*[@label='Confirm'])[2]");
    
    public static By objModalConfirmBtn = By.xpath("//*[@name='Confirm']");
    
    public static By objConfirmDetails = By.xpath("//*[@label='Confirm Details']");
    
    public static By objInvalidFirstNameMsg = By.xpath("//*[@name='First name must only contain letters and spaces']");
    
    public static By objInvalidSecondNameMsg = By.xpath("//*[@name='Middle name must only contain letters and spaces']");
    
    public static By objInvalidLastName = By.xpath("//*[@name='Last name must only contain letters and spaces']");
    
    public static By objInvalidAmount = By.xpath("//*[@name='The amount should not be less than 1']");
    
    public static By objAccountNumberRequiredMsg = By.xpath("//*[@name='Account Number is required']");
    
    public static By objFirstNameRequiredMsg = By.xpath("//*[@name='First name is required']");
    
    public static By objLastNameRequiredMsg = By.xpath("//*[@name='Last name is required']");
    
    public static By objBillerName = By.xpath("(//*[@name='Biller'])[3]");
    
    public static By objAccountName = By.xpath("(//*[@name='Account Name'])[3]");
    
    public static By objAccountNumber = By.xpath("(//*[@name='Account Number'])[3]");
    
    public static By objPaymentMethod = By.xpath("(//*[@name='Payment Method'])[3]");
    
    public static By objPayBtn = By.xpath("//*[@name='T0BQ32']");
    
    public static By objMaxLimitErrorMessage = By.xpath("//*[@name='The maximum Bills Pay per transaction set for your verification level is P10,000.00. Please try again.']");
    
    public static By objMaxLimitErrorMessage1 = By.xpath("//*[@name='The maximum Bills Pay per transaction set for your verification level is P50,000.00. Please try again.']");
    		
    public static By objMaxLimitErrorMessage2 = By.xpath("//*[@name='The maximum Bills Pay per transaction set for your verification level is P25,000.00. Please try again.']");
    				
    public static By objAddBiller = By.xpath("(//*[@name='Add Biller'])[2]");
    
    public static By objAddBillers = By.xpath("(//*[@name='Add Billers'])[2]");
    
    public static By objAddSeectedBiller = By.xpath("//*[@label='Biller ']");
    
    public static By objBillerListSearchBiller = By.xpath("//*[@name='Search biller ...']/XCUIElementTypeTextField");
    
    public static By objAddAccountNumber = By.xpath("//*[@name='Account Number']/XCUIElementTypeTextField");
    
    public static By objAddFirstName = By.xpath("//*[@name='Account Holder First Name']/XCUIElementTypeTextField");
    
    public static By objAddMiddleName = By.xpath("//*[@name='Account Holder Middle Name']/XCUIElementTypeTextField");
    
    public static By objAddLastName = By.xpath("//*[@name='Account Holder Last Name']");
    
    public static By objAddNickName = By.xpath("//*[@name='Nickname']/XCUIElementTypeTextField");
    
    public static By objAddAccountNumberRequiredMsg = By.xpath("(//*[@class='android.widget.EditText'])[1]/following-sibling::android.widget.TextView");
    public static By objAddFirstNameRequiredMsg = By.xpath("(//*[@class='android.widget.EditText'])[2]/following-sibling::android.widget.TextView");
    public static By ObjAddLastNameRequiredMsg = By.xpath("(//*[@class='android.widget.EditText'])[3]/following-sibling::android.widget.TextView");
    
    public static By objProceedBtn = By.xpath("//*[@label='Proceed']");
    
    public static By objSuccessPillPaymentMsg = By.xpath("//*[@label='Bills Payment Successful']");
    
    public static By objSomethingWentWrongPopUp = By.xpath("//*[@name='Something went wrong. Our technical team has been notified. We will get this sorted as soon as possible.']");
    
    public static By objAmountPaid = By.xpath("//*[@name='8USUKB']");
    
    public static By objPaidDate = By.xpath("//*[@name='31OM5F']");
    
    public static By objTransactionDetails = By.xpath("//*[@label='Transaction Details']");
    
    public static By objServiceFee = By.xpath("(//*[@name='Service Fee'])[3]");
    
    public static By objTotalAmount = By.xpath("(//*[@name='Total'])[3]");
    
    public static By objAmountToPay = By.xpath("(//*[@name='Amount To Pay'])[3]");
    
    public static By objAmountToSend = By.xpath("(//*[@name='Amount to Send'])[3]");
    
    public static By objBackToHomeBtn = By.xpath("//*[@label='Back To Home']");
    
    public static By objTransactionNumber = By.xpath("(//*[@name='Transaction No.'])[3]");
    
    public static By objReferenceNumber = By.xpath("(//*[@name='Reference Number'])[3]");
    
    public static By objReceiverName = By.xpath("(//*[@name='Receiver Name'])[3]");
    
    public static By objReceiverMobNo = By.xpath("(//*[@name='Receiver Mobile No.'])[2]");
    
    public static By objSearchSavedBiller  = By.xpath("//*[@name='Search ...']/XCUIElementTypeTextField");
    
    public static By objClearSearchBar = By.xpath("//*[@name='']/XCUIElementTypeOther/XCUIElementTypeTextField");
    
    public static By objOKBtn=By.xpath("//*[@label='Ok']");
    
    public static By objSelectLastName(String sLastName,String sFirstName){
        return By.xpath("//*[contains(@label,'"+sLastName+", "+sFirstName+"')]");
    }
    public static By objEditRecipientLastName = By.xpath("(//*[@name='MIS BILLSPAY NEW TEST  Save'])[3]/XCUIElementTypeOther[9]/XCUIElementTypeTextField");
    
    public static By objLastNameClr = By.xpath("//*[@name='Account Holder Last Name']/XCUIElementTypeTextField");
    public static  By ObjSaveBtn = By.xpath("(//*[@name='Save'])[2]");
    
    public static By objSelectBillerInnSavedBiller = By.xpath("(//*[XCUIElementTypeScrollView]/descendant::XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeOther)[10]");
    
//    public static By objSelectBillerInnSavedBiller (String text){
//        return By.xpath("//*[@label='"+text+"']");
//    }
    public static By objRemoveBtn = By.xpath("(//*[@name='Remove'])[2]");
    
    public static By objConfirmPopup = By.xpath("//*[@name='Are you sure to remove this record?']");
    public static By objNewTransactionBtn = By.xpath("//*[@label='New Transaction']");
    
    public static By objRecentTransactionOne = By.xpath("(//*[XCUIElementTypeScrollView]/descendant::XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeOther)[4]");
    
  //
    public static By objAccountInfo = By.xpath("//*[@label='Account Info']");
    public static By objUpgradeNowBtn = By.xpath("//*[@label='Upgrade Now']");

    

}

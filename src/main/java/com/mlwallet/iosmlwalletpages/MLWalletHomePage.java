package com.mlwallet.iosmlwalletpages;

import org.openqa.selenium.By;

public class MLWalletHomePage {

    public static By objRecentTransactions = By.xpath("//*[@name='Recent Transactions']");

//    public static By objCashOutButton = By.xpath("(//*[@name=\"I7FJEO\"]/parent::XCUIElementTypeOther/descendant::XCUIElementTypeOther)[5]\n"
//    		+ "");
    
    public static By objCashOutButton = By.xpath("(//*[@name='Recent Transactions']/parent::XCUIElementTypeOther/descendant::XCUIElementTypeOther)[5]");

    public static By objKwartaPadala = By.xpath("(//*[@name='Recent Transactions']/parent::XCUIElementTypeOther/descendant::XCUIElementTypeOther)[5]");

    public static By objWalletToWallet = By.xpath("(//*[@name='Recent Transactions']/parent::XCUIElementTypeOther/descendant::XCUIElementTypeOther)[5]");

    
    //================= Latest ================
    
  //  public static By objRecentTransactions = By.xpath("(//*[@text='Recent Transactions'])");
  //  public static By objCashOutButton = By.xpath("(//*[@text='Cash Out'])[1]");
    public static By objPendingStatusForCashOut = By.xpath("((//*[@text='Cash Out'])[1]/preceding-sibling::android.view.ViewGroup/child::android.widget.TextView)[2]");
//    public static By objKwartaPadala = By.xpath("(//*[@text='Kwarta Padala'])[1]");
  //  public static By objWalletToWallet = By.xpath("(//*[@text='Wallet to Wallet'])[1]");
    public static By objPayBills = By.xpath("(//*[@name='Recent Transactions']/parent::XCUIElementTypeOther/descendant::XCUIElementTypeOther)[5]");


//=================================== Icons ==================================================================//

    public static By objCashInIcon = By.xpath("//*[@name='FF293B' or @label='Cash In']");
    
    public static By objCashOutIcon = By.xpath("//*[@name='214NQL']");
    
    public static By objBuyELoadIcon =By.xpath("//*[@name='PM3US2' or @name='Buy eLoad']");
    
    public static By objBuyELoadTransaction =By.xpath("(//*[@name='Recent Transactions']/parent::XCUIElementTypeOther/descendant::XCUIElementTypeOther)[5]");
    
    public static By objPosted = By.xpath("//*[@label='Posted']");
    
    public static By objPayBillsIcon = By.xpath("//*[@label='Pay Bills' or @name='2IZN4I']");
    
    public static By objShopItemsIcon = By.xpath("//*[@label='Shop Items' or @name='R80OMI']");
    
    public static By objSendTransferIcon = By.xpath("//*[@name='AY6EU5']");
    
    public static By objMLLoans = By.xpath("//*[@name='Loans']");
    
    public static By objUseQR = By.xpath("//*[@label='Use QR' or @name='RQSICY']");
    
    public static By objHamburgerMenu = By.xpath("(//*[@name=''])[4]");
    
    public static By objProfileIcon1=By.xpath("(//*[@name=''])[3]/*[3]/XCUIElementTypeOther");
    
    public static By objAvailableBalanceHeader = By.xpath("//*[@name='UTJ9TN' or @ label='Available Balance']");
    
    public static By objEyeIcon = By.xpath("//*[@name='JKRBGQ' or @ label='']");
    
    public static By objAvailableBalance = By.xpath("//*[@name='0619JV']");
    
    public static By objHiddenAvailableBalance = By.xpath("//*[@name='3P4KX3']");
    
    public static By objTransactions = By.xpath("//*[@name='OSJVNX']/descendant::XCUIElementTypeScrollView/descendant::XCUIElementTypeOther[contains(@name,'Php')]");
    
    public static By objSeeMore = By.xpath("(//*[@name='See More'])[3]");
    
    public static By objIIcon = By.xpath("//*[@name=' Learn more about your account level by clicking here.']");
    
    public static By objVerificationTierPerks = By.xpath("//*[@name='Verification Tier Perks']");
    
    public static By objDoYouHaveAnotherAccBtn = By.xpath("//*[@name='SYOFR5']");
    
    public static By objAddAccFacility = By.xpath("//*[@name='ML Wallet Add Account Facility']");
    
    public static By objViewTier = By.xpath("//*[@name='BRANCH VERIFIED TIER BRANCH VERIFIED View Tier ' or @name='SEMI VERIFIED TIER SEMI VERIFIED View Tier ' or @name='FULLY VERIFIED TIER FULLY VERIFIED View Tier ']");
    
   // public static By objViewTier = By.xpath("//*[contains(@name,'BRANCH VERIFIED TIER BRANCH VERIFIED View Tier ' or @name,'SEMI VERIFIED TIER SEMI VERIFIED View Tier ')]");
    
    public static By objMaxBalanceText = By.xpath("//*[@label='Max. Balance:']");
    
    public static By objMaxBalanceAmount = By.xpath("//*[@label='Max. Balance:']/following-sibling::XCUIElementTypeStaticText");
    
    public static By objSendingLimitsCashOut = By.xpath("//*[@label='Sending Limits (cash-out)']");
    
    public static By objSendingLimitTransactionTypeAndAmount = By.xpath("//*[@label='Sending Limits (cash-out)']/parent::XCUIElementTypeOther/descendant::XCUIElementTypeOther/child::XCUIElementTypeOther");
    
    public static By objReceivingLimitsCashIn = By.xpath("//*[@label='Receiving Limits (cash-in)']");
    
    public static By objReceivingLimitsTransactionTypeAndAmount = By.xpath("//*[@label='Receiving Limits (cash-in)']/parent::XCUIElementTypeOther/descendant::XCUIElementTypeOther/child::XCUIElementTypeOther");
    
    public static By objPurchaseLimits = By.xpath("//*[@label='Purchase Limits']");
    
    public static By objPurchaseLimitsTransactionTypeAndAmount = By.xpath("//*[@label='Purchase Limits']/parent::XCUIElementTypeOther/descendant::XCUIElementTypeOther/child::XCUIElementTypeOther");
    
    public static By objTransaction = By.xpath("//*[@label='Transaction ']");
    
    public static By objWithdrawCash = By.xpath("//*[@name='Withdraw Cash Top Up ML Wallet']/child::XCUIElementTypeOther/descendant::XCUIElementTypeButton[@name='Withdraw Cash']");
    
    public static By objTopUpMLWallet = By.xpath("//*[@name='Withdraw Cash Top Up ML Wallet']/child::XCUIElementTypeOther/descendant::XCUIElementTypeButton[@name='Top Up ML Wallet']");
    
    public static By objShop = By.xpath("//*[@label='Shop']");
    
    public static By objKwartaPadalaRatesBtn = By.xpath("//*[@label='Kwarta Padala Rates']");
    
    public static By objKwartaPadalaRates = By.xpath("//*[@name='Amount Rates']/ancestor::XCUIElementTypeOther/descendant::XCUIElementTypeScrollView/child::XCUIElementTypeOther/descendant::XCUIElementTypeOther");
   
    public static By objViewTierFullyVerified = By.xpath("//*[@name='FULLY VERIFIED TIER FULLY VERIFIED View Tier ']");
    
    public static By objViewTierSemiVerified = By.xpath("//*[@name='SEMI VERIFIED TIER SEMI VERIFIED View Tier ']");
    
    public static By objViewTierBranchVerified = By.xpath("//*[@name='BRANCH VERIFIED TIER BRANCH VERIFIED View Tier ']");
    
    public static By objViewTierBuyierTierVerified = By.xpath("//*[@name='BUYER TIER BUYER View Tier ' or @name='SEMI VERIFIED TIER SEMI VERIFIED View Tier ']");
    
    public static By objSupport = By.xpath("//*[@label='Support']");
    
    public static By objAbout = By.xpath("//*[@label='About']");
    
    public static By objTier = By.xpath("//*[contains(@label,'Verified Tier Perks')]");
    
    public static By objSemiVerified = By.xpath("//*[@label='Semi Verified']");
    
    public static By objBranchVerified = By.xpath("//*[@label='Branch Verified']");
    
    public static By objFullyVerified = By.xpath("//*[@label='Fully Verified']");
    
    public static By objUpgradeTierLevelBtn = By.xpath("//*[@label='Upgrade Tier Level']");
    
   
    public static By objPopUpMsg = By.xpath("//XCUIElementTypeStaticText[@name='“ML Wallet” Would Like to Access Your Contacts' or @name='Allow “ML Wallet” to use your location?']");
    
    public static By obPopupAllowBtn = By.xpath("//*[@name='Allow']");
    
   // public static By obPopupAllowBtn = By.xpath("//*[@name='Allow']");
    
    
    public static By objContactPopUpAllowBtn = By.xpath("//*[@name='OK']");
    
    public static By objLocationPopUpAllowOnceBtn = By.xpath("//*[@resource-id='com.android.permissioncontroller:id/permission_allow_one_time_button']");
   
    public static By objPopUpDenyBtn = By.xpath("//*[@name='Don’t Allow']");
   
    public static By objContactPopupDenyBtn = By.xpath("//*[@resource-id='com.android.permissioncontroller:id/permission_deny_and_dont_ask_again_button']");
    public static By objPermissionDeniedPopUp = By.xpath("(//*[@resource-id='something']/following-sibling::android.widget.TextView)[1]");
    public static By objOpenSettingBtn = By.xpath("//*[@resource-id='modal-confirm-button']/child::android.widget.TextView");
    public static By objCloseBtn = By.xpath("//*[@resource-id='modal-cancel-button']/child::android.widget.TextView");
    public static By objInternetConnectionPopUp = By.xpath("(//*[@resource-id='something']/following-sibling::android.widget.TextView)[1]");
    public static By objInternetConnectionMsg = By.xpath("(//*[@resource-id='something']/following-sibling::android.widget.TextView)[2]");
    public static By objOkBtn = By.xpath("//*[@resource-id='modal-confirm-button']/child::android.widget.TextView");
    
    public static By objSettings = By.xpath("//*[@label='Settings']");
    
    public static By objAccountDetails = By.xpath("(//*[@label='Account Details '])[2]");
    
    public static By objChangeMLPin = By.xpath("(//*[@label='Change ML PIN '])[2]");
    
    public static By objBranchCashIn = By.xpath("//*[@name='Cash In']");
    
    public static By objMLShopPage = By.xpath("//*[@name='ML Shop']");
    
    public static By objTroubleSingingInPAge = By.xpath("//*[@name='Trouble Signing In']");
     
    public static By objAccountRecovery = By.xpath("(//*[@label='Account Recovery '])[2]");
    
    public static By objNotification = By.xpath("(//*[@label='Notification '])[2]");
    
    public static By objDeleteAccount = By.xpath("(//*[@label='Delete Account'])[2]");
    
  //  public static By objSettings = By.xpath("");
    

}

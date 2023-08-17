package com.mlwallet.iosmlwalletpages;

import org.openqa.selenium.By;

public class MLWalletTransactionHistoryPage {
	
	public static By objRecentTransaction=By.xpath("//*[@name='Recent Transactions']");
	
	public static By objSeeMoreBtn=By.xpath("//*[@name='See More']");

	public static By objTransactionHistory = By.xpath("//*[@name='Transaction History']");
	
	public static By objBillsPayTab=By.xpath("//*[@name='Billspay']");
	
	public static By objeLoadTab=By.xpath("//*[@name='eLoad']");
	
	public static By objSendMoneyTab=By.xpath("//*[@name='Send Money']");
	
	public static By objWalletToWallet = By.xpath("//*[@name='Wallet to Wallet']");
	
	public static By objKwartaPadala = By.xpath("(//*[@name='Kwarta Padala'])[1]");
	
	public static By objSenderName = By.xpath("(//*[@name='Sender Name'])[3]");
	
	public static By objCashInTab=By.xpath("//*[@name='Cash In']");
	
	public static By objCashOutTab=By.xpath("//*[@name='Cash Out']");
	
	public static By objReceiveMoneyTab=By.xpath("//*[@name='Receive Money']");
	
	public static By objBalanceAdjustmentTab=By.xpath("//*[@name='Balance Adjustment']");
	
	public static By objMlShopTab=By.xpath("//*[@name='ML Shop']");
	
//	public static By objPayBillsHistory=By.xpath("//*[@text='Pay Bills' or @text='No Recent Transaction']");
	//public static By objPayBillsTransctionList=By.xpath("//*[@text='Pay Bills']/(following-sibling::android.widget.TextView)[2]");
	
	
	
	public static By objPayBillsTransctionList1(String moduleName)// return	By.xpath("//*[contains(@name,'"+moduleName+"')]");
	{
		return By.xpath("(//*[@name=\"All\"]/ancestor::XCUIElementTypeOther/descendant::XCUIElementTypeOther/child::XCUIElementTypeScrollView/descendant::XCUIElementTypeOther/descendant::XCUIElementTypeOther/child::XCUIElementTypeOther[contains(@name,'"+moduleName+"')])");
	}
	
	public static By objCashoutFirstTransaction = By.xpath("(//*[@name=\"All\"]/ancestor::XCUIElementTypeOther/descendant::XCUIElementTypeOther/child::XCUIElementTypeScrollView/descendant::XCUIElementTypeOther/descendant::XCUIElementTypeOther/child::XCUIElementTypeOther[contains(@name,'Cash Out')])[2]");
	
	public static By objCashInFirstTransaction = By.xpath("(//*[@name=\"All\"]/ancestor::XCUIElementTypeOther/descendant::XCUIElementTypeOther/child::XCUIElementTypeScrollView/descendant::XCUIElementTypeOther/descendant::XCUIElementTypeOther/child::XCUIElementTypeOther[contains(@name,'Cash In')])[2]");
	
//	public static By objPayBillsTransctionList1(String moduleName)
//	{
//	   return	By.xpath("//*[@name=\"All\"]/ancestor::XCUIElementTypeOther/descendant::XCUIElementTypeOther/child::XCUIElementTypeScrollView/descendant::XCUIElementTypeOther[contains(@name,'"+moduleName+"')]");
//	}
	
//	public static By objBillHistory(String billModule,String transaction)
//	{
//		return By.xpath("//*[@name='"+billModule+"' or @name='"+transaction+"']");
//	}
	
	public static By objBillHistory(String billModule)
	{
		return	By.xpath("//*[contains(@name,'"+billModule+"')]");
	}

	public static By  objAllTab = By.xpath("//*[@name='All']");

	public static By objFirstTransaction = By.xpath("(//*[@name=\\\"All\\\"]/ancestor::XCUIElementTypeOther/descendant::XCUIElementTypeOther/child::XCUIElementTypeScrollView/descendant::XCUIElementTypeOther/descendant::XCUIElementTypeOther/child::XCUIElementTypeOther[contains(@name,'Pay Bills')])");
	
	public static By objTransactionDetails = By.xpath("//*[@name='Transaction Details']");
	
	public static By objReceiverMobileNo= By.xpath("(//*[@name='Receiver Mobile No.'])[3]");
	
	public static By objReceiverName = By.xpath("(//*[@name='Receiver Name'])[3]");
	
	public static By objPaymentMethod = By.xpath("(//*[@name='Payment Method'])[3]");

	public static By objLoadType = By.xpath("(//*[@name='Load Type'])[3]");
	
	public static By objBiller = By.xpath("(//*[@name='Biller'])[3]");
	
	public static By objTransactionType = By.xpath("(//*[@name='Transaction Type'])[3]");
	
	public static By objAmount = By.xpath("(//*[@name='Amount to Send'])[3]");
	
	public static By objServiceFee = By.xpath("(//*[@name='Service Fee'])[3]");
	
	public static By objTotalAmount = By.xpath("(//*[@name='Total'])[3]");
	
	public static By objTotalCashIn = By.xpath("(//*[@name='Total Cash In'])[3]");
	
	public static By objTotalCashOut = By.xpath("(//*[@name='Total Cash Out'])[3]");
	
	public static By objReferenceNumberInTransactionDetails = By.xpath("(//*[@name='Reference Number'])[3]");
	
	public static By objDate = By.xpath("(//*[XCUIElementTypeOther]/descendant::XCUIElementTypeStaticText)[5]");
	
	public static By objBank = By.xpath("(//*[@resource-id='Bank'])[2]");
	
	public static By objAmountReceived = By.xpath("(//*[@resource-id='Amount Received'])[2]");
}
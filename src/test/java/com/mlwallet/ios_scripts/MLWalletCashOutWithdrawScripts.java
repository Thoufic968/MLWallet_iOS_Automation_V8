package com.mlwallet.ios_scripts;


import org.testng.annotations.Test;
import com.business.mlwallet.MLWalletiOSBusinessLogic;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MLWalletCashOutWithdrawScripts {

	private MLWalletiOSBusinessLogic MLWalletBusinessLogic;


	//@BeforeTest
	@BeforeSuite
	public void init() throws InterruptedException {
		MLWalletBusinessLogic = new MLWalletiOSBusinessLogic("mlwallet");
	}
    

//==============================Phase 1=======================================//


    @Test(priority = 1) 
    public void cashOutWithdrawBank_WM_TC_01() throws Exception
    {
        MLWalletBusinessLogic.cashOutWithdrawBank_WM_TC_01("100");
    }

    @Test(priority = 2)  
    public void cashOutWithInvalidAccNumber_WM_TC_02() throws Exception
    {
        MLWalletBusinessLogic.cashOutWithInvalidAccNumber_WM_TC_02();
    }

    @Test(priority = 3)  
    public void cashOutWithdrawBankMaxAmount_WM_TC_03() throws Exception
    {
        MLWalletBusinessLogic.cashOutWithdrawBankMaxAmount_WM_TC_03("60000");
    }

    @Test(priority = 4) 
    public void cashOutWithdrawMinTransactionLimit_WM_TC_04() throws Exception
    {
        MLWalletBusinessLogic.cashOutWithdrawMinTransactionLimit_WM_TC_04("10");
    }

    @Test(priority = 5) //blocked
    public void cashOutWithdrawBranch_WM_TC_05() throws Exception
    {
        MLWalletBusinessLogic.cashOutWithdrawBranch_WM_TC_05();
    }

    @Test(priority = 6) //blocked
    public void cashOutMaxLimit_WM_TC_06() throws Exception
    {
        MLWalletBusinessLogic.cashOutMaxLimit_WM_TC_06();
    }

    @Test(priority = 7) //blocked
    public void cashOutInsufficientBalance_WM_TC_07() throws Exception {
        MLWalletBusinessLogic.cashOutInsufficientBalance_WM_TC_07();
    }

    @Test(priority = 8) //blocked
    public void cashOutBuyerTierLevelAcc_WM_TC_09() throws Exception
    {
        MLWalletBusinessLogic.cashOutBuyerTierLevelAcc_WM_TC_09();
    }

   
  //=========================== Phase 2=================================================================//

    

    @Test(priority = 9) //pass
    public void cashOutInvalidBank_WM_TC_10() throws Exception {
        MLWalletBusinessLogic.cashOutInvalidBank_WM_TC_10();
    }

    @Test(priority = 10) //pass
    public void searchAndSelectBank_WM_TC_11() throws Exception {
        MLWalletBusinessLogic.searchAndSelectBank_WM_TC_11();
    }

    @Test(priority = 11)// pass
    public void cashOutInvalidAmount_WM_TC_12() throws Exception {
       MLWalletBusinessLogic.cashOutInvalidAmount_WM_TC_12();
    }

    @Test(priority = 12) //pass
    public void cashOutSaveRecipient_WM_TC_13() throws Exception {
        MLWalletBusinessLogic.cashOutSaveRecipient_WM_TC_13("100");
    }

    @Test(priority = 13) //pass
    public void cashOutRecipientDuplicate_WM_TC_14() throws Exception {
        MLWalletBusinessLogic.cashOutRecipientDuplicate_WM_TC_14("100");
    }

    @Test(priority = 14)// pass
    public void cashOutUIValidation_WM_TC_16() throws Exception {
        MLWalletBusinessLogic.cashOutUIValidation_WM_TC_16();
    }

    @Test(priority = 15)// pass
    public void cashOutWithdrawBackBtnValidation_WM_TC_17() throws Exception {
        MLWalletBusinessLogic.cashOutWithdrawBackBtnValidation_WM_TC_17();
    }

    @Test(priority = 16) // blocked
    public void cashOutToBranchUIValidation_WM_TC_18() throws Exception {
        MLWalletBusinessLogic.cashOutToBranchUIValidation_WM_TC_18();
    }

    @Test(priority = 17) //pass
    public void cashOutToBranchBackBtnValidation_WM_TC_19 () throws Exception {
        MLWalletBusinessLogic.cashOutToBranchBackBtnValidation_WM_TC_19();
    }

            @Test(priority = 18)
    public void cashOutOTPPageUIValidation_WM_TC_20() throws Exception {
        MLWalletBusinessLogic.cashOutOTPPageUIValidation_WM_TC_20("100");
    }

           @Test(priority = 19) 
    public void cashOutOTPPageBackBtnValidation_WM_TC_21() throws Exception {
        MLWalletBusinessLogic.cashOutOTPPageBackBtnValidation_WM_TC_21("100");
    } 


}

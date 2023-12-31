package com.mlwallet.ios_scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.business.mlwallet.MLWalletiOSBusinessLogic;

public class MLWalletCashInViaBankScripts {
	
	private MLWalletiOSBusinessLogic MLWalletBusinessLogic;

//	@BeforeTest
	@BeforeSuite
	public void init() throws InterruptedException {
		MLWalletBusinessLogic = new MLWalletiOSBusinessLogic("mlwallet");
		
	}

	
	//==================================================================================================//


    @Test(priority = 1) 
    public void cashInViaBank_CIBA_TC_01() throws Exception {
        MLWalletBusinessLogic.cashInViaBank_CIBA_TC_01("true");
    }

    @Test(priority = 2)  
    public void cashInViaBankMinimumTransactionLimit_CIBA_TC_03() throws Exception {
        MLWalletBusinessLogic.cashInViaBankMinimumTransactionLimit_CIBA_TC_03();
    }

    @Test(priority = 3) 
    public void cashInViaBankMaximumTransaction_CIBA_TC_04() throws Exception {
        MLWalletBusinessLogic.cashInViaBankMaximumTransaction_CIBA_TC_04();
    }


//=========================================================================================================//

    @Test(priority = 4) 
    public void cashInViaBankInvalidAmount_STW_TC_05() throws Exception {
        MLWalletBusinessLogic.cashInViaBankInvalidAmount_STW_TC_05();
    }

    @Test(priority = 5) 
    public void cashInViaBankNavigation_STW_TC_06() throws Exception {
        MLWalletBusinessLogic.cashInViaBankNavigation_STW_TC_06();
    }

    @Test(priority = 6) 
    public void cashInUIValidation_STW_TC_07() throws Exception {
        MLWalletBusinessLogic.cashInUIValidation_STW_TC_07();
    } 

    @Test(priority = 7) 
    public void cashInPageBackArrowBtnValidation_STW_TC_08() throws Exception {
        MLWalletBusinessLogic.cashInPageBackArrowBtnValidation_STW_TC_08();
    }

   
    @Test(priority = 8) 
    public void cashInSelectBankPageUIValidation_STW_TC_09() throws Exception {
        MLWalletBusinessLogic.cashInSelectBankPageUIValidation_STW_TC_09();
    }

    @Test(priority = 9) 
    public void cashInViaBankSearchInvalidBank_STW_TC_10() throws Exception {
        MLWalletBusinessLogic.cashInViaBankSearchInvalidBank_STW_TC_10("true");
    }

    @Test(priority = 10) 
    public void cashInViaBankSelectBankPageBackBtnValidation_STW_TC_11() throws Exception {
        MLWalletBusinessLogic.cashInViaBankSelectBankPageBackBtnValidation_STW_TC_11();
    }

    @Test(priority = 11) 
    public void cashInViaBankDragonPayPageUIValidation_STW_TC_12() throws Exception {
        MLWalletBusinessLogic.cashInViaBankDragonPayPageUIValidation_STW_TC_12("true");
    }

    @Test(priority = 12) 
    public void cashInViaBankDragonPayBackBtnValidation_STW_TC_13() throws Exception {
        MLWalletBusinessLogic.cashInViaBankDragonPayBackBtnValidation_STW_TC_13();
    }

    @Test(priority = 13) 
    public void cashInViaBankReviewTransactionPageUIValidation_STW_TC_14() throws Exception {
        MLWalletBusinessLogic.cashInViaBankReviewTransactionPageUIValidation_STW_TC_14("true");
    }

    @Test(priority = 14) 
    public void cashInViaBankReviewTransactionBackBtnValidation_STW_TC_15() throws Exception {
        MLWalletBusinessLogic.cashInViaBankReviewTransactionBackBtnValidation_STW_TC_15();
    }

    @Test(priority = 15) 
    public void cashInViaBankPendingTransaction_CIBA_TC_17() throws Exception {
        MLWalletBusinessLogic.cashInViaBankPendingTransaction_CIBA_TC_17();
    }

    @Test(priority = 16) 
    public void cashInViaBankWithExistingPendingTransaction_CIBA_TC_20() throws Exception {
        MLWalletBusinessLogic.cashInViaBankWithExistingPendingTransaction_CIBA_TC_20();
    }

    @Test(priority = 17)  
    public void cancelButtonValidationInDragonPayPopUp_CIBA_TC_21() throws Exception {
        MLWalletBusinessLogic.cancelButtonValidationInDragonPayPopUp_CIBA_TC_21();
    } 
    
    @Test(priority = 18)
    public void cashInViaBankTappingOutsideTheDragonPayPopupValidation_CIBA_TC_22() throws Exception {
        MLWalletBusinessLogic.cashInViaBankTappingOutsideTheDragonPayPopupValidation_CIBA_TC_22();
    }

    @Test(priority = 19) 
    public void cashInViaBankInvalidAmountFieldValidation_CIBA_TC_23() throws Exception {
        MLWalletBusinessLogic.cashInViaBankInvalidAmountFieldValidation_CIBA_TC_23();
    }

    @Test(priority = 20) 
    public void cashInViaBankBuyerTierLevel_CIBA_TC_24() throws Exception {
        MLWalletBusinessLogic.cashInViaBankBuyerTierLevel_CIBA_TC_24();
    }

    @Test(priority = 21) 
    public void cashInViaBankMaxLimit_CIBA_TC_27() throws Exception {
        MLWalletBusinessLogic.cashInViaBankSemiVerifiedUserMaxLimit_CIBA_TC_27();
    }

    @Test(priority = 22) 
    public void cashInViaBankFullyVerifiedUserMaxLimit_CIBA_TC_28() throws Exception {
        MLWalletBusinessLogic.cashInViaBankFullyVerifiedUserMaxLimit_CIBA_TC_28();
    }

    @Test(priority = 23)
    public void cashInViaBankTransactionDetailsPageUIValidation_CIBA_TC_29() throws Exception {
        MLWalletBusinessLogic.cashInViaBankTransactionDetailsPageUIValidation_CIBA_TC_29();
    }


    @Test(priority = 24)
    public void cashInViaBankDragonPayChagresPopUpValidation_CIBA_TC_32() throws Exception {
        MLWalletBusinessLogic.cashInViaBankDragonPayChagresPopUpValidation_CIBA_TC_32();
    }   
    
    @Test(priority = 25) 
    public void cashInViaBankAmountFieldValidation_CIBA_TC_49() throws Exception {
        MLWalletBusinessLogic.cashInViaBankAmountFieldValidation_CIBA_TC_49();
    }

    @Test(priority = 26) 
    public void cashInViaBankTransactionWithValidMLPin_CIBA_TC_50() throws Exception {
        MLWalletBusinessLogic.cashInViaBankTransactionWithValidMLPin_CIBA_TC_50();
    }

    @Test(priority = 27) 
    public void cashInViaBankTransactionWithInValidMLPin_CIBA_TC_51() throws Exception {
        MLWalletBusinessLogic.cashInViaBankTransactionWithInValidMLPin_CIBA_TC_51();
    }

    @Test(priority = 28) 
    public void cashInViaBankOTPPopupValidation_CIBA_TC_57() throws Exception {
        MLWalletBusinessLogic.cashInViaBankOTPPopupValidation_CIBA_TC_57();
    }
    
    @Test(priority = 29) 
    public void cashInViaBankTransactionInAppOTPPopupUIValidation_CIBA_TC_58() throws Exception {
        MLWalletBusinessLogic.cashInViaBankTransactionInAppOTPPopupUIValidation_CIBA_TC_58();
    }

    @Test(priority = 30)
    public void cashInViaBankTransactionNewOTPAfterSixtySecondsValidation_CIBA_TC_59() throws Exception {
        MLWalletBusinessLogic.cashInViaBankTransactionNewOTPAfterSixtySecondsValidation_CIBA_TC_59();
    }

    @Test(priority = 31)
    public void cashInViaBankTransactionOTPCancelBtnFunctionality_CIBA_TC_60() throws Exception {
        MLWalletBusinessLogic.cashInViaBankTransactionOTPCancelBtnFunctionality_CIBA_TC_60();
    }

    @Test(priority = 32)
    public void cashInViaBankOTPContinueBtnFunctionality_CIBA_TC_61() throws Exception {
        MLWalletBusinessLogic.cashInViaBankOTPContinueBtnFunctionality_CIBA_TC_61();
    } 

    

}

package com.mlwallet.ios_scripts;


import org.testng.annotations.Test;
import com.business.mlwallet.MLWalletiOSBusinessLogic;
import com.propertyfilereader.PropertyFileReader;

import org.testng.annotations.*;


public class MLWalletBuyEload {

	private MLWalletiOSBusinessLogic MLWalletBusinessLogic;
	public static PropertyFileReader prop = new PropertyFileReader(".//properties//testdata.properties");

	@BeforeSuite
	public void init() throws InterruptedException {
		MLWalletBusinessLogic = new MLWalletiOSBusinessLogic("mlwallet");
	}


	   

    //===================================buy eload=======================================


    @Test(priority = 1) 
    public void buyELoadTransactionDetails_BE_TC_01() throws Exception
    {
        MLWalletBusinessLogic.buyELoadTransactionDetails_BE_TC_01(prop.getproperty("Branch_Verified"),4);
    }

    @Test(priority = 2) 
    public void buyELoadInvalidMobileNumber_BE_TC_02() throws Exception
    {
        MLWalletBusinessLogic.buyELoadInvalidMobileNumber_BE_TC_02();
    }

    @Test(priority = 3) 
    public void buyELoadWithoutInputMobNumber_BE_TC_03() throws Exception
    {
        MLWalletBusinessLogic.buyELoadWithoutInputMobNumber_BE_TC_03();
    }

    @Test(priority = 4) 
    public void buyELoadWithoutTelecommunicationSelected_BE_TC_04() throws Exception
    {
        MLWalletBusinessLogic.buyELoadWithoutTelecommunicationSelected_BE_TC_04();
    } 

    @Test(priority = 5)  
    public void buyELoadInsufficientBalance_BE_TC_05() throws Exception
    {
        MLWalletBusinessLogic.buyELoadInsufficientBalance_BE_TC_05(prop.getproperty("ios_BranchVerifiedTier"),4);
    }

    @Test(priority = 6)  
    public void buyELoadMaxLimitPerTransaction_BE_TC_09() throws Exception {
        MLWalletBusinessLogic.buyELoadMaxLimitPerTransaction_BE_TC_09(prop.getproperty("Fully_Verified"),2);
    } 

    @Test(priority = 7) 
    public void buyELoadTransactionPageUIValidation_BE_TC_10() throws Exception {
        MLWalletBusinessLogic.buyELoadTransactionPageUIValidation_BE_TC_10(prop.getproperty("Branch_Verified"));
    }

    @Test(priority = 8)
    public void buyELoadNextButtonFunctionalityOnELoadTransactionPage_BE_TC_11() throws Exception {
        MLWalletBusinessLogic.buyELoadNextButtonFunctionalityOnELoadTransactionPage_BE_TC_11(prop.getproperty("Branch_Verified"),prop.getproperty("sunMobileNumber"),4,"true");
    }

    @Test(priority = 9) 
    public void buyELoadLoadSelectionPageBackBtnValidation_BE_TC_12() throws Exception {
        MLWalletBusinessLogic.buyELoadLoadSelectionPageBackBtnValidation_BE_TC_12();
    }

    @Test(priority = 10) 
    public void buyELoadLoadSelectionPageUIValidation_BE_TC_13() throws Exception {
        MLWalletBusinessLogic.buyELoadLoadSelectionPageUIValidation_BE_TC_13(prop.getproperty("Branch_Verified"),4);
    }

    @Test(priority = 11) 
    public void buyELoadLoadSelectionChangeBtnFunctionality_BE_TC_14() throws Exception {
        MLWalletBusinessLogic.buyELoadLoadSelectionChangeBtnFunctionality_BE_TC_14(prop.getproperty("Branch_Verified"),4);
    }

    @Test(priority = 12) 
    public void buyELoadTransactionDetailsPageUIValidation__BE_TC_15() throws Exception {
        MLWalletBusinessLogic.buyELoadTransactionDetailsPageUIValidation_BE_TC_15(prop.getproperty("Branch_Verified"),4);
    }

    @Test(priority = 13)
    public void buyELoadSelectFromContactsBtnFunctionality_BE_TC_16() throws Exception {
        MLWalletBusinessLogic.buyELoadSelectFromContactsBtnFunctionality_BE_TC_16(prop.getproperty("Branch_Verified"),"true");
    }

    @Test(priority = 14) 
    public void buyELoadContactsPageUIValidation_BE_TC_17() throws Exception {
        MLWalletBusinessLogic.buyELoadContactsPageUIValidation_BE_TC_17(prop.getproperty("Branch_Verified"));
    }

    @Test(priority = 15)
    public void buyELoadContactsPageBackBtnValidation_BE_TC_18() throws Exception {
        MLWalletBusinessLogic.buyELoadContactsPageBackBtnValidation_BE_TC_18(prop.getproperty("Branch_Verified"));
    }

    @Test(priority = 16) 
    public void buyELoadSearchFieldValidation_BE_TC_19() throws Exception {
        MLWalletBusinessLogic.buyELoadSearchFieldValidation_BE_TC_19(prop.getproperty("Branch_Verified"));
    }

    @Test(priority = 17) 
    public void buyELoadAddContactToFavorites_BE_TC_20() throws Exception {
        MLWalletBusinessLogic.buyELoadAddContactToFavorites_BE_TC_20(prop.getproperty("Branch_Verified"));
    }

    @Test(priority = 18) 
    public void buyELoadAddedContactToFavoritesValidation_BE_TC_21() throws Exception {
        MLWalletBusinessLogic.buyELoadAddedContactToFavoritesValidation_BE_TC_21(prop.getproperty("Branch_Verified"));
    }

    @Test(priority = 19) 
    public void buyELoadSearchAddedFavoriteContact_BE_TC_22() throws Exception {
        MLWalletBusinessLogic.buyELoadSearchAddedFavoriteContact_BE_TC_22(prop.getproperty("Branch_Verified"));
    }

    @Test(priority = 20) 
    public void buyELoadSearchUnFavoriteContact_BE_TC_23() throws Exception {
        MLWalletBusinessLogic.buyELoadSearchUnFavoriteContact_BE_TC_23(prop.getproperty("Branch_Verified"));
    }

    @Test(priority = 21) 
    public void buyELoadPromoConfirmationPopupValidation_BE_TC_24() throws Exception {
        MLWalletBusinessLogic.buyELoadPromoConfirmationPopupValidation_BE_TC_24(prop.getproperty("Branch_Verified"),4);
    }

    @Test(priority = 22)
    public void buyELoadOTPPageUIValidation_BE_TC_25() throws Exception {
        MLWalletBusinessLogic.buyELoadOTPPageUIValidation_BE_TC_25(prop.getproperty("Branch_Verified"),4);
    }

    @Test(priority = 23)
    public void buyELoadSuccessfulTransactionUIValidation_BE_TC_26() throws Exception {
        MLWalletBusinessLogic.buyELoadSuccessfulTransactionUIValidation_BE_TC_26(prop.getproperty("Branch_Verified"),4,"true");
    }

    @Test(priority = 24)
    public void buyELoadRecentTransactionUIValidation_BE_TC_27() throws Exception {
        MLWalletBusinessLogic.buyELoadRecentTransactionUIValidation_BE_TC_27("true");
    }

    @Test(priority = 25) 
    public void buyELoadTransactionDetailsUIValidation_BE_TC_28() throws Exception {
        MLWalletBusinessLogic.buyELoadTransactionDetailsUIValidation_BE_TC_28();
    }

    
    @Test(priority = 26)
    public void buyELoadContactsPermissionPopup_BE_TC_57() throws Exception {
       MLWalletBusinessLogic.buyELoadContactsPermissionPopup_BE_TC_57(prop.getproperty("Branch_Verified"),"true");
    }
    
   @Test(priority = 27)
   public void buyELoadContactPermissionPopupAllowBtnFunctionality_BE_TC_58() throws Exception {
       MLWalletBusinessLogic.buyELoadContactPermissionPopupAllowBtnFunctionality_BE_TC_58();
   }
  
   @Test(priority = 28)
   public void buyELoadContactPermissionPopupDenyBtnFunctionality_BE_TC_59() throws Exception {
       MLWalletBusinessLogic.buyELoadContactPermissionPopupDenyBtnFunctionality_BE_TC_59("true");
   }
  
  
   @Test(priority = 29)
   public void buyELoadContactPermissionTwoAllowBtnFunctionality_BE_TC_61() throws Exception {
      MLWalletBusinessLogic.buyELoadContactPermissionTwoAllowBtnFunctionality_BE_TC_61();
   }
   
   @Test(priority = 30) 
   public void buyELoadTransactionWithValidMLPin_BE_TC_73() throws Exception {
       MLWalletBusinessLogic.buyELoadTransactionWithValidMLPin_BE_TC_73(prop.getproperty("Fully_Verified"), 3);
   }

   @Test(priority = 31) 
   public void buyELoadTransactionWithInValidMLPin_BE_TC_74() throws Exception {
       MLWalletBusinessLogic.buyELoadTransactionWithInValidMLPin_BE_TC_74(prop.getproperty("Fully_Verified"), 3);
   }

   @Test(priority = 32) 
   public void buyELoadInOTPPopupValidation_BE_TC_084() throws Exception {
      MLWalletBusinessLogic.buyELoadInOTPPopupValidation_BE_TC_084(prop.getproperty("Fully_Verified"), 4);
   }

   @Test(priority = 33) 
   public void buyELoadTransactionInAppOTPPopupUIValidation_BE_TC_085() throws Exception {
       MLWalletBusinessLogic.buyELoadTransactionInAppOTPPopupUIValidation_BE_TC_085(prop.getproperty("Fully_Verified"), 4);
   }

  @Test(priority = 34) 
  public void buyELoadTransactionNewOTPAfterSixtySecondsValidation_BE_TC_086() throws Exception {
      MLWalletBusinessLogic.buyELoadTransactionNewOTPAfterSixtySecondsValidation_BE_TC_086(prop.getproperty("Fully_Verified"), 4);
  }

  @Test(priority = 35) 
  public void buyELoadTransactionOTPCancelBtnFunctionality_BE_TC_087() throws Exception {
      MLWalletBusinessLogic.buyELoadTransactionOTPCancelBtnFunctionality_BE_TC_087(prop.getproperty("Fully_Verified"), 4);
  }

  @Test(priority = 36) 
  public void buyELoadTransactionOTPContinueBtnFunctionality_BE_TC_088() throws Exception {
     MLWalletBusinessLogic.buyELoadTransactionOTPContinueBtnFunctionality_BE_TC_088(prop.getproperty("Fully_Verified"), 4);
  }

 @Test(priority = 37) 
  public void buyELoadTransactionValidationAfterMinimizingApp_BE_TC_064() throws Exception {
     MLWalletBusinessLogic.buyELoadTransactionValidationAfterMinimizingApp_BE_TC_064(prop.getproperty("Branch_Verified"), 4);
  }

  @Test(priority = 38)
  public void buyELoadMaximumTransactionForSemiVerifiedTier_BE_TC_30() throws Exception
  {
	 MLWalletBusinessLogic.buyELoadMaximumTransactionForSemiVerifiedTier_BE_TC_30(prop.getproperty("Semi_Verified"),2);
  }

  @Test(priority = 39)
  public void buyELoadMaximumLimitForSemiVerifiedTier_BE_TC_33() throws Exception
  {
	MLWalletBusinessLogic.buyELoadMaximumLimitForSemiVerifiedTier_BE_TC_33(prop.getproperty("Semi_Verified"),2);
  }

  @Test(priority = 40)
  public void buyELoadMaximumTransactionForFullyVerifiedTier_BE_TC_36() throws Exception
  {
	MLWalletBusinessLogic.buyELoadMaximumTransactionForFullyVerifiedTier_BE_TC_36(prop.getproperty("Fully_Verified"),2);
  }

  @Test(priority = 41)
  public void buyELoadMaximumLimitForFullyVerifiedTier_BE_TC_39() throws Exception
  {
	MLWalletBusinessLogic.buyELoadMaximumLimitForFullyVerifiedTier_BE_TC_39(prop.getproperty("Fully_Verified"),2);
  }

  @Test(priority = 42)
  public void buyELoadMaximumTransactionForBranchVerifiedTier_BE_TC_42() throws Exception
  {
	 MLWalletBusinessLogic.buyELoadMaximumTransactionForBranchVerifiedTier_BE_TC_42(prop.getproperty("Branch_Verified"),2);
  }

  @Test(priority = 43)
  public void buyELoadMaximumTransactionForBuyerTierVerifiedTier_BE_TC_48() throws Exception
  {
	 MLWalletBusinessLogic.buyELoadMaximumTransactionForBuyerTierVerifiedTier_BE_TC_48(prop.getproperty("Buyer_Tier"),2);
  }


}
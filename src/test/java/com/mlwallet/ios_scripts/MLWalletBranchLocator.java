package com.mlwallet.ios_scripts;


import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.business.mlwallet.MLWalletiOSBusinessLogic;

public class MLWalletBranchLocator {

	private MLWalletiOSBusinessLogic MLWalletBusinessLogic;

//	@BeforeTest
	@BeforeSuite
	public void init() throws InterruptedException {
		MLWalletBusinessLogic = new MLWalletiOSBusinessLogic("mlwallet");
	}

//==============================================================================================================//

    @Test(priority = 1)
    public void branchLocatorNavigationFromMPinPage_BL_TC_01() throws Exception {
        MLWalletBusinessLogic.branchLocatorNavigationFromMPinPage_BL_TC_01();
    }

    @Test(priority = 2)
    public void branchLocatorNavigationFromLogInPage_BL_TC_02() throws Exception {
        MLWalletBusinessLogic.branchLocatorNavigationFromLogInPage_BL_TC_02();
    }

    @Test(priority = 3)
    public void branchLocatorHamburgerFunctionality_BL_TC_05() throws Exception {
        MLWalletBusinessLogic.branchLocatorHamburgerFunctionality_BL_TC_05();
    }

    @Test(priority = 4)
    public void branchLocatorBranchesButtonFunctionality_BL_TC_07() throws Exception {
        MLWalletBusinessLogic.branchLocatorBranchesButtonFunctionality_BL_TC_07();
    }

    @Test(priority = 5)
    public void branchLocatorPromosFunctionality_BL_TC_08() throws Exception {
        MLWalletBusinessLogic.branchLocatorPromosFunctionality_BL_TC_08();
    }

    @Test(priority = 6)
    public void branchLocatorBlogFunctionality_BL_TC_09() throws Exception {
        MLWalletBusinessLogic.branchLocatorBlogFunctionality_BL_TC_09();
    }


    @Test(priority = 7)
    public void branchLocatorNewsLettersFunctionality_BL_TC_10() throws Exception {
        MLWalletBusinessLogic.branchLocatorNewsLettersFunctionality_BL_TC_10();
    }

    @Test(priority = 8)
    public void branchLocatorFAQFunctionality_BL_TC_11() throws Exception {
        MLWalletBusinessLogic.branchLocatorFAQFunctionality_BL_TC_11();
    } 





}
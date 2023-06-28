package com.mlwallet.ios_scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.business.mlwallet.MLWalletiOSBusinessLogic;

public class MLWalletLogoutScripts {

    	private MLWalletiOSBusinessLogic MLWalletBusinessLogic;
    	
    	@BeforeSuite
    	public void init() throws InterruptedException {
    		MLWalletBusinessLogic = new MLWalletiOSBusinessLogic("mlwallet");
    	}

//=======================================================================================================================//


    @Test(priority = 1)
    public void logOutMinimizeAndRelaunch_Lout_TC_03() throws Exception {
        MLWalletBusinessLogic.logOutMinimizeAndRelaunch_Lout_TC_03();
    }

    @Test(priority = 2)
    public void logOutAppKillAndRelaunch() throws Exception {
        MLWalletBusinessLogic.logOutAppKillAndRelaunch_Lout_TC_04();
    }

    @Test(priority = 3)
    public void logOUtPopUpValidation_Lout_TC_05() throws Exception {
        MLWalletBusinessLogic.logOUtPopUpValidation_Lout_TC_05("true");
    }

    @Test(priority = 4)
    public void logOutPopUpCancelBtnValidation_Lout_TC_06() throws Exception {
        MLWalletBusinessLogic.logOutPopUpCancelBtnValidation_Lout_TC_06();
    }

    @Test(priority = 5)
    public void logOutPopUpLogOutBtnValidation_Lout_TC_07() throws Exception {
        MLWalletBusinessLogic.logOutPopUpLogOutBtnValidation_Lout_TC_07();
    }

    @Test(priority = 6)
    public void logoutChangeNumberUIValidation_Lout_TC_08() throws Exception {
        MLWalletBusinessLogic.logoutChangeNumberUIValidation_Lout_TC_08();
    }

    @Test(priority = 7)
    public void logInWithChangeNumber_Lout_TC_09() throws Exception {
        MLWalletBusinessLogic.logInWithChangeNumber_Lout_TC_09();
    }



}
package com.wallet.wallet_msir_jour_groupe2.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "com.wallet.wallet_msir_jour_groupe2.cucumber")
public class CucumberTestRunner {

}


package com.medishares.mathwalletsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import static com.medishares.mathwalletsample.Chains.BLOCKCHAIN;
import static com.medishares.mathwalletsample.Chains.CHAIN_EOS;
import static com.medishares.mathwalletsample.Chains.CHAIN_EOSFORCE;
import static com.medishares.mathwalletsample.Chains.CHAIN_ETH;
import static com.medishares.mathwalletsample.Chains.CHAIN_TRX;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void openEos(View view) {
        Intent intent = new Intent(this, SimpleWalletActivity.class);
        intent.putExtra(BLOCKCHAIN, CHAIN_EOS);
        startActivity(intent);
    }

    public void openEth(View view) {
        Intent intent = new Intent(this, SimpleWalletActivity.class);
        intent.putExtra(BLOCKCHAIN, CHAIN_ETH);
        startActivity(intent);
    }

    public void openEosForce(View view) {
        Intent intent = new Intent(this, SimpleWalletActivity.class);
        intent.putExtra(BLOCKCHAIN, CHAIN_EOSFORCE);
        startActivity(intent);
    }

    public void openTrx(View view) {
        Intent intent = new Intent(this, SimpleWalletActivity.class);
        intent.putExtra(BLOCKCHAIN, CHAIN_TRX);
        startActivity(intent);
    }
}

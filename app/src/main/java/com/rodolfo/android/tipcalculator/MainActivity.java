package com.rodolfo.android.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etBillAmount)
    EditText mEtBillAmount;
    @BindView(R.id.tvTipPercent)
    TextView mTvTipPercent;
    @BindView(R.id.tvTipAmount)
    TextView mTvTipAmount;
    @BindView(R.id.tvBillTotalAmount)
    TextView mTvBillTotalAmount;

    float percentage = 0;
    float tipTotal = 0;
    float finalBillAmount = 0;
    float totalBillAmount = 0;

    float REGULAR_TIP_PERCENTAGE = 10;
    float DEFAULT_TIP_PERCENTAGE = 15;
    float EXELLENT_TIP_PERCENTAGE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTipValues();
    }

    private void setTipValues() {
        mTvTipPercent.setText(getString(R.string.main_msg_tippercent, percentage));
        mTvTipAmount.setText(getString(R.string.main_msg_tiptotal, tipTotal));
        mTvBillTotalAmount.setText(getString(R.string.main_msg_billtotalresult, finalBillAmount));
    }

    @OnClick({R.id.ibRegularService, R.id.ibGoodService, R.id.ibExcellentService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibRegularService:
                percentage = REGULAR_TIP_PERCENTAGE;
                break;
            case R.id.ibGoodService:
                percentage = DEFAULT_TIP_PERCENTAGE;
                break;
            case R.id.ibExcellentService:
                percentage = EXELLENT_TIP_PERCENTAGE;
                break;
        }
        calculateFinallBill();
        setTipValues();
    }

    @OnTextChanged(R.id.etBillAmount)
    public void onTextChanged() {
        calculateFinallBill();
        setTipValues();
    }

    private void calculateFinallBill() {
        if (percentage == 0)
            percentage = DEFAULT_TIP_PERCENTAGE;

        if (!mEtBillAmount.getText().toString().equals("") && !mEtBillAmount.getText().toString().equals("."))
            totalBillAmount = Float.valueOf(mEtBillAmount.getText().toString());

        else
            totalBillAmount = 0;

        tipTotal = (totalBillAmount * percentage)/100;
        finalBillAmount = totalBillAmount + tipTotal;
    }
}

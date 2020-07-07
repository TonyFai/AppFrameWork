package com.dxtdkwt.zzh.appframework.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.dxtdkwt.zzh.appframework.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDimmedFragmentActivity extends BottomSheetDialogFragment {

    public static final String TAG = BottomSheetDialogFragment.class.getSimpleName();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.activity_bottom_sheet_dimmed_fragment, null);
        dialog.setContentView(view);
        return dialog;
    }

    public void show(FragmentActivity fragmentActivity){
        show(fragmentActivity.getSupportFragmentManager(),TAG);
    }
}
package com.ibuildapp.moveinandmoveout.fragments.details;


public interface OnItemChangedListener {
    void itemChanged();
    void changesCleared();

    void justLockSwipe();

    void justUnlockSwipe();

    void callPhone(String phone);

    void showMap(String address);
}

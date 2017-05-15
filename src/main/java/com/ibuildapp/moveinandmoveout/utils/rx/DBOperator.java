package com.ibuildapp.moveinandmoveout.utils.rx;


import com.ibuildapp.moveinandmoveout.database.EntityManager;
import com.ibuildapp.moveinandmoveout.model.ItemsContainerProp;

import rx.Observable;
import rx.Subscriber;


public class DBOperator implements Observable.Operator<ItemsContainerProp, ItemsContainerProp> {
    private EntityManager manager;

    public DBOperator(){
        manager = EntityManager.getInstance();
    }
    @Override
    public Subscriber<? super ItemsContainerProp> call(final Subscriber<? super ItemsContainerProp> subscriber) {
        return new Subscriber<ItemsContainerProp>() {
            @Override
            public void onCompleted() {
                if (!subscriber.isUnsubscribed())
                    subscriber.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                if (!subscriber.isUnsubscribed())
                    subscriber.onError(e);
            }

            @Override
            public void onNext(ItemsContainerProp container) {
                if (!subscriber.isUnsubscribed()) {
                    manager.saveToDbProp(container);
                    subscriber.onNext(container);
                }
            }
        };
    }
}
